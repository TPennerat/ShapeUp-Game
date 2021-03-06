package view;

import controller.GameController;
import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Settings {
    public JPanel panel1;
    private JButton validerButton;
    private JComboBox<String> nbPlayer;
    private JComboBox<String> nbRealPlayer;
    private JRadioButton modeNormalRadioButton;
    private JRadioButton modeAdvancedRadioButton;
    private JLabel errorLabelMessage;
    private JTextField playerName1;
    private JTextField playerName2;
    private JTextField playerName3;
    private JLabel labelJ1;
    private JLabel labelJ2;
    private JLabel labelJ3;
    private JComboBox<String> comboBox1;
    private JComboBox<String> boardType;

    public Settings(ShapeUpGra sug, GameController gc) {
        validerButton.addActionListener(e -> {
            errorLabelMessage.setVisible(false);
            gc.startGame(createPlayerList(), createBoard(), determinePlayingMode());
        });
        nbRealPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelJ1.setVisible(false);
                playerName1.setVisible(false);
                labelJ2.setVisible(false);
                playerName2.setVisible(false);
                labelJ3.setVisible(false);
                playerName3.setVisible(false);
                if (nbRealPlayer.getSelectedItem() == "2") {
                    labelJ1.setVisible(true);
                    playerName1.setVisible(true);
                    labelJ2.setVisible(true);
                    playerName2.setVisible(true);
                } else if (nbRealPlayer.getSelectedItem() == "3") {
                    labelJ1.setVisible(true);
                    playerName1.setVisible(true);
                    labelJ2.setVisible(true);
                    playerName2.setVisible(true);
                    labelJ3.setVisible(true);
                    playerName3.setVisible(true);
                } else if (nbRealPlayer.getSelectedItem() == "1") {
                    labelJ1.setVisible(true);
                    playerName1.setVisible(true);
                }
            }
        });
        nbPlayer.addActionListener(e -> {
            if (nbPlayer.getSelectedItem() == "3") {
                nbRealPlayer.addItem("3");
            } else {
                nbRealPlayer.removeItem("3");
            }
        });
    }

    private int determinePlayingMode() {
        int mode = PlayingModel.NORMAL_MODE;
        if (modeAdvancedRadioButton.isSelected()) {
            mode = PlayingModel.ADVANCED_MODE;
        }
        return mode;
    }

    private List<Player> createPlayerList() {
        List<Player> players = new LinkedList<>();
        String playerName;
        if (nbRealPlayer.getSelectedItem() != "0") {
            for (int i = 0; i < nbRealPlayer.getSelectedIndex(); i++) {
                switch (i) {
                    case 0:
                        playerName = playerName1.getText();
                        break;
                    case 1:
                        playerName = playerName2.getText();
                        break;
                    case 2:
                        playerName = playerName3.getText();
                        break;
                    default:
                        playerName = "Impossible";
                        System.err.println("Impossible");
                        break;
                }
                players.add(new RealPlayer(playerName));
            }
        }
        int nbVirtualPlayer = Integer.parseInt((String) Objects.requireNonNull(nbPlayer.getSelectedItem())) - Integer.parseInt((String) Objects.requireNonNull(nbRealPlayer.getSelectedItem()));
        for (int i = 0; i < nbVirtualPlayer; i++) {
                playerName = "Robot " + (i + 1);
                PlayingStrategy ps;
                ps = new PlayingStrategy1();
                // nb difficulty
                players.add(new VirtualPlayer(playerName, ps));
            }
        return players;
}

    private AbstractBoard createBoard() {
        AbstractBoard ab;
        switch (boardType.getSelectedIndex()) {
            case 1:
                ab = new TriangleBoard();
                break;
            case 2:
                ab = new CircleBoard();
                break;
            case 0:
            default:
                ab = new RectangleBoard();
                break;
        }
        return ab;
    }


}
