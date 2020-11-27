public class ScoreCalculator implements InterfaceVisitor {
    @Override
    public int visitBoard(AbstractBoard board, Card victoryCard) {
        
		//Card victorycard = p.getVictoryCard(); //RÃ©cupÃ¨re victory card du joueur
		
		Color victorycolor = victoryCard.getColor(); //RÃ©cupÃ¨re les attributs de la victory card du joueur
		Shape victoryshape = victoryCard.getShape();
		boolean victoryfilled = victoryCard.isFilled();

		int minX = board.getRealMinimunX(); //RÃ©cupÃ©re les bornes du plateau
		int maxX = board.getRealMaximumX();
		int minY = board.getRealMinimumY();
		int maxY = board.getRealMaximumY();

        List<Coord> coords = board.toList();
        coords.sort(Coord::compareTo);

        // abstractboard.getPlacedCards().get(c);


		//-------------------------------------- INITIALISATION ----------------------------------------
		
		int posX = minX;
		int posY = minY;

		int posX_color = minX; //utilisÃ© pour le while de color
		int posX_shape = minX; //utilisÃ© pour le while de shape
		int posX_filled = minX; //utilisÃ© pour le while de filled
		
		int posY_color = minY; //utilisÃ© pour le while de color
		int posY_shape = minY; //utilisÃ© pour le while de shape
		int posY_filled = minY; //utilisÃ© pour le while de filled

        Coord coord = new Coord(minX, minY);
		Card card = board.getPlacedCards().get(coord);
		
		Color cardcolor = card.getColor();  //RÃ©cupÃ¨re les attributs de la card en (minX,minY)
		Shape cardshape = card.getShape();
		boolean cardfilled = card.isFilled();

		int i = 0; //compte le nombre de cartes identiques
		int cpt_int = 0; //compte le nombre de points en fonction du nombre de cartes identiques
		int cpt_tot = 0; //compte le nombre de points en fonction du nombre de cartes identiques

		
		
		//----------------------------------- SCAN DES LIGNES --------------------------------------------
		while(posY < maxY) { 
			
			while((cardcolor == victorycolor) && (posX_color < maxX)) { //tant que color commune on continue a scanner la prochaine carte en x
				posX_color++; i++;
                
                coord = new Coord(posX_color, posY);
		        card = board.getPlacedCards().get(coord);
                
				cardcolor = card.getColor(); //recupere cardcolor de la nouvelle carte
	
				if(i >= 3){ //si au moins 3 cartes avec meme couleur, augmenter cpt_int
					cpt_int = i+1;
				}
	
				if(!(cardcolor == victorycolor)) { //si la carte n'a plus la bonne couleur
					posX_color++; 
					i = 0;
					cpt_tot += cpt_int;
					cpt_int = 0;
				}
			}
			
			while((cardshape == victoryshape) && (posX_shape < maxX)) { //tant que shape commune on continue a scanner la prochaine carte en x
				posX_shape++; i++;
               
                coord = new Coord(posX_shape, posY);
		        card = board.getPlacedCards().get(coord);
                
                cardshape = card.getShape(); 
	
				if(i >= 2){ //si au moins 2 cartes avec meme shape, augmenter cpt_int
					cpt_int = i-1;
				}
	
				if(!(cardshape == victoryshape)) { //si la carte n'a plus la bonne shape
					posX_shape++; 
					i = 0;
					cpt_tot += cpt_int;
					cpt_int = 0;
				}
			}
			
			while((cardfilled== victoryfilled) && (posX_filled < maxX)) { //tant que filled commune on continue a scanner la prochaine carte en x
				posX_filled++; i++;
                
                coord = new Coord(posX_filled, posY);
		        card = board.getPlacedCards().get(coord);
                
                cardfilled = card.isFilled(); 
	
				if(i >= 3){ //si au moins 3 cartes avec meme filled, augmenter cpt_int
					cpt_int = i;
				}
				
				if(!(cardfilled == victoryfilled)) { //si la carte n'a plus le bon filled
					posX_filled++; 
					i = 0;
					cpt_tot += cpt_int;
					cpt_int = 0;
				}
			}

			posY++; //On passe Ã  la ligne suivante
		}
		
		//----------------------------------- SCAN DES COLONNES --------------------------------------------
		while(posX < maxX) { 
			
			while((cardcolor == victorycolor) && (posY_color < maxY)) { //tant que color commune on continue a scanner la prochaine carte en Y
				posY_color++; i++;
                
                coord = new Coord(posY_color, posX);
		        card = board.getPlacedCards().get(coord);

				cardcolor = card.getColor(); //recupere cardcolor de la nouvelle carte
	
				if(i >= 3){ //si au moins 3 cartes avec meme couleur, augmenter cpt_int
					cpt_int = i+1;
				}
	
				if(!(cardcolor == victorycolor)) { //si la carte n'a plus la bonne couleur
					posY_color++; 
					i = 0;
					cpt_tot += cpt_int;
					cpt_int = 0;
				}
			}
			
			while((cardshape == victoryshape) && (posY_shape < maxY)) { //tant que shape commune on continue a scanner la prochaine carte en Y
				posY_shape++; i++;
                
                coord = new Coord(posY_shape, posX);
		        card = board.getPlacedCards().get(coord);

				cardshape = card.getShape(); 
	
				if(i >= 2){ //si au moins 2 cartes avec meme shape, augmenter cpt_int
					cpt_int = i-1;
				}
	
				if(!(cardshape == victoryshape)) { //si la carte n'a plus la bonne shape
					posY_shape++; 
					i = 0;
					cpt_tot += cpt_int;
					cpt_int = 0;
				}
			}
			
			while((cardfilled== victoryfilled) && (posY_filled < maxY)) { //tant que filled commune on continue a scanner la prochaine carte en Y
				posY_filled++; i++;
                
                coord = new Coord(posY_filled, posX);
		        card = board.getPlacedCards().get(coord);

				cardfilled = card.isFilled(); 
	
				if(i >= 3){ //si au moins 3 cartes avec meme filled, augmenter cpt_int
					cpt_int = i;
				}
				
				if(!(cardfilled == victoryfilled)) { //si la carte n'a plus le bon filled
					posY_filled++; 
					i = 0;
					cpt_tot += cpt_int;
					cpt_int = 0;
				}
			}

			posX++; //On passe a la colonne suivante
		}

		return cpt_tot; //Score total du joueur
    }
}
