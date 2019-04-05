package map;
import java.util.ArrayList;


import agents.Agent;
import agents.AgentAction;
import agents.AgentType;
import agents.Agent_Bomberman;
import objets.Objet_Bomb;
import objets.ObjetType;

public class GameState {
	
	Map map;
	
	private ArrayList<Agent> ennemies;
	private ArrayList<Agent_Bomberman> bombermans;
	private ArrayList<Objet_Bomb> bombs;
	
	//Construit l'état courant de la map
	
	public GameState(Map map){
		
		ennemies = new ArrayList<Agent>();
		bombermans = new ArrayList<Agent_Bomberman>();
		bombs = new ArrayList<Objet_Bomb>();
		
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
	
	//place une bombe
	
	public void placeBomb(Agent_Bomberman agent, AgentAction action)
	{
		//bombs = new ArrayList<Objet>();
		int x = agent.getX();
		int y = agent.getY();
		
		Objet_Bomb bomb = new Objet_Bomb(ObjetType.BOMB,x,y);
		
		bombs.add(bomb);
			
	}
	
	//détruit les mur adjacent de la bomb
	
	public void bombExplode(Objet_Bomb bomb)
	{
		int x = bomb.getObjX();
		int y = bomb.getObjY();
		
		for(int i = 1; i<bomb.getRange(); i++){
			if(x+i<map.getSizeX()){
				if(map.isBrokable_Wall(x+i, y)){
					map.setBrokable_Wall(x+i,y,false);
					break;
				}
				else if(map.isWall(x+i, y)) break;
			}
		}
		
		for(int i = 1; i<bomb.getRange(); i++){
			if( y+i < map.getSizeY()){
				if(map.isBrokable_Wall(x, y+i)){
					map.setBrokable_Wall(x,y+i,false);
					break;
				}
				else if(map.isWall(x, y+1)) break;
			}
				
		}
			
		for(int i = 1; i<bomb.getRange(); i++){
			if(x-i>0){
				if(map.isBrokable_Wall(x-i, y)){
					map.setBrokable_Wall(x-i,y,false);
					break;
				}
				else if(map.isWall(x-1, y)) break;
			}
		}
			
		for(int i = 1; i<bomb.getRange(); i++){
			if(y-i>0){
				if(map.isBrokable_Wall(x, y-i)){
					map.setBrokable_Wall(x,y-i,false);
					break;
				}
				else if(map.isWall(x, y-i)) break;
			}
		}

		
	}
	
	//Réalise un tour du jeu 
	
	public void taketurn(){
		
		bombermansTurn();
		ennemiesTurn();
		bombeTurn();
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
	
	public void bombeTurn(){

		ArrayList<Objet_Bomb> bombs = this.getBombs();
		
		for(int i = 0; i < bombs.size(); i++){

			Objet_Bomb bombe = bombs.get(i);
			int etat_bombe = bombe.getEtat();
			
			if (etat_bombe >= 9) {
				bombExplode(bombe);
				bombs.remove(bombe);
			}
			else bombe.setEtat(etat_bombe + 1);
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
	
	public ArrayList<Objet_Bomb> getBombs(){
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
