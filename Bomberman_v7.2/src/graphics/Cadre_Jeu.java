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

import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import java.awt.BorderLayout;



//import map.Map;
import game.BombermanGame;
import graphics.paint_bomberman;
import graphics.paint_score;

public class Cadre_Jeu extends JFrame{ 
	
	private static final long serialVersionUID = 1L;
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    Point centerPoint = ge.getCenterPoint();
    private JPanel p_bm;
    private JPanel p_sc;
	
    //Création de la fenêtre de jeu
	public Cadre_Jeu(BombermanGame BbmG){
		
		    this.setSize(BbmG.etatJeu.getMap().getSizeX()*50, (BbmG.etatJeu.getMap().getSizeY()*50)+45);
		    this.setTitle("Jeu Bomberman");
		    this.setLocationRelativeTo(null);

		    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);         
	        
	        this.setLayout(new GridBagLayout());

	        GridBagConstraints c = new GridBagConstraints();
	        c.fill = GridBagConstraints.BOTH;
	        c.gridx = 0;
	        c.gridy = 0; 
	        c.gridwidth = 10;
	        c.gridheight = 2;
	        c.weightx = 10;
	        c.weighty = 10;
	        
	        /*JLabel text = new JLabel();
			text.setText("test");
			this.add(text,c);*/
	        
	        p_sc = new paint_score(BbmG);
	        this.add(p_sc,c);
	        c.fill = GridBagConstraints.BOTH;
	        c.gridx = 0;
	        c.gridy = 5; 
	        c.gridwidth = 10;
	        c.gridheight = 100;
	        c.weightx = 10;
	        c.weighty = 90;
	        
	        
	        p_bm = new paint_bomberman(BbmG);
	        this.add(p_bm,c);
	        
	        this.getContentPane();
	        
	}
	
	
	//JButton choixRun;
}
