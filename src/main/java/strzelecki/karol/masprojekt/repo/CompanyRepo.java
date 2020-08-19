package strzelecki.karol.masprojekt.repo;

import org.springframework.data.repository.CrudRepository;
import strzelecki.karol.masprojekt.model.Company;

public interface CompanyRepo extends CrudRepository<Company, Long> {
}
