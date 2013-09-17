package com.group8.AndroidFantasy.model.grid;

public class Space {
	private final Terrain terr;
	
	public Space(Terrain terr){
		this.terr=terr;
	}
	public Space(){
		this(Terrain.GRASS);
	}
}
