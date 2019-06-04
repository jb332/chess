package main;

public class Cavalier extends Piece {
	public Cavalier(Couleur couleur, Point point) throws Exception {
		super(couleur, Type.CAVALIER, point);
	}
	
	public boolean validMove(Point point) {
		return super.getCoord().mouvementDiagonale(point) || super.getCoord().mouvementDroit(point);
	}
}
