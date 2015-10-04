package lab3AndAnd;

import java.util.Random;



public class TestAVL {
	
	public static void main(String []args){
		AvlTree at=new AvlTree();
		
		 Random rand=new Random();
	        int[] array=new int[20];
	        for(int i=0;i<20;i++){
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
	        at.printTree();
	        System.out.println();
	        at.inOrder(at.getRoot());
	        System.out.println();
	        at.find(3);
	}
		
	
	
}
