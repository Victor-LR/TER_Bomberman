package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

/*import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;*/

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import javax.swing.JPanel;

import agents.Agent;
import agents.Agent_Bomberman;
import objets.Objet;
import objets.Objet_Bomb;

//import game.Game;
import game.BombermanGame;
import game.GameObserver;

import map.Map;
import map.GameState;

//Création graphique de la carte et des agents
public class paint_bomberman extends JPanel implements GameObserver{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Color wallColor=Color.GRAY;
	protected Color brokable_walls_Color=Color.lightGray;
	protected Color ground_Color= new Color(50,175,50);
	
	BombermanGame BbmG;
	private GameState Jeu_actuel = null;
	private Map m = null;
	private int taille_x;
	private int taille_y;
	
	public paint_bomberman(BombermanGame BbmG){
		if (BbmG == null) { 
			BbmG = new BombermanGame();
		}

		BbmG.addObserver((GameObserver)this);
		Jeu_actuel = BbmG.etatJeu;
		m = this.Jeu_actuel.getMap();;
		
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
					try {
						Image img = ImageIO.read(new File("./image/wall.png"));
						g.drawImage(img, (int)position_x, (int)position_y, (int)stepx, (int)stepy, this);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				//Création des briques destructibles
				else if (m.isBrokable_Wall(x, y)){
					//g.setColor(brokable_walls_Color);
					//g.fillRect((int)position_x, (int)position_y, (int)(stepx+1),(int)(stepy+1));
					//g.fillRoundRect((int)position_x, (int)position_y, (int)(stepx+1),(int)(stepy+1),5,5);
					try {
						Image img = ImageIO.read(new File("./image/brique_2.png"));
						g.drawImage(img, (int)position_x, (int)position_y, (int)stepx, (int)stepy, this);
					} catch (IOException e) {
						e.printStackTrace();
					}
			
				}else {
					try {
						Image img = ImageIO.read(new File("./image/grass.png"));
						g.drawImage(img, (int)position_x, (int)position_y, (int)stepx, (int)stepy, this);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				position_y+=stepy;				
			}
			position_x+=stepx;
		}
		
		ArrayList<Objet_Bomb> bombes = Jeu_actuel.getBombs();
		ArrayList<Agent_Bomberman> bombermans = Jeu_actuel.getBombermans();
		ArrayList<Agent> ennemies = Jeu_actuel.getEnnemies();
		ArrayList<Objet> items = Jeu_actuel.getItems();
		
		if (bombes != null) {
			for(int i = 0; i < bombes.size(); i++){
				dessine_Bomb(g,bombes.get(i),bombermans.get(0));	
			}
		}
		
		
		for(int i = 0; i < ennemies.size(); i++){
			dessine_Ennemy(g,ennemies.get(i));	
		}
		
		
		for(int i = 0; i < bombermans.size(); i++){
			dessine_Bomberman(g,bombermans.get(i));
			
		}
		
		if (bombes != null) {
			for(int i = 0; i < items.size(); i++){
				dessine_Items(g,items.get(i));	
			}
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
		if (direc_en==Map.NORTH){
			
			try {
				Image img = ImageIO.read(new File("./image/ennemy_North.png"));
				g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (direc_en==Map.SOUTH){
			
			try {
				Image img = ImageIO.read(new File("./image/ennemy_South.png"));
				g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (direc_en==Map.EAST){
			
			try {
				Image img = ImageIO.read(new File("./image/ennemy_East.png"));
				g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (direc_en==Map.WEST){
			
			try {
				Image img = ImageIO.read(new File("./image/ennemy_West.png"));
				g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	void dessine_Bomberman(Graphics g, Agent_Bomberman agentBBM)
	{
		int fen_x = getSize().width;
		int fen_y = getSize().height;

		double stepx = fen_x/(double)taille_x;
		double stepy = fen_y/(double)taille_y;

		int px = agentBBM.getX();
		int py = agentBBM.getY();
		
		double pos_x=px*stepx;
		double pos_y=py*stepy;
		
		int direc_en = agentBBM.getDirection();
		if (direc_en==Map.NORTH){
			
			try {
				Image img = ImageIO.read(new File("./image/bomberman_NORTH.png"));
				g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (direc_en==Map.SOUTH){
			
			try {
				Image img = ImageIO.read(new File("./image/bomberman_SOUTH.png"));
				g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (direc_en==Map.EAST){
			
			try {
				Image img = ImageIO.read(new File("./image/bomberman_EAST.png"));
				g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (direc_en==Map.WEST){
			
			try {
				Image img = ImageIO.read(new File("./image/bomberman_WEST.png"));
				g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	void dessine_Items(Graphics g, Objet item){
		
		int fen_x = getSize().width;
		int fen_y = getSize().height;

		double stepx = fen_x/(double)taille_x;
		double stepy = fen_y/(double)taille_y;
		
		int px = item.getObjX();
		int py = item.getObjY();
		
		double pos_x=px*stepx;
		double pos_y=py*stepy;
		
		g.setColor(Color.BLACK);
		g.fillRoundRect((int)pos_x, (int)pos_y, (int)(stepx+1),(int)(stepy+1),5,5);
		
	}
	
	
	
	void dessine_Bomb(Graphics g, Objet_Bomb bomb, Agent_Bomberman agentBBM)
	{
		int fen_x = getSize().width;
		int fen_y = getSize().height;

		double stepx = fen_x/(double)taille_x;
		double stepy = fen_y/(double)taille_y;

		int px = bomb.getObjX();
		int py = bomb.getObjY();
		
		double pos_x=px*stepx;
		double pos_y=py*stepy;
		
		if (bomb.getEtat() < 3 ){
		
			try {
				Image img = ImageIO.read(new File("./image/Bomb_0.png"));
				g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (bomb.getEtat() < 6 ){
			
			try {
				Image img = ImageIO.read(new File("./image/Bomb_1_jaune.png"));
				g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (bomb.getEtat() < 9 ){
			
			try {
				Image img = ImageIO.read(new File("./image/Bomb_2_rouge.png"));
				g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if (bomb.getEtat() <= 11 ){
			
			try {
				Image img = ImageIO.read(new File("./image/Range_CENTRE.png"));
				g.drawImage(img, (int)pos_x, (int)pos_y, (int)stepx, (int)stepy, this);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
			int range_limit = 1;
			
			range_limit = Jeu_actuel.test_range(Map.NORTH, bomb,agentBBM);
			
			for (int i = 1 ; i <= py - range_limit; i++){
				if(i != (py - range_limit)){
					try {
						Image img = ImageIO.read(new File("./image/Range_NORTH.png"));
						g.drawImage(img, (int)pos_x, (int)(pos_y - (stepy*i)), (int)stepx, (int)stepy, this);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else {
					try {
						Image img = ImageIO.read(new File("./image/Range_NORTH_Fin.png"));
						g.drawImage(img, (int)pos_x, (int)(pos_y - (stepy*i)), (int)stepx, (int)stepy, this);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			range_limit = Jeu_actuel.test_range(Map.SOUTH, bomb,agentBBM);
			
			for (int i = 1 ; i <= range_limit -py  ; i++){
				if(i != (range_limit - py)){
					try {
						Image img = ImageIO.read(new File("./image/Range_SOUTH.png"));
						g.drawImage(img, (int)pos_x, (int)(pos_y + (stepy*i)), (int)stepx, (int)stepy, this);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else {
					try {
						Image img = ImageIO.read(new File("./image/Range_SOUTH_Fin.png"));
						g.drawImage(img, (int)pos_x, (int)(pos_y + (stepy*i)), (int)stepx, (int)stepy, this);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
		
			range_limit = Jeu_actuel.test_range(Map.WEST, bomb, agentBBM);
			for (int i = 1 ; i <= px - range_limit; i++){
				if(i != (px - range_limit)){
					try {
						Image img = ImageIO.read(new File("./image/Range_WEST.png"));
						g.drawImage(img, (int)(pos_x - (stepx*i)), (int)pos_y, (int)stepx, (int)stepy, this);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else {
					try {
						Image img = ImageIO.read(new File("./image/Range_WEST_Fin.png"));
						g.drawImage(img, (int)(pos_x - (stepx*i)), (int)pos_y, (int)stepx, (int)stepy, this);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			
			range_limit = Jeu_actuel.test_range(Map.EAST, bomb, agentBBM);
			for (int i = 1 ; i <= range_limit - px  ; i++){
				if(i != (range_limit - px)){
					try {
						Image img = ImageIO.read(new File("./image/Range_EAST.png"));
						g.drawImage(img, (int)(pos_x + (stepx*i)), (int)pos_y, (int)stepx, (int)stepy, this);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else {
					try {
						Image img = ImageIO.read(new File("./image/Range_EAST_Fin.png"));
						g.drawImage(img, (int)(pos_x + (stepx*i)), (int)pos_y, (int)stepx, (int)stepy, this);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	
	@Override
	public void update() {
		this.repaint();
		
		
	}

	
}
