package com.group8.AndroidFantasy.model.grid.path;

import com.group8.AndroidFantasy.model.grid.Board;

public class PathTester {
	
	private Board board;
	private Pathfinder pathfinder;
	
	public PathTester(){
		board = Board.create(8, 13);
		pathfinder = new Pathfinder(board);
	}
	
	public boolean execute(){
		
		if(!test1()){
			return false;
		}
		
		return true;
	}
	
	private boolean test1(){
		
		pathfinder.generatePathsFrom(board.getCornerPosition(), 200);
		
		int boardSize = board.getAllPositions().size();
		
		int pathSize = pathfinder.getPositionsInRange().size();
		
		if(boardSize != pathSize){
			System.out.println("Error: in test1: board: "+boardSize+", pathSize: "+pathSize);
			assert(false);
			return false;
		}
		
		
		return true;
	}

}
