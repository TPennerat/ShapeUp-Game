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
}
