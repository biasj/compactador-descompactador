/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compactador_v1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author beatrizsato
 */
public class Compactador {
    public static void leituraArquivo(String arquivo) throws FileNotFoundException, IOException {
        BufferedReader buffer = new BufferedReader(new FileReader(arquivo));
        String linha;
        String linhaEntrada[];
        ListaEncadeada lista;
        
        try {
            // se r!= null significa que r está guardando referência do arquivo físico
            if(buffer != null) {
                linha = buffer.readLine();
                while(linha != null) {
                    linhaEntrada = linha.split(":");
                    
//                    for(String s: linhaEntrada) {
//                        lista.buscaLinear(s);
//                    }
                    
                    
                    // lê a próxima linha
                    linha = buffer.readLine();
                    System.out.println("hello world");
                }
                
            }
            // encerra comunicação entre arquivo lógico e físico, não encerra arquivo físico
            buffer.close();
            
        } catch(Exception e) {
            System.exit(-1);
        }
    }
}
