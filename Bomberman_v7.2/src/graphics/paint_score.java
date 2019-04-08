package graphics;

import java.awt.Color;
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
		

		
		/*JLabel text = new JLabel();
		text.setText("test");
		this.add(text);*/

		//this.setVisible(true);
	
	}
	
	public void affiche_score(){
		JLabel text = new JLabel();
		text.setText("test");
		this.add(text);
	}
	
	public void paint(Graphics g){
		ArrayList<Agent_Bomberman> bombermans = Jeu_actuel.getBombermans();
		//g.setColor(Color.BLUE);
		//int x = this.getSize().width;
		//int y = this.getSize().height;
		//g.fillRect((int)0, (int)0, (int)x,(int)y);
		//g.drawString("Test!", 10, 20);
		for(int i = 0; i < bombermans.size(); i++){
			Agent_Bomberman bomberman = bombermans.get(i);
			//g.drawString("Score " + bomberman.getPoints(), (i*100)+10, 20);
			affichage_score(g,bomberman.getPoints(),i);
			System.out.println("		test score "+ bomberman.getPoints());	
			
		}
	}
	
	public void affichage_score(Graphics g, int score, int decal ){
		g.drawString("Score " + score, (decal*100)+10, 20);
	}
	@Override
	public void update() {
		this.repaint();
		
	}
}
