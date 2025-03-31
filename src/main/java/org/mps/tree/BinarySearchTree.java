package org.mps.tree;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinarySearchTree<T> implements BinarySearchTreeStructure<T> {
    private Comparator<T> comparator;
    private T value;
    private BinarySearchTree<T> left;
    private BinarySearchTree<T> right;

    public String render(){
        String render = "";

        if (value != null) {
            render += value.toString();
        }

        if (left != null || right != null) {
            render += "(";
            if (left != null) {
                render += left.render();
            }
            render += ",";
            if (right != null) {
                render += right.render();
            }
            render += ")";
        }

        return render;
    }

    public BinarySearchTree(Comparator<T> comparator) {
        this.comparator = comparator;
        this.left = null;
        this.right = null;
        this.value = null;
    }

    @Override
    public void insert(T value) {

        if(value == null){
            throw new BinarySearchTreeException("ERROR: intentamos insertar un valor nulo");
        }

        if(this.value == null){
            this.value = value;
            this.left = null;
            this.right = null;
        }else{
            if(this.comparator.compare(this.value, value) < 0){
                if(this.right == null){
                    this.right = new BinarySearchTree<>(this.comparator);
                }

                this.right.insert(value);
            }else{
                if(this.left == null){
                    this.left = new BinarySearchTree<>(this.comparator);
                }

                this.left.insert(value);
            }
        }
    }

    @Override
    public boolean isLeaf() {
        return this.value != null && this.right == null && this.left == null;
    }

    @Override
    public boolean contains(T value) {
        boolean result = false;

        if(this.value == null || value == null){
            result = false;

        }else if(this.comparator.compare(this.value, value) < 0 && this.right != null){
            result = this.right.contains(value);

        }else if(this.comparator.compare(this.value, value) > 0 && this.left != null){
            result = this.left.contains(value);

        }else {
            result = this.value == value;
            
        }

        return result;
    }

    @Override
    public T minimum() {
        T minimo = this.value;

        if(this.left != null){
            minimo = this.left.minimum();
        }

        return minimo;
    }

    @Override
    public T maximum() {
        T maximo = this.value;

        if(this.right != null){
            maximo = this.right.maximum();
        }

        return maximo;
    }

    @Override
    public void removeBranch(T value){

        if(value == null){
            throw new BinarySearchTreeException("ERROR: intentamos borrar una rama con un valor nulo");
        }

        if(this.comparator.compare(this.value, value) < 0){
            if(this.right == null){
                throw new BinarySearchTreeException("ERROR: Valor no existe en el arbol");
            }

            if(this.right.value == value){
                this.right = null;
            }else {
                this.right.removeBranch(value);
            }

        }else if(this.comparator.compare(this.value, value) == 0){
            this.value = null;
            this.left = null;
            this.right = null;

        }else{
            if(this.left == null){
                throw new BinarySearchTreeException("ERROR: Valor no existe en el arbol");
            }

            if(this.left.value == value){
                this.left = null;
            }else {
                this.left.removeBranch(value);
            }
        }
    }

    @Override
    public int size() {
        int inc = 0;
        int izq = 0;
        int der = 0;

        if(this.value != null){
            inc = 1;
        }

        if(this.left != null){
            izq = this.left.size();
        }

        if(this.right != null){
            der = this.right.size();
        }

        return izq + inc + der;
    }

    @Override
    public int depth() {
        int result = 0;
        int left = 0;
        int right = 0;

        if (this.value != null){
            if(this.left != null){
                left = this.left.depth();
            }
            if(this.right != null){
                right = this.right.depth();
            }
            
            if(left > right){
                result = 1 + left;
            }else {
                result = 1 + right;
            }
        }

        return result;
    }

    // Complex operations
    // (Estas operaciones se incluirán más adelante para ser realizadas en la segunda
    // sesión de laboratorio de esta práctica)

    @Override
    public void removeValue(T value){

        if(value == null){
            throw new BinarySearchTreeException("ERROR: intentamos borrar un valor nulo del arbol");
        }

        if (this.value == null){
            throw new BinarySearchTreeException("ERROR: intentamos borrar un valor de un arbol vacio");
        }

        if(this.comparator.compare(value, this.value) < 0){
            if (this.left == null){
                throw new BinarySearchTreeException("ERROR: intentamos borrar un valor que no esta en el arbol");
            }
            this.left.removeValue(value);

        }else if(this.comparator.compare(value, this.value) == 0){

            if(this.right != null){
                T minimo = this.right.minimum();
                if(minimo != this.right.value) {
                    this.removeValue(minimo);
                    this.value = minimo;
                }else {
                    this.value = this.right.value;
                    this.right = this.right.right;
                }
            }else if(this.left != null){
                this.value = this.left.value;
                this.right = this.left.right;
                this.left = this.left.left;
            }else {
                this.value = null;
            }

        }else{
            if (this.right == null){
                throw new BinarySearchTreeException("ERROR: intentamos borrar un valor que no esta en el arbol");
            }
            this.right.removeValue(value);
        }
    }

    @Override
    public List<T> inOrder(){
        List<T> lista = new ArrayList<>();

        if(this.left != null){
            lista.addAll(this.left.inOrder());
        }

        if(this.value != null){
            lista.add(this.value);
        }
        
        if(this.right != null){
            lista.addAll(this.right.inOrder());
        }

        return lista;
    }

    @Override
    public void balance(){
        if (this.size() > 0){
            List<T> lista = this.inOrder();

            int medio   = (lista.size()-1)/2;
            
            this.removeBranch(this.value);
            
            this.value = lista.get(medio);
            lista.remove(medio);
            
            List<List<T>> listas = dividirLista(lista, this.value);
            List<T> listaIzq = listas.get(0);
            List<T> listaDer = listas.get(1);
            this.left  = balance(listaIzq);
            this.right = balance(listaDer);
        }
    }

    private BinarySearchTree<T> balance(List<T> lista){
        BinarySearchTree<T> tree = new BinarySearchTree<>(comparator);

        if(lista.size() == 0){
            tree = null;
        }else {
            int medio   = (lista.size()-1)/2;
    
            tree.value = lista.get(medio);
            lista.remove(medio);

            List<List<T>> listas = dividirLista(lista, tree.value);
            List<T> listaIzq = listas.get(0);
            List<T> listaDer = listas.get(1);
            tree.left  = balance(listaIzq);
            tree.right = balance(listaDer);
        }
        return tree;
    }

    private List<List<T>> dividirLista(List<T> lista, T central){
        
        List<List<T>> res = new ArrayList<>();
        List<T> listaIzq = new ArrayList<>();
        List<T> listaDer = new ArrayList<>();
        
        if (lista.size() > 0){

            for(int i = 0; i < lista.size(); i++){
                T temp = lista.get(i);
                if(this.comparator.compare(temp, central) <= 0){
                    listaIzq.add(temp);
                }else {
                    listaDer.add(temp);
                }
            }
            
        }
        res.add(listaIzq);
        res.add(listaDer);
        return res;
    }




}
