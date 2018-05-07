/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

import Ussers.Usser;

/**
 *
 * @author Daniel
 */
public class LinkedList {
    private int size;
    private Node head;
    private Node tail;
    
    public class Node{
        private Node next;
        private Usser value;
        public Node(Usser u){
            this.next = null;
            this.value = u;
            
        }
        public Usser getValue(){
            return this.value;
        }
        public Node getNext(){
            return this.next;
            
        }
        public void setNext(Usser u){
            this.next = new Node(u);
        }
    }
    
    public LinkedList(){
        this.head = this.tail = null;
        this.size = 0;
    }
    public boolean isEmpty(){
        return this.head == null;
    }
    
}
