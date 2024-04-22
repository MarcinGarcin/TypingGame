import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HoverLabelPanel extends JPanel {
    private Window window;
    private JLabel hoverLabel;
    private int x, y, width, height;
    private Color bg = new Color(51, 51, 51);
    private boolean hovered = false;

    public HoverLabelPanel(Window window, int x, int y, int width, int height) {
        this.window = window;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        setupPanel();
        setupLabel();
    }

    private void setupPanel() {
        setBounds(x, y, width, height);
        setVisible(true);
        setBackground(bg);
        setOpaque(false);
    }

    private void setupLabel() {
        hoverLabel = new JLabel("Try again");
        hoverLabel.setForeground(Color.WHITE);
        hoverLabel.setFont(new Font("Arial", Font.BOLD, 30));
        hoverLabel.setVisible(true);
        add(hoverLabel);
        addMouseListener(new LabelMouseListener());
    }

    private class LabelMouseListener extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            hovered = true;
            repaint();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            hovered = false;
            repaint();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            window.startNewGame();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        if (hovered) {
            hoverLabel.setForeground(Color.lightGray);
        } else {
            hoverLabel.setForeground(Color.white);
        }
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
}