package strzelecki.karol.masprojekt.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import strzelecki.karol.masprojekt.model.person.Employee;
import strzelecki.karol.masprojekt.model.person.Person;

import java.util.Optional;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {

    @Query("select p from Employee p left join fetch p.person x left join fetch p.contracts where x.pesel = :pesel")
    Optional<Employee> findByPesel(@Param("pesel") String pesel);
}
