package view;

import controller.GameController;
import model.Card;
import model.Coord;
import model.PlayingModel;

import javax.swing.*;
import javax.xml.bind.SchemaOutputResolver;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class ShapeUpGra implements Observer {
    private static final String REGEX_COORD = "-?[0-9]+,-?[0-9]+";
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
    private JLabel errorMessage;
    private JTextField playCoord;
    private JTextField moveCoord;
    private JButton finishYourTurnButton;
    private JLabel labelMove;
    private JLabel labelVictoryCard;
    private JLabel labelPlay;
    private PlayingModel pm;
    private GameController gc;
    private Card selectedCard;
    private Card selectedBoardCard;

    public static void main(String[] args) {
        PlayingModel pm = new PlayingModel();
        GameController gc = new GameController(pm);
        EventQueue.invokeLater(() -> {
            try {
                ShapeUpGra window = new ShapeUpGra(pm, gc);
                pm.addObserver(window);
                window.frame.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        ShapeUp up = new ShapeUp(pm, gc);
        pm.addObserver(up);
        up.launcher();
    }

    public ShapeUpGra(PlayingModel pm, GameController gc) {
        this.pm = pm;
        this.gc = gc;
        selectedCard = null;
        selectedBoardCard = null;
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
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(new Settings(this, gc).panel1);
    }

    private void updateVictoryCardText() {
        labelVictoryCard.setText(pm.getPlayerList().get(pm.getCurrentPlayerIndex()).getPseudo() + " victory card");
    }

    public void playingPhase() {
        playButton.addActionListener(e -> tryToPlay());
        playCoord.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && !moveCoord.isVisible()) {
                    tryToPlay();
                }
            }
        });
        moveButton.addActionListener(e -> tryToMove());
        moveCoord.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && !moveCoord.isVisible()) {
                    tryToMove();
                }
            }
        });
        finishYourTurnButton.addActionListener(e -> gc.nextPlayer());
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel1);
        frame.revalidate();
        frame.repaint();
    }

    private void tryToPlay() {
        if (selectedCard != null) {
            errorMessage.setVisible(false);
            String s = playCoord.getText();
            if (!s.matches(REGEX_COORD)) {
                showErrorMessage("Vos coordonnées sont mal écrites (ex: 0,2)");
            } else {
                String[] split = s.split(",");
                int x = Integer.parseInt(split[0]);
                int y = Integer.parseInt(split[1]);
                Coord c = new Coord(x, y);
                if (pm.getBoard().isCardCorrectlyPlaced(c)) {
                    gc.play(c, selectedCard);
                    selectedCard = null;
                } else {
                    showErrorMessage("Coordonnées incorrectes");
                }
            }
        } else {
            showErrorMessage("Veuillez sélectionner une carte à jouer");
        }
    }

    private void showErrorMessage(String message) {
        errorMessage.setText(message);
        errorMessage.setVisible(true);
    }

    private void tryToMove() {
        if (selectedBoardCard != null) {
            errorMessage.setVisible(false);
            String s = moveCoord.getText();
            if (!s.matches(REGEX_COORD)) {
                showErrorMessage("Vos coordonnées sont mal écrites (ex: 0,2)");
            } else {
                String[] split = s.split(",");
                int x = Integer.parseInt(split[0]);
                int y = Integer.parseInt(split[1]);
                Coord c = new Coord(x, y);
                if (pm.getBoard().isCardCorrectlyPlaced(c)) {
                    gc.move(c, selectedBoardCard);
                    selectedBoardCard = null;
                } else {
                    showErrorMessage("Can't move here");
                }
            }
        } else {
            errorMessage.setText("Veuillez sélectionner une carte à bouger");
            errorMessage.setVisible(true);
        }
    }

    private void refreshFrame() {
        frame.revalidate();
        frame.repaint();
    }

    @Override
    public void update(Observable o, Object arg) {
        switch (pm.getActualState()) {
            case PlayingModel.WAITING_STATE:
                if (pm.isFirstTurn()) {
                    playingPhase();
                } else {
                    showPlay();
                    showMove();
                }
                updateVictoryCardText();
                break;
            case PlayingModel.PLAYING_STATE:
                hidePlay();
                finishYourTurnButton.setEnabled(true);
                break;
            case PlayingModel.MOVING_STATE:
                hideMove();
                break;
            case PlayingModel.SETTING_STATE:
                break;
            case PlayingModel.FINISHED_STATE:
                displayFinalScreen();
                break;
            default:
                break;
        }
        displayCards();
        refreshFrame();
    }

    private void displayFinalScreen() {
        // TODO final screen
    }

    private void hidePlay() {
        playButton.setEnabled(false);
        playCoord.setVisible(false);
        playCoord.setText("");
        labelPlay.setVisible(false);
    }

    private void showPlay() {
        playButton.setEnabled(true);
        playCoord.setVisible(true);
        labelPlay.setVisible(true);
    }

    private void hideMove() {
        moveButton.setEnabled(false);
        moveCoord.setVisible(false);
        moveCoord.setText("");
        labelMove.setVisible(false);
    }

    private void showMove() {
        moveButton.setEnabled(true);
        moveCoord.setVisible(true);
        labelMove.setVisible(true);
    }

    private void displayCards() {
        displayHandCards();
        if (pm.getMODE() == PlayingModel.NORMAL_MODE) {
            displayVictoryCard();
        }
        if (!pm.getBoard().toList().isEmpty()) {
            displayBoard();
        }
    }

    private void displayBoard() {
        board.removeAll();
        board.add(pm.getBoard().renderGraphicBoard(this));
        refresh(board);
        refreshFrame();
    }


    private void displayHandCards() {
        int i = 1;
        card1.removeAll();
        card2.removeAll();
        card3.removeAll();
        for (Card c :
                pm.getPlayerList().get(pm.getCurrentPlayerIndex()).getHand()) {
            BufferedImage img = c.getImg();
            JLabel labelCard = new JLabel(new ImageIcon(img.getScaledInstance(img.getWidth() / 8,
                    img.getHeight() / 8, Image.SCALE_SMOOTH)));
            switch (i) {
                case 1:
                    labelCard.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            selectCard(card1, c);
                        }
                    });
                    card1.add(labelCard);
                    break;
                case 2:
                    labelCard.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            selectCard(card2, c);
                        }
                    });
                    card2.add(labelCard);
                    break;
                case 3:
                    labelCard.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mousePressed(MouseEvent e) {
                            selectCard(card3, c);
                        }
                    });
                    card3.add(labelCard);
                    break;
                default:
                    break;
            }
            i++;
        }
        refresh(card1);
        refresh(card2);
        refresh(card3);
    }

    private void selectCard(JPanel card, Card c) {
        unselectPanels();
        if (!c.equals(selectedCard)) {
            for (Component component :
                    card.getComponents()) {
                if (component instanceof JLabel) {
                    ((JLabel) component).setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
                }
            }
            selectedCard = c;
        } else {
            for (Component component :
                    card.getComponents()) {
                if (component instanceof JLabel) {
                    ((JLabel) component).setBorder(null);
                }
            }
            selectedCard = null;
        }
        refresh(card);
    }

    public void selectBoardCard(JLabel jl, Card c) {
        unselectBoardPanel();
        if (!c.equals(selectedBoardCard)) {
            for (Component component :
                    board.getComponents()) {
                if (component instanceof JPanel) {
                    for (Component component1 :
                            ((JPanel) component).getComponents()) {
                        if (component1 instanceof JLabel) {
                            JLabel lab = (JLabel) component1;
                            if (lab.equals(jl)) {
                                lab.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
                            }
                        }
                    }
                }
            }
            selectedBoardCard = c;
        } else {
            selectedBoardCard = null;
        }
        refresh(board);
    }

    private void unselectBoardPanel() {
        for (Component component :
                board.getComponents()) {
            if (component instanceof JPanel) {
                for (Component component1 :
                        ((JPanel) component).getComponents())
                    if (component1 instanceof JLabel)
                        ((JLabel) component1).setBorder(null);
            }
        }
    }

    private void unselectPanels() {
        ArrayList<JPanel> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        for (JPanel panel :
                cards) {
            for (Component component :
                    panel.getComponents()) {
                if (component instanceof JLabel) {
                    ((JLabel) component).setBorder(null);
                }
            }
        }
    }

    private void displayVictoryCard() {
        BufferedImage i = pm.getPlayerList().get(pm.getCurrentPlayerIndex()).getVictoryCard().getImg();
        JLabel picLabel = new JLabel(new ImageIcon(i.getScaledInstance(i.getWidth() / 6, i.getHeight() / 6, Image.SCALE_SMOOTH)));
        victoryCard.removeAll();
        victoryCard.add(picLabel);
        refresh(victoryCard);
    }

    private void refresh(JComponent jComponent) {
        jComponent.revalidate();
        jComponent.repaint();
    }
}
