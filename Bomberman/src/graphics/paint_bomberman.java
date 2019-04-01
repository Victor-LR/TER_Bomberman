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
	protected Color brokable_walls_Color=Color.BLUE;
	
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
				if (m.isWall(x, y))
				{
					g.setColor(wallColor);
					g.fillRect((int)position_x, (int)position_y, (int)(stepx+1),(int)(stepy+1));
	
					g.setColor(wallColor);
					double nsx=stepx*0.5;
					double nsy=stepy*0.5;
					double npx=(stepx-nsx)/2.0;
					double npy=(stepy-nsy)/2.0;
					g.fillRect((int)(npx+position_x),(int)(npy+position_y),(int)(nsx),(int)nsy);						
				}
				position_y+=stepy;				
			}
			position_x+=stepx;
		}
	}
}
