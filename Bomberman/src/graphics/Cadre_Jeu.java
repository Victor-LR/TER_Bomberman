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
import graphics.paint_bomberman;

public class Cadre_Jeu extends JFrame{ 
	
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    Point centerPoint = ge.getCenterPoint();
   // paint_bomberman p_bm = null;
	
	public Cadre_Jeu(){
		
		    Dimension windowSize = this.getSize();
		    this.setSize(600, 500);
		    this.setTitle("Jeu Bomberman");
		    this.setLocationRelativeTo(null);

		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             

		    this.setVisible(true);
		    
	        int dxg = centerPoint.x - windowSize.width / 2 ;
	        int dyg = centerPoint.y - windowSize.height / 2 -500; 
	        this.setLocation(dxg, dyg);
	        paint_bomberman p_bm = new paint_bomberman();
	        p_bm.paint(getGraphics());
	        
	}
	
	
	//JLabel label;
}
