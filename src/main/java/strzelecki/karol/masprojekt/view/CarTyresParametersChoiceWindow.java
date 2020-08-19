package strzelecki.karol.masprojekt.view;

import javax.swing.*;

public class CarTyresParametersChoiceWindow {
    private JPanel mainPanel;
    private JComboBox rimBox;
    private JComboBox aspectRatioBox;
    private JButton exitButton;
    private JButton confirmButton;
    private JComboBox widthBox;
    private JButton passengerButton;
    private JButton truckButton;
    private JButton suvButton;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JComboBox getWidthBox() {
        return widthBox;
    }

    public JComboBox getAspectRatioBox() {
        return aspectRatioBox;
    }

    public JComboBox getRimBox() {
        return rimBox;
    }

    public JButton getPassengerButton() {
        return passengerButton;
    }

    public JButton getTruckButton() {
        return truckButton;
    }

    public JButton getSuvButton() {
        return suvButton;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }
}
