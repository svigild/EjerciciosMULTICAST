
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
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
public class MultiCastCliente {
    
    public static void main(String[]args){
        try {
            //Creo el multicast socket
            MulticastSocket s = new MulticastSocket(10000);
            
            //Creo el grupo
            InetAddress group = InetAddress.getByName("231.0.0.1");
            
            //Uno el cliente al grupo
            s.joinGroup(group);
            
            String mensaje = new String();
            
            while(!mensaje.equals("salir")){
            byte[] buffer = new byte[25];
                DatagramPacket datagrama = new DatagramPacket(buffer, buffer.length);
                //Recibo el mensaje
                s.receive(datagrama);
                mensaje =  (new String(buffer)).trim();
                
                System.out.println(mensaje);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(MultiCastCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
