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

    public String getGeneroR() {
        return generoR;
    }

    public void setGeneroR(String generoR) {
        this.generoR = generoR;
    }

    public String getNameR() {
        return nameR;
    }

    public void setNameR(String nameR) {
        this.nameR = nameR;
    }

    public String getSongR() {
        return songR;
    }

    public void setSongR(String songR) {
        this.songR = songR;
    }

    public String getArtistR() {
        return artistR;
    }

    public void setArtistR(String artistR) {
        this.artistR = artistR;
    }

    public String getAlbumR() {
        return albumR;
    }

    public void setAlbumR(String albumR) {
        this.albumR = albumR;
    }

    public int getAgeR() {
        return ageR;
    }

    public void setAgeR(int ageR) {
        this.ageR = ageR;
    }

    public String getLetraR() {
        return letraR;
    }

    public void setLetraR(String letraR) {
        this.letraR = letraR;
    }
    
}
