public class CircleBoard extends AbstractBoard {
    @Override
    protected boolean isAtLeastOneAdjacentCard(Coord c) {
        return false;
    }

    @Override
    public boolean isCardCorrectlyPlaced(Coord c) {
        return false;
    }

    @Override
    public void showBoard() {

    }

    @Override
    public int getPotentialMinimumX() {
        return 0;
    }

    @Override
    public int getPotentialMinimumY() {
        return 0;
    }

    @Override
    public int getPotentialMaximumX() {
        return 0;
    }

    @Override
    public int getPotentialMaximumY() {
        return 0;
    }
}
