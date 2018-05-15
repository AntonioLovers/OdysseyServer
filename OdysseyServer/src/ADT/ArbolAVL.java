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
public class ArbolAVL {
    
    NodoAVL raiz;
    
    public ArbolAVL(){
        raiz=null;
    }
    
    public NodoAVL buscar (int key, NodoAVL r){
        if(raiz==null){
            return null;
        }else if (r.llave==key){
            return r;
        }else if(r.llave<key){
            return buscar(key,r.der);
        }else{
            return buscar(key,r.izq);
        }
    }
    
    // obtener factor de equilibrio
    public int obtenerFE(NodoAVL x){
        if(x==null){
            return -1;
        }
        else{
            return x.fe;
        }
    }
    
    // rotacion simple izquierda
    public NodoAVL rotIzq(NodoAVL c){
        NodoAVL temp=c.izq;
        c.izq=temp.der;
        temp.der=c;
        c.fe=Math.max(obtenerFE(c.izq), obtenerFE(c.der))+1;
        temp.fe=Math.max(obtenerFE(temp.izq), obtenerFE(temp.der))+1;
        return temp;
        
    }
    
    // rotacion doble izquierda
    public NodoAVL rotDobleIzq(NodoAVL c){
        NodoAVL temp;
        c.izq=rotDer(c.izq);
        temp=rotIzq(c);
        return temp;
        
    }
    
    // rotacion simple derecha
    public NodoAVL rotDer(NodoAVL c){
        NodoAVL temp=c.der;
        c.der=temp.izq;
        temp.izq=c;
        c.fe=Math.max(obtenerFE(c.izq), obtenerFE(c.der))+1;
        temp.fe=Math.max(obtenerFE(temp.izq), obtenerFE(temp.der))+1;
        return temp;
        
    }
    
    // rotacion doble derecha
    public NodoAVL rotDobleDer(NodoAVL c){
        NodoAVL temp;
        c.der=rotIzq(c.der);
        temp=rotDer(c);
        return temp;
        
    }
    
    
    
    // Metodo insertar balanceado
    public NodoAVL insertarAVL (NodoAVL nuevo, NodoAVL subArb){
        NodoAVL newPadre= subArb;
        if(nuevo.llave<subArb.llave){
            if(subArb.izq==null){
                subArb.izq=nuevo;
                
            }else{
                subArb.izq=insertarAVL(nuevo,subArb.izq);
                if((obtenerFE(subArb.izq)- obtenerFE(subArb.der)==2)){
                    if (nuevo.llave<subArb.izq.llave){
                        newPadre=rotIzq(subArb);
                    }else{
                        newPadre=rotDobleIzq(subArb);
                    }
                    
                }
            }
            
        }
        
        
        else if(nuevo.llave>subArb.llave){
            if(subArb.der==null){
                subArb.der=nuevo;
                
            }else{
                subArb.der=insertarAVL(nuevo,subArb.der);
                if((obtenerFE(subArb.der)- obtenerFE(subArb.izq)==2)){
                    if (nuevo.llave>subArb.der.llave){
                        newPadre=rotDer(subArb);
                    }else{
                        newPadre=rotDobleDer(subArb);
                    }
                    
                }
            }
            
        }
        else{
            System.out.println("Nodo Duplicado");
        }
        
        //Actualiza la factor de equilibrio
        if((subArb.izq==null) && (subArb.der!=null)){
            subArb.fe=subArb.der.fe+1;
        }
        else if ((subArb.der==null) && (subArb.izq!= null)){
            subArb.fe=subArb.izq.fe+1;
            
        }
        else{
            subArb.fe=Math.max(obtenerFE(subArb.izq), obtenerFE(subArb.der))+1;
        }
        
        return newPadre;
        
            
    }
    
    // insertar normal
    public void insertar(int key){
        NodoAVL nuevo=new NodoAVL(key);
        if(raiz==null){
            raiz=nuevo;
        }
        else{
            raiz=insertarAVL(nuevo, raiz);
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
     public class NodoAVL {
        
        public NodoAVL p; //Nodo padre
        public NodoAVL izq; //hijo izquierdo
        public NodoAVL der; //hijo derecho
        public int llave, fe; //llave del nodo
        public Object cancion; //lo que vaya dentro del nodo
        
        
        public NodoAVL(int key){
            fe=0;
            llave=key;
            p=null;
            izq=null;
            der=null;
            cancion=null;
        
    }
        
    }
    
}
