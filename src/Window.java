
    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.*;

    public class Window extends JFrame {
        private int x = 0;
        private int width = 1280;
        private int height = 800;
        private Color bg = new Color(51, 51, 51);
        private Font labelsFont = new Font("Arial", Font.BOLD, 20);
        private JPanel mainPanel;
        private JPanel infoPanel;
        private BlinkingLabel startTextArea;
        public LabelOperator labelOperator;
        private JLabel scoreLabel;
        private JLabel timeLabel;
        private JLabel lettersPerSecondsLabel;
        private int time = 0;

        public Window() {
            setupWindow();
            setupMainPanel();
            setupInfoPanel();
            setupStartTextArea();
            setupKeyListener();
        }

        private void setupWindow() {
            setPreferredSize(new Dimension(width, height));
            setVisible(true);
            requestFocusInWindow();
            setResizable(false);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(null);
            setFocusable(true);
        }


        private void setupMainPanel() {
            mainPanel = new JPanel();
            mainPanel.setSize(new Dimension((int) (width*1.2), 720));
            mainPanel.setLayout(null);
            mainPanel.setBackground(bg);
            mainPanel.setFocusable(true);
            add(mainPanel);
            pack();
        }
        private void setupInfoPanel() {
            scoreLabel = new JLabel();
            scoreLabel.setText("Score: ");
            scoreLabel.setForeground(Color.white);
            scoreLabel.setBounds(width/4-100, 10, 200, 20);
            scoreLabel.setFont(labelsFont);

            timeLabel = new JLabel();
            timeLabel.setText("Time : ");
            timeLabel.setForeground(Color.white);
            timeLabel.setBounds(width/4*2-100, 10, 200, 20);
            timeLabel.setFont(labelsFont);

            lettersPerSecondsLabel = new JLabel();
            lettersPerSecondsLabel.setText("LPM : ");
            lettersPerSecondsLabel.setForeground(Color.white);
            lettersPerSecondsLabel.setBounds(width/4*3-100, 10, 200, 20);
            lettersPerSecondsLabel.setFont(labelsFont);

            infoPanel = new JPanel();
            infoPanel.setBounds(0, 720, width, 80);
            infoPanel.setBackground(new Color(30, 30, 30));
            infoPanel.setLayout(null);
            infoPanel.add(scoreLabel);
            infoPanel.add(timeLabel);
            infoPanel.add(lettersPerSecondsLabel);
            add(infoPanel);
            pack();
        }
        private void setupStartTextArea() {
            startTextArea = new BlinkingLabel("Press spacebar to play");
            startTextArea.setForeground(Color.white);
            startTextArea.setFont(new Font("Arial", Font.BOLD, 40));
            int textWidth = (width - startTextArea.getPreferredSize().width) / 2;
            int textHeight = (height - startTextArea.getPreferredSize().height) / 2;
            startTextArea.setBounds(textWidth, textHeight, startTextArea.getPreferredSize().width, startTextArea.getPreferredSize().height);
            mainPanel.add(startTextArea);
        }

        private void setupKeyListener() {
            addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    if (e.getKeyChar() == ' ') {
                        handleSpacebarPress();
                        removeKeyListener(this);
                    }
                }
            });
        }

        private void handleSpacebarPress() {
            startTextArea.stopBlinking();
            labelOperator = new LabelOperator(this,mainPanel);
            updateInfoLabels();
        }
        public void startNewGame() {
            mainPanel.removeAll();
            labelOperator = new LabelOperator(this,mainPanel);
            mainPanel.revalidate();
            mainPanel.repaint();
            time = 0;
        }
        private void updateScoreLabel(int score) {
            scoreLabel.setText("Score: " + score);
        }

        private void updateTimeLabel(int time) {
            int minutes = time / 60;
            int seconds = time % 60;
            timeLabel.setText(String.format("Time: %d:%02d", minutes, seconds));

        }
        private void updateLPSLabel(int time, int score){
            time = time/1000;
            if(time == 0){
                time = 1;
            }
            lettersPerSecondsLabel.setText(String.format("LPS: %.2f", (double) score / time));
        }
        private void updateInfoLabels() {
            Timer infoTimer = new Timer(1, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    time++;
                    updateScoreLabel(labelOperator.getScore());
                    updateTimeLabel(time/1000);
                    updateLPSLabel(time, labelOperator.getScore());
                }
            });
            infoTimer.start();
        }
    }