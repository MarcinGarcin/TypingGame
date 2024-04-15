import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class LabelOperator {
    private JPanel panel;
    private ArrayList<Timer> timers;
    public ArrayList<JLabel> labels;
    private String playerInput = "";
    private Random rand;

    public LabelOperator(JPanel panel){
        this.panel = panel;
        setupTextArea();
        startGame();
    }

    void startGame(){
        setupTextArea();
        timers = new ArrayList<Timer>();
        for (int i = 0; i < 4; i++) {
            final int finalIndex = i; // Dodajemy tę linię
            keyTyper(labels.get(finalIndex));
            Timer timer = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JLabel currentLabel = labels.get(finalIndex);
                    currentLabel.setLocation(currentLabel.getX()+10, currentLabel.getY());
                    currentLabel.setText("chuj");
                    panel.repaint();
                    panel.revalidate();
                }
            });
            timers.add(timer);
            int delay = rand.nextInt(5000); // Losowe opóźnienie do 5 sekund
            new Timer(delay, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    timer.start();
                }
            }).start();
        }
    }

    private void setupTextArea(){
        labels = new ArrayList<JLabel>();
        rand = new Random();
        for(int i = 0;i<10;i++){
            JLabel label = new JLabel("label"+i);
            label.setBounds(-100,rand.nextInt(720),200,50);
            label.setForeground(Color.white);
            label.setFont(new Font("Arial",Font.BOLD,20));
            label.setVisible(true);
            panel.add(label);
            labels.add(label);
        }
    }
    private void keyTyper(JLabel label){
        playerInput = "";
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                playerInput += e.getKeyChar();
                if(label.getText().contains(playerInput)){
                    label.setForeground(Color.GREEN);
                }
                else{
                    label.setForeground(Color.RED);
                }
            }
        });
    }
}
