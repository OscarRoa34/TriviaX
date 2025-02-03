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
        musicLabel.setBounds(150, 400, 100, 30);
        add(musicLabel);
        JCheckBox musicCheckBox = new JCheckBox();
        musicCheckBox.setBounds(300, 400, 40, 40);
        musicCheckBox.setOpaque(false);
        musicCheckBox.setFocusPainted(false);
        UIManager.put("CheckBox.background", new Color(0, 0, 0, 0));
        UIManager.put("CheckBox.foreground", Color.WHITE);
        UIManager.put("CheckBox.icon", new CustomCheckBoxIcon());
        SwingUtilities.updateComponentTreeUI(musicCheckBox);
        add(musicCheckBox);
    }

    private void createTimerSetting() {
        JLabel timerLabel = new JLabel("Timer");
        timerLabel.setForeground(Color.WHITE);
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        timerLabel.setBounds(150, 300, 100, 30);
        add(timerLabel);
        JCheckBox timerCheckBox = new JCheckBox();
        timerCheckBox.setBounds(300, 300, 40, 40);
        timerCheckBox.setOpaque(false);
        timerCheckBox.setFocusPainted(false);
        UIManager.put("CheckBox.background", new Color(0, 0, 0, 0));
        UIManager.put("CheckBox.foreground", Color.WHITE);
        UIManager.put("CheckBox.icon", new CustomCheckBoxIcon());

        SwingUtilities.updateComponentTreeUI(timerCheckBox);
        add(timerCheckBox);
    }

    class CustomCheckBoxIcon implements Icon {
        private final int SIZE = 20;

        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
            JCheckBox checkBox = (JCheckBox) c;
            ButtonModel model = checkBox.getModel();
            g.setColor(new Color(30, 30, 70));
            g.fillRect(x, y, SIZE, SIZE);
            g.setColor(Color.WHITE);
            g.drawRect(x, y, SIZE, SIZE);
            if (model.isSelected()) {
                g.setColor(Color.WHITE);
                g.drawLine(x + 3, y + SIZE / 2, x + SIZE / 2, y + SIZE - 3);
                g.drawLine(x + SIZE / 2, y + SIZE - 3, x + SIZE - 3, y + 3);
            }
        }

        @Override
        public int getIconWidth() {
            return SIZE;
        }

        @Override
        public int getIconHeight() {
            return SIZE;
        }
    }

    public ImageIcon getImageIcon(String key) {
        return new ImageIcon(key);
    }
}