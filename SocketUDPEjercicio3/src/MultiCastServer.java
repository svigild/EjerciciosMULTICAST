
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
public class MultiCastServer {
    
    public static void main(String[]args){
        System.out.println("Arrancando el servidor multicast...");
        
        try {
            //Creo el socket multicast
            MulticastSocket s = new MulticastSocket();
            
            //Creo el grupo
            InetAddress group = InetAddress.getByName("231.0.0.1");
            
            // Creamos un datagrama vacío en principio:
            byte[] vacio = new byte[0];
            DatagramPacket dgp = new DatagramPacket(vacio, 0, group,10000);
            
            //Leo por teclado
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String linea;
            
            do {
                //Leemos de la entrada estandar para evitar bucles infinitos
                System.out.print(">"); 
                linea = br.readLine();
                //Creamos el buffer a enviar
                byte[] buffer = linea.getBytes();//Pasamos los datos al datagrama
                dgp.setData(buffer);
                //Establecemos la longitud
                dgp.setLength(buffer.length);
                // las dos ultimas instrucciones sería equivalente a
                // dgp = new DatagramPacket(buffer, buffer.length, group,10000);
            
                //Y por último enviamos:            
                s.send(dgp);
            } while (!linea.equals("salir"));
        } catch (IOException ex) {
            Logger.getLogger(MultiCastServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
