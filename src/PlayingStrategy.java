public interface PlayingStrategy {
    Coord play(int minX, int minY, int maxX, int maxY);
    Coord move(int minX, int minY, int maxX, int maxY);
    int askChoice();
    int askMoveChoice();
    int handChoice();
}
