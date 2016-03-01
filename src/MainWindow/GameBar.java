package MainWindow;

import java.util.Observable;
import java.util.Observer;
/**
 * 
 * @author Yenira Chacón
 *
 */
public class GameBar implements Observer{
	//Posición en X de la barra
	private int x_pos;
	//Posición en Y de la barra
	private int y_pos;
	//Largo de la barra
	private int length = 20;
	//Maximo valor en X que alcanza la barra dentro de la interfaz
	private int max_x;
	
	private int smaller = 8;
	
	private int height = 15;
	
	private boolean rCollision = false;
	
	private boolean rBlock = false;

	/**
	 * La barra posee los atributos de largo, máxima posición en X
	 * y su posición en Y para verificar lo siguiente: que la bola
	 * rebote dentro de las coordenadas de la barra, que la barra
	 * no pase de los bordes de la ventana de juego y que ya bola
	 * no altere sus coordenadas en Y, solo en X
	 * @param length
	 * @param max_x
	 * @param y_pos
	 */
	public GameBar(int length, int max_x, int y_pos) {
		this.length = length;
		this.max_x = max_x;
		this.y_pos = y_pos;
		
		x_pos = (max_x/2) - (length/2);
	}

	/**
	 * @return the x_pos
	 */
	public int getX_pos() {
		return x_pos;
	}

	/**
	 * @param x_pos the x_pos to set
	 */
	public void setX_pos(int x_pos) {
		if(x_pos < 0){
			x_pos = 0;
		}
		else if(x_pos > (max_x - length)){
			x_pos = max_x - length;
		}
		else{
			this.x_pos = x_pos;
		}
	}

	public int getY_pos() {
		return y_pos;
	}

	public int smallBar(){
		length = length - smaller;
		return length;
	}
	
	public int gLength(){
		return length;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void roll(){
		if(!rBlock){
			Thread t = new Thread(){
				public void run(){
					rBlock = true;
					int tempH = height;
					int tempL = length;
					int tempY = y_pos;
					int tempX = x_pos;
					height = tempL;
					length = tempH;
					y_pos = y_pos - tempL/2;
					x_pos = x_pos + tempL/2;
					rCollision = true;
					try {
						Thread.sleep(400);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					rCollision = false;
					height = tempH;
					length = tempL;
					y_pos = tempY;
					x_pos = tempX;
					rBlock = false;
				}
			};
			t.start();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
		int position[] = (int[])arg;
		Ball ball = (Ball) o;
				
		if((position[1] + 10) == y_pos && position[0] >= x_pos && position[0] <= x_pos + length && 
				!rCollision){
			ball.yCollision();
			
		}		
		
		if(rCollision == true){
			if((ball.getPos_y() + 10) == y_pos && ball.getPos_x() >= x_pos && ball.getPos_x() <= x_pos + length){
				ball.yCollision();
			}
			
			if((ball.getPos_x() + 10) == x_pos && ball.getPos_y() >= y_pos){
				ball.yCollision();
				ball.xCollision();
			}
			
			if(ball.getPos_x() == (x_pos + 15) && ball.getPos_y() >= y_pos){
				ball.yCollision();
				ball.xCollision();
			}
		}
	}		
}
