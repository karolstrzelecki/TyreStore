package strzelecki.karol.masprojekt.model;

import org.hibernate.annotations.DynamicUpdate;
import strzelecki.karol.masprojekt.model.person.Customer;
import strzelecki.karol.masprojekt.model.person.Person;
import strzelecki.karol.masprojekt.model.warehouse.Warehouse;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Entity
@DynamicUpdate
@Table(name="address")
public class Address {

    @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    private String street;
    @NotNull
    private Integer numberOfBuilding;

    private Integer numberOfApartment;

    @NotBlank
    private String city;

    @NotBlank
    private String postalCode;

    @NotBlank
    private static String country = "Polska";

    @OneToOne(mappedBy = "address", orphanRemoval = true)
    private Warehouse warehouse;

    @OneToOne(mappedBy = "address")
    private Company company;

    @OneToOne(mappedBy = "address")
    private Person person;

    @OneToOne(mappedBy = "shippingAddress")
    private Customer customer;




    public Address() {
    }





    public Address( @NotNull String street, @NotNull Integer numberOfBuilding, Integer numberOfApartment, @NotBlank String city, @NotBlank String postalCode) {
        this.street = street;
        this.numberOfBuilding = numberOfBuilding;
        this.numberOfApartment = numberOfApartment;
        this.city = city;
        setPostalCode(postalCode);

     }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumberOfBuilding() {
        return numberOfBuilding;
    }

    public void setNumberOfBuilding(Integer numberOfBuilding) {
        this.numberOfBuilding = numberOfBuilding;
    }

    public Integer getNumberOfApartment() {
        return numberOfApartment;
    }

    public void setNumberOfApartment(Integer numberOfApartment) {
        this.numberOfApartment = numberOfApartment;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        checkPostalCode(postalCode);
        this.postalCode = postalCode;
    }

    public static String getCountry() {
        return country;
    }

    public static void setCountry(String country) {
        Address.country = country;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }


    public void setWarehouse(Warehouse warehouse) {
        if(this.warehouse != warehouse) {
            if (this.warehouse != null) {
                Warehouse tmp= this.warehouse;
                this.warehouse = null;
                tmp.removeAddress();

            }

            if (warehouse != null) {
                this.warehouse = warehouse;
                warehouse.setAddress(this);

            }
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        if(this.customer != customer) {
            if (this.customer != null) {
                Customer tmp= this.customer;
                this.customer = null;
                tmp.removeAddress();

            }

            if (customer != null) {
                this.customer = customer;
                customer.setAddress(this);

            }
        }
    }

    public Person getPerson() {
        return person;
    }
    public void setPerson(Person person) {
        if(this.person != person) {
            if (this.person != null) {
                Person tmp= this.person;
                this.person = null;
                tmp.removeAddress();

            }

            if (person != null) {
                this.person = person;
                person.setAddress(this);

            }
        }
    }
    public Company getCompany() {
        return company;
    }
    public void setCompany(Company company) {
        if(this.company != company) {
            if (this.company != null) {
                Company tmp= this.company;
                this.company = null;
                tmp.removeAddress();

            }

            if (company != null) {
                this.company = company;
                company.setAddress(this);

            }
        }
    }

    public boolean checkPostalCode(String postalCode){
        String regex = "^[\\d]{2}-[\\d]{3}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(postalCode.trim());

        return m.find();
    }



    @Override
    public String toString() {
        if(getNumberOfApartment() == null){
            return "Adres:" +
                    "\n ulica: "+ street + " " + numberOfBuilding+
                    "\n kod pocztowy: " + postalCode + " " + city +
                    "\n kraj: " + country;


        }

        return "Adres:" +
                "\n ulica: "+ street + " " + numberOfBuilding+"/" + numberOfApartment+
                "\n kod pocztowy: " + postalCode + " " + city +
                "\n kraj: " + country;


    }



}


