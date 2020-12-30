package view;

import model.PlayingModel;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ShapeUpGra implements Observer {
    private JFrame frame;
    public JPanel panel1;
    private JPanel controlAction;
    private JPanel hand;
    private JButton playButton;
    private JButton moveButton;
    private JPanel card1;
    private JPanel card2;
    private JPanel card3;
    private JPanel board;

    public static void main(String[] args) {
        PlayingModel pm = new PlayingModel();
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ShapeUpGra window = new ShapeUpGra();
                    window.frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });



    }

    public ShapeUpGra() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        String imagePath = "docs/icon.png";
        frame = new JFrame();
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(imagePath));
        frame.setTitle("ShapeUp!");
        frame.setResizable(true);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Settings(this).panel1);
    }

    public void playingPhase() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel1);
        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
