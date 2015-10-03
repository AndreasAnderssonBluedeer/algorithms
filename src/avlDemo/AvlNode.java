package avlDemo;

public class AvlNode {
	 public AvlNode left;
	 public AvlNode right;
	 public AvlNode parent;
	 public int key;
	 public int balance;

	 public AvlNode(int k) {
	  left = right = parent = null;
	  balance = 0;
	  key = k;
	 }
	 public String toString() {
	  return "" + key;
	 }
	 //PRINT
	 public void printTree(){

	        if(this.right!=null){
	            right.printTree(true, "");
	        }
	        printNodeValue();
	        if(this.left!=null){
	            left.printTree(false, "");
	        }
	    }

	    private void printTree(boolean isRight, String indent){

	        if(right!=null){
	            right.printTree(true,indent+(isRight ? "      ":" |     ")); 
	        }
	        System.out.print(indent);
	        if(isRight){
	            System.out.print(" /");
	        }else{
	            System.out.print(" \\");
	        }
	        System.out.print("----- ");
	        printNodeValue();
	        if(left!=null){
	            left.printTree(false,indent+ (isRight? " |    ":"      "));
	        }

	    }

	    private void printNodeValue(){
	        System.out.print(key);
	        System.out.print('\n');         
	    }

	}