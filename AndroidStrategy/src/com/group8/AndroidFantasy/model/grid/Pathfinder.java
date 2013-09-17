package com.group8.AndroidFantasy.model.grid;

import java.util.Comparator;

public class Pathfinder {
	
	private class Node{
		
	}
	
	private class NodeComparator implements Comparator<Node>{

		public int compare(Node arg0, Node arg1) {
			// TODO compare
			return 0;
		}
		
	}
	
	private final Board board;
	
	public Pathfinder(Board board){
		this.board = board;
	}
	
	public void pathsFrom(Position p){
		//TODO
	}

}
