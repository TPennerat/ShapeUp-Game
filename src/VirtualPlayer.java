public class VirtualPlayer extends Player {
    private PlayingStrategy playingStrategy;

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
}
