package binarySearchTreeTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mps.tree.BinarySearchTree;
import org.mps.tree.BinarySearchTreeException;

@DisplayName("Pruebas de clase BinarySearchTree")
public class BinarySearchTreeTest {

    public BinarySearchTree<Integer> arbol;

    @BeforeEach
    public void BinarySearchTreeSetUp(){
        arbol = new BinarySearchTree<Integer>(Comparator.naturalOrder());
    }

    @Nested
    @DisplayName("Pruebas de render")
    class RenderTests{

        @Test
        @DisplayName("Render de arbol vacio")
        public void BinarySearchTreeRenderArbolVacio(){
            assertEquals("", arbol.render());
        }

        @Test
        @DisplayName("Render de arbol solo con raiz")
        public void BinarySearchTreeRenderArbolSoloRaiz(){
            arbol.insert(50);
            assertEquals("50", arbol.render());
        }

        @Test
        @DisplayName("Render de arbol solo con rama izquierda")
        public void BinarySearchTreeRenderArbolSoloIzquierda(){
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(20);
            arbol.insert(40);
            assertEquals("50(30(20,40),)", arbol.render());
        }

        @Test
        @DisplayName("Render de arbol solo con rama derecha")
        public void BinarySearchTreeRenderArbolSoloDerecha(){
            arbol.insert(50);
            arbol.insert(70);
            arbol.insert(60);
            arbol.insert(80);
            assertEquals("50(,70(60,80))", arbol.render());
        }

        @Test
        @DisplayName("Render de arbol completo")
        public void BinarySearchTreeRenderCompleto(){
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(20);
            arbol.insert(40);
            arbol.insert(70);
            arbol.insert(60);
            arbol.insert(80);
            assertEquals("50(30(20,40),70(60,80))", arbol.render());
        }
    }

    @Nested
    @DisplayName("Pruebas de insert")
    class InsertTests{

        @Test
        @DisplayName("Insert valor nulo")
        public void BinarySearchTreeInsertNull(){
            assertThrows(BinarySearchTreeException.class, () -> {arbol.insert(null);});
        }

        @Test
        @DisplayName("Insert valor no nulo sobre arbol vacio")
        public void BinarySearchTreeInsertInicialmenteNull(){
            String renderInicial = arbol.render();
            arbol.insert(50);
            assertEquals("", renderInicial);
            assertEquals("50", arbol.render());
        }

        @Test
        @DisplayName("Insert valor menor sobre hoja")
        public void BinarySearchTreeInsertValorMenorSobreHoja(){
            arbol.insert(50);
            String renderInicial = arbol.render();
            arbol.insert(30);
            assertEquals("50", renderInicial);
            assertEquals("50(30,)", arbol.render());
        }

        @Test
        @DisplayName("Insert valor mayor sobre hoja")
        public void BinarySearchTreeInsertValorMayorSobreHoja(){
            arbol.insert(50);
            String renderInicial = arbol.render();
            arbol.insert(70);
            assertEquals("50", renderInicial);
            assertEquals("50(,70)", arbol.render());
        }

        @Test
        @DisplayName("Insert valor menor sobre arbol")
        public void BinarySearchTreeInsertValorMenorSobreArbol(){
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(70);
            String renderInicial = arbol.render();
            arbol.insert(40);
            assertEquals("50(30,70)", renderInicial);
            assertEquals("50(30(,40),70)", arbol.render());
        }

        @Test
        @DisplayName("Insert valor mayor sobre arbol")
        public void BinarySearchTreeInsertValorMayorSobreArbol(){
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(70);
            String renderInicial = arbol.render();
            arbol.insert(60);
            assertEquals("50(30,70)", renderInicial);
            assertEquals("50(30,70(60,))", arbol.render());
        }
    }

    @Nested
    @DisplayName("Pruebas isLeaf")
    class IsLeafTests{

        @Test
        @DisplayName("isLeaf sobre arbol vacio")
        public void BinarySearchTreeIsLeafArbolVacio(){
            assertFalse(arbol.isLeaf());
        }

        @Test
        @DisplayName("isLeaf sobre hoja")
        public void BinarySearchTreeIsLeafHoja(){
            arbol.insert(50);
            assertTrue(arbol.isLeaf());
        }

        @Test
        @DisplayName("isLeaf sobre rama")
        public void BinarySearchTreeIsLeafRama(){
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(70);
            assertFalse(arbol.isLeaf());
        }

        @Test
        @DisplayName("isLeaf sobre arbol con rama izq nada mas")
        public void BinarySearchTreeIsLeafRamaIzquierda(){
            arbol.insert(50);
            arbol.insert(30);
            assertFalse(arbol.isLeaf());
        }

        @Test
        @DisplayName("isLeaf sobre arbol con rama der nada mas")
        public void BinarySearchTreeIsLeafRamaDerecha(){
            arbol.insert(50);
            arbol.insert(70);
            assertFalse(arbol.isLeaf());
        }


    }

    @Nested
    @DisplayName("Pruebas contains")
    class ContainsTests{

        @Test
        @DisplayName("contains sobre arbol vacio")
        public void BinarySearchTreeContainsArbolVacio(){
            assertFalse(arbol.contains(50));
        }

        @Test
        @DisplayName("contains valor nulo")
        public void BinarySearchTreeContainsValorNulo(){
            arbol.insert(50);
            assertFalse(arbol.contains(null));
        }

        @Test
        @DisplayName("contains valor raiz")
        public void BinarySearchTreeContainsValorRaiz(){
            arbol.insert(50);
            assertTrue(arbol.contains(50));
        }

        @Test
        @DisplayName("contains valor rama derecha")
        public void BinarySearchTreeContainsValorRamaDerecha(){
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(70);
            assertTrue(arbol.contains(70));
        }

        @Test
        @DisplayName("contains valor rama izquierda")
        public void BinarySearchTreeContainsValorRamaIzquierda(){
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(70);
            assertTrue(arbol.contains(30));
        }

        @Test
        @DisplayName("contains valor rama derecha Nula")
        public void BinarySearchTreeContainsValorRamaDerechaNula(){
            arbol.insert(50);
            arbol.insert(30);
            assertFalse(arbol.contains(70));
        }

        @Test
        @DisplayName("contains valor rama izquierda Nula")
        public void BinarySearchTreeContainsValorRamaIzquierdaNula(){
            arbol.insert(50);
            arbol.insert(70);
            assertFalse(arbol.contains(30));
        }

        @Test
        @DisplayName("contains valor arbol sin ramas y no esta")
        public void BinarySearchTreeContainsValorNoEsRaiz(){
            arbol.insert(50);
            assertFalse(arbol.contains(30));
        }
    }

    @Nested
    @DisplayName("Pruebas minimum")
    class MinimumTests{

        @Test
        @DisplayName("minimum arbol vacio")
        public void BinarySearchTreeMinimumArbolVacio(){
            assertEquals(null, arbol.minimum());
        }

        @Test
        @DisplayName("minimum hoja")
        public void BinarySearchTreeMinimumHoja(){
            arbol.insert(50);
            assertEquals(50, arbol.minimum());
        }

        @Test
        @DisplayName("minimum rama sin hijo izquierdo")
        public void BinarySearchTreeMinimumRamaSinHijoIzquierdo(){
            arbol.insert(50);
            arbol.insert(70);
            assertEquals(50, arbol.minimum());
        }

        @Test
        @DisplayName("minimum rama con hijo izquierdo")
        public void BinarySearchTreeMinimumRamaConHijoIzquierdo(){
            arbol.insert(50);
            arbol.insert(30);
            assertEquals(30, arbol.minimum());
        }
    }

    @Nested
    @DisplayName("Pruebas maximum")
    class MaximumTests{

        @Test
        @DisplayName("maximum arbol vacio")
        public void BinarySearchTreeMaximumArbolVacio(){
            assertEquals(null, arbol.maximum());
        }

        @Test
        @DisplayName("maximum hoja")
        public void BinarySearchTreeMaximumHoja(){
            arbol.insert(50);
            assertEquals(50, arbol.maximum());
        }

        @Test
        @DisplayName("maximum rama sin hijo derecho")
        public void BinarySearchTreeMaximumRamaSinHijoIzquierdo(){
            arbol.insert(50);
            arbol.insert(30);
            assertEquals(50, arbol.maximum());
        }

        @Test
        @DisplayName("minimum rama con hijo derecho")
        public void BinarySearchTreeMaximumRamaConHijoIzquierdo(){
            arbol.insert(50);
            arbol.insert(70);
            assertEquals(70, arbol.maximum());
        }
    }

    @Nested
    @DisplayName("Pruebas removeBranch")
    class RemoveBranchTests{

        @Test
        @DisplayName("removeBranch valor nulo")
        public void BinarySearchTreeRemoveBranchValorNull(){
            assertThrows(BinarySearchTreeException.class, () -> {arbol.removeBranch(null);});
        }

        @Test
        @DisplayName("removeBranch valor raiz")
        public void BinarySearchTreeRemoveBranchValorRaiz(){
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(70);
            String renderInicial = arbol.render();
            arbol.removeBranch(50);
            assertEquals(renderInicial, "50(30,70)");
            assertEquals("", arbol.render());
        }

        @Test
        @DisplayName("removeBranch valor rama derecha")
        public void BinarySearchTreeRemoveBranchValorRamaDerecha(){
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(70);
            String renderInicial = arbol.render();
            arbol.removeBranch(70);
            assertEquals(renderInicial, "50(30,70)");
            assertEquals("50(30,)", arbol.render());
        }

        @Test
        @DisplayName("removeBranch valor rama izquierda")
        public void BinarySearchTreeRemoveBranchValorRamaIzquierda(){
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(70);
            String renderInicial = arbol.render();
            arbol.removeBranch(30);
            assertEquals(renderInicial, "50(30,70)");
            assertEquals("50(,70)", arbol.render());
        }

        @Test
        @DisplayName("removeBranch valor rama derecha larga")
        public void BinarySearchTreeRemoveBranchValorRamaDerechaLarga(){
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(70);
            arbol.insert(60);
            arbol.insert(80);
            String renderInicial = arbol.render();
            arbol.removeBranch(80);
            assertEquals(renderInicial, "50(30,70(60,80))");
            assertEquals("50(30,70(60,))", arbol.render());
        }

        @Test
        @DisplayName("removeBranch valor rama izquierda larga")
        public void BinarySearchTreeRemoveBranchValorRamaIzquierdaLarga(){
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(70);
            arbol.insert(20);
            arbol.insert(40);
            String renderInicial = arbol.render();
            arbol.removeBranch(20);
            assertEquals(renderInicial, "50(30(20,40),70)");
            assertEquals("50(30(,40),70)", arbol.render());
        }

        @Test
        @DisplayName("removeBranch valor rama derecha nula")
        public void BinarySearchTreeRemoveBranchValorRamaDerechaNula(){
            arbol.insert(50);
            arbol.insert(30);
            assertThrows(BinarySearchTreeException.class, () -> arbol.removeBranch(70));
        }

        @Test
        @DisplayName("removeBranch valor rama izquierda nula")
        public void BinarySearchTreeRemoveBranchValorRamaIzquierdaNula(){
            arbol.insert(50);
            arbol.insert(70);

            assertThrows(BinarySearchTreeException.class, () -> arbol.removeBranch(30));
        }
    }

    @Nested
    @DisplayName("Pruebas size")
    class SizeTests{

        @Test
        @DisplayName("size arbol vacio")
        public void BinarySearchTreeSizeArbolVacio(){
            assertEquals(0, arbol.size());
        }
        
        @Test
        @DisplayName("size hoja")
        public void BinarySearchTreeSizeHoja(){
            arbol.insert(50);
            assertEquals(1, arbol.size());
        }

        @Test
        @DisplayName("size arbol solo con rama izquierda")
        public void BinarySearchTreeSizeArbolIzquierdo(){
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(20);
            arbol.insert(40);
            assertEquals(4, arbol.size());
        }

        @Test
        @DisplayName("size arbol solo con rama derecha")
        public void BinarySearchTreeSizeArbolDerecho(){
            arbol.insert(50);
            arbol.insert(70);
            arbol.insert(60);
            arbol.insert(80);
            assertEquals(4, arbol.size());
        }

        @Test
        @DisplayName("size arbol completo")
        public void BinarySearchTreeSizeArbolCompleto(){
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(20);
            arbol.insert(40);
            arbol.insert(70);
            arbol.insert(60);
            arbol.insert(80);
            assertEquals(7, arbol.size());
        }
    }

    @Nested
    @DisplayName("Pruebas depth")
    class DepthTests{
        
    }

}