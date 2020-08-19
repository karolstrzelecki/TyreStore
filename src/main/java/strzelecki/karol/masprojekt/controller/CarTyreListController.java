package strzelecki.karol.masprojekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import strzelecki.karol.masprojekt.model.Procurement;
import strzelecki.karol.masprojekt.model.Tyre.CarTyres;
import strzelecki.karol.masprojekt.model.Tyre.MotorcycleTyres;
import strzelecki.karol.masprojekt.model.Tyre.Tyre;
import strzelecki.karol.masprojekt.model.Tyre.TyreSet;
import strzelecki.karol.masprojekt.model.person.Customer;
import strzelecki.karol.masprojekt.repo.*;
import strzelecki.karol.masprojekt.view.CarTyreListView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

@Component
public class CarTyreListController {

    private Long tmpId;
    private int dtmp;
    private CarTyreListView view;

    @Autowired
    MainWindowController mainWindowController;



    @Autowired
    CarTyreSetListController carTyreSetListController;




    @Autowired
    private CarTyresRepo carTyresRepo;


    @Autowired
    private MotorcycleTyreRepo motorcycleTyreRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    private ProcurementRepo procurementRepo;

    @Autowired
    private TyreSetRepo tyreSetRepo;

    public CarTyreListController() {
    this.view = new CarTyreListView();
    }


    public void showGUI(MainWindowController mainWindowController, Long tmpId, int dtmp){
        this.dtmp = dtmp;
        this.tmpId = tmpId;
        this.mainWindowController = mainWindowController;
        mainWindowController.showView(view.getMainPanel());
        initTable();
        initButtons();

    }

    private void initTable(){

        List<Tyre> tyreList = new ArrayList<>();
        if(dtmp == 1) {

            CarTyres carTyres = carTyresRepo.findById(tmpId).get();
            tyreList = carTyres.getTyres();
        }

        if(dtmp == 2){
            MotorcycleTyres motorcycleTyres = motorcycleTyreRepo.findById(tmpId).get();
            tyreList = motorcycleTyres.getTyres();
        }



        DefaultTableModel model = new DefaultTableModel();


        Object[] columnsName = new Object[5];

        columnsName[0] = "Opona";
        columnsName[1] = "Bieżnik";
        columnsName[2] = "Data Produkcji";
        columnsName[3] = "Cena";
        columnsName[4] = "Id";

        model.setColumnIdentifiers(columnsName);

        Object[] rowData = new Object[5];

        for (int i =0; i< tyreList.size(); i++){

            rowData[0] = "Opona" + (i+1);
            rowData[1] = tyreList.get(i).getHeightOfTread();
            rowData[2] = tyreList.get(i).getDateOfManufactureCode();
            rowData[3] = tyreList.get(i).getPrice();
            rowData[4] = tyreList.get(i).getId();




            model.addRow(rowData);
        }

        view.getTable().setModel(model);
        view.getTable().getColumnModel().getColumn(4).setMaxWidth(0);
        view.getTable().getColumnModel().getColumn(4).setMinWidth(0);
        view.getTable().getColumnModel().getColumn(4).setWidth(0);
        view.getTable().getTableHeader().getColumnModel().getColumn(4).setWidth(0);
        view.getTable().getTableHeader().getColumnModel().getColumn(4).setMinWidth(0);
        view.getTable().getTableHeader().getColumnModel().getColumn(4).setMaxWidth(0);

    }


    private void initButtons(){
        view.getAcceptButton().addActionListener(action -> {

            SwingUtilities.invokeLater(()->{





                    TyreSet carTyres = tyreSetRepo.findById(tmpId).get();


                    Procurement procurement = new Procurement(customerRepo.findByPesel("82052948446").get());
                    procurement.addTyreSet(carTyres);
                    carTyres.setProcurement(procurement);
                    tyreSetRepo.save(carTyres);
                    procurementRepo.save(procurement);




                    JOptionPane.showMessageDialog(null, "Kupiłeś opony","Zakup", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);





            });

        });


        view.getExitButton().addActionListener(action -> {
            SwingUtilities.invokeLater(()->{

                if (dtmp == 1){
                    CarTyres carTyres = carTyresRepo.findById(tmpId).get();
                    carTyreSetListController.showGUI(mainWindowController, carTyres.getPhysicalParameters(), carTyres.getVehicleType());
                }
                if(dtmp == 2){
                    MotorcycleTyres motorcycleTyres = motorcycleTyreRepo.findById(tmpId).get();
                    carTyreSetListController.showGUI(mainWindowController, motorcycleTyres.getPhysicalParameters(), motorcycleTyres.getMotorcycleTyreType());
                }

            });
        });
    }


}
