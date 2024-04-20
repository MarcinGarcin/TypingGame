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
    private int textFramesAmount = 2;

    public LabelOperator(JPanel panel) {
        this.panel = panel;
        rand = new Random();
        setupTextArea();
        startGame();
    }

    void startGame() {
        timers = new ArrayList<Timer>();
        for (int i = 0; i < textFramesAmount; i++) {
            final int finalIndex = i;
            JLabel currentLabel = labels.get(finalIndex);
            Timer timer = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentLabel.setLocation(currentLabel.getX() + 10, currentLabel.getY());
                    System.out.println(currentLabel.getText());
                    panel.repaint();
                }
            });
            timers.add(timer);
            timer.start();
        }
        keyTyper(labels.get(0));
    }

    private void setupTextArea() {
        labels = new ArrayList<JLabel>();
        for (int i = 0; i < textFramesAmount; i++) {
            JLabel label = new JLabel("label" + i);
            label.setBounds((i*-200)-200, rand.nextInt(680), 200, 50);
            label.setForeground(Color.white);
            label.setFont(new Font("Arial", Font.BOLD, 20));
            label.setVisible(true);
            label.setText(texts[rand.nextInt(texts.length)]);
            panel.add(label);
            labels.add(label);

        }
    }

    private void keyTyper(JLabel currentLabel) {
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        panel.addKeyListener(new KeyAdapter() {
            private String correctText = "";
            String labelText = currentLabel.getText();

            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == labelText.charAt(correctText.length())) {
                    correctText += e.getKeyChar();
                    updateLabelColor(currentLabel, correctText, labelText);
                }
                if (labelText.length() == correctText.length()) {
                    currentLabel.setLocation(-100, currentLabel.getY());
                    currentLabel.setText(texts[rand.nextInt(texts.length)]);
                    correctText = "";
                    labels.remove(0);
                    labels.add(currentLabel);
                    keyTyper(labels.get(0));
                }
            }
        });
    }

    private void updateLabelColor(JLabel label, String correctText, String labelText) {
        String remainingText = labelText.substring(correctText.length());
        label.setText("<html><font color=green>" + correctText + "</font><font color=white>" + remainingText + "</font></html>");
    }
}