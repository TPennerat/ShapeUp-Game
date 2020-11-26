public class ScoreCalculator implements InterfaceVisitor {
	
	public int visitBoard(AbstractBoard abstractboard, Player p) {
		
		Card victorycard = p.getVictoryCard(); //Récupère victory card du joueur
		
		Color victorycolor = victorycard.getColor(); //Récupère les attributs de la victory card du joueur
		Shape victoryshape = victorycard.getShape();
		boolean victoryfilled = victorycard.isFilled();

		//Parcourir plateau et voir pour toutes les cartes adjacentes si elles ont des caractéristiques communes
		/*for (Card card : placedCards) {
			
			Color cardcolor = card.getColor(); 
			Shape cardshape = card.getShape();
			boolean cardfilled = card.isFilled();

			if (coord.isCoordAdjacent(Coord c) == true) {  //
				if (cardcolor == victorycolor) {

				}
				if (cardshape == victoryshape) {
					
				}
				if (cardfilled == victoryfilled) {
					
				}
			}

		}*/


		return 0;
	}
	
}
