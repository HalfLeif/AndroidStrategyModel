package se.chalmers.shend.core.model.grid;

public enum Terrain {
	GRASS(1),
	FOREST(2),
	HILL(2);
	
	private final int moveCost;
	
	private Terrain(int moveCost){
		this.moveCost = moveCost;
	}
	
	public int getMoveCost(){
		return moveCost;
	}

}
