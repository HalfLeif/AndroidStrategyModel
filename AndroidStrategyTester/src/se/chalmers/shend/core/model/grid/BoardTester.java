package se.chalmers.shend.core.model.grid;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import se.chalmers.shend.core.model.grid.Board;
import se.chalmers.shend.core.model.grid.Direction;
import se.chalmers.shend.core.model.grid.Position;

public class BoardTester {
	
	private Board board;
	
	public BoardTester(){
		board = Board.create(8, 13);
	}

	public boolean execute(){
		
		if(!testValidPositions())
			return false;
		
		if(!testMoveOneStep(false))
			return false;
		
		if(!testMoveOneStep(true))
			return false;
		
		return true;
	}
	
	private boolean testValidPositions(){
		
		int rows = board.getRows();
		int cols = board.getCols();
		
		//System.out.println("Rows: "+rows);
		//System.out.println("Cols: "+cols);
		
		assert(! board.isPositionValid(3, 1));
		
		int counter = 0;
		int eCounter = 0;
		
		for(int x=0; x<rows; x++){
			for(int y=0; y<cols; y++){
				try{
					Position p = board.getPosition(x, y);
					if(p==null){
						throw new ArrayIndexOutOfBoundsException();
					}
					
					assert( board.isPositionValid(x, y));
					counter++;
				} catch (ArrayIndexOutOfBoundsException e){
					eCounter++;
					assert( !board.isPositionValid(x, y));
				}
			}
		}
		
		int listSize = board.getAllPositions().size();
		//System.out.println("List: "+listSize);
		//System.out.println("Counter: "+counter);
		//System.out.println("eCounter: "+eCounter);
		
		assert(listSize == counter);
		
		return true;
	}
	
	private boolean testMoveOneStep(boolean sparingly){
		
		Set<Position> positionSet = new HashSet<Position>();
		Queue<Position> queue = new LinkedList<Position>();
		
		queue.add(board.getCornerPosition());
		while(!queue.isEmpty()){
			Position p = queue.remove();
			if(p==null || positionSet.contains(p)){
				continue;
			}
			
			positionSet.add(p);
			if(sparingly){
				//System.out.println("Visited ["+p.getX()+", "+p.getY()+"]");
			}
			
			try {
				if(sparingly){
					queue.add(board.getPositionTo(p, Direction.SOUTH));
					queue.add(board.getPositionTo(p, Direction.NORTHEAST));
					
				} else {
					for(Position neighbour : board.getPositionsSurrounding(p)){
						queue.add(neighbour);
					}
				}				
				
			} catch(Exception e){
				e.printStackTrace(System.out);
				assert(false);
				return false;
			}
		}
		
		int setSize = positionSet.size();
		int boardSize = board.getAllPositions().size();
		
		/*System.out.println();
		System.out.println("Sparingly: "+sparingly);
		System.out.println("Set: "+setSize);
		System.out.println("Board: "+boardSize);
		*/
		
		
		if(setSize!=boardSize){
			assert(false);
			return false;
		}
		
		return true;
	}
	
}
