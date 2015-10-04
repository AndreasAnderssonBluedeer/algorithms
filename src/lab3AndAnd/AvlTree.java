package lab3AndAnd;



public class AvlTree {		//Skriv ut meddelanden, JAVA-DOC kvar

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
    
    public void remove(TreeNode node,int key) { // OK
    	
  	  if(node==null) {
  	   
  	  } else {
  		  int data=node.getData();
  	   if(data>key)  {
  	    remove(node.getLeft(),key);
  	   } else if(data<key) {
  	    remove(node.getRight(),key);
  	   } else if(data==key) {
  	   
  	    removeFoundNode(node);
  	   }
  	  }
  	 }
   
    public void removeFoundNode(TreeNode nodeToRemove) {	// OK
		
    	Boolean rootRemoved=true;
    	TreeNode remove;

		if (nodeToRemove.getLeft() == null || nodeToRemove.getRight() == null) {

			if (nodeToRemove.getParent() == null) {
				this.root = null;
				nodeToRemove = null;
				rootRemoved=false;
			}
			remove = nodeToRemove;
		} else {

			remove = successor(nodeToRemove);
			nodeToRemove.setData(remove.getData());
		}
		if(rootRemoved){
		TreeNode node;
		TreeNode removeParent=remove.getParent();
		TreeNode removeLeft=remove.getLeft();
		
		if (removeLeft != null) {
			node = removeLeft;
		} else {
			node = remove.getRight();
		}

		if (node != null) {
			node.setParent(removeParent);
		}

		if (removeParent == null) {
			this.root = node;
		} else {		
			if (remove == removeParent.getLeft()) {
				removeParent.setLeft(node);
			} else {
				removeParent.setRight(node);
			}

			balance(removeParent);
		}
		remove = null;
		}
	}
   
    public TreeNode successor(TreeNode node) {		// OK
	
    	if (node.getRight() != null) {
			TreeNode right = node.getRight();

			while (right.getLeft() != null) {
				right = right.getLeft();
			}

			return right;
		} else {

			TreeNode parent = node.getParent();
			while (parent != null && node == parent.getRight()) {
				node = parent;
				parent = node.getParent();
			}
			return parent;
		}
	}
   
    public void insert(TreeNode current,TreeNode newNode, int value){	//OK
    	
		if (current == null) {
			root = newNode;
		} else {

			TreeNode right = current.getRight();
			TreeNode left = current.getLeft();

			if (value < current.getData()) {
				if (left == null) {
					current.setLeft(newNode);
					newNode.setParent(current);

					balance(current);

				} else {
					insert(left, newNode, value);
				}
			} else if (value > current.getData()) {
				if (right == null) {
					newNode.setParent(current);
					current.setRight(newNode);

					balance(current);

				} else {
					insert(right, newNode, value);
				}
			} else {
				System.out.println("Värdet '" + value + "' finns redan");
			}
		}
		
    }
            
    public void balance(TreeNode node){	//	OK
    	
		setBalance(node);
		int balance = node.getBalance();

		if (balance == -2) {
			TreeNode nodeLeft = node.getLeft();

			if (height(nodeLeft.getLeft()) >= height(nodeLeft.getRight())) {
				node = rotateRight(node);
			} else {
				node.setLeft(rotateLeft(nodeLeft));
				node = rotateRight(node);
			}
		} else if (balance == 2) {
			TreeNode nodeRight = node.getRight();
			if (height(nodeRight.getRight()) >= height(nodeRight.getLeft())) {
				node = rotateLeft(node);
			} else {
				node.setRight(rotateRight(nodeRight));
				node = rotateLeft(node);
				;
			}
		}

		if (node.getParent() != null) {
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
    	
		if (node == null) {
			return -1;
		}

		TreeNode left = node.getLeft();
		TreeNode right = node.getRight();

		if (left == null && right == null) {
			return 0;
		} else if (left == null) {
			return 1 + height(right);
		} else if (right == null) {
			return 1 + height(left);
		} else {
			return 1 + heighest(height(left), height(right));
		}
  	 }
    
    private int heighest(int left, int right) {	//OK
		
    	if (left >= right) {
			return left;
		} else {
			return right;
		}
  	 }

    public TreeNode rotateLeft(TreeNode node){	//OK
    	
		System.out.println("Roterar Vänster: " + node.getData());

		TreeNode nRight = node.getRight();
		nRight.setParent(node.getParent());
		TreeNode nRP = nRight.getParent();

		node.setRight(nRight.getLeft());

		TreeNode newNR = node.getRight();

		if (newNR != null) {
			newNR.setParent(node);
		}

		nRight.setLeft(node);
		node.setParent(nRight);

		if (nRP != null) {
			if (nRP.getRight() == node) {
				nRP.setRight(nRight);
			} else if (nRP.getLeft() == node) {
				nRP.setLeft(nRight);
			}
		}

		setBalance(node);
		setBalance(nRight);

		return nRight;
    }
  
    public TreeNode rotateRight(TreeNode node){		//OK
    	
		System.out.println("Roterar Höger: " + node.getData());

		TreeNode left = node.getLeft();
		left.setParent(node.getParent());

		node.setLeft(left.getRight());

		TreeNode nl = node.getLeft();

		if (nl != null) {
			nl.setParent(node);
		}

		left.setRight(node);
		node.setParent(left);

		TreeNode lp = left.getParent();

		if (lp != null) {
			if (lp.getRight() == node) {
				lp.setRight(left);
			} else if (lp.getLeft() == node) {
				lp.setLeft(left);
			}
		}

		setBalance(node);
		setBalance(left);

		return left;
    }
 
    public TreeNode find(int value){		//OK

		TreeNode findNode = find(value, root);
		if (findNode != null) {
			System.out.println("Värdet '" + value + "' hittades.");
		} else {
			System.out.println("Värdet '" + value + "' hittades inte.");
		}
		return findNode;
    }	
    
    public TreeNode find(int key, TreeNode node){		//OK
    		
		if (node == null || node.getData() == key) {
			return node;
		} else if (key < node.getData()) {
			return find(key, node.getLeft());
		} else if (key > node.getData()) {
			return find(key, node.getRight());
		} else {
			return null;
		}
    		   
    }

    public void inOrder(TreeNode node){		//	OK
		
    	if (node != null) {
			inOrder(node.getLeft());
			System.out.print(node.getData() + ", ");
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
