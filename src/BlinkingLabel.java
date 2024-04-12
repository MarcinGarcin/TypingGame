import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlinkingLabel extends JLabel {
    private static final int BLINKING_RATE = 750; // in ms
    private Timer timer;

    public BlinkingLabel(String text) {
        super(text);
        timer = new Timer(BLINKING_RATE , new ActionListener(){
            private boolean isVisible = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                isVisible = !isVisible;
                setVisible(isVisible);
            }
        });
        timer.start();
    }

    public void stopBlinking() {
        timer.stop();
        setVisible(false);
    }
}
