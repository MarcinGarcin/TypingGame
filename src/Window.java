
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Window extends JFrame {
    private int x = 0;
    private int width = 1280;
    private int height = 720;
    private Color bg = new Color(51, 51, 51);
    private JPanel mainPanel;
    private BlinkingLabel startTextArea;
    private ArrayList<JLabel> textAreas;
    private LabelOperator labelOperator;

    public Window() {
        setupWindow();
        setupMainPanel();
        setupStartTextArea();
        setupKeyListener();

    }

    private void setupWindow() {
        setPreferredSize(new Dimension(width, height));
        setVisible(true);
        requestFocusInWindow();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setFocusable(true);
    }


    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setSize(new Dimension((int) (width*1.2), height));
        mainPanel.setLayout(null);
        mainPanel.setBackground(bg);
        mainPanel.setFocusable(true);
        add(mainPanel);
        pack();
    }
    private void setupStartTextArea() {
        startTextArea = new BlinkingLabel("Press spacebar to play");
        startTextArea.setForeground(Color.white);
        startTextArea.setFont(new Font("Arial", Font.BOLD, 40));
        int textWidth = (width - startTextArea.getPreferredSize().width) / 2;
        int textHeight = (height - startTextArea.getPreferredSize().height) / 2;
        startTextArea.setBounds(textWidth, textHeight, startTextArea.getPreferredSize().width, startTextArea.getPreferredSize().height);
        mainPanel.add(startTextArea);
    }


    private void setupKeyListener() {
        addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == ' ') {
                    handleSpacebarPress();
                    removeKeyListener(this);

                }
            }
        });
    }

    private void handleSpacebarPress() {
        startTextArea.stopBlinking();
        new LabelOperator(mainPanel);
        Timer Timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
        Timer.start();
    }

}