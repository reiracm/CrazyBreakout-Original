package MainWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;



public class Cliente {
	//Variable privada para establecer la conexión con el servidor
	private Socket cliente;	
	//Variable booleana para iniciar y terminar el hilo
	private boolean isAlive = true;
	
	/**
	 * El contructor de cliente posee dos parámetros que son la dirección IP y el puerto.
	 * Dentro del constructor se crea el nuevo Socket cliente que espera respuesta por parte
	 * del servidor.
	 * @param ip
	 * @param puerto
	 */
	public Cliente(String ip, int puerto){
		try {
			cliente = new Socket(ip, puerto);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		inputData();
		
		startConsole();
		
	}
	
	public Cliente(Socket socket){
		cliente = socket;
		
		inputData();
		
	}
	/**
	 * El método inputData recibirá datos por parte del servidor y
	 * estos serán leídos por el buffer y serán impresos en la
	 * consola para probar que si se están recibiendo los datos
	 * del servidor.
	 */
	public void inputData(){
		Thread tIn = new Thread(){
			public void run(){
				try {
					//Recibe los datos enviados por la clase servidor
					InputStream in = cliente.getInputStream();
					//Realiza las lecturas de los flujos de 
					//caracteres que está recibiendo del servidor
					BufferedReader inBuff = new BufferedReader(new InputStreamReader(in));
					//Este hilo se encargara de reproducir en la consola lo que está recibiendo
					//por parte del servidor
					while(isAlive){
						Thread.sleep(10);
						if(in.available() > 0){
							System.out.println(inBuff.readLine());
						}
					}
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					isAlive = false;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		tIn.start();
	}
	
	/**
	 * Método utilizado para enviar datos al servidor
	 * @param msg
	 */
	public void sendMessage(String msg){
		try {
			PrintWriter send = new PrintWriter(cliente.getOutputStream(),true);
			send.println(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * En este método el InputStream será en la consola y el buffer leerá lo que
	 * está en la consola de EClipse
	 */
	public void startConsole(){
		Thread t = new Thread(){
			public void run(){
				InputStream in = System.in;
				BufferedReader brIn = new BufferedReader(new InputStreamReader(in));
				
				while(isAlive){
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						if(in.available() > 0){
							String strIn = brIn.readLine();
							sendMessage(strIn);
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		t.start();
	}
	
	/**
	 * El cliente tendrá el IP 0.0.0.0 y el puerto 12345, y esperará
	 * algún mensaje para enviar al servidor
	 * @param args
	 */
	public static void main(String[] args){
		Cliente cliente = new Cliente("0.0.0.0", 12345);
		
	}
}
