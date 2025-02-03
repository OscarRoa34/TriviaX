package co.edu.uptc.view;

import javax.swing.*;
import java.awt.*;

public class RecordsPanel extends JPanel {
    public RecordsPanel() {
        setBackground(Color.yellow);
        JLabel label = new JLabel("Records", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label);
    }
}
