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

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}
		
	}
	
	public HexGrid(int rows, int cols){
		innerRows = rows/2 +1;
		innerCols = cols;
		
		positions = new PositionImpl[innerRows][innerCols];
		
		for(int x = 0; x<rows; x++){
			for(int y = 0; y<cols; y++){
				int adX = adaptX(x);
				int adY = adaptY(y);
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
	
	public Position getPosition(int x, int y) throws ArrayIndexOutOfBoundsException{
		int adX = adaptX(x);
		int adY = adaptY(y);
		
		if(! isPositionValid(x, y)){
			throw new ArrayIndexOutOfBoundsException();
		}
		//TODO return clone?
		return positions[adX][adY];
	}
	
	public boolean isPositionValid(int x, int y){
		return isOuterPositionValid(x, y) && isInnerPositionValid(adaptX(x), adaptY(y));
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
	
	private int adaptX(int x){
		return x/2;
	}
	
	private int adaptY(int y){
		return y;
	}
}
