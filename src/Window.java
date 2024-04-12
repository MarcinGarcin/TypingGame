import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame {
    private int x = 0;
    private int width = 1280;
    private int height = 720;
    private Color bg = new Color(51, 51, 51);
    private JPanel mainPanel;
    private BlinkingLabel textArea;

    public Window() {
        setupWindow();
        setupMainPanel();
        setupTextArea();
        setupKeyListener();
    }

    private void setupWindow() {
        setPreferredSize(new Dimension(width, height));
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
    }

    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setSize(new Dimension((int) (width*1.2), height));
        mainPanel.setLayout(null);
        mainPanel.setBackground(bg);
        add(mainPanel);
        pack();
    }

    private void setupTextArea() {
        textArea = new BlinkingLabel("Press spacebar to play");
        textArea.setForeground(Color.white);
        textArea.setFont(new Font("Arial", Font.BOLD, 40));
        int textWidth = (width - textArea.getPreferredSize().width) / 2;
        int textHeight = (height - textArea.getPreferredSize().height) / 2;
        textArea.setBounds(textWidth, textHeight, textArea.getPreferredSize().width, textArea.getPreferredSize().height);
        mainPanel.add(textArea);
    }

    private void setupKeyListener() {
        addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == ' ') {
                    handleSpacebarPress();
                }
            }
        });
    }

    private void handleSpacebarPress() {
        textArea.stopBlinking();
        JLabel label = new JLabel("gra");
        mainPanel.add(label);
        Timer timer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveLabel(label);
            }
        });
        timer.start();
    }

    private void moveLabel(JLabel label) {
        x = x + 10;
        label.setLocation(500, 500 / 2);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
