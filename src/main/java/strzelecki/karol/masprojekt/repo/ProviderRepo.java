package strzelecki.karol.masprojekt.repo;

import org.springframework.data.repository.CrudRepository;
import strzelecki.karol.masprojekt.model.Provider;

public interface ProviderRepo extends CrudRepository<Provider, Long> {
}
