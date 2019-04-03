package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.Image;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import agents.Agent;

import game.Game;
import game.BombermanGame;
import game.GameObserver;

import map.Map;
import map.GameState;

//Création graphique de la carte et des agents
public class paint_bomberman extends JPanel implements GameObserver{
	protected Color wallColor=Color.GRAY;
	protected Color brokable_walls_Color=Color.lightGray;
	protected Color ground_Color= new Color(50,175,50);
	
	private BombermanGame BbmG =null;
	private GameState Jeu_actuel = null;
	private Map m = null;
	private int taille_x;
	private int taille_y;
	
	public paint_bomberman(){
		try {
			BombermanGame BbmG = new BombermanGame();
			BbmG.addObserver((GameObserver)this);
			BbmG.loadFile("./layout/exemple.lay");
			//m = new Map();
			BbmG.initializeGame();

			BbmG.launch();
			BbmG.run();
			Jeu_actuel = BbmG.etatJeu;
			m = this.Jeu_actuel.getMap();
			System.out.println(BbmG);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Jeu_actuel = new GameState(m);
		
		//Taille de la carte
	   taille_x= m.getSizeX();
	   taille_y= m.getSizeY();
		
	}
	
	public void paint(Graphics g)
	{
		//Taille de la fenêtre
		int fen_x=getSize().width;
		int fen_y=getSize().height;
		
		g.setColor(ground_Color);
		g.fillRect(0, 0,fen_x,fen_y);
	   // System.out.println(taille_x);
	
		double stepx=fen_x/(double)taille_x;
		double stepy=fen_y/(double)taille_y;
		double position_x=0;
	
		for(int x=0; x<taille_x; x++)
		{
			double position_y = 0 ;
			for(int y=0; y<taille_y; y++)
			{
				//Création des murs
				if (m.isWall(x, y)){
					g.setColor(wallColor);
					g.fillRect((int)position_x, (int)position_y, (int)(stepx+1),(int)(stepy+1));
							
				}
				
				//Création des briques destructibles
				 if (m.isBrokable_Wall(x, y)){
					//g.setColor(brokable_walls_Color);
					//g.fillRect((int)position_x, (int)position_y, (int)(stepx+1),(int)(stepy+1));
					//g.fillRoundRect((int)position_x, (int)position_y, (int)(stepx+1),(int)(stepy+1),5,5);
					try {
						Image img = ImageIO.read(new File("brique.png"));
						g.drawImage(img, (int)position_x, (int)position_y, (int)stepx, (int)stepy, this);
					} catch (IOException e) {
						e.printStackTrace();
					}
			
				}
				position_y+=stepy;				
			}
			position_x+=stepx;
		}
		
		ArrayList<Agent> ennemies = Jeu_actuel.getEnnemies();
		for(int i = 0; i < ennemies.size(); i++){
			System.out.println("draw enn");
			dessine_Ennemy(g,ennemies.get(i));	
		}
		
	}
	
	void dessine_Ennemy(Graphics g, Agent agent)
	{
		int fen_x = getSize().width;
		int fen_y = getSize().height;

		double stepx = fen_x/(double)taille_x;
		double stepy = fen_y/(double)taille_y;

		int px = agent.getX();
		int py = agent.getY();
		
		double pos_x=px*stepx;
		double pos_y=py*stepy;
		
		int direc_en = agent.getDirection();
		g.setColor(Color.red);
		if (direc_en==Map.NORTH){
			
			try {
				Image img = ImageIO.read(new File("ennemy_North.png"));
				g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (direc_en==Map.SOUTH){
			
			try {
				Image img = ImageIO.read(new File("ennemy_South.png"));
				g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (direc_en==Map.EAST){
			
			try {
				Image img = ImageIO.read(new File("ennemy_East.png"));
				g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (direc_en==Map.WEST){
			
			try {
				Image img = ImageIO.read(new File("ennemy_West.png"));
				g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	@Override
	public void update() {
		System.out.println("update test");
		this.repaint();
		
		
	}

	
}
