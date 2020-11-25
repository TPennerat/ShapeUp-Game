import java.util.InputMismatchException;
import java.util.Scanner;

public class RealPlayer extends Player {

    public RealPlayer(String pseudo) {
        super(pseudo);
    }

    @Override
    public Coord play() {
        Integer x = askOneCoord("x");
        Integer y = askOneCoord("y");
        while (x == null || y == null)  {
             x = askOneCoord("x");
             y = askOneCoord("y");
        }
        return new Coord(x,y);
    }

    @Override
    public Coord move() {
        Integer x = askOneCoord("x");
        Integer y = askOneCoord("y");
        while (x == null || y == null)  {
            x = askOneCoord("x");
            y = askOneCoord("y");
        }
        return new Coord(x,y);
    }
}
