package strzelecki.karol.masprojekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import strzelecki.karol.masprojekt.model.Tyre.*;
import strzelecki.karol.masprojekt.repo.CarTyresRepo;
import strzelecki.karol.masprojekt.repo.MotorcycleTyreRepo;
import strzelecki.karol.masprojekt.view.CarTyreSetListView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

@Component
public class CarTyreSetListController {

    private CarTyreSetListView view;


    @Autowired
    MainWindowController mainWindowController;

    @Autowired
    CarTyreListController carTyreListController;

    @Autowired
    CarTyresParametersController carTyresParametersController;

    @Autowired
    MotorcycleParametersController motorcycleParametersController;

    @Autowired
    MotorcycleTyreRepo motorcycleTyreRepo;



    @Autowired
    private CarTyresRepo carTyresRepo;



    private PhysicalParameters pp;
    private MotorcycleTyreType mtt;
    private VehicleType vt;
    private Long tmpId;
    private int dtmp;


    public CarTyreSetListController() {
        this.view = new CarTyreSetListView();
    }

    public void showGUI(MainWindowController mainWindowController, PhysicalParameters pp, VehicleType vt){
        mainWindowController.showView(view.getMainPanel());
        this.mainWindowController = mainWindowController;
        this.pp = pp;
        this.vt = vt;
        this.mtt = null;
        this.dtmp =1;

        initCarTyreTable();
        initButtons();

    }

    public void showGUI(MainWindowController mainWindowController, PhysicalParameters pp, MotorcycleTyreType mtt){
        mainWindowController.showView(view.getMainPanel());
        this.mainWindowController = mainWindowController;
        this.pp = pp;
        this.mtt = mtt;
        this.vt = null;
        this.dtmp =2;


        initButtons();
        initMotorcycleTyreTable();

    }


    private void initCarTyreTable(){

        view.getjLabel().setText("Zestawy o rozmiarze: " + pp);

        view.getConfirmButtom().setEnabled(false);
        view.getTable().setDefaultEditor(Object.class, null);
        List<CarTyres> cc = (List<CarTyres>) carTyresRepo.findAllByVehicleTypeAndPhysicalParameters(vt, pp.getWidth(), pp.getRim(), pp.getAspectRatio() );



       // view.getTable().set

        DefaultTableModel model = new DefaultTableModel();


        Object[] columnsName = new Object[8];

        columnsName[0] = "Nazwa";
        columnsName[1] = "Sezon";
        columnsName[2] = "Opór toczenia";
        columnsName[3] = "Przyczepność";
        columnsName[4] = "Głośność";
        columnsName[5] = "Ilość";
        columnsName[6] = "Cena";
        columnsName[7] = "id";

        model.setColumnIdentifiers(columnsName);

        Object[] rowData = new Object[8];

        for (int i =0; i< cc.size(); i++){

            rowData[0] = cc.get(i).getBrand() + " " + cc.get(i).getModel();
            rowData[1] = cc.get(i).getSeason();
            rowData[2] = cc.get(i).getRollingResistance();
            rowData[3] = cc.get(i).getWetGrip();
            rowData[4] = cc.get(i).getNoiseLevel() + " dB";
            CarTyres tmp = carTyresRepo.findById(cc.get(i).getId()).get();
            rowData[5] = tmp.getQuantity();
            rowData[6] = tmp.getPrice();
            rowData[7] = cc.get(i).getId();

            model.addRow(rowData);
        }

        view.getTable().setModel(model);
        view.getTable().getColumnModel().getColumn(7).setMaxWidth(0);
        view.getTable().getColumnModel().getColumn(7).setMinWidth(0);
        view.getTable().getColumnModel().getColumn(7).setWidth(0);
        view.getTable().getTableHeader().getColumnModel().getColumn(7).setWidth(0);
        view.getTable().getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
        view.getTable().getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);







        view.getTable().getSelectionModel().addListSelectionListener(action ->{

            if(view.getTable().getSelectedRow() > -1 && dtmp == 1) {
                tmpId = (Long) view.getTable().getValueAt(view.getTable().getSelectedRow(), 7);
                view.getConfirmButtom().setEnabled(true);
                this.dtmp = 1;
            }
        });


    }


    private void initMotorcycleTyreTable(){


        view.getjLabel().setText("Zestawy o rozmiarze: " + pp);

        view.getConfirmButtom().setEnabled(false);
        view.getTable().setDefaultEditor(Object.class, null);

        List<MotorcycleTyres> cc = (List<MotorcycleTyres>) motorcycleTyreRepo.findAllByMotorcycleTyreTypeAndPhysicalParameters(mtt, pp.getWidth(), pp.getRim(), pp.getAspectRatio());



        DefaultTableModel model = new DefaultTableModel();


        Object[] columnsName = new Object[4];

        columnsName[0] = "Nazwa";
        columnsName[1] = "Ilość";
        columnsName[2] = "Cena";
        columnsName[3] = "Id";


        model.setColumnIdentifiers(columnsName);

        Object[] rowData = new Object[4];

        for (int i =0; i< cc.size(); i++){

            rowData[0] = cc.get(i).getBrand() + " " + cc.get(i).getModel();
            MotorcycleTyres tmp = motorcycleTyreRepo.findById(cc.get(i).getId()).get();
            rowData[1] = tmp.getQuantity();
            rowData[2] = tmp.getPrice();
            rowData[3] = cc.get(i).getId();

            model.addRow(rowData);
        }

        view.getTable().setModel(model);
        view.getTable().getColumnModel().getColumn(3).setMaxWidth(0);
        view.getTable().getColumnModel().getColumn(3).setMinWidth(0);
        view.getTable().getColumnModel().getColumn(3).setWidth(0);
        view.getTable().getTableHeader().getColumnModel().getColumn(3).setWidth(0);
        view.getTable().getTableHeader().getColumnModel().getColumn(3).setMinWidth(0);
        view.getTable().getTableHeader().getColumnModel().getColumn(3).setMaxWidth(0);






        view.getTable().getSelectionModel().addListSelectionListener(action -> {
            if(view.getTable().getSelectedRow() > -1 && dtmp == 2) {
            tmpId = (Long) view.getTable().getValueAt(view.getTable().getSelectedRow(), 3);
            view.getConfirmButtom().setEnabled(true);
            this.dtmp = 2;
        }
        });


    }

    private void initButtons(){
        view.getConfirmButtom().addActionListener(action -> {

            SwingUtilities.invokeLater(()->{

                carTyreListController.showGUI(mainWindowController,tmpId, dtmp);

            });

        });


        view.getReturnButton().addActionListener((action -> {
            SwingUtilities.invokeLater(() -> {
                if(dtmp == 1) {
                    carTyresParametersController.showGUI(mainWindowController);
                }
                if(dtmp == 2) {
                    motorcycleParametersController.showGUI(mainWindowController);
                }

            });
        }));


    }


}
