/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * 
 * Grupo:
 * Beatriz Sato
 * Gabriel Friedmann
 * Marcelo Frost Marchesan
 */
package compactador_v1;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author beatrizsato
 */
public class TesteCompactador {
    public static void main(String[] args) {
        Compactador c;
        
        c = new Compactador();
        
        try{
            c.compactarArquivo("entrada.txt");
        } catch (FileNotFoundException e){
            System.out.println(e);
        }
        catch (IOException e){
            System.out.println(e);
        }
        
        try{
            c.descompactarArquivo("entrada.txt");
        } catch (FileNotFoundException e){
            System.out.println(e);
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
}
