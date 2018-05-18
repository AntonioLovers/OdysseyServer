/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ADT;

import java.util.Scanner;
/**
 *
 * @author pablo
 */
public class ArbolSplay{
     private NodoSplay root;
     private int count = 0;
 
     /** Constructor **/
     public ArbolSplay()
     {
         root = null;
     }
 
     /** verifica si el arbol está vacio **/
     public boolean isEmpty()
     {
         return root == null;
     }
 
     /** limpia el arbol **/
     public void clear()
     {
         root = null;
     }
 
     /** insertar nodo
      * 
      * @param ele 
      */
      
     public void insert(String ele)
     {
         NodoSplay z = root;
         NodoSplay p = null;
         while (z != null)
         {
             p = z;
             if (convertTOBytes(ele) <convertTOBytes( p.llave))
                 z = z.right;
             else
                 z = z.left;
         }
         z = new NodoSplay(ele);
         z.llave = ele;
         z.parent = p;
         if (p == null)
             root = z;
         else if (convertTOBytes(ele) <convertTOBytes( p.llave))
             p.right = z;
         else
             p.left = z;
         Splay(z);
         count++;
     }
     /** rotacion a la izquierda
      * 
      * @param c
      * @param p 
      */
     public void makeLeftChildParent(NodoSplay c, NodoSplay p)
     {
         if ((c == null) || (p == null) || (p.left != c) || (c.parent != p))
             throw new RuntimeException("WRONG");
 
         if (p.parent != null)
         {
             if (p == p.parent.left)
                 p.parent.left = c;
             else 
                 p.parent.right = c;
         }
         if (c.right != null)
             c.right.parent = p;
 
         c.parent = p.parent;
         p.parent = c;
         p.left = c.right;
         c.right = p;
     }
 
     /**rotacion a la derecha
      * 
      * @param c
      * @param p 
      */
     public void makeRightChildParent(NodoSplay c, NodoSplay p)
     {
         if ((c == null) || (p == null) || (p.right != c) || (c.parent != p))
             throw new RuntimeException("WRONG");
         if (p.parent != null)
         {
             if (p == p.parent.left)
                 p.parent.left = c;
             else
                 p.parent.right = c;
         }
         if (c.left != null)
             c.left.parent = p;
         c.parent = p.parent;
         p.parent = c;
         p.right = c.left;
         c.left = p;
     }
 
     /** metodo splay
      * 
      * @param x 
      */
     private void Splay(NodoSplay x)
     {
         while (x.parent != null)
         {
             NodoSplay Parent = x.parent;
             NodoSplay GrandParent = Parent.parent;
             if (GrandParent == null)
             {
                 if (x == Parent.left)
                     makeLeftChildParent(x, Parent);
                 else
                     makeRightChildParent(x, Parent);                 
             } 
             else
             {
                 if (x == Parent.left)
                 {
                     if (Parent == GrandParent.left)
                     {
                         makeLeftChildParent(Parent, GrandParent);
                         makeLeftChildParent(x, Parent);
                     }
                     else 
                     {
                         makeLeftChildParent(x, x.parent);
                         makeRightChildParent(x, x.parent);
                     }
                 }
                 else 
                 {
                     if (Parent == GrandParent.left)
                     {
                         makeRightChildParent(x, x.parent);
                         makeLeftChildParent(x, x.parent);
                     } 
                     else 
                     {
                         makeRightChildParent(Parent, GrandParent);
                         makeRightChildParent(x, Parent);
                     }
                 }
             }
         }
         root = x;
     }
 
     /** elimina elemento del arbol
      * 
      * @param ele 
      */
     public void remove(String ele)
     {
         NodoSplay node = findNode(ele);
        remove(node);
     }
 
     /** elimina nodo del arbol
      * 
      * @param node 
      */
     private void remove(NodoSplay node)
     {
         if (node == null)
             return;
 
         Splay(node);
         if( (node.left != null) && (node.right !=null))
         { 
             NodoSplay min = node.left;
             while(min.right!=null)
                 min = min.right;
 
             min.right = node.right;
             node.right.parent = min;
             node.left.parent = null;
             root = node.left;
         }
         else if (node.right != null)
         {
             node.right.parent = null;
             root = node.right;
         } 
         else if( node.left !=null)
         {
             node.left.parent = null;
             root = node.left;
         }
         else
         {
             root = null;
         }
         node.parent = null;
         node.left = null;
         node.right = null;
         node = null;
         count--;
     }
 
     /** nos permite contar los nodos del arbol
      * 
      * @return 
      */
     public int countNodes()
     {
         return count;
     }
 
     /** funcion para buscar elemento
      * 
      * @param val
      * @return elemento encontrado
      */
     public boolean search(String val)
     {
         return findNode(val) != null;
     }
     private NodoSplay findNode(String ele)
     {
         NodoSplay z = root;
         while (z != null)
         {
             if (convertTOBytes(ele) < convertTOBytes( z.llave))
                 z = z.right;
             else if (convertTOBytes(ele) > convertTOBytes( z.llave))
                 z = z.left;
             else
                 return z;
         }
         return null;
     }
 
     /** recorrido innorden
      * 
      */
     public void inorder()
     {
         inorder(root);
     }
     private void inorder(NodoSplay r)
     {
         if (r != null)
         {
             inorder(r.left);
             System.out.print(r.llave +" ");
             inorder(r.right);
         }
     }
 
     /** recorrido preorder
      * 
      */
     public void preorder()
     {
         preorder(root);
     }
     private void preorder(NodoSplay r)
     {
         if (r != null)
         {
             System.out.print(r.llave +" ");
             preorder(r.left);             
             preorder(r.right);
         }
     }
 
     /** recorrido postorden
      * 
      */
     public void postorder()
     {
         postorder(root);
     }
     private void postorder(NodoSplay r)
     {
         if (r != null)
         {
             postorder(r.left);             
             postorder(r.right);
             System.out.print(r.llave +" ");
         }
     }
     
 /**FUNCION STRING = NUMERO PARA ARBOLES
  * 
  * @param id
  * @return entero del string solicitado
  */
        public int convertTOBytes(String id){
            int cont = 0;
            for(int i = 0 ; i<id.length();i++){
                cont+= (byte)id.charAt(i);   
            }return cont;               
        }      
        
     
     public class NodoSplay {
         public NodoSplay left, right, parent;
         public String llave;
 
     
     /** Constructor **/
     public NodoSplay(String ele)
     {
         left = null;
         right = null;
         parent =null;
         llave = ele; 
         
     }
 }
}
