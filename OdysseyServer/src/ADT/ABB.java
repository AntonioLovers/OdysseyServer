/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

/**
 *
 * @author pablo
 */
public class ABB {
    
    Nodo raiz;
    
    public ABB(){
        raiz=null;
    }
    
    public boolean isVacio(){
        return(raiz==null);
    }
    
    public Nodo buscar(int key){
        Nodo temp=raiz;
        while (temp.llave!= key){
            if(key<temp.llave){
                temp=temp.izq;
            }
            else{
                temp=temp.der;
            }
            if (temp==null){
                return null;
            }
            
        }   
        return temp;
        
    }
    
    public boolean eliminar(int key){
        Nodo temp=raiz;
        Nodo padre=raiz;
        boolean esHijoIz=true;
        while(temp.llave!=key){
            padre=temp;
            if(key<temp.llave){
                esHijoIz=true;
                temp=temp.izq;
            }
            else{
                esHijoIz=false;
                temp=temp.der;
                
            }
            if(temp==null){
                return false;
            }
        }// Fin del while
        
        if (temp.izq==null && temp.der==null){
            if (temp==raiz){
                raiz=null;
            }
            else if(esHijoIz){
                padre.izq=null;
                
            }   
            else{
                padre.der=null;
            }
            
        }
        else if(temp.der==null){
            if (temp==raiz){
                raiz=temp.izq;
            }
            else if(esHijoIz){
                padre.izq=temp.izq;
                
            }   
            else{
                padre.der=temp.izq;
            }
            
            
        }
        else if(temp.izq==null){
            if (temp==raiz){
                raiz=temp.der;
            }
            else if(esHijoIz){
                padre.izq=temp.der;
                
            }   
            else{
                padre.der=temp.izq;
            }
            
        }
        else{
            Nodo reemplazo=obtenerNodoRemp(temp);
            if(temp==raiz){
                raiz=reemplazo;
                
            }
            else if (esHijoIz){
                padre.izq=reemplazo;
            }
            else{
                padre.der=reemplazo;
            }
            reemplazo.izq=temp.izq;
        }
        
        return true;       
        
    }
    
    //Devuelve nodo remplazo
    public Nodo obtenerNodoRemp(Nodo remp){
        Nodo rempPadre=remp;
        Nodo remplazo=remp;
        Nodo temp=remp.der;
        while(temp!=null){
            rempPadre=remp;
            remp=temp;
            temp=temp.izq;
            
        }
        if(remplazo!=remp.der){
            rempPadre.izq=remplazo.der;
            remplazo.der=remp.der;
        }
        return remplazo; 
        
        
    }

    
    public void insertar (int key, Object song){
        Nodo nuevo= new Nodo(key);
        nuevo.cancion=song;
        
        if (raiz == null){
            raiz=nuevo;
        }
        else{
            Nodo temp=raiz;
            while(temp!=null){
                nuevo.p=temp;
                if (nuevo.llave >= temp.llave){
                    temp=temp.der;
                }
                else{
                    temp=temp.izq;
                }
            }
            if(nuevo.llave< nuevo.p.llave){
                nuevo.p.izq=nuevo;
            }
            else{
                nuevo.p.der=nuevo;
            }
        }
        
    }
    
    public class Nodo {
        
        public Nodo p; //Nodo padre
        public Nodo izq; //hijo izquierdo
        public Nodo der; //hijo derecho
        public int llave; //llave del nodo
        public Object cancion; //lo que vaya dentro del nodo
        
        
        public Nodo(int key){
            llave=key;
            p=null;
            izq=null;
            der=null;
            cancion=null;
        
    }
        
    }
    
    
}
