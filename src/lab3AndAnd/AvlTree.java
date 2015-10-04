package lab3AndAnd;

import avlDemo.AvlNode;

public class AvlTree {		//Allting fungerar. Skriv ut meddelanden, optimera koden, namnge variabler+kommentera.


    private int size;
    private TreeNode root;  
   
    public void add(int data){	//OK
        TreeNode newNode= new TreeNode(data); 
     
         insert(root,newNode,data);	
           
         size++;

    }
    public int getSize(){		//OK
    	return size;
    }
    public void delete(int data){	//OK
       remove(root, data); 
       System.out.println("Delete complete.");
        
    }
    
    public void remove(TreeNode p,int q) { //FÖRSTÅ, strukturera om.
  	  if(p==null) {
  	   // der Wert existiert nicht in diesem Baum, daher ist nichts zu tun
  	   return;
  	  } else {
  	   if(p.getData()>q)  {
  	    remove(p.getLeft(),q);
  	   } else if(p.getData()<q) {
  	    remove(p.getRight(),q);
  	   } else if(p.getData()==q) {
  	    // we found the node in the tree.. now lets go on!
  	    removeFoundNode(p);
  	   }
  	  }
  	 }
   
    public void removeFoundNode(TreeNode q) {	//Förstå, strukturera
  	 TreeNode r;
  	  // at least one child of q, q will be removed directly
  	  if(q.getLeft()==null || q.getRight()==null) {
  	   // the root is deleted
  	   if(q.getParent()==null) {
  	    this.root=null;
  	    q=null;
  	    return;
  	   }
  	   r = q;
  	  } else {
  	   // q has two children --> will be replaced by successor
  	   r = successor(q);
  	   q.setData(r.getData());
  	  }
  	  
  	  TreeNode p;
  	  if(r.getLeft()!=null) {
  	   p = r.getLeft();
  	  } else {
  	   p = r.getRight();
  	  }
  	  
  	  if(p!=null) {
  	   p.setParent(r.getParent());
  	  }
  	  
  	  if(r.getParent()==null) {
  	   this.root = p;
  	  } else {
  	   if(r==r.getParent().getLeft()) {
  	    r.getParent().setLeft(p);
  	   } else {
  	    r.getParent().setRight(p);
  	   }
  	   // balancing must be done until the root is reached.
  	   balance(r.getParent());
  	  }
  	  r = null;
  	 }
   
    public TreeNode successor(TreeNode q) {		//FÖRSTÅ
  	  if(q.getRight()!=null) {
  	   TreeNode r = q.getRight();
  	   while(r.getLeft()!=null) {
  	    r = r.getLeft();
  	   }
  	   return r;
  	  } else {
  	   TreeNode p = q.getParent();
  	   while(p!=null && q==p.getRight()) {
  	    q = p;
  	    p = q.getParent();
  	   }
  	   return p;
  	  }
  	 }
   
    public void insert(TreeNode current,TreeNode newNode, int value){	//OK
    	
    	if(current==null){
    		root=newNode;
    	}else{
    	
    	TreeNode right=	current.getRight();
    	TreeNode left=	current.getLeft();
    	
    	
		if(value<current.getData()){
				if(left==null){	
					current.setLeft(newNode);	
					newNode.setParent(current);
					
					balance(current);

			}else{
				 insert(left,newNode,value);
			}
		}
		else if(value>current.getData()){
			if(right==null){				
				newNode.setParent(current);	
				current.setRight(newNode);	
				
				balance(current);
				
			}else{
				insert(right,newNode,value);
			}
		}
		else{
			System.out.println("Värdet '"+value+"' finns redan");
		}
	}
		
    }
    
    
    
   
    public void balance(TreeNode cur){	//Förstå /Strukturera om.
    	
    	setBalance(cur);
    	int balance = cur.getBalance();
    	
    	
    	 if(balance==-2) {
    		   
    		   if(height(cur.getLeft().getLeft())>=height(cur.getLeft().getRight())) {
    		    cur = rotateRight(cur);
    		   } else {
    		    cur = doubleRotateLeftRight(cur);
    		   }
    		  } else if(balance==2) {
    		   if(height(cur.getRight().getRight())>=height(cur.getRight().getLeft())) {
    		    cur = rotateLeft(cur);
    		   } else {
    		    cur = doubleRotateRightLeft(cur);
    		   }
    		  }
    		  
    		  // we did not reach the root yet
    		  if(cur.getParent()!=null) {
    		   balance(cur.getParent());	
    		  } else {
    		   this.root = cur;
    		   System.out.println("------------ Balancing finished ----------------");
    		  }
    }
    private void setBalance(TreeNode node) {	//OK
  	  node.setBalance(height(node.getRight())-height(node.getLeft())) ;
  	 }
    private int height(TreeNode node) {	//Förstå
  	 
    	
      if(node==null) {	
  	   return -1;
  	  }

      TreeNode left=node.getLeft();
      TreeNode right=node.getRight();
  	
  	  if(left==null && right==null) {	
  	   return 0;
  	  }
  	  else if(left==null) {
  	   return 1+height(right);
  	  }
  	  else if(right==null) {
  	   return 1+height(left);
  	  } 
  	  else {
  	   return 1+maximum(height(left),height(right));
  	  }
  	 }
    
    private int maximum(int a, int b) {	//Förstå
  	  if(a>=b) {
  	   return a;
  	  } else {
  	   return b;
  	  }
  	 }


    public TreeNode rotateLeft(TreeNode n){	//FÖRSTÅ
    	System.out.println("Roterar Vänster: "+n.getData());
    	TreeNode v = n.getRight();
  	  v.setParent(n.getParent());
  	  
  	  n.setRight(v.getLeft());
  	  
  	  if(n.getRight()!=null) {
  	   n.getRight().setParent(n);
  	  }
  	  
  	  v.setLeft(n);
  	  n.setParent(v);
  	  
  	  if(v.getParent()!=null) {
  	   if(v.getParent().getRight()==n) {
  	    v.getParent().setRight(v);
  	   } else if(v.getParent().getLeft()==n) {
  	    v.getParent().setLeft(v);
  	   }
  	  }
  	  
  	  setBalance(n);
  	  setBalance(v);
  	  
  	  return v;
    }
    public TreeNode rotateRight(TreeNode n){		//FÖrändra till barns struktur ist för FÖrälder.
    	System.out.println("Roterar Höger: "+n.getData());
    	  TreeNode v = n.getLeft();
    	  v.setParent(n.getParent());
    	  
    	  n.setLeft(v.getRight());
    	  
    	  if(n.getLeft()!=null) {
    	   n.getLeft().setParent(n);
    	  }
    	  
    	  v.setRight(n);
    	  n.setParent(v);
    	  
    	  
    	  if(v.getParent()!=null) {
    	   if(v.getParent().getRight()==n) {
    	    v.getParent().setRight(v);
    	   } else if(v.getParent().getLeft()==n) {
    	    v.getParent().setLeft(v);
    	   }
    	  }
    	  
    	  setBalance(n);
    	  setBalance(v);
    	  
    	  return v;
    }

    public TreeNode doubleRotateRightLeft(TreeNode u) {	//FÖrstå
  	  u.setRight(rotateRight(u.getRight()) );
  	  return rotateLeft(u);
  	 }
    public TreeNode doubleRotateLeftRight(TreeNode u) {	//Förstå
  	  u.setLeft(rotateLeft(u.getLeft()));
  	  return rotateRight(u);
  	 }
    
    public TreeNode find(int value){
    	TreeNode findNode=find(value,root);
    	if(findNode!=null){
    		System.out.println("Värdet '"+findNode.getData()+"' hittades.");
    	}else{
    		System.out.println("Värdet '"+value+"' hittades inte.");
    	}
    	return findNode;
    }	
    public TreeNode find(int key, TreeNode node){
    		
    		if(node==null || node.getData()==key){

    			return node;
    		}
    		else if(key<node.getData()){
    			return find(key,node.getLeft());
    		}
    		else if(key>node.getData()){
    			return find(key,node.getRight());
    		}
    		else{
    			return null;
    		}
    		   
    }

    public void inOrder(TreeNode node){
    	if(node!=null){
        inOrder(node.getLeft());
        System.out.print(node.getData()+", ");
        inOrder(node.getRight());
    	}
    	

    }
    public TreeNode getRoot(){
        return root;
    }
    public void printTree(){ //skriver ut en visualisering utav trädet.
    	
        root.printTree();

    }
}
