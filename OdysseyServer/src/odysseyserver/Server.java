
package odysseyserver;

/**
 *
 * @author Daniel
 */
//import Server.Server;
import ADT.BinarySearch_Tree;
import ADT.ObjectSong;
import ADT.User;
import Receivers.Song;
import Receivers.logu;
import Receivers.usuario;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXB;
import org.apache.commons.io.FileUtils;
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
        private int large;
        
    public Server(){
        this.pathUsers = "Users";
        
        this.usersTree = new BinarySearch_Tree();
        this.large = 20001;
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
                                    
                                    output.writeUTF("true");
                                    loginInput = socket.getInputStream();
                                    
                                    if(readMessage(loginInput).equals("list")){
                                        String send = "*";
                                        ObjectSong temp = currentUser.getSongsList().getHead();
                                        
                                        
                                        while(temp.getNext()!=null){
                                            send+=temp.getTitle()+"@"+temp.getArtist()+"@"+temp.getAlbum()+"/";
                                            temp = temp.getNext();
                                        } send+=temp.getTitle()+"@"+temp.getArtist()+"@"+temp.getAlbum();
                                        output.writeUTF(send);
                                        
                                    }
                                
                                }else{
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
                            this.currentUser = usersTree.getUser(us.getUserName());
                            saveInDisc();

                            break;
                            
                        case "addSong":
                            
                            message = "";
                            String chunks = "";
                            output.writeUTF("send");
                            System.out.println("addSong>send");  
                            System.out.println("large: "+ large);
                            int cont = 0;
                            
                            InputStream addSongInput = socket.getInputStream();
                            chunks += readMessage(addSongInput);
                            
                            while(true ){
                                
                                String part = readMessage(addSongInput);
                                System.out.println(part);
                                System.out.println(cont);
                                chunks += part;
                                
                                if(part.contains("</Song>")){
                                    System.out.println("llego a finish");
                                    break;
                                } 
                                
                                cont++;
                                addSongInput = socket.getInputStream();
                            }
                            
                            System.out.println("termino");
                            if (chunks.charAt(0)!='<')
                            {
                                chunks = "<"+chunks;
                            }
                            
                            String pattern = "[^"
                    + "\u0009\r\n"
                    + "\u0020-\uD7FF"
                    + "\uE000-\uFFFD"
                    + "\ud800\udc00-\udbff\udfff"
                    + "]";
                            chunks = chunks.replaceAll(pattern, "");
                            
                            chunks = chunks.substring(chunks.indexOf("<"),chunks.lastIndexOf(">")+1);
                            
                            Song song = JAXB.unmarshal(new StringReader(chunks), Song.class);
                            
                            currentUser.getSongsList().add( song.getTitle(),
                                    song.getArtist(), song.getAlbum(), song.getYear(), song.getLetra(), song.getGenre());
//                            System.out.println(song.getSong());
//                            System.out.println(song.getTitle());
//                            System.out.println(song.getArtist());
//                            System.out.println(song.getAlbum());
//                            System.out.println(song.getYear());
//                            System.out.println(song.getLetra());
//                            System.out.println(song.getGenre());
                            saveInDisc();

                            makeSongFile(song.getTitle()+".mp3",song.getSong());
       
                            break;
                            
                        case "play":
                            
                            output.writeUTF("send");
                            
                            
                            //Busca la cancion enviada desde el cliente
//                            InputStream playInput = socket.getInputStream();
//                            
//                            String nameSon = currentUser.getSongsList().findV(readMessage(playInput));
//                            File filee = new File(pathUsers+"/Dan/"+nameSon+".mp3");
                            
                            
                            File file = new File(pathUsers+"/Dan/Welcom to the Jungle.mp3");
                            
                            InputStream playInput = socket.getInputStream();
                            if(readMessage(playInput).equals("len")){
                                
                            long indice = 50000;
                            long veces = file.length()/indice;
                            long ultimo = file.length() - veces*indice;                                
                                
                                
                                output.writeUTF( String.valueOf("-"+file.length()+"@"+String.valueOf(veces)+"@"+String.valueOf(ultimo)));
                            }                              
                            
                            
                            
                            


                            
                            InputStream fileInput = new FileInputStream(file);
                            
                            BufferedInputStream bf = new BufferedInputStream(fileInput);
                            
                            byte[] nuevo = new byte [50000];
                            
                                                          

                            int byteread = 0;
                            int cc = 0;
                            while(( byteread = fileInput.read(nuevo,0,nuevo.length) ) != -1  ){
                                

                                    System.out.println(cc);
                                    output.write(nuevo,0,byteread);
                                  cc++;                              
                                
                            }
                            break;
                            
                            
                        case "find":
                            
                            output.writeUTF("send");
                            
                            InputStream findInput = socket.getInputStream();
                            
                            String songFinder = readMessage(findInput);
                            //otra manera haciendo la busqueda en la carpeta y convirtiendo el file en base64
                            usersTree.getUser(currentUser.getId()).getSongsList().getNode(songFinder).getTitle();
                            
                            
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
                            } catch (JSONException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }      
    }

    public String readMessage(InputStream in) throws IOException{
                    String inputMessage = "";

                    byte[] data = new byte[large];
                    int cont = in.read(data);
                    for(int i = 0 ; i< cont;i++){
                        inputMessage += (char)data[i];
                    }
                    
                    inputMessage= inputMessage.replaceAll("[\r\n]+ ","");   
//                    System.out.println(inputMessage); 
                    return inputMessage;
    }
    public void init() throws FileNotFoundException, IOException, JSONException{

             BufferedReader r = new BufferedReader(
                                new FileReader(pathUsers+"/UsersList.txt"));
             
             String ussers = r.readLine();
             
             if(!ussers.isEmpty()){
             String b []= ussers.split("@");       
             for(int i =0; i<b.length; i++){
                BufferedReader reader = new BufferedReader(
                                        new FileReader(pathUsers+"/"+b[i]+"/DataJson.txt"));
                JSONObject json = new JSONObject(reader.readLine());
                
                usersTree.insert(json.getString("id"), 
                                     json.getString("password"), 
                                     json.getInt("age"), 
                                     json.getString("name"), 
                                     json.getString("lastname"), 
                                     json.getString("friends"), 
                                     json.getString("genres"));
                
                BufferedReader rr = new BufferedReader(
                        new FileReader(pathUsers+"/"+b[i]+"/Songs.txt"));
                
                
                
                String songs = rr.readLine();
                
                if(songs!= null){
                JSONObject jlist = new JSONObject(songs);
                int cont = jlist.getInt("NUMBER");
                for(int j = 1 ; j< cont ; j++){
                    
                JSONObject jlll = new JSONObject(jlist.getString("SONGS"));
                JSONObject jsong = new JSONObject(jlll.getString("Song"+j));
   
                
                usersTree.getUser(b[i]).getSongsList().add( 
                                                jsong.getString("title"), 
                                                jsong.getString("artist"), 
                                                jsong.getString("album"), 
                                                jsong.getInt("year"), 
                                                jsong.getString("lyrics"), 
                                                jsong.getString("genre"));
                }

                
                
                }
                
             }
             }
          
    }
    
            public void saveInDisc() throws IOException, JSONException{
                try {
                    PrintWriter print = new PrintWriter(
                                        new BufferedWriter(
                                        new FileWriter(
                                        new File(pathUsers+"/UsersList.txt"))));
                    
                    
                    String ussers = usersTree.getResult();
                    print.write(ussers);
                    print.close();
                    
                    String b []= ussers.split("@");
                    for(int i =0;i<b.length;i++){
                        
                        File userFile = new File(pathUsers+"/"+b[i]);
                        userFile.mkdir();
                        
                        try (BufferedWriter bw = new BufferedWriter(
                                                 new FileWriter(
                                                 new File(pathUsers+"/"+b[i]+"/DataJson.txt")))){
                            
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
                            bw.close();
                                            
                        } catch (JSONException ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                        
                    }                    
                    
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if(currentUser != null){
            
                            BufferedWriter bww = new BufferedWriter(
                                                 new FileWriter(
                                                 new File(pathUsers+"/"+currentUser.getId()+"/Songs.txt")));
                            
                            JSONObject finalJson = new JSONObject();
                            JSONObject jsonSongsList = new JSONObject();
                            ObjectSong temp = currentUser.getSongsList().getHead();
                            int cont = 1;
                            while(temp!= null){
                                JSONObject jsonSong = new JSONObject();
                                
                                jsonSong.put("title",temp.getTitle());
                                jsonSong.put("artist",temp.getArtist());
                                jsonSong.put("album",temp.getAlbum());
                                jsonSong.put("year",temp.getYear());
                                jsonSong.put("lyrics",temp.getLetra());
                                jsonSong.put("genre",temp.getGenre());
                                jsonSongsList.put("Song"+cont,jsonSong.toString());
                                temp = temp.getNext();
                                cont++;
                            }
                            finalJson.put("SONGS", jsonSongsList.toString());
                            finalJson.put("NUMBER",cont);
                            bww.write(finalJson.toString());
                            bww.close();             
                }
            }
            

            
            
            public boolean makeSongFile(String filename, String string64 ){
        try {

            //Decode Base64 back to Binary format
            byte[] decodedBytes = Base64.getDecoder().decode(string64);


            FileUtils.writeByteArrayToFile(new File(pathUsers + "/"+currentUser.getId()+"/"+filename), decodedBytes);

            return true;
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }                
            }

}

