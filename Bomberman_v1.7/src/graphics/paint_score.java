package graphics;

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.util.ArrayList;

import game.BombermanGame;
import game.GameObserver;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import agents.Agent_Bomberman;

import map.GameState;
import map.Map;

public class paint_score  extends JPanel implements GameObserver {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	BombermanGame BbmG;
	private GameState Jeu_actuel = null;
	private Map m = null;
	
	public paint_score(BombermanGame BbmG){
		if (BbmG == null) { 
			BbmG = new BombermanGame();
		}
		this.setLayout(new FlowLayout());
		
		BbmG.addObserver((GameObserver)this);
		Jeu_actuel = BbmG.etatJeu;
		m = this.Jeu_actuel.getMap();
		
		JLabel text = new JLabel();
		text.setText("test");
		this.add(text);
	
	}
	
	public void paint(Graphics g){
		ArrayList<Agent_Bomberman> bombermans = Jeu_actuel.getBombermans();
		for(int i = 0; i < bombermans.size(); i++){
			Agent_Bomberman bomberman = bombermans.get(i);
			JButton text = new JButton("Score : "+ bomberman.getPoints());
			this.add(text);
			System.out.println("		test score "+ bomberman.getPoints());	
			
		}
	}


	@Override
	public void update() {
		this.repaint();
		
	}
}
