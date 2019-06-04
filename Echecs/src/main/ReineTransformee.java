package main;

public class ReineTransformee extends Reine {
	public ReineTransformee(Couleur couleur, Point point, int id) throws Exception {
		super(couleur, point);
		this.id = id;
	}
}
