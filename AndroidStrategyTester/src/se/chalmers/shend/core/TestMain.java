package se.chalmers.shend.core;

import se.chalmers.shend.core.model.grid.BoardTester;
import se.chalmers.shend.core.model.grid.path.PathTester;

public class TestMain {

	/**
	 * Don't forget to activate Assertions!
	 * @param args
	 */
	public static void main(String[] args) {
		
		boolean works = true;
		
		System.out.println("Test commenced...");
		if(!testBoard()){
			works = false;
			System.out.println("Error in testing Board...");
			return;
		}
		
		if(!testPath()){
			works = false;
			System.out.println("Error in testing Path...");
			return;
		}
		
		if(works){
			System.out.println("Test finished with no errors.");
		}
	}
	
	/**
	 * Assert dependent!
	 */
	private static boolean testBoard(){
		return new BoardTester().execute();
	}
	
	private static boolean testPath(){
		return new PathTester().execute();
	}

}
