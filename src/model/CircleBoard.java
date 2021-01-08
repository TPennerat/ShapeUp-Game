package model;

import view.ShapeUpGra;

import javax.swing.*;

public class CircleBoard extends AbstractBoard {

    @Override
    public boolean isCardCorrectlyPlaced(Coord c) {
        return true;
    }

    @Override
    public void showConsoleBoard() {

    }

    @Override
    public JPanel renderGraphicBoard(ShapeUpGra sug) {
        return null;
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

    @Override
    public boolean isCardMoveable(Coord coord, Card card) {
        return false;
    }
}
