import java.util.Objects;

/**
 * Classe qui définit les coordonnées d'une carte à travers une Map
 *
 * @see AbstractBoard
 */
public class Coord implements Comparable{
    private int posX;
    private int posY;

    /**
     * Constructeur de la classe Coord
     *
     * @param x entier sur l'axe x
     * @param y entier sur l'axe y
     */
    public Coord(int x, int y) {
        posX = x;
        posY = y;
    }

    /**
     * getter de l'attribut posX
     *
     * @return l'attribut posX
     */
    public int getPosX() {
        return posX;
    }

    /**
     * setter de l'attribut posX
     *
     * @param posX l'entier qui va remplacer posX
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * getter de l'attribut posY
     *
     * @return l'attribut posY
     */
    public int getPosY() {
        return posY;
    }

    /**
     * setter de l'attribut posY
     *
     * @param posY l'entier qui va remplacer posY
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    public boolean isCoordAdjacent(Coord c) {
        int x = c.getPosX();
        int y = c.getPosY();
        
		int soustracX =  x - this.posX;
		int soustracY =  y - this.posY;
		
		if ((soustracX == 0 && soustracY == 1) ||
			(soustracX == 1 && soustracY == 0) ||
			(soustracX == -1 && soustracY == 0) ||
			(soustracX == 0 && soustracY == -1)) {
			
			return true;
		}
		else {
			return false;
		}
		
        
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coord)) return false;
        Coord coord = (Coord) o;
        return posX == coord.posX &&
                posY == coord.posY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(posX, posY);
    }

    @Override
    public int compareTo(Object o) {
        Coord c = (Coord) o;
        int x = c.getPosX();
        int y = c.getPosY();

        int soustracX =  x - this.posX;
        int soustracY =  y - this.posY;
        if (soustracY >= 1) {
            return 1;
        } else if (soustracY <= -1) {
            return -1;
        } else {
            if (soustracX >= 1) {
                return -1;
            } else if (soustracX <= -1) {
                return 1;
            } else {
                    return 0;
                }
        }
    }
}
