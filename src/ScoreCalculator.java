public class ScoreCalculator implements InterfaceVisitor {
	
	public int visitBoard(AbstractBoard abstractboard, Player p) {
		
		Card victorycard = getVictoryCard(); //Récupère victory card du joueur
		
		Color victorycolor = getColor(); //Récupère les attributs de la victory card du joueur
		Shape victoryshape = getShape();
		boolean victoryfilled = isFilled();

		//Parcourir plateau et voir pour toutes les cartes adjacentes si elles ont des caractéristiques communes
		for (Card c : placedCards) { 
			
			if (coord.isCoordAdjacent(Coord c) == true) {  //
				if (this.Color == victorycolor)
			}

		}


		
	}
	
}
