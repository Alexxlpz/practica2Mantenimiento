import java.util.Comparator;

import org.mps.tree.BinarySearchTree;

public class BinarySearchTreeMain {
    
    public static void main(String[] args) {
        BinarySearchTree<Integer> arbol = new BinarySearchTree<Integer>(Comparator.naturalOrder());
        arbol.insert(50);
        arbol.insert(30);

        System.out.println(arbol.render());

        arbol.insert(60);
        arbol.insert(70);
        arbol.insert(80);
        arbol.insert(50);

        System.out.println(arbol.render());

        arbol.removeBranch(70);

        System.out.println(arbol.render());

        boolean isLeaf = arbol.isLeaf();
        System.out.println("el arbol es hoja?: "+isLeaf);

        arbol.removeBranch(30);
        arbol.removeBranch(60);

        System.out.println(arbol.render());

        isLeaf = arbol.isLeaf();
        System.out.println("el arbol es hoja?: "+isLeaf); // DEBERIA DE DAR TRUE
		
	}
}
