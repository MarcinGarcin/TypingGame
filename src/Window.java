import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    int width = 1280;
    int height = 720;
    Color bg = new Color(51, 51, 51);

    public Window() {
        setPreferredSize(new Dimension(width, height));
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setSize(new Dimension(width, height));
        mainPanel.setLayout(null);
        mainPanel.setBackground(bg);
        mainPanel.setForeground(Color.white);
        JTextArea textArea = new JTextArea("Press spacebar to play");
        textArea.setFont(new Font("Arial",Font.BOLD,30));


        int textWidth = (width - textArea.getPreferredSize().width) / 2;
        int textHeight = (height - textArea.getPreferredSize().height) / 2;
        textArea.setBounds(textWidth, textHeight, textArea.getPreferredSize().width, textArea.getPreferredSize().height);
        mainPanel.add(textArea);
        add(mainPanel);
        pack();
    }
}

