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

//import Agent.Agent;

import map.Map;
//import maze.Maze;

//Création graphique de la carte et des agents
public class paint_bomberman extends JPanel{
	protected Color wallColor=Color.GRAY;
	protected Color brokable_walls_Color=Color.lightGray;
	protected Color ground_Color= new Color(50,175,50);
	
	private Map m = null;
	
	public paint_bomberman(){
		try {
			m = new Map("./layout/exemple.lay");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g)
	{

		
		//Taille de la fenêtre
		int fen_x=getSize().width;
		int fen_y=getSize().height;
		
		
		g.setColor(ground_Color);
		g.fillRect(0, 0,fen_x,fen_y);
		
		//Taille de la carte
	    int taille_x= m.getSizeX();
	    int taille_y= m.getSizeY();
	   // System.out.println(taille_x);
	
		double stepx=fen_x/(double)taille_x;
		double stepy=fen_y/(double)taille_y;
		double position_x=0;
	
		for(int x=0; x<taille_x; x++)
		{
			double position_y = 0 ;
			for(int y=0; y<taille_y; y++)
			{
				if (m.isWall(x, y)){
					g.setColor(wallColor);
					g.fillRect((int)position_x, (int)position_y, (int)(stepx+1),(int)(stepy+1));
							
				}
				
				else if (m.isBrokable_Wall(x, y)){
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
	}
	
	/*void dessine_Bomberman(Graphics g, Agent agent)
	{
		int dx = getSize().width;
		int dy = getSize().height;

		Maze m = stateMaze.getMaze();
		int sx = m.getSizeX();
		int sy = m.getSizeY();
		double stepx = dx/(double)sx;
		double stepy = dy/(double)sy;

		int px = agent.getX();
		int py = agent.getY();
		double posx=px*stepx;
		double posy=py*stepy;
		
	}*/


}
