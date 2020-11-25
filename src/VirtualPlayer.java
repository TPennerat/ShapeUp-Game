public class VirtualPlayer extends Player {
    private PlayingStrategy playingStrategy;

    public VirtualPlayer(String pseudo, PlayingStrategy p) {
        super(pseudo);
        playingStrategy = p;
    }

    @Override
    public Coord play() {
       return playingStrategy.play();
    }

    @Override
    public Coord move() {
        return playingStrategy.move();
    }
}
