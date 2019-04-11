package agents;

import java.util.ArrayList;

import objets.Objet_Bomb;

import map.GameState;
import agents.Agent;

public class Agent_Bomberman extends Agent {
	
	private int range;
	private int points = 0;
	private ArrayList<Objet_Bomb> bombes = null;
	private int nbBombes;
	private ColorBomberman couleur;

	public Agent_Bomberman (int px, int py,int id) {
		super(AgentType.BOMBERMAN, px, py);
		super.setId(id);
		this.range = 1;
		this.points = 0;
		this.bombes = new ArrayList<Objet_Bomb>();
		this.nbBombes = 5;
	}
	
	public AgentAction chooseAction(GameState etatjeu) 
	{
		ArrayList<AgentAction> listAction=new ArrayList<AgentAction>();
		for(int i=0;i<=5;i++)
		{
			if (etatjeu.isLegalMove(new AgentAction(i), this))
				listAction.add(new AgentAction(i));
		}		
		return(listAction.get((int)(Math.random()*listAction.size())));
	}	
	
	public int getRange() {
		return range;
	}


	public void setRange(int range) {
		this.range = range;
	}
	
	public int getPoints() {
		return points;
	}

	public void setPoints(int point) {
		this.points = point;
	}
	
	public ArrayList<Objet_Bomb> getBombes() {
		return bombes;
	}

	public void setBombes(ArrayList<Objet_Bomb> bombes) {
		this.bombes = bombes;
	}
	
	public int getNbBombes() {
		return nbBombes;
	}

	public void setNbBombes(int nbBombes) {
		this.nbBombes = nbBombes;
	}
	
	public ColorBomberman getCouleur() {
		return couleur;
	}

	public void setCouleur(ColorBomberman couleur) {
		this.couleur = couleur;
	}
}
