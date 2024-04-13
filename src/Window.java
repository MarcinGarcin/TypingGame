
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
    private JLabel textArea;
    private ArrayList<JLabel> textAreas;
    private GameEngine gameEngine;

    public Window() {
        setupWindow();
        setupMainPanel();
        setupStartTextArea();
        setupTextAreas();
        setupKeyListener();
        gameEngine = new GameEngine(width, height, mainPanel);
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
        mainPanel.setSize(new Dimension(width, height));
        mainPanel.setLayout(null);
        mainPanel.setBackground(bg);
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
    private void setupTextArea(){
        textArea = new JLabel("example");
        textArea.setForeground(Color.white);
        textArea.setFont(new Font("Arial", Font.BOLD, 15));
        int textWidth = (width - startTextArea.getPreferredSize().width) / 2;
        int textHeight = (height - startTextArea.getPreferredSize().height) / 2;
        textArea.setBounds(0, textHeight, startTextArea.getPreferredSize().width, startTextArea.getPreferredSize().height);
        mainPanel.add(textArea);
        textArea.setVisible(false);
    }

    private void setupKeyListener() {
        addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == ' ') {
                    handleSpacebarPress();
                } else {
                    gameEngine.handleKeyPress(e.getKeyChar());
                }
            }
        });
    }

    private void handleSpacebarPress() {
        startTextArea.stopBlinking();
        gameEngine.startGame();
        Timer Timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
        Timer.start();
    }
    private void setupTextAreas(){
        textAreas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            JLabel textArea = new JLabel("example" + i);
            textArea.setForeground(Color.white);
            textArea.setFont(new Font("Arial", Font.BOLD, 15));
            int textWidth = (width - textArea.getPreferredSize().width) / 2;
            int textHeight = (height - textArea.getPreferredSize().height) / 2 + i * 50;
            textArea.setBounds(0, textHeight, textArea.getPreferredSize().width, textArea.getPreferredSize().height);
            mainPanel.add(textArea);
            textArea.setVisible(false);
            textAreas.add(textArea);
        }
    }
}