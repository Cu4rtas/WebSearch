package com.example.jham0.websearch;

import android.util.Log;

import com.example.jham0.websearch.Tree.BinarySearchTree;

import java.util.ArrayList;

public class AppMain {

    private static BinarySearchTree<Web> webs = new BinarySearchTree<>();

    public static void insert(Web w){
        webs.insert(w);
    }

    public static BinarySearchTree<Web> getWebs(){  return webs;    }

    public static ArrayList<Web> getWebsByPalabrasClave(String... palabras){
        for(Web w : webs.getAllItems()){
            Log.i("WEB", w.getURL());
        }
        return webs.getAllItems();
    }
}
