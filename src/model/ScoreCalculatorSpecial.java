package model;

public class ScoreCalculatorSpecial implements InterfaceVisitor {

    @Override
    public int visitBoard(AbstractBoard board, Card victoryCard) {
        return 0;
    }
}
