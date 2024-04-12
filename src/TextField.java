import javax.swing.*;
import java.awt.*;

public class TextField extends JLabel {
    int x = 400;
    int y = 400;
    int width = 200;
    int height = 100;

    public TextField() {
        setBounds(x,y,width,height);
        setText("chuj");
        setVisible(true);
        setBackground(Color.red);
        setForeground(Color.white);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }
}

