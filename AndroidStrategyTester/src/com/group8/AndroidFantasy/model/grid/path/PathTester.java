package com.group8.AndroidFantasy.model.grid.path;

import java.util.Collection;
import java.util.List;
import java.util.Random;

import com.group8.AndroidFantasy.model.grid.Board;
import com.group8.AndroidFantasy.model.grid.Direction;
import com.group8.AndroidFantasy.model.grid.Position;

public class PathTester {
	
	private Board board;
	private Pathfinder pathfinder;
	private Random rand;
	
	public PathTester(){
		board = Board.create(8, 13);
		pathfinder = new Pathfinder(board);
		rand = new Random();
	}
	
	public boolean execute(){
		
		if(!testCornerAll()){
			return false;
		}
		
		if(!testRandomPath()){
			return false;
		}
		
		return true;
	}
	
	private boolean testCornerAll(){
		
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
	
	private boolean testRandomPath(){
		
		List<Position> allPos = board.getAllPositions();
		Position randStart = allPos.get(rand.nextInt(allPos.size()));
		
		Position randTarget=null;
		{
			Collection<Position> inRange = pathfinder.getPositionsInRange();
			int stopAt = rand.nextInt(inRange.size());
			int counter = 0;
			for(Position p : inRange){
				if(counter==stopAt){
					randTarget = p;
				}
				counter++;
			}
		}
		
		pathfinder.generatePathsFrom(randStart, 4);
		Path path = pathfinder.getPathTo(randTarget);
		
		List<Direction> dirs = path.getDirList();
		List<Position> poss = path.getPosList();
		
		System.out.println();
		System.out.println("Start: "+randStart.toString());
		System.out.println("Target: "+randTarget.toString());
		
		System.out.println("Dirlist: "+dirs.size());
		System.out.println("Poslist: "+poss.size());
		
		System.out.println();
		for(int i=0; i<poss.size();i++){
			System.out.print(poss.get(i).toString());
			if(i < dirs.size()){
				System.out.print("\t\t"+ dirs.get(i).name());
			}
			System.out.println();
		}
		System.out.println("");
		
		return true;
	}

}
