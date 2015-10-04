package lab3AndAnd;

import java.util.Random;

/**
 * Created by Andreas on 2015-09-25.
 * 
 * Testclass for BinaryTree, test Insert,Delete,Find,Print.
 */
public class TestBT {

    public static void main(String [] args){
        BinaryTree bt=new BinaryTree();

        Random rand=new Random();
        int[] array=new int[20];
        for(int i=0;i<20;i++){
        	int b=rand.nextInt(100);
        	array[i]=b;
			bt.add(b);
			
		}
        System.out.println("Insert-ordning");
        for(int i=0;i<20;i++){
        System.out.print(array[i]+", ");
        }
        System.out.println();
        bt.inOrder(bt.getRoot());
        System.out.println();
        bt.printTree();
        System.out.println(array[4]+" ,"+bt.getSize());
        bt.delete(array[4]);
        System.out.println();
        bt.printTree();
        System.out.println(bt.getSize());
        bt.find(array[5]);
        bt.add(30);
        bt.printTree();
    }
}
