package co.edu.uptc.view;

import javax.swing.*;
import java.awt.*;

import co.edu.uptc.Utils.PropertiesService;

public class GamePanel extends JPanel {

    private PropertiesService propertiesService = new PropertiesService();

    public GamePanel() {
        initPanel();
        createBackButton();
        createTriviaLayout();
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

    private void createTriviaLayout() {
        RoundedLabel questionLabel = new RoundedLabel("¿Cuál es la capital de Francia?");
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setFont(new Font("Arial", Font.BOLD, 20));
        questionLabel.setBounds(30, 110, 425, 200);
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        questionLabel.setBackground(new Color(27, 165, 224));
        add(questionLabel);
        JButton optionA = createOptionButton("a) París", 30, 350);
        JButton optionB = createOptionButton("b) Londres", 30, 420);
        JButton optionC = createOptionButton("c) Berlín", 30, 490);
        JButton optionD = createOptionButton("d) Madrid", 30, 560);
        add(optionA);
        add(optionB);
        add(optionC);
        add(optionD);
    }

    private JButton createOptionButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 425, 50);
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(30, 30, 70));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        button.addActionListener(e -> {
            JButton selectedButton = (JButton) e.getSource();
            System.out.println("Respuesta seleccionada: " + selectedButton.getText());
        });
        return button;
    }

    public ImageIcon getImageIcon(String key) {
        return new ImageIcon(key);
    }

    class RoundedLabel extends JLabel {
        private int arcWidth = 30;
        private int arcHeight = 30;

        public RoundedLabel(String text) {
            super(text);
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(getBackground());
            g2d.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
            super.paintComponent(g2d);
            g2d.dispose();
        }
    }
}