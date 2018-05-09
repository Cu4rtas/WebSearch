package com.example.jham0.websearch;

import com.example.jham0.websearch.Tree.BinarySearchTree;

public class AppMain {
    private static BinarySearchTree<Web> webs = new BinarySearchTree<>();

    public static void insert(Web w){
        webs.insert(w);
    }
}
