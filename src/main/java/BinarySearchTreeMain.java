import java.util.Comparator;
import java.util.List;

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


        System.out.println("maximo?: "+arbol.maximum());
        System.out.println("minimo?: "+arbol.minimum());
        System.out.println("contiene 100 "+arbol.contains(100));
        System.out.println("contiene 80 "+arbol.contains(80));
        
        arbol.removeBranch(70);

        System.out.println(arbol.render());

        boolean isLeaf = arbol.isLeaf();
        System.out.println("el arbol es hoja?: "+isLeaf);

        arbol.removeBranch(30);
        arbol.removeBranch(60);

        System.out.println(arbol.render());

        isLeaf = arbol.isLeaf();
        System.out.println("el arbol es hoja?: "+isLeaf); // DEBERIA DE DAR TRUE (HE COMPROBADO SI LOS VALORES SON NULOS EN VEZ DE LAS SUBRAMAS YA QUE LAS RAMAS EN VEZ DE NULOS SON EMPTY)

        System.out.println("-------------------OPERACIONES COMPLEJAS-------------------");

        arbol = new BinarySearchTree<Integer>(Comparator.naturalOrder());
        BinarySearchTree<Integer> arbol2;

        arbol.insert(50);
        arbol.insert(30);

        System.out.println(arbol.render());
        
        arbol.insert(20);
        arbol.insert(70);
        arbol.insert(60);
        arbol.insert(80);
        arbol.insert(40);

        System.out.println(arbol.render());

        List<Integer> lista = arbol.inOrder();
        
        System.out.println("lista ordenada"+lista.toString());

        arbol2 = arbol;

        arbol2.removeValue(30);

        System.out.println("eliminamos el 30");
        System.out.println(arbol2.render());

        arbol = new BinarySearchTree<Integer>(Comparator.naturalOrder());

        arbol.insert(50);
        arbol.insert(30);
        arbol.insert(20);
        arbol.insert(70);
        arbol.insert(60);
        arbol.insert(80);
        arbol.insert(40);
        arbol.insert(42);
        arbol.insert(35);

        System.out.println(arbol.render());

        arbol.removeValue(30);
        System.out.println("eliminamos el 30");
        System.out.println(arbol.render());

        arbol.insert(100);
        arbol.insert(110);
        arbol.insert(120);
        arbol.insert(130);

        System.out.println(arbol.render());

        System.out.println("hacemos el balance");
        arbol.balance();
        System.out.println(arbol.render());
	}
}
