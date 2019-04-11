package map;
import java.util.ArrayList;
import java.util.Random;


import agents.Agent;
import agents.AgentAction;
import agents.AgentType;
import agents.Agent_Bomberman;
import agents.ColorBomberman;
import objets.Objet;
import objets.Objet_Bomb;
import objets.ObjetType;

public class GameState {
	
	Map map;
	
	private ArrayList<Agent> ennemies;
	private ArrayList<Agent_Bomberman> bombermans;
	private ArrayList<Objet_Bomb> bombes;
	private ArrayList<Objet> items;
	
	private static Random numberGenerator = new Random();
	
	private int pourcentage = 25;
	
	//Construit l'état courant de la map
	
	public GameState(Map map){
		
		ennemies = new ArrayList<Agent>();
		bombermans = new ArrayList<Agent_Bomberman>();
		bombes = new ArrayList<Objet_Bomb>();
		items = new ArrayList<Objet>();
		
		this.map=map;
		
		for(int i=0;i<map.getNumber_of_ennemies();i++)
		{
			Agent a = new Agent(AgentType.ENNEMY, map.getEnnemy_start_x(i), map.getEnnemy_start_y(i) );
			ennemies.add(a);
		}

		ColorBomberman[] Couleurs= ColorBomberman.values();
		for(int i=0;i<map.getNumber_of_bombermans();i++)
		{
			Agent_Bomberman b = new Agent_Bomberman(map.getBomberman_start_x(i), map.getBomberman_start_y(i) );
			ColorBomberman col = Couleurs[i];
			b.setCouleur(col);
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
		
		if(agent.getBombes().size() < agent.getNbBombes()){
			Objet_Bomb bomb = new Objet_Bomb(ObjetType.BOMB,x,y);
			//System.out.println("action bombe");
			bombes.add(bomb);
			agent.getBombes().add(bomb);
		}
		//else System.out.println("		NON action bombe");
			
	}
	
	/*public <T> T randomElement(T[] elements){
		  return elements[numberGenerator.nextInt(elements.length)];
		}*/
		
		//Place un item
		public void placeItem(int itemx, int itemy)
		{
			ObjetType[] TypeItems= ObjetType.values();
			ObjetType UnType;
			do {
				UnType = TypeItems[numberGenerator.nextInt(TypeItems.length)];
			} while (UnType == ObjetType.BOMB);
			
			Objet item = new Objet(UnType,itemx,itemy);
			
			items.add(item);
				
		}
	
	public int test_range(int direction ,Objet_Bomb bomb, Agent_Bomberman bomberman)
	{
		int x = bomb.getObjX();
		int y = bomb.getObjY();
		
		int taille_range = 1;
		
		
		if (direction == Map.EAST) {
			for(int i = 0; i<=bomberman.getRange(); i++){
				if(x+i<map.getSizeX()){
					if(map.isBrokable_Wall(x+i, y) || map.isWall(x+i, y)){
						taille_range = x+i;
						break;
					}
					else taille_range = x+i;
				}
			}
		}
		
		if (direction == Map.SOUTH) {
			for(int i = 0; i<=bomberman.getRange(); i++){
				if(y+i < map.getSizeY()){
					if(map.isBrokable_Wall(x, y+i) || map.isWall(x, y+i)){
						taille_range = y+i;
						break;
					} else taille_range = y+i;
				}
			}
		}
		
		if (direction == Map.WEST) {
			for(int i = 0; i<=bomberman.getRange(); i++){
				if(x-i>=0){
					if(map.isBrokable_Wall(x-i, y) || map.isWall(x-i, y)){
						taille_range = x-i;
						break;
					} else taille_range = x-i;
				}
			}
		}
		if (direction == Map.NORTH) {
			for(int i = 0; i<=bomberman.getRange(); i++){
				if(y-i>=0){
					if(map.isBrokable_Wall(x, y-i) || map.isWall(x, y-i)){
						taille_range = y-i;
						break;
					} else taille_range = y-i;
				}
			}
		}
		return taille_range;
		
	}
	
	//détruit les murs adjacent de la bombe et les ennemies
	
	public void bombExplode(Objet_Bomb bomb, Agent_Bomberman bomberman )
	{
		int x = bomb.getObjX();
		int y = bomb.getObjY();
		
		ArrayList<Agent> ennemies = this.getEnnemies();
		
		int range_limit;
		
		int r = 100;
		
		range_limit = test_range(Map.EAST,bomb,bomberman);
	
		if(map.isBrokable_Wall(range_limit, y)) {
			map.setBrokable_Wall(range_limit,y,false);
			r =(int)(Math.random()*100);
			if ( r < pourcentage) placeItem(range_limit,y);
		}
				
		for (int i = x ; i <= range_limit ; i++){
				for(int j = 0; j<ennemies.size(); j++){
					Agent ennemie = ennemies.get(j);
					if(ennemie.getX() == i & ennemie.getY() == y){
						ennemies.remove(j);
						bomberman.setPoints(bomberman.getPoints() + 100);
					}
				}
				
				for(int j = 0; j<bombes.size(); j++){
					Objet_Bomb bombe = bombes.get(j);
					if(bombe.getObjX() == i & bombe.getObjY() == y){
						bombe.setEtat(10);
					}
				}
		}
		
		range_limit = test_range(Map.SOUTH,bomb,bomberman);
		if(map.isBrokable_Wall(x,range_limit)){
			map.setBrokable_Wall(x,range_limit,false);
			r =(int)(Math.random()*100);
			if ( r < pourcentage) placeItem(x,range_limit);
		}
		for (int i = y ; i <= range_limit ; i++){
				for(int j = 0; j<ennemies.size(); j++){
					Agent ennemie = ennemies.get(j);
					if(ennemie.getX() == x & ennemie.getY() == i){
						ennemies.remove(j);
						bomberman.setPoints(bomberman.getPoints() + 100);
					}
				}
				
				for(int j = 0; j<bombes.size(); j++){
					Objet_Bomb bombe = bombes.get(j);
					if(bombe.getObjX() == x & bombe.getObjY() == i){
						bombe.setEtat(10);
					}
				}
		}
		
		range_limit = test_range(Map.WEST,bomb,bomberman);
		if(map.isBrokable_Wall(range_limit, y)){
			map.setBrokable_Wall(range_limit,y,false);
			r =(int)(Math.random()*100);
			if ( r < pourcentage) placeItem(range_limit,y);
		}
		for (int i = x ; i > range_limit ; i--){
				for(int j = 0; j<ennemies.size(); j++){
					Agent ennemie = ennemies.get(j);
					if(ennemie.getX() == i & ennemie.getY() == y){
						ennemies.remove(j);
						bomberman.setPoints(bomberman.getPoints() + 100);
					}
				}
				
				for(int j = 0; j<bombes.size(); j++){
					Objet_Bomb bombe = bombes.get(j);
					if(bombe.getObjX() == i & bombe.getObjY() == y){
						bombe.setEtat(10);
					}
				}
		}
		
		range_limit = test_range(Map.NORTH,bomb,bomberman);
		if(map.isBrokable_Wall(x, range_limit)){
			map.setBrokable_Wall(x,range_limit,false);
			r =(int)(Math.random()*100);
			if ( r < pourcentage) placeItem(x,range_limit);
		}
		for (int i = y ; i > range_limit ; i--){
				for(int j = 0; j<ennemies.size(); j++){
					Agent ennemie = ennemies.get(j);
					if(ennemie.getX() == x & ennemie.getY() == i){
						ennemies.remove(j);
						bomberman.setPoints(bomberman.getPoints() + 100);
					}
				}
				
				for(int j = 0; j<bombes.size(); j++){
					Objet_Bomb bombe = bombes.get(j);
					if(bombe.getObjX() == x & bombe.getObjY() == i){
						bombe.setEtat(10);
					}
				}
		}
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
			
			//System.out.println(bomberman.getId()+ ":" + bomberman.getPoints());
			
			for (int j = 0; j < items.size(); j++){
				
				Objet item = items.get(j);
				
				if ( (bomberman.getX() == item.getObjX()) && (bomberman.getY() == item.getObjY()) ){
					
					if(item.getType() == ObjetType.FIRE_UP) bomberman.setRange(bomberman.getRange()+1);
					else if((item.getType() == ObjetType.FIRE_DOWN) && bomberman.getRange() > 1) bomberman.setRange(bomberman.getRange()-1);
					
					System.out.println("	range : "+bomberman.getRange());
					items.remove(item);
				}
			}
			
			AgentAction bombermanAction = bomberman.chooseAction(this);

			if (bombermanAction.getAction() < 5){
					
				this.moveAgent(bomberman, bombermanAction);
				this.bombeTurn(bomberman);
			}
			else{
				this.placeBomb(bomberman, bombermanAction);
				this.bombeTurn(bomberman);
				
			}
				
		}
	}
	
	public void bombeTurn(Agent_Bomberman bomberman){

		ArrayList<Objet_Bomb> bombs = bomberman.getBombes();
		
		
		for(int i = 0; i < bombs.size(); i++){

			Objet_Bomb bombe = bombs.get(i);
			int etat_bombe = bombe.getEtat();
			
			if (etat_bombe >= 11) { 
				bombExplode(bombe, bomberman);
				bombs.remove(bombe);
				bombes.remove(bombe);
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
	
	public ArrayList<Objet_Bomb> getBombes(){
		return bombes;
	}

	//accesseur sur la liste d'items
	
	public ArrayList<Objet> getItems(){
		return items;
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
