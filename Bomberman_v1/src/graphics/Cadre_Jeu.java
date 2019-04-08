package graphics;

/*import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;*/

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import javax.swing.JFrame;

import javax.swing.JPanel;

import javax.swing.JButton;


//import map.Map;
import game.BombermanGame;
import graphics.paint_bomberman;

public class Cadre_Jeu extends JFrame{ 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    Point centerPoint = ge.getCenterPoint();
    private JButton bouton = new JButton("Mon bouton");
	
    //Création de la fenêtre de jeu
	public Cadre_Jeu(BombermanGame BbmG){
		
		    Dimension windowSize = this.getSize();
		    this.setSize(BbmG.etatJeu.getMap().getSizeX()*50, BbmG.etatJeu.getMap().getSizeY()*50);
		    this.setTitle("Jeu Bomberman");
		    this.setLocationRelativeTo(null);

		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
		    
	        int dxg = centerPoint.x - windowSize.width / 2 ;
	        int dyg = centerPoint.y - windowSize.height / 2 -500; 
	        this.setLocation(dxg, dyg);
	               
	        p_bm = new paint_bomberman(BbmG);
	        p_bm.add(bouton);
	        getContentPane().add(p_bm);
	        
	        
	        
	        
	        
	}
	
	private JPanel p_bm;
	//JButton choixRun;
}
