package strzelecki.karol.masprojekt.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import strzelecki.karol.masprojekt.model.Tyre.CarTyres;
import strzelecki.karol.masprojekt.model.Tyre.MotorcycleTyreType;
import strzelecki.karol.masprojekt.model.Tyre.PhysicalParameters;
import strzelecki.karol.masprojekt.model.Tyre.VehicleType;

import java.util.Optional;

public interface PhysicalParametersRepo extends CrudRepository<PhysicalParameters, Long> {

    @Query("SELECT p from PhysicalParameters p LEFT JOIN FETCH p.tyres")
Optional<PhysicalParameters> findById(Long id);




  @Query("select p from PhysicalParameters p  join fetch p.tyres t WHERE t.vehicleType = :v AND TYPE(t) = CarTyres")
    Iterable<PhysicalParameters> findAllByVehicleType(@Param("v") VehicleType v);

    @Query("select p from PhysicalParameters p  join fetch p.tyres t WHERE t.vehicleType = :v AND TYPE(t) = CarTyres AND p.rim = :r")
    Iterable<PhysicalParameters> findAllByVehicleTypeAndRim(@Param("v") VehicleType v, @Param("r") int r);


    @Query("select p from PhysicalParameters p  join fetch p.tyres t WHERE t.vehicleType = :v AND TYPE(t) = CarTyres AND p.rim = :r AND p.width = :w")
    Iterable<PhysicalParameters> findAllByVehicleTypeAndRimAndWidth(@Param("v") VehicleType v, @Param("r") int r, @Param("w") int w);


  @Query("select p from PhysicalParameters p  join fetch p.tyres t WHERE t.motorcycleTyreType = :v AND TYPE(t) = MotorcycleTyres")
  Iterable<PhysicalParameters> findAllByMotorcycleTyreType(@Param("v") MotorcycleTyreType v);

  @Query("select p from PhysicalParameters p  join fetch p.tyres t WHERE t.motorcycleTyreType = :v AND TYPE(t) = MotorcycleTyres AND p.rim = :r")
  Iterable<PhysicalParameters> findAllByMotorcycleTyreTypeAndRim(@Param("v") MotorcycleTyreType v, @Param("r") int r);

  @Query("select p from PhysicalParameters p  join fetch p.tyres t WHERE t.motorcycleTyreType = :v AND TYPE(t) = MotorcycleTyres AND p.rim = :r AND p.width = :w")
  Iterable<PhysicalParameters> findAllByMotorcycleTyreTypeAndRimAndWidth(@Param("v") MotorcycleTyreType v, @Param("r") int r, @Param("w") int w);





//
//
//    "select s from PhysicalParameters p, IN(p.tyres) s WHERE s.vehicleType = :v"

//
//    @Query("SELECT p from PhysicalParameters p LEFT JOIN FETCH p.tyres where TYPE p.tyres = CarTyres")
//Optional<PhysicalParameters> findAllCarTyres();




}
