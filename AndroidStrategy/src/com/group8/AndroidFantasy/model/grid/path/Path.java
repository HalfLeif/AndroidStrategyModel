package com.group8.AndroidFantasy.model.grid.path;

import java.util.List;
import java.util.Stack;

import com.group8.AndroidFantasy.model.grid.Direction;
import com.group8.AndroidFantasy.model.grid.Position;

public class Path{
	private final List<Position> posList;
	private final List<Direction> dirList;
	private final int totalCost;
	
	Path(Node targetNode){
		
		totalCost = targetNode.getTotalCost();
		
		Stack<Position> posStack = new Stack<Position>();
		Stack<Direction> dirStack = new Stack<Direction>();
		
		Node iter = targetNode;
		while(iter != null){
			posStack.push(iter.getMyPos());
			
			
			iter = iter.getFrom();
		}
		
		posList = posStack;
		dirList = dirStack;
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
