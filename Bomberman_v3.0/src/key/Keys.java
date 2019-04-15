package key;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import agents.AgentAction;
import agents.Agent_Bomberman;
import map.GameState;
import map.Map;

public class Keys implements KeyListener{
	
	private Agent_Bomberman bomberman;
	private GameState game;
	private AgentAction Kaction;
	
	public Keys (GameState game, Agent_Bomberman bomberman) {
		this.bomberman = bomberman;
		this.game = game;
		this.Kaction = bomberman.chooseAction(game,new AgentAction(Map.STOP));
	}

//	public AgentAction getKeyAction(){
//		
//	}
	
	@Override
	public void keyPressed(KeyEvent evt) {
		// TODO Auto-generated method stub
		switch(evt.getKeyChar()) {
		case 'z':
			setKaction(bomberman.chooseAction(game,new AgentAction(Map.NORTH)));
			System.out.println("z");
			break;
		case 'q':
			setKaction(bomberman.chooseAction(game,new AgentAction(Map.WEST)));
			System.out.println("q");
			break;
		case 's':
			setKaction(bomberman.chooseAction(game,new AgentAction(Map.SOUTH)));
			System.out.println("s");
			break;
		case 'd':
			setKaction(bomberman.chooseAction(game,new AgentAction(Map.EAST)));
			System.out.println("d");
			break;
		default :
			setKaction(bomberman.chooseAction(game,new AgentAction(Map.STOP)));
			break;
		}
		//if (evt.getKeyChar() == 'r')
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent evt) {
		// TODO Auto-generated method stub
		switch(evt.getKeyChar()) {
		case 'z':
			setKaction(bomberman.chooseAction(game,new AgentAction(Map.NORTH)));
			break;
		case 'q':
			setKaction(bomberman.chooseAction(game,new AgentAction(Map.WEST)));
			break;
		case 's':
			setKaction(bomberman.chooseAction(game,new AgentAction(Map.SOUTH)));
			break;
		case 'd':
			setKaction(bomberman.chooseAction(game,new AgentAction(Map.EAST)));
			break;
		default :
			setKaction(bomberman.chooseAction(game,new AgentAction(Map.STOP)));
			break;
		}
	}

	public AgentAction getKaction() {
		return Kaction;
	}

	public void setKaction(AgentAction kaction) {
		Kaction = kaction;
	}
	
}
