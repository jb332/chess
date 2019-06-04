package main;

import java.util.ArrayList;
import main.Piece.Couleur;
import main.Piece.Type;

public class Plateau {
	ArrayList<Piece> pieces = new ArrayList<Piece>();
	public static final int cote = 8;
	
	public Plateau() throws Exception {
		this.pieces.add(new Tour(Couleur.BLANC, new Point(7, 0)));
		this.pieces.add(new Cavalier(Couleur.BLANC, new Point(7, 1)));
		this.pieces.add(new Fou(Couleur.BLANC, new Point(7, 2)));
		this.pieces.add(new Reine(Couleur.BLANC, new Point(7, 3)));
		this.pieces.add(new Roi(Couleur.BLANC, new Point(7, 4)));
		this.pieces.add(new Fou(Couleur.BLANC, new Point(7, 5)));
		this.pieces.add(new Cavalier(Couleur.BLANC, new Point(7, 6)));
		this.pieces.add(new Tour(Couleur.BLANC, new Point(7, 7)));
		
		for(int i=0; i<cote; i++) {
			this.pieces.add(new Pion(Couleur.BLANC, new Point(6, i)));
		}
		
		this.pieces.add(new Tour(Couleur.NOIR, new Point(0, 0)));
		this.pieces.add(new Cavalier(Couleur.NOIR, new Point(0, 1)));
		this.pieces.add(new Fou(Couleur.NOIR, new Point(0, 2)));
		this.pieces.add(new Reine(Couleur.NOIR, new Point(0, 3)));
		this.pieces.add(new Roi(Couleur.NOIR, new Point(0, 4)));
		this.pieces.add(new Fou(Couleur.NOIR, new Point(0, 5)));
		this.pieces.add(new Cavalier(Couleur.NOIR, new Point(0, 6)));
		this.pieces.add(new Tour(Couleur.NOIR, new Point(0, 7)));
		
		for(int i=0; i<cote; i++) {
			this.pieces.add(new Pion(Couleur.NOIR, new Point(1, i)));
		}
	}
	
	public ArrayList<Piece> getPieces() {
		return this.pieces;
	}
	
	public int getIndexPiece(int id) throws Exception {
		for(int i=0; i<this.pieces.size(); i++) {
			if(this.pieces.get(i).getId()==id) {
				return i;
			}
		}
		throw new Exception("la pièce d'identifiant " + id + " n'est pas sur le plateau");
	}
	
	public void removePiece(int id) throws Exception {
		try {
			this.pieces.remove(this.getIndexPiece(id));
		} catch(Exception e) {
			e.getStackTrace();
		}
	}
	
	public void transformerEnReine(int id) throws Exception {
		int indexPiece = getIndexPiece(id);
		if(this.pieces.get(indexPiece).getType()==Type.PION) {
			this.pieces.add(new ReineTransformee(this.pieces.get(indexPiece).getCouleur(), this.pieces.get(indexPiece).getCoord(), id));
			this.pieces.remove(indexPiece);
		} else {
			throw new Exception("erreur : seuls les pions peuvent être transformés en reine");
		}
	}
}
