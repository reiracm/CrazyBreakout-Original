package MainWindow;

import java.util.Observable;
import java.util.Observer;
/**
 * 
 * @author Yenira Chacón
 *
 */
public class BallManager implements Observer {
	
	GamePanel panel;
	
	/**
	 * El control de la cantidad de vidas disponibles
	 * se manejará desde el GamePanel para que el
	 * usuario vea la cantidad de bolas disponibles
	 * @param panel
	 */
	public BallManager(GamePanel panel){
		this.panel = panel;
	}

	@Override
	/**
	 * En el momento que la bola caiga y no rebote
	 * en la barra de juego, esta se perderá, se
	 * pondrá la nueva bola en el centro de la pantalla,
	 * y se descontará de la cantidad de bolas
	 * disponibles.
	 */
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		Ball ball = (Ball)o;
		
		if(ball.getPos_y() > 370){
			panel.RemoveLife();
			ball.resetBall();
		}
	}
	

}
