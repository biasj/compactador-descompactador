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
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 *
 * @author beatrizsato
 */
public class Compactador {
    public static void leituraArquivo(String arquivo) throws FileNotFoundException, IOException {
        BufferedReader buffer = new BufferedReader(new FileReader(arquivo));
        
        String lsLinha;
        String linhaEntrada[];
        ListaEncadeada lista = new ListaEncadeada();
         
        try {
            // se r!= null significa que r está guardando referência do arquivo físico
            if(buffer != null) {
                
                lsLinha = buffer.readLine();
                
//linhaEntrada = lsLinha.split("[^A-z]");
                
                Pattern lpPalavra = Pattern.compile("[a-zA-Z]*");
                Pattern lpNaoPalavra = Pattern.compile("[^ a-zA-Z]*");
                
                Matcher lmPalavra = lpPalavra.matcher(lsLinha);
                Matcher lmNaoPalavra = lpNaoPalavra.matcher(lsLinha);

                Boolean lbExistePalavra, lbExisteNaoPalavra;
                int liPosPalavra, liPosNaoPalavra;
                
                lbExistePalavra = lmPalavra.find();
                lbExisteNaoPalavra = lmNaoPalavra.find();
                
                while(lbExistePalavra || lbExisteNaoPalavra){
                    
                    if (lbExistePalavra && lbExisteNaoPalavra){
                        
                        if (lmPalavra.start() < lmNaoPalavra.start()) {
                            System.out.println("Posição da Palavra: " + lmPalavra.start() + "Termina em: " + lmPalavra.end());
                            lbExistePalavra = lmPalavra.find(lmPalavra.end()+1);
                        } else {
                            System.out.println("Posição da Não Palavra: " + lmNaoPalavra.start() + "Termina em: " + lmNaoPalavra.end());
                            lbExisteNaoPalavra = lmNaoPalavra.find(lmNaoPalavra.end()+1);
                        }
                    } else if (lbExistePalavra){
                        System.out.println("Posição da Palavra: " + lmPalavra.start() + "Termina em: " + lmPalavra.end());
                        lbExistePalavra = lmPalavra.find(lmPalavra.end()+1);
                    } else if (lbExisteNaoPalavra){
                        System.out.println("Posição da Não Palavra: " + lmNaoPalavra.start() + "Termina em: " + lmNaoPalavra.end());
                        lbExisteNaoPalavra = lmNaoPalavra.find(lmNaoPalavra.end()+1);
                    }
                    
                }
                
                /*
                for (String lsPalavra : linhaEntrada){
                    System.out.println(lsPalavra);
                }
                */
               /* 
                while() {
                        
                    
                    // se não for caracter especial
                    for(String s: linhaEntrada) {
                        No temp = lista.buscaLinear(s);
                        // se palavra não estiver na lista
                        if(temp == null) {
                            // insere no início da lista
                            // escreve no arquivo compactado
                        } else {
                            // pega a posição na lista e escreve no arquivo
                            // remove da posição da lista
                            // insere no início da lista
                        }
                    } 
                    
                    
                    // lê a próxima linha
                    lsLinha = buffer.readLine();
                    System.out.println("hello world");
                }*/

                
            }
            // encerra comunicação entre arquivo lógico e físico, não encerra arquivo físico
            buffer.close();
            
        } catch (FileNotFoundException e){
            System.out.println(e);
        }
        catch (IOException e){
            System.out.println(e);
        }
    }
}
