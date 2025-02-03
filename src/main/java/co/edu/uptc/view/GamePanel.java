package co.edu.uptc.view;

import javax.swing.*;

import co.edu.uptc.Utils.PropertiesService;

import java.awt.*;

public class GamePanel extends JPanel {

    private PropertiesService propertiesService = new PropertiesService();

    public GamePanel() {
        initPanel();
        createBackButton();
        createTitle();
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

    private void initPanel() {
        setLayout(null);
    }

    private void createTitle() {
        JLabel label = new JLabel("Game", SwingConstants.CENTER);
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