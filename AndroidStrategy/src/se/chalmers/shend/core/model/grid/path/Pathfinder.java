package se.chalmers.shend.core.model.grid.path;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.zip.DataFormatException;

import se.chalmers.shend.core.model.grid.Board;
import se.chalmers.shend.core.model.grid.Direction;
import se.chalmers.shend.core.model.grid.Position;

public class Pathfinder {
	
	private class NodeComparator implements Comparator<Node>{
		
		public int compare(Node arg0, Node arg1) {
			return arg0.getTotalCost() - arg1.getTotalCost();
		}
	}
	
	private final Board board;
	private Map<Position, Node> nodeMap;
	private Position startPos;
	
	public Pathfinder(Board board){
		this.board = board;
	}
	
	/**
	 * Tracks all paths from p within walking range "move".
	 * @param startPos
	 * @param move
	 * @return Returns a map of Nodes containing the shortest path information for each Position within walking range.
	 */
	public void generatePathsFrom(Position startPos, int move){
		PriorityQueue<Node> queue = new PriorityQueue<Node>(10, new NodeComparator());
		this.nodeMap = new HashMap<Position, Node>();
		this.startPos = startPos;
		
		queue.add(new Node(null, Direction.NORTH , startPos, 0));
		
		while(!queue.isEmpty()){
			Node n = queue.remove();
			Position ip = n.getMyPos();
			
			if(nodeMap.containsKey(ip)){
				continue;
			}
			
			nodeMap.put(ip, n);
			
			for(Direction direc : Direction.values()){
				Position newp;
				try {
					newp = board.getPositionTo(ip, direc);
					if(newp==null){
						continue;
					}
				} catch (DataFormatException e) {
					//TODO Log exc
					System.out.println("Exception: Unsupported direction: "+direc.name());
					e.printStackTrace(System.out);
					continue;
				}
				
				if(nodeMap.containsKey(newp)){
					continue;
				}
				int newMoveCost = n.getTotalCost() + board.getSpace(ip).getMoveCost();
				queue.add(new Node(n, direc, newp, newMoveCost));
			}
		}
		

		if( !nodeMap.containsKey(startPos) || nodeMap.get(startPos).getTotalCost()!=0){
			throw new IllegalStateException();
		}
	}
	
	/**
	 * Requires path to be generated first with "generatePathsFrom(...)"
	 * @param target
	 * @return
	 * @throws IllegalArgumentException if target Position is not in walking range.
	 * @throws NullPointerException if paths are not generated with "generatePathsFrom(...)" 
	 */
	public Path getPathTo(Position target)
			throws IllegalArgumentException, NullPointerException{
		
		if(nodeMap==null){
			throw new NullPointerException();
		}
		
		if( !nodeMap.containsKey(target)){
			throw new IllegalArgumentException();
		}
		
		Node targetNode = nodeMap.get(target);
		
		return new Path(targetNode);
	}
	
	/**
	 * Requires paths to be generated first with "generatePathsFrom(...)"
	 * 
	 * @return positions within walking range, as specified in "generatePathsFrom(...)".
	 * @throws NullPointerException if Paths are not generated
	 */
	public Collection<Position> getPositionsInRange() throws NullPointerException{
		if(nodeMap==null){
			throw new NullPointerException();
		}
		return nodeMap.keySet();
	}
	
	

}
