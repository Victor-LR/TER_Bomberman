package map;
import java.util.ArrayList;


import agents.Agent;
import agents.AgentAction;
import agents.AgentType;

public class GameState {
	
	Map map;
	
	private ArrayList<Agent> ennemies;
	
	//Construit l'état courant de la map
	
	public GameState(Map map){
		
		ennemies = new ArrayList<Agent>();
		
		this.map=map;
		
		for(int i=0;i<map.getNumber_of_ennemies();i++)
		{
			Agent a = new Agent(AgentType.ENNEMY, map.getEnnemy_start_x(i), map.getEnnemy_start_y(i) );
			ennemies.add(a);
		}

	}
	
	//Verifie si l'action de éplacement est possible à l'etat courant
	
	public boolean isLegalMove(AgentAction action, Agent agent){
		int x = action.getVx();
		int y = action.getVy();
		if(map.isWall(agent.getX()+x, agent.getY()+y) || map.isBrokable_Wall(agent.getX()+x, agent.getY()+y) )
			return false;
		return true;
	}
	
	//réalise le mouvement 
	
	public void moveAgent(Agent agent, AgentAction action)
	{
		int x = action.getVx();
		int y = action.getVy();
		
	    agent.setX(x+action.getVx());
		agent.setY(y+action.getVy());
		
		if (action.getAction()!=Map.STOP) 
			agent.setDirection(action.getAction());
	}
	
	//Réalise un tour du jeu 
	
	public void taketurn(){
		
		ennemiesTurn();
	}	
	
	//Réalise le tour de l'ennemi
	
	public void ennemiesTurn(){

		ArrayList<Agent> ennemies = this.getEnnemies();

		for(int i = 0; i < ennemies.size(); i++){

			Agent ennemy = ennemies.get(i);
			
			AgentAction ennemyAction = ennemy.chooseAction(this);

			if (ennemyAction != null){
				
				this.moveAgent(ennemy, ennemyAction);
			}
			
		}
}


	/*private void updateMaze(){
	
		
		
		
	}*/
		
	//Renvoie un agent en fonction d'un id 
	
	public Agent getAgent(GameState etat, int agentId){
		
		for (Agent p : etat.getEnnemies()){
			if(p.getId() == agentId){
				return p;
			}
		}

		return null;
	}
	
	//accesseur sur la liste d'enemies
	
	public ArrayList<Agent> getEnnemies(){
		return ennemies;
	}
	
	//accesseur sur la map courrante 
	
	public Map getMap(){
		return map;
}

}
