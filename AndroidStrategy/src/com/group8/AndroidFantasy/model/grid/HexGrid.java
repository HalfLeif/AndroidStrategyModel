package com.group8.AndroidFantasy.model.grid;

import java.util.ArrayList;
import java.util.List;

public class HexGrid {
	
	private final Position[][] positions;
	private final int innerRows, innerCols;
	
	private class PositionImpl implements Position{
		private final int x,y;
		
		public PositionImpl(int x, int y){
			this.x=x;
			this.y=y;
		}

		@Override
		public int getX() {
			return x;
		}

		@Override
		public int getY() {
			return y;
		}
		
		@Override
		public String toString(){
			return "["+x+", "+y+"]";
		}
		
	}
	
	public HexGrid(int rows, int cols){
		innerRows = rows/2 +1;
		innerCols = cols;
		
		positions = new PositionImpl[innerRows][innerCols];
		
		for(int x = 0; x<rows; x++){
			for(int y = 0; y<cols; y++){
				int adX = adaptRow(x);
				int adY = adaptCol(y);
				if(isPositionValid(x, y)){
					positions[adX][adY]= new PositionImpl(x,y);
				}
			}
		}
	}
	
	public List<Position> getAllPositions(){
		List<Position> list = new ArrayList<Position>();
		for(int adX = 0; adX<innerRows; adX++){
			for(int adY = 0; adY<innerCols; adY++){
				Position p = positions[adX][adY];
				if(p!=null){
					list.add(p);
				}
			}
		}
		return list;
	}
	
	public Position getPosition(int row, int col) throws ArrayIndexOutOfBoundsException{
		int adX = adaptRow(row);
		int adY = adaptCol(col);
		
		if(! isPositionValid(row, col)){
			throw new ArrayIndexOutOfBoundsException();
		}
		//TODO return clone?
		return positions[adX][adY];
	}
	
	public boolean isPositionValid(int row, int col){
		return isOuterPositionValid(row, col) && isInnerPositionValid(adaptRow(row), adaptCol(col));
	}
	
	private boolean isOuterPositionValid(int x, int y){
		return (x+y)%2==1;
	}

	private boolean isInnerPositionValid(int adX, int adY){
		if( adX<0 || adY<0 || adX>=innerRows || adY>=innerCols){
			return false;
		}
		
		// Every other column is one hex shorter for symmetry
		if(adY%2==0 && adX==innerRows-1){
			return false;
		}
		
		return true;
	}
	
	private int adaptRow(int x){
		return x/2;
	}
	
	private int adaptCol(int y){
		return y;
	}
}
