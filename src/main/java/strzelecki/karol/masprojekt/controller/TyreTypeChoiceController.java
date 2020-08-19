package strzelecki.karol.masprojekt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import strzelecki.karol.masprojekt.repo.CarTyresRepo;
import strzelecki.karol.masprojekt.view.TyreTypeChoiceView;

import javax.swing.*;

@Component
public class TyreTypeChoiceController {

    TyreTypeChoiceView view;

    @Autowired
    MainWindowController mainWindowController;

    @Autowired
    CarTyresParametersController carTyresParametersController;

    @Autowired
    MotorcycleParametersController motorcycleParametersController;




    public TyreTypeChoiceController() {
        this.view = new TyreTypeChoiceView();
    }

    public void showGUI(MainWindowController mainWindowController){
        mainWindowController.showView(view.getMainPanel());
        this.mainWindowController = mainWindowController;
        initButtonListeners();
    }


    private void initButtonListeners() {

        view.getCarTyres().addActionListener( action ->{

            SwingUtilities.invokeLater(()->{
                carTyresParametersController.showGUI(this.mainWindowController);

            });
        } );

        view.getMotrocycleTyres().addActionListener(action -> {
            motorcycleParametersController.showGUI(mainWindowController);
        });




    }
}
