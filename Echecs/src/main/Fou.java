package main;

public class Fou extends Piece {
	public Fou(Couleur couleur, Point point) throws Exception {
		super(couleur, Type.FOU, point);
	}
	
	public boolean validMove(Point point) {
		return super.getCoord().mouvementDiagonale(point) || super.getCoord().mouvementDroit(point);
	}
}
