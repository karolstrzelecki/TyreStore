package strzelecki.karol.masprojekt.model.person;

import strzelecki.karol.masprojekt.model.Contract;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long emId;

    @Enumerated
    private Position position; //przykład atrybutu złożonego
    @NotNull
    private LocalDate hireDate;// przykład atrybutu konkretnego



    @OneToMany(mappedBy = "employee", cascade = CascadeType.MERGE)
    private Set<Contract> contracts = new HashSet<>();


    @OneToOne(mappedBy = "employee")
    private Person person;

    public Employee() {
    }

    public Employee(Position position, @NotNull LocalDate hireDate) {
        this.position = position;
        this.hireDate = hireDate;
    }

    public Long getEmId() {
        return emId;
    }

    public void setEmId(Long emId) {
        this.emId = emId;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        if(this.person != person) {
            if (this.person != null) {
                Person tmp = this.person;
                this.person = null;
                tmp.removeEmployeeRole();

            }

            if (person != null) {
                this.person = person;
                person.addEmployeeRole(this);

            }
        }
    }

    public void addContract(Contract contract){

        if(contract == null){
            throw new IllegalArgumentException("contract cannot be null");
        }
        if(contract.getEmployee() != this){
            throw new IllegalArgumentException("invalid contract!");
        }
        this.contracts.add(contract);
    }





    public void removeContract(Contract contract){
        if(contract.getEmployee() == this) {
            if(this.contracts.contains(contract)) {
                this.contracts.remove(contract);
            }
            contract.remove();
        }
    }


    @Override
    public String toString() {
        return "Employee{" +
                "emId=" + emId +
                ", position=" + position +
                ", hireDate=" + hireDate +
                ", contracts=" + contracts +
                ", person=" + person +
                '}';
    }
}
