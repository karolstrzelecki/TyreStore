package strzelecki.karol.masprojekt;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import strzelecki.karol.masprojekt.controller.MainWindowController;
import strzelecki.karol.masprojekt.model.Address;
import strzelecki.karol.masprojekt.model.Company;
import strzelecki.karol.masprojekt.model.Contract;

import strzelecki.karol.masprojekt.model.Procurement;
import strzelecki.karol.masprojekt.model.Tyre.*;
import strzelecki.karol.masprojekt.model.person.Customer;
import strzelecki.karol.masprojekt.model.person.Employee;
import strzelecki.karol.masprojekt.model.person.Person;
import strzelecki.karol.masprojekt.model.person.Position;
import strzelecki.karol.masprojekt.model.warehouse.OwnWarehouse;
import strzelecki.karol.masprojekt.model.warehouse.RentedWarehouse;
import strzelecki.karol.masprojekt.model.warehouse.Warehouse;
import strzelecki.karol.masprojekt.repo.*;

import javax.swing.*;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.Optional;

@SpringBootApplication
public class MasprojektApplication {

//    @Autowired
//    private WarehouseRepo warehouseRepo;

    public static void main(String[] args) throws MalformedURLException {

     //   SpringApplication.run(MasprojektApplication.class, args);

        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(MasprojektApplication.class).headless(false).run(args);


        ctx.getBean(DataInitializer.class).initData();
        SwingUtilities.invokeLater(() -> {
           ctx.getBean(MainWindowController.class).showGUI();
        });
    }

//    @Bean
//    public CommandLineRunner demo(
//       AddressRepo addressRepo,
//     WarehouseRepo warehouseRepo,
//        CompanyRepo companyRepo,
//       PersonRepo personRepo,
//       CustomerRepo customerRepo,
//       EmployeeRepo employeeRepo,
//       TyreSetRepo tyresetRepo,
//       PhysicalParametersRepo physicalParametersRepo,
//       ContractRepo contractRepo,
//       ProcurementRepo procurementRepo
//    ){
//        return (args) ->{
////            Address a1 = new Address( "Zamkowa", 8, 45, "Warszawa", "00-121");
////           // Address a2 = new Address(new Long(102), "Mleczna", 4, 2, "Kielce", "25-724");
////
////
////            Warehouse w1 = new OwnWarehouse("Elżbieta", LocalDate.now(), a1,new BigDecimal("500.00"), new BigDecimal("400.00"));
////         // Warehouse w2 = new RentedWarehouse("Krystyna", LocalDate.now(), a2,new BigDecimal("500.00"), new BigDecimal("400.00"), new BigDecimal("300.00"));
////
////
////        //    w1.setAddress(a1);
////         //   a1.setWarehouse(w1);
////            warehouseRepo.save(w1);
//
//
//
////
////          //  w2.setAddress(a2);
////       //     a2.setWarehouse(w2);
////
////
////            warehouseRepo.save(w2);
////
////
////    // w2 = new OwnWarehouse(w2, new BigDecimal("300"));
////            Warehouse w3 = warehouseRepo.findById(w2.getwId()).get();
////            warehouseRepo.delete(w3);
////            w3 = new OwnWarehouse(w3, new BigDecimal("300"));
////            warehouseRepo.save(w3);
////
////
////
////            Company c = new Company("7272445205");
////            companyRepo.save(c);
////
////
////            Address a3 = new Address(new Long(104), "Jesionowa", 12, 1, "Kalisz", "80-121");
////
////            Person person = new Person("90052903451", "Karol", "Damian", "Strzelecki", a3);
////
////            personRepo.save(person);
////
////            System.out.println(person);
////            Address a4 = new Address(new Long(107), "Oporowa", 12, 1, "Januszopol", "99-121");
////
////
////            Customer customer = new Customer(LocalDate.now(), a4);
////            Employee employee = new Employee(Position.storekeeper, LocalDate.now());
////
////            person.addCustomerRole(customer);
////            person.addEmployeeRole(employee);
////            personRepo.save(person);
////
////
////            System.out.println(person);
////            //   person.removeCustomerRole();
////
////            System.out.println(person);
////
////         person.removeEmployeeRole();
////
////
////            personRepo.save(person);
//
//
//            // =========== TYRE ==============
//
//
//            PhysicalParameters pp1 = new PhysicalParameters(225, 50, 17);
//
////            TyreSet ts1 = new TyreSet("Michelin", "Turazzio");
////            TyreSet ts2 = new TyreSet("Dunlop", "Bamlop");
////
////
////
////            pp1.addTyres(ts1);
////            pp1.addTyres(ts2);
////
////
////            TyreSet ts3 = new TyreSet("Dębica", "Prestario");
////
////            try {
////                Tyre.createPart(ts3, "1220", new BigDecimal("6.2"), new BigDecimal("120"));
////                Tyre.createPart(ts3, "1220", new BigDecimal("6.0"), new BigDecimal("120"));
////                Tyre.createPart(ts3, "1220", new BigDecimal("5.9"), new BigDecimal("120"));
////                Tyre.createPart(ts3, "1220", new BigDecimal("5.7"), new BigDecimal("120"));
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////
////            pp1.addTyres(ts3);
//
//
//
//
////            pp1.removeTyreSet(ts3);
//
////                public CarTyres(String brand, String model, String rollingResistance, String wetGrip, int noiseLevel, Season season, VehicleType
////            vehicleType) {
//
//                CarTyres ts4 = new CarTyres("Dębica", "Prestario","A","A",69, strzelecki.karol.masprojekt.model.Tyre.Season.summer, VehicleType.Passenger );
//
//            try {
//                Tyre.createPart(ts4, "1220", new BigDecimal("6.2"), new BigDecimal("120"));
//                Tyre.createPart(ts4, "1220", new BigDecimal("6.0"), new BigDecimal("120"));
//                Tyre.createPart(ts4, "1220", new BigDecimal("5.9"), new BigDecimal("120"));
//                Tyre.createPart(ts4, "1220", new BigDecimal("5.7"), new BigDecimal("120"));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            Address a2 = new Address( "Jesionowa", 12, 1, "Kalisz", "80-121");
//            Person person = new Person("90052903451", "Karol", "Damian", "Strzelecki", a2);
//            Employee employee = new Employee(Position.storekeeper, LocalDate.now());
//            Customer customer = new Customer(LocalDate.now(), null);
//
//            person.addEmployeeRole(employee);
//            person.addCustomerRole(customer);
//
//           personRepo.save(person);
//
//            System.out.println(employee.getEmId());
//
//
//            MotorcycleTyres ts5 = new MotorcycleTyres("Goodyear","Moto2",MotorcycleTyreType.tubelessTyre);
//
//
//            pp1.addTyres(ts4);
//            pp1.addTyres(ts5);
//
//            Procurement procurement = new Procurement(customerRepo.findById(customer.getCuId()).get());
//            procurement.addTyreSet(ts4);
//            procurement.addTyreSet(ts5);
//
//          procurementRepo.save(procurement);
////           personRepo.save(person);
//
//
//
//
//
////            physicalParametersRepo.save(pp1);
//
//
//
//          //  physicalParametersRepo.save(pp1);
//          //  tyresetRepo.delete(ts3);
//
//         //   tyresetRepo.save(ts4);
//
////            PhysicalParameters pp2 = physicalParametersRepo.findById(pp1.getId()).get();
////            System.out.println(pp2);
////
////
////            TyreSet ts2 = new TyreSet("Dunlop", "Bamlop", pp2);
////            tyresetRepo.save(ts2);
////            TyreSet ts3 = new TyreSet("Dębica", "Prestario", physicalParametersRepo.findById(pp1.getId()).get());
////
////            tyresetRepo.save(ts3);
//
//
//
////            Address a1 = new Address( "Zamkowa", 8, 45, "Warszawa", "00-121");
////            Warehouse w1 = new OwnWarehouse("Elżbieta", LocalDate.now(), a1,new BigDecimal("500.00"), new BigDecimal("400.00"));
////
////
////            Address a2 = new Address( "Jesionowa", 12, 1, "Kalisz", "80-121");
////
////            Person person = new Person("90052903451", "Karol", "Damian", "Strzelecki", a2);
////            Employee employee = new Employee(Position.storekeeper, LocalDate.now());
//////
////            person.addEmployeeRole(employee);
////
////
////
////            Contract contract = new Contract(new BigDecimal("300.00"), LocalDate.now(),w1, employee);
////            contractRepo.save(contract);
//
//
//
//
//
//
////            Address ac = new Address(new Long(109), "Zamkowa", 8, 45, "Warszawa", "00-121");
////            Warehouse wc = new OwnWarehouse("Elżbieta", LocalDate.now(), a1,new BigDecimal("500.00"), new BigDecimal("400.00"));
//
//
//
//
//
//
//
//
//
//
//        };
//    }
}
