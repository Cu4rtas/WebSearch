package com.example.jham0.websearch;

import android.util.Log;

import com.example.jham0.websearch.Tree.BinarySearchTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AppMain {

    private static Web webAModificar;

    private static BinarySearchTree<Web> webs = new BinarySearchTree<>();

    public static void insert(Web w){
        webs.insert(w);
    }

    public static BinarySearchTree<Web> getWebs(){  return webs;    }

    public static List<Web> getAllWebsWithWords(String... palabras){
        //YONOSÉNIQUÉESTOYHACIENDOCONMIVIDAHOY
        List<Web> listaPorPalabras =getWebsByKeyWords(palabras);
        List<Web> listaPorURL = getWebsByURL(palabras);

        return listaPorURL
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<Web> getWebsByKeyWords(String... palabras){
        return webs.getAllItems()
                .stream()
                .filter(web -> Arrays.asList(palabras)
                        .stream()
                        .anyMatch(palabra -> web.getKewWords()
                                .stream()
                                .anyMatch(s -> s.contains(palabra.toLowerCase()))))
                .collect(Collectors.toList());
    }

    public static List<Web> getWebsByURL(String... palabras){
        return webs.getAllItems()
                .stream()
                .filter(web -> Arrays.asList(palabras)
                        .stream()
                        .anyMatch(pal -> web.getURL().contains(pal)))
                .collect(Collectors.toList());
    }

    public static List<Web> getAllWebs(){
        return webs.getAllItems();
    }

    public static void modificar(Web nueva){
        webs.delete(webAModificar);
        webs.insert(nueva);
        webAModificar = null;
    }

    public static boolean isModifyingWeb(){
        return webAModificar != null;
    }

    public static void setWebAModificar(Web w){
        webAModificar = w;
        Log.i("WEB A MODIFICAR", w.toString());
    }
}
