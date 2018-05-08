
package odysseyserver;

/**
 *
 * @author Daniel
 */
//import Server.Server;
import ADT.BinarySearch_Tree;
import ADT.User;
import Users.logu;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXB;
import org.json.JSONException;
import org.json.JSONObject;


/**
 *
 * @author daniel
 */
public class Server implements Runnable {
        private static DataOutputStream output;
        private User currentUser;
        private String pathUsers;
        private BinarySearch_Tree usersTree;
        
    public Server(){
        this.pathUsers = "C:\\Users\\Daniel\\Desktop\\PROGRAMACION\\JAVA\\"
                + "PROYECTOS2018\\Odyssey\\OdysseyServer\\Users\\";
        this.usersTree = new BinarySearch_Tree();
    }    

    @Override
    public void run(){
        
            try {
              
                System.out.println(Inet4Address.getLocalHost().getHostAddress());
                ServerSocket server = new ServerSocket(9090);

                System.out.println("Waiting client...");
                Socket socket = server.accept();
                

                output = new DataOutputStream(socket.getOutputStream());
                
                System.out.println("Client connected !");
                output.writeUTF("Welcom to Server! you are online..");
                
                
                String message = "";

  
                while(true){
                    
                    //LEYENDO BYTES DEL MENSAJE CLIENTE 
                    InputStream in = socket.getInputStream();
                    message = readMessage(in);  
                    System.out.println(message);
                                                     
                    switch(message){
                        case "login":
                            output.writeUTF("send");
                            InputStream loginInput = socket.getInputStream();
                           
                            
                            
                            logu code = JAXB.unmarshal(new StringReader(readMessage(loginInput)), logu.class);
                            
                            
                            if(usersTree.contains(code.getName())){
                                User p = usersTree.getUser(code.getName());
                                if(p.getPassword().equals(code.getPassWord())){
                                    currentUser = p;
                                    System.out.println("true");
                                    
                                    output.writeUTF("true");}else{
                                    System.out.println("false");
                                    output.writeUTF("false");
                                }
                                }

                            else{
                                System.out.println("false");
                                output.writeUTF("false");
                            break;}
                        default:
                            System.out.println("false");
                            output.writeUTF("false");
                            break;
                    }                    
                    
                    
                    message = "";
                    

                } } catch (UnknownHostException ex) {
                                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                            }catch (IOException ex) {
                                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                            }
            
            finally{
                try {
                    PrintWriter print = new PrintWriter(
                                        new BufferedWriter(
                                        new FileWriter(
                                        new File(pathUsers+"UsersList.txt"))));
                    
                    
                    String ussers = usersTree.getResult();
                    print.write(ussers);
                    print.close();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            
             
             
             
            }
 
    }

    
    
    public String readMessage(InputStream in) throws IOException{
                    String inputMessage = "";
                    byte [] data = new byte[1645];                    
                    int cont = in.read(data);
                    for(int i = 0 ; i< cont;i++){
                        inputMessage += (char)data[i];
                    }
                    
                    inputMessage= inputMessage.replaceAll("[\r\n]+ ","");   
                    System.out.println(inputMessage); 
                    return inputMessage;
    }
    public void init() throws FileNotFoundException, IOException, JSONException{

        
             BufferedReader r = new BufferedReader(
                                new FileReader(pathUsers+"UsersList.txt"));
             
             String ussers = r.readLine();
             String b []= ussers.split("@");       
             for(int i =0; i<b.length; i++){
                BufferedReader reader = new BufferedReader(
                                        new FileReader(pathUsers+b[i]+"\\DataJson.txt"));
                JSONObject json = new JSONObject(reader.readLine());
                
                usersTree.insert(json.getString("id"), 
                                     json.getString("password"), 
                                     json.getInt("age"), 
                                     json.getString("name"), 
                                     json.getString("lastName"), 
                                     json.getString("friends"), 
                                     json.getString("gnres"));
             }
        
        
        
    }
            
    
}

