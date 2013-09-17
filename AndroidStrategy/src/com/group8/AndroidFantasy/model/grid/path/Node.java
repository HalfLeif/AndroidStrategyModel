package com.group8.AndroidFantasy.model.grid.path;

import com.group8.AndroidFantasy.model.grid.Direction;
import com.group8.AndroidFantasy.model.grid.Position;

class Node {
	private final Node from;
	private final Direction fromPrevToMe;
	private final Position myPos;
	private final int totalCost;
	
	public Node(Node from, Direction fromPrevToMe, Position myPos, int totalCost){
		this.from=from;
		this.fromPrevToMe=fromPrevToMe;
		this.myPos = myPos;
		this.totalCost=totalCost;
	}

	public Node getFrom() {
		return from;
	}

	public Position getMyPos() {
		return myPos;
	}

	public int getTotalCost() {
		return totalCost;
	}

}
