package model;

import java.util.ArrayList;

public class PlayingStrategy1 implements PlayingStrategy {

	@Override
	public Coord play(int minX, int minY, int maxX, int maxY) {
		int randomX = minX + (int)(Math.random() * ((maxX - minX) + 1));
		int randomY = minY + (int)(Math.random() * ((maxY - minY) + 1));
		
        return new Coord(randomX,randomY);
    }
	
	@Override
	public Coord move(int minX, int minY, int maxX, int maxY) {
		return play(minX, minY, maxX, maxY);
    }

	@Override
	public int askChoice() {
		return 1;
	} // 1 = play 2 = move

	@Override
	public int askMoveChoice() {
		return 2;
	} // 1 = want to move after played; 2 = don't want to move after played

	@Override
	public int handChoice() {
		return 0;
	} // 0 1st card, 1 2nd card, 2 3rd card

	@Override
	public Card chooseMovingCard(AbstractBoard board) {
		ArrayList<Card> cards = new ArrayList<>(board.getPlacedCards().values());
		return cards.get(0);
	}
}
