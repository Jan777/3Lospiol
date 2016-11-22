package gui;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;

import castas.Paladin;
import personaje.Personaje;
import personaje.PersonajeDibujable;
import razas.Elfo;

public class Juego extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Juego(int num) throws UnknownHostException, IOException{
        
        setTitle("Juego 2D con Java ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        setLocationRelativeTo(null);
        setResizable(false);
       
        add(new Jugador(num));
       
        setVisible(true);
    }
	
public Juego(Socket sock, String id, PersonajeDibujable p, Personaje pb) throws UnknownHostException, IOException{
        
        setTitle("Juego 2D con Java ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        setLocationRelativeTo(null);
        setResizable(false);
       
        add(new Jugador( sock,  id,  p,  pb));
       
        setVisible(true);
    }
    
    public static void main(String args[]) throws UnknownHostException, IOException{
        new Juego(2);
        //new Juego(3);
        /*
        Socket cliente = new Socket("localhost", 2028);
        String id = "jugador1";
        new Juego(cliente,id,new PersonajeDibujable(id,"elfoP"),new Elfo(new Paladin(),id,"elfoP"));
        //*/
    }
}
