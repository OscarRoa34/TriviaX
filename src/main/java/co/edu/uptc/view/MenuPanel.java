package co.edu.uptc.view;

import javax.swing.*;
import co.edu.uptc.Utils.PropertiesService;
import co.edu.uptc.Utils.StrokeLabel;
import java.awt.*;
import java.io.InputStream;

public class MenuPanel extends JPanel {

    private StrokeLabel title;
    private PropertiesService propertiesService;

    public MenuPanel() {
        propertiesService = new PropertiesService();
        setLayout(null);
        createTitle();
        createPlayButton();
        createBottomLabel();
        createExitButton();
        createSettingsButton();
        createRecordsButton();
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

    public Font loadFont(String fontPath, float size) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fontPath)) {
            if (inputStream == null) {
                throw new RuntimeException("No se pudo cargar la fuente: " + fontPath);
            }
            Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            return font.deriveFont(size);
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar la fuente: " + e.getMessage(), e);
        }
    }

    private void createTitle() {
        title = new StrokeLabel("TriviaX", new Color(27, 165, 224), 20f);
        title.setBounds(119, 71, 253, 84);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        Font legoFont = loadFont(propertiesService.getProperties("logo.font"), 64f);
        title.setFont(legoFont);
        title.setForeground(Color.WHITE);
        this.add(title);
    }

    private void createPlayButton() {
        JButton playButton = new JButton();
        playButton.setBounds(180, 150, 128, 250);
        playButton.setIcon(getImageIcon(propertiesService.getProperties("play.icon")));
        playButton.setBackground(new Color(27, 165, 224));
        playButton.setFocusPainted(false);
        playButton.setBorderPainted(false); // No mostrar borde
        playButton.setContentAreaFilled(false); // No rellenar área
        playButton.addActionListener(e -> {
            MainView mainView = (MainView) SwingUtilities.getWindowAncestor(this);
            mainView.showPanel("Game");
        });
        this.add(playButton);
    }

    private void createSettingsButton() {
        JButton settingsButton = new JButton();
        settingsButton.setBounds(32, 150, 128, 250);
        settingsButton.setIcon(getImageIcon(propertiesService.getProperties("settings.icon")));
        settingsButton.setBackground(new Color(27, 165, 224));
        settingsButton.setFocusPainted(false);
        settingsButton.setBorderPainted(false);
        settingsButton.setContentAreaFilled(false);
        settingsButton.addActionListener(e -> {
            MainView mainView = (MainView) SwingUtilities.getWindowAncestor(this);
            mainView.showPanel("Settings");
        });
        this.add(settingsButton);
    }

    private void createRecordsButton() {
        JButton recordsButton = new JButton();
        recordsButton.setBounds(328, 150, 128, 250);
        recordsButton.setIcon(getImageIcon(propertiesService.getProperties("trophy.icon")));
        recordsButton.setBackground(new Color(27, 165, 224));
        recordsButton.setFocusPainted(false);
        recordsButton.setBorderPainted(false);
        recordsButton.setContentAreaFilled(false);
        recordsButton.addActionListener(e -> {
            MainView mainView = (MainView) SwingUtilities.getWindowAncestor(this);
            mainView.showPanel("Records");
        });
        this.add(recordsButton);
    }

    private void createExitButton() {
        JButton exitButton = new JButton();
        exitButton.setBounds(180, 300, 128, 250);
        exitButton.setIcon(getImageIcon(propertiesService.getProperties("exit.icon")));
        exitButton.setBackground(new Color(27, 165, 224));
        exitButton.setFocusPainted(false);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.addActionListener(e -> System.exit(0));
        this.add(exitButton);
    }

    private void createBottomLabel() {
        JLabel bottomLabel = new JLabel("© 2021 TriviaX. Todos los derechos reservados.");
        bottomLabel.setBounds(120, 600, 400, 50);
        bottomLabel.setFont(new Font("Arial", Font.BOLD, 12));
        bottomLabel.setForeground(Color.WHITE);
        this.add(bottomLabel);
    }

    public ImageIcon getImageIcon(String key) {
        return new ImageIcon(key);
    }

}