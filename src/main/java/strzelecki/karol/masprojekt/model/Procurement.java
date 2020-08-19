package strzelecki.karol.masprojekt.model;

import strzelecki.karol.masprojekt.model.Tyre.Status;
import strzelecki.karol.masprojekt.model.Tyre.TyreSet;
import strzelecki.karol.masprojekt.model.person.Customer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Procurement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;


    @Enumerated(EnumType.STRING)
    @NotNull
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "procurement", cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    private Set<TyreSet> tyreSets = new HashSet<>();



    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer")
    private Customer customer;


    private LocalDate dateOfOrder;

    public Procurement() {
    }

    public Procurement(Customer customer) {
        this.dateOfOrder = LocalDate.now();
        setCustomer(customer);
        this.orderStatus = OrderStatus.Accepted;

    }





    public void addTyreSet(TyreSet tyreSet){

        if(!this.tyreSets.contains(tyreSet)){
            tyreSet.setProcurement(this);
            this.tyreSets.add(tyreSet);


        }

    }





    public void removeTyreSet(TyreSet tyreSet){
        if(tyreSet == null){
            throw new IllegalArgumentException("tyre set cannot be null");
        }
        if(this.tyreSets.contains(tyreSet)){
            this.tyreSets.remove(tyreSet);
            tyreSet.setProcurement(null);
        //    tyreSet.setStatus(Status.available);
        }
    }


    public void setCustomer(Customer customer){
        if(this.customer != customer){
            if(this.customer != null){
                Customer tmp = this.customer;
                this.customer = null;
                tmp.removeProcurement(this);
            }
            if(customer != null){
                this.customer = customer;
                customer.addProcurement(this);
            }
        }
    }

    public void deleteCustomer(){
        Procurement tmpSet = this;
        Customer tmp = this.customer;
        this.customer = null;
        if(this != null){
            tmp.removeProcurement(tmpSet);
        }
        }


    @Override
    public String toString() {
        return "Procurement{" +
                "id=" + id +
                ", orderStatus=" + orderStatus +
                ", tyreSets=" + tyreSets +
                ", customer=" + customer +
                ", dateOfOrder=" + dateOfOrder +
                '}';
    }
}



