package strzelecki.karol.masprojekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import strzelecki.karol.masprojekt.model.Tyre.PhysicalParameters;
import strzelecki.karol.masprojekt.model.Tyre.VehicleType;
import strzelecki.karol.masprojekt.repo.PhysicalParametersRepo;
import strzelecki.karol.masprojekt.view.CarTyresParametersChoiceWindow;

import javax.swing.*;
import java.util.*;

@Component
public class CarTyresParametersController {

   private PhysicalParameters pp = new PhysicalParameters();
   private VehicleType v;



    private CarTyresParametersChoiceWindow view;

    @Autowired
    private CarTyreSetListController carTyreSetListController;

    @Autowired
    private TyreTypeChoiceController tyreTypeChoiceController;


    @Autowired
    private MainWindowController mainWindowController;

    @Autowired
    private PhysicalParametersRepo physicalParametersRepo;

    public CarTyresParametersController() {
        this.view = new CarTyresParametersChoiceWindow();

    }

    public void showGUI(MainWindowController mainWindowController){

        this.mainWindowController = mainWindowController;
        mainWindowController.showView(view.getMainPanel());

        initJToolbarButtonsListener();
        initjBoxes();
        initButtonslistener();

    }

    private void initjBoxes(){
        view.getWidthBox().removeAllItems();
        view.getAspectRatioBox().removeAllItems();
        view.getRimBox().removeAllItems();

        view.getConfirmButton().setEnabled(false);
        List<PhysicalParameters> ppList = (List<PhysicalParameters>) physicalParametersRepo.findAllByVehicleType(v);




        Set<Integer> rims = new TreeSet<>();

        for(PhysicalParameters pp1 : ppList){
            rims.add(pp1.getRim());
        }

        for(Integer rim : rims){
            view.getRimBox().addItem(rim);
        }






        view.getRimBox().addActionListener(action -> {
            if(view.getRimBox().getSelectedItem() != null) {
                view.getWidthBox().removeAllItems();
                view.getAspectRatioBox().removeAllItems();

                pp.setRim((Integer) view.getRimBox().getSelectedItem());
                List<PhysicalParameters> tmpPpList = (List<PhysicalParameters>) physicalParametersRepo.findAllByVehicleTypeAndRim(v, pp.getRim());

                Set<Integer> widths = new TreeSet<>();
                Set<Integer> aspectRatios = new TreeSet<>();

                for (PhysicalParameters pp : tmpPpList) {
                    widths.add(pp.getWidth());
                    aspectRatios.add(pp.getAspectRatio());
                }

                for (Integer width : widths) {
                    view.getWidthBox().addItem(width);
                }

                if (pp.getWidth() != null) {
                    List<PhysicalParameters> tmpPpList2 = (List<PhysicalParameters>) physicalParametersRepo.findAllByVehicleTypeAndRimAndWidth(v, pp.getRim(), pp.getWidth());


                    for (PhysicalParameters pp : tmpPpList2) {
                        aspectRatios.add(pp.getAspectRatio());
                    }

                    for (Integer aspectRatio : aspectRatios) {
                        view.getAspectRatioBox().addItem(aspectRatio);
                    }
                }


                if (pp.getAspectRatio() == null || pp.getWidth() == null) {
                    view.getConfirmButton().setEnabled(false);
                } else {


                    if (!widths.contains(pp.getWidth())) {
                        view.getConfirmButton().setEnabled(false);
                    }

                    if (!aspectRatios.contains(pp.getAspectRatio())) {
                        view.getConfirmButton().setEnabled(false);
                    }


                    if (aspectRatios.contains(pp.getAspectRatio()) && widths.contains(pp.getWidth())) {
                        view.getConfirmButton().setEnabled(true);
                    }
                }
            }
        });


        view.getWidthBox().addActionListener( action ->{

            pp.setWidth((Integer) view.getWidthBox().getSelectedItem());

            if(pp.getWidth() != null) {

                view.getAspectRatioBox().removeAllItems();

                List<PhysicalParameters> tmpPpList = (List<PhysicalParameters>) physicalParametersRepo.findAllByVehicleTypeAndRimAndWidth(v, pp.getRim(), pp.getWidth());
                Set<Integer> aspectRatios = new TreeSet<>();

                for (PhysicalParameters pp : tmpPpList) {
                    aspectRatios.add(pp.getAspectRatio());
                }

                for (Integer aspectRatio : aspectRatios) {
                    view.getAspectRatioBox().addItem(aspectRatio);
                }

                if (pp.getAspectRatio() == null) {
                    view.getConfirmButton().setEnabled(false);
                } else {
                    if (!aspectRatios.contains(pp.getAspectRatio())) {
                        view.getConfirmButton().setEnabled(false);
                    } else {
                        view.getConfirmButton().setEnabled(true);
                    }
                }
            }


        } );


        view.getAspectRatioBox().addActionListener( action ->{
            pp.setAspectRatio((Integer) view.getAspectRatioBox().getSelectedItem());



        } );


    }


    private void initJToolbarButtonsListener(){

        view.getPassengerButton().setEnabled(false);
        v = VehicleType.Passenger;



        view.getPassengerButton().addActionListener( action ->{
           view.getSuvButton().setEnabled(true);
           view.getTruckButton().setEnabled(true);
           view.getPassengerButton().setEnabled(false);
           v = VehicleType.Passenger;
            initjBoxes();
        } );




        view.getSuvButton().addActionListener(action -> {
            view.getSuvButton().setEnabled(false);
            view.getTruckButton().setEnabled(true);
            view.getPassengerButton().setEnabled(true);
            v = VehicleType.SUV;
            initjBoxes();


        });



        view.getTruckButton().addActionListener(action -> {
            view.getSuvButton().setEnabled(true);
            view.getTruckButton().setEnabled(false);
            view.getPassengerButton().setEnabled(true);
            v = VehicleType.Truck;
            initjBoxes();


        });

    }





    private void initButtonslistener(){



        view.getConfirmButton().addActionListener(action -> {
            SwingUtilities.invokeLater(()->{
                carTyreSetListController.showGUI(mainWindowController, pp, v);
            });
        });

        view.getExitButton().addActionListener(action ->{
            SwingUtilities.invokeLater(()->{

                tyreTypeChoiceController.showGUI(mainWindowController);
              });
        });



    }






}
