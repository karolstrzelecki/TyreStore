package strzelecki.karol.masprojekt.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import strzelecki.karol.masprojekt.model.Tyre.CarTyres;
import strzelecki.karol.masprojekt.model.Tyre.PhysicalParameters;
import strzelecki.karol.masprojekt.model.Tyre.Status;
import strzelecki.karol.masprojekt.model.Tyre.VehicleType;

import java.util.Optional;

public interface CarTyresRepo extends CrudRepository<CarTyres, Long> {
//    @Query("SELECT physicalParameters from CarTyres c LEFT JOIN FETCH c.physicalParameters")
//    Iterable<PhysicalParameters> findPhysicalParameters();

//    @Query("SELECT physicalParameters from CarTyres c LEFT JOIN FETCH c.physicalParameters")
//    Iterable<PhysicalParameters> findPhysicalParameters();
//left outer join fetch c.tyres

    @Query ("select c from CarTyres c left join fetch c.physicalParameters  where c.status = strzelecki.karol.masprojekt.model.Tyre.Status.available and c.vehicleType = :v and c.physicalParameters.width = :width and c.physicalParameters.rim = :rim and c.physicalParameters.aspectRatio = :aa")
    Iterable<CarTyres> findAllByVehicleTypeAndPhysicalParameters( @Param("v")VehicleType v, @Param("width") int width, @Param("rim")int rim, @Param("aa")int aa);


    @Query("SELECT c from CarTyres c LEFT JOIN FETCH c.tyres  where c.status = strzelecki.karol.masprojekt.model.Tyre.Status.available and c.id =:id")
    Optional<CarTyres> findById(@Param("id") Long id);

//    @Query ("select p from PhysicalParameters p left join fetch p.tyres t where t.vehicleType ")
//    Iterable<PhysicalParameters> findAllByVehicleType(@Param("v")VehicleType v);


}
