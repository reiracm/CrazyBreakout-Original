package MainWindow;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * 
 * @author Yenira Chacón
 *
 */
public class PanelInicial extends JPanel {

	JFrame frame;
	
	/**
	 * Crea el panel inicial
	 */
	public PanelInicial(MainWindow frame) {
		super();
		this.frame = frame;
		
		setLayout(null);
		
		//Crea el botón para empezar a jugar
		JButton btnNewButton = new JButton("Jugar");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			
			/**
			 * Cuando se presiona el botón de juego 
			 * aparecerá una ventana con un input, donde ese input
			 * será guardado como el nuevo jugador para asignarle
			 * el puntaje respectivo
			 */
			public void actionPerformed(ActionEvent e) {
				//frame.changeContentPane(new GamePanel(frame));
				String newPlayer = JOptionPane.showInputDialog("Ingrese un nombre de 7 caracteres:");
				
				int confirm = JOptionPane.showConfirmDialog(frame,"¿Confirma este nombre de usuario?", newPlayer, JOptionPane.YES_NO_OPTION);
				if (confirm == JOptionPane.YES_OPTION){
					JOptionPane.showMessageDialog(null, "Bienvenido: " + newPlayer);
					ScoresWindow.addPlayer(newPlayer);
					frame.changeContentPane(new GamePanel(frame));
				}
			}
		});
		btnNewButton.setBounds(204, 255, 117, 25);
		add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("img/ultra.jpg"));
		lblNewLabel.setBounds(0, 0, 520, 400);
		add(lblNewLabel);
		
		//Botón de puntajes
		JButton btnPuntajes = new JButton("Puntajes");
		btnPuntajes.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.changeContentPane(new ScoresWindow());
			}			
		});
		btnPuntajes.setBounds(204, 311, 117, 25);
		add(btnPuntajes);
		
		//Botón de salida
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);				
			}
		});
		btnSalir.setBounds(204, 366, 117, 25);
		add(btnSalir);
	}
}
