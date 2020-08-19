package strzelecki.karol.masprojekt.model.Tyre;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
public class Tyre {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long id;

    @NotBlank
    private String dateOfManufactureCode;
    @NotNull
    private BigDecimal heightOfTread;
    @NotNull
    private BigDecimal price;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "tyreSet_id")
    private TyreSet tyreSet;

    public Tyre() {
    }

    private Tyre(TyreSet tyreSet, String dateOfManufactureCode, BigDecimal heightOfTread, BigDecimal price) {

        this.tyreSet = tyreSet;
        setDateOfManufactureCode(dateOfManufactureCode);
        setHeightOfTread(heightOfTread);
        setPrice(price);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static Tyre createPart(TyreSet tyreSet, String dateOfManufactureCode, BigDecimal heightOfTread, BigDecimal price) throws Exception {
        if (tyreSet == null) {
            throw new Exception("The given tyre set does not exist!");
        }


        Tyre tyre = new Tyre(tyreSet, dateOfManufactureCode, heightOfTread, price);

        // Add to the whole
        tyreSet.addTyre(tyre);

        return tyre;
    }

//    public void removePart(){
//        if(this.tyreSet != null) {
//            TyreSet tmp = this.tyreSet;
//            this.tyreSet = null;
//            tmp.removeTyre(this);
//
//        }
//    }

    public TyreSet getTyreSet() {
        return tyreSet;
    }

    public void setTyreSet(TyreSet tyreSet) {
        this.tyreSet = tyreSet;
    }

    public String getDateOfManufactureCode() {
        return dateOfManufactureCode;
    }

    public BigDecimal getHeightOfTread() {
        return heightOfTread;
    }

    public BigDecimal getPrice() {
        return price;
    }


    public void setDateOfManufactureCode(String dateOfManufactureCode) {
        if (dateOfManufactureCode == null || "".equals(dateOfManufactureCode.trim())) {
            throw new IllegalArgumentException("date of manufacture code cannot be null or empty!");
        }
        if (dateOfManufactureCode.matches("[0-9]+") && dateOfManufactureCode.length() != 4) {
            throw new IllegalArgumentException("date of manufacture code should contain four digits");
        }
        int week = Integer.parseInt(dateOfManufactureCode.substring(0, 1));
        int year = Integer.parseInt(dateOfManufactureCode.substring(2, 3));

        // Pozostałą walidację tego parametru (sprawdzenie tygodnia i czy przypadkiem nie została wyprodukowana w przyszłości
        // Zaimplementuję w projekcie głównym

        this.dateOfManufactureCode = dateOfManufactureCode;
    }

    public void setHeightOfTread(BigDecimal heightOfTread) {

        if (heightOfTread.equals(null)) {
            throw new IllegalArgumentException("height of thread cannot be null");
        }

        if (heightOfTread.compareTo(new BigDecimal(1.6)) < 0) {
            throw new IllegalArgumentException("Due to polish law height of thread cannot be lower than 1.6 mm");
        }


        this.heightOfTread = heightOfTread;
    }

    public void setPrice(BigDecimal price) {
        if (heightOfTread.equals(null)) {
            throw new IllegalArgumentException("price cannot be null");
        }

        if (price.compareTo(new BigDecimal(0)) <= 0) {
            throw new IllegalArgumentException("price cannot be lower or equal to 0");
        }
        this.price = price;

    }




    @Override
    public String toString() {
        return "\nkod daty produkcji: " + dateOfManufactureCode + " wysokość bieżnika: " + heightOfTread + "  cena: " +price;

    }


}
