package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

import com.google.gson.Gson;

import mapa.Mapa;
import mensaje.Mensaje;
import personaje.Personaje;

public class HiloServidor implements Runnable{
    
    private Socket socket;
    private String idCliente;
    private DataOutputStream out;
    private DataInputStream in;
    private Mapa map;
    private Mensaje mensaje;
    private Gson gson = new Gson();
        
    private LinkedList<Socket> usuarios = new LinkedList<Socket>();
        
    public HiloServidor(Socket soc,LinkedList<Socket> users,Mapa mapa) throws IOException{
        socket = soc;
        usuarios = users;
        map = mapa ;
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
    }
    
    public void interpretarMensaje() throws IOException
    {
    	if(mensaje.getNombreMensaje().equals("Cargar"))
    	{
    		Personaje pers = gson.fromJson(mensaje.getJson(), Personaje.class);
    		idCliente = pers.getID();
    		map.agregarDibujable(pers);
    		String respuesta = gson.toJson(map);
    		mensaje = new Mensaje("Cargado",respuesta);
    		responder();
    	}
    	
    	if(mensaje.getNombreMensaje().equals("ActualizarMapa"))
    	{
    		Personaje pers = gson.fromJson(mensaje.getJson(), Personaje.class);
    		map.actualizarMapa(pers);
    		String respuesta = gson.toJson(map);
    		mensaje = new Mensaje("MapaActualizado",respuesta);
    		responder();
    	}
    }
    
    public void responder() throws IOException
    {
    	String salida = gson.toJson(mensaje);
    	out.writeUTF(salida);
    	out.flush();
    }
 
 
    @Override
    public void run() {
        try {            
            String entrada = in.readUTF();
            mensaje = gson.fromJson(entrada, Mensaje.class);
            interpretarMensaje();            
 
            while(true){
            	 entrada = in.readUTF();
                 mensaje = gson.fromJson(entrada, Mensaje.class);
                 interpretarMensaje();    
                
            }
        } catch (Exception e) {
        			map.quitar(idCliente);
                    usuarios.remove(socket);
            }
            try {
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            
        }
    }

 
    