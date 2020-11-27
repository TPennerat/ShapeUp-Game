import java.util.ArrayList;
import java.util.List;

public class ScoreCalculator implements InterfaceVisitor {
	
	public int visitBoard(AbstractBoard abstractboard, Player p) {
		
		Card victorycard = p.getVictoryCard(); //Récupère victory card du joueur
		
		Color victorycolor = victorycard.getColor(); //Récupère les attributs de la victory card du joueur
		Shape victoryshape = victorycard.getShape();
		boolean victoryfilled = victorycard.isFilled();

		int minX = abstractboard.getRealMinimunX(); //Récupére les bornes du plateau
		int maxX = abstractboard.getRealMaximumX();
		int minY = abstractboard.getRealMinimumY();
		int maxY = abstractboard.getRealMaximumY();

        List<Coord> coords = abstractboard.toList();
        coords.sort(Coord::compareTo);

        // abstractboard.getPlacedCards().get(c);

		//-------------------------------------- INITIALISATION ----------------------------------------
		
		int posX = minX;
		int posY = minY;

		int posX_color = minX; //utilisé pour le while de color
		int posX_shape = minX; //utilisé pour le while de shape
		int posX_filled = minX; //utilisé pour le while de filled
		
		int posY_color = minY; //utilisé pour le while de color
		int posY_shape = minY; //utilisé pour le while de shape
		int posY_filled = minY; //utilisé pour le while de filled

        Coord coord = new Coord(minX, minY);

		Card card = abstractboard.getPlacedCards().get(coord);
		
		Color cardcolor = card.getColor();  //Récupère les attributs de la card en (minX,minY)
		Shape cardshape = card.getShape();
		boolean cardfilled = card.isFilled();

		int i = 0; //compte le nombre de cartes identiques
		int cpt_int = 0; //compte le nombre de points en fonction du nombre de cartes identiques
		int cpt_tot = 0; //compte le nombre de points en fonction du nombre de cartes identiques

		
		
		//----------------------------------- SCAN DES LIGNES --------------------------------------------
		while(posY < maxY) { 
			
			while((cardcolor == victorycolor) && (posX_color < maxX)) { //tant que color commune on continue a scanner la prochaine carte en x
				posX_color++; i++;
				card.setPosX(posX_color);
				card.hashCode(); //retourne la carte en (posX,minY)
				Color cardcolor = card.getColor(); //recupere cardcolor de la nouvelle carte
	
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
				card.setPosX(posX_shape);
				card.hashCode(); //retourne la carte en (posX,minY)
				Color cardshape = card.getShape(); 
	
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
				card.setPosX(posX_filled);
				card.hashCode(); //retourne la carte en (posX,minY)
				Color cardfilled = card.isFilled(); 
	
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

			posY++; //On passe à la ligne suivante
		}
		
		//----------------------------------- SCAN DES COLONNES --------------------------------------------
		while(posX < maxX) { 
			
			while((cardcolor == victorycolor) && (posY_color < maxY)) { //tant que color commune on continue a scanner la prochaine carte en Y
				posY_color++; i++;
				card.setPosY(posY_color);
				card.hashCode(); //retourne la carte en (minX,posY)
				Color cardcolor = card.getColor(); //recupere cardcolor de la nouvelle carte
	
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
				card.setPosY(posY_shape);
				card.hashCode(); //retourne la carte en (posX,minY)
				Color cardshape = card.getShape(); 
	
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
				card.setPosY(posY_filled);
				card.hashCode(); //retourne la carte en (posX,minY)
				Color cardfilled = card.isFilled(); 
	
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

		//for (Card card : placedCards) { }
	}
	
}
