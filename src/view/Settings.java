package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Settings {
    public JPanel panel1;
    private JButton validerButton;
    private JComboBox<String> nbPlayer;
    private JComboBox<String> nbRealPlayer;
    private JRadioButton modeNormalRadioButton;
    private JRadioButton modeAdvancedRadioButton;
    private JLabel errorLabelMessage;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JLabel labelJ1;
    private JLabel labelJ2;
    private JLabel labelJ3;
    private JComboBox<String> comboBox1;
    private JComboBox<String> comboBox2;

    public Settings(ShapeUpGra sug) {
        validerButton.addActionListener(e -> {
            errorLabelMessage.setVisible(false);
            sug.playingPhase();
        });
        nbRealPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelJ2.setVisible(false);
                textField2.setVisible(false);
                labelJ3.setVisible(false);
                textField3.setVisible(false);
                if (nbRealPlayer.getSelectedItem() == "2") {
                    labelJ2.setVisible(true);
                    textField2.setVisible(true);
                } else if (nbRealPlayer.getSelectedItem() == "3") {
                    labelJ2.setVisible(true);
                    textField2.setVisible(true);
                    labelJ3.setVisible(true);
                    textField3.setVisible(true);
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


}
