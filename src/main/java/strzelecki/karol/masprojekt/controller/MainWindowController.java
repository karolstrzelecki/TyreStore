package strzelecki.karol.masprojekt.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import strzelecki.karol.masprojekt.view.MainWindow;

import javax.swing.*;

@Component
public class MainWindowController {

    public MainWindow view;

    @Autowired
    private TyreTypeChoiceController tyreTypeChoiceController;



    public MainWindowController(MainWindow view) {
        this.view = view;
        initMenuListeners();

    }

    private void initMenuListeners() {

        view.getCompanyListMenuItem().addActionListener( action ->{

            SwingUtilities.invokeLater(()->{

                tyreTypeChoiceController.showGUI(this);

            });
        } );

    }

    public void showGUI(){
        view.setVisible(true);
    }

    public void showView(JPanel viewToShow){
        view.setSize(640,480);
        view.getContentPane().removeAll();
        view.getContentPane().add(viewToShow);
        view.revalidate();
        view.pack();
    }

}
