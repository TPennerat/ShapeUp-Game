/**
 * Classe qui définit les coordonnées d'une carte à travers une Map
 *
 * @see AbstractBoard
 */
public class Coord {
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
        // TODO
        return false;
    }
}
