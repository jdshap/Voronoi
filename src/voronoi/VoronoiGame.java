package voronoi;

import java.util.ArrayList;

/**
 * Back-end for Voronoi Game
 * 
 * @author Joseph Shapiro
 */
public class VoronoiGame {
	private int rows;
	private int cols;
	private int displayRows;
	private int displayCols;
	
	private char[][] board;
	
	private ArrayList<CoordinateSet> seedsRed = new ArrayList<> ();
	private ArrayList<CoordinateSet> seedsBlack = new ArrayList<> ();
	
	//Define Display Chits
	final static char BLACK_SEED = 'B';
	final static char RED_SEED = 'R';
	final static char BLACK_OWNED = '-';
	final static char RED_OWNED = '+';
	final static char BLANK = ' ';
	
	public VoronoiGame(int r, int c) {
		if(r>25) {
			rows=26;
			System.out.println("Max rows is 26, setting to 26.");
		} else rows = r;
		if(c>25) {
			cols=26;
			System.out.println("Max columns is 26, setting to 26.");
		} else cols = c;
		System.out.println();
		
		displayRows = rows+1;
		displayCols = cols+1;
		
		board = new char[displayRows][displayCols];
		board[0][0] = BLANK;
		for(int i = 1; i < displayCols; i++) board[0][i] = (char) ('@' + i);
		for(int i = 1; i < displayRows; i++) board[i][0] = (char) (i);
		
		calculateGameState();
	}
	
	
	/*
	 * Checks a seed's validity, then logs and places it
	 * @param target The destination coordinate
	 * @param color The owner of the seed
	 */
	public boolean setSeed(String target, char color) {

		if(target.charAt(1) >= '0' && target.charAt(1) <= '9') {
			if(CoordinateSet.getCoordRow(target) >= displayRows || CoordinateSet.getCoordRow(target) < 1 || CoordinateSet.getCoordCol(target) >= displayCols || CoordinateSet.getCoordCol(target) < 1) {
				return false;
			}
		} else return false;
		
		CoordinateSet coord = new CoordinateSet(CoordinateSet.getCoordCol(target), CoordinateSet.getCoordRow(target));
		
		if(seedsBlack.contains(coord) || seedsRed.contains(coord)) return false;
		
		if(color == BLACK_SEED) {
			seedsBlack.add(coord);
		} else seedsRed.add(coord);
		
		board[CoordinateSet.getCoordRow(target)][CoordinateSet.getCoordCol(target)] = color;
		return true;
	}
	
	/*
	 * Returns the number of cells owned by a specific color
	 * @param color The owner to be tallied
	 */
	private int getCount(char color) {
		int count = 0;
		
		for(int r = 1; r < displayRows; r++) {
			for(int c = 1; c < displayCols; c++) {
				if(board[r][c] == color) count++;
			}
		}
		return count;
	}
	
	public int getRedCount() {
		return getCount(RED_OWNED) + getCount(RED_SEED);
	}
	
	public int getBlackCount() {
		return getCount(BLACK_OWNED) + getCount(BLACK_SEED);
	}
	
	/*
	 * Sets each cell's owner
	 */
	private void calculateGameState() {
		for(int r = 1; r < displayRows; r++) {
			for(int c = 1; c < displayCols; c++) {
				if(seedsRed.size() == 0 && seedsBlack.size() == 0) {
					board[r][c] = BLANK;
					continue;
				}
				if(board[r][c] == BLACK_SEED || board[r][c] == RED_SEED) continue;
				
				CoordinateSet here = new CoordinateSet(c, r);
				int red = 100;
				int black = 100;
				
				for(CoordinateSet x : seedsRed) if(CoordinateSet.distance(here, x) < red) red = CoordinateSet.distance(here, x);
				for(CoordinateSet x : seedsBlack) if(CoordinateSet.distance(here, x) < black) black = CoordinateSet.distance(here, x);
				
				if(black == red) board[r][c] = BLANK;
				if(black < red) board[r][c] = BLACK_OWNED;
				if(black > red) board[r][c] = RED_OWNED;
			}
		}
	}
	
	/*
	 * Displays the game board when printed
	 */
	@Override
	public String toString() {
		calculateGameState();
		String output = "\n";
		for(int r = 0; r < displayRows; r++) {
			for(int c = 0; c < displayCols; c++) {
				if(c==0 && r!=0) {
					output += (int)board[r][c]<10 ? " " + (int)board[r][c] + " " : (int)board[r][c] + " ";
				}
				else if(c==0 && r==0) output += " " + board[r][c] + " ";
				else output += board[r][c] + " ";
			}
			output+="\n";
		}
		return output;
	}
}