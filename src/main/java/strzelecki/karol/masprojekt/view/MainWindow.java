package strzelecki.karol.masprojekt.view;

import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class MainWindow extends JFrame {

    private JMenuItem companyListMenuItem;

    public MainWindow() {

        setSize(1280, 768);
        setTitle("Projekt tanie opony");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        initMenuBar();
    }

    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menuCompany = new JMenu("Zadania");
        menuBar.add(menuCompany);
        this.setJMenuBar(menuBar);
        companyListMenuItem = new JMenuItem("Kup Opony");
        menuCompany.add(companyListMenuItem);


    }

    public JMenuItem getCompanyListMenuItem() {
        return companyListMenuItem;
    }
}