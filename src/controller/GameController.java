package controller;

import model.*;

import java.util.List;

public class GameController {
    private PlayingModel pm;

    public GameController(PlayingModel pm) {
        this.pm = pm;
    }

    public void play(Coord coord, Card card) {
        pm.play(coord, card);
    }

    public void startGame(List<Player> playerList, AbstractBoard board, int determinePlayingMode) {
        pm.setVariables(playerList, board, determinePlayingMode);
    }

    public void nextPlayer() {
        pm.determineNextPlayer();
    }

    public void move(Coord coord, Card card) {
        pm.move(coord, card);
    }
}
