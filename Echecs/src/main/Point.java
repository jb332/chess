package main;

public class Point {
	public int i, j;
	public boolean mouvementDroitUnitaire(Point point) {
		return (this.i==point.i-1 && this.j==point.j) || (this.i==point.i+1 && this.j==point.j) || (this.i==point.i && this.j==point.j-1) || (this.i==point.i && this.j==point.j+1);
	}
	public boolean mouvementDiagonaleUnitaire(Point point) {
		return (this.i==point.i-1 && this.j==point.j-1) || (this.i==point.i-1 && this.j==point.j+1) || (this.i==point.i+1 && this.j==point.j-1) || (this.i==point.i+1 && this.j==point.j+1);
	}
	public boolean mouvementDroit(Point point) {
		for(int i=0; i<5; i++);
		return false;
	}
	public boolean mouvementDiagonale(Point point) {
		for(int i=0; i<5; i++);
		return false;
	}
	public Point(int i, int j) {
		this.i = i;
		this.j = j;
	}
}
