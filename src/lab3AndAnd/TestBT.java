package lab3AndAnd;

/**
 * Created by Andreas on 2015-09-25.
 */
public class TestBT {

    public static void main(String [] args){
        BinaryTree bt=new BinaryTree();
        System.out.println("Sökning: Add/Insert");
        bt.add(38);
        bt.add(13);
        bt.add(51);
        bt.add(10);
        bt.add(12);
        bt.add(40);
        bt.add(84);
        bt.add(25);
        bt.add(89);
        bt.add(37);
        bt.add(66);
        bt.add(95);
        bt.add(24);
        bt.add(22);
        System.out.println("Storlek: "+bt.getSize());
        System.out.println("Sökning:");
        bt.find(38);
        bt.find(13);
        bt.find(51);
        bt.find(10);
        bt.find(12);
        bt.find(40);
        bt.find(84);
        bt.find(25);
        bt.find(89);
        bt.find(37);
        bt.find(66);
        bt.find(95);
        bt.find(1);
        
       
       
        System.out.println();
    	
        bt.inOrder(bt.getRoot());

        System.out.println();
            
        bt.printTree();
        System.out.println();
        System.out.println("Storlek: "+bt.getSize());
        bt.delete(84);
        System.out.println("Storlek: "+bt.getSize());
        bt.delete(40);
        System.out.println("Storlek: "+bt.getSize());
        bt.delete(12);
        System.out.println("Storlek: "+bt.getSize());
        bt.add(12);
        System.out.println("Storlek: "+bt.getSize());
        bt.add(40);
        System.out.println("Storlek: "+bt.getSize());
        bt.printTree();
        System.out.println();
        bt.inOrder(bt.getRoot());
        System.out.println();
    }
}
