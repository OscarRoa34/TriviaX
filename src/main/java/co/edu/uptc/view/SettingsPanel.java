package co.edu.uptc.view;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {
    public SettingsPanel() {
        setBackground(Color.green);
        JLabel label = new JLabel("Settings", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label);
    }
}
