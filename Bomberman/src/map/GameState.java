package map;
import java.util.ArrayList;


import agents.Agent;
import agents.AgentAction;
import agents.AgentType;
import agents.Agent_Bomberman;
import objets.Objet;
import objets.ObjetType;

public class GameState {
	
	Map map;
	
	private ArrayList<Agent> ennemies;
	private ArrayList<Agent_Bomberman> bombermans;
	private ArrayList<Objet> bombs;
	
	//Construit l'état courant de la map
	
	public GameState(Map map){
		
		ennemies = new ArrayList<Agent>();
		bombermans = new ArrayList<Agent_Bomberman>();
		bombs = new ArrayList<Objet>();
		
		this.map=map;
		
		for(int i=0;i<map.getNumber_of_ennemies();i++)
		{
			Agent a = new Agent(AgentType.ENNEMY, map.getEnnemy_start_x(i), map.getEnnemy_start_y(i) );
			ennemies.add(a);
		}
		
		for(int i=0;i<map.getNumber_of_bombermans();i++)
		{
			Agent_Bomberman b = new Agent_Bomberman(map.getBomberman_start_x(i), map.getBomberman_start_y(i) );
			bombermans.add(b);
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
		int x = agent.getX();
		int y = agent.getY();
		
	    agent.setX(x+action.getVx());
		agent.setY(y+action.getVy());
		
		if (action.getAction()!=Map.STOP) 
			agent.setDirection(action.getAction());
	}
	
	//place une bomb
	
	public void placeBomb(Agent_Bomberman agent, AgentAction action)
	{
		//bombs = new ArrayList<Objet>();
		int x = agent.getX();
		int y = agent.getY();
		
		Objet bomb = new Objet(ObjetType.BOMB,x,y);
		
		bombs.add(bomb);
			
	}
	
	//Réalise un tour du jeu 
	
	public void taketurn(){
		
		bombermansTurn();
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
	
	//Réalise le tour des bombermans
	
	public void bombermansTurn(){

		ArrayList<Agent_Bomberman> bombermans = this.getBombermans();
		
		for(int i = 0; i < bombermans.size(); i++){

			Agent_Bomberman bomberman = bombermans.get(i);
				
			AgentAction bombermanAction = bomberman.chooseAction(this);

			if (bombermanAction.getAction() < 5){
					
				this.moveAgent(bomberman, bombermanAction);
			}
			else{
				this.placeBomb(bomberman, bombermanAction);
			}
				
		}
	}


	//Renvoie un agent en fonction d'un id 
	
	public Agent getAgent(GameState etat, int agentId){
		
		for (Agent p : etat.getEnnemies()){
			if(p.getId() == agentId){
				return p;
			}
		}

		return null;
	}
	
	//accesseur sur la liste de bombermans
	
	public ArrayList<Agent_Bomberman> getBombermans(){
		return bombermans;
	}
	
	//accesseur sur la liste de bombermans
	
	public ArrayList<Objet> getBombs(){
		return bombs;
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
