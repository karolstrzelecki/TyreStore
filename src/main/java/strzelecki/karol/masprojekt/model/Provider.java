package strzelecki.karol.masprojekt.model;

import strzelecki.karol.masprojekt.model.Tyre.PhysicalParameters;
import strzelecki.karol.masprojekt.model.Tyre.TyreSet;
import strzelecki.karol.masprojekt.model.person.Employee;

import javax.persistence.*;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class Provider extends Company {





    @OneToMany(mappedBy = "provider", cascade = {CascadeType.ALL},  fetch = FetchType.EAGER)
    private Set<Delivery> deliveries = new HashSet<>();


    public Provider() {
        super();
    }

    public Provider(String taxNumber) throws MalformedURLException {
        super(taxNumber);
    }


    public void removeDelivery(Delivery delivery) {


            Delivery tmp = delivery;

            if (delivery.getProvider() == this) {
                if (this.deliveries.size() == 1) {
                    throw new IllegalArgumentException("cannot remove the last employee");
                }
                if(deliveries.contains(tmp)) {
                    this.deliveries.remove(delivery);
                }
                delivery.remove();
            }


    }

    public void addDelivery(Delivery delivery) {

        if (delivery == null) {
            throw new IllegalArgumentException("delivery cannot be null");
        }
        if (delivery.getProvider() != this) {
            throw new IllegalArgumentException("invalid delivery!");
        }
        this.deliveries.add(delivery);

    }
}




