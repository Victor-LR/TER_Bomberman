package map;
import java.awt.Frame;
import java.util.ArrayList;


import agents.Agent;
import agents.AgentAction;
import agents.AgentType;

public class GameState {
	
	Map map;
	
	private ArrayList<Agent> ennemies;
	
	public GameState(Map map){
		
		ennemies = new ArrayList<Agent>();
		
		this.map=map;
		
		for(int i=0;i<map.getNumber_of_ennemies();i++)
		{
			Agent a = new Agent(AgentType.ENNEMY, map.getEnnemy_start_x(i), map.getEnnemy_start_y(i) );
			ennemies.add(a);
		}

	}
	
	public boolean isLegalMove(AgentAction action, Agent agent){
		int x = action.getVx();
		int y = action.getVy();
		if(map.isWall(agent.getX()+x, agent.getY()+y))
			return false;
		return true;
	}

}
