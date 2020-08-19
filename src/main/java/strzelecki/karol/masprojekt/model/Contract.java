package strzelecki.karol.masprojekt.model;

import strzelecki.karol.masprojekt.model.person.Customer;
import strzelecki.karol.masprojekt.model.person.Employee;
import strzelecki.karol.masprojekt.model.warehouse.Warehouse;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long Id;

    @OneToOne
    @MapsId
    private Salary salary;
    @NotNull
    private LocalDate startDate;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;


    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;


    public Contract() {
    }

    public Contract(BigDecimal salary, LocalDate startDate, Warehouse warehouse, Employee employee) {
        Salary tmp = new Salary(salary);
        setSalary(tmp);
        setWarehouse(warehouse);
        setEmployee(employee);
        this.startDate = startDate;
    }

    public void setSalary(Salary salary){
        if(this.salary == null) {
            this.salary = salary;
            salary.setContract(this);
        }
    }


    public void removeSalary(){
        if(this.salary != null) {
            Salary tmp = this.salary;
            this.salary = null;
            tmp.setContract(null);

        }
    }
    private void setWarehouse(Warehouse warehouse){
        if(warehouse == null){
            throw new IllegalArgumentException("warehouse cannot be null");
        }
        this.warehouse = warehouse;
        warehouse.addContract(this);
    }

    public void updateWarehouse(Warehouse warehouse){
        if(warehouse != this.warehouse){
            this.warehouse = warehouse;
        }
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    private void setEmployee(Employee employee){
        if(employee == null){
            throw new IllegalArgumentException("employee cannot be null");
        }
        this.employee = employee;
        employee.addContract(this);
    }

    public Employee getEmployee() {
        return employee;
    }

    public void remove(){

        Contract tmp = this;
        if (this.warehouse != null){
            Warehouse tmpWarehouse =this.warehouse;
            this.warehouse = null;
            tmpWarehouse.removeContract(this);

        }

        if(this.employee != null){
            Employee tmpEmployee = this.employee;
            this.employee = null;
            tmpEmployee.removeContract(this);

        }

        if(this.salary != null) {
            Salary tmp1 = this.salary;
            this.salary = null;
            tmp1.setContract(null);

        }

    }

    @Override
    public String toString() {
        return  "\npensja: " + salary.getNetGrossSalary() +
                "\ndata rozpoczecia: " + startDate;
    }
}
