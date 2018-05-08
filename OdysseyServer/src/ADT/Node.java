package ADT;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Clase Node para almacenamiento de información
 * @author Daniel
 */
public class Node {
    private Node previous;
    private Node next;
    private String value;
    
    
    
    public Node(){
        this("");
    }
    /**
     * 
     * @param value - es el valor a almacenar
     */
    public Node(String value){
        this.value = value;
        this.next = null;
        this.previous = null;
     
    }
    /**
     * 
     * @param value - edita el valor del Node
     */
    public void setValue(String value){
        this.value =  value;
    }
    /**
     * 
     * @return - retorna el valor dentro del nodo 
     */
    public String getValue(){
        return this.value;
    }
    /**
     * 
     * @return retorna la posicion del siguiente Node
     */
    public Node getNext(){
        return this.next;
    }
    public void setNext(Node node){
        this.next = node;
    }
    public void setPrevious(Node node){
        
        this.previous = node;
    }
    public Node getPrevious(){
    return this.previous;
    }

    
}
