package co.edu.uptc.Utils;

import javax.swing.*;
import java.awt.*;

public class StrokeLabel extends JLabel {

    private Color strokeColor;
    private float strokeWidth;

    public StrokeLabel(String text, Color strokeColor, float strokeWidth) {
        super(text);
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
        setOpaque(false); // Hacer el fondo transparente
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Habilitar antialiasing para mejor calidad
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Obtener el texto y la fuente
        String text = getText();
        Font font = getFont();

        // Calcular la posición del texto
        FontMetrics metrics = g2d.getFontMetrics(font);
        int x = (getWidth() - metrics.stringWidth(text)) / 2;
        int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();

        // Dibujar el stroke
        g2d.setColor(strokeColor);
        g2d.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2d.setFont(font);
        g2d.drawString(text, x, y);

        // Dibujar el texto relleno
        g2d.setColor(getForeground());
        g2d.drawString(text, x, y);

        g2d.dispose();
    }
}