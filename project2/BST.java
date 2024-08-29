import java.util.*;
public class BST <E extends Comparable<E>> extends AbstractTree<E>{
    protected TreeNode<E> root;
    protected int size = 0;
    
    public BST(E[] objects){
        for(int i = 0; i<objects.length;i++){
            //insert(objects[i]);
        }
            
    }
    
   @Override
   public boolean search(E e){
       TreeNode<E> current  = root;
       
       while(current!= null){
           if(e.compareTo(current.element)< 0){
               current = current.left;
           }
           else if(e.compareTo(current.element)>0){
               current = current.right;
           }
           else
            return true;
       }
       
       return false;
   }

    @Override  
    public boolean insert(E e, Article a){
        if(root == null){
            root = createNewNode(e);
            root.addFirst(a);
        }
        else {
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            
            while( current != null)
                if(e.compareTo(current.element) < 0){
                    parent = current;
                    current = current.left;
                }
                else if(e.compareTo(current.element) > 0){
                    parent = current;
                    current = current.right;
                }
                else {
                    current.addFirst(a);
                    return false;
                }
                if(e.compareTo(parent.element) < 0){
                    parent.left = createNewNode(e);
                    parent.left.addFirst(a);
                }
                else{
                    parent.right = createNewNode(e);
                    parent.right.addFirst(a);
                }
                
                
        }
        
        size++;
        return true;
        
   }
   
   protected TreeNode<E> createNewNode(E e){
       return new TreeNode<>(e);
   }
   
   @Override
   public void inorder(){
       inorder(root);
   }
   
   protected void inorder(TreeNode<E> root){
       if(root == null)
       inorder(root.left);
       System.out.print(root.element+"");
       Node<Article> artRec = root.head;
       System.out.println("/t");
       while(artRec != null){
           System.out.printf("%s", artRec);
           artRec =artRec.next;
       }
       System.out.println();
       inorder(root.right);
   }
protected void print(TreeNode<E> root){
       if(root == null)
       System.out.print(root.element+"");
       Node<Article> artRec = root.head;
       System.out.println("/t");
       while(artRec != null){
           System.out.printf("%s", artRec);
           artRec =artRec.next;
       }
       System.out.println();
   }
   
   @Override
   public void postorder(){
       postorder(root);
   }
   
   protected void preorder(TreeNode<E> root){
       if(root == null) return;
       postorder(root.left);
       postorder(root.right);
       System.out.print(root.element+"");
   }
   
   @Override
   public void preorder(){
       preorder(root);
       
   }
   
   protected void postorder(TreeNode<E> root){
       if(root == null) return;
       System.out.print(root.element + "");
       preorder(root.left);
       preorder(root.right);
   }
   
  
   
   @Override
   public int getSize(){
       return size;
   }
   
   public TreeNode<E> getRoot(){
       return root;
   }
   public ArrayList<TreeNode<E>> path(E e){
       ArrayList<TreeNode<E>> list=  
        new ArrayList<>();
    TreeNode<E> current = root;
    
    while (current != null){
        list.add(current);
        if(e.compareTo(current.element) < 0){
            current = current.left;
        }
        else if(e.compareTo(current.element) > 0){
            current = current.right;
        }
        else 
            break;
    }
    
    return list;
   }
   
   @Override
   
   public boolean delete(E e){
       TreeNode<E> parent = null;
       TreeNode<E> current = root;
       
      while(current != null){
          if(e.compareTo(current.element) < 0){
              parent = current;
              current = current.left;
          }
          else if(e.compareTo(current.element) > 0){
              parent = current;
              current = current.right;
          }
          else 
            break;
      }
      
    if(current == null)
        return false; 
        
    if(current.left ==null){
        if(parent == null){
            root = current.right;
        }
        else{
            if(e.compareTo(parent.element) < 0)
                parent.left = current.right;
            else 
            parent.right = current.right;
        }
    }
    else{
        TreeNode<E> parentOfRightMost = current;
        TreeNode<E> rightMost = current.left;
        
        
        while(rightMost.right != null){
            parentOfRightMost = current;
            rightMost = rightMost.right;
        }
        current.element = rightMost.element;
        
        if(parentOfRightMost.right == rightMost)
            parentOfRightMost.right = rightMost.left;
        else
            parentOfRightMost.left = rightMost.left;
    }
    
    size--;
    return true;
    
   }
   
   @Override
   public java.util.Iterator<E> iterator(){
       return new InorderIterator();
   }
   
   private class InorderIterator implements java.util.Iterator<E>{
        private java.util.ArrayList<E> list = 
            new java.util.ArrayList<>();
        private int current = 0;
        
        public InorderIterator(){
            inorder();
        }
        
        private void inorder(){
            inorder(root);
        }
        
        private void inorder(TreeNode<E> root){
            if(root == null) return;
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }
        
        @Override
        public boolean hasNext(){
            if(current < list.size())
                return true;
            return false;
        }
        
        @Override
        public E next(){
            return list.get(current++);
            
        }
        
        @Override
        public void remove(){
            delete(list.get(current));
            list.clear();
            inorder();
        }
    
   }
   
   public void clear(){
       root = null;
       size = 0;
   }
}