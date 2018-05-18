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
    
    /** constructor
     * 
     */
    public ArbolAVL(){
        raiz=null;
    }
    
    /** busca el nodo solicitado
     * 
     * @param key
     * @param r
     * @return el nodo solicitado
     */
    public NodoAVL buscar (String key, NodoAVL r){
        if(raiz==null){
            return null;
        }else{
		if(key.equals(r.llave)){
			return r;
			
		}else if(convertTOBytes(key)<=convertTOBytes(r.llave)){
			if(r.izq== null)
				return null;
			else
			return buscar(key,r.izq);
			
	}else if(convertTOBytes(key) >convertTOBytes(r.llave)){
		if(r.der==null)
			return null;
		else
			return buscar(key,r.der);
		}return null;
		
            }
        }
    
    /** obtener factor de equilibrio
     * 
     * @param x
     * @return factor de equilibrio
     */
    public int obtenerFE(NodoAVL x){
        if(x==null){
            return -1;
        }
        else{
            return x.fe;
        }
    }
    
    /** rotacion simple izquierda
     * 
     * @param c
     * @return arbol con rotacion a la izquierda
     */
    public NodoAVL rotIzq(NodoAVL c){
        NodoAVL temp=c.izq;
        c.izq=temp.der;
        temp.der=c;
        c.fe=Math.max(obtenerFE(c.izq), obtenerFE(c.der))+1;
        temp.fe=Math.max(obtenerFE(temp.izq), obtenerFE(temp.der))+1;
        return temp;
        
    }
    
    /** rotacion doble izquierda
     * 
     * @param c
     * @return arbol con rotacion doble a la izquierda
     */
    public NodoAVL rotDobleIzq(NodoAVL c){
        NodoAVL temp;
        c.izq=rotDer(c.izq);
        temp=rotIzq(c);
        return temp;
        
    }
    
    /** rotacion simple derecha
     * 
     * @param c
     * @return arbol con rotacion a la derecha
     */
    public NodoAVL rotDer(NodoAVL c){
        NodoAVL temp=c.der;
        c.der=temp.izq;
        temp.izq=c;
        c.fe=Math.max(obtenerFE(c.izq), obtenerFE(c.der))+1;
        temp.fe=Math.max(obtenerFE(temp.izq), obtenerFE(temp.der))+1;
        return temp;
        
    }
    
    /** rotacion doble derecha
     * 
     * @param c
     * @return arbol con rotacion doble a la derecha
     */
    public NodoAVL rotDobleDer(NodoAVL c){
        NodoAVL temp;
        c.der=rotIzq(c.der);
        temp=rotDer(c);
        return temp;
        
    }
    
    
    
    /** Metodo insertar balanceado
     * 
     * @param nuevo
     * @param subArb
     * @return arbol con nuevo nodo
     */
    public NodoAVL insertarAVL (NodoAVL nuevo, NodoAVL subArb){
        NodoAVL newPadre= subArb;
        if(convertTOBytes(nuevo.llave)<=convertTOBytes(subArb.llave)){
            if(subArb.izq==null){
                subArb.izq=nuevo;
                
            }else{
                subArb.izq=insertarAVL(nuevo,subArb.izq);
                if((obtenerFE(subArb.izq)- obtenerFE(subArb.der)==2)){
                    if (convertTOBytes(nuevo.llave)<=convertTOBytes(subArb.llave)){
                        newPadre=rotIzq(subArb);
                    }else{
                        newPadre=rotDobleIzq(subArb);
                    }
                    
                }
            }
            
        }
        
        
        else if(convertTOBytes(nuevo.llave)>convertTOBytes(subArb.llave)){
            if(subArb.der==null){
                subArb.der=nuevo;
                
            }else{
                subArb.der=insertarAVL(nuevo,subArb.der);
                if((obtenerFE(subArb.der)- obtenerFE(subArb.izq)==2)){
                    if (convertTOBytes(nuevo.llave)>convertTOBytes(subArb.llave)){
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
    
    /** insertar normal
     * 
     * @param key 
     */
    public void insertar(String key){
        NodoAVL nuevo=new NodoAVL(key);
        if(raiz==null){
            raiz=nuevo;
        }
        else{
            raiz=insertarAVL(nuevo, raiz);
        }
        
        
    }
    /**FUNCION STRING = NUMERO PARA ARBOLES
     * 
     * @param id
     * @return entero del String solicitado
     */
        public int convertTOBytes(String id){
            int cont = 0;
            for(int i = 0 ; i<id.length();i++){
                cont+= (byte)id.charAt(i);   
            }return cont;               
        } 
        
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
     public class NodoAVL {
        
        public NodoAVL p; //Nodo padre
        public NodoAVL izq; //hijo izquierdo
        public NodoAVL der; //hijo derecho
        public String llave;
        public int fe; //llave del nodo
        public Object cancion; //lo que vaya dentro del nodo
        
        
        public NodoAVL(String key){
            fe = 0;
            llave=key;
            p=null;
            izq=null;
            der=null;
            cancion=null;
        
    }
        
    }
    
}
