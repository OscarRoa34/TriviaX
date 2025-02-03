package co.edu.uptc.view;

import javax.swing.*;

import co.edu.uptc.Utils.PropertiesService;

import java.awt.*;

public class SettingsPanel extends JPanel {

    private PropertiesService propertiesService = new PropertiesService();

    public SettingsPanel() {
        initPanel();
        createBackButton();
        createTitle();
    }

    private void initPanel() {
        setLayout(null);
        setBackground(Color.green);
    }

    private void createTitle() {
        JLabel label = new JLabel("Settings", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        label.setBounds(150, 20, 200, 50);
        add(label);
    }

    private void createBackButton() {
        JButton backButton = new JButton();
        backButton.setBounds(10, 20, 64, 64);
        backButton.setIcon(getImageIcon(propertiesService.getProperties("back.icon")));
        backButton.setBackground(new Color(27, 165, 224));
        backButton.setFocusPainted(false);
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.addActionListener(e -> {
            MainView mainView = (MainView) SwingUtilities.getWindowAncestor(this);
            mainView.showPanel("Menu");
        });
        this.add(backButton);
    }

    public ImageIcon getImageIcon(String key) {
        return new ImageIcon(key);
    }

}
