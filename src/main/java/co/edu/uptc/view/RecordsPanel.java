package co.edu.uptc.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import co.edu.uptc.Utils.PropertiesService;

public class RecordsPanel extends JPanel {

    private PropertiesService propertiesService = new PropertiesService();

    public RecordsPanel() {
        initPanel();
        createBackButton();
        createTitle();
        createRecordsTable();
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
        JLabel label = new JLabel("Records", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 50));
        label.setBounds(120, 100, 250, 50);
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

    private void createRecordsTable() {
        String[] columnNames = { "Nombre", "Aciertos" };
        Object[][] data = {
                { "1. Oscar", 10 },
                { "2. Sara", 5 }
        };
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        table.setForeground(Color.WHITE);
        table.setBackground(new Color(30, 30, 70));
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(30);
        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(43, 250, 400, 150);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        this.add(scrollPane);
    }

    public ImageIcon getImageIcon(String key) {
        return new ImageIcon(key);
    }
}