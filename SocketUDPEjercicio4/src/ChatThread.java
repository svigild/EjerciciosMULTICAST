
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author SERGIO
 */
public class ChatThread extends Thread {
    
    MulticastSocket socket;

    public ChatThread(MulticastSocket socket) {
        this.socket = socket;
    }
    
    @Override
    public void run(){
        String mensaje = new String();
        while(!mensaje.equals("salir")){
            byte[] buffer = new byte[25];
            DatagramPacket datagrama = new DatagramPacket(buffer, buffer.length);
            try {
                socket.receive(datagrama);
            } catch (IOException ex) {
                Logger.getLogger(ChatThread.class.getName()).log(Level.SEVERE, null, ex);
            }
            mensaje = (new String(buffer)).trim();
            System.out.println(mensaje);
        }
    }
}
