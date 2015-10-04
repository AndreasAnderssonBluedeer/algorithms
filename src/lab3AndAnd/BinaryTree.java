package lab3AndAnd;

/**
 * Created by Andreas on 2015-09-25.
 * Creates a BinaryTree by using the TreeNode class. Allows the user to Add/insert TreeNodes,Delete
 * ,find/Search, print the tree, get number of nodes in the entire tree, print the keyValues in order by number.
 */
public class BinaryTree {

    private int size;
    private TreeNode root;  
   
    /**
     * Adds and creates a new TreeNode to the BinaryTree with the given parameter "data"
     * as the new nodes Key/Value.
     * @param data -Key value for the new TreeNode
     */
	public void add(int data) {
        TreeNode newNode= new TreeNode(data); 
        if(root==null){
        	root=newNode;
        }else{
         root=insert(root,newNode,data);
        }    
        size++;
    }
  
    /**
     * Returns the number of nodes in the BinaryTree.
     * @return int
     */
	public int getSize() {
    	return size;
    }
    
    /**
     * Deletes the TreeNode with the given int:Data as key.
     * Uses the Remove-method.
     * @param data -key to TreeNode.
     */
	public void delete(int data) {
       remove(root, data); 
        
    }
    
    /**
     * Receives a key and a TreeNode to compare the key to.
     * If the key and TreeNode matches- the TreeNode will be removed.
     * 
     * @param current	-TreeNode to compare key/dataToRemove to.
     * @param dataToRemove -Key to TreeNode to be removed.
     */
    public void remove(TreeNode current, int dataToRemove){
    	
		TreeNode nodeRemove = find(dataToRemove, current);

		if (nodeRemove != null) {

			TreeNode parent = nodeRemove.getParent();
			TreeNode right = nodeRemove.getRight();
			TreeNode left = nodeRemove.getLeft();

			if (right == null && left == null) {

				if (parent.getLeft() == nodeRemove) {
					parent.setLeft(null);
				} else if (parent.getRight() == nodeRemove) {
					parent.setRight(null);
				}
				size--;

			} else if (right == null) {
				if (parent.getLeft() == nodeRemove) {
					parent.setLeft(left);
				} else if (parent.getRight() == nodeRemove) {
					parent.setRight(left);
				}

				size--;
			} else if (left == null) {
				if (parent.getLeft() == nodeRemove) {
					parent.setLeft(right);
				} else if (parent.getRight() == nodeRemove) {
					parent.setRight(right);
				}
				size--;

			} else {
				while (left.getLeft() != null) {
					left = left.getLeft();
				}
				delete(left.getData());
				nodeRemove.setData(left.getData());

			}

		}

	}

    /**
     * Inserts the newNode into the Binarytree when it finds the right position.
     * Current is used to compare to value to find the right location. 
     * Returns newNodes parent.
     * @param current -TreeNode to compare to.
     * @param newNode -TreeNode to insert.
     * @param value -int with NewNodes value/Key.
     * @return TreeNode
     */
	public TreeNode insert(TreeNode current, TreeNode newNode, int value) {
    	
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
					return current;
			}else{
				 insert(left,newNode,value);
			}
		}
		else if(value>current.getData()){
			if(right==null){
				current.setRight(newNode);
				newNode.setParent(current);
				return current;
		
			}else{
				insert(right,newNode,value);
			}			
		}
			return current;
    }
    
   /**
    * Search method for a specific TreeNode. Uses Value as Key
    * to find it.
    * @param value -key to the TreeNode to be Found
    */
	public void find(int value) {
		TreeNode findNode = find(value, root);
		if (findNode != null) {
			System.out.println("Värdet '" + value + "' hittades.");
		} else {
			System.out.println("Värdet '" + value + "' hittades inte.");
		}
	}
	
	/**
	 * A search Method to find a Specific TreeNode by a Key and a TreeNode to 
	 * compare the Key to. If there is a Match- Return the "Found" TreeNode.
	 * @param key -Key to the Searched TreeNode
	 * @param node	- TreeNode for comparison.
	 * @return
	 */
	public TreeNode find(int key, TreeNode node) {

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

	/**
	 * Prints the whole trees keyvalues in order. Takes a node as parameter to get the
	 * next value(s)
	 * @param node -TreeNode to get Values.
	 */
	public void inOrder(TreeNode node) {
		if (node != null) {
			inOrder(node.getLeft());
			System.out.print(node.getData() + ", ");
			inOrder(node.getRight());
		}

	}

	/**
	 * Returns the BinaryTrees Root.
	 * @return TreeNode
	 */
	public TreeNode getRoot() {
		return root;
	}

	/**
	 * Prints the entire BinaryTree in the Console.
	 */
	public void printTree() { 

		root.printTree();

	}
}
