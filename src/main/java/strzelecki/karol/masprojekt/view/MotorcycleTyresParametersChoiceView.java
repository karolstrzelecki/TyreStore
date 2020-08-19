package strzelecki.karol.masprojekt.view;

import javax.swing.*;

public class MotorcycleTyresParametersChoiceView {
    private JPanel mainPanel;
    private JButton exitButton;
    private JButton confirmButton;
    private JButton tubeTyresButton;
    private JButton tubelessTyresButton;
    private JComboBox rimBox;
    private JComboBox widthBox;
    private JComboBox aspectRatioBox;
    private JLabel jLabel;

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public JButton getExitButton() {
        return exitButton;
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public JButton getTubeTyresButton() {
        return tubeTyresButton;
    }

    public JButton getTubelessTyresButton() {
        return tubelessTyresButton;
    }

    public JComboBox getRimBox() {
        return rimBox;
    }

    public JComboBox getWidthBox() {
        return widthBox;
    }

    public JComboBox getAspectRatioBox() {
        return aspectRatioBox;
    }

    public JLabel getjLabel() {
        return jLabel;
    }
}
