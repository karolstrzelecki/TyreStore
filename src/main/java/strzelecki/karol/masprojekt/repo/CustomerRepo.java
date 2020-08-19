package strzelecki.karol.masprojekt.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import strzelecki.karol.masprojekt.model.person.Customer;
import strzelecki.karol.masprojekt.model.person.Person;

import java.util.Optional;

public interface CustomerRepo extends CrudRepository<Customer, Long> {

    @Query("select c from Customer c left join fetch c.person p left join fetch p.address where p.pesel = :pesel")
    Optional<Customer> findByPesel(@Param("pesel") String pesel);
}
