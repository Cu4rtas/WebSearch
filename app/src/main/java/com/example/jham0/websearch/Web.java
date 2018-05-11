/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.jham0.websearch;

import com.example.jham0.websearch.Tree.BinarySearchTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
/**
 *
 * @author usuario
 */
public class Web implements Comparable<Web>{
    
    private final String preURL = "http://";
    private List<String> palabrasClave;
    private String URL;
    
    public Web(String URL, String... keyWords){
        init(URL);
        addKeyWords(keyWords);
    }
    
    /**
     * Constructor que recibe una URL por defecto
     */ 
    public Web(String URL){
        init(URL);
    }
    
    private void init(String URL){
        palabrasClave = new ArrayList();
        this.URL = preURL + URL;
    }

    public String getCuerpoURL(){
        return URL.substring(7);
    }
    public void setURL(String url){
        URL = preURL + url;
    }
    
    public String getURL(){
        return this.URL;
    }

    public String getPalabrasClaveConComa(){
        String str = "";
        for(String s : palabrasClave) str += "," + s;
        return str.replaceFirst(",", "");
    }
    
    public void addKeyWords(String... keyWords){
        (Arrays.asList(keyWords)).forEach(palabrasClave::add);
        palabrasClave = palabrasClave.stream().distinct().collect(Collectors.toList());
    }

    public List<String> getKewWords() {
        return palabrasClave;
    }

    @Override
    public int compareTo(Web web2compare) {
        return this.URL.compareTo((web2compare).getURL());
    }
    
    @Override
    public String toString(){
        return "URL: " + URL + ". Palabras clave [" + palabrasClave.toString() + "]";
    }

    public static BinarySearchTree<Web> randomsWebs(int limite) {
        ArrayList<Integer> letras = new ArrayList<>();
        BinarySearchTree<Web> aleatorias = new BinarySearchTree<>();
        char[] abc = new char[26];
        int numeroAleatorio;
        //Se crea un char[] con el abecedario
        for (int i = 0; i < 26; i++) {
            abc[i] = (char)('A'+i);
        }
        //Se llena la web con valores aleatorios
        for (int i = 0; i < limite; i++) {
            String[] palabras = new String[10];
            String url = "";
            for (int j = 0; j < 10; i++) {
                palabras[j] = "";
                for (int k = 0; k < 10; k++) {
                    numeroAleatorio = (int) (Math.random()*25+1);
                    url += abc[numeroAleatorio];
                    palabras[j] += abc[numeroAleatorio]+"";
                }
            }
            url += ".com";
            Web newWeb = new Web(url);
            newWeb.addKeyWords(palabras);
            aleatorias.insert(newWeb);
        }
        return aleatorias;
    }

    public static BinarySearchTree<String> randomWebs(int limite){
        BinarySearchTree<String> aleatorias = new BinarySearchTree<>();
        for (int i = 0; i < limite; i++) {
            aleatorias.insert(palabraAleatoria());
        }
        return aleatorias;
    }

    private static String palabraAleatoria() {
        String word = "";
        int numeroAleatorio;
        for (int j = 0; j < 3; j++) {
            numeroAleatorio = (int) (Math.random()*25+1);
            word += abedecario()[numeroAleatorio];
        }
        return word;
    }

    private static char[] abedecario() {
        char[] abc = new char[26];
        for (int i = 0; i < abc.length; i++) {
            abc[i] = (char)('A'+i);
        }
        return abc;
    }
}
