public class PlayingStrategy1 implements PlayingStrategy {
	
	@Override
	public Coord play(int minX, int minY, int maxX, int maxY) {
		
		int randomX = minX + (int)(Math.random() * ((maxX - minX) + 1));
		int randomY = minY + (int)(Math.random() * ((maxY - minY) + 1));
		
        return new Coord(randomX,randomY);
    }
	
	@Override
	public Coord move(int minX, int minY, int maxX, int maxY) {
		int randomX = minX + (int)(Math.random() * ((maxX - minX) + 1));
		int randomY = minY + (int)(Math.random() * ((maxY - minY) + 1));

		return new Coord(randomX,randomY);
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
