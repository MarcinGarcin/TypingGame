import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class LabelOperator {
    private final String[] texts = {
            "table", "chair", "computer", "cup", "book", "window", "door", "wardrobe", "telephone", "lamp",
            "carpet", "curtain", "television", "radio", "sofa", "bed", "fridge", "oven", "stove", "sink",
            "bathtub", "shower", "mirror", "picture", "clock", "vase", "plant", "lamp", "pillow", "blanket",
            "rug", "desk", "pencil", "pen", "eraser", "notebook", "backpack", "glasses", "keys", "wallet"
    };
    private final JPanel panel;
    private HoverLabelPanel hoverLabel;
    private final ArrayList<Timer> timers;
    public final ArrayList<JLabel> labels;
    private final Random rand;
    private final int textFramesAmount = 20;
    private int score = 0;
    private JLabel endGameLabel;
    private Window window;
    private float gameHardness = 1;
    private float initLabelSpeed = 5;

    public LabelOperator(Window window,JPanel panel) {
        this.window = window;
        this.panel = panel;
        rand = new Random();
        labels = new ArrayList<>();
        timers = new ArrayList<>();
        setupTextArea();
        setupLoseArea();
        startGame();
    }

    private void startGame() {
        for (int i = 0; i < textFramesAmount; i++) {
            JLabel currentLabel = labels.get(i);
            Timer timer = new Timer(50, e -> {
                gameHardness += 0.00003;
                currentLabel.setLocation(currentLabel.getX() + Math.round(initLabelSpeed*gameHardness), currentLabel.getY());
                if (currentLabel.getX() >= window.getWidth()) {
                    endGame();
                }
                panel.repaint();
                panel.revalidate();
            });
            timers.add(timer);
            timer.start();
        }
        keyTyper(labels.get(0));
    }

    private void setupLoseArea() {
        endGameLabel = new JLabel("Game Over");
        endGameLabel.setBounds(450, 300, 500, 50);
        endGameLabel.setForeground(Color.RED);
        endGameLabel.setFont(new Font("Arial", Font.BOLD, 50));
        endGameLabel.setVisible(false);
        panel.add(endGameLabel);

        hoverLabel = new HoverLabelPanel(window,500, 400, 200, 50);
        hoverLabel.setVisible(false);
        panel.add(hoverLabel);
    }

    private void setupTextArea() {
        for (int i = 0; i < textFramesAmount; i++) {
            JLabel label = createLabel(i);
            panel.add(label);
            labels.add(label);
        }
    }

    private JLabel createLabel(int index) {
        JLabel label = new JLabel(texts[rand.nextInt(texts.length)]);
        label.setBounds((index * -150) - 150, rand.nextInt(650) + 15, 200, 50);
        label.setForeground(Color.white);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        label.setVisible(true);
        return label;
    }

    private void keyTyper(JLabel currentLabel) {
        panel.setFocusable(true);
        panel.requestFocusInWindow();

        panel.addKeyListener(new KeyAdapter() {
            private String correctText = "";
            private final String labelText = currentLabel.getText();

            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == labelText.charAt(correctText.length())) {
                    correctText += e.getKeyChar();
                    updateLabelColor(currentLabel, correctText, labelText);

                    if (labelText.length() == correctText.length()) {
                        moveToEnd(currentLabel);
                        correctText = "";
                        labels.removeFirst();
                        labels.add(currentLabel);
                        panel.removeKeyListener(this);
                        score += labelText.length();

                        if (!labels.isEmpty()) {
                            keyTyper(labels.get(0));
                        }
                    }
                }
            }
        });
    }

    private void moveToEnd(JLabel label) {
        label.setLocation(labels.get(labels.size() - 1).getX() - 150, label.getY());
        label.setText(texts[rand.nextInt(texts.length)]);
    }

    private void updateLabelColor(JLabel label, String correctText, String labelText) {
        String remainingText = labelText.substring(correctText.length());
        label.setText("<html><font color=green>" + correctText + "</font><font color=white>" + remainingText + "</font></html>");
    }
    public int getScore(){
        return score;
    }
    private void endGame() {
        score = 0;
        for (Timer timer : timers) {
            timer.stop();
            window.stopTime();
        }
        endGameLabel.setVisible(true);
        hoverLabel.setVisible(true);
    }
}