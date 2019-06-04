package main;

public class Tour extends Piece {
	public Tour(Couleur couleur, Point point) throws Exception {
		super(couleur, Type.TOUR, point);
	}
	
	public boolean validMove(Point point) {
		return super.getCoord().mouvementDiagonale(point) || super.getCoord().mouvementDroit(point);
	}
}
