package strzelecki.karol.masprojekt.model.Tyre;


import strzelecki.karol.masprojekt.model.Procurement;
import strzelecki.karol.masprojekt.model.warehouse.Warehouse;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class TyreSet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long id;

    private String brand;
    private String model;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Status status;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "physicalParameters_id")
    private PhysicalParameters physicalParameters;


    @OneToMany(mappedBy = "tyreSet", cascade = CascadeType.ALL)
    private List<Tyre> tyres = new ArrayList<>();

    @OneToMany(mappedBy = "tyreSet", cascade = CascadeType.ALL)
    private static Set<Tyre> allTyres = new HashSet<>();
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "procurement_id")
    private Procurement procurement;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH,  CascadeType.REFRESH})
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;

    public TyreSet() {
    }



    public TyreSet(String brand, String model) {
        this.brand = brand;
        this.model = model;
        this.status = Status.available;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPhysicalParameters(PhysicalParameters physicalParameters) {
        if(this.physicalParameters != physicalParameters){
            if(this.physicalParameters != null){
                PhysicalParameters tmp = this.physicalParameters;
                this.physicalParameters = null;
                removeTyres();

                tmp.removeTyreSet(this);
            }
            if (physicalParameters != null){
                this.physicalParameters = physicalParameters;
                physicalParameters.addTyres(this);
            }
        }

    }

    public PhysicalParameters getPhysicalParameters() {
        return physicalParameters;
    }

    public void deletePhysicalParameters(){
        TyreSet tmpSet = this;
        PhysicalParameters tmp = physicalParameters;
        this.physicalParameters = null;
        if(this != null) {
            tmp.removeTyreSet(tmpSet);
        }
    }

    public List<Tyre> getTyres() {
        return tyres;
    }

    public void addTyre(Tyre tyre) throws Exception {
        if(!tyres.contains(tyre)) {
//            if(allTyres.contains(tyre)) {
//                throw new Exception("The tyre is already connected with a whole!");
//            }

            tyres.add(tyre);

            allTyres.add(tyre);
            tyre.setTyreSet(this);
        }
    }

    public void removeTyres(){
        for(int i = 0; i< tyres.size(); i++){
//            Tyre tmp =
//            System.out.println(tmp);
            removeTyre(tyres.get(i));

        }

    }


    public void removeTyre(Tyre tyre){


       if(tyre != null){

            if(tyres.contains(tyre)){

                allTyres.remove(tyre);
                tyres.remove(tyre);
                tyre.setTyreSet(null);
            }
       }

    }



    public void setProcurement(Procurement procurement){
        if(this.procurement != procurement){
            if(this.procurement != null){
                Procurement tmp = this.procurement;
                this.procurement = null;
                tmp.removeTyreSet(this);
            }
            if(procurement != null){
                this.procurement = procurement;
               procurement.addTyreSet(this);
               this.status = Status.sold;
            }
        }
    }

    public void deleteProcurement(){
        TyreSet tmpSet = this;
        Procurement tmp = this.procurement;
        this.procurement = null;
        if(this != null) {
            tmp.removeTyreSet(tmpSet);
        }
    }

    public void setWarehouse(Warehouse warehouse){
        if(this.warehouse != warehouse){
            if(this.warehouse != null){
                Warehouse tmp = this.warehouse;
                this.warehouse = null;
                tmp.removeTyreSet(this);
            }
            if(warehouse != null){
                this.warehouse = warehouse;
                warehouse.addTyreSet(this);
            }
        }
    }


    public void deleteWarehouse(){
        TyreSet tmpSet = this;
        Warehouse tmp = this.warehouse;
        this.warehouse = null;
        if(this != null) {
            tmp.removeTyreSet(tmpSet);
        }
    }



    public String getPrice(){
        BigDecimal tmp = BigDecimal.ZERO;
        for(Tyre tyre: tyres){
            tmp = tmp.add(tyre.getPrice());

        }

        return tmp.toString();
    }

    public String getQuantity(){
        int tmp = tyres.size();

        return String.valueOf(tmp);
    }


    public void sellTyreSet(){
        this.status = Status.sold;
    }


    @Override
    public String toString() {
        return "TyreSet{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
//                ", physicalParameters=" + physicalParameters +
//                ", tyres=" + tyres +
                '}';
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
