package strzelecki.karol.masprojekt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import strzelecki.karol.masprojekt.model.*;
import strzelecki.karol.masprojekt.model.Tyre.*;
import strzelecki.karol.masprojekt.model.person.*;
import strzelecki.karol.masprojekt.model.warehouse.OwnWarehouse;
import strzelecki.karol.masprojekt.model.warehouse.RentedWarehouse;
import strzelecki.karol.masprojekt.model.warehouse.Warehouse;
import strzelecki.karol.masprojekt.repo.*;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.time.LocalDate;

@Component
public class DataInitializer {
    @Autowired
    private PhysicalParametersRepo physicalParametersRepo;

    @Autowired
    private PersonRepo personRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ProcurementRepo procurementRepo;

    @Autowired
    WarehouseRepo warehouseRepo;

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    ContractRepo contractRepo;

    @Autowired
    AddressRepo addressRepo;

    @Autowired
    ProviderRepo providerRepo;

    @Autowired
    DeliveryRepo deliveryRepo;

    @Autowired
    CarTyresRepo carTyresRepo;

    @Autowired
    TyreSetRepo tyreSetRepo;


    public void initData() throws MalformedURLException {

//
//
//        // Wprowadzam kilka osób
//        // Numery PESEL zostały wygenerowane losowo
//
////        90092125914
////        66062479312
////        66042257417
////        82052948446
////        92062339244
////        57120193723
////        58112431858
////        83091752755
////        60052648399
////        72042951763
//
////
//////        // Numery NIP dostepne w
////        Address a1 = new Address( "Cichociemnych", 134, 12, "Kraków", "31-572");
////        Person p1 = new Person("90092125914", "Kazimierz", null, "Rutkowski","975729208", a1);
////
////
////        Address a2 = new Address( "Gniazdowska", 150, null, "Warszawa", "03-637");
////        Person p2 = new Person("66062479312", "Przemek", "Krystian", "Pawłowski","948308833", a2);
////
////        Address a3 = new Address( "Gąsiorowskiego Wacława", 150, null, "Warszawa", "03-637");
////        Person p3 = new Person("66042257417", "Fryderka", "Anna", "Nowak","975729208", a3);
////
////        Address a4 = new Address( "Lubraniecka", 40, null, "Elbląg", "82-301");
////        Person p4 = new Person("82052948446", "Gabrysia", "Katarzyna", "Grabowska","856457652", a4);
////
////
////        Address a5 = new Address( "Podchorążych", 79, 48, "Warszawa", "00-722");
////        Person p5 = new Person("58112431858", "Radomił", "Andrzej", "Woźniak","612284115", a5);
////
////        Address a6 = new Address( "Uroczysko", 94, null, "Szczecin", "00-129");
////        Person p6 = new Person("92062339244", "Tadeusz", "null", "Pawłowski","744575358", a6);
////
////        Address a7 = new Address( "Młynkówka Królewska", 100, null, "Kraków", "30-134");
////        Person p7 = new Person("57120193723", "Celestyna", "Jolanta", "Zawdzka","772417273", a7);
////
////        Address a8 = new Address( "Willowa", 15, null, "Warszawa", "03-137");
////        Person p8 = new Person("83091752755", "Wojciecha", "Adrianna", "Olszewska", "772417273", a8);
////
////        Address a9 = new Address( "Rynek Śródecki", 15, null, "Warszawa", "03-137");
////        Person p9 = new Person("60052648399", "Bronisława", null, "Jabłońska","396515027", a9);
////
////
////
////
////
////        Employee e1 = new Employee(Position.storekeeper, LocalDate.now());
////        p1.addEmployeeRole(e1);
////
////        Employee e2 = new Employee(Position.storekeeper, LocalDate.now());
////        p2.addEmployeeRole(e2);
////
////        Employee e3 = new Employee(Position.storekeeper, LocalDate.now());
////        p3.addEmployeeRole(e3);
////
////        Employee e4 = new Employee(Position.storekeeper, LocalDate.now());
////        p4.addEmployeeRole(e4);
////
////// Numery NIP obsługiwane przez testową (nieodpłatną) wersję NIP24 api
//////        7272445205
//////        5213003700
//////        5252242171
//////        7171642051
////
////        Address a10 = new Address("Mała", 18, 12,"Kielce", "25-710");
////
////
////
////        BusinessCustomer bc = new BusinessCustomer(LocalDate.now(), a10,  new Company("7272445205"));
////
////        p4.addCustomerRole(new Customer(LocalDate.now(), null));
////        p5.addCustomerRole(new Customer(LocalDate.now(), null));
////        p6.addCustomerRole(new Customer(LocalDate.now(), null));
////        p7.addCustomerRole(new Customer(LocalDate.now(), null));
////        p8.addCustomerRole(new Customer(LocalDate.now(), null));
////        p9.addCustomerRole(bc);
////
////
////
////        personRepo.save(p1);
////        personRepo.save(p2);
////        personRepo.save(p3);
////        personRepo.save(p4);
////        personRepo.save(p5);
////        personRepo.save(p6);
////        personRepo.save(p7);
////        personRepo.save(p8);
////        personRepo.save(p9);
////
////        System.out.println(personRepo.findByPesel("90092125914"));
////
////
////
////
////        personRepo.delete(personRepo.findByPesel("90092125914").get());
////        personRepo.delete(personRepo.findByPesel("60052648399").get());
////
//
//
//
//        PhysicalParameters pp10 = new PhysicalParameters(135, 30,19);
//
//        MotorcycleTyres mt1 = new MotorcycleTyres("Goodyear", "Ultrafast", MotorcycleTyreType.tubeTyre );
//        try {
//            Tyre.createPart(mt1, "1220", new BigDecimal("6.2"), new BigDecimal("120"));
//            Tyre.createPart(mt1, "1220", new BigDecimal("6.0"), new BigDecimal("120"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        MotorcycleTyres mt2 = new MotorcycleTyres("Hankook", "Motospeed", MotorcycleTyreType.tubeTyre );
//        try {
//            Tyre.createPart(mt2, "1220", new BigDecimal("6.2"), new BigDecimal("120"));
//            Tyre.createPart(mt2, "1220", new BigDecimal("6.0"), new BigDecimal("120"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//
//
//        MotorcycleTyres mt3 = new MotorcycleTyres("Bridgestone", "Suicide", MotorcycleTyreType.tubelessTyre );
//        try {
//            Tyre.createPart(mt3, "1220", new BigDecimal("6.2"), new BigDecimal("120"));
//            Tyre.createPart(mt3, "1220", new BigDecimal("6.0"), new BigDecimal("120"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        MotorcycleTyres mt4 = new MotorcycleTyres("Kumho", "Ultrafast", MotorcycleTyreType.tubelessTyre );
//        try {
//            Tyre.createPart(mt4, "1220", new BigDecimal("6.2"), new BigDecimal("120"));
//            Tyre.createPart(mt4, "1220", new BigDecimal("6.0"), new BigDecimal("120"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        pp10.addTyres(mt1);
//        pp10.addTyres(mt2);
//        pp10.addTyres(mt3);
//        pp10.addTyres(mt4);
//
////        physicalParametersRepo.save(pp10);
//
//
//
//        VehicleType vt = VehicleType.SUV;
//
//
//        CarTyres ts1 = new CarTyres("Dębica", "Prestario","A","A",69, strzelecki.karol.masprojekt.model.Tyre.Season.summer, vt );
//
//        try {
//            Tyre.createPart(ts1, "1220", new BigDecimal("6.2"), new BigDecimal("120"));
//            Tyre.createPart(ts1, "1220", new BigDecimal("6.0"), new BigDecimal("120"));
//            Tyre.createPart(ts1, "1220", new BigDecimal("5.9"), new BigDecimal("120"));
//            Tyre.createPart(ts1, "1220", new BigDecimal("5.7"), new BigDecimal("120"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        CarTyres ts2 = new CarTyres("Dębica", "SummerActiv","A","A",69, strzelecki.karol.masprojekt.model.Tyre.Season.summer, vt );
//
//        try {
//            Tyre.createPart(ts2, "1220", new BigDecimal("6.2"), new BigDecimal("120"));
//            Tyre.createPart(ts2, "1220", new BigDecimal("6.0"), new BigDecimal("120"));
//            Tyre.createPart(ts2, "1220", new BigDecimal("5.9"), new BigDecimal("120"));
//            Tyre.createPart(ts2, "1220", new BigDecimal("5.7"), new BigDecimal("120"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        CarTyres ts3 = new CarTyres("Continental", "ContiSport","A","A",69, strzelecki.karol.masprojekt.model.Tyre.Season.summer, vt );
//
//        try {
//            Tyre.createPart(ts3, "1220", new BigDecimal("6.2"), new BigDecimal("120"));
//            Tyre.createPart(ts3, "1220", new BigDecimal("6.0"), new BigDecimal("120"));
//            Tyre.createPart(ts3, "1220", new BigDecimal("5.9"), new BigDecimal("120"));
//            Tyre.createPart(ts3, "1220", new BigDecimal("5.7"), new BigDecimal("120"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        CarTyres ts4 = new CarTyres("Dunlop", "Maxx Race","A","A",69, strzelecki.karol.masprojekt.model.Tyre.Season.summer, vt );
//
//        try {
//            Tyre.createPart(ts4, "1220", new BigDecimal("6.2"), new BigDecimal("120"));
//            Tyre.createPart(ts4, "1220", new BigDecimal("6.0"), new BigDecimal("120"));
//            Tyre.createPart(ts4, "1220", new BigDecimal("5.9"), new BigDecimal("120"));
//            Tyre.createPart(ts4, "1220", new BigDecimal("5.7"), new BigDecimal("120"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        CarTyres ts5 = new CarTyres("Bridgestone", "Blizzak","A","A",69, strzelecki.karol.masprojekt.model.Tyre.Season.winter, vt );
//
//        try {
//            Tyre.createPart(ts5, "1220", new BigDecimal("6.2"), new BigDecimal("120"));
//            Tyre.createPart(ts5, "1220", new BigDecimal("6.0"), new BigDecimal("120"));
//            Tyre.createPart(ts5, "1220", new BigDecimal("5.9"), new BigDecimal("120"));
//            Tyre.createPart(ts5, "1220", new BigDecimal("5.7"), new BigDecimal("120"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        CarTyres ts6 = new CarTyres("Continental", "ContiSport","A","A",69, strzelecki.karol.masprojekt.model.Tyre.Season.summer, vt );
//
//        try {
//            Tyre.createPart(ts6, "1220", new BigDecimal("6.2"), new BigDecimal("120"));
//            Tyre.createPart(ts6, "1220", new BigDecimal("6.0"), new BigDecimal("120"));
//            Tyre.createPart(ts6, "1220", new BigDecimal("5.9"), new BigDecimal("120"));
//            Tyre.createPart(ts6, "1220", new BigDecimal("5.7"), new BigDecimal("120"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        CarTyres ts7 = new CarTyres("Goodyear", "UltraGrip 9","A","A",70, strzelecki.karol.masprojekt.model.Tyre.Season.summer, vt );
//
//        try {
//            Tyre.createPart(ts7, "1220", new BigDecimal("6.2"), new BigDecimal("120"));
//            Tyre.createPart(ts7, "1220", new BigDecimal("6.0"), new BigDecimal("120"));
//            Tyre.createPart(ts7, "1220", new BigDecimal("5.9"), new BigDecimal("120"));
//            Tyre.createPart(ts7, "1220", new BigDecimal("5.7"), new BigDecimal("120"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        CarTyres ts8 = new CarTyres("Pirelli", "P9","A","A",69, strzelecki.karol.masprojekt.model.Tyre.Season.summer, vt );
//
//        try {
//            Tyre.createPart(ts8, "1220", new BigDecimal("6.2"), new BigDecimal("120"));
//            Tyre.createPart(ts8, "1220", new BigDecimal("6.0"), new BigDecimal("120"));
//            Tyre.createPart(ts8, "1220", new BigDecimal("5.9"), new BigDecimal("120"));
//            Tyre.createPart(ts8, "1220", new BigDecimal("5.7"), new BigDecimal("120"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        CarTyres ts9 = new CarTyres("Nokian", "SuperTyre","A","A",69, strzelecki.karol.masprojekt.model.Tyre.Season.summer, vt);
//
//        try {
//            Tyre.createPart(ts9, "1220", new BigDecimal("6.2"), new BigDecimal("120"));
//            Tyre.createPart(ts9, "1220", new BigDecimal("6.0"), new BigDecimal("120"));
//            Tyre.createPart(ts9, "1220", new BigDecimal("5.9"), new BigDecimal("120"));
//            Tyre.createPart(ts9, "1220", new BigDecimal("5.7"), new BigDecimal("120"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        CarTyres ts10 = new CarTyres("Hankook", "GoodKook","A","A",69, strzelecki.karol.masprojekt.model.Tyre.Season.summer, vt );
//
//        try {
//            Tyre.createPart(ts10, "1220", new BigDecimal("6.2"), new BigDecimal("120"));
//            Tyre.createPart(ts10, "1220", new BigDecimal("6.0"), new BigDecimal("120"));
//            Tyre.createPart(ts10, "1220", new BigDecimal("5.9"), new BigDecimal("120"));
//            Tyre.createPart(ts10, "1220", new BigDecimal("5.7"), new BigDecimal("120"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        CarTyres ts11 = new CarTyres("Fulda", "NieDa","A","A",69, strzelecki.karol.masprojekt.model.Tyre.Season.summer, vt );
//
//        try {
//            Tyre.createPart(ts11, "1220", new BigDecimal("6.2"), new BigDecimal("120"));
//            Tyre.createPart(ts11, "1220", new BigDecimal("6.0"), new BigDecimal("120"));
//            Tyre.createPart(ts11, "1220", new BigDecimal("5.9"), new BigDecimal("120"));
//            Tyre.createPart(ts11, "1220", new BigDecimal("5.7"), new BigDecimal("120"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        CarTyres ts12 = new CarTyres("BFGoodrich", "UltimateSport","A","A",69, strzelecki.karol.masprojekt.model.Tyre.Season.summer, vt );
//
//        try {
//            Tyre.createPart(ts12, "1220", new BigDecimal("6.2"), new BigDecimal("120"));
//            Tyre.createPart(ts12, "1220", new BigDecimal("6.0"), new BigDecimal("120"));
//            Tyre.createPart(ts12, "1220", new BigDecimal("5.9"), new BigDecimal("120"));
//            Tyre.createPart(ts12, "1220", new BigDecimal("5.7"), new BigDecimal("120"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        CarTyres ts13 = new CarTyres("Uniroyal", "SeasonX","A","A",69, strzelecki.karol.masprojekt.model.Tyre.Season.summer,vt );
//
//        try {
//            Tyre.createPart(ts13, "1220", new BigDecimal("6.2"), new BigDecimal("120"));
//            Tyre.createPart(ts13, "1220", new BigDecimal("6.0"), new BigDecimal("120"));
//            Tyre.createPart(ts13, "1220", new BigDecimal("5.9"), new BigDecimal("120"));
//            Tyre.createPart(ts13, "1220", new BigDecimal("5.7"), new BigDecimal("120"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//
//        CarTyres ts14 = new CarTyres("Kleber", "Road Performance","A","A",69, strzelecki.karol.masprojekt.model.Tyre.Season.summer, vt );
//
//        try {
//            Tyre.createPart(ts14, "1220", new BigDecimal("6.2"), new BigDecimal("120"));
//            Tyre.createPart(ts14, "1220", new BigDecimal("6.0"), new BigDecimal("120"));
//            Tyre.createPart(ts14, "1220", new BigDecimal("5.9"), new BigDecimal("120"));
//            Tyre.createPart(ts14, "1220", new BigDecimal("5.7"), new BigDecimal("120"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//        CarTyres ts15 = new CarTyres("Dunlop", "Sp Sport","A","A",69, strzelecki.karol.masprojekt.model.Tyre.Season.summer,vt );
//
//        try {
//            Tyre.createPart(ts15, "1220", new BigDecimal("6.2"), new BigDecimal("120"));
//            Tyre.createPart(ts15, "1220", new BigDecimal("6.0"), new BigDecimal("120"));
//            Tyre.createPart(ts15, "1220", new BigDecimal("5.9"), new BigDecimal("120"));
//            Tyre.createPart(ts15, "1220", new BigDecimal("5.7"), new BigDecimal("120"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//
//
//        PhysicalParameters pp1 = new PhysicalParameters(220, 50, 19);
//        PhysicalParameters pp2 = new PhysicalParameters(220, 45, 19);
//        PhysicalParameters pp3 = new PhysicalParameters(220, 40, 19);
//        PhysicalParameters pp4 = new PhysicalParameters(220, 35, 19);
//        PhysicalParameters pp5 = new PhysicalParameters(220, 50, 19);
//        PhysicalParameters pp6 = new PhysicalParameters(215, 45, 19);
//        PhysicalParameters pp7 = new PhysicalParameters(215, 40, 19);
//
//        pp1.addTyres(ts1);
//        pp1.addTyres(ts2);
//        pp1.addTyres(ts3);
//        pp2.addTyres(ts4);
//        pp2.addTyres(ts5);
//        pp2.addTyres(ts6);
//        pp3.addTyres(ts7);
//        pp3.addTyres(ts8);
//        pp3.addTyres(ts9);
//        pp4.addTyres(ts10);
//        pp4.addTyres(ts11);
//        pp4.addTyres(ts12);
//        pp5.addTyres(ts13);
//        pp7.addTyres(ts14);
//        pp6.addTyres(ts15);
//
//
////        physicalParametersRepo.save(pp2);
////        physicalParametersRepo.save(pp3);
////        physicalParametersRepo.save(pp4);
////        physicalParametersRepo.save(pp5);
////        physicalParametersRepo.save(pp6);
////        physicalParametersRepo.save(pp7);
//
//
//
//
//
////        CarTyres ts4 = new CarTyres("Dębica", "Prestario","A","A",69, strzelecki.karol.masprojekt.model.Tyre.Season.summer, VehicleType.Passenger );
////
////            try {
////                Tyre.createPart(ts4, "1220", new BigDecimal("6.2"), new BigDecimal("120"));
////                Tyre.createPart(ts4, "1220", new BigDecimal("6.0"), new BigDecimal("120"));
////                Tyre.createPart(ts4, "1220", new BigDecimal("5.9"), new BigDecimal("120"));
////                Tyre.createPart(ts4, "1220", new BigDecimal("5.7"), new BigDecimal("120"));
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////
////            Address a = new Address( "Jesionowa", 13, 1, "Kalisz", "80-121");
////            Person person = new Person("90052903451", "Karol", "Damian", "Strzelecki", a);
////            Employee employee = new Employee(Position.storekeeper, LocalDate.now());
////            Customer customer = new Customer(LocalDate.now(), null);
////
////
////            person.addCustomerRole(customer);
////              person.addEmployeeRole(employee);
////
////             personRepo.save(person);
//
//
//
//
//       //  Warehouse
//
//
////            Address ad1 = new Address( "Oporowa", 8, 45, "Warszawa", "00-121");
////
////
////            Warehouse w1 = new OwnWarehouse("Ela", LocalDate.now(), ad1,new BigDecimal("500.00"), new BigDecimal("400.00"));
////
////            w1.setAddress(ad1);
//
//        Warehouse w1 = warehouseRepo.findByName("Ela").get();
//
//        w1.addTyreSet(ts1);
//        w1.addTyreSet(ts2);
//        w1.addTyreSet(ts3);
//        w1.addTyreSet(ts4);
//        w1.addTyreSet(ts5);
//        w1.addTyreSet(ts6);
//        w1.addTyreSet(ts7);
//        w1.addTyreSet(ts8);
//        w1.addTyreSet(ts9);
//        w1.addTyreSet(ts10);
//        w1.addTyreSet(ts11);
//        w1.addTyreSet(ts12);
//        w1.addTyreSet(ts13);
//        w1.addTyreSet(ts14);
//        w1.addTyreSet(mt1);
//        w1.addTyreSet(mt2);
//        w1.addTyreSet(mt3);
//        w1.addTyreSet(mt4);
//
//        warehouseRepo.save(w1);
//
//
////        Provider provider = new Provider("5252242171");
////        providerRepo.save(provider);
////
////
////
////
////
////            warehouseRepo.save(w1);
////
////        Delivery delivery = new Delivery( providerRepo.findById(provider.getId()).get(), warehouseRepo.findById(w1.getwId()).get());
////        deliveryRepo.save(delivery);
////
////
////
////
////
////
////          Contract contract1 = new Contract(new BigDecimal("2000.00"), LocalDate.now(), warehouseRepo.findByName("Ela").get() , employeeRepo.findByPesel("90052903451").get());
////
////
////        contractRepo.save(contract1);
////
////
////
////
////
////
////        Warehouse w3 = new RentedWarehouse(warehouseRepo.findById(w1.getwId()).get(), new BigDecimal("400.00"), new BigDecimal("400.00") );
////
////
////
////
////
////        warehouseRepo.save(w3);
////        warehouseRepo.delete(warehouseRepo.findById(w1.getwId()).get());
//
//
////
////
////                Procurement procurement = new Procurement(customerRepo.findById(customer.getCuId()).get());
////
////
////
////
////      TyreSet ts80 =  carTyresRepo.findById(ts4.getId()).get();
////      ts80.setProcurement(procurement);
////
////          procurementRepo.save(procurement);
////          tyreSetRepo.save(ts80);
////
//
//
//
//




    }

}
