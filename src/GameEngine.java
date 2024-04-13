import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameEngine {
    final int panelWidth;
    final int panelHeight;
    private List<JLabel> labels;
    private List<String> words;
    private int currentWordIndex = 0;
    private String currentWord = "example";
    private int currentCharIndex = 0;
    private JPanel panel;

    public GameEngine(int panelWidth, int panelHeight, JPanel panel){
        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;
        this.labels = new ArrayList<>();
        this.words = List.of("example1", "example2", "example3");
        this.panel = panel;

    }

    public void startGame(){

        new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Random rand = new Random();
                int y = rand.nextInt(panelHeight - 50);
                JLabel currentLabel = labels.get(currentWordIndex);
                currentLabel.setLocation(0, y);
                currentLabel.setVisible(true);
                currentLabel.setForeground(Color.WHITE);

                new Timer(100, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int x = currentLabel.getLocation().x;
                        if (x < panelWidth) {
                            currentLabel.setLocation(x + 10, y);
                            System.out.println(currentLabel.getLocation());
                            panel.revalidate();
                            panel.repaint();
                        } else {
                            ((Timer)e.getSource()).stop();
                            currentWordIndex = (currentWordIndex + 1) % words.size();
                        }
                    }
                }).start();
            }
        }).start();
    }

    public void handleKeyPress(char keyChar) {
        if (currentWord.charAt(currentCharIndex) == keyChar) {
            currentCharIndex++;
            if (currentCharIndex == currentWord.length()) {
                currentCharIndex = 0;
                currentWord = words.get(currentWordIndex);
            }
        } else {
        }
    }
}
