package MainWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	private String valid = "[0-9]+:[0-9A-Za-z\\s]+";
	//Variable para establecer conexión con el cliente
	private ServerSocket server;
	//ARreglo para una cantidad de clientes ilimitada al servidor
	private Cliente clientes[];
	//Lector de la entrada de datos
	private BufferedReader in;
	//Booleano para iniciar el hilo
	boolean isAlive = true;
	//Indice para agregar nuevos clientes
	private int index = 0;
	
	/**
	 * Este constructor crea el Servidor. Obtiene la dirección IP
	 * y el puerto
	 * @param clNum
	 */
	public Servidor(int clNum){
		//Crear el server
		clientes = new Cliente [clNum];
		try {
			//Crea el nuevo servidor
			server = new ServerSocket(12345);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("puerto: " + 12345 + " ip: " + server.getInetAddress().getHostName());
		Thread t = new Thread(){
			public void run(){
				while(isAlive){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(index < clientes.length){
						try {
							clientes[index] = new Cliente(server.accept());
							index++;
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		};
		t.start();
		startConsole();
	}
	
	/**
	 * Este método posee un hilo dentro del cual ocurre el envío y
	 * lectura de información que viene por parte del cliente. 
	 * 
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
						/**
						 * Mientras la cantidad de bytes sea mayor a cero,
						 * leerá esa información
						 */
						if(in.available() > 0){
							String strIn = brIn.readLine();
							if(strIn.matches(valid)){
								String[] array = strIn.split(":");
								int i = Integer.parseInt(array[0]);
								clientes[i].sendMessage(array[1]);
							}
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
	 * Al servidor se le asigna el puerto 12345
	 * @param args
	 */
	public static void main(String[] args){
		Servidor server = new Servidor(12345);
	}
}


