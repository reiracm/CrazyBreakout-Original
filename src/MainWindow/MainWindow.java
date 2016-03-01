package MainWindow;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import MainWindow.PanelInicial;
/**
 * 
 * @author Yenira Chacón
 *
 */
public class MainWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Inicia la aplicación
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					MainWindow frame = new MainWindow();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crea el frame
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 420);
		contentPane = new PanelInicial(this);
		setContentPane(contentPane);
		setVisible(true);
	}
	/**
	 * Método para el cambio de ventana
	 * @param panel
	 */
	public void changeContentPane(JPanel panel){
		contentPane = panel;
		setContentPane(contentPane);
		setVisible(true);
	}

}
