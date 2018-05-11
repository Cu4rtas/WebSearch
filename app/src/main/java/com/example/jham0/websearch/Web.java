/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.jham0.websearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        for(String s : palabrasClave) str += "," + str;
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
}
