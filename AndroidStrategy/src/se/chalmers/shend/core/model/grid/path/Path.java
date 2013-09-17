package se.chalmers.shend.core.model.grid.path;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import se.chalmers.shend.core.model.grid.Direction;
import se.chalmers.shend.core.model.grid.Position;

public class Path{
	private final List<Position> posList;
	private final List<Direction> dirList;
	private final int totalCost;
	
	Path(Node targetNode){
		
		totalCost = targetNode.getTotalCost();
		
		posList = new ArrayList<Position>();
		dirList = new ArrayList<Direction>();
		
		Node iter = targetNode;
		while(iter != null){
			posList.add(iter.getMyPos());
			
			if(iter.getFrom()!=null){
				dirList.add(iter.getFromPrevToMe());
			}
			
			iter = iter.getFrom();
		}

		Collections.reverse(dirList);
		Collections.reverse(posList);
	}
	
	public List<Position> getPosList() {
		return posList;
	}

	public List<Direction> getDirList() {
		return dirList;
	}

	public int getTotalCost() {
		return totalCost;
	}
}
