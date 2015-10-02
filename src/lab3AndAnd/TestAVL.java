package lab3AndAnd;

public class TestAVL {
	
	public static void main(String []args){
		AvlTree at=new AvlTree();
		for(int i=0;i<10;i+=2){
			at.add(i);
		}
//		at.add(4);
//		at.add(6);
//		at.add(11);
				System.out.println();
		at.inOrder(at.getRoot());
		System.out.println();
		at.getRoot().getRight().getBalance();
		System.out.println(at.getRoot().getBalance()+" ROOT BALANCE,"+at.getRoot().getRight().getBalance()+" Right ROOT BALANCE,"+at.getRoot().getLeft().getBalance()+" Left ROOT BALANCE");
//		at.add(10);
//		at.add(3);
//		at.add(2);
//		at.add(12);
//		at.add(13);
//		at.add(14);
////		
//		at.add(1);
//		at.add(1);
		System.out.println(at.getRoot().getBalance()+" ROOT BALANCE");
		System.out.println();
		at.inOrder(at.getRoot());
		System.out.println();
		at.printTree();
	}
}
