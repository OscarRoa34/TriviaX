package co.edu.uptc.view;

import javax.swing.*;
import java.awt.*;

import co.edu.uptc.Utils.PropertiesService;

public class SettingsPanel extends JPanel {

    private PropertiesService propertiesService = new PropertiesService();

    public SettingsPanel() {
        initPanel();
        createBackButton();
        createTitle();
        createMusicSetting();
        createTimerSetting();
    }

    private void initPanel() {
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        createBackground(g);
    }

    private void createBackground(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Color color1 = new Color(10, 10, 40);
        Color color2 = new Color(20, 20, 60);
        GradientPaint gradient = new GradientPaint(
                0, 0, color1,
                0, getHeight(), color2);
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }

    private void createTitle() {
        JLabel label = new JLabel("Settings", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 50));
        label.setBounds(140, 120, 200, 70);
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

    private void createMusicSetting() {
        JLabel musicLabel = new JLabel("Music");
        musicLabel.setForeground(Color.WHITE);
        musicLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        musicLabel.setBounds(150, 250, 100, 30);
        add(musicLabel);
        JCheckBox musicCheckBox = new JCheckBox();
        musicCheckBox.setBounds(300, 250, 30, 30);
        musicCheckBox.setBackground(new Color(30, 30, 70));
        musicCheckBox.setForeground(Color.WHITE);
        musicCheckBox.setFocusPainted(false);
        add(musicCheckBox);
    }

    private void createTimerSetting() {
        JLabel timerLabel = new JLabel("Timer");
        timerLabel.setForeground(Color.WHITE);
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        timerLabel.setBounds(150, 350, 100, 30);
        add(timerLabel);
        JCheckBox timerCheckBox = new JCheckBox();
        timerCheckBox.setBounds(300, 350, 30, 30);
        timerCheckBox.setBackground(new Color(30, 30, 70));
        timerCheckBox.setForeground(Color.WHITE);
        timerCheckBox.setFocusPainted(false);
        add(timerCheckBox);
    }

    public ImageIcon getImageIcon(String key) {
        return new ImageIcon(key);
    }
}