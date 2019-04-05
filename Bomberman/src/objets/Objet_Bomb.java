package objets;

import objets.Objet;

public class Objet_Bomb extends Objet {

	private int etat;
	private int range;
	
	public Objet_Bomb(ObjetType obj, int px, int py) {
		super(obj, px, py);
		this.etat = 0;
		this.range = 4;
	}
	
	public int getEtat() {
		return etat;
	}


	public void setEtat(int etat) {
		this.etat = etat;
	}
	
	public int getRange() {
		return range;
	}


	public void setRange(int range) {
		this.range = range;
	}
}
