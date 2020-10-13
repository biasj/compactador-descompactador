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
        ListaEncadeada lista = new ListaEncadeada();
         
        try {
            // se r!= null significa que r está guardando referência do arquivo físico
            if(buffer != null) {
                linha = buffer.readLine();
                while(linha != null) {
                    linhaEntrada = linha.split(":");
                    
                    // se não for caracter especial, tem que ser for iterativo para pegar o índice
                    for(int i=0;i<linhaEntrada.length;i++) {
                        No temp = lista.buscaLinear(linhaEntrada[i]);
                        
                        // se palavra não estiver na lista
                        if(temp == null) {
                            // insere no início da lista
                            lista.insereInicio(linhaEntrada[i]);
                            
                            
                            // escreve no arquivo compactado BUFFERED WRITER 
                            
                            
                        } else {
                            // pega a posição na lista e escreve no arquivo BUFFEREDWRITER (i+1)
                            
                            
                            // insere no início da lista
                            lista.insereInicio(linhaEntrada[i]);
                            
                            // remove da posição da lista
                            lista.removeFinal();
                        }
                    } 
                    
                    
                    // lê a próxima linha
                    linha = buffer.readLine();
                }
                
            }
            // encerra comunicação entre arquivo lógico e físico, não encerra arquivo físico
            buffer.close();
            
            System.out.println(lista);
            
        } catch(Exception e) {
            System.exit(-1);
        }
    }
}
