import java.util.List;

public class ScoreCalculator implements InterfaceVisitor {
    @Override
    public int visitBoard(AbstractBoard board, Card victoryCard) {
        
		Color victorycolor = victoryCard.getColor(); //Recupere les attributs de la victory card du joueur
		Shape victoryshape = victoryCard.getShape();
		boolean victoryfilled = victoryCard.isFilled();

		int minX = board.getRealMinimunX(); //Recupere les bornes du plateau
		int maxX = board.getRealMaximumX();
		int minY = board.getRealMinimumY();
		int maxY = board.getRealMaximumY();

        List<Coord> coords = board.toList(); //cree une liste de coord
        coords.sort(Coord::compareTo); //ordonne les coord sur le plateau (tri)

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
		Card card = board.getPlacedCards().get(coord); //va chercher la card du board aux coord (minX, minY) soit (0,0)
		
		Color cardcolor = card.getColor();  //Recupere les attributs de la card en (minX,minY)
		Shape cardshape = card.getShape();
		boolean cardfilled = card.isFilled();

		int i = 0; //compte le nombre de cartes identiques
		int cpt_int = 0; //compte le nombre de points pour chaque suite de cartes identiques sur plateau
		int cpt_tot = 0; //compte le nombre de points total

		//----------------------------------- SCAN DES LIGNES --------------------------------------------
		while(posY < maxY) { 
			
			while(posX_color < maxX) {  //scan des attributs color pour chaque ligne
				
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
		        
		        cardcolor = card.getColor(); //recupere cardcolor de la nouvelle carte
				
		        cpt_tot += cpt_int;
		        cpt_int = 0;
			}
			
			while(posX_shape < maxX) {  //scan des attributs shape pour chaque ligne
				
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
		        
		        cardshape = card.getShape(); //recupere cardshape de la nouvelle carte
				
		        cpt_tot += cpt_int;
		        cpt_int = 0;
			}
			
			while(posX_filled < maxX) {  //scan des attributs filled pour chaque ligne
				
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
		        
		        cardfilled = card.isFilled(); //recupere cardfilled de la nouvelle carte
				
		        cpt_tot += cpt_int;
		        cpt_int = 0;
			}

			i = 0;
			posY++; //On passe a la ligne suivante
		}
		
		//----------------------------------- SCAN DES COLONNES --------------------------------------------
		while(posX < maxX) { 
			
			while(posY_color < maxY) {  //scan des attributs color pour chaque colonne
				
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
				
				coord = new Coord(posY_color, posX);
		        card = board.getPlacedCards().get(coord);
		        
		        cardcolor = card.getColor(); //recupere cardcolor de la nouvelle carte
				
		        cpt_tot += cpt_int;
		        cpt_int = 0;
			}
			
			while(posY_shape < maxY) {  //scan des attributs shape pour chaque colonne
				
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
				
				coord = new Coord(posY_shape, posX);
		        card = board.getPlacedCards().get(coord);
		        
		        cardshape = card.getShape(); //recupere cardshape de la nouvelle carte
				
		        cpt_tot += cpt_int;
		        cpt_int = 0;
			}
			
			while(posY_filled < maxY) {  //scan des attributs filled pour chaque colonne
				
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
				
				coord = new Coord(posY_filled, posX);
		        card = board.getPlacedCards().get(coord);
		        
		        cardfilled = card.isFilled(); //recupere cardfilled de la nouvelle carte
				
		        cpt_tot += cpt_int;
		        cpt_int = 0;
			}

			posX++; //On passe a la colonne suivante
		}

		i = 0;
		return cpt_tot; //Score total du joueur
    }
}