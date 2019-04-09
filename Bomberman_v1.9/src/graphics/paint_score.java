package graphics;

import java.awt.FlowLayout;
import java.util.ArrayList;

import game.BombermanGame;
import game.GameObserver;

import javax.swing.JLabel;
import javax.swing.JPanel;

import agents.Agent_Bomberman;

import map.GameState;

public class paint_score implements GameObserver {

	
	BombermanGame BbmG;
	private GameState Jeu_actuel = null;
	
	public JPanel panel = null;
	public JLabel lab = null;
	public ArrayList<JLabel> listlab;
	
	public paint_score(BombermanGame BbmG){
		if (BbmG == null) { 
			BbmG = new BombermanGame();
		}
		panel = new JPanel();
		
		panel.setLayout(new FlowLayout());
		
		BbmG.addObserver((GameObserver)this);
		Jeu_actuel = BbmG.etatJeu;
		
		lab = new JLabel();
		
		listlab = new ArrayList<JLabel>();
		
		ArrayList<Agent_Bomberman> bombermans = this.Jeu_actuel.getBombermans();
		for(int i = 0; i < bombermans.size(); i++){
			this.listlab.add(new JLabel());
			panel.add(listlab.get(i));
		}
	}
	 
	public void affichage_score(Agent_Bomberman ag,int i){
		listlab.get(i).setText("Score : "+ag.getPoints());
	}

	@Override
	public void update() {
		System.out.println("scores : ");
		ArrayList<Agent_Bomberman> bombermans = Jeu_actuel.getBombermans();
		for(int i = 0; i < bombermans.size(); i++){
			Agent_Bomberman bomberman = bombermans.get(i);
			affichage_score(bomberman,i);
			
			System.out.println("		test score "+ bomberman.getPoints());
		
		}
		//panel.repaint();
		
	}
}
