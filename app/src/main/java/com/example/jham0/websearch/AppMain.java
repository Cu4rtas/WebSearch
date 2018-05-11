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
        List<Web> listaPorPalabras = webs.getAllItems()
                                        .stream()
                                        .filter(web -> Arrays.asList(palabras)
                                                .stream()
                                                .anyMatch(palabra -> web.getKewWords()
                                                        .stream()
                                                        .anyMatch(s -> s.contains(palabra.toLowerCase()))))
                                                .collect(Collectors.toList());

        List<Web> listaPorURL = webs.getAllItems()
                .stream()
                .filter(web -> Arrays.asList(palabras)
                        .stream()
                        .anyMatch(pal -> web.getURL().contains(pal)))
                .collect(Collectors.toList());

        listaPorPalabras.forEach(w -> listaPorURL.add(w));

        return listaPorURL
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<Web> getAllWebs(){
        return webs.getAllItems();
    }
}
