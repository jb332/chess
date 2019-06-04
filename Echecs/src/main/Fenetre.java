package main;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Fenetre extends JFrame {
	private Panneau p;

	public Fenetre(String titreFenetre, int largeurFenetre, int hauteurFenetre, int margePlateauFenetre, int coefficientEpaisseur, String cheminImage) throws Exception {
		super.setTitle(titreFenetre);
		super.setSize(largeurFenetre, hauteurFenetre);
		super.setLocationRelativeTo(null);
		super.setDefaultCloseOperation(Fenetre.EXIT_ON_CLOSE);
		this.p = new Panneau(margePlateauFenetre, coefficientEpaisseur, cheminImage);
		super.setContentPane(this.p);
		super.setVisible(true);
		
		Plateau plateau = new Plateau();
		p.updatePlateau(plateau);
		p.repaint();
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {
		String titreFenetre = "Jeu d'échecs";
		int largeurFenetre = 800;
		int hauteurFenetre = 800;
		int margePlateauFenetre = 2;
		int coefficientEpaisseur = 5;
		String cheminImage = "img/pieces.png";
		switch(args.length) {
			case 0:
			case 1:
				break;
			case 3:
				largeurFenetre = Integer.parseInt(args[2]);
				hauteurFenetre = Integer.parseInt(args[2]);
			case 2:
				titreFenetre = args[1];
				break;
			case 4:
				titreFenetre = args[1];
				largeurFenetre = Integer.parseInt(args[2]);
				hauteurFenetre = Integer.parseInt(args[3]);
			default:
		}
		Fenetre fenetre = new Fenetre(titreFenetre, largeurFenetre, hauteurFenetre, margePlateauFenetre, coefficientEpaisseur, cheminImage);
	}
}
