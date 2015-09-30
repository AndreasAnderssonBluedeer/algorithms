package lab3AndAnd;

/**
 * Created by Andreas on 2015-09-25.
 */
public class TreeNode {
	private int balance;
    private int data;
    private TreeNode left;
    private TreeNode right;
    private TreeNode parent;
    
    public TreeNode (int data){
        this.data=data;
    }
    public void setBalance(int value){
    	balance=value;
    }
    public int getBalance(){
    	return balance;
    }
    public void setData(int data){
	   this.data=data;
   }
    public void setParent(TreeNode node){
	   parent=node;
   }
    public TreeNode getParent(){
	   return parent;
   }
    public void setLeft(TreeNode left){
        this.left=left;
    }
    public void setRight(TreeNode right){
        this.right=right;
    }
    public int getData(){
        return data;
    }
    public TreeNode getLeft(){
        return left;
    }
    public TreeNode getRight(){
        return right;
    }

    //Print metoder
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
        System.out.print(data);
        System.out.print('\n');         
    }

}
