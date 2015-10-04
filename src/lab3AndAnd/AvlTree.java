package lab3AndAnd;



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
       System.out.println("Delete '"+data+"' complete.");
        
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
    
    
    
   
    public void balance(TreeNode node){	//	OK
    	
    	setBalance(node);
    	int balance = node.getBalance();
    	
    	
    	 if(balance==-2) {
    		 TreeNode nodeLeft=node.getLeft();
    		   
    		   if(height(nodeLeft.getLeft())>=height(nodeLeft.getRight())) {
    		    node = rotateRight(node);
    		   } else {
    			   node.setLeft(rotateLeft(nodeLeft));    			  	 
    			   node = rotateRight(node);
    		   }
    		  } else if(balance==2) {
    			  TreeNode nodeRight=node.getRight();
    		   if(height(nodeRight.getRight())>=height(nodeRight.getLeft())) {
    		    node = rotateLeft(node);
    		   } else {
    			   node.setRight(rotateRight(nodeRight) );   			  	 
    			   node = rotateLeft(node);;
    		   }
    		  }
    		  
    		 
    		  if(node.getParent()!=null) {
    		   balance(node.getParent());	
    		  } else {
    		   this.root = node;
    		   System.out.println("****Balansering Genomförd****");
    		  }
    }
    
    private void setBalance(TreeNode node) {	//OK
  	  node.setBalance(height(node.getRight())-height(node.getLeft())) ;
  	 }
    
    private int height(TreeNode node) {	//OK
  	 
    	
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
  	   return 1+heighest(height(left),height(right));
  	  }
  	 }
    
    private int heighest(int left, int right) {	//OK
  	  if(left>=right) {
  	   return left;
  	  } else {
  	   return right;
  	  }
  	 }


    public TreeNode rotateLeft(TreeNode node){	//OK
    	
    	System.out.println("Roterar Vänster: "+node.getData());
    	
    	TreeNode nRight = node.getRight();
    	nRight.setParent(node.getParent());
    	TreeNode nRP= nRight.getParent();
  	  
  	  node.setRight(nRight.getLeft());
  	  
  	  TreeNode newNR=node.getRight();
  	  
  	  if(newNR!=null) {
  	   newNR.setParent(node);
  	  }
  	  
  	  nRight.setLeft(node);
  	  node.setParent(nRight);
  	  
  	  if(nRP!=null) {
  	   if(nRP.getRight()==node) {
  	    nRP.setRight(nRight);
  	   } else if(nRP.getLeft()==node) {
  	    nRP.setLeft(nRight);
  	   }
  	  }
  	  
  	  setBalance(node);
  	  setBalance(nRight);
  	  
  	  return nRight;
    }
    public TreeNode rotateRight(TreeNode node){		//OK
    	
    	System.out.println("Roterar Höger: "+node.getData());
    	
    	  TreeNode left = node.getLeft();
    	  left.setParent(node.getParent());
    	  
    	  node.setLeft(left.getRight());
    	  
    	  TreeNode nl=node.getLeft();
    	  
    	  if(nl!=null) {
    	   nl.setParent(node);
    	  }
    	  
    	  left.setRight(node);
    	  node.setParent(left);
    	  
    	  TreeNode lp =left.getParent();
    	  
    	  if(lp!=null) {
    	   if(lp.getRight()==node) {
    		   lp.setRight(left);
    	   } else if(lp.getLeft()==node) {
    		   lp.setLeft(left);
    	   }
    	  }
    	  
    	  setBalance(node);
    	  setBalance(left);
    	  
    	  return left;
    }

   
    public TreeNode find(int value){		//OK
    	
    	TreeNode findNode=find(value,root);
    	if(findNode!=null){
    		System.out.println("Värdet '"+findNode.getData()+"' hittades.");
    	}else{
    		System.out.println("Värdet '"+value+"' hittades inte.");
    	}
    	return findNode;
    }	
    
    public TreeNode find(int key, TreeNode node){		//OK
    		
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

    public void inOrder(TreeNode node){		//	OK
    	if(node!=null){
        inOrder(node.getLeft());
        System.out.print(node.getData()+", ");
        inOrder(node.getRight());
    	}
    	

    }
    public TreeNode getRoot(){	//OK
        return root;
    }
    public void printTree(){ //	OK
    	
        root.printTree();

    }
}
