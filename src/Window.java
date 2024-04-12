import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;

public class Window extends JFrame {
    int width = 1280;
    int height = 720;
    public Window(){
        setPreferredSize(new Dimension(width, height));
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel startText = new JPanel();
        startText.setSize(new Dimension(1280,720));
        startText.setBackground(new Color(51,51,51));
        startText.setLayout(new GridLayout(3,3));
        JTextArea textArea = new JTextArea("Ssij pale");
        startText.add(textArea);
        add(startText);
        pack();


    }
}
