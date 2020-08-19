package strzelecki.karol.masprojekt.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import strzelecki.karol.masprojekt.model.Company;
import strzelecki.karol.masprojekt.model.person.Person;

import java.util.Optional;

public interface PersonRepo extends CrudRepository<Person, String> {

    @Query("select p from Person p left join fetch p.address where p.pesel = :pesel")
    Optional<Person> findByPesel(@Param("pesel") String pesel);

}

