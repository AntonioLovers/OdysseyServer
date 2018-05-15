
package odysseyserver;

import ADT.BinarySearch_Tree;
import ADT.User;
import Receivers.Song;
import com.thoughtworks.xstream.XStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;
/**
 *
 * @author daniel
 */
public class Main {
        

    public static void main(String[] args) throws IOException, JSONException, URISyntaxException {
        
        Server server =  new Server();
        server.init();
        server.run();  
        
//        File f = new File("Users","newFile");
//        f.mkdir();
//        File f2 = new File("Users/newFile","HOLA");
//        
//        System.out.println(f);
//        
//       BufferedWriter bw = new BufferedWriter(
//                         new FileWriter(f2));  
//       bw.write("hola mundo:" + f2.toURI().toURL().toString());
//       bw.close();
       

//f.delete();
//f2.delete();

//          Song s = new Song("bytes", "gods plan", "drake", "no c bro", 2017, "she said do you love me?", "house");
//          XStream xstream = new XStream();
//          xstream.autodetectAnnotations(Boolean.TRUE);
//          System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+xstream.toXML(s));

          


//        String pathUsers = "C:\\Users\\Daniel\\Desktop\\PROGRAMACION\\JAVA\\"
//                + "PROYECTOS2018\\Odyssey\\OdysseyServer\\Users\\";
//        File f = new File(pathUsers+"directorio");
//        f.mkdir();
//        
//                BufferedReader reader = new BufferedReader(
//                                        new FileReader(pathUsers+"DaniC"+"\\DataJson.txt"));
//                String json = reader.readLine();
//                System.out.println(json);
//            JSONObject json = new JSONObject();
//            json.put("id","Garza");
//            json.put("age",18);
//            json.put("pass","meme22112");
//            System.out.println(json.toString());
            
            


//          BinarySearch_Tree tree = new BinarySearch_Tree();
//          tree.insert("ana", "1234", 18, "daniel", "garcia", "daniel", "rock");
//          tree.insert("aan", "1234", 18, "daniel", "garcia", "daniel", "rock");
//          tree.insert("melany", "1234", 18, "daniel", "garcia", "daniel", "rock");
//          tree.insert("naa", "1234", 18, "daniel", "garcia", "daniel", "rock");
//          tree.insert("zzzzzz", "1234", 18, "daniel", "garcia", "daniel", "rock");
//          tree.see();
//          System.out.println(tree.getInorder());
//          System.out.println(tree.getResult());

//    
//          
//          String b []= result.split("@");
//        for (String b1 : b) {
//            System.out.println(b1);
//        }
//          System.out.println(tree.convertTOBytes("b"));
//          System.out.println(tree.convertTOBytes("c"));
//          System.out.println(tree.convertTOBytes("a"));
//          tree.see();
//          tree.remove("ana");
//          tree.see();

          
          
    }
    
}
