package strzelecki.karol.masprojekt.repo;

import org.springframework.data.repository.CrudRepository;
import strzelecki.karol.masprojekt.model.Contract;

public interface ContractRepo extends CrudRepository<Contract, Long> {

}
