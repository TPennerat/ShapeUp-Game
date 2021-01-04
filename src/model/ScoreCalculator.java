package model;

import java.util.List;

public class ScoreCalculator implements InterfaceVisitor {
    @Override
    public int visitBoard(AbstractBoard board, Card victoryCard) {
        
		// Card victorycard = p.getVictoryCard(); //Recupere victory card du joueur
		
		Color victorycolor = victoryCard.getColor(); //Recupere les attributs de la victory card du joueur
		Shape victoryshape = victoryCard.getShape();
		boolean victoryfilled = victoryCard.isFilled();

		int minX = board.getRealMinimunX(); //Recupere les bornes du plateau
		int maxX = board.getRealMaximumX();
		int minY = board.getRealMinimumY();
		int maxY = board.getRealMaximumY();

        List<Coord> coords = board.toList(); //cree une liste de coord
        coords.sort(Coord::compareTo); //tri des cartes en fonctions de leurs coordonnees (les ordonne)

        // abstractboard.getPlacedCards().get(c);


		//-------------------------------------- INITIALISATION ----------------------------------------
		
		int posX = minX;
		int posY = minY;

		int posX_color = minX; //utilise pour le while de color
		int posX_shape = minX; //utilie pour le while de shape
		int posX_filled = minX; //utilise pour le while de filled
		
		int posY_color = minY; //utilise pour le while de color
		int posY_shape = minY; //utilise pour le while de shape
		int posY_filled = minY; //utilise pour le while de filled

        Coord coord = new Coord(minX, minY);
		Card card = board.getPlacedCards().get(coord); //va chercher la carte du board aux coord (minX, minY)

		if (card == null)
			coord = new Coord(minX+1, minY);
		card = board.getPlacedCards().get(coord);
		Color cardcolor = card.getColor();  //Recupere les attributs de la card en (minX,minY)
		Shape cardshape = card.getShape();
		boolean cardfilled = card.isFilled();

		int i = 0; //compte le nombre de cartes identiques
		int cpt_int = 0; //compte le nombre de points en fonction du nombre de cartes identiques
		int cpt_tot = 0; //compte le nombre de points en fonction du nombre de cartes identiques

		
		
		//----------------------------------- SCAN DES LIGNES --------------------------------------------
		while(posY < maxY) { 
			
			while(posX_color < maxX) {  
				
				if(cardcolor == victorycolor) {
					i++;
					
			        if(i >= 3){ //si au moins 3 cartes avec meme couleur, augmenter cpt_int
						cpt_int = i+1;
					}	
				}
	
				if(!(cardcolor == victorycolor)) { //si la carte n'a pas la bonne couleur 
					i = 0;
					cpt_tot += cpt_int;
					cpt_int = 0;
				}
				
				posX_color++; 
				
				coord = new Coord(posX_color, posY);
		        card = board.getPlacedCards().get(coord);

		        if(card != null)
		        	cardcolor = card.getColor(); //recupere cardcolor de la nouvelle carte
				else {
					cardcolor = null;
				}

			}
			i=0;
			cpt_tot += cpt_int;
			cpt_int = 0;
			while(posX_shape < maxX) {  
				
				if(cardshape == victoryshape) {
					i++;
					
					if(i >= 2){ //si au moins 2 cartes avec meme shape, augmenter cpt_int
						cpt_int = i-1;
					}
				}
	
				if(!(cardshape == victoryshape)) { //si la carte n'a pas la bonne forme
					i = 0;
					cpt_tot += cpt_int;
					cpt_int = 0;
				}
				
				posX_shape++; 
				
				coord = new Coord(posX_shape, posY);
		        card = board.getPlacedCards().get(coord);

		        if (card!=null) {
					cardshape = card.getShape(); //recupere cardshape de la nouvelle carte
				} else {
					cardshape = null;
				}

			}
			i=0;
			cpt_tot += cpt_int;
			cpt_int = 0;
			while(posX_filled < maxX) {  
				
				if(cardfilled == victoryfilled) {
					i++;
					
					if(i >= 3){ //si au moins 3 cartes avec meme filled, augmenter cpt_int
						cpt_int = i;
					}
				}
	
				if(!(cardfilled == victoryfilled)) { //si la carte n'a pas le bon filled
					i = 0;
					cpt_tot += cpt_int;
					cpt_int = 0;
				}
				
				posX_filled++; 
				
				coord = new Coord(posX_filled, posY);
		        card = board.getPlacedCards().get(coord);

		        if (card!=null) {
					cardfilled = card.isFilled(); //recupere cardfilled de la nouvelle carte
				} else if (posX_filled < maxX){
					posX_filled++;

					coord = new Coord(posX_filled, posY);
					card = board.getPlacedCards().get(coord);
					cardfilled = card.isFilled();
				}

			}
			i=0;
			cpt_tot += cpt_int;
			cpt_int = 0;
			posY++; //On passe a la ligne suivante
		}
		
		//----------------------------------- SCAN DES COLONNES --------------------------------------------
		while(posX < maxX) { 
			
			while(posY_color < maxY) {  
				
				if(cardcolor == victorycolor) {
					i++;
					
			        if(i >= 3){ //si au moins 3 cartes avec meme couleur, augmenter cpt_int
						cpt_int = i+1;
					}	
				}
	
				if(!(cardcolor == victorycolor)) { //si la carte n'a pas la bonne couleur 
					i = 0;
					cpt_tot += cpt_int;
					cpt_int = 0;
				}
				
				posY_color++; 
				
				coord = new Coord(posX, posY_color);
		        card = board.getPlacedCards().get(coord);

		        if (card!=null)
		        cardcolor = card.getColor(); //recupere cardcolor de la nouvelle carte
				else {
					cardcolor = null;
				}
			}
			i=0;
			cpt_tot += cpt_int;
			cpt_int = 0;
			while(posY_shape < maxY) {  
				
				if(cardshape == victoryshape) {
					i++;
					
					if(i >= 2){ //si au moins 2 cartes avec meme shape, augmenter cpt_int
						cpt_int = i-1;
					}
				}
	
				if(!(cardshape == victoryshape)) { //si la carte n'a pas la bonne forme
					i = 0;
					cpt_tot += cpt_int;
					cpt_int = 0;
				}
				
				posY_shape++; 
				
				coord = new Coord(posX, posY_shape);
		        card = board.getPlacedCards().get(coord);
				if (card!=null)
		        cardshape = card.getShape(); //recupere cardshape de la nouvelle carte
				else {
					cardshape = null;
				}
			}
			i=0;
			cpt_tot += cpt_int;
			cpt_int = 0;
			while(posY_filled < maxY) {  
				
				if(cardfilled == victoryfilled) {
					i++;
					
					if(i >= 3){ //si au moins 3 cartes avec meme filled, augmenter cpt_int
						cpt_int = i;
					}
				}
	
				if(!(cardfilled == victoryfilled)) { //si la carte n'a pas le bon filled
					i = 0;
					cpt_tot += cpt_int;
					cpt_int = 0;
				}
				
				posY_filled++; 
				
				coord = new Coord(posX, posY_filled);
		        card = board.getPlacedCards().get(coord);
				if (card!=null)
		        cardfilled = card.isFilled(); //recupere cardfilled de la nouvelle carte
				else if (posY_filled < maxY){
					posY_filled++;

					coord = new Coord(posX, posY_filled);
					card = board.getPlacedCards().get(coord);

					cardfilled = card.isFilled();
				}
			}
			i=0;
			cpt_tot += cpt_int;
			cpt_int = 0;
			posX++; //On passe a la colonne suivante
		}

		return cpt_tot; //Score total du joueur
    }
}