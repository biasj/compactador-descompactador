/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compactador_v1;

import java.util.Scanner;


/**
 *
 * @author beatrizsato
 */
public class ListaEncadeada {
    private No ini;

    public ListaEncadeada() {
        this.ini = null;
    }

    public No getIni() {
        return ini;
    }
    
    public boolean vazia() {
        return this.ini == null;
    }

    @Override
    public String toString() {
        String lista = "";
        No temp = ini;
        while (temp != null) {
            lista += temp.getElemento() + " ";
            temp = temp.getProx();
        }
        
        return lista;
    }
    
    // inserção no início
    public void insereInicio(String elemento) {
        No novo = new No(elemento, ini);
        ini = novo;
    }
    
    public No buscaLinear(String elemento){
        No temp = ini;
        while(temp != null) {
            if (elemento.equals(temp.getElemento())) {
                return temp;
            }
            temp = temp.getProx();
        }
        
        return null;
    }
    
    // remoção no final -> acha onde prox = null e deixa o prox do anterior como nó
    public void removeFinal() {
        if(vazia()){
            System.out.println("Lista vazia");
            return;
        }
        
        No temp = ini;
        No anterior = null;
        while(temp.getProx() != null) {
            anterior = temp;
            temp = temp.getProx();
        }
        
        if(anterior == null) {
            ini = null; 
            return;
        }
        
        anterior.setProx(null);
    }
    
    public int buscaPosicao(String elemento){
        No temp = ini;
        int cont = 0;
        while(temp != null) {
            cont++;
            if (elemento.equals(temp.getElemento())) {
                return cont;
            }
            temp = temp.getProx();
        }
        
        return 0;
    }
    
    public void removePalavra(String palavra) {
        No temp = ini;
        No anterior = null;

        while(temp != null && !temp.getElemento().equals(palavra)) {
            anterior = temp;
            temp = temp.getProx();
        }
        
        // remove no inicio 1 ou mais nós
        if(anterior == null) {
            ini = ini.getProx();
            return;
        }
        
        if(palavra.equals(temp.getElemento())) {
            anterior.setProx(temp.getProx());
            return;
        }
        
        if(temp == null) {
            System.out.println("Elemento não está na lista");
            return;
        }
    }
}
