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
    private int textFramesAmount = 10;

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
            Timer timer = new Timer(40, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentLabel.setLocation(currentLabel.getX() + 5, currentLabel.getY());
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
            label.setBounds((i*-200)-200, rand.nextInt(650)+15, 200, 50);
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

        KeyListener keyListener = new KeyAdapter() {
            private String correctText = "";
            String labelText = currentLabel.getText();

            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println(labelText);
                if (e.getKeyChar() == labelText.charAt(correctText.length())) {
                    correctText += e.getKeyChar();
                    updateLabelColor(currentLabel, correctText, labelText);
                }
                if (labelText.length() == correctText.length()) {
                    currentLabel.setLocation(labels.getLast().getX()-150, currentLabel.getY());
                    currentLabel.setText(texts[rand.nextInt(texts.length)]);
                    correctText = "";
                    labels.remove(0);
                    labels.add(currentLabel);

                    panel.removeKeyListener(this);

                    if (!labels.isEmpty()) {
                        keyTyper(labels.get(0));
                    }
                }
            }
        };

        panel.addKeyListener(keyListener);
    }

    private void updateLabelColor(JLabel label, String correctText, String labelText) {
        String remainingText = labelText.substring(correctText.length());
        label.setText("<html><font color=green>" + correctText + "</font><font color=white>" + remainingText + "</font></html>");
    }
}