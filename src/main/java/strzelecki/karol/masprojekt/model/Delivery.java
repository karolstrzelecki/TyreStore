package strzelecki.karol.masprojekt.model;

import strzelecki.karol.masprojekt.model.Tyre.TyreSet;
import strzelecki.karol.masprojekt.model.person.Customer;
import strzelecki.karol.masprojekt.model.warehouse.Warehouse;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "provider_id")
    @NotNull
    private Provider provider;


    @ManyToOne(cascade = CascadeType.MERGE)
    @NotNull
    private Warehouse warehouse;


    public Delivery() {
    }

    public Delivery(@NotNull Provider provider, @NotNull Warehouse warehouse) {
       setWarehouse(warehouse);
       setProvider(provider);
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
        provider.addDelivery(this);
    }


    public void setWarehouse(Warehouse warehouse){
        if(this.warehouse != warehouse){
            if(this.warehouse != null){
                Warehouse tmp = this.warehouse;
                this.warehouse = null;
                tmp.removeDelivery(this);
            }
            if(warehouse != null){
                this.warehouse = warehouse;
                warehouse.addDelivery(this);
            }
        }
    }




    public void remove() {
        Delivery tmp = this;
        if(this.provider != null){
            Provider tmpProvider = this.provider;
            this.provider = null;
            tmpProvider.removeDelivery(this);
        }

        if (this.warehouse != null){
            Warehouse tmpWarehouse =this.warehouse;
            this.warehouse = null;
            tmpWarehouse.removeDelivery(this);

        }

    }


    public Provider getProvider() {
        return provider;
    }
}
