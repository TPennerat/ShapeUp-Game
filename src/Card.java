import com.diogonunes.jcolor.Attribute;
import static com.diogonunes.jcolor.Ansi.*;
import static com.diogonunes.jcolor.Attribute.*;

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

    public String toASCIIArt() {
        Attribute c;
        switch (color) {
            case GREEN:
                c = GREEN_TEXT();
                break;
            case BLUE:
                c = BLUE_TEXT();
                break;
            case RED:
                c = RED_TEXT();
                break;
            default:
                c = YELLOW_TEXT();
        }
        String s;
        switch (shape) {
            case CIRCLE:
                if (filled) {
                    s = "●";
                } else {
                    s = "○";
                }
                break;
            case TRIANGLE:
                if (filled) {
                    s = "▲";
                } else {
                    s = "△";
                }
                break;
            case SQUARE:
                if (filled) {
                    s = "■";
                } else {
                    s = "□";
                }
                break;
            default:
                s = null;
                break;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(colorize("[",c));
        sb.append(colorize(s,c));
        sb.append(colorize("]",c));
        return sb.toString();
    }
}
