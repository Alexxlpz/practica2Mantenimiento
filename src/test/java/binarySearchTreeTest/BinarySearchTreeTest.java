package binarySearchTreeTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.ArrayList;
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
            //Arrange
            //Act
            //Assert
            assertEquals("", arbol.render());
        }

        @Test
        @DisplayName("Render de arbol solo con raiz")
        public void BinarySearchTreeRenderArbolSoloRaiz(){
            //Arrange
            arbol.insert(50);
            //Act
            //Assert
            assertEquals("50", arbol.render());
        }

        @Test
        @DisplayName("Render de arbol solo con rama izquierda")
        public void BinarySearchTreeRenderArbolSoloIzquierda(){
            //Arrange
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(20);
            arbol.insert(40);
            //Act
            //Assert
            assertEquals("50(30(20,40),)", arbol.render());
        }

        @Test
        @DisplayName("Render de arbol solo con rama derecha")
        public void BinarySearchTreeRenderArbolSoloDerecha(){
            //Arrange
            arbol.insert(50);
            arbol.insert(70);
            arbol.insert(60);
            arbol.insert(80);
            //Act
            //Assert
            assertEquals("50(,70(60,80))", arbol.render());
        }

        @Test
        @DisplayName("Render de arbol completo")
        public void BinarySearchTreeRenderCompleto(){
            //Arrange
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(20);
            arbol.insert(40);
            arbol.insert(70);
            arbol.insert(60);
            arbol.insert(80);
            //Act
            //Assert
            assertEquals("50(30(20,40),70(60,80))", arbol.render());
        }
    }

    @Nested
    @DisplayName("Pruebas de insert")
    class InsertTests{

        @Test
        @DisplayName("Insert valor nulo")
        public void BinarySearchTreeInsertNull(){
            //Arrange
            //Act
            //Assert
            assertThrows(BinarySearchTreeException.class, () -> {arbol.insert(null);});
        }

        @Test
        @DisplayName("Insert valor no nulo sobre arbol vacio")
        public void BinarySearchTreeInsertInicialmenteNull(){
            //Arrange
            String renderInicial = arbol.render();
            //Act
            arbol.insert(50);
            //Assert
            assertEquals("", renderInicial);
            assertEquals("50", arbol.render());
        }

        @Test
        @DisplayName("Insert valor menor sobre hoja")
        public void BinarySearchTreeInsertValorMenorSobreHoja(){
            //Arrange
            arbol.insert(50);
            String renderInicial = arbol.render();
            //Act
            arbol.insert(30);
            //Assert
            assertEquals("50", renderInicial);
            assertEquals("50(30,)", arbol.render());
        }

        @Test
        @DisplayName("Insert valor mayor sobre hoja")
        public void BinarySearchTreeInsertValorMayorSobreHoja(){
            //Arrange
            arbol.insert(50);
            String renderInicial = arbol.render();
            //Act
            arbol.insert(70);
            //Assert
            assertEquals("50", renderInicial);
            assertEquals("50(,70)", arbol.render());
        }

        @Test
        @DisplayName("Insert valor menor sobre arbol")
        public void BinarySearchTreeInsertValorMenorSobreArbol(){
            //Arrange
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(70);
            String renderInicial = arbol.render();
            //Act
            arbol.insert(40);
            //Assert
            assertEquals("50(30,70)", renderInicial);
            assertEquals("50(30(,40),70)", arbol.render());
        }

        @Test
        @DisplayName("Insert valor mayor sobre arbol")
        public void BinarySearchTreeInsertValorMayorSobreArbol(){
            //Arrange
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(70);
            String renderInicial = arbol.render();
            //Act
            arbol.insert(60);
            //Assert
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
            //Arrange
            //Act
            //Assert
            assertFalse(arbol.isLeaf());
        }

        @Test
        @DisplayName("isLeaf sobre hoja")
        public void BinarySearchTreeIsLeafHoja(){
            //Arrange
            arbol.insert(50);
            //Act
            //Assert
            assertTrue(arbol.isLeaf());
        }

        @Test
        @DisplayName("isLeaf sobre rama")
        public void BinarySearchTreeIsLeafRama(){
            //Arrange
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(70);
            //Act
            //Assert
            assertFalse(arbol.isLeaf());
        }

        @Test
        @DisplayName("isLeaf sobre arbol con rama izq nada mas")
        public void BinarySearchTreeIsLeafRamaIzquierda(){
            //Arrange
            arbol.insert(50);
            arbol.insert(30);
            //Act
            //Assert
            assertFalse(arbol.isLeaf());
        }

        @Test
        @DisplayName("isLeaf sobre arbol con rama der nada mas")
        public void BinarySearchTreeIsLeafRamaDerecha(){
            //Arrange
            arbol.insert(50);
            arbol.insert(70);
            //Act
            //Assert
            assertFalse(arbol.isLeaf());
        }


    }

    @Nested
    @DisplayName("Pruebas contains")
    class ContainsTests{

        @Test
        @DisplayName("contains sobre arbol vacio")
        public void BinarySearchTreeContainsArbolVacio(){
            //Arrange
            //Act
            //Assert
            assertFalse(arbol.contains(50));
        }

        @Test
        @DisplayName("contains valor nulo")
        public void BinarySearchTreeContainsValorNulo(){
            //Arrange
            arbol.insert(50);
            //Act
            //Assert
            assertFalse(arbol.contains(null));
        }

        @Test
        @DisplayName("contains valor raiz")
        public void BinarySearchTreeContainsValorRaiz(){
            //Arrange
            arbol.insert(50);
            //Act
            //Assert
            assertTrue(arbol.contains(50));
        }

        @Test
        @DisplayName("contains valor rama derecha")
        public void BinarySearchTreeContainsValorRamaDerecha(){
            //Arrange
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(70);
            //Act
            //Assert
            assertTrue(arbol.contains(70));
        }

        @Test
        @DisplayName("contains valor rama izquierda")
        public void BinarySearchTreeContainsValorRamaIzquierda(){
            //Arrange
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(70);
            //Act
            //Assert
            assertTrue(arbol.contains(30));
        }

        @Test
        @DisplayName("contains valor rama derecha Nula")
        public void BinarySearchTreeContainsValorRamaDerechaNula(){
            //Arrange
            arbol.insert(50);
            arbol.insert(30);
            //Act
            //Assert
            assertFalse(arbol.contains(70));
        }

        @Test
        @DisplayName("contains valor rama izquierda Nula")
        public void BinarySearchTreeContainsValorRamaIzquierdaNula(){
            //Arrange
            arbol.insert(50);
            arbol.insert(70);
            //Act
            //Assert
            assertFalse(arbol.contains(30));
        }
    }

    @Nested
    @DisplayName("Pruebas minimum")
    class MinimumTests{

        @Test
        @DisplayName("minimum arbol vacio")
        public void BinarySearchTreeMinimumArbolVacio(){
            //Arrange
            //Act
            //Assert
            assertEquals(null, arbol.minimum());
        }

        @Test
        @DisplayName("minimum hoja")
        public void BinarySearchTreeMinimumHoja(){
            //Arrange
            arbol.insert(50);
            //Act
            //Assert
            assertEquals(50, arbol.minimum());
        }

        @Test
        @DisplayName("minimum rama sin hijo izquierdo")
        public void BinarySearchTreeMinimumRamaSinHijoIzquierdo(){
            //Arrange
            arbol.insert(50);
            arbol.insert(70);
            //Act
            //Assert
            assertEquals(50, arbol.minimum());
        }

        @Test
        @DisplayName("minimum rama con hijo izquierdo")
        public void BinarySearchTreeMinimumRamaConHijoIzquierdo(){
            //Arrange
            arbol.insert(50);
            arbol.insert(30);
            //Act
            //Assert
            assertEquals(30, arbol.minimum());
        }
    }

    @Nested
    @DisplayName("Pruebas maximum")
    class MaximumTests{

        @Test
        @DisplayName("maximum arbol vacio")
        public void BinarySearchTreeMaximumArbolVacio(){
            //Arrange
            //Act
            //Assert
            assertEquals(null, arbol.maximum());
        }

        @Test
        @DisplayName("maximum hoja")
        public void BinarySearchTreeMaximumHoja(){
            //Arrange
            arbol.insert(50);
            //Act
            //Assert
            assertEquals(50, arbol.maximum());
        }

        @Test
        @DisplayName("maximum rama sin hijo derecho")
        public void BinarySearchTreeMaximumRamaSinHijoDerecho(){
            //Arrange
            arbol.insert(50);
            arbol.insert(30);
            //Act
            //Assert
            assertEquals(50, arbol.maximum());
        }

        @Test
        @DisplayName("minimum rama con hijo derecho")
        public void BinarySearchTreeMaximumRamaConHijoDerecho(){
            //Arrange
            arbol.insert(50);
            arbol.insert(70);
            //Act
            //Assert
            assertEquals(70, arbol.maximum());
        }
    }

    @Nested
    @DisplayName("Pruebas removeBranch")
    class RemoveBranchTests{

        @Test
        @DisplayName("removeBranch valor nulo")
        public void BinarySearchTreeRemoveBranchValorNull(){
            //Arrange
            //Act
            //Assert
            assertThrows(BinarySearchTreeException.class, () -> {arbol.removeBranch(null);});
        }

        @Test
        @DisplayName("removeBranch valor raiz")
        public void BinarySearchTreeRemoveBranchValorRaiz(){
            //Arrange
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(70);
            String renderInicial = arbol.render();
            //Act
            arbol.removeBranch(50);
            //Assert
            assertEquals("50(30,70)", renderInicial);
            assertEquals("", arbol.render());
        }

        @Test
        @DisplayName("removeBranch valor rama derecha")
        public void BinarySearchTreeRemoveBranchValorRamaDerecha(){
            //Arrange
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(70);
            String renderInicial = arbol.render();
            //Act
            arbol.removeBranch(70);
            //Assert
            assertEquals("50(30,70)", renderInicial);
            assertEquals("50(30,)", arbol.render());
        }

        @Test
        @DisplayName("removeBranch valor rama izquierda")
        public void BinarySearchTreeRemoveBranchValorRamaIzquierda(){
            //Arrange
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(70);
            String renderInicial = arbol.render();
            //Act
            arbol.removeBranch(30);
            //Assert
            assertEquals("50(30,70)", renderInicial);
            assertEquals("50(,70)", arbol.render());
        }

        @Test
        @DisplayName("removeBranch valor rama derecha larga")
        public void BinarySearchTreeRemoveBranchValorRamaDerechaLarga(){
            //Arrange
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(70);
            arbol.insert(60);
            arbol.insert(80);
            String renderInicial = arbol.render();
            //Act
            arbol.removeBranch(80);
            //Assert
            assertEquals("50(30,70(60,80))", renderInicial);
            assertEquals("50(30,70(60,))", arbol.render());
        }

        @Test
        @DisplayName("removeBranch valor rama izquierda larga")
        public void BinarySearchTreeRemoveBranchValorRamaIzquierdaLarga(){
            //Arrange
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(70);
            arbol.insert(20);
            arbol.insert(40);
            String renderInicial = arbol.render();
            //Act
            arbol.removeBranch(20);
            //Assert
            assertEquals("50(30(20,40),70)", renderInicial);
            assertEquals("50(30(,40),70)", arbol.render());
        }

        @Test
        @DisplayName("removeBranch valor rama derecha nula")
        public void BinarySearchTreeRemoveBranchValorRamaDerechaNula(){
            //Arrange
            arbol.insert(50);
            arbol.insert(30);
            //Act
            //Assert
            assertThrows(BinarySearchTreeException.class, () -> arbol.removeBranch(70));
        }

        @Test
        @DisplayName("removeBranch valor rama izquierda nula")
        public void BinarySearchTreeRemoveBranchValorRamaIzquierdaNula(){
            //Arrange
            arbol.insert(50);
            arbol.insert(70);
            //Act
            //Assert
            assertThrows(BinarySearchTreeException.class, () -> arbol.removeBranch(30));
        }
    }

    @Nested
    @DisplayName("Pruebas size")
    class SizeTests{

        @Test
        @DisplayName("size arbol vacio")
        public void BinarySearchTreeSizeArbolVacio(){
            //Arrange
            //Act
            //Assert
            assertEquals(0, arbol.size());
        }
        
        @Test
        @DisplayName("size hoja")
        public void BinarySearchTreeSizeHoja(){
            //Arrange
            arbol.insert(50);
            //Act
            //Assert
            assertEquals(1, arbol.size());
        }

        @Test
        @DisplayName("size arbol solo con rama izquierda")
        public void BinarySearchTreeSizeArbolIzquierdo(){
            //Arrange
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(20);
            arbol.insert(40);
            //Act
            //Assert
            assertEquals(4, arbol.size());
        }

        @Test
        @DisplayName("size arbol solo con rama derecha")
        public void BinarySearchTreeSizeArbolDerecho(){
            //Arrange
            arbol.insert(50);
            arbol.insert(70);
            arbol.insert(60);
            arbol.insert(80);
            //Act
            //Assert
            assertEquals(4, arbol.size());
        }

        @Test
        @DisplayName("size arbol completo")
        public void BinarySearchTreeSizeArbolCompleto(){
            //Arrange
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(20);
            arbol.insert(40);
            arbol.insert(70);
            arbol.insert(60);
            arbol.insert(80);
            //Act
            //Assert
            assertEquals(7, arbol.size());
        }
    }

    @Nested
    @DisplayName("Pruebas depth")
    class DepthTests{

        @Test
        @DisplayName("depth arbol vacio")
        public void BinarySearchTreeDepthArbolVacio(){
            //Arrange
            //Act
            //Assert
            assertEquals(0, arbol.depth());
        }

        @Test
        @DisplayName("depth hoja")
        public void BinarySearchTreeDepthHoja(){
            //Arrange
            arbol.insert(50);
            //Act
            //Assert
            assertEquals(1, arbol.depth());
        }

        @Test
        @DisplayName("depth rama izquierda vacia")
        public void BinarySearchTreeDepthRamaIzquierdaVacia(){
            //Arrange
            arbol.insert(50);
            arbol.insert(70);
            arbol.insert(60);
            arbol.insert(80);
            //Act
            //Assert
            assertEquals(3, arbol.depth());
        }

        @Test
        @DisplayName("depth rama derecha vacia")
        public void BinarySearchTreeDepthRamaDerechaVacia(){
            //Arrange
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(20);
            arbol.insert(40);
            //Act
            //Assert
            assertEquals(3, arbol.depth());
        }

        @Test
        @DisplayName("depth rama derecha vacia")
        public void BinarySearchTreeDepthArbolCompleto(){
            //Arrange
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(20);
            arbol.insert(40);
            arbol.insert(70);
            arbol.insert(60);
            arbol.insert(80);
            //Act
            //Assert
            assertEquals(3, arbol.depth());
        }
    }

    @Nested
    @DisplayName("Pruebas removeValue")
    class RemoveValueTests{

        @Test
        @DisplayName("removeValue valor nulo")
        public void BinarySearchTreeRemoveValueValorNulo(){
            //Arrange
            //Act
            //Assert
            assertThrows(BinarySearchTreeException.class, () -> {arbol.removeValue(null);});
        }

        @Test
        @DisplayName("removeValue arbol vacio")
        public void BinarySearchTreeRemoveValueArbolVacio(){
            //Arrange
            //Act
            //Assert
            assertThrows(BinarySearchTreeException.class, () -> {arbol.removeValue(50);});
        }

        @Test
        @DisplayName("removeValue rama izquierda vacia")
        public void BinarySearchTreeRemoveValueRamaIzquierdaVacia(){
            //Arrange
            arbol.insert(50);
            arbol.insert(70);
            //Act
            //Assert
            assertThrows(BinarySearchTreeException.class, () -> {arbol.removeValue(30);});
        }

        @Test
        @DisplayName("removeValue rama derecha vacia")
        public void BinarySearchTreeRemoveValueRamaDerechaVacia(){
            //Arrange
            arbol.insert(50);
            arbol.insert(30);
            //Act
            //Assert
            assertThrows(BinarySearchTreeException.class, () -> {arbol.removeValue(70);});
        }

        @Test
        @DisplayName("removeValue hoja")
        public void BinarySearchTreeRemoveValueHoja(){
            //Arrange
            arbol.insert(50);
            String renderInicial = arbol.render();
            //Act
            arbol.removeValue(50);
            //Assert
            assertEquals("50", renderInicial);
            assertEquals("", arbol.render());
        }

        @Test
        @DisplayName("removeValue valor en raiz")
        public void BinarySearchTreeRemoveValueValorRaiz(){
            //Arrange
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(20);
            arbol.insert(40);
            arbol.insert(70);
            arbol.insert(60);
            arbol.insert(80);
            String renderInicial = arbol.render();
            //Act
            arbol.removeValue(50);
            //Assert
            assertEquals("50(30(20,40),70(60,80))", renderInicial);
            assertEquals("60(30(20,40),70(,80))", arbol.render());
        }

        @Test
        @DisplayName("removeValue valor en rama izquierda")
        public void BinarySearchTreeRemoveValueValorRamaIzquierda(){
            //Arrange
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(20);
            arbol.insert(40);
            arbol.insert(70);
            arbol.insert(60);
            arbol.insert(80);
            String renderInicial = arbol.render();
            //Act
            arbol.removeValue(30);
            //Assert
            assertEquals("50(30(20,40),70(60,80))", renderInicial);
            assertEquals("50(40(20,),70(60,80))", arbol.render());
        }

        @Test
        @DisplayName("removeValue valor en rama derecha")
        public void BinarySearchTreeRemoveValueValorRamaDerecha(){
            //Arrange
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(20);
            arbol.insert(40);
            arbol.insert(70);
            arbol.insert(60);
            arbol.insert(80);
            String renderInicial = arbol.render();
            //Act
            arbol.removeValue(70);
            //Assert
            assertEquals("50(30(20,40),70(60,80))", renderInicial);
            assertEquals("50(30(20,40),80(60,))", arbol.render());
        }

        @Test
        @DisplayName("removeValue valor de la raiz sin rama derecha vacia")
        public void BinarySearchTreeRemoveValueValorRaizSinRamaDerecha(){
            //Arrange
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(20);
            arbol.insert(40);
            String renderInicial = arbol.render();
            //Act
            arbol.removeValue(50);
            //Assert
            assertEquals("50(30(20,40),)", renderInicial);
            assertEquals("30(20,40)", arbol.render());
        }

    }

    @Nested
    @DisplayName("Pruebas inOrder")
    class InOrderTests{
        
        @Test
        @DisplayName("inOrder arbol vacio")
        public void BinarySearchTreeInOrderArbolVacio(){
            //Arrange
            //Act
            //Assert
            assertEquals(new ArrayList<>(), arbol.inOrder());
        }

        @Test
        @DisplayName("inOrder hoja")
        public void BinarySearchTreeInOrderHoja(){
            //Arrange
            arbol.insert(50);
            List<Integer> lista = new ArrayList<>();
            lista.add(50);
            //Act
            //Assert
            assertEquals(lista, arbol.inOrder());
        }

        @Test
        @DisplayName("inOrder sin rama izquierda")
        public void BinarySearchTreeInOrderSinRamaIzquierda(){
            //Arrange
            arbol.insert(50);
            arbol.insert(70);
            arbol.insert(60);
            arbol.insert(80);
            List<Integer> lista = new ArrayList<>();
            lista.add(50);
            lista.add(60);
            lista.add(70);
            lista.add(80);
            //Act
            //Assert
            assertEquals(lista, arbol.inOrder());
        }

        @Test
        @DisplayName("inOrder sin rama derecha")
        public void BinarySearchTreeInOrderSinRamaDerecha(){
            //Arrange
            arbol.insert(50);
            arbol.insert(70);
            arbol.insert(60);
            arbol.insert(80);
            arbol.insert(30);
            arbol.insert(20);
            arbol.insert(40);
            List<Integer> lista = new ArrayList<>();
            lista.add(20);
            lista.add(30);
            lista.add(40);
            lista.add(50);
            lista.add(60);
            lista.add(70);
            lista.add(80);
            //Act
            //Assert
            assertEquals(lista, arbol.inOrder());
        }

        @Test
        @DisplayName("inOrder arbol completo")
        public void BinarySearchTreeInOrderArbolCompleto(){
            //Arrange
            arbol.insert(50);
            arbol.insert(30);
            arbol.insert(20);
            arbol.insert(40);
            List<Integer> lista = new ArrayList<>();
            lista.add(20);
            lista.add(30);
            lista.add(40);
            lista.add(50);
            //Act
            //Assert
            assertEquals(lista, arbol.inOrder());
        }
    }

    @Nested
    @DisplayName("Pruebas balance")
    class BalanceTests{

        @Test
        @DisplayName("balance arbol vacio")
        public void BinarySearchTreeBalanceArbolVacio(){
            //Arrange
            //Act
            arbol.balance();
            //Assert
            assertEquals("", arbol.render());
        }

        @Test
        @DisplayName("balance hoja")
        public void BinarySearchTreeBalanceHoja(){
            //Arrange
            arbol.insert(50);
            //Act
            arbol.balance();
            //Assert
            assertEquals("50", arbol.render());
        }

        @Test
        @DisplayName("balance arbol izquierda")
        public void BinarySearchTreeBalanceArbolIzquierda(){
            //Arrange
            arbol.insert(80);
            arbol.insert(70);
            arbol.insert(60);
            arbol.insert(50);
            arbol.insert(40);
            arbol.insert(30);
            arbol.insert(20);
            arbol.insert(10);
            String renderInicial = arbol.render();
            //Act
            arbol.balance();
            //Assert
            assertEquals("80(70(60(50(40(30(20(10,),),),),),),)", renderInicial);
            assertEquals("40(20(10,30),60(50,70(,80)))", arbol.render());
        }

        @Test
        @DisplayName("balance arbol derecha")
        public void BinarySearchTreeBalanceArbolDerecha(){
            //Arrange
            arbol.insert(10);
            arbol.insert(20);
            arbol.insert(30);
            arbol.insert(40);
            arbol.insert(50);
            arbol.insert(60);
            arbol.insert(70);
            arbol.insert(80);
            String renderInicial = arbol.render();
            //Act
            arbol.balance();
            //Assert
            assertEquals("10(,20(,30(,40(,50(,60(,70(,80)))))))", renderInicial);
            assertEquals("40(20(10,30),60(50,70(,80)))", arbol.render());
        }
    }

}