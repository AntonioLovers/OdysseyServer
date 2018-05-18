/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daniel
 */
//@XStreamAlias("song")

public class ObjectSong {
    
  
    private String titleR;
    private String artistR;
    private String albumR;
    private int yearR;
    private String letraR;
    private String genreR;
    
    private ObjectSong next;
    
    public ObjectSong(){
        
    }
    public ObjectSong(String name, String artist , String album , 
            int year , String lyrics,String genre){
        
        this.titleR = name;
        this.artistR = artist;
        this.albumR = album;
        this.yearR = year;
        this.letraR = lyrics;
        this.genreR = genre;
        this.next = null;
    }
    public String getTitle() {
        return titleR;
    }

    public void setTitle(String title) {
        this.titleR = title;
    }

    public String getArtist() {
        return artistR;
    }

    public void setArtist(String artist) {
        this.artistR = artist;
    }
    public String getAlbum() {
        return albumR;
    }

    public void setAlbum(String album) {
        this.albumR = album;
    }
    public int getYear() {
        return yearR;
    }

    public void setYear(int year) {
        this.yearR = year;
    }
    public String getLetra() {
        return letraR;
    }

    public void setLetra(String letra) {
        this.letraR = letra;
    }
    public String getGenre() {
        return genreR;
    }

    public void setGenre(String genre) {
        this.genreR = genre;
    }

    public ObjectSong getNext() {
        return next;
    }
    public void setNext(ObjectSong next) {
        this.next = next;
    }  
}
