<<<<<<< HEAD

package ADT;

/**
 *
 * @author Daniel
 * @param <T>
 */
public class User<T extends Comparable<?>>{
	
	private User right;
        private User left;
        private String id,password,name,lastName,friends,fGnres;
        private int age;
        private LinkedList_S songsList;
       
        
        
	//Node parent;
	public User(String id , String pass ,int age, String name , 
               String last ,String friends , String gnres ){
		
	
                this.id =id;
                this.password = pass;
                this.name = name;
                this.lastName =last;
                this.friends = friends;
                this.fGnres = gnres;
		this.age  = age;
                
                this.songsList = new LinkedList_S();
                
		this.right = null;
		this.left = null;
		
	}
        
	public User getRight() {
		return this.right;
	}
	public User getLeft() {
		return this.left;
	}

	public void setRight(User node) {
		this.right = node;
	}
	public void setLeft(User node) {
		this.left = node;
	}

        public String getId() {
            return id;
        }

        public String getPassword() {
            return password;
        }

        public String getName() {
            return name;
        }

        public String getLastName() {
            return lastName;
        }

        public String getFriends() {
            return friends;
        }

        public String getfGnres() {
            return fGnres;
        }

        public int getAge() {
            return age;
        }

        public void setId(String id) {
            this.id = id;
        }

         public LinkedList_S getSongsList() {
            return songsList;
        }
        
        
        
        

}
=======

package ADT;

/**
 *
 * @author Daniel
 * @param <T>
 */
public class User<T extends Comparable<?>>{
	
	private User right;
        private User left;
        private String id,password,name,lastName,friends,fGnres;
        private int age;
       
        
        
	//Node parent;
	public User(String id , String pass ,int age, String name , 
               String last ,String friends , String gnres ){
		
	
                this.id =id;
                this.password = pass;
                this.name = name;
                this.lastName =last;
                this.friends = friends;
                this.fGnres = gnres;
		this.age  = age;
                
		this.right = null;
		this.left = null;
		
	}
        
	public User getRight() {
		return this.right;
	}
	public User getLeft() {
		return this.left;
	}

	public void setRight(User node) {
		this.right = node;
	}
	public void setLeft(User node) {
		this.left = node;
	}

        public String getId() {
            return id;
        }

        public String getPassword() {
            return password;
        }

        public String getName() {
            return name;
        }

        public String getLastName() {
            return lastName;
        }

        public String getFriends() {
            return friends;
        }

        public String getfGnres() {
            return fGnres;
        }

        public int getAge() {
            return age;
        }

        public void setId(String id) {
            this.id = id;
        }
        
        
        

}
>>>>>>> edc524e9c9f4b064ebe08588ca82dbc405b8a65a
