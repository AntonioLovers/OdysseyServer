/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ussers;

import com.google.gson.Gson;
import org.json.JSONObject;


/**
 *
 * @author Daniel
 */
public class Usser {
    private String id;
    public Usser(String id){
        this.id = id;
        Gson g = new Gson();    
    }
    
    public void init(JSONObject json){
        for(int i = 0; i<json.length();i++){
            
        }
        
    }
    public String getId(){
        return this.id;
    }
}
