package agents;
import java.io.Serializable;
import java.util.ArrayList;

import agents.AgentAction;
import agents.AgentType;
import map.Map;
import map.GameState;

public class Agent implements Serializable{
	
	static int current_Id = 0;
	
	private int x;
	private int y;
	private int id;
	private AgentType agent;
	
	private int position;
	
	private boolean isDead;
	
	public Agent(AgentType ag, int px, int py){
		this.x = px;
		this.y = py;
		this.id = Agent.current_Id;
		this.position = Map.NORTH;
		current_Id++;
		this.agent = ag;
		this.isDead = false;
	}
	
	public AgentAction chooseAction(GameState etatjeu) 
	{
		ArrayList<AgentAction> listAction=new ArrayList<AgentAction>();
		for(int i=0;i<5;i++)
		{
			if (etatjeu.isLegalMove(new AgentAction(i), this))
				listAction.add(new AgentAction(i));
		}		
		return(listAction.get((int)(Math.random()*listAction.size())));
	}
	
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public AgentType getAgentype() {
		return agent;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	
}
