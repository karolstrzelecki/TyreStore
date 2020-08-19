package strzelecki.karol.masprojekt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import strzelecki.karol.masprojekt.model.Tyre.MotorcycleTyreType;
import strzelecki.karol.masprojekt.model.Tyre.PhysicalParameters;
import strzelecki.karol.masprojekt.repo.PhysicalParametersRepo;
import strzelecki.karol.masprojekt.view.MotorcycleTyresParametersChoiceView;

import javax.swing.*;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Component
public class MotorcycleParametersController {
    private PhysicalParameters pp = new PhysicalParameters();
    private MotorcycleTyreType mtt;

    private MotorcycleTyresParametersChoiceView view;

    @Autowired
    MainWindowController mainWindowController;

    @Autowired
    CarTyreSetListController carTyreSetListController;

    @Autowired
    TyreTypeChoiceController tyreTypeChoiceController;

    @Autowired
    PhysicalParametersRepo physicalParametersRepo;




    public MotorcycleParametersController() {
        this.view =new MotorcycleTyresParametersChoiceView();

    }

    public void showGUI(MainWindowController mainWindowController){

        this.mainWindowController = mainWindowController;
        mainWindowController.showView(view.getMainPanel());
        initJToolbarButtonsListener();
        initjBoxes();
        initButtonslistener();
    }


    private void initjBoxes(){

        view.getRimBox().removeAllItems();
        view.getAspectRatioBox().removeAllItems();
        view.getWidthBox().removeAllItems();



        view.getConfirmButton().setEnabled(false);
        List<PhysicalParameters> ppList = (List<PhysicalParameters>) physicalParametersRepo.findAllByMotorcycleTyreType(mtt);




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
                List<PhysicalParameters> tmpPpList = (List<PhysicalParameters>) physicalParametersRepo.findAllByMotorcycleTyreTypeAndRim(mtt, pp.getRim());


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
                    List<PhysicalParameters> tmpPpList2 = (List<PhysicalParameters>) physicalParametersRepo.findAllByMotorcycleTyreTypeAndRimAndWidth(mtt, pp.getRim(), pp.getWidth());


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
                List<PhysicalParameters> tmpPpList = (List<PhysicalParameters>) physicalParametersRepo.findAllByMotorcycleTyreTypeAndRimAndWidth(mtt, pp.getRim(), pp.getWidth());


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


//        view.getRimBox().addActionListener(action ->{
//
//            pp.setWidth((Integer) view.getRimBox().getSelectedItem());
//
//            if(pp.getWidth() != null) {
//
//                view.getAspectRatioBox().removeAllItems();
//
//                List<PhysicalParameters> tmpPpList = (List<PhysicalParameters>) physicalParametersRepo.findAllByMotorcycleTyreTypeAndRimAndWidth(mtt, pp.getRim(), pp.getWidth());
//                Set<Integer> aspectRatios = new TreeSet<>();
//
//                for (PhysicalParameters pp : tmpPpList) {
//                    aspectRatios.add(pp.getAspectRatio());
//                }
//
//                for (Integer aspectRatio : aspectRatios) {
//                    view.getAspectRatioBox().addItem(aspectRatio);
//                }
//
//                if (pp.getAspectRatio() == null) {
//                    view.getConfirmButton().setEnabled(false);
//                } else {
//                    if (!aspectRatios.contains(pp.getAspectRatio())) {
//                        view.getConfirmButton().setEnabled(false);
//                    } else {
//                        view.getConfirmButton().setEnabled(true);
//                    }
//                }
//            }
//
//
//        } );


        view.getAspectRatioBox().addActionListener( action ->{
            pp.setAspectRatio((Integer) view.getAspectRatioBox().getSelectedItem());



        } );



    }

    private void initJToolbarButtonsListener(){
        view.getTubeTyresButton().setEnabled(false);
        mtt = MotorcycleTyreType.tubeTyre;

        view.getTubeTyresButton().addActionListener(action ->{
            view.getTubeTyresButton().setEnabled(false);
            view.getTubelessTyresButton().setEnabled(true);
            mtt = MotorcycleTyreType.tubeTyre;
            initjBoxes();
        });


        view.getTubelessTyresButton().addActionListener(action -> {
            view.getTubeTyresButton().setEnabled(true);
            view.getTubelessTyresButton().setEnabled(false);
            mtt = MotorcycleTyreType.tubelessTyre;
            initjBoxes();
        });
    }

    private void initButtonslistener(){



        view.getConfirmButton().addActionListener(action -> {
            SwingUtilities.invokeLater(()->{

               carTyreSetListController.showGUI(mainWindowController, pp, mtt);

            });
        });

        view.getExitButton().addActionListener(action ->{
            SwingUtilities.invokeLater(()->{
                tyreTypeChoiceController.showGUI(mainWindowController);
            });
        });



    }





}
