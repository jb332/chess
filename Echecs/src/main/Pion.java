package main;

public class Pion extends Piece {
	public Pion(Couleur couleur, Point point) throws Exception {
		super(couleur, Type.PION, point);
	}
	
	public boolean validMove(Point point) {
		return super.getCoord().mouvementDiagonale(point) || super.getCoord().mouvementDroit(point);
	}
}
