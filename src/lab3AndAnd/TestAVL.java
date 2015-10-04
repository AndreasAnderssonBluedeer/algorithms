package lab3AndAnd;

import java.util.Random;

/**
 * 
 * created @author Andreas Andersson, Systemutveckling on 2015-09-25.
 *
 *A simple testclass for the Avl-Tree and TreeNode.
 */

public class TestAVL {
	
	/**
	 * Insert,find,delete and print-instructions with random numbers.
	 * @param args
	 */
	public static void main(String []args){
		AvlTree at=new AvlTree();
		
		 Random rand=new Random();
	        int[] array=new int[50];
	        for(int i=0;i<50;i++){
	        	int b=rand.nextInt(100);
	        	array[i]=b;
				at.add(b);
				
			}
	        System.out.println("Insert-ordning");
	        for(int i=0;i<20;i++){
	        System.out.print(array[i]+", ");
	        }
	        at.printTree();
	        at.delete(array[5]);
	        at.delete(array[2]);
	        at.delete(array[7]);
	        at.printTree();
	        System.out.println();
	        at.inOrder(at.getRoot());
	        System.out.println();
	        at.find(3);
	        System.out.println(at.getSize());
	}
		
	
	
}
