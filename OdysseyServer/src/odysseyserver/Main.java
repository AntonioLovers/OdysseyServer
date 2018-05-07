
package odysseyserver;

import java.io.IOException;
import org.json.JSONException;
/**
 *
 * @author daniel
 */
public class Main {
        

    public static void main(String[] args) throws IOException, JSONException {
        Server server =  new Server();
        server.run();  
        
//        String a = "a";
//        System.out.println(a.charAt(0));
//        int cont = 0;
//        for(int i = 0 ; i<a.length();i++){
//            cont+= (byte)a.charAt(i);
//        }

    }
    
}
