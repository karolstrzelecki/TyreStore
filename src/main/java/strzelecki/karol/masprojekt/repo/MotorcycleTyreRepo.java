package strzelecki.karol.masprojekt.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import strzelecki.karol.masprojekt.model.Tyre.MotorcycleTyreType;
import strzelecki.karol.masprojekt.model.Tyre.MotorcycleTyres;


import java.util.Optional;

public interface MotorcycleTyreRepo extends CrudRepository<MotorcycleTyres, Long> {

    @Query("select m from MotorcycleTyres m left join fetch m.physicalParameters   where m.status = strzelecki.karol.masprojekt.model.Tyre.Status.available and m.motorcycleTyreType = :v and m.physicalParameters.width = :width and m.physicalParameters.rim = :rim and m.physicalParameters.aspectRatio = :aa")
    Iterable<MotorcycleTyres> findAllByMotorcycleTyreTypeAndPhysicalParameters(@Param("v") MotorcycleTyreType v, @Param("width") int width, @Param("rim")int rim, @Param("aa")int aa);


    @Query("SELECT m from MotorcycleTyres m LEFT JOIN FETCH m.tyres w where m.status = strzelecki.karol.masprojekt.model.Tyre.Status.available and m.id =:id")
    Optional<MotorcycleTyres> findById(@Param("id") Long id);
}
