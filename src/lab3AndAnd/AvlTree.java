package lab3AndAnd;

public class AvlTree {


    private int size;
    private TreeNode root;  
   
    public void add(int data){
        TreeNode newNode= new TreeNode(data); //skapa en ny node
        if(root==null){
        	root=newNode;
        }else{
         root=insert(root,newNode,data);
        }    
        size++;
    }
    public int getSize(){
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

   
    
    public TreeNode insert(TreeNode current,TreeNode newNode, int value){
    	
    	TreeNode right=	current.getRight();
    	TreeNode left=	current.getLeft();
    	
    	if(current.getData()==value){
    		System.out.println("Värdet finns redan");
    		return current;
    	}
		else if(value<current.getData()){
				if(left==null){
					current.setLeft(newNode);
					newNode.setParent(current);
					System.out.println("InsertVärde:"+newNode.getData());
					addBalance(newNode,current);
					return current;
			}else{
				 insert(left,newNode,value);
			}
		}
		else if(value>current.getData()){
			if(right==null){
				current.setRight(newNode);
				newNode.setParent(current);
				System.out.println("InsertVärde:"+newNode.getData());
				addBalance(newNode,current);
				return current;
		
			}else{
				insert(right,newNode,value);
			}			
		}
			return current;
    }
    
    public void addBalance(TreeNode node,TreeNode parent){
    	
    	int pBalance=parent.getBalance();
    	int nBalance=node.getBalance();
    	
    	//Förälderns vänsterbarn
    	if(node==parent.getLeft()){	//är den för tung?   		
    		System.out.println("getLeft");
    		if(pBalance == -1){ 	//vänster,Vänster tung
    		
    			if(nBalance == 1){	//vänster höger?
    				
    				rotateLeft(node);
    				
    			}
    			
    			rotateRight(parent);	//rotera föräldern höger.
    			//break;
    		}
    		if (pBalance == -1) {
    			
    		       parent.setBalance(0); // N’s height increase is absorbed at P.
    		     //  break; // Leave the loop
    		     }
    		
    		parent.setBalance(1);
    	}	//Förälderns högerbarn
    	else if(node==parent.getRight()){
    		System.out.println("GetRIght");
    		if(pBalance == -1){
    			
    			if(nBalance == 1){
    				rotateRight(node);
    			}
    			rotateLeft(parent);
    		}
    		if(pBalance == 1){
    			
    			parent.setBalance(0);
    		}
    	
    		parent.setBalance(-1);
    		
    	}
    		
    	
    	System.out.println("Balans Nod: "+node.getBalance()+"-Värde:"+node.getData()+", "
    			+ "FörälderVikt: "+parent.getBalance()+" -Värde:"+parent.getData());
    	
    	
    	//Sista instruktion-rekursiv
    	node=parent;
    	parent=parent.getParent();
    	
    	if(parent!=null){
    		addBalance(node,parent);
    	}
    	
    }
    public void rotateLeft(TreeNode rotate){
    	System.out.println("**Rotate Left**");
    }
    public void rotateRight(TreeNode rotate){
    	System.out.println("**Rotate Right**");
    	TreeNode p=rotate.getLeft();	//P =rotates vänstra barn
    	rotate.setLeft(p.getRight());	//rotates vänstra barn= p's högra barn
    	p.getRight().setParent(rotate);	//p's högra barns förälder=rotate
    	p.setRight(rotate);				//p's högra barn=rotate
    	rotate.setParent(p);			//rotates förälder=p
    	
    	/*
    	 * Right Rotation of node Q:
			
			Let P be Q's left child.
			Set Q's left child to be P's right child.
			[Set P's right-child's parent to Q]*
			Set P's right child to be Q.
			[Set Q's parent to P]
    	 */
    }
    
    public void find(int value){
    	TreeNode findNode=find(value,root);
    	if(findNode!=null){
    		System.out.println("Värdet '"+value+"' hittades.");
    	}else{
    		System.out.println("Värdet '"+value+"' hittades inte.");
    	}
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
