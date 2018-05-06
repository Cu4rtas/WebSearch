package com.example.jham0.websearch.Tree;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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

