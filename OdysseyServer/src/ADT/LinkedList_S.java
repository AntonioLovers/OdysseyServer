package ADT;

import Receivers.Song;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Daniel
 */
public class LinkedList_S {
    protected Song head;
    protected int size;
    
    
    /**
     * Este constructor pasa los valores por defecto a la cabeza y tamaño
     */
    public LinkedList_S(){
        this.head = null;
        this.size = 0;
    }
    public Song getHead(){
        return this.head;
    }
    /**
     * El método publico que llama al privado para agregar al final
     * @param song
     * @param name
     * @param album
     * @param age
     * @param letra
     * @param genero
     * @param artist
     */
    public void add(String song ,String name, String artist , String album , 
            int age , String letra,String genero){
        this.ad( song , name,  artist ,  album , 
             age ,  letra, genero);
    }


    /**
     * Este métdoo llama al privado que busca un valor en la lsta
     * @param value - valor a buscar
     * @return - devuelve el valor 
     */
    public String findV(String value){
        String r = this.fdV(value);
        return r;
    }
    /**
     * Método que llama al privado para buscar un valor por su indice
     * @param index - Posición de la cual sacar el valor
     * @return - retorna el valor
     */
    public Object findI(int index){
        Object r = this.fdI(index);
        return r;
    }
    /**
     * Llama al método privado que permite ver la lista en pantalla
     */
    public void see(){
        this.se();
    }
    /**
     * Llama al método privado que elimina el Node con el valor indicado
     * @param value - valor a buscar para eliminar
     */
    public void delete(String value){
        this.del(value);
    }
    /**
     * Llama al método privado que borra un Node por su posición en la lista
     * @param index - posición la cual eliminar
     */
    public void deleteI(int index){
        this.delI(index);
    }
    /**
     * Llama al método privado para obtener la referencia a un Node particular
     * @param value - valor para identificar al Node
     * @return - retorna la referencia al Node
     */
    public Song getNode(String value){
        Song r = this.findNode(value);
        return r;
    }
    /**
     * Llama al método privado para obtener la referencia a un Node particular
     * @param index - Posición de la cual sacar la referencia
     * @return - retorna la referencia al Node
     */
    public Song getNodeI(int index){
        Song r = this.findNodeI(index);
        return r;
    }
    /**
     * añade al final de la lista
     * @param value - valor a añadir
     */
    private void ad(String song ,String name, String artist , String album , 
            int age , String letra,String genero){
        if(isEmpty()){
            head = new Song( song , name,  artist ,  album , 
             age ,  letra, genero);
        }else{
        Song temp = head;
        while(temp.getNext()!=null){
            temp = temp.getNext();
        }Song ultimo = new Song( song , name,  artist ,  album , 
             age ,  letra, genero);
         temp.setNext(ultimo);}
         size++;
    }

    /**
     * Realiza una busqueda por valor 
     * 
     * @param value 
     * @return - Hasta que el valor coincida retorna los datos
     */
    private String fdV(String value){
        Song temp = head;
        
        while(temp.getTitle()!=value){
            temp = temp.getNext();
        }
        return temp.getTitle();
    }
    /**
     * Realiza una busqueda por posición 
     * @param index - cantidad de veces a iterar 
     * @return - Hasta que el contador coincida con el index retorna el valor en esta posición
     */
    private Object fdI(int index){
        if(index>size){
            return -1;
            
        }else{
        int cont = 0;
        Song temp = head;
 
        while(cont<=index){
            if(cont==index){
                return temp.getTitle();
            }else{
            temp = temp.getNext();
            cont++;
            }
        }
        }
  
        return -1;

    }
    /**
     * Busca la referencia de un nodo con respecto a su valor
     * @param value - parametro para que reconozca el nodo
     * @return - retorna la referencia al nodo
     */
        private Song findNode(String value){

        Song temp = head;
        
        while(temp.getTitle()!=value){
            temp = temp.getNext();
        }

        return temp;
        
    }
        /**
         * Busca la referencia de un nodo con respecto a su posición
         * @param index - cantidad de veces a iterar
         * @return - retorna la referencia al Node 
         */
        private Song findNodeI(int index){
    
        int cont = 0;
        Song temp = head;
 
        while(cont<=index){
            if(cont==index){
                return temp;
            }else{
            temp = temp.getNext();
            cont++;
            }
        }
  
        return null;
    }
    /**
     * verifica si la lista esta vacía 
     * @return - retorna true si es cierto si no entonces false
     */
    public boolean isEmpty(){
        return(head==null);
    }   
    public int getSize(){
        return size;
    }
    /**
     * muestra la lista completa
     */
    private void se(){
        
        Song temp = head;
        System.out.print("[");
        while(temp.getNext()!=null){
            System.out.print(String.valueOf(temp.getTitle())+",");
            temp = temp.getNext();
        } System.out.println(String.valueOf(temp.getTitle())+"]");
    }
    /**
     * Método para eliminar un valor en la lista
     * @param value - valor a encontrar y eliminar
     */
    private void del(String value){
        Song temp = head;
        if(value.equals(temp.getTitle())){
            head = temp.getNext();
        }else{
            while(temp.getNext().getTitle()!= value){
                temp = temp.getNext();
            }
            temp.setNext(temp.getNext().getNext());
        } size--;
    }
    /**
     * Este método busca la posición asignada y elimina el Node de la lista
     * @param index - posición a eliminar
     */
    private void delI(int index){
        if(index == 0){
            head = head.getNext();
        }else{
            Song temp = head;
            int cont = 0;
            while(cont < index-1){
                temp = temp.getNext();
                cont++;
            }temp.setNext(temp.getNext().getNext());
             size--;
        }
    }
   
}
