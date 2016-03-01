package MainWindow;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.UIManager;
import java.awt.Font;

/**
 * 
 * @author Yenira Chacón
 *
 */
public class ScoresWindow extends JPanel {

	private static Player finalPlayer [] = new Player [10];
	private static int pointer = 0;
	/**
	 * Se agrega un nuevo jugador una vez que se ingrese
	 * el nombre en la ventana principal. Este debe tener
	 * por lo menos 7 caracteres
	 * @param name
	 */
	public static void addPlayer(String name){		
		finalPlayer[pointer] = new Player(name);
		}
	/**
	 * Una vez que se tenga el puntaje final del
	 * jugador actual, se va a imprimir en la
	 * ventana de puntajes y el "pointer" aumentará
	 * en +1
	 * @param score
	 */
	public static void setScore(int score){
		finalPlayer[pointer].setFinalScore(score);
		pointer++;
	}
	
	/**
	 * Crea el panel de puntajes.
	 */
	public ScoresWindow() {
		
		setBackground(new Color(255, 235, 205));
		setLayout(null);
		
		//Se crea el label de puntajes
		JLabel lblPuntaje = new JLabel("Puntajes");
		lblPuntaje.setFont(new Font("Purisa", Font.BOLD, 14));
		lblPuntaje.setBounds(220, 29, 70, 15);
		add(lblPuntaje);
		
		/**
		 * Se crean dos arreglos de labels, uno
		 * para los primeros diez jugadores y otro
		 * para los primeros diez scores de cada
		 * jugador
		 */
		JLabel [] players = new JLabel[10];
		JLabel [] scores = new JLabel [10];
		
		int y = 40;
		
		/**
		 * Cada label creado tiene una posición con
		 * respecto a X y a Y alineado con su respectivo
		 * puntaje
		 */
		for(int i = 0; i < pointer; i++){
			players[i] = new JLabel();
			players[i].setBounds(110,y + 10, 70, 15);
			players[i].setOpaque(true);
			players[i].setText(finalPlayer[i].getPlayer());
			players[i].setBackground(new Color(255, 235, 205));
			add(players[i]);
			y = y + 35;
		}
		
		int ys = 40;
		
		for(int i = 0; i < pointer; i++){
			scores[i] = new JLabel();
			scores[i].setBounds(350,ys + 10, 70, 15);
			scores[i].setOpaque(true);
			scores[i].setText(Integer.toString(finalPlayer[i].getFinalScore()));
			scores[i].setBackground(new Color(255, 235, 205));
			add(scores[i]);
			ys = ys + 35;
		}
	}
}
