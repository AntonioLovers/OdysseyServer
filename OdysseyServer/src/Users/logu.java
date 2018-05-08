/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Users;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daniel
 */

@XmlRootElement
public class logu {

        @XmlElement(name = "name")
        String usserName;
        @XmlElement(name = "password")
        String usserPassword;
        
    public logu(){
          
    }
    public logu(String name  , String password){
        this.usserName = name;
        this.usserPassword = password;
    }
    @XmlElement
    public String getName(){
        return this.usserName;
    }
    @XmlElement
    public String getPassWord(){
        return this.usserPassword;
    }       
}
