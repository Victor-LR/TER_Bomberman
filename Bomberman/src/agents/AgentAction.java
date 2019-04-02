package agents;

import map.Map;

public class AgentAction {
	
	private int vx;
	private int vy;
	
	private int action;
	
	public  AgentAction(int dir){
		this.action = dir;
		if (action==Map.STOP)
		{
			vx=0;
			vy=0;
		}
		else
		if (action==Map.NORTH)
		{
			vx=0;
			vy=-1;
		}
		else if (action==Map.SOUTH)
		{
			vx=0;
			vy=1;			
		}
		else if (action==Map.EAST)
		{
			vy=0;
			vx=+1;
		}
		else if (action==Map.WEST)
		{
			vy=0;
			vx=-1;			
		}
		/*else if (action==Map.BOMB)
		{
			vy=0;
			vx=-1;			
		}*/
		else 
		{
			System.err.println("Unknown action in AgentAction....");
			this.action=Map.STOP;
			vx=0;
			vy=0;
		}
		
	}
	
	public int getVx(){
		return vx;
	}
	
	public int getVy(){
		return vy;
	}
	
	public int getAction(){
		return action;
	}

}
