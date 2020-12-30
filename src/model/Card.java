package model;

import com.diogonunes.jcolor.Attribute;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

import static com.diogonunes.jcolor.Ansi.*;
import static com.diogonunes.jcolor.Attribute.*;

public class Card {
    private Color color;
    private Shape shape;
    private boolean filled;
    private BufferedImage img;

    public Card(Color c, Shape s, boolean filled, String imgLink) {
        color = c;
        shape = s;
        this.filled = filled;
        try {
            img = ImageIO.read(new File(imgLink));
        } catch (IOException ex) {
            // handle exception...
        }
    }

    @Override
    public String toString() {
        return "model.Card{" +
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
        return colorize("[", c) +
                colorize(s, c) +
                colorize("]", c);
    }

    public Color getColor() {
        return color;
    }

    public Shape getShape() {
        return shape;
    }

    public boolean isFilled() {
        return filled;
    }
}
