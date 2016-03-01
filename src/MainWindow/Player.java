package MainWindow;

/**
 * 
 * @author Yenira Chacón
 *
 */
public class Player {
	
	//Nuevo jugador
	private String player;
	
	//Puntaje final
	private int finalScore = 0;
	
	
	
	/**
	 * Constructor de jugador
	 * @param player
	 */
	public Player(String player){
		this.player = player;
	}

	/**
	 * 
	 * @return player
	 */
	public String getPlayer() {
		return player;
	}

	/**
	 * 
	 * @param player
	 */
	public void setPlayer(String player) {
		this.player = player;
	}

	/**
	 * 
	 * @return finalScore
	 */
	public int getFinalScore() {
		return finalScore;
	}
	
	/**
	 * 
	 * @param finalScore
	 */
	public void setFinalScore(int finalScore) {
		this.finalScore = finalScore;
	}
}
