public class PlayingStrategy1 implements PlayingStrategy {
	
	@Override
	public Coord play() {
        int Min = -10;
		int Max = 10;
		
		int randomX = Min + (int)(Math.random() * ((Max - Min) + 1));
		int randomY = Min + (int)(Math.random() * ((Max - Min) + 1));
		
        return new Coord(randomX,randomY);
    }
	
	@Override
	public Coord move() {
        
		
		
		return null;
    }
}
