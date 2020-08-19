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
@DiscriminatorValue("rented")
@DynamicUpdate
public class RentedWarehouse extends Warehouse {




    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=6, fraction=2)
    private BigDecimal deposit;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=6, fraction=2)
    private BigDecimal rent;


    public RentedWarehouse() {
    }

    public RentedWarehouse(@NotBlank String name, @NotNull LocalDate dateOfCommencement, @NotNull Address address, @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 6, fraction = 2) BigDecimal regularFee, @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 6, fraction = 2) BigDecimal deposit, @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 6, fraction = 2) BigDecimal rent) {
        super(name, dateOfCommencement, address, regularFee);
        this.deposit = deposit;
        this.rent = rent;
    }

    public RentedWarehouse(@NotNull Warehouse warehouse, @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 6, fraction = 2) BigDecimal deposit, @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 6, fraction = 2) BigDecimal rent) {
        super(warehouse);
        this.deposit = deposit;
        this.rent = rent;
    }


    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }
}
