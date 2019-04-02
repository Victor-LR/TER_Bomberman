package graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import map.Map;

public class paint_bomberman extends JPanel{
	protected Color wallColor=Color.GRAY;
	protected Color brokable_walls_Color=Color.lightGray;
	protected Color ground_Color= new Color(50,175,50);
	
	public void paint(Graphics g)
	{
		Map m = null;
		try {
			m = new Map("./layout/exemple.lay");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int dx=getSize().width;
		int dy=getSize().height;
		System.out.println(dx);
		
	    int taille_x= m.getSizeX();
	    int taille_y= m.getSizeY();
	    System.out.println(taille_x);
	
	
		double stepx=dx/(double)taille_x;
		double stepy=dy/(double)taille_y;
		double position_x=0;
	
		for(int x=0; x<taille_x; x++)
		{
			double position_y = 0 ;
			for(int y=0; y<taille_y; y++)
			{
				//System.out.println(m.isWall(x, y));
				if (m.isWall(x, y)){
					
					g.setColor(wallColor);
					g.fillRect((int)position_x, (int)position_y, (int)(stepx+1),(int)(stepy+1));
							
				}
				
				else if (m.isBrokable_Wall(x, y)){
					
					g.setColor(brokable_walls_Color);
					g.fillRect((int)position_x, (int)position_y, (int)(stepx+1),(int)(stepy+1));
			
				}
				else {
					g.setColor(ground_Color);
					g.fillRect((int)position_x, (int)position_y, (int)(stepx+1),(int)(stepy+1));
					
				}
				position_y+=stepy;				
			}
			position_x+=stepx;
		}
	}

}
