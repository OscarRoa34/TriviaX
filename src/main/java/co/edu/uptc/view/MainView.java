package co.edu.uptc.view;

import javax.swing.*;
import java.awt.*;

public class MainView extends JFrame {

    private JPanel cardPanel;
    private CardLayout cardLayout;

    public MainView() {
        super("TriviaX");
        setSize(500, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        MenuPanel menuPanel = new MenuPanel();
        GamePanel gamePanel = new GamePanel();
        SettingsPanel settingsPanel = new SettingsPanel();
        RecordsPanel recordsPanel = new RecordsPanel();
        cardPanel.add(menuPanel, "Menu");
        cardPanel.add(gamePanel, "Game");
        cardPanel.add(settingsPanel, "Settings");
        cardPanel.add(recordsPanel, "Records");
        cardLayout.show(cardPanel, "Menu");
        add(cardPanel);
        setVisible(true);
    }

    public void showPanel(String panelName) {
        cardLayout.show(cardPanel, panelName);
    }

    public static void main(String[] args) {
        MainView mainView = new MainView();
    }
}