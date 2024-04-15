import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.random.RandomGenerator;

public class GameEngine {
    private JPanel panel;
    private Timer timer;

    public GameEngine(JPanel panel ){
        this.panel = panel;
    }
    void startGame(){
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                panel.repaint();
                panel.revalidate();
            }
        });
        timer.start();

    }
    private void setupTextArea(){
        for(int i = 0;i<8;i++){
            JLabel label = new JLabel("Label"+i);


        }

    }

}