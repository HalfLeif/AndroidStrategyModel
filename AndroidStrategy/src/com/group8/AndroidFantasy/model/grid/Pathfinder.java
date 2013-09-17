package com.group8.AndroidFantasy.model.grid;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Pathfinder {
	
	public class Node{
		private final Position from;
		private final Position myPos;
		private final int totalCost;
		
		public Node(Position from, Position myPos, int totalCost){
			this.from=from;
			this.myPos = myPos;
			this.totalCost=totalCost;
		}

		public Position getFrom() {
			return from;
		}

		public Position getMyPos() {
			return myPos;
		}

		public int getTotalCost() {
			return totalCost;
		}
	}
	
	private class NodeComparator implements Comparator<Node>{
		
		public int compare(Node arg0, Node arg1) {
			return arg0.getTotalCost() - arg1.getTotalCost();
		}
	}
	
	private final Board board;
	
	public Pathfinder(Board board){
		this.board = board;
	}
	
	/**
	 * Tracks all paths from p within walking range "move".
	 * @param startPos
	 * @param move
	 * @return Returns a map of Nodes containing the shortest path information for each Position within walking range.
	 */
	public Map<Position, Node> pathsFrom(Position startPos, int move){
		PriorityQueue<Node> queue = new PriorityQueue<Node>(10, new NodeComparator());
		Map<Position, Node> nodeMap = new HashMap<Position, Node>();
		
		queue.add(new Node(null, startPos, 0));
		
		while(!queue.isEmpty()){
			Node n = queue.remove();
			Position ip = n.getMyPos();
			
			if(nodeMap.containsKey(ip)){
				continue;
			}
			
			nodeMap.put(ip, n);
			
			for(Position newp : board.getPositionsSurrounding(ip)){
				if(nodeMap.containsKey(newp)){
					continue;
				}
				int newMoveCost = n.getTotalCost() + board.getSpace(ip).getMoveCost();
				queue.add(new Node(ip, newp, newMoveCost));
			}
		}
		
		return nodeMap;
	}
	
	

}
