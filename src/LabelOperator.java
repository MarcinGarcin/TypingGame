import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class LabelOperator {
    private JPanel panel;
    private Timer timer;
    private ArrayList<JLabel> labels;

    public LabelOperator(JPanel panel ){
        this.panel = panel;
    }

    void startGame(){
        //setupTextArea();
        JLabel label = new JLabel("chuj");
        label.setBounds(500,500,200,100);
        label.setForeground(Color.white);
        label.setVisible(true);
        label.setFont(new Font("Arial",Font.BOLD,20));
        panel.add(label);
        timer = new Timer(3, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setLocation(label.getX()+1, label.getY());
                System.out.println(label.getX());


                panel.repaint();
                panel.revalidate();
            }
        });
        timer.start();

    }
    private void setupTextArea(){
        JLabel label = new JLabel("chuj");
        label.setLocation(500,500);
        labels.add(label);

    }

}