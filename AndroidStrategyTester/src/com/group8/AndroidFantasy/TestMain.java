package com.group8.AndroidFantasy;

import com.group8.AndroidFantasy.model.grid.BoardTester;

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

}
