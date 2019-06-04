package main;

public class Roi extends Piece {
	public Roi(Couleur couleur, Point point) throws Exception {
		super(couleur, Type.ROI, point);
	}
	
	public boolean validMove(Point point) {
		return super.getCoord().mouvementDiagonale(point) || super.getCoord().mouvementDroit(point);
	}
}
