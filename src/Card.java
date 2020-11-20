public class Card {
    private Color color;
    private Shape shape;
    private boolean filled;

    public Card(Color c, Shape s, boolean filled) {
        color = c;
        shape = s;
        this.filled = filled;
    }

    @Override
    public String toString() {
        return "Card{" +
                "color=" + color +
                ", shape=" + shape +
                ", filled=" + filled +
                '}';
    }
}
