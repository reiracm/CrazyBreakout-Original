package MainWindow;

import javax.swing.JOptionPane;

/**
 * 
 * @author Yenira Chacón
 *
 */
public class PlayerScore {
	
	//Puntaje que inicia en 0
	private static int score = 0;
	//Cantidad de bolas extra inicia en 5
	private static int balls = 5;
	
	/**
	 * El puntaje obtenido se sumará al puntaje final del jugador
	 * @param nScore
	 */
	public static void addScore(int nScore){
		score += nScore;
		}
	
	/**
	 * Cuando una bola se pierda por la parte inferior de la 
	 * pantalla, la variable "balls" disminuye el -1
	 */
	public static void looseBall(){
		balls--;
	}
	/**
	 * Este método solo se encargará de indicar cuando un jugador
	 * pierde la partida
	 */
	public static void looser(){
		JOptionPane.showMessageDialog(null, "You lose\nYour new Score is: " + score);
	}

	public static void winner(){
		JOptionPane.showMessageDialog(null, "You won\nYour new Score is: " + score);
	}
	/**
	 * 
	 * @return score
	 */
	public static int getScore() {
		return score;
	}

	/**
	 * 
	 * @return balls
	 */
	public static int getBalls() {
		return balls;
	}
	
	

}
