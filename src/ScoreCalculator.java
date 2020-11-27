public class ScoreCalculator implements InterfaceVisitor {
	
	public int visitBoard(AbstractBoard abstractboard, Player p) {
		
		Card victorycard = p.getVictoryCard(); //Récupère victory card du joueur
		
		Color victorycolor = victorycard.getColor(); //Récupère les attributs de la victory card du joueur
		Shape victoryshape = victorycard.getShape();
		boolean victoryfilled = victorycard.isFilled();

		int minX = abstractboard.getRealMinimunX(); //Récupére les bornes du plateau
		int maxX = abstractboard.getRealMaximumX();
		int minY = abstractboard.getRealMinimunY();
		int maxY = abstractboard.getRealMaximumY();

		//-------------------------------------- initialisation ----------------------------------------
		
		int posX = minX; 
		int posY = minY;
		
		card.setPosX(minX); //Pointe sur la 1ere carte du plateau
		card.setPosY(minY);

		card.hashCode(); //retourne la carte en (minX,minY)
		
		Color cardcolor = card.getColor();  //Récupère les attributs de la card en (minX,minY)
		Shape cardshape = card.getShape();
		boolean cardfilled = card.isFilled();

		int i = 0; //compte le nombre de cartes identiques
		int cpt_int = 0; //compte le nombre de points en fonction du nombre de cartes identiques
		int cpt_tot = 0; //compte le nombre de points en fonction du nombre de cartes identiques

		//-----------------------------------------------------------------------------------------------

		while((posX < maxX) && (posY < maxY)) {
			
			while(cardcolor == victorycolor) { //tant que color commune on continue a scanner la prochaine carte en x
				posX++; i++;
				card.setPosX(posX);
				card.hashCode(); //retourne la carte en (posX,minY)
				Color cardcolor = card.getColor(); //recupere cardcolor de la nouvelle carte
	
				if(i >= 3){ //si au moins 3 cartes avec meme couleur, augmentez cpt_int
					cpt_int = i+1;
				}
	
				if(!(cardcolor == victorycolor)) { //si la carte n'a plus la bonne couleur
					posX++; 
					i = 0;
					cpt_tot += cpt_int;
					cpt_int = 0;
				}
				
			}
			
			while(cardshape == victoryshape) { //tant que shape commune on continue a scanner la prochaine carte en x
				posX++; i++;
				card.setPosX(posX);
				card.hashCode(); //retourne la carte en (posX,minY)
				Color cardshape = card.getShape(); 
	
				if(i >= 2){ //si au moins 2 cartes avec meme shape, augmentez cpt_int
					cpt_int = i-1;
				}
	
				if(!(cardshape == victoryshape)) { //si la carte n'a plus la bonne shape
					posX++; 
					i = 0;
					cpt_tot += cpt_int;
					cpt_int = 0;
				}
			}
			
			while(cardfilled== victoryfilled) { //tant que filled commune on continue a scanner la prochaine carte en x
				posX++; i++;
				card.setPosX(posX);
				card.hashCode(); //retourne la carte en (posX,minY)
				Color cardfilled = card.isFilled(); 
	
				if(i >= 3){ //si au moins 3 cartes avec meme filled, augmentez cpt_int
					cpt_int = i;
				}
				
				if(!(cardfilled == victoryfilled)) { //si la carte n'a plus le bon filled
					posX++; 
					i = 0;
					cpt_tot += cpt_int;
					cpt_int = 0;
				}
			}
		}
		
		//for (Card card : placedCards) { }

		return cpt_tot;
	}
	
}
