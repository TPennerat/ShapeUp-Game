public class PlayingStrategy1 implements PlayingStrategy {
	
	@Override
	public playVirtual() {
        int Min = -10;
		int Max = 10;
		
		int randomX = Min + (int)(Math.random() * ((Max - Min) + 1));
		int randomY = Min + (int)(Math.random() * ((Max - Min) + 1));
		
		while (randomX == null || randomY == null)  {
             randomX = Min + (int)(Math.random() * ((Max - Min) + 1));
             randomY = Min + (int)(Math.random() * ((Max - Min) + 1));
        }
        return new Coord(randomX,randomY);
    }
	
	@Override
	public moveVirtual() {
        
		
		
		return null;
    }
}
