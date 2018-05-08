
package Manager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author daniel
 */
public class Manager {
    public Manager(){
        
    }
    public String find(String code, String name){
        return "";
    }
    public String sort(String code){
        return "";
    }
    public void singIn(String usser , String password) throws FileNotFoundException, IOException{
        
        File f = new File(usser);
        if(f.exists()){
            BufferedReader br;
            br = new BufferedReader(new FileReader(usser+"//DataJson"));
            String json = br.readLine();
            
//            User u = new User();
            File fjson = new File(usser);
            
        }else{
            System.out.println("Ese usuario no existe, debes crear una cuenta!");
        }
        
    }
    public void addUser(String nick ,String name ,String last, int age ,
                        String gnre , String password,String friends){
        
    }
   public void addSong(){
       
   }
   public void deleteSong(String user , String name){
       
   }
   public void playSong(){
       
   }
   public void upDateSong(){
       
   }
   public void upDateMetaData(){
       
   }
   public void sync_upMetaData(){
       
   }
   public String findFriend(){
       return "";
   }
   public void addFriend(){
       
   }
   public void recommendSong(String user , String name){
       
   }
   public void qualifySong(String user , String name){
       
   }
   
    
    
}
