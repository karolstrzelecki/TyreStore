package strzelecki.karol.masprojekt.model;

import pl.nip24.client.InvoiceData;
import pl.nip24.client.NIP24Client;
import pl.nip24.client.Number;
import strzelecki.karol.masprojekt.model.person.BusinessCustomer;
import strzelecki.karol.masprojekt.model.person.Customer;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.net.MalformedURLException;

@Entity
@Inheritance( strategy = InheritanceType.JOINED )
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @NotBlank
    @Size(min = 10, max = 10)
    @Column(unique = true)
    private String taxNumber;

    private String companyName;

    @OneToOne(cascade = CascadeType.ALL,  orphanRemoval = true)
    @JoinColumn(
            name= "ADDRESS_ID"
    )
    private Address address;

    @OneToOne(mappedBy = "company")
    private BusinessCustomer businessCustomer;


    public Company() {

    }



    public Company(String taxNumber) {
        this.taxNumber = taxNumber;
        NIP24Client nip24 = null;
        try {
            nip24 = new NIP24Client();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.println(e);
        }
        InvoiceData invoice = nip24.getInvoiceData(Number.NIP, taxNumber);

        if(invoice == null){
            throw new IllegalArgumentException("Problem with taxNumber DataBase/ Wrong taxNumber");
        }
        if (invoice != null) {
            this.companyName = invoice.getName();
           Address address = new Address(invoice.getStreet(), Integer.parseInt(invoice.getStreetNumber()),null, invoice.getCity(), invoice.getPostCode());



        setAddress(address);
        }

    }



    public void setBusinessCustomer(BusinessCustomer customer) {
        if(this.businessCustomer != customer) {
            if (this.businessCustomer != null) {
                BusinessCustomer tmp= this.businessCustomer;
                this.businessCustomer = null;
               tmp.removeCompany();

            }

            if (customer != null) {
                this.businessCustomer = customer;
                customer.setCompany(this);

            }
        }
    }

    public void setAddress(Address address) {
        if(this.address != address){
            this.address = address;
            address.setCompany(this);
        }

    }

    public void removeAddress() {
        if(this.address != null) {
            Address tmp = this.address;
            this.address = null;
            tmp.setCompany(null);
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
