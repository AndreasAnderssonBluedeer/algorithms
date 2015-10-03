package lab3AndAnd;

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
    public void delete(int data){
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
					
					balance(newNode,current);

			}else{
				 insert(left,newNode,value);
			}
		}
		else if(value>current.getData()){
			if(right==null){				
				newNode.setParent(current);	
				current.setRight(newNode);	
				
				balance(newNode,current);
				
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
    public void balance(TreeNode n,TreeNode p){
    	
    	do{
    		
    		  // balance_factor(P) has not yet been updated!
    		   if (n == p.getLeft()) { // the left subtree increases
    		     if (p.getBalance() == 1) { // The left column in the picture
    		       // ==> the temporary balance_factor(P) == 2 ==> rebalancing is required.
    		       if (n.getBalance() == -1) { // Left Right Case
    		    	   rotateLeft(n); // Reduce to Left Left Case
    		       }
    		       // Left Left Case
    		       root=rotateRight(p);
    		       break; // Leave the loop
    		     }
    		     if (p.getBalance() == -1) {
    		       p.setBalance(0); // N’s height increase is absorbed at P.
    		       break; // Leave the loop
    		     }
    		     p.setBalance(1); // Height increases at P
    		   } else if (n==p.getRight()) { // N == right_child(P), the child whose height increases by 1.
    		     if (p.getBalance() == -1) { // The right column in the picture
    		       // ==> the temporary balance_factor(P) == -2 ==> rebalancing is required.
    		       if (n.getBalance() == 1) { // Right Left Case
    		    	   rotateRight(n); // Reduce to Right Right Case
    		       }
    		       // Right Right Case
    		       System.out.println(p.getData());
    		       root= rotateLeft(p);
    		       break; // Leave the loop
    		     }
    		     if (p.getBalance() == 1) {
    		      	p.setBalance(0); // N’s height increase is absorbed at P.
    		       break; // Leave the loop
    		     }
    		     p.setBalance(-1); // Height increases at P
    		   }
    		   n=p;
    		   p = n.getParent();
    		
    	}while(p!=null);
    	
    }
//    public TreeNode rotateLeft(TreeNode   c){
//    	
//    	System.out.println("**Rotate Left** "+c.getData());
//    	
//    	TreeNode b=  c.getParent();
//    	System.out.println(b.getData());
//    	
//    	TreeNode a=b.getParent();
//    	TreeNode aRight=  b.getLeft();
//    	TreeNode aParent=a.getParent();
//    	System.out.println("C: "+c.getData()+",B: "+b.getData()+", A:"+a.getData()+", AR:"+a.getRight().getData()
//    		);
//    	
//    	
//    	
//    	  b.setLeft(a);
//    	  b.setParent(aParent);
//    	if(aParent!=null){
//    	if(aParent.getLeft()==a){
//    		aParent.setLeft(  b);
//    	}
//    	else{
//    		aParent.setRight(  b);
//    	}
//    	}
//    	a.setParent(  b);
//    	a.setRight(aRight);
//    	System.out.println("C: "+c.getData()+",B: "+b.getData()+", A:"+a.getData()+", BR:"+b.getRight().getData()+
//    			",BL:"+b.getLeft().getData());
//    	
//    	a.resetBalance();
//    	return b;
//    }
//    public TreeNode rotateRight(TreeNode a){
//    	
//    	System.out.println("**Rotate Right**");
//    	
//    	TreeNode b=  a.getParent();
//    	System.out.println(b.getData());
//    	
//    	TreeNode c=b.getParent();
//    	TreeNode cLeft=  b.getRight();
//    	TreeNode cParent=c.getParent();
//    	
//    	System.out.println("Förälder: "+b.getData()+" FöräldersFörälder: "+a.getData()+
//    			" FÖräldersblivandeHöger: ");
//    	
//    	  b.setRight(c);
//    	  b.setParent(cParent);
//    	
//    	if(cParent.getLeft()==c){
//    		cParent.setLeft(  b);
//    	}
//    	else{
//    		cParent.setRight(  b);
//    	}
//    	a.setParent(  b);
//    	c.setLeft(cLeft);
//    	
//    	return b;
//    }
//    
    public TreeNode rotateLeft(TreeNode node){
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
    public TreeNode rotateRight(TreeNode node){
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
