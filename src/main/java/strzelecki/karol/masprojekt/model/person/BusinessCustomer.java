package strzelecki.karol.masprojekt.model.person;


import strzelecki.karol.masprojekt.model.Address;
import strzelecki.karol.masprojekt.model.Company;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="cuId")
public class BusinessCustomer extends Customer {





    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    public BusinessCustomer() {
        super();
    }



    public BusinessCustomer(@NotNull LocalDate joinDate, Address shippingAddress, Company company) {
        super(joinDate, shippingAddress);
        setCompany(company);
    }


    public void removeCompany() {
        if(this.company != null) {
            Company tmp = this.company;
            this.company = null;
            tmp.setBusinessCustomer(null);
        }

    }


    public void setCompany(Company company) {
        if(this.company != company){
            this.company = company;
            company.setBusinessCustomer(this);
        }

    }

}
