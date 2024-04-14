import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameEngine {
    private JPanel panel;
    private ArrayList<JLabel> labels;
    private Timer timer;

    public GameEngine(JPanel panel , ArrayList<JLabel> labels){
        this.panel = panel;
        this.labels = labels;
    }
    void startGame(){
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labels.get(1).setLocation(labels.get(1).getX()+10, labels.get(1).getY());
                System.out.println(labels.get(1).getLocation());
                panel.repaint();
                panel.revalidate();
            }
        });
        timer.start();

    }

}