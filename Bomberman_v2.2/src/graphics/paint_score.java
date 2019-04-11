package graphics;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import game.BombermanGame;
import game.GameObserver;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;



import agents.Agent_Bomberman;


public class paint_score implements GameObserver {

	
	BombermanGame BbmG;
	//private GameState Jeu_actuel = null;
	
	public JPanel panel = null;
	public ArrayList<JLabel> listlab;
	private JButton stop =null;
	private JButton run =null;
	private JButton restart =null;
	private JLabel turn = null;
	
	public paint_score(BombermanGame bomberman){
		
		this.BbmG = bomberman;
		
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		
		this.BbmG.addObserver((GameObserver)this);
		//Jeu_actuel = bomberman.etatJeu;
		
		
		listlab = new ArrayList<JLabel>();
		
		turn = new JLabel();
		this.panel.add(this.turn);
		
		Icon icon_pause = new ImageIcon("./image/icon_pause.png");
		stop = new JButton(icon_pause);
		this.panel.add(this.stop);
		
		Icon icon_run = new ImageIcon("./image/icon_run.png");
		run = new JButton(icon_run);
		this.panel.add(this.run);
		
		Icon icon_restart = new ImageIcon("./image/icon_restart.png");
		restart = new JButton(icon_restart);
		this.panel.add(this.restart);
		
		ArrayList<Agent_Bomberman> bombermans = this.BbmG.etatJeu.getBombermans();
		for(int i = 0; i < bombermans.size(); i++){
			this.listlab.add(new JLabel());
			panel.add(listlab.get(i));
		}
		
		activerStop();
		desactiverRun();
		desactiverRestart();
		
		creer_button();
	}
	
	public void creer_button(){
		restart.setFocusPainted(false);
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evenement) {
				
				desactiverRun();
				activerStop();
				desactiverRestart();
				
				try {
					BbmG.loadFile(BbmG.getMap().getFilename());
				} catch (Exception e) {
					e.printStackTrace();
				}
				BbmG.restart();
			}
		});
		
		run.setFocusPainted(false);
		run.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evenement) {
				activerStop();
				desactiverRun();
				desactiverRestart();
				
				BbmG.launch();
			}
		});
		
		stop.setFocusPainted(false);
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evenement) {
				
				desactiverStop();
				activerRun();
				activerRestart();
				
				BbmG.stop();
			}
		});
		
		
	}
	 
	public void affichage_score(Agent_Bomberman ag,int i){
		listlab.get(i).setText("N°"+(i+1)+" score : "+ag.getPoints()+"    ");
	}


	@Override
	public void update() {
		
		//System.out.println("scores : ");
		//ArrayList<Agent_Bomberman> bombermans = this.BbmG.etatJeu.getBombermans();
		
		for(int i = 0; i < this.BbmG.etatJeu.getBombermans().size(); i++){
			Agent_Bomberman bomberman = this.BbmG.etatJeu.getBombermans().get(i);
			affichage_score(bomberman,i);
			
			//System.out.println("		test score "+ bomberman.getPoints());
		}
		
		this.turn.setText("Tour : "+this.BbmG.getTurn());
		
		
			
	}
	
	public void activerStop() {
		stop.setEnabled(true);
	}

	public void desactiverStop() {
		stop.setEnabled(false);
	}

	public void activerRun() {
		run.setEnabled(true);
	}

	public void desactiverRun() {
		run.setEnabled(false);
	}

	public void activerRestart() {
		restart.setEnabled(true);
	}

	public void desactiverRestart() {
		restart.setEnabled(false);
	}	
}
