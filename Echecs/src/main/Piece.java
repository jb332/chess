package main;

public abstract class Piece {
	private static int nbPieces = 0;
	protected int id;
	private final Couleur couleur;
	private Type type;
	private Point point;
	
	public static enum Couleur {
		BLANC, NOIR;
	}
	
	public static enum Type {
		PION, TOUR, CAVALIER, FOU, ROI, REINE;
	}
	
	public Piece(Couleur couleur, Type type, Point point) throws Exception {
		this.id = nbPieces;
		Piece.nbPieces++;
		this.couleur = couleur;
		this.type = type;
		this.point = point;
	}
	
	public abstract boolean validMove(Point point);
	
	public void placer(Point point) throws Exception {
		if(this.validMove(point)) {
			this.point = point;
		} else {
			throw new Exception("déplacement impossible");
		}
	}
	
	public int getId() {
		return this.id;
	}
	
	public Couleur getCouleur() {
		return this.couleur;
	}
	
	public Type getType() {
		return this.type;
	}
	
	public Point getCoord() {
		return this.point;
	}
}
