package strzelecki.karol.masprojekt.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import strzelecki.karol.masprojekt.model.person.Employee;
import strzelecki.karol.masprojekt.model.warehouse.Warehouse;

import java.util.Optional;

public interface WarehouseRepo extends CrudRepository<Warehouse, Long> {




    @Override
    @Query("select w from Warehouse w left join fetch w.contracts left join fetch w.deliveries where w.wId = :aLong")
    Optional<Warehouse> findById(@Param("aLong") Long aLong);

    @Query("select w from Warehouse w left join fetch w.contracts where w.name = :name")
    Optional<Warehouse> findByName(@Param("name") String name);
}
