
package odyssey;

//import Client.Client;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

/**
 *
 * @author Daniel
 */
public class Main {
        private static DataOutputStream output;
    public static void main(String[] args) throws IOException {
//       Client client = new Client();
//       client.initClient();
        Scanner s = new Scanner(System.in);
        
        System.out.println("conectando..");
        Socket socket = new Socket("127.0.0.1",3000);
        DataInputStream input = new DataInputStream(socket.getInputStream());
        String message  = input.readUTF();
        System.out.println(message);
        output =  new DataOutputStream(socket.getOutputStream());
        String mensaje = "";
        boolean flag = true;
        while(flag){
            
//            input = new DataInputStream(socket.getInputStream());
//            message = input.readUTF();
//            System.out.println(message);            
            mensaje = s.nextLine();
            if("salir".equals(mensaje)){
                flag = false;
            }
            output.writeUTF(mensaje);
            input = new DataInputStream(socket.getInputStream());
            message = input.readUTF();
            System.out.println(message);

            
        }
        
    }
    
}
