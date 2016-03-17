package MainWindow;

import java.util.Observable;
/**
 * 
 * @author Yenira Chacón
 *
 */

public class Ball extends Observable implements Runnable {
	
	private int speed;
	//minimo valor en x que puede tener la bola
	private int min_x = 0;
	//minimo valor en y que puede tener la bola
	private int min_y = 0;
	//posición en x de la bola
	private int pos_x;
	//poscición en y de la bola
	private int pos_y;
	//máximo valor en x que puede tener la bola
	private int max_x;
	//máximo valor en y que puede tener la bola
	private int max_y;
	//velocidad en x
	private int speed_x = 1;
	//velocidad en y
	private int speed_y = 1;
	//Variable booleana para iniciar el hilo
	private boolean alive = false;
	//Modo profundidad de la bola
	private int depth = 0;
	//Cantidad de bloques que ha pasado la bola en modo profundidad
	private int pastBlocks = 0;
	//Velocidad inicial de la bola
	private int tInitial = 0;
	//Tiempo de espera antes del cambio de velocidad
	private int initialDelay = 20;
	
	/**
	 * Constructor que asigna la rapidez inicial y los valores iniciales en X y Y 
	 * de la bola
	 * @param speed
	 * @param max_x
	 * @param max_y
	 */
	public Ball(int speed, int max_x, int max_y) {
		this.speed = speed;
		this.max_y = max_y;
		this.max_x = max_x;
		pos_x = max_x/2;
		pos_y = max_y/2;
		this.max_x = max_x;
		this.max_y = max_y;
		
		alive = true;
	}	
	/**
	 * Dirección respecto a la posición inicial que va a determinar velocidad
	 * en X y en Y
	 * @param x
	 * @param y
	 */	
	public void setInitialVector(int x, int y){
		
	}

	@Override
	/**
	 *Este método va a ejecutar el hilo del movimiento de la bola, de manera que va a 
	 *refrescar cada cierto tiempo la pantalla para una mejor ejecución.
	 *En este hilo se especifica su velocidad y su máximo alcance en eje X y eje Y.
	 *Dentro de este método existe la condición de que la bola no supere las coordenadas
	 *dee la ventana. De lo contrario, se multiplicará por -1 para cambiar la dirección
	 */
	public void run() {		
		// TODO Auto-generated method stub
				while(alive){				
					try {
						Thread.sleep(initialDelay);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					tInitial = tInitial + initialDelay;
					if(tInitial >= 20000 && initialDelay > 2){
						initialDelay--;
						tInitial = 0;
					}
					
					int temp_x = pos_x + speed_x;
					if(temp_x < max_x && temp_x > min_x){
						pos_x = temp_x;
					}
					else if(temp_x >= max_x){
						pos_x = max_x;
						speed_x *= -1;
					}
					else if(temp_x <= min_x){
						pos_x = min_x;
						speed_x *= -1;
					}
					
					int temp_y = pos_y + speed_y;
					if(temp_y < max_y && temp_y > min_y){
						pos_y = temp_y;
					}
					else if(temp_y >= max_y){
						pos_y = max_y;
						speed_y *= -1;
					}
					else if(temp_y <= min_y){
						pos_y = min_y;
						speed_y *= -1;
					}
					
					int positions[] = {pos_x, pos_y};
					
					setChanged();
					notifyObservers(positions);
					
				}
				
			}
	/**
	 * @return the alive
	 */
	public boolean isAlive() {
		return alive;
	}
	/**
	 * Este método no retornará nada pero cada vez que 
	 * el jugador pierda una bola,la siguiente tomará 
	 * el valor inicial de la primera y empezará a 
	 * moverse sola. Seguirán apareciendo hasta que la 
	 * cantidad de vidas o bolas sea de cero. 
	 */
	public void resetBall(){
		if(PlayerScore.getBalls() != 0){
			//valor inicial de x
			pos_x = max_x/2;
			//valor inicial de y
			pos_y = max_y/2;
		}
	}

	/**
	 * @param alive 
	 */
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	/**
	 * 
	 * @return pos_x que es la posición de la bola en el eje X
	 */
	public int getPos_x() {
		return pos_x;
	}

	/**
	 * @return pos_y que es la posición de la bola en el eje Y
	 */
	public int getPos_y() {
		return pos_y;
	}
	
	/**
	 * pastBlocks lleva el conteo de los bloques que la bola 
	 * ha pasado en modo profundidad 1 o 2
	 * @param pastBlocks
	 */
	public void setPastBlocks(int pastBlocks) {
		this.pastBlocks = pastBlocks;
	}
	/**
	 * Este método toma control del nivel de profundidad de
	 * la bola. Se utiliza la palabra reservada synchronized
	 * por para que se realice el cambio de profundidad solo
	 * cuando se toca el bloque magenta
	 * @return depth
	 */
	public synchronized int getDepth() {
		return depth;
	}
	/**
	 * Mientras el modo profundidad sea menor a 2, "depth"
	 * podrá aumentar en +1. De lo contrario se resetea el
	 * valor a 0 porque alcanzó su valor máximo.
	 */
	public synchronized void addDepth(){
		if(depth < 2){
			depth++;
		}
		else{
			depth = 0;
		}
	}
	/**
	 * En este método se obtiene la cantidad de bloques que ha
	 * pasado la bola en modo profundidad.
	 * @return pastBlocks
	 */
	public synchronized int getPastBlocks() {
		return pastBlocks;
	}
	/**
	 * Mientras la cantidad de bloques que ha pasado sea menor
	 * al modo profundidad de la bola, podrán aumentar en +1.
	 * De lo contrario se resetea el valor a cero. Por ejemplo,
	 * si el valor de profundidad es de 1 y no se ha brincado el
	 * bloque 1, addPastBlocks aumenta en 1. Y si addPastBlocks
	 * fuera igual a 1, cumple con la condición y vuelve a 0
	 * para destruir el bloque 2.
	 */
	public synchronized void addPastBlocks(){
		if(pastBlocks < depth){
			pastBlocks++;
		}
		else{
			pastBlocks = 0;
		}
	}
	/**
	 * Cuando la bola colisione, su valor en Y cambiará
	 * multiplicandose por -1 para el cambio de dirección
	 */
	public void yCollision(){
		speed_y *= -1;
	}
	/**
	 * Cuando la bola colisione, su valor en X cambiará
	 * multiplicandose por -1 para el cambio de dirección
	 */
	public void xCollision(){
		speed_x *= -1;
	}

}