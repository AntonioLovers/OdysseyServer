
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
    }
    
}
