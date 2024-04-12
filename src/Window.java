import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame {
    int x = 0;
    int width = 1280;
    int height = 720;
    Color bg = new Color(51, 51, 51);

    public Window() {
        setPreferredSize(new Dimension(width, height));
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setSize(new Dimension((int) (width*1.2), height));
        mainPanel.setLayout(null);
        mainPanel.setBackground(bg);
        BlinkingLabel textArea = new BlinkingLabel("Press spacebar to play");
        textArea.setForeground(Color.white);
        textArea.setFont(new Font("Arial", Font.BOLD, 40));
        int textWidth = (width - textArea.getPreferredSize().width) / 2;
        int textHeight = (height - textArea.getPreferredSize().height) / 2;
        textArea.setBounds(textWidth, textHeight, textArea.getPreferredSize().width, textArea.getPreferredSize().height);
        mainPanel.add(textArea);
        add(mainPanel);
        pack();
        addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == ' ') {
                    textArea.stopBlinking();
                    JLabel label = new JLabel("gra");
                    label.setBounds(textWidth, textHeight + 50, label.getPreferredSize().width, label.getPreferredSize().height);
                    mainPanel.add(label);
                    mainPanel.revalidate();
                    mainPanel.repaint();
                }
            }
        });
        pack();
    }
}
