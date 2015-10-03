package lab3AndAnd;

import avlDemo.AvlNode;

public class AvlTree {


    private int size;
    private TreeNode root;  
   
    public void add(int data){	//OK
        TreeNode newNode= new TreeNode(data); //skapa en ny node
     
         insert(root,newNode,data);	//Sätter inte förälderna till newNode?!!:S
           
         size++;

    }
    public int getSize(){		//OK
    	return size;
    }
    public void delete(int data){	//OK
       remove(root, data); //hämta den nya Roten
        
    }
    
    public void remove(TreeNode current, int dataToRemove){
    	
    		TreeNode nodeRemove=find(dataToRemove,current);
    		
    		
    		if(nodeRemove!=null){	//Om noden som ska tas bort finns
    			
    			TreeNode parent=nodeRemove.getParent();
    			TreeNode right=nodeRemove.getRight();
    			TreeNode left=nodeRemove.getLeft();
    			
	    			if(right==null && left==null){	//Har noden inga barn
	    				
	    				if(parent.getLeft()==nodeRemove){	//Kolla om det är den vänstra noden
	    					parent.setLeft(null);			//Sätt värdet till NULL- nod raderad
	    				}
	    				else if(parent.getRight()==nodeRemove){	//Kolla om det är den högra noden
	    					parent.setRight(null);				//Sätt värdet till NULL- nod raderad
	    				}
	    				size--;
	    				
	    			}
	    			else if(right==null){	//Om noden har barn, har den endast ett vänster barn?
	    				if(parent.getLeft()==nodeRemove){
	    					parent.setLeft(left);
	    				}
	    				else if(parent.getRight()==nodeRemove){
	    					parent.setRight(left);
	    				}
	    					
	    					size--;
	    			}
	    			else if(left==null){	//Om noden har barn, har den endast ett höger barn?
	    				if(parent.getLeft()==nodeRemove){	//Är noden förälderns höger eller vänster barn?
	    					parent.setLeft(right);
	    				}
	    				else if(parent.getRight()==nodeRemove){
	    					parent.setRight(right);
	    				}
	    				size--;
    				
	    			}
	    			else {
	    				while(left.getLeft()!=null){ //Hitta vänstraste "högervärde"
	    				left=left.getLeft();	
	    				}
	    				delete(left.getData());
	    				nodeRemove.setData(left.getData());	  	
	    				
	    			}
    						
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
    
    public void addBalance(TreeNode node,TreeNode parent){
    	TreeNode x=null;
    	if(parent!=null){
    		
    	if(node==parent.getLeft()){
    	
    		parent.addBalance(-1);
    		System.out.println(node.getData()+" Added value -1 to parent with value: "+parent.getData());
    		
    		if( parent.getBalance() == -2 ){
    	//		rotateRight(node);  
    			System.out.println("BALANS -2!!!******");
    			if(node.getLeft()!=null) {	
    				System.out.println(node.getData()+"RoteringRight sker V**");
    				x=rotateRight(node.getLeft()); 
    				
    			//	return parent;
    			}
    		
    		else{
    			System.out.println(node.getData()+" RoteringRight sker H**");
    			x=rotateRight(node.getRight()); 
    		}
    			
				if(x.getParent()==null){
				root=x; 
				}else{
					parent=x;
				}
    		}
    	
    	}
    	else if(node==parent.getRight()){
    		parent.addBalance(1);
    		System.out.println(node.getData()+" Added value +1 to parent with value: "+parent.getData());
    		if( parent.getBalance() == 2 ){
    		System.out.println("BALANS +2!!!******");
    			if(node.getLeft()!=null) {	
    				System.out.println(node.getData()+"RoteringLeft sker V**");
    				x=rotateLeft(node.getLeft());
    			//	return parent;
    			}
    		
    		else{
    			System.out.println(node.getData()+" RoteringLeft sker H**");
    			x=rotateLeft(node.getRight()); 
    			
    		//	return parent;
    		}
    			
//    		parent.resetBalance();	//?
//    		node.resetBalance();	//?
    			
				if(x.getParent()==null){
				root=x; 
				}else{
					parent=x;
				}
    	}
    	}
    	
    	
    	
    	}
    	if(parent.getParent()!=null){
    	addBalance(parent,parent.getParent()); //fortsätt upp i trädet.
    	}
    //	return root;
    }
    
    //TEST BALANCE-METOD!!!
    public void balance(TreeNode cur){	//balance(TreeNode n,TreeNode p)
    	
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
    		   balance(cur.getParent());	//recursiveBalance
    		  } else {
    		   this.root = cur;
    		   System.out.println("------------ Balancing finished ----------------");
    		  }
    }
    private void setBalance(TreeNode node) {	//OK
  	  node.setBalance(height(node.getRight())-height(node.getLeft())) ;
  	 }
    private int height(TreeNode cur) {	//Förstå
  	  if(cur==null) {
  	   return -1;
  	  }
  	  if(cur.getLeft()==null && cur.getRight()==null) {	
  	   return 0;
  	  } else if(cur.getLeft()==null) {
  	   return 1+height(cur.getRight());
  	  } else if(cur.getRight()==null) {
  	   return 1+height(cur.getLeft());
  	  } else {
  	   return 1+maximum(height(cur.getLeft()),height(cur.getRight()));
  	  }
  	 }
    
    private int maximum(int a, int b) {	//Förstå
  	  if(a>=b) {
  	   return a;
  	  } else {
  	   return b;
  	  }
  	 }


    public TreeNode rotateLeft(TreeNode node){	//FÖRÄNDRA
    	System.out.println("Roterar Vänster: "+node.getData());
    	TreeNode p=node.getParent();
    	TreeNode pp=p.getParent();
    	
    	node.setParent(pp);
    	p.setParent(node);
    	if(pp!=null){
    	if(pp.getLeft()==p){
    		pp.setLeft(node);
    	}else{
    		pp.setRight(node);
    	}
    	}
    	p.setRight(node.getLeft());
    	node.setLeft(p);
    	
    	return node;
    }
    public TreeNode rotateRight(TreeNode node){		//FÖrändra till barns struktur ist för FÖrälder.
    	System.out.println("Roterar Höger: "+node.getData());
    	TreeNode p=node.getParent();
    	TreeNode pp=p.getParent();
    	
    	node.setParent(pp);
    	p.setParent(node);
    	
    	if(pp!=null){
    	if(pp.getLeft()==p){
    		pp.setLeft(node);
    	}else{
    		pp.setRight(node);
    	}
    	}
    	p.setLeft(node.getRight());
    	node.setRight(p);
    	
    	return node;
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
