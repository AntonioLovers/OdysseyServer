<<<<<<< HEAD

package ADT;



/**
 * @author Daniel
 */
public class BinarySearch_Tree {
	/**
	 * Métodos públicos del árbol
	 */
	private int size;
	private User root;
        private String result,inorder;
	/**
	 * Constructor del árbol le da valor nulo a la raíz
	 */
	public BinarySearch_Tree() {
		this.root = null;	
                this.result = "";
                this.inorder = "";
	}
	/**
	 * Busca el menor valor del árbol
	 * @return
	 */
	public String findMin(){
		return this.min();
	}
	/**
	 * Busca el mayor valor del árbol
	 * @return
	 */
	public String findMax() {
		return this.max(this.getRoot());
	}
	/**
	 * regresa true si la raíz es nula
	 * @return
	 */
	public boolean isEmpty() {
		return this.root == null;
	}
	/**
	 * retorna la raíz del árbol
	 * @return
	 */
	public User getRoot() {
		return this.root;
	}
	/**
	 * Inserta un nuevo valor en el árbol
         * @param id
         * @param pass
         * @param age
         * @param last
         * @param friends
         * @param gnres
         * @param name
	 * @return
	 */
	public boolean insert(String id , String pass ,int age, String name , 
               String last ,String friends , String gnres ) {
		return this.in(id,pass,age,name,last,friends,gnres,root);
	}
        /**
         * Elimina un nodo del arbol binario buscando el valor
         * @param id 
         */
        public void remove(String id){
            if(this.contains(id)){
             size--;
             this.del(id,root);
            }else{
                System.out.println("El elemento no se encuentra");
            }
        }
	/**
	 * Busca cualquier valor en el árbol
	 * retorna true si lo encuentra y si no , false
	 * @param id
	 * @return
	 */
	public boolean contains(String id) {
		return this.fi(id,this.root);
	}
        public User getUser(String id){
            return this.findUser(id,root);
        }

        /**
         * retorna la cantidad de nodos en la lista
         * @return 
         */
        public int getSize(){
            return this.size;
        }

	/**
	 * Métodos privados del árbol
	 * 
	 * 
	 */

	
	/**
	 * FINDMAX
	 * Busca el mayor valor del árbol
	 * @param node
	 * @return
	 */
	
	private String max(User node) {
		if(node.getRight() == null) {
			return node.getId();
		}else {
			return max(node.getRight());
		}
		
	}
	/**
	 * FINDMIN
	 * Busca el menor valor del árbol
	 * @return
	 */
	private String min(){
		User temp = getRoot();
		while(temp.getLeft() != null){
			temp = temp.getLeft();
		}return temp.getId();
	}


	/**
	 * INSERT
	 * inserta un nuevo elemento en el árbol
	 
	 * @param node
	 * @return
	 */
	private boolean in(String id , String pass ,int age, String name , 
               String last ,String friends , String gnres ,User node) {
		if(isEmpty()) {
			this.root = new User(id,pass,age,name,
                                last,friends,gnres);
                        size++;
			
		}else {
                    int b = convertTOBytes(node.getId());
                    int a = convertTOBytes(id);
			if(convertTOBytes(node.getId()) >= convertTOBytes(id)) {
	
				if(node.getLeft() == null) {
					node.setLeft( new User(id,pass,age,name,
                                                last,friends,gnres));
                                        size++;
					return true;
				}else {
					return in(id,pass,age,name,
                                                last,friends,gnres,node.getLeft());
				}
			}else {
				if(node.getRight() == null) {
					node.setRight( new User(id,pass,age,name,
                                                last,friends,gnres));
                                        size++;
					return true;
				}else {
					return in(id,pass,age,name,
                                                last,friends,gnres,node.getRight());
				}
			}
		}return false;
	}
        /**
         * REMOVE 
         * Elimina un elemento del àrbol 
         * @param key
         * @param n
         * @return 
         */
        private User del(String id,User n){
            if(n == null){
                return n;
            }
            if(convertTOBytes(id) < convertTOBytes(n.getId())){
                
                n.setLeft(del(id,n.getLeft()));
                
            }else if(convertTOBytes(id) > convertTOBytes(n.getId())){
                
                n.setRight(del(id,n.getRight()));
                
            }else if(id.equals(n.getId())){
                if(n.getLeft() != null && n.getRight() != null){
                
                n.setId(max(n.getLeft()));
                n.setLeft(del(n.getId(),n.getLeft()));
                
            }else{
                
                n = n.getLeft() != null ? n.getLeft() : n.getRight();
                
            }}else{
                n.setLeft(del(id,n.getLeft()));
            }
            return n;
            
        }
	/**
	 * FIND
	 * Encuentra cualquier valor en el árbol
	 * y retorna true , salvo que sea nulo
	 * @return
	 */
	private boolean fi(String id,User node) {
            if(isEmpty()){
                return false;
            }else{
		if(id.equals(node.getId())){
			return true;
			
		}else if(convertTOBytes(id)<=convertTOBytes(node.getId())){
			if(node.getLeft()== null)
				return false;
			else
			return fi(id,node.getLeft());
			
	}else if(convertTOBytes(id) >convertTOBytes(node.getId())){
		if(node.getRight()==null)
			return false;
		else
			return fi(id,node.getRight());
		}return false;
		
            }
        }
        
        
        
        /**
         * FINDIUSER
         * Regresa un nodo luego de buscarlo por su id si no se encuentra
         * regresa null
         * @param id
         * @param node
         * @return 
         */
	private User findUser(String id,User node) {
            if(isEmpty()){
                return null;
            }else{
		if(id.equals(node.getId())){
			return node;
			
		}else if(convertTOBytes(id)<=convertTOBytes(node.getId())){
			if(node.getLeft()== null)
				return null;
			else
			return findUser(id,node.getLeft());
			
	}else if(convertTOBytes(id) >convertTOBytes(node.getId())){
		if(node.getRight()==null)
			return null;
		else
			return findUser(id,node.getRight());
		}return null;
		
            }
        }        
	
	/**
         * MAX
	 * Compara dos números y obtiene el mayor
	 * a menos de que sean iguales
	 * @param num1
	 * @param num2
	 * @return
	 */
	private int max(int num1 , int num2) {
		if(num1>=num2) {
			return num1;
		}else{
			return num2;
		 }
	}
        
//FUNCION STRING = NUMERO PARA ARBOLES
        public int convertTOBytes(String id){
            int cont = 0;
            for(int i = 0 ; i<id.length();i++){
                cont+= (byte)id.charAt(i);   
            }return cont;               
        } 
        public String getInorder(){
            inorder = "";
            this.Inorder(root);
            String ii =inorder.substring(0, inorder.length()-1);
            return ii;
        }
        public void Inorder(User root){
		if(root != null) {
                        
			Inorder(root.getLeft());
                        inorder+= root.getId()+"@";
			Inorder(root.getRight());
		}              
        }
        
        /**
         * Llama al metodo print de la clase BTreePrinter
         * Imprime los nodos y las ramas del arbol de manera precisa.
         */
	public void see() { 
            BTreePrinter.printNode(root);
        }
        
        public String getResult(){
            result ="";
            this.createUserList(root);
            String rr = result.substring(0, result.length()-1);
            return rr;
        }
        private void createUserList(User u){
                
		if(u != null) {
                        result+= u.getId()+"@";
			createUserList(u.getLeft());
			createUserList(u.getRight());
		}           
        }
	
	
}
=======

package ADT;



/**
 * @author Daniel
 */
public class BinarySearch_Tree {
	/**
	 * Métodos públicos del árbol
	 */
	private int size;
	private User root;
        private String result,inorder;
	/**
	 * Constructor del árbol le da valor nulo a la raíz
	 */
	public BinarySearch_Tree() {
		this.root = null;	
                this.result = "";
                this.inorder = "";
	}
	/**
	 * Busca el menor valor del árbol
	 * @return
	 */
	public String findMin(){
		return this.min();
	}
	/**
	 * Busca el mayor valor del árbol
	 * @return
	 */
	public String findMax() {
		return this.max(this.getRoot());
	}
	/**
	 * regresa true si la raíz es nula
	 * @return
	 */
	public boolean isEmpty() {
		return this.root == null;
	}
	/**
	 * retorna la raíz del árbol
	 * @return
	 */
	public User getRoot() {
		return this.root;
	}
	/**
	 * Inserta un nuevo valor en el árbol
         * @param id
         * @param pass
         * @param age
         * @param last
         * @param friends
         * @param gnres
         * @param name
	 * @return
	 */
	public boolean insert(String id , String pass ,int age, String name , 
               String last ,String friends , String gnres ) {
		return this.in(id,pass,age,name,last,friends,gnres,root);
	}
        /**
         * Elimina un nodo del arbol binario buscando el valor
         * @param id 
         */
        public void remove(String id){
            if(this.contains(id)){
             size--;
             this.del(id,root);
            }else{
                System.out.println("El elemento no se encuentra");
            }
        }
	/**
	 * Busca cualquier valor en el árbol
	 * retorna true si lo encuentra y si no , false
	 * @param id
	 * @return
	 */
	public boolean contains(String id) {
		return this.fi(id,this.root);
	}
        public User getUser(String id){
            return this.findUser(id,root);
        }

        /**
         * retorna la cantidad de nodos en la lista
         * @return 
         */
        public int getSize(){
            return this.size;
        }

	/**
	 * Métodos privados del árbol
	 * 
	 * 
	 */

	
	/**
	 * FINDMAX
	 * Busca el mayor valor del árbol
	 * @param node
	 * @return
	 */
	
	private String max(User node) {
		if(node.getRight() == null) {
			return node.getId();
		}else {
			return max(node.getRight());
		}
		
	}
	/**
	 * FINDMIN
	 * Busca el menor valor del árbol
	 * @return
	 */
	private String min(){
		User temp = getRoot();
		while(temp.getLeft() != null){
			temp = temp.getLeft();
		}return temp.getId();
	}


	/**
	 * INSERT
	 * inserta un nuevo elemento en el árbol
	 
	 * @param node
	 * @return
	 */
	private boolean in(String id , String pass ,int age, String name , 
               String last ,String friends , String gnres ,User node) {
		if(isEmpty()) {
			this.root = new User(id,pass,age,name,
                                last,friends,gnres);
                        size++;
			
		}else {
                    int b = convertTOBytes(node.getId());
                    int a = convertTOBytes(id);
			if(convertTOBytes(node.getId()) >= convertTOBytes(id)) {
	
				if(node.getLeft() == null) {
					node.setLeft( new User(id,pass,age,name,
                                                last,friends,gnres));
                                        size++;
					return true;
				}else {
					return in(id,pass,age,name,
                                                last,friends,gnres,node.getLeft());
				}
			}else {
				if(node.getRight() == null) {
					node.setRight( new User(id,pass,age,name,
                                                last,friends,gnres));
                                        size++;
					return true;
				}else {
					return in(id,pass,age,name,
                                                last,friends,gnres,node.getRight());
				}
			}
		}return false;
	}
        /**
         * REMOVE 
         * Elimina un elemento del àrbol 
         * @param key
         * @param n
         * @return 
         */
        private User del(String id,User n){
            if(n == null){
                return n;
            }
            if(convertTOBytes(id) < convertTOBytes(n.getId())){
                
                n.setLeft(del(id,n.getLeft()));
                
            }else if(convertTOBytes(id) > convertTOBytes(n.getId())){
                
                n.setRight(del(id,n.getRight()));
                
            }else if(id.equals(n.getId())){
                if(n.getLeft() != null && n.getRight() != null){
                
                n.setId(max(n.getLeft()));
                n.setLeft(del(n.getId(),n.getLeft()));
                
            }else{
                
                n = n.getLeft() != null ? n.getLeft() : n.getRight();
                
            }}else{
                n.setLeft(del(id,n.getLeft()));
            }
            return n;
            
        }
	/**
	 * FIND
	 * Encuentra cualquier valor en el árbol
	 * y retorna true , salvo que sea nulo
	 * @return
	 */
	private boolean fi(String id,User node) {
            if(isEmpty()){
                return false;
            }else{
		if(id.equals(node.getId())){
			return true;
			
		}else if(convertTOBytes(id)<=convertTOBytes(node.getId())){
			if(node.getLeft()== null)
				return false;
			else
			return fi(id,node.getLeft());
			
	}else if(convertTOBytes(id) >convertTOBytes(node.getId())){
		if(node.getRight()==null)
			return false;
		else
			return fi(id,node.getRight());
		}return false;
		
            }
        }
        
        
        
        /**
         * FINDIUSER
         * Regresa un nodo luego de buscarlo por su id si no se encuentra
         * regresa null
         * @param id
         * @param node
         * @return 
         */
	private User findUser(String id,User node) {
            if(isEmpty()){
                return null;
            }else{
		if(id.equals(node.getId())){
			return node;
			
		}else if(convertTOBytes(id)<=convertTOBytes(node.getId())){
			if(node.getLeft()== null)
				return null;
			else
			return findUser(id,node.getLeft());
			
	}else if(convertTOBytes(id) >convertTOBytes(node.getId())){
		if(node.getRight()==null)
			return null;
		else
			return findUser(id,node.getRight());
		}return null;
		
            }
        }        
	
	/**
         * MAX
	 * Compara dos números y obtiene el mayor
	 * a menos de que sean iguales
	 * @param num1
	 * @param num2
	 * @return
	 */
	private int max(int num1 , int num2) {
		if(num1>=num2) {
			return num1;
		}else{
			return num2;
		 }
	}
        
//FUNCION STRING = NUMERO PARA ARBOLES
        public int convertTOBytes(String id){
            int cont = 0;
            for(int i = 0 ; i<id.length();i++){
                cont+= (byte)id.charAt(i);   
            }return cont;               
        } 
        public String getInorder(){
            inorder = "";
            this.Inorder(root);
            String ii =inorder.substring(0, inorder.length()-1);
            return ii;
        }
        public void Inorder(User root){
		if(root != null) {
                        
			Inorder(root.getLeft());
                        inorder+= root.getId()+"@";
			Inorder(root.getRight());
		}              
        }
        
        /**
         * Llama al metodo print de la clase BTreePrinter
         * Imprime los nodos y las ramas del arbol de manera precisa.
         */
	public void see() { 
            BTreePrinter.printNode(root);
        }
        
        public String getResult(){
            result ="";
            this.createUserList(root);
            String rr = result.substring(0, result.length()-1);
            return rr;
        }
        private void createUserList(User u){
                
		if(u != null) {
                        result+= u.getId()+"@";
			createUserList(u.getLeft());
			createUserList(u.getRight());
		}           
        }
	
	
}
>>>>>>> edc524e9c9f4b064ebe08588ca82dbc405b8a65a
