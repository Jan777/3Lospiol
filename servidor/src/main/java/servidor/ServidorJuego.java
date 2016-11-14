package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

import mapa.Mapa;

public class ServidorJuego {
	
    private final int puerto = 2028;
    private final int noConexiones = 20;
    private ServerSocket servidor;
    private Mapa map;
    private LinkedList<Socket> usuarios = new LinkedList<Socket>();
 
   
    public void escuchar() throws IOException{
        try {
            
             servidor = new ServerSocket(puerto,noConexiones);
             map = new Mapa();
            
            while(true){
                System.out.println("Escuchando....");
                
                Socket cliente = servidor.accept();
                usuarios.add(cliente);
                
                Runnable  run = new HiloServidor(cliente,usuarios,map);
                Thread hilo = new Thread(run);
                hilo.start();
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{        	
			servidor.close();
        } 
    }
    
    public static void main(String[] args) {
    	ServidorJuego servidor= new ServidorJuego();
        try {
			servidor.escuchar();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}