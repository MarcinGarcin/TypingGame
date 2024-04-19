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
        for (int i = 0; i < 10; i++) {
            final int finalIndex = i;
            JLabel currentLabel = labels.get(finalIndex);
            currentLabel.setText(texts[rand.nextInt(39)]);
            Timer timer = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentLabel.setLocation(currentLabel.getX() + 10, currentLabel.getY());
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
        for (int i = 0; i < 10; i++) {
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
            private String incorrectText = "";
            private int correctChars = 0;
            JLabel currentLabel = labels.get(0);
            String labelText = currentLabel.getText();

            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println(labelText);
                System.out.println(playerInput);

                char actualChar = e.getKeyChar();
                if(!(actualChar == ' ')){
                    playerInput += e.getKeyChar();
                }
                if (labelText.contains(playerInput)) {
                    correctChars = playerInput.length();
                    updateLabelColor(currentLabel, correctChars, incorrectText);
                    if (correctChars == labelText.length()) {
                        currentLabel.setLocation(0, currentLabel.getY());
                        labels.remove(0);
                        playerInput = "";
                        correctChars = 0;
                    }
                }
            }
        });
    }

    private void updateLabelColor(JLabel label, int correctChars,String incorrectText) {
        String text = label.getText();
        String correctText = text.substring(0, correctChars);
        String remainingText = text.substring(correctChars);
        label.setForeground(Color.green);
    }
}