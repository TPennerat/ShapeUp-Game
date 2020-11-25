public class RealPlayer extends Player {

    public RealPlayer(String pseudo) {
        super(pseudo);
    }

    @Override
    public void play() {
		
		if (this.deck.size() > 0) { //verifier qu'il reste des cartes dans pioche
			Card card_piochee = this.deck.remove(this.deck.size() - 1) // Si oui piocher la dernière carte de la pioche

			return card_piochee;
		}
		else {
			System.err.println("Il n'y a plus de cartes dans la pioche"); //plus de cartes dans la pioche, la partie est finie
			return null;
		}
		
		//afficher caracteristiques de la carte au joueur
		String toString(); //ne suis pas sur que ce soit bien cette fonction qu'il faille utiliser...
		
		//TODO eventuellement afficher cartes deja presentes sur plateau ?
		
		//lui demander de choisir ou la placer
		Scanner scx = new Scanner(System.in); 
        String messageChoiceX = "Choisir ou placer la carte en x"; //Demander position en X
		int choiceNumberX = ShapeUp.askNumber(messageChoiceX);
		
		Scanner scy = new Scanner(System.in);
        String messageChoiceY = "Choisir ou placer la carte en y"; //Demander position en Y
		int choiceNumberY = ShapeUp.askNumber(messageChoiceY);
		
		//TODO placer carte sur plateau
		
		//TODO afficher plateau actualisé avec la nouvelle card_piochee ?
		
		//sortir
    }

    @Override
    public void move() {
		//afficher cartes deja presentes sur plateau
		//demander au joueur s'il veut deplacer une carte
		//si oui demander laquelle et si non sortir
		//demander ou deplacer la carte choisie
		//faire le deplacement
		//sortir
    }
}
