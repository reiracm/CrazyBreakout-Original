package MainWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	private String valid = "[0-9]+:[0-9A-Za-z\\s]+";
	
	private ServerSocket server;
	private Cliente clientes[];
	private BufferedReader in;
	boolean isAlive = true;
	private int index = 0;
	
	public Servidor(int clNum){
		//Crear el server
		clientes = new Cliente [clNum];
		try {
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
	
	public static void main(String[] args){
		Servidor server = new Servidor(12345);
	}
}


