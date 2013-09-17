package se.chalmers.shend.core.model.grid;

public class Space {
	private final Terrain terr;
	
	public Space(Terrain terr){
		this.terr=terr;
	}
	public Space(){
		this(Terrain.GRASS);
	}
	
	public int getMoveCost(){
		return terr.getMoveCost();
	}
	
	public Terrain getTerrain(){
		return terr;
	}
}
