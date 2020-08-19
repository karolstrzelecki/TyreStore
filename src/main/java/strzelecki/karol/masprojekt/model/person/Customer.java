package strzelecki.karol.masprojekt.model.person;

import strzelecki.karol.masprojekt.model.Address;
import strzelecki.karol.masprojekt.model.Procurement;
import strzelecki.karol.masprojekt.model.Tyre.TyreSet;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Inheritance( strategy = InheritanceType.JOINED )
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long cuId;

    @NotNull
    private LocalDate JoinDate;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address shippingAddress;



    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Person person;

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.ALL},  fetch = FetchType.EAGER)
    private Set<Procurement> procurements = new HashSet<>();


    public Customer() {
    }

    public Customer(@NotNull LocalDate joinDate, Address shippingAddress) {
        JoinDate = joinDate;

        setAddress(shippingAddress);
    }


    public Long getCuId() {
        return cuId;
    }

    public void setCuId(Long cuId) {
        this.cuId = cuId;
    }

    public LocalDate getJoinDate() {
        return JoinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        JoinDate = joinDate;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        if(this.person != person) {
            if (this.person != null) {
                Person tmp = this.person;
                this.person = null;
                tmp.removeCustomerRole();
                tmp =null;
                removeAddress();

            }

            if (person != null) {
                this.person = person;
                person.addCustomerRole(this);

            }
        }
    }


    public void removeAddress() {
        if(this.shippingAddress != null) {
            Address tmp = this.shippingAddress;
            this.shippingAddress = null;
            tmp.setCustomer(null);
        }

    }


    public void setAddress(Address address) {
        if(this.shippingAddress != address){
            this.shippingAddress = address;
            shippingAddress.setCustomer(this);
        }

    }

    public void addProcurement(Procurement procurement){

        if(!this.procurements.contains(procurement)){

            this.procurements.add(procurement);
            procurement.setCustomer(this);
        }
    }





    public void removeProcurement(Procurement procurement){
        if(procurement == null){
            throw new IllegalArgumentException("tyre set cannot be null");
        }
        if(this.procurements.contains(procurement)){
            this.procurements.remove(procurement);
            procurement.setCustomer(null);
        }
    }
}
