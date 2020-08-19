package strzelecki.karol.masprojekt.repo;

import org.springframework.data.repository.CrudRepository;
import strzelecki.karol.masprojekt.model.Delivery;

public interface DeliveryRepo extends CrudRepository<Delivery, Long> {
}
