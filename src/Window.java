import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    int width = 1280;
    int height = 720;

    public Window() {
        setPreferredSize(new Dimension(width, height));
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setSize(new Dimension(width, height));
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(51, 51, 51));
        JTextArea textArea = new JTextArea("Ssij pale");

        int textWidth = (width - textArea.getPreferredSize().width) / 2;
        int textHeight = (height - textArea.getPreferredSize().height) / 2;
        textArea.setBounds(textWidth, textHeight, textArea.getPreferredSize().width, textArea.getPreferredSize().height);
        mainPanel.add(textArea);
        add(mainPanel);
        pack();
    }
}

