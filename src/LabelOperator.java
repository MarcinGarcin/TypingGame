import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class LabelOperator {
    private String[] texts = {"table", "chair", "computer", "cup", "book", "window", "door", "wardrobe", "telephone", "lamp", "carpet", "curtain", "television", "radio", "sofa",
            "bed", "fridge", "oven", "stove", "sink", "bathtub", "shower", "mirror", "picture", "clock", "vase", "plant", "lamp", "pillow", "blanket", "rug", "desk", "pencil",
            "pen", "eraser", "notebook", "backpack", "glasses", "keys", "wallet"};
    private JPanel panel;
    private ArrayList<Timer> timers;
    public ArrayList<JLabel> labels;
    private Random rand;

    public LabelOperator(JPanel panel) {
        this.panel = panel;
        setupTextArea();
        startGame();
    }

    void startGame() {
        setupTextArea();
        timers = new ArrayList<Timer>();
        for (int i = 0; i < 1; i++) {
            final int finalIndex = i;
            JLabel currentLabel = labels.get(finalIndex);
            currentLabel.setText(texts[rand.nextInt(39)]);
            Timer timer = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentLabel.setLocation(currentLabel.getX() + 10, currentLabel.getY());
                    System.out.println(currentLabel.getLocation());
                    panel.repaint();
                    panel.revalidate();
                }
            });
            timers.add(timer);
            int delay = rand.nextInt(10000);
            new Timer(delay, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    timer.start();
                }
            }).start();
        }
        keyTyper();
    }

    private void setupTextArea() {
        labels = new ArrayList<JLabel>();
        rand = new Random();
        for (int i = 0; i < 1; i++) {
            JLabel label = new JLabel("label" + i);
            label.setBounds(-200, rand.nextInt(680), 200, 50);
            label.setForeground(Color.white);
            label.setFont(new Font("Arial", Font.BOLD, 20));
            label.setVisible(true);
            label.putClientProperty("html.disable", Boolean.FALSE);
            panel.add(label);
            labels.add(label);
        }
    }

    private void keyTyper() {
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        panel.addKeyListener(new KeyAdapter() {
            private String playerInput = "";
            private int correctChars = 0;

            @Override
            public void keyTyped(KeyEvent e) {
                if (!labels.isEmpty()) {
                    JLabel currentLabel = labels.get(0);
                    String labelText = currentLabel.getText().replaceAll("<[^>]*>", ""); // Remove HTML tags
                    playerInput += e.getKeyChar();
                    if (playerInput.equals(labelText.substring(0, playerInput.length()))) {
                        correctChars = playerInput.length();
                        updateLabelColor(currentLabel, correctChars);
                        if (correctChars == labelText.length()) {
                            currentLabel.setLocation(0, currentLabel.getY());
                            System.out.println(currentLabel.getLocation());
                            labels.remove(0);
                            playerInput = "";
                            correctChars = 0;
                        }
                    } else {
                        playerInput = playerInput.substring(0, playerInput.length() - 1);
                    }
                }
            }
        });
    }

    private void updateLabelColor(JLabel label, int correctChars) {
        String text = label.getText();
        String correctText = text.substring(0, correctChars);
        String remainingText = text.substring(correctChars);
        label.setText("<html><font color='green'>" + correctText + "</font><font color='white'>" + remainingText + "</font></html>");
    }
}