/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Receivers;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daniel
 */
@XmlRootElement
public class usuario {

        @XmlElement(name = "username")
        String usId;
        @XmlElement(name = "password")
        String usPassword;
        @XmlElement(name = "name")
        String usName;
        @XmlElement(name = "lastname")
        String usLastName;
        @XmlElement(name = "age")
        int usAge;
        @XmlElement(name = "genres")
        String usGenres;
        @XmlElement(name = "friends")
        String usFriends;
        
        
    public usuario(){
          
    }
    public usuario(String username,String password,
                   String name,String lastname,
                   int age,String genres,String friends){
        
        this.usId = username;
        this.usPassword = password;
        this.usName = name;
        this.usLastName = lastname;
        this.usAge = age;
        this.usGenres = genres;
        this.usFriends = friends;
    }
    @XmlElement
    public String getUserName(){
        return this.usId;
    }
    @XmlElement
    public String getPassWord(){
        return this.usPassword;
    }  
    @XmlElement    
    public String getName(){
        return this.usName;
    }
    @XmlElement    
    public String getLastName() {
        return usLastName;
    }
    @XmlElement
    public int getAge() {
        return usAge;
    }
    @XmlElement
    public String getGenres() {
        return usGenres;
    }
    @XmlElement
    public String getFriends() {
        return usFriends;
    }
    
}
