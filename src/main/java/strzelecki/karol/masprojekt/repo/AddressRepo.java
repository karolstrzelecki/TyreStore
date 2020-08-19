package strzelecki.karol.masprojekt.repo;

import org.springframework.data.repository.CrudRepository;
import strzelecki.karol.masprojekt.model.Address;

public interface AddressRepo extends CrudRepository<Address, Long> {
}
