import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class LabelOperator {
    private JPanel panel;
    private Timer timer;
    public ArrayList<JLabel> labels;

    public LabelOperator(JPanel panel ){
        this.panel = panel;
        setupTextArea();
        startGame();
    }

    void startGame(){
        setupTextArea();
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel currentLabel = labels.get(0);
                currentLabel.setLocation(currentLabel.getX()+10, currentLabel.getY());
                currentLabel.setText("Masny kurwa ben");
                panel.repaint();
                panel.revalidate();
            }
        });
        timer.start();

    }
    private void setupTextArea(){
        labels = new ArrayList<JLabel>();
        Random rand = new Random();
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

}