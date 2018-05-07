
package odysseyserver;

/**
 *
 * @author Daniel
 */
//import Server.Server;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;


/**
 *
 * @author daniel
 */
public class Server implements Runnable {
        private static DataOutputStream output;

    @Override
    public void run(){
        
            try {

                System.out.println(Inet4Address.getLocalHost().getHostAddress());
                ServerSocket server = new ServerSocket(9090);

                System.out.println("Waiting client...");
                Socket socket = server.accept();
                System.out.println("llego el hpta");

                output = new DataOutputStream(socket.getOutputStream());
                System.out.println("Client connected !");
                output.writeUTF("Server: Estas conectado ! envianos un mensaje..");
                DataInputStream input;

                String message;
                while(true){
                    input = new DataInputStream(socket.getInputStream());
                    message = input.readUTF();
                    switch(message){
                        case "Musica":
                            System.out.println("eligió el comando de musica");
                            output.writeUTF("Recibimos tu mensaje de 'Musica' ");
                            break;
                        default:
                            System.out.println("no eligió el comando de musica");
                            output.writeUTF("Recibimos un mensaje tuyo pero sin el commando 'Musica'");
                            break;
                    }
                } } catch (UnknownHostException ex) {
                                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                            }


        
    }
    
}

