package voronoi;

/**
 * Coordinate encoding and decoding utility for Voronoi Game
 * 
 * @author Joseph Shapiro
 */

public class CoordinateSet {
	private int row;
	private int col;
	
	public CoordinateSet(int c, int r) {
		col = c;
		row = r;
	}
	
	public int getCoordRow() {
		return this.row;
	}
	
	public int getCoordCol() {
		return this.col;
	}
	
	/*
	 * Decodes row of a coordinate string of form A1
	 */
	public static int getCoordRow(String coordinate) {
		return Integer.parseInt(coordinate.substring(1));
	}
	
	/*
	 * Decodes column of a coordinate of form A1
	 */
	public static int getCoordCol(String coordinate) {
		return coordinate.toUpperCase().charAt(0) - '@';
	}
	
	/*
	 * Calculates Manhattan distance between two CoordinateSet objects
	 */
	public static int distance(CoordinateSet arg0, CoordinateSet arg1) {
		return Math.abs(arg0.getCoordRow() - arg1.getCoordRow()) + Math.abs(arg0.getCoordCol() - arg1.getCoordCol());
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj.getClass() == CoordinateSet.class) {
			CoordinateSet set = (CoordinateSet) obj;
			if(this.row == set.row && this.col == set.col) return true;
			else return false;
		}
		else return false;
	}
	
	@Override
	public String toString() {
		return (char)(col + '@') + "" + row;
	}
}