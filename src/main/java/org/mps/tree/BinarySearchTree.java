package org.mps.tree;

import java.util.Comparator;

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
        }else{
            if(this.comparator.compare(this.value, value) < 0){
                if(this.right == null){
                    this.right = new BinarySearchTree<>(this.comparator);
                }

                this.right.insert(value);
            }else if(this.comparator.compare(this.value, value) > 0){
                if(this.left == null){
                    this.left = new BinarySearchTree<>(this.comparator);
                }

                this.left.insert(value);
            }
        }
    }

    @Override
    public boolean isLeaf() {
        return this.value != null && this.right.value == null && this.left.value == null;
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

        if(this.value != null){
            if(this.comparator.compare(this.value, value) < 0 && this.right != null){
                this.right.removeBranch(value);

            }else if(this.comparator.compare(this.value, value) == 0){
                this.value = null;
                this.left = null;
                this.right = null;

            }else if(this.comparator.compare(this.value, value) > 0 && this.left != null){
                this.left.removeBranch(value);

            }
        }
    }

    @Override
    public int size() {
        int inc = 0;

        if(this.value != null){
            inc = 1;
        }

        return this.left.size() + inc + this.right.size();
    }

    @Override
    public int depth() {
        int result = 0;
        int left = 0;
        int right = 0;

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

        return result;
    }

    // Complex operations
    // (Estas operaciones se incluirán más adelante para ser realizadas en la segunda
    // sesión de laboratorio de esta práctica)
}
