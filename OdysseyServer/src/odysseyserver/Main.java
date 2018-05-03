
package odysseyserver;

//import Server.Server;
//import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.net.*;

/**
 *
 * @author daniel
 */
public class Main {
        private static DataOutputStream output;

    public static void main(String[] args) throws IOException {
        
//        Server server = new Server();
//        server.initServer();
          System.out.println(Inet4Address.getLocalHost().getHostAddress());
          ServerSocket server = new ServerSocket(3000);
          
          System.out.println("Waiting client...");
          Socket socket = server.accept();
          
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
          }
          
          
        
    }
    
}
