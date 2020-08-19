package strzelecki.karol.masprojekt.model;

import strzelecki.karol.masprojekt.model.person.Person;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Entity
public class  Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private Long id;


    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer=5, fraction=2)
    private BigDecimal grossSalary; // atrybut prosty, wymagany, pojedynczy, obieku
    private static BigDecimal taxDeductibleExpenses = new BigDecimal( 111.25); // atrybut prosty, pojedynczy, klasowy - wymagany do obliczeń
    private static BigDecimal standardDeduction = new BigDecimal( 43.76);   // atrybut prosty, pojedynczy, klasowy - wymagany do obliczeń

    // W obliczeniach częściej występują stałe dla całej klasy, jednak jest ich spora ilość i występują co najwyżej jednokrotnie, więc w celu zaprezentowania argumentu klasowego wybrałem dwa powyższe

    @OneToOne(cascade = CascadeType.MERGE,  orphanRemoval = true)
    @JoinColumn(
            name= "ADDRESS_ID"
    )
    Contract contract;


    public Salary() {
    }

    public Salary(BigDecimal grossSalary) {
        this.grossSalary = grossSalary;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        if(this.contract != contract) {
            if (this.contract != null) {
                Contract tmp = this.contract;
                this.contract = null;
                tmp.removeSalary();

            }

            if (contract != null) {
                this.contract = contract;
                contract.setSalary(this);

            }
        }
    }


    // Tutaj pozwoliłem sobie na implementacje kilku atrybutów wyliczeniowych
    // Są to składowe pensji brutto i mogą służyć to wyliczenia pensji netto





    public BigDecimal getPensionContribution(){


        return grossSalary.multiply(new BigDecimal(0.0976)).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getDisabilityPensionContribution(){


        return grossSalary.multiply(new BigDecimal(0.015)).setScale(2,RoundingMode.HALF_UP);
    }

    public BigDecimal getSicknessInsurance (){


        return grossSalary.multiply(new BigDecimal(0.0245)).setScale(2,RoundingMode.HALF_UP);

    }

    public BigDecimal getSocialSecuritySum(){

        return getPensionContribution().add(getDisabilityPensionContribution()).add(getSicknessInsurance()).setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getHealthInsurance(){



        return grossSalary.subtract(getSocialSecuritySum()).multiply(new BigDecimal(0.09)).setScale(2, RoundingMode.HALF_UP);

    }

    public BigDecimal getHealthInsuranceDeduction(){


        return grossSalary.subtract(getSocialSecuritySum()).multiply(new BigDecimal(0.0775)).setScale(0, RoundingMode.HALF_UP);

    }

    public BigDecimal getTax(){

        BigDecimal totalTax;
        BigDecimal salaryMinusSocialSecurity= grossSalary.subtract(getSocialSecuritySum()).subtract(taxDeductibleExpenses);





        if (salaryMinusSocialSecurity.multiply(new BigDecimal(12)).compareTo(new BigDecimal(85528)) == -1){

            totalTax = salaryMinusSocialSecurity.multiply(new BigDecimal(0.17));



        }else {
            BigDecimal secondTaxThresholdAmount = salaryMinusSocialSecurity.subtract(new BigDecimal(7127.33)).multiply(new BigDecimal(0.32));
            BigDecimal firstTaxThresholdAmount = new BigDecimal(1211.65);
            totalTax = firstTaxThresholdAmount.add(secondTaxThresholdAmount);
        }

        return  totalTax.subtract(standardDeduction).subtract(getHealthInsuranceDeduction()).setScale(0,RoundingMode.HALF_UP);

    }


    public BigDecimal getNetGrossSalary(){

        return grossSalary.subtract(getHealthInsurance()).subtract(getSocialSecuritySum()).subtract(getTax());



    }

    // Metoda pomocnicza sprawdzająca czy String zawierający wartość przyszłego BigDecimala może zostać w niego przeksztacony
    public boolean checkSalaryString(String salaryString){
        String regex = "^[\\d]{1,}[.]{0,1}[\\d]*$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(salaryString);

        return m.find();


    }


    @Override
    public String toString(){
        return "Wynagrodzenie brutto wynosi: " +grossSalary
                + "\ni zawiera: \n składkę emerytalną: " + getPensionContribution()
                + "\n składkę rentową: " + getDisabilityPensionContribution()
                + "\n składkę chorobową: " +getSicknessInsurance()
                + "\n składkę zdrowotną: " + getHealthInsurance()
                + "\n zaliczkę na podatek dochodowy: " + getTax()
                + "\n ================================== "
                + "\n wynagrodzenie netto wynosi: " +getNetGrossSalary();
    }
}
