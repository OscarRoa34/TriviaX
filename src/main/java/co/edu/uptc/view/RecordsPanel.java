package co.edu.uptc.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import co.edu.uptc.Utils.PropertiesService;
import co.edu.uptc.model.Record; // Asegúrate de que la clase Record esté en este paquete
import co.edu.uptc.model.RecordsLoader; // Asegúrate de que la clase RecordsLoader esté en este paquete

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
        // Columnas de la tabla
        String[] columnNames = { "Nombre", "Aciertos" };

        // Obtener los datos de la base de datos
        List<Record> records = RecordsLoader.loadRecordsFromDatabase();

        // Convertir los registros en una matriz de objetos para la JTable
        Object[][] data = new Object[records.size()][2];
        for (int i = 0; i < records.size(); i++) {
            Record record = records.get(i);
            data[i][0] = (i + 1) + ". " + record.getName(); // Nombre con número de posición
            data[i][1] = record.getCorrectChoices(); // Aciertos
        }

        // Crear el modelo de la tabla
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // Crear la JTable con el modelo
        JTable table = new JTable(model);
        table.setForeground(Color.WHITE);
        table.setBackground(new Color(30, 30, 70));
        table.setFont(new Font("Arial", Font.PLAIN, 16));
        table.setRowHeight(30);
        table.setEnabled(false); // Hacer la tabla no editable

        // Agregar la tabla a un JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(43, 250, 400, 150);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        // Agregar el JScrollPane al panel
        this.add(scrollPane);
    }

    public ImageIcon getImageIcon(String key) {
        return new ImageIcon(key);
    }
}