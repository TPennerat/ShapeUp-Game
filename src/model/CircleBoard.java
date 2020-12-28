package model;

public class CircleBoard extends AbstractBoard {

    @Override
    public boolean isCardCorrectlyPlaced(Coord c) {
        return true;
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
