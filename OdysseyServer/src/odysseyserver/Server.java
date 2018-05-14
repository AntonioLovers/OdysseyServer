
package odysseyserver;

/**
 *
 * @author Daniel
 */
//import Server.Server;
import ADT.BinarySearch_Tree;
import ADT.User;
import Receivers.logu;
import Receivers.usuario;
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
                    
                                                     
                    switch(message){
                        
                        //FUNCION LOGIN
                        case "login":
                            message = "";
                            output.writeUTF("send");
                            InputStream loginInput = socket.getInputStream();

                            logu code = JAXB.unmarshal(new StringReader(readMessage(loginInput)), logu.class);
                            
                            
                            if(usersTree.contains(code.getName())){
                                User p = usersTree.getUser(code.getName());
                                if(p.getPassword().equals(code.getPassWord())){
                                    this.currentUser = usersTree.getUser(code.getName());
                                    System.out.println("login>true");
                                    
                                    output.writeUTF("true");}else{
                                    System.out.println("login>false");
                                    output.writeUTF("false");
                                }
                                }

                            else{
                                System.out.println("login>false");
                                output.writeUTF("false");
                            }break;
                        case "register":
                            
                            output.writeUTF("("+usersTree.getResult()+")");
                            usersTree.see();
                            System.out.println(usersTree.getResult());
                            break;
                            
                        case "verify":
                            message = "";
                            output.writeUTF("send");
                            

                            
                            InputStream veryfyInput = socket.getInputStream();
                            String verifymessage = readMessage(veryfyInput);
                            if(usersTree.contains(verifymessage)){
                                System.out.println("verify>false");
                                output.writeUTF("false");
                                break;
                            }else{
                                System.out.println("verify>true");
                                output.writeUTF("true");
                                break;
                            }
                            
                        case "signup":
                            message = "";
                            output.writeUTF("send");
                            System.out.println("signup>send");
                            
                            InputStream singupInput = socket.getInputStream();
                            
                            usuario us = JAXB.unmarshal(new StringReader(readMessage(singupInput)), usuario.class); 
                            
                            usersTree.insert(us.getUserName(), us.getPassWord(), 
                                    us.getAge(), us.getName(), us.getLastName(), 
                                    us.getFriends(), us.getGenres());
                            saveInDisc();

                            break;
                            
                        case "addSong":
                            int cont = 0;
                            message = "";
                            output.writeUTF("send");
                            
                            String chunks = "";
                            
                            InputStream addSongInput = socket.getInputStream();
                            chunks += readMessage(addSongInput);
                            
                            while(addSongInput.available() != 0 ){
                                
                                
                                String part = readMessage(addSongInput);
                                System.out.println(cont);
                                System.out.println(part);
                                
                                chunks += part;
                                cont++;
                                addSongInput = socket.getInputStream();
                                
                            } 
                            chunks = chunks.substring(chunks.indexOf("<"),chunks.lastIndexOf(">")+1);
                            Song song = JAXB.unmarshal(new StringReader(chunks),Song.class);
                            break;

                            
                            
                            
                        default:
                            message = "";
                            System.out.println("Default>false");
                            output.writeUTF("false");
                            break;
                    }                    
                    
                                     

                } } catch (UnknownHostException ex) {
                                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                            }catch (IOException ex) {
                                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                            }
            
    }

       
    public String readMessage(InputStream in) throws IOException{
                    String inputMessage = "";
                    byte [] data = new byte[10000];                    
                    int cont = in.read(data);
                    for(int i = 0 ; i< cont;i++){
                        inputMessage += (char)data[i];
                    }
                    
                    inputMessage= inputMessage.replaceAll("[\r\n]+ ","");   
                    
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
                                     json.getString("lastname"), 
                                     json.getString("friends"), 
                                     json.getString("genres"));
             }
        
        
        
    }
    
    
            public void saveInDisc(){
                try {
                    PrintWriter print = new PrintWriter(
                                        new BufferedWriter(
                                        new FileWriter(
                                        new File(pathUsers+"UsersList.txt"))));
                    
                    
                    String ussers = usersTree.getResult();
                    print.write(ussers);
                    print.close();
                    
                    String b []= ussers.split("@");
                    for(int i =0;i<b.length;i++){
                        
                        File userFile = new File(pathUsers+b[i]);
                        userFile.mkdir();
                        
                        try (BufferedWriter bw = new BufferedWriter(
                                                 new FileWriter(
                                                 new File(pathUsers+b[i]+"\\DataJson.txt")))){
                            
                            User uu = usersTree.getUser(b[i]);
                            JSONObject jj = new JSONObject();

                            jj.put("id",uu.getId());
                            jj.put("password",uu.getPassword());
                            jj.put("age",uu.getAge());
                            jj.put("name",uu.getName());
                            jj.put("lastname",uu.getLastName());
                            jj.put("genres",uu.getfGnres());
                            jj.put("friends",uu.getFriends());
                            bw.write(jj.toString());
                        } catch (JSONException ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                        
                    }
                    
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            
             
             
             
            }    
            
    
}

