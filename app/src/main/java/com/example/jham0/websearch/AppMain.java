package com.example.jham0.websearch;

import android.util.Log;

import com.example.jham0.websearch.Tree.BinarySearchTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AppMain {
    private static BinarySearchTree<Web> webs = new BinarySearchTree<>();

    public static void insert(Web w){
        webs.insert(w);
    }

    public static BinarySearchTree<Web> getWebs(){  return webs;    }

    public static List<Web> getWebsByPalabrasClave(String... palabras){
        //YONOSÉNIQUÉESTOYHACIENDOCONMIVIDAHOY
        return (webs.getAllItems()
                .stream()
                .filter(web -> web.getKewWords()
                        .stream()
                        .anyMatch(palabra -> !Arrays.asList(palabras).contains(palabra.toLowerCase()))).collect(Collectors.toList()));
    }

    public static List<Web> getAllWebs(){
        return webs.getAllItems();
    }
}
