package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import main.Piece.Type;
import main.Piece.Couleur;

@SuppressWarnings("serial")
public class Panneau extends JPanel {
	private int margePlateauFenetre;
	private int coefficientEpaisseur;
	private ArrayList<ArrayList<BufferedImage>> defaultImages;
	private ArrayList<ArrayList<BufferedImage>> images;
	private ArrayList<Piece> pieces;

	public Panneau(int margePlateauFenetre, int coefficientEpaisseur, String cheminImage) throws IOException {
		this.margePlateauFenetre = margePlateauFenetre;
		this.coefficientEpaisseur = coefficientEpaisseur;
		
		BufferedImage imageAux = ImageIO.read(new File(cheminImage));
		this.defaultImages = new ArrayList<ArrayList<BufferedImage>>();
		this.images = new ArrayList<ArrayList<BufferedImage>>();
		int nbTypes = Type.values().length;
		int nbCouleurs = Couleur.values().length;
		for(int x=0; x<nbTypes; x++) {
			this.defaultImages.add(new ArrayList<BufferedImage>());
			this.images.add(new ArrayList<BufferedImage>());
			for(int y=0; y<nbCouleurs; y++) {
				BufferedImage image = imageAux.getSubimage(x*imageAux.getWidth()/6, y*imageAux.getHeight()/2, imageAux.getWidth()/6, imageAux.getHeight()/2);
				this.defaultImages.get(x).add(image);
				this.images.get(x).add(null);
			}
		}		
	}
	
	public void updatePlateau(Plateau plateau) {
		this.pieces = plateau.getPieces();
	}
	
	public BufferedImage getImage(Piece piece) throws Exception {
		int x, y;
		switch(piece.getType()) {
			case PION:		x = 5;	break;
			case TOUR:		x = 4;	break;
			case CAVALIER:	x = 3;	break;
			case FOU:		x = 2;	break;
			case ROI:		x = 0;	break;
			case REINE:		x = 1;	break;
			default:		throw new Exception("type incorrect");
		}
		switch(piece.getCouleur()) {
			case BLANC:		y = 0;	break;
			case NOIR:		y = 1;	break;
			default:		throw new Exception("couleur incorrecte");
		}
		return this.images.get(x).get(y);
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.BLACK);
		int cotePlateau = Math.min(this.getWidth(), this.getHeight())*(100-this.margePlateauFenetre)/100;
		int epaisseurLigneGrille = 1 + this.coefficientEpaisseur*cotePlateau/1000;
		int tailleCoteCase = (cotePlateau - (Plateau.cote + 1)*epaisseurLigneGrille)/Plateau.cote;
		cotePlateau = Plateau.cote*(epaisseurLigneGrille + tailleCoteCase) + epaisseurLigneGrille;
		int originX = (this.getWidth() - cotePlateau)/2;
		int originY = (this.getHeight() - cotePlateau)/2;
		for(int i=0; i<=Plateau.cote; i++) {
			g.fillRect(originX+(epaisseurLigneGrille+tailleCoteCase)*i, originY, epaisseurLigneGrille, cotePlateau);
			g.fillRect(originX, originY+(epaisseurLigneGrille+tailleCoteCase)*i, cotePlateau, epaisseurLigneGrille);	
		}
		g.setColor(Color.GRAY);
		for(int i=0; i<Plateau.cote; i++) {
			for(int j=0; j<Plateau.cote; j++) {
				if((j+i%2)%2==1) {
					g.fillRect(originX+epaisseurLigneGrille+(epaisseurLigneGrille+tailleCoteCase)*i, originY+epaisseurLigneGrille+(epaisseurLigneGrille+tailleCoteCase)*j, tailleCoteCase, tailleCoteCase);	
				}
			}
		}
		if(this.images.get(0).get(0) == null || this.images.get(0).get(0).getWidth() != tailleCoteCase) {
			int x=0, y;
			for(ArrayList<BufferedImage> imagesAux : this.defaultImages) {
				y=0;
				for(BufferedImage imageIn : imagesAux) {
					Image toolkitImage = imageIn.getScaledInstance(tailleCoteCase, tailleCoteCase, Image.SCALE_DEFAULT);
					BufferedImage imageOut = new BufferedImage(toolkitImage.getWidth(null), toolkitImage.getHeight(null), BufferedImage.TYPE_INT_ARGB);
					Graphics gAux = imageOut.getGraphics();
					gAux.drawImage(toolkitImage, 0, 0, null);
					gAux.dispose();
					this.images.get(x).set(y, imageOut);
					y++;
				}
				x++;
			}
		}
		if(this.pieces != null) {
			for(Piece piece : this.pieces) {
				try {
					BufferedImage image = this.getImage(piece);
					g.drawImage(image, originX+epaisseurLigneGrille+(epaisseurLigneGrille+tailleCoteCase)*piece.getCoord().j, originY+epaisseurLigneGrille+(epaisseurLigneGrille+tailleCoteCase)*piece.getCoord().i, image.getWidth(), image.getHeight(), this);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
