package strzelecki.karol.masprojekt.model.Tyre;

import javax.persistence.*;
@Entity
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class MotorcycleTyres extends TyreSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long id;

    @Enumerated
    private MotorcycleTyreType motorcycleTyreType;


    public MotorcycleTyres() {
    }

    public MotorcycleTyres(String brand, String model, MotorcycleTyreType motorcycleTyreType) {
        super(brand, model);
        this.motorcycleTyreType = motorcycleTyreType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MotorcycleTyreType getMotorcycleTyreType() {
        return motorcycleTyreType;
    }

    public void setMotorcycleTyreType(MotorcycleTyreType motorcycleTyreType) {
        this.motorcycleTyreType = motorcycleTyreType;
    }
}
