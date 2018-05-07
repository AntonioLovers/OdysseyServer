
package odysseyserver;

/**
 *
 * @author Daniel
 */
//import Server.Server;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
        private JSONObject jsonUsser;
        

    @Override
    public void run(){
        
            try {

                System.out.println(Inet4Address.getLocalHost().getHostAddress());
                ServerSocket server = new ServerSocket(9090);

                System.out.println("Waiting client...");
                Socket socket = server.accept();
                

                output = new DataOutputStream(socket.getOutputStream());
                
                System.out.println("Client connected !");
                output.writeUTF("Welcom to Server! you are online.. ");
                
                
                String message = "";
  
                while(true){
                    
                    InputStream in = socket.getInputStream();
                    byte [] data = new byte[100];
                    int cont = in.read(data);
                    System.out.println(cont);
                    for(int i = 0 ; i< cont;i++){
                        message += (char)data[i];
                    }
                    System.out.println(message);
                    jsonUsser = new JSONObject(message);
                    String method = jsonUsser.getString("code");
                    
                    
                    
                    
                    
                    switch(method){
                        case "login":
                            if("Daniel".equals(jsonUsser.getString("name")) && "1234".equals(jsonUsser.getString("password")))
                            output.writeUTF("true");
                            else
                                output.writeUTF("no escribio name:daniel password:1234 , no inicia sesion");
                            break;
                        default:
                            System.out.println("no escribio name:daniel passwor:1234");
                            output.writeUTF("no escribio name:daniel passwor:1234");
                            break;
                    }                    
                    
                    
                    message = "";
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                } } catch (UnknownHostException ex) {
                                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (IOException ex) {
                                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (JSONException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }


        
    }
    
}

