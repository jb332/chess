package main;

public class Reine extends Piece {
	public Reine(Couleur couleur, Point point) throws Exception {
		super(couleur, Type.REINE, point);
	}
	
	public boolean validMove(Point point) {
		return super.getCoord().mouvementDiagonale(point) || super.getCoord().mouvementDroit(point);
	}
}
