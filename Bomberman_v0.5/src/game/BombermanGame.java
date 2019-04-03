package game;

import map.GameState;
import map.Map;

public class BombermanGame extends Game implements InterfaceGame {

	Map map;
	public GameState etatJeu;
	
	@Override
	protected void initializeGame() {

		this.etatJeu = new GameState(map);
	}



	@Override
	protected void taketurn() {
		etatJeu.taketurn();	
	}
	
	@Override
	protected void gameOver() {

	}

}
