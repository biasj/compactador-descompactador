/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compactador_v1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author beatrizsato
 */
public class Compactador {
    public static void leituraArquivo(String arquivo) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(arquivo));
        BufferedWriter writer = new BufferedWriter(new FileWriter("compactado.txt"));

        String linha;
        String linhaEntrada[];
        ListaEncadeada lista = new ListaEncadeada();
         
        try {
            // se r!= null significa que r está guardando referência do arquivo físico
            if(reader != null && writer != null) {
                linha = reader.readLine();
                while(linha != null && !linha.equals("0")) {
                    linhaEntrada = linha.split(" ");
                    
                    // para cada palavra na linha, conferir se está na lista e copiar a palavra ou a posição na lista para o arquivo
                    for(int i=0;i<linhaEntrada.length;i++) {
                        No temp = lista.buscaLinear(linhaEntrada[i]);
                        
                        // se palavra não estiver na lista
                        if(temp == null) {
                            // insere no início da lista
                            lista.insereInicio(linhaEntrada[i]);
                            
                            // escreve no arquivo compactado 
                            writer.write(linhaEntrada[i]);
                            
                        } else {
                            // pega a posição na lista e escreve no arquivo 
                            // como pegar a posição do No na lista?
                            int posicao = lista.buscaPosicao(linhaEntrada[i]);
                            writer.write(String.valueOf(posicao));
                            
                            // remove palavra da lista
                            lista.removePalavra(linhaEntrada[i]);
                            
                            // insere no início da lista
                            lista.insereInicio(linhaEntrada[i]);
                            
                            
                            
                        }
                    } 
                    
                    
                    // lê a próxima linha
                    linha = reader.readLine();
                }
                
            }
            // encerra comunicação entre arquivo lógico e físico, não encerra arquivo físico
            reader.close();
            writer.flush();
            writer.close();
            
            System.out.println(lista);
            
        } catch(Exception e) {
            System.exit(-1);
        }
    }
}
