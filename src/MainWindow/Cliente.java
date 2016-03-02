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
	private Socket cliente;	
	private boolean isAlive = true;
	
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
	
	public void inputData(){
		Thread tIn = new Thread(){
			public void run(){
				try {
					InputStream in = cliente.getInputStream();
					BufferedReader inBuff = new BufferedReader(new InputStreamReader(in));
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

	public void sendMessage(String msg){
		try {
			PrintWriter send = new PrintWriter(cliente.getOutputStream(),true);
			send.println(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public static void main(String[] args){
		Cliente cliente = new Cliente("0.0.0.0", 12345);
		
	}
}
