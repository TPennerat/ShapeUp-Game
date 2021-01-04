package view;

import model.PlayingModel;

import javax.swing.*;
import javax.xml.bind.annotation.XmlType;
import java.awt.*;
import java.awt.image.BufferedImage;
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
    private JPanel victoryCard;
    public PlayingModel pm;

    public static void main(String[] args) {
        PlayingModel pm = new PlayingModel();
        EventQueue.invokeLater(() -> {
            try {
                ShapeUpGra window = new ShapeUpGra(pm);
                pm.addObserver(window);
                window.frame.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        ShapeUp up = new ShapeUp(pm);
        pm.addObserver(up);
        up.launcher();
    }

    public ShapeUpGra(PlayingModel pm) {
        this.pm = pm;
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
        frame.getContentPane().add(new Settings(this, pm).panel1);
    }

    public void playingPhase() {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel1);
        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void update(Observable o, Object arg) {
        switch (PlayingModel.getActualState()) {
            case PlayingModel.WAITING_STATE :
                BufferedImage i = pm.getPlayerList().get(pm.getCurrentPlayerIndex()).getVictoryCard().getImg();
                JLabel picLabel = new JLabel(new ImageIcon(i.getScaledInstance(i.getWidth()/8, i.getHeight()/8, Image.SCALE_SMOOTH)));
                victoryCard.add(picLabel);
                System.out.println(pm.getPlayerList().get(pm.getCurrentPlayerIndex()).getHand());
                i = pm.getPlayerList().get(pm.getCurrentPlayerIndex()).getHand().get(0).getImg();
                picLabel = new JLabel(new ImageIcon(i.getScaledInstance(i.getWidth()/8, i.getHeight()/8, Image.SCALE_SMOOTH)));
                card1.add(picLabel);
                if (pm.getMODE() == PlayingModel.ADVANCED_MODE) {
                    i = pm.getPlayerList().get(pm.getCurrentPlayerIndex()).getHand().get(1).getImg();
                    picLabel = new JLabel(new ImageIcon(i.getScaledInstance(i.getWidth() / 8, i.getHeight() / 8, Image.SCALE_SMOOTH)));
                    card2.add(picLabel);
                    i = pm.getPlayerList().get(pm.getCurrentPlayerIndex()).getHand().get(2).getImg();
                    picLabel = new JLabel(new ImageIcon(i.getScaledInstance(i.getWidth() / 8, i.getHeight() / 8, Image.SCALE_SMOOTH)));
                    card3.add(picLabel);
                }
                break;
            case PlayingModel.PLAYING_STATE :
                break;
            case PlayingModel.MOVING_STATE :
                break;
            case PlayingModel.SETTING_STATE :
                break;
            default:
                break;
        }
    }
}
