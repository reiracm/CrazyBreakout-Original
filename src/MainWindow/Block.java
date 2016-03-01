package MainWindow;

import java.util.Observable;
import java.util.Observer;

/**
 * 
 * @author Yenira Chacón
 *
 */
public class Block implements Observer {
	// Bloque en la posición Y
	int bpos_x;
	// Bloque en la posición X
	int bpos_y;
	//Lado de arriba del bloque
	int up_side;
	//Lado de abajo del bloque
	int down_side;
	//Lado izquierdo del bloque
	int left_side;
	//Lado derecho del bloque
	int right_side;
	//Alto del bloque
	int bheight;
	//Ancho del bloque
	int bwidth;
	//Tipo de bloque: común, doble, triple, interno
	int btype;
	//Booleano para destruir el bloque y quitarlo de la interfaz
	boolean visible;
	//Cantidad de golpes según el bloque
	int hits = 1;
	//Conteo de bloques destruidos
	static int destroyed = 0;
	
	/**
	 * Cada bloque se formará con los siguientes parámetros, que son sus atributos
	 * @param bpos_x
	 * @param bpos_y
	 * @param bheight
	 * @param bwidth
	 * @param btype
	 */
	public Block(int bpos_x, int bpos_y, int bheight, int bwidth, int btype) {

		this.bpos_x = bpos_x;
		this.bpos_y = bpos_y;
		this.bheight = bheight;
		this.bwidth = bwidth;
		this.btype = btype;

		up_side = bpos_y;
		down_side = bpos_y + bheight;
		left_side = bpos_x;
		right_side = bpos_x + bwidth;

	}
	/**
	 * @return bpos_x
	 */
	public int getBpos_x() {
		return bpos_x;
	}
	/**
	 * 
	 * @param bpos_x
	 */
	public void setBpos_x(int bpos_x) {
		this.bpos_x = bpos_x;
	}
	/**
	 * 
	 * @return bpos_y
	 */
	public int getBpos_y() {
		return bpos_y;
	}
	
	/**
	 * 
	 * @param bpos_y
	 */
	public void setBpos_y(int bpos_y) {
		this.bpos_y = bpos_y;
	}
	
	/**
	 * 
	 * @return up_side
	 */
	public int getUp_side() {
		return up_side;
	}
	
	/**
	 * 
	 * @param up_side
	 */
	public void setUp_side(int up_side) {
		this.up_side = up_side;
	}

	/**
	 * 
	 * @return down_side
	 */
	public int getDown_side() {
		return down_side;
	}

	/**
	 * 
	 * @param down_side
	 */
	public void setDown_side(int down_side) {
		this.down_side = down_side;
	}

	/**
	 * 
	 * @return left_side
	 */
	public int getLeft_side() {
		return left_side;
	}

	/**
	 * 
	 * @param left_side
	 */
	public void setLeft_side(int left_side) {
		this.left_side = left_side;
	}

	/**
	 * 
	 * @return right_side
	 */
	public int getRight_side() {
		return right_side;
	}

	/**
	 * 
	 * @param right_side
	 */
	public void setRight_side(int right_side) {
		this.right_side = right_side;
	}

	/**
	 * 
	 * @return bheight
	 */
	public int getBheight() {
		return bheight;
	}

	/**
	 * 
	 * @param bheight
	 */
	public void setBheight(int bheight) {
		this.bheight = bheight;
	}

	/**
	 * 
	 * @return bwidth
	 */
	public int getBwidth() {
		return bwidth;
	}

	/**
	 * 
	 * @param bwidth
	 */
	public void setBwidth(int bwidth) {
		this.bwidth = bwidth;
	}

	/**
	 * 
	 * @return btype
	 */
	public int getBtype() {
		return btype;
	}

	/**
	 * 
	 * @param btype
	 */
	public void setBtype(int btype) {
		this.btype = btype;
	}
	
	/**
	 * 
	 * @return visible
	 */
	public boolean isVisible() {
		return visible;
	}
	
	/**
	 * 
	 * @param visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public static void addDestroyed(){
		destroyed++;
	}

	public static int getDestroyed() {
		return destroyed;
	}
	/**
	 * Cuando la bola colisione con el bloque magenta se le sumará
	 * la profundidad y solamente rebotará con el bloque, en el caso 
	 * de que el bloque sea doble, contará la cantidad de golpes, igual
	 * en el caso triple. Y para los bloques internos verificará si
	 * la bola colisiona en modo profundidad. Esto para las cuatro
	 * laterales de los bloques.
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		Ball ball = (Ball) o;
		int pos_x = ball.getPos_x();
		int pos_y = ball.getPos_y();

		if (pos_y + 10 == up_side && ((pos_x >= left_side && pos_x <= right_side)
				|| (pos_x + 10 >= left_side && pos_x + 10 <= right_side))) {
			if (btype > 4) {
				ball.yCollision();
				if (ball.getDepth() < 2) {
					ball.addDepth();
					ball.setPastBlocks(0);
				} else if (ball.getDepth() == 2) {
					ball.addDepth();
					ball.setPastBlocks(0);

				}
			}

			else {
				if (ball.getDepth() == ball.getPastBlocks()) {
					ball.yCollision();
					if (hits < btype && btype < 4) {
						hits++;
					} else if (hits == btype) {
						visible = false;
						ball.deleteObserver(this);
						ball.addPastBlocks();
						score(btype);
					} else if (btype == 4) {
						visible = false;
						ball.deleteObserver(this);
						ball.addPastBlocks();
						if (ball.getDepth() > 0) {
							PlayerScore.addScore(30);
							addDestroyed();
						} else {
							PlayerScore.addScore(10);
							addDestroyed();
						}
					}
				}

				else if (ball.getDepth() > ball.getPastBlocks()) {
					ball.addPastBlocks();
				}
			}
		} else if (pos_y == down_side && ((pos_x >= left_side && pos_x <= right_side)
				|| (pos_x + 10 >= left_side && pos_x + 10 <= right_side))) {
			if (btype > 4) {
				ball.yCollision();
				if (ball.getDepth() < 2) {
					ball.addDepth();
					ball.setPastBlocks(0);
				} else if (ball.getDepth() == 2) {
					ball.addDepth();
					ball.setPastBlocks(0);

				}
			} else {
				if (ball.getDepth() == ball.getPastBlocks()) {
					ball.yCollision();
					if (hits < btype && btype < 4) {
						hits++;
					} else if (hits == btype) {
						visible = false;
						ball.deleteObserver(this);
						ball.addPastBlocks();
						score(btype);
					} else if (btype == 4) {
						visible = false;
						ball.deleteObserver(this);
						ball.addPastBlocks();
						if (ball.getDepth() > 0) {
							PlayerScore.addScore(30);
							addDestroyed();
						} else {
							PlayerScore.addScore(10);
							addDestroyed();
						}
					}
				}

				else if (ball.getDepth() < ball.getPastBlocks()) {
					ball.addPastBlocks();
				}
			}
		} else if (pos_x + 10 == left_side
				&& ((pos_y >= up_side && pos_y <= down_side) || (pos_y + 10 >= up_side && pos_y + 10 <= down_side))) {
			if (btype > 4) {
				ball.xCollision();
				if (ball.getDepth() < 2) {
					ball.addDepth();
					ball.setPastBlocks(0);
				} else if (ball.getDepth() == 2) {
					ball.addDepth();
					ball.setPastBlocks(0);

				}
			} else {
				if (ball.getDepth() == ball.getPastBlocks()) {
					ball.xCollision();
					if (hits < btype && btype < 4) {
						hits++;
					} else if (hits == btype) {
						visible = false;
						ball.deleteObserver(this);
						ball.addPastBlocks();
						score(btype);
					} else if (btype == 4) {
						visible = false;
						ball.deleteObserver(this);
						ball.addPastBlocks();
						if (ball.getDepth() > 0) {
							PlayerScore.addScore(30);
							addDestroyed();
						} else {
							PlayerScore.addScore(10);
							addDestroyed();
						}
					}
				}

				else if (ball.getDepth() < ball.getPastBlocks()) {
					ball.addPastBlocks();
				}
			}
		} else if (pos_x == right_side
				&& ((pos_y >= up_side && pos_y <= down_side) || (pos_y + 10 >= up_side && pos_y + 10 <= down_side))) {
			if (btype > 4) {
				ball.xCollision();
				if (ball.getDepth() < 2) {
					ball.addDepth();
					ball.setPastBlocks(0);
				} else if (ball.getDepth() == 2) {
					ball.addDepth();
					ball.setPastBlocks(0);

				}
			} else {
				if (ball.getDepth() == ball.getPastBlocks()) {
					ball.xCollision();
					if (hits < btype && btype < 4) {
						hits++;
					} else if (hits == btype) {
						visible = false;
						ball.deleteObserver(this);
						ball.addPastBlocks();
						score(btype);
					} else if (btype == 4) {
						visible = false;
						ball.deleteObserver(this);
						ball.addPastBlocks();
						if (ball.getDepth() > 0) {
							PlayerScore.addScore(30);
							addDestroyed();
						} else {
							PlayerScore.addScore(10);
							addDestroyed();
						}
					}
				}

				else if (ball.getDepth() < ball.getPastBlocks()) {
					ball.addPastBlocks();
				}
			}
		}
	}// fin de update

	/**
	 * Para cada caso de cada bloques se agregará
	 * el puntaje correspondiente, por medio de este
	 * método
	 * @param type
	 */
	public void score(int type) {
		switch (type) {
		case (1):
			PlayerScore.addScore(10);
			addDestroyed();
			break;
		case (2):
			PlayerScore.addScore(15);
			addDestroyed();
			break;
		case (3):
			PlayerScore.addScore(12);
			addDestroyed();
			break;
		}
	}

}