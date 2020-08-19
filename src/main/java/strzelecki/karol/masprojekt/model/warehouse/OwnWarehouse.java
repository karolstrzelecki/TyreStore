package strzelecki.karol.masprojekt.model.warehouse;

import org.hibernate.annotations.DynamicUpdate;
import strzelecki.karol.masprojekt.model.Address;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("own")
@DynamicUpdate
public class OwnWarehouse extends Warehouse {


    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=6, fraction=2)
    private BigDecimal rateMortgage;


    public OwnWarehouse() {
    }

    public OwnWarehouse(@NotBlank String name, @NotNull LocalDate dateOfCommencement, @NotNull Address address, @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 6, fraction = 2) BigDecimal regularFee,  @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 6, fraction = 2) BigDecimal rateMortgage) {
        super(name, dateOfCommencement, address, regularFee);
        this.rateMortgage = rateMortgage;
    }

    public OwnWarehouse(@NotNull Warehouse warehouse, @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 6, fraction = 2) BigDecimal rateMortgage) {
        super(warehouse);

        this.rateMortgage = rateMortgage;
    }



    public BigDecimal getRateMortgage() {
        return rateMortgage;
    }

    public void setRateMortgage(BigDecimal rateMortgage) {
        this.rateMortgage = rateMortgage;
    }


}
