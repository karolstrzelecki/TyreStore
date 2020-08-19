package strzelecki.karol.masprojekt.model.person;

import strzelecki.karol.masprojekt.model.Address;
import strzelecki.karol.masprojekt.model.Contract;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.*;

@Entity(name="Person")
@Table(name="person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pId;

    @NotBlank
    @Size(min = 11, max = 11)
    @Column(unique = true)
    private String pesel;

    @NotBlank
    @Size(min = 2, max = 20)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 20)
    private String middleName;

    @ElementCollection
    private List<String> lastName = new ArrayList<>();

    @NotNull
    private LocalDate birthDate;

    @NotBlank
    @Size(min = 9, max = 9)
    private String telephoneNumber;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL,  orphanRemoval = true)
    @JoinColumn(
            name= "SHIPPING_ADDRESS_ID"
    )
    private Address address;




    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "customer_id", referencedColumnName = "cuId")
    private Customer customer;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "employee_id", referencedColumnName = "emId")
    private Employee employee;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "candidate_id", referencedColumnName = "id")
    private Candidate candidate;


    public Person() {
    }

    public Person(@NotBlank @Size(min = 11, max = 11) String pesel, @NotBlank @Size(min = 2, max = 20) String firstName, @NotBlank @Size(min = 2, max = 20) String middleName, String lastName,  @NotBlank @Size(min = 9, max = 9) String telephoneNumber, @NotNull Address address) {
        setPesel(pesel);
        this.firstName = firstName;
        this.middleName = middleName;
        addLastName(lastName);

        this.telephoneNumber = telephoneNumber;
        setAddress(address);
    }



    public void addCustomerRole(Customer customer){
        if(this.customer == null) {
            this.customer = customer;
            customer.setPerson(this);
        }
    }


    public void removeCandidateRole(){
        if(this.candidate != null) {
           Candidate tmp = this.candidate;
            this.candidate = null;
            tmp.setPerson(null);

        }
    }



    public void addCandidateRole(Candidate candidate){
        if(this.candidate == null) {
            this.candidate = candidate;
            candidate.setPerson(this);
        }
    }


    public void removeCustomerRole(){
        if(this.customer != null) {
            Customer tmp = this.customer;
            this.customer = null;
            tmp.setPerson(null);

        }
    }


    public void addEmployeeRole(Employee employee){
        if(this.employee != employee){
            this.employee = employee;
            employee.setPerson(this);
        }

    }


    public void removeEmployeeRole(){
        if(this.employee != null) {
                Employee tmp = this.employee;
                this.employee = null;
                tmp.setPerson(null);
        }
    }

    public void removeAddress() {
        if(this.address != null) {
            Address tmp = this.address;
            this.address = null;
            tmp.setPerson(null);
        }

    }


    public void setAddress(Address address) {
        if(this.address != address){
            this.address = address;
            address.setPerson(this);
        }

    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public List<String> getLastName() {
        return lastName;
    }

    public void setLastName(List<String> lastName) {
        this.lastName = lastName;
    }

    public void addLastName(String lastNameVal){
        if (lastNameVal == null || "".equals(lastNameVal.trim())){
            throw new IllegalArgumentException("last name cannot be null or empty")   ;
        }
        if (this.lastName.size() >= 2){
            throw new IllegalArgumentException("In compliance with polish law person can have no more than two last names");
        }

        this.lastName.add(lastNameVal);
    }


    public void removeLastName(String lastNameVal){
        if(this.lastName.size() <= 1){
            throw new IllegalArgumentException("last name list must contain at least one element");
        }
        this.lastName.remove(lastNameVal);
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddress() {
        return address;
    }


    private boolean checkPESEL(String pesel){
        int [] weight = {1, 3, 7, 9, 1, 3, 7 ,9 ,1 ,3};
        int sum = 0;

        int[] tab = pesel.chars().map(Character::getNumericValue).toArray();
        for (int i=0; i<10; i++){
            sum += tab[i] * weight[i];
        }

        int checkSum= Integer.parseInt(pesel.substring(10, 11));

        sum %= 10;
        sum = 10 - sum;
        sum %= 10;

        return (sum == checkSum);

    }

    public void setPesel(String pesel) {


        if (pesel.length() != 11) {
            throw new IllegalArgumentException("PESEL number should contain 11 digits!");
        }

        if (checkPESEL(pesel) == false) {
            throw new IllegalArgumentException("PESEL number is invalid, wrong checksum!");
        }


        this.pesel = pesel;
        setBirthDateFromPESEL(pesel);
    }


    private void setBirthDateFromPESEL(String pesel) {

        int year;
        int month;
        int day;

        if (!checkPESEL(pesel)) {
            throw new IllegalArgumentException("PESEL number is invalid, wrong checksum!");
        }

        int[] tab = pesel.chars().map(Character::getNumericValue).toArray();

        year = 10 * tab[0];
        year += tab[1];
        month = 10 * tab[2];
        month += tab[3];
        day = 10 * tab[4];
        day += tab[5];


        if (month > 80 && month < 93) {
            year += 1800;
        } else if (month > 0 && month < 13) {
            year += 1900;
        } else if (month > 20 && month < 33) {
            year += 2000;
        }


        if (month > 80 && month < 93) {
            month -= 80;
        } else if (month > 20 && month < 33) {
            month -= 20;
        }


        this.birthDate = LocalDate.of(year, month, day);


    }

    // Metody dla klasy dziedziczącej Employee


    @Override
    public String toString() {
        String tmp = new String();
        if(customer != null){
            tmp += "Klient, ";
        }
        if(employee != null){
            tmp += "Pracownik, ";
        }
        tmp += getFirstName();
        return tmp;
    }
}