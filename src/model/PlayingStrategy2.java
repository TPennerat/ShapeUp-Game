package model;

import java.util.ArrayList;
import java.util.Random;

public class PlayingStrategy2 implements PlayingStrategy {

    @Override
    public Coord play(int minX, int minY, int maxX, int maxY) {
        return null;
    }

    @Override
    public Coord move(int minX, int minY, int maxX, int maxY) {
        return null;
    }

    @Override
    public int askChoice() {
        return 1;
    }

    @Override
    public int askMoveChoice() {
        return 2;
    }

    @Override
    public int handChoice() {
        return 1;
    }

    @Override
    public Card chooseMovingCard(AbstractBoard board) {
        ArrayList<Card> cards = new ArrayList<>(board.getPlacedCards().values());
        return cards.get(0);
    }
}
