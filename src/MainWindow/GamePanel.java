package MainWindow;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;
/**
 * 
 * @author Yenira Chacón
 *
 */
public class GamePanel extends JPanel{
	
	JLabel lblBall;
	Ball ball;
	
	Thread ball_t;
	
	boolean isStarted = false;
	//Label de la barra de juego
	private JLabel lbl_gamebar;
	//Barra de juego
	private GameBar gamebar;
	//Ventana
	private JFrame frame;
	//Booleano para el inicio del hilo
	private boolean isAlive = true;
	//Panel de juego
	private GamePanel panel = this;
	//Label de puntaje
	private JLabel lblScore;
	//Label de la primera bola
	private JLabel lblBall1;
	//Label de la segunda bola
	private JLabel lblBall2;
	//Label de la tercera bola
	private JLabel lblBall3;
	//Label de la cuarta bola
	private JLabel lblBall4;
	//Label de la quinta bola
	private JLabel lblBall5;
	
	private int magenta = 0;
	
	/**
	 * Create the panel.
	 */
	public GamePanel(JFrame frame) {
		this.frame = frame;
		setBackground(Color.BLACK);
		setLayout(null);
		setSize(500, 420);
		
		lblBall = new JLabel();
		lblBall.setBackground(Color.RED);
		lblBall.setSize(10, 10);
		lblBall.setOpaque(true);
		
		ball = new Ball(20, 500, 500);
		ball_t = new Thread(ball);
		
		BallManager bm = new BallManager(this);
		ball.addObserver(bm);
		
		lblBall.setBounds(ball.getPos_x(), ball.getPos_y(), 10, 10);
		
		add(lblBall);
		
		gamebar = new GameBar(70,610,340);
		
		ball.addObserver(gamebar);
		
		lbl_gamebar = new JLabel();
		lbl_gamebar.setBackground(Color.WHITE);
		lbl_gamebar.setBounds(gamebar.getX_pos(), 340, 20, 15);
		lbl_gamebar.setOpaque(true);
		add(lbl_gamebar);
		
		lblScore = new JLabel();
		lblScore.setForeground(Color.WHITE);
		lblScore.setBounds(12, 370, 110, 20);
		add(lblScore);
		
		lblBall1 = new JLabel();
		lblBall1.setBackground(Color.RED);
		lblBall1.setBounds(616, 370, 10, 10);
		add(lblBall1);
		lblBall1.setOpaque(true);
		
		lblBall2 = new JLabel();
		lblBall2.setBackground(Color.RED);
		lblBall2.setBounds(630, 370, 10, 10);
		add(lblBall2);
		lblBall2.setOpaque(true);
		
		lblBall3 = new JLabel();
		lblBall3.setBackground(Color.RED);
		lblBall3.setBounds(644, 370, 10, 10);
		add(lblBall3);
		lblBall3.setOpaque(true);
		
		lblBall4 = new JLabel();
		lblBall4.setBackground(Color.RED);
		lblBall4.setBounds(658, 300, 10, 10);
		add(lblBall4);
		lblBall4.setOpaque(true);
		
		lblBall5 = new JLabel();
		lblBall5.setBackground(Color.RED);
		lblBall5.setBounds(672, 370, 10, 10);
		add(lblBall5);
		lblBall5.setOpaque(true);
		
		JLabel [] labels = new JLabel[30];
		Block blocks [ ] = new Block[30];

		int p = 0;
		
		/**
		 * Condiciones para la creación de bloques y 
		 * creación de variable local "num" que define
		 * el tipo de bloque según el número aleatorio
		 */
		for(int i = 0 ; i < labels.length ; i++){
			Random rnd = new Random();
			int num = (int) (rnd.nextDouble()*5+1);			
			
			if(i <= 9){
				if (i == 0){
					blocks[i] = new Block(0, 0, 50, 50, num);
					ball.addObserver(blocks[i]);
					blocks[i].setVisible(true);
					labels[i] = new JLabel(); 
					labels[i].setBounds(blocks[i].bpos_x, blocks[i].bpos_y, blocks[i].bwidth, blocks[i].bheight);
					labels[i].setBorder(BorderFactory.createLineBorder(Color.white,2));
					labels[i].setOpaque(true);
					if(blocks[i].getBtype() == 1){
						labels[i].setBackground(Color.PINK);
						add(labels[i]);
					}
					
					else if(num == 2){
						labels[i].setBackground(Color.orange);
						add(labels[i]);
					}
					else if(num == 3){
						labels[i].setBackground(Color.YELLOW);
						add(labels[i]);
					}
					else if(num == 4){
						labels[i].setBackground(Color.GREEN);
						add(labels[i]);
					}
					else{
						labels[i].setBackground(Color.magenta);
						add(labels[i]);
						magenta++;
					}
					add(labels[i]);
				}
				else if(i == 1){
					blocks[i] = new Block(50, 0, 50, 50, num);
					ball.addObserver(blocks[i]);
					blocks[i].setVisible(true);
					labels[i] = new JLabel(); 
					labels[i].setBounds(blocks[i].bpos_x, blocks[i].bpos_y, blocks[i].bwidth, blocks[i].bheight);
					labels[i].setBorder(BorderFactory.createLineBorder(Color.white,2));
					labels[i].setOpaque(true);
					if(blocks[i].getBtype() == 1){
						labels[i].setBackground(Color.PINK);
						add(labels[i]);
					}
					
					else if(num == 2){
						labels[i].setBackground(Color.orange);
						add(labels[i]);
					}
					else if(num == 3){
						labels[i].setBackground(Color.YELLOW);
						add(labels[i]);
					}
					else if(num == 4){
						labels[i].setBackground(Color.GREEN);
						add(labels[i]);
					}
					else{
						labels[i].setBackground(Color.magenta);
						add(labels[i]);
						magenta++;
					}
				}
				else {
					p = p + 50;
					blocks[i] = new Block(50 + p, 0, 50, 50, num);
					ball.addObserver(blocks[i]);
					blocks[i].setVisible(true);
					labels[i] = new JLabel(); 
					labels[i].setBounds(blocks[i].bpos_x, blocks[i].bpos_y, blocks[i].bwidth, blocks[i].bheight);
					labels[i].setBorder(BorderFactory.createLineBorder(Color.white,2));
					labels[i].setOpaque(true);
					if(num == 1){
						labels[i].setBackground(Color.PINK);
						add(labels[i]);
					}
					
					else if(num == 2){
						labels[i].setBackground(Color.orange);
						add(labels[i]);
					}
					else if(num == 3){
						labels[i].setBackground(Color.YELLOW);
						add(labels[i]);
					}
					else if(num == 4){
						labels[i].setBackground(Color.GREEN);
						add(labels[i]);
					}
					else{
						labels[i].setBackground(Color.magenta);
						add(labels[i]);
						magenta++;
					}
				}
			}
			
			else if( i > 9 && i < 20){
				if(i == 10){
					p = 0;
					blocks[i] = new Block(0, 50, 50, 50, num);
					ball.addObserver(blocks[i]);
					blocks[i].setVisible(true);
					labels[i] = new JLabel(); 
					labels[i].setBounds(blocks[i].bpos_x, blocks[i].bpos_y, blocks[i].bwidth, blocks[i].bheight);
					labels[i].setBorder(BorderFactory.createLineBorder(Color.white,2));
					labels[i].setOpaque(true);
					if(num == 1){
						labels[i].setBackground(Color.PINK);
						add(labels[i]);
					}
					
					else if(num == 2){
						labels[i].setBackground(Color.orange);
						add(labels[i]);
					}
					else if(num == 3){
						labels[i].setBackground(Color.YELLOW);
						add(labels[i]);
					}
					else if(num == 4){
						labels[i].setBackground(Color.GREEN);
						add(labels[i]);
					}
					else{
						labels[i].setBackground(Color.MAGENTA);
						add(labels[i]);
						magenta++;
					}
				}
				else if(i > 10 && i <= 19){
					blocks[i] = new Block(50 + p, 50, 50, 50, num);
					ball.addObserver(blocks[i]);
					blocks[i].setVisible(true);
					labels[i] = new JLabel(); 
					labels[i].setBounds(blocks[i].bpos_x, blocks[i].bpos_y, blocks[i].bwidth, blocks[i].bheight);
					labels[i].setBorder(BorderFactory.createLineBorder(Color.white,2));
					p = p + 50;
					labels[i].setOpaque(true);
					if(num == 1){
						labels[i].setBackground(Color.PINK);
						add(labels[i]);
					}
					
					else if(num == 2){
						labels[i].setBackground(Color.orange);
						add(labels[i]);
					}
					else if(num == 3){
						labels[i].setBackground(Color.YELLOW);
						add(labels[i]);
					}
					else if(num == 4){
						labels[i].setBackground(Color.GREEN);
						add(labels[i]);
					}
					else{
						labels[i].setBackground(Color.magenta);
						add(labels[i]);
						magenta++;
					}
				}
			}
			
			else{
				if(i == 20){
					p = 0;
					blocks[i] = new Block(0, 100, 50, 50, num);
					ball.addObserver(blocks[i]);
					blocks[i].setVisible(true);
					labels[i] = new JLabel(); 
					labels[i].setBounds(blocks[i].bpos_x, blocks[i].bpos_y, blocks[i].bwidth, blocks[i].bheight);
					labels[i].setBorder(BorderFactory.createLineBorder(Color.white,2));
					labels[i].setOpaque(true);
					if(num == 1){
						labels[i].setBackground(Color.PINK);
						add(labels[i]);
					}
					
					else if(num == 2){
						labels[i].setBackground(Color.orange);
						add(labels[i]);
					}
					else if(num == 3){
						labels[i].setBackground(Color.YELLOW);
						add(labels[i]);
					}
					else if(num == 4){
						labels[i].setBackground(Color.GREEN);
						add(labels[i]);
					}
					else{
						labels[i].setBackground(Color.magenta);
						add(labels[i]);
						magenta++;
					}
				}
				
				else{
				blocks[i] = new Block(50+ p, 100, 50, 50, num);
				ball.addObserver(blocks[i]);
				blocks[i].setVisible(true);
				labels[i] = new JLabel(); 	
				labels[i].setBounds(blocks[i].bpos_x, blocks[i].bpos_y, blocks[i].bwidth, blocks[i].bheight);
				labels[i].setBorder(BorderFactory.createLineBorder(Color.white,2));
				p = p + 50;
				labels[i].setOpaque(true);
				if(num == 1){
					labels[i].setBackground(Color.PINK);
					add(labels[i]);
				}
				
				else if(num == 2){
					labels[i].setBackground(Color.orange);
					add(labels[i]);
				}
				else if(num == 3){
					labels[i].setBackground(Color.YELLOW);
					add(labels[i]);
				}
				else if(num == 4){
					labels[i].setBackground(Color.GREEN);
					add(labels[i]);
				}
				else{
					labels[i].setBackground(Color.magenta);
					add(labels[i]);
					magenta++;
				}
			}
		}
	}
		
		Thread t_refresh = new Thread(){
			
			@Override
			/**
			 * Hilo que refresca la pantalla de juego
			 * y posiciona los labels según sus posiciones
			 * actuales.
			 */
			public void run(){
				while(isAlive){
					 try {
						Thread.sleep(16);
					 } 
					 catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					 }
					 
					 lbl_gamebar.setBounds(gamebar.getX_pos(), gamebar.getY_pos(), gamebar.gLength(), gamebar.getHeight());
					 lblBall.setBounds(ball.getPos_x(), ball.getPos_y(), 10, 10);
					 lblScore.setText("score: " + PlayerScore.getScore());
					 lblBall1.setBounds(416, 370, 10, 10);
					 lblBall2.setBounds(430, 370, 10, 10);
					 lblBall3.setBounds(444, 370, 10, 10);
					 lblBall4.setBounds(458, 370, 10, 10);
					 lblBall5.setBounds(472, 370, 10, 10);
					 for(int i = 0; i < blocks.length; i++){
						 if (!blocks[i].isVisible()){
							 panel.remove(labels[i]);
							 labels[i].setVisible(true);
						 }
					 }
					 
					 frame.setVisible(true);
					 panel.revalidate();
					 panel.repaint();
					 if((magenta + Block.getDestroyed()) == 30){
							PlayerScore.winner();
							isAlive = false;
							ball.setAlive(false);
							ScoresWindow.setScore(PlayerScore.getScore());
							frame.setContentPane(new PanelInicial((MainWindow)frame));
					}
				}
			}			
		};
		//Inicia el hilo
		t_refresh.start();
		
		/**
		 * Cuando se presione el mouse o se presione
		 * en cualquier parte dentro de la pantalla
		 * de juego, la bola inicia a moverse
		 * aleatoriamente
		 */
		this.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(!isStarted){
					isStarted=true;
					ball.setInitialVector(arg0.getX(), arg0.getY());
					ball_t.start();
				}else{
					ball.setInitialVector(arg0.getX(), arg0.getY());
					gamebar.roll();
				}
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		addMouseMotionListener(new MouseMotionListener() {
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				gamebar.setX_pos(e.getX());
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	/**
	 * Este método se encargará de descontar
	 * cada vida cuando la bola se caiga por
	 * la parte inferior de la pantalla. De
	 * manera que cuando quede una bola y 
	 * esta se pierda, aparece una ventana
	 * con el puntaje final, en ese momento
	 * se pierde el juego.
	 */
	public void RemoveLife(){
		switch(PlayerScore.getBalls()){
			case(1):
				this.remove(lblBall1);
				PlayerScore.looseBall();
				isAlive = false;
				PlayerScore.looser();
				ScoresWindow.setScore(PlayerScore.getScore());
				frame.setContentPane(new PanelInicial((MainWindow)frame));
				frame.setVisible(true);
				break;
			case(2):
				this.remove(lblBall2);
				PlayerScore.looseBall();
				lbl_gamebar.setBounds(gamebar.getX_pos(), 340, gamebar.smallBar() , 15);
				break;
			case(3):
				this.remove(lblBall3);
				PlayerScore.looseBall();
				lbl_gamebar.setBounds(gamebar.getX_pos(), 340, gamebar.smallBar() , 15);
				break;
			case(4):
				this.remove(lblBall4);
				PlayerScore.looseBall();
				lbl_gamebar.setBounds(gamebar.getX_pos(), 340, gamebar.smallBar() , 15);
				break;
			case(5):
				this.remove(lblBall5);
				PlayerScore.looseBall();
				lbl_gamebar.setBounds(gamebar.getX_pos(), 340,gamebar.smallBar() , 15);
				break;
		}
		
	}
}
	
