package com.example.jham0.websearch.Tree;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author
 */
public class BinarySearchTree< E extends Comparable<? super E> >
        implements Iterable<E> {

    private class BinaryNode< T extends Comparable<? super T> > {

        public T item;
        public BinaryNode<T> left;
        public BinaryNode<T> right;

        public BinaryNode(T item) {
            this.item = item;
        }

    }

    private BinaryNode<E> root;

    public boolean find(E itemToFind) {
        BinaryNode<E> current = root;
        while(itemToFind.compareTo(current.item) != 0) {
            if(itemToFind.compareTo(current.item) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
            if(current == null) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<E> getAllItems(){
        ArrayList<E> list = new ArrayList<>();
        list = getAllItems(root, 0, list);
        return list;
    }

    private ArrayList<E> getAllItems(BinaryNode<E> node, int c, ArrayList<E> list){
        if (node != null){
            list.add(node.item);
        }
        if(node == null || node.left == null && node.right == null) return list;
        list = getAllItems( node.left, c+1, list );
        list = getAllItems( node.right, c+1, list );
        return list;
    }

    public BinarySearchTree<E> insert(E itemToInsert) {
        BinaryNode<E> newNode = new BinaryNode(itemToInsert);
        if(root == null) {
            root = newNode;
        } else {
            BinaryNode<E> parent = null;
            BinaryNode<E> current = root;
            while(true) {
                parent = current;
                if(itemToInsert.compareTo(current.item) < 0) {
                    current = current.left;
                    if(current == null) {
                        parent.left = newNode;
                        break;
                    }
                } else {
                    current = current.right;
                    if(current == null) {
                        parent.right = newNode;
                        break;
                    }
                }
            }
        }
        return this;
    }

    public void delete(E itemToDelete) {
        if (root == null) {
            System.out.println("El árbol esta vacío");
            return;
        }

        BinaryNode<E> parent = null;
        BinaryNode<E> current = root;
        Boolean isLeft = null;

        while (itemToDelete.compareTo(current.item) != 0) {
            parent = current;
            if (itemToDelete.compareTo(current.item) < 0) {
                current = current.left;
                isLeft = true;
            } else {
                current = current.right;
                isLeft = false;
            }

            if (current == null) {
                System.out.println("No se encontró el item a eliminar");
                return;
            }
        }

        // Caso 1: hoja
        if (current.left == null && current.right == null) {
            if (current != root) {
                if (isLeft) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            } else {
                root = null;
            }
        } else if (current.right == null) {
            // Caso 2: solo hijo izquierdo

            if (current == root) {
                root = current.left;
            } else if (isLeft) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else if (current.left == null) {
            // Caso 3: solo hijo derecho

            if (current == root) {
                root = current.right;
            } else if (isLeft) {
                parent.left = current.right;
            } else {
                parent.right = current.right;

            }
        } else {
            ArrayList<BinaryNode<E>> sucesor = getSucesor(current.right);
            BinaryNode<E> successor = sucesor.get(0);
            BinaryNode<E> successorParent = sucesor.get(1);
            successorParent.left = successor.right;
            successor.right = current.right;
            if(current == root) {
                root = successor;
            }
            else if(isLeft) parent.left = successor;
            else parent.right = successor;
        }

    }

    private ArrayList<BinaryNode<E>> getSucesor(BinaryNode<E> node) {
        if(node.left.left == null) {
            ArrayList<BinaryNode<E>> family = new ArrayList<>();
            family.add(node.left);
            family.add(node);
            return family;
        }
        else {
            return getSucesor(node.left);
        }
    }

    public String preorder() {
        return preorder(root, 0);
    }

    private String preorder(BinaryNode<E> node, int c) {
        String t = "";
        for(int i = 0; i < c; i++) {
            t += "---";
        }
        t += (node != null ? node.item : "") + "\n";
        if(node == null || node.left == null && node.right == null) return t;
        t = t + preorder( node.left, c+1 );
        t = t + preorder( node.right, c+1 );
        return t;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator() {

            private Queue<BinaryNode<E>> queue;

            {
                queue = new LinkedList();
                queue.add(root);
            }

            @Override
            public boolean hasNext() {
                return !queue.isEmpty();
            }

            @Override
            public Object next() {
                BinaryNode<E> node = queue.remove();
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
                return node.item;
            }

        };
    }
}

