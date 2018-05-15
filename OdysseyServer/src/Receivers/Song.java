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
//@XStreamAlias("song")
@XmlRootElement
public class Song {
    
//    @XStreamAlias("titleX")
    @XmlElement(name = "title")
    private String titleR;
    
//    @XStreamAlias("song64X")
    @XmlElement(name = "song64")
    private String songR;
    
//    @XStreamAlias("artistX")
    @XmlElement(name = "artist")
    private String artistR;
    
//    @XStreamAlias("albumX")
    @XmlElement(name = "album")
    private String albumR;
    
//    @XStreamAlias("yearX")
    @XmlElement(name = "year")
    private int yearR;
    
//    @XStreamAlias("lyricsX")
    @XmlElement(name = "lyrics")
    private String letraR;
    
//    @XStreamAlias("genreX")
    @XmlElement(name = "genre")
    private String genreR;
    
    private Song next;
    
    public Song(){
        
    }
    public Song(String song ,String name, String artist , String album , 
            int year , String lyrics,String genre){
        
        this.songR = song;
        this.titleR = name;
        this.artistR = artist;
        this.albumR = album;
        this.yearR = year;
        this.letraR = lyrics;
        this.genreR = genre;
        this.next = null;
    }
    @XmlElement
    public String getTitle() {
        return titleR;
    }

    public void setTitle(String title) {
        this.titleR = title;
    }
    @XmlElement
    public String getSong() {
        return songR;
    }

    public void setSong(String song) {
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
    public int getYear() {
        return yearR;
    }

    public void setYear(int year) {
        this.yearR = year;
    }
    @XmlElement
    public String getLetra() {
        return letraR;
    }

    public void setLetra(String letra) {
        this.letraR = letra;
    }
    @XmlElement
    public String getGenre() {
        return genreR;
    }

    public void setGenre(String genre) {
        this.genreR = genre;
    }

    public Song getNext() {
        return next;
    }

    public void setNext(Song next) {
        this.next = next;
    }


    
    
}
