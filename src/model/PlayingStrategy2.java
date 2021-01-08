package model;

public class PlayingStrategy2 implements PlayingStrategy {


    @Override
    public Coord play(int minX, int minY, int maxX, int maxY) {
        
    	Color drawcardcolor = //.getColor(); //TODO Recuperer les attributs de la card piochee
		Shape drawcardshape = //.getShape();
		boolean drawcardfilled = //.isFilled();

		int minX = board.getRealMinimunX(); //Recupere les bornes du plateau
		int maxX = board.getRealMaximumX();
		int minY = board.getRealMinimumY();
		int maxY = board.getRealMaximumY();

        List<Coord> coords = board.toList(); //cree une liste de coord
        coords.sort(Coord::compareTo); //tri des cartes en fonctions de leurs coordonnees (les ordonne)
        
        //-------------------------------------- INITIALISATION ----------------------------------------
		int pts_strategy = 0;
		int max_pts_strategy = 0;
		
		int valX;
		int valY;
		int valX_final;
		int valY_final;        
       
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
		
		//----------------------------------- SCAN DES LIGNES --------------------------------------------
		while(posY < maxY) { 
			
			while(posX_color < maxX) {  
				
				if(cardcolor == drawcardcolor) {
					i++;
					if(i >= 2){
						//TODO placer card piochee au rang d'apres (donc a posX_color++ et tester si cest possible ( board.isCardCorrectlyPlaced(coord) ) 
						pts_strategy = i+1;
						
						if(pts_strategy > max_pts_strategy) {
							valX = posX_color+1;
							valY = posY;
							coord = new Coord(valX, valY);
							
							if(board.isCardCorrectlyPlaced(coord)) {
								valX_final = valX;
								valY_final = valY;
								//return new Coord(valX,valY); 
								max_pts_strategy = pts_strategy;
							}
						}
					} 
				}
	
				if(!(cardcolor == drawcardcolor)) { //si la carte n'a pas la bonne couleur 
					i = 0;
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
			
			while(posX_shape < maxX) {  
				
				if(cardshape == drawcardshape) {
					i++;
					if(i >= 2){
						pts_strategy = i-1;
						
						if(pts_strategy > max_pts_strategy) {
							valX = posX_shape+1;
							valY = posY;
							coord = new Coord(valX, valY);
							
							if(board.isCardCorrectlyPlaced(coord)) {
								valX_final = valX;
								valY_final = valY;
								//return new Coord(valX,valY); 
								max_pts_strategy = pts_strategy;
							}
						}
					} 
				}
	
				if(!(cardshape == drawcardshape)) { 
					i = 0;
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
			
			while(posX_filled < maxX) {  
				if(cardfilled == drawcardfilled) {
					i++;
					if(i >= 2){
						pts_strategy = i;
						
						if(pts_strategy > max_pts_strategy) {
							valX = posX_filled+1;
							valY = posY;
							coord = new Coord(valX, valY);
							
							if(board.isCardCorrectlyPlaced(coord)) {
								valX_final = valX;
								valY_final = valY;
								//return new Coord(valX,valY); 
								max_pts_strategy = pts_strategy;
							}
						}
					} 
				}
	
				if(!(cardfilled == drawcardfilled)) {
					i = 0;
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
			
			posY++; //On passe a la ligne suivante
		}			
		
		//----------------------------------- SCAN DES COLONNES --------------------------------------------
		while(posX < maxX) { 
			
			while(posY_color < maxY) {  
				
				if(cardcolor == drawcardcolor) {
					i++;
					if(i >= 2){
						//TODO placer card piochee au rang d'apres (donc a posX_color++ et tester si cest possible ( board.isCardCorrectlyPlaced(coord) ) 
						pts_strategy = i+1;
						
						if(pts_strategy > max_pts_strategy) {
							valY = posY_color+1;
							valX = posX;
							coord = new Coord(valX, valY);
							
							if(board.isCardCorrectlyPlaced(coord)) {
								valX_final = valX;
								valY_final = valY;
								//return new Coord(valX,valY); 
								max_pts_strategy = pts_strategy;
							}
						}
					} 
				}
	
				if(!(cardcolor == drawcardcolor)) { //si la carte n'a pas la bonne couleur 
					i = 0;
				}
				
				posY_color++; 
				
				coord = new Coord(posX, posY_color);
		        card = board.getPlacedCards().get(coord);

		        if(card != null)
		        	cardcolor = card.getColor(); //recupere cardcolor de la nouvelle carte
				else {
					cardcolor = null;
				} 
			}
			i=0;
			
			while(posY_shape < maxY) {  
				
				if(cardshape == drawcardshape) {
					i++;
					if(i >= 2){
						pts_strategy = i-1;
						
						if(pts_strategy > max_pts_strategy) {
							valY= posY_shape+1;
							valX = posX;
							coord = new Coord(valX, valY);
							
							if(board.isCardCorrectlyPlaced(coord)) {
								valX_final = valX;
								valY_final = valY;
								//return new Coord(valX,valY); 
								max_pts_strategy = pts_strategy;
							}
						}
					} 
				}
	
				if(!(cardshape == drawcardshape)) { 
					i = 0;
				}
				
				posX_shape++; 
				
				coord = new Coord(posX, posY_shape);
		        card = board.getPlacedCards().get(coord);

		        if (card!=null) {
					cardshape = card.getShape(); //recupere cardshape de la nouvelle carte
				} else {
					cardshape = null;
				}

			}
			i=0;
			
			
			while(posY_filled < maxY) {  
				if(cardfilled == drawcardfilled) {
					i++;
					if(i >= 2){
						pts_strategy = i;
						
						if(pts_strategy > max_pts_strategy) {
						valY = posY_filled+1;
							valX = posX;
							coord = new Coord(valX, valY);
							
							if(board.isCardCorrectlyPlaced(coord)) {
								valX_final = valX;
								valY_final = valY;
								//return new Coord(valX,valY); 
								max_pts_strategy = pts_strategy;
							}
						}
					} 
				}
	
				if(!(cardfilled == drawcardfilled)) { 
					i = 0;
				}
				
				posX_filled++; 
				
				coord = new Coord(posX, posY_filled);
		        card = board.getPlacedCards().get(coord);

		        if (card!=null) {
					cardfilled = card.isFilled(); //recupere cardfilled de la nouvelle carte
				} else if (posY_filled < maxY){
					posY_filled++;

					coord = new Coord(posX, posY_filled);
					card = board.getPlacedCards().get(coord);
					cardfilled = card.isFilled();
				}

			}
			i=0;
			posX++; //on passe a la colonne suivante
		}				
		
		return new Coord(valX_final,valY_final);
    }

    @Override
    public Coord move(int minX, int minY, int maxX, int maxY) {
        return null;
    }

    @Override
    public int askChoice() {
        return 1;
    }

    @Override
    public int askMoveChoice() {
        return 2;
    }

    @Override
    public int handChoice() {
        return 1;
    }
}
