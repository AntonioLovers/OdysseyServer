/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Receivers;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author Daniel
 */
@XStreamAlias("song")
@XmlRootElement
public class song {
    
    @XStreamAlias("name")
    @XmlElement(name = "name")
    private String nameR;
    
    @XStreamAlias("cancion")
    @XmlElement(name = "cancion")
    private String songR;
    
    @XStreamAlias("artist")
    @XmlElement(name = "artist")
    private String artistR;
    
    @XStreamAlias("album")
    @XmlElement(name = "album")
    private String albumR;
    
    @XStreamAlias("año")
    @XmlElement(name = "año")
    private int ageR;
    
    @XStreamAlias("letra")
    @XmlElement(name = "letra")
    private String letraR;
    
    @XStreamAlias("genero")
    @XmlElement(name = "genero")
    private String generoR;
    
    public song(String song ,String name, String artist , String album , 
            int age , String letra,String genero){
        
        this.songR = song;
        this.nameR = name;
        this.artistR = artist;
        this.albumR = album;
        this.ageR = age;
        this.letraR = letra;
        this.generoR = genero;
    }
    @XmlElement
    public String getGenero() {
        return generoR;
    }

    public void setGeneroR(String genero) {
        this.generoR = genero;
    }
    @XmlElement
    public String getName() {
        return nameR;
    }

    public void setName(String name) {
        this.nameR = name;
    }
    @XmlElement
    public String getSong() {
        return songR;
    }

    public void setSongR(String song) {
        this.songR = song;
    }
    @XmlElement
    public String getArtist() {
        return artistR;
    }

    public void setArtist(String artist) {
        this.artistR = artist;
    }
    @XmlElement
    public String getAlbum() {
        return albumR;
    }

    public void setAlbum(String album) {
        this.albumR = album;
    }
    @XmlElement
    public int getAge() {
        return ageR;
    }

    public void setAge(int age) {
        this.ageR = age;
    }
    @XmlElement
    public String getLetra() {
        return letraR;
    }

    public void setLetra(String letra) {
        this.letraR = letra;
    }
    
}
