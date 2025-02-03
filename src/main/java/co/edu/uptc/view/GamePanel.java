package co.edu.uptc.view;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public GamePanel() {
        setBackground(Color.BLACK);
        JLabel label = new JLabel("Game", SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        add(label);
    }
}