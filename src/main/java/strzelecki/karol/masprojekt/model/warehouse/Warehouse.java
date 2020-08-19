package strzelecki.karol.masprojekt.model.warehouse;


import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import strzelecki.karol.masprojekt.model.Address;
import strzelecki.karol.masprojekt.model.Contract;
import strzelecki.karol.masprojekt.model.Delivery;
import strzelecki.karol.masprojekt.model.Tyre.TyreSet;
import strzelecki.karol.masprojekt.repo.WarehouseRepo;


import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;



@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        discriminatorType =  DiscriminatorType.STRING,
        name="warehouse_type"
)
@DynamicUpdate
public abstract class Warehouse {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long wId;

    @NotBlank
    private String name;

    @NotNull
    private LocalDate dateOfCommencement;

    @OneToOne(cascade = CascadeType.ALL,  orphanRemoval = true)
    @JoinColumn(
            name= "ADDRESS_ID"
    )
    private Address address;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=3, fraction=2)
    private BigDecimal regularFee;

    @OneToMany(mappedBy = "warehouse", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private Set<Contract> contracts = new HashSet<>();

    @OneToMany(mappedBy = "warehouse", cascade = {CascadeType.PERSIST,CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private Set<Delivery> deliveries = new HashSet<>();

    @OneToMany(mappedBy = "warehouse", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    private Set<TyreSet> tyreSets = new HashSet<>();





    public Warehouse() {
    }


    public Warehouse(@NotBlank String name, @NotNull LocalDate dateOfCommencement, @NotNull Address address, @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 3, fraction = 2) BigDecimal regularFee) {
        this.name = name;
        this.dateOfCommencement = dateOfCommencement;
        setAddress(address);
        this.regularFee = regularFee;
    }

    public Warehouse(Long wId, @NotBlank String name, @NotNull LocalDate dateOfCommencement, Address address, @DecimalMin(value = "0.0", inclusive = false) @Digits(integer = 3, fraction = 2) BigDecimal regularFee) {



        this.name = name;
        this.dateOfCommencement = dateOfCommencement;
        this.address = address;
        this.regularFee = regularFee;
    }

    public Warehouse(Warehouse warehouse){


        Warehouse tmp = warehouse;




        this.wId = tmp.getwId();
        this.name = tmp.getName();
        this.dateOfCommencement = tmp.getDateOfCommencement();
        this.address = tmp.getAddress();
        this.regularFee = tmp.getRegularFee();
        setAddress(tmp.getAddress());

        Set<Contract> tmpContracts1 = tmp.getContracts();
        Set<TyreSet> tmpTyres = tmp.getTyreSets();
        Set<Delivery> tmpDeliveries = tmp.getDeliveries();

        Iterator<Contract> itr1 = tmpContracts1.iterator();
        while(itr1.hasNext()){
            Contract cTmp = itr1.next();
            itr1.remove();
            addContract(cTmp);
            cTmp.updateWarehouse(this);
        }



        Iterator<TyreSet> itr = tmpTyres.iterator();

        while(itr.hasNext()){
            TyreSet tmp1 = itr.next();
            itr.remove();
            addTyreSet(tmp1);
        }


        Iterator<Delivery> itr3 = tmpDeliveries.iterator();
        while(itr3.hasNext()){
            Delivery tDel = itr3.next();
            itr3.remove();
            addDelivery(tDel);
        }

      //  warehouse.setAddress(null);

//        for(Delivery d : tmpDeliveries){
//            addDelivery(d);
//        }




//        Set<Delivery> deliveries = tmp.getDeliveries();
//
//        for(Delivery d = deliveries){
//
//        }

//        warehouse = null;
//        warehouse.setAddress(null);
//        warehouse.setDateOfCommencement(null);
//        warehouse.setwId(null);
//        warehouse.setName(null);
//        warehouse.setRegularFee(null);
//        warehouse.setContracts(null);
//        warehouse.setDeliveries(null);
//        warehouse.setTyreSets(null);




    }

    public Long getwId() {
        return wId;
    }

    public void setwId(Long wId) {
        this.wId = wId;
    }

    public BigDecimal getRegularFee() {
        return regularFee;
    }

    public void setRegularFee(BigDecimal regularFee) {
        this.regularFee = regularFee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfCommencement() {
        return dateOfCommencement;
    }

    public void setDateOfCommencement(LocalDate dateOfCommencement) {
        this.dateOfCommencement = dateOfCommencement;
    }

    public Address getAddress() {
        return address;
    }

    public void removeAddress() {
        if(this.address != null) {
            Address tmp = this.address;
            this.address = null;
            tmp.setWarehouse(null);
        }

    }


    public void setAddress(Address address) {
        if(this.address != address){
            this.address = address;
            address.setWarehouse(this);
        }

    }


    public void addContract(Contract contract) {
        if (contract == null) {
            throw new IllegalArgumentException("contract cannot be null");
        }


        this.contracts.add(contract);


    }


    public void removeContract(Contract contract) {
        Contract tmp = contract;

        if (contract.getWarehouse() == this) {
            if(contracts.contains(tmp)) {
                this.contracts.remove(contract);
            }
            contract.remove();
        }
    }

    public void addDelivery(Delivery delivery) {

        if (delivery == null) {
            throw new IllegalArgumentException("delivery cannot be null");
        }


        this.deliveries.add(delivery);
        delivery.setWarehouse(this);
    }

    public void removeDelivery(Delivery delivery) {
        Delivery tmp = delivery;

        if (delivery.getWarehouse() == this) {
            if (this.deliveries.size() == 1) {
                throw new IllegalArgumentException("cannot remove the last employee");
            }
            if(deliveries.contains(tmp)) {
                this.deliveries.remove(delivery);
            }
            delivery.remove();
        }
    }

    public void addTyreSet(TyreSet tyreSet){

        if(!this.tyreSets.contains(tyreSet)){

            this.tyreSets.add(tyreSet);
            tyreSet.setWarehouse(this);
        }
    }




    public void removeTyreSet(TyreSet tyreSet){
        if(tyreSet == null){
            throw new IllegalArgumentException("tyre set cannot be null");
        }
        if(this.tyreSets.contains(tyreSet)){
            this.tyreSets.remove(tyreSet);
            tyreSet.setWarehouse(null);
        }
    }

    public Set<Contract> getContracts() {
        return contracts;
    }

    public Set<Delivery> getDeliveries() {
        return deliveries;
    }

    public Set<TyreSet> getTyreSets() {
        return tyreSets;
    }

    public void setContracts(Set<Contract> contracts) {
        this.contracts = contracts;
    }

    public void setDeliveries(Set<Delivery> deliveries) {
        this.deliveries = deliveries;
    }

    public void setTyreSets(Set<TyreSet> tyreSets) {
        this.tyreSets = tyreSets;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "wId=" + wId +
                ", name='" + name + '\'' +
                ", dateOfCommencement=" + dateOfCommencement +
                ", address=" + address +
                ", regularFee=" + regularFee +
                ", contracts=" + contracts +
                ", deliveries=" + deliveries +
                ", tyreSets=" + tyreSets +
                '}';
    }
}
