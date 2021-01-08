package model;

public class VirtualPlayer extends Player {
    private final PlayingStrategy playingStrategy;

    public VirtualPlayer(String pseudo, PlayingStrategy p) {
        super(pseudo);
        playingStrategy = p;
    }

    @Override
    public Coord play(int minX, int minY, int maxX, int maxY) {
        return playingStrategy.play(minX, minY, maxX, maxY);
    }

    @Override
    public Coord move(int minX, int minY, int maxX, int maxY, int choice) {
        return playingStrategy.move(minX, minY, maxX, maxY);
    }

    @Override
    public int askChoice() {
        return playingStrategy.askChoice();
    }

    @Override
    public int askMoveChoice() {
        return playingStrategy.askMoveChoice();
    }

    @Override
    public int askHandChoice(String messageWhichCard) {
        return playingStrategy.handChoice();
    }

    public Card chooseMovingCard(AbstractBoard board) {
        return playingStrategy.chooseMovingCard(board);
    }

    public void autoPlay(int playingMode, AbstractBoard board, PlayingModel pm, Card card) {
        if (playingMode == PlayingModel.NORMAL_MODE) {
            if (pm.isFirstTurn()) {
                pm.setIsFirstTurn(false);
                pm.play(this, card);
            } else {
                int choice = askChoice();
                if (choice == 1) {
                    pm.play(this, card);
                    if (askMoveChoice() == 1) {
                        pm.move(this);
                    }
                } else {
                    pm.move(this);
                    pm.play(this, card);
                }
            }
        } else {
            // TODO ADVANCED MODE
        }
    }
}
