package com.group8.AndroidFantasy.model.grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;

public class Board {
	
	private final HexGrid grid;
	private final Map<Position, Space> spaceMap;
	private final int rows, cols;
	
	/**
	 * 
	 * @return Number of rows used in the Coordinate System.
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * 
	 * @return Number of columns used in the Coordinate System.
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * 
	 * @param rows Number of rows used in the Coordinate System.
	 * @param cols Number of columns used in the Coordinate System.
	 */
	public Board(int rows, int cols) throws IllegalArgumentException{
		
		if(rows<2 || cols <2)
			throw new IllegalArgumentException();
		
		this.rows=rows;
		this.cols=cols;
		
		grid = new HexGrid(rows, cols);
		spaceMap = new HashMap<Position, Space>();
		
		for(Position p : grid.getAllPositions()){
			spaceMap.put(p, new Space());
		}
	}
	
	/**
	 * 
	 * @param maxHeight Maximum number of Hexes in one column. Not equal to number of "rows"!
	 * @param maxWidth Maximum number of Hexes in one row.
	 */
	public static Board create(int maxHeight, int maxWidth) throws IllegalArgumentException{
		return new Board(2*maxHeight -1, maxWidth);
	}
	

	/**
	 * 
	 * @param p Position of Space
	 * @return Space object.
	 */
	public Space getSpace(Position p){
		//TODO return clone?
		return spaceMap.get(p);
	}
	
	/**
	 * Can iterate from this Position through SOUTH and NORTHEAST to reach all Positions.
	 * 
	 * @return Position in Northwest corner.
	 */
	public Position getCornerPosition(){
		return grid.getPosition(1, 0);
	}
	
	public Position getPosition(int x, int y) throws ArrayIndexOutOfBoundsException{
		return grid.getPosition(x, y);
	}
	
	/**
	 * 
	 * @return List of Positions on the Board.
	 */
	public List<Position> getAllPositions(){
		return grid.getAllPositions();
	}
	
	/**
	 * Does this board hold such a Position? Is this coordinate valid on this very board?
	 * 
	 * @param x
	 * @param y
	 * @return boolean answer
	 */
	public boolean isPositionValid(int x, int y){
		return grid.isPositionValid(x, y);
	}
	
	/**
	 * Returns null if position not valid.
	 * @param p
	 * @param direc
	 * @return The Position in the given direction.
	 * @throws DataFormatException
	 */
	public Position getPositionTo(Position p, Direction direc) throws DataFormatException{
		try {
			int x = p.getX() + deltaX(direc);
			int y = p.getY() + deltaY(direc);
			if(grid.isPositionValid(x, y)){
				return grid.getPosition(x, y);
			}
			return null;
		} catch(ArrayIndexOutOfBoundsException e){
			//TODO Log error!
			e.printStackTrace(System.out);
			System.out.println("Error! in Board.getPositionTo(...)");
			return null;
		}
	}
	
	/**
	 * Returns a list of Positions in all Directions from p that is one "step" away.
	 * If p has no neighbour in a certain Direction it is not included in the list.
	 * 
	 * @param p
	 * @return List of neighbouring Positions. 
	 */
	public List<Position> getPositionsSurrounding(Position p){
		List<Position> list = new ArrayList<Position>();
		
		try {
			for(Direction or : Direction.values()){
				list.add( getPositionTo(p, or));
			}
		} catch (DataFormatException e){
			//TODO Log error!
			e.printStackTrace(System.out);
			System.out.println("Error! in Board.getPositionsSurrounding(...)");
			// Should really never happen here
		}
		
		return list;
	}
	
	private int deltaX(Direction direc) throws DataFormatException{
		switch(direc){
		
		case NORTHWEST:
		case NORTHEAST:
			return -1;
		
		case NORTH:
			return -2;
		case SOUTH:
			return 2;
			
		case SOUTHEAST:
		case SOUTHWEST:
			return 1;
			
		default:
			throw new DataFormatException();
		}
	}
	
	private int deltaY(Direction direc) throws DataFormatException{
		switch(direc){
		
		case NORTHWEST:
		case SOUTHWEST:
			return -1;
		
		case NORTH:
		case SOUTH:
			return 0;
			
		case NORTHEAST:
		case SOUTHEAST:
			return 1;
		
		default:
			throw new DataFormatException();
		}
	}

}
