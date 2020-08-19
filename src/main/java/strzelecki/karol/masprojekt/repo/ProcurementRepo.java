package strzelecki.karol.masprojekt.repo;

import org.springframework.data.repository.CrudRepository;

import strzelecki.karol.masprojekt.model.Procurement;

public interface ProcurementRepo extends CrudRepository<Procurement, Long> {
}
