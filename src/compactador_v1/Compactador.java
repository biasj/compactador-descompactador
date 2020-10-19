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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author beatrizsato
 */
public class Compactador {

    public static void compactarArquivo(String arquivo) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(arquivo));
        BufferedWriter writer = new BufferedWriter(new FileWriter("compactado.txt"));

        String linha, palavra, naoPalavra;
        String linhaEntrada[];
        ListaEncadeada lista = new ListaEncadeada();
        int PosicaoLinha;
        
        naoPalavra = "";
        
        try {
            
            if (reader == null || writer == null) {
                   return;
            }
                
            linha = reader.readLine();
            PosicaoLinha = 0;

            while (linha != null && !linha.equals("0")) {
                
                Pattern lpPalavra = Pattern.compile("[a-zA-Z]+");

                Matcher lmPalavra = lpPalavra.matcher(linha);

                Boolean lbExistePalavra;

                lbExistePalavra = lmPalavra.find();

                while (lbExistePalavra) {
                                        
                    // Se a palavra está no início da linha
                    if (lmPalavra.start() != PosicaoLinha) {
                        // se for pontuação ou espaço, escreve direto no arquivo
                        naoPalavra = linha.substring(PosicaoLinha, lmPalavra.start());
                        writer.write(naoPalavra);
                    }

                    // retirar a palavra da linha desde a posição start até end -1 e armazenar em uma variável
                    palavra = linha.substring(lmPalavra.start(), lmPalavra.end());

                    
                    // LÓGICA DE MANIPULAÇÃO DA LISTA E DO ARQUIVO
                    
                    // busca a palavra na lista
                    No temp = lista.buscaLinear(palavra);

                    // se palavra não estiver na lista
                    if (temp == null) {
                        // insere no início da lista
                        lista.insereInicio(palavra);

                        // escreve no arquivo compactado 
                        writer.write(palavra);

                    } else {
                        // pega a posição na lista e escreve no arquivo 
                        int posicao = lista.buscaPosicao(palavra);
                        writer.write(String.valueOf(posicao));

                        // remove palavra da lista
                        lista.removePalavra(palavra);

                        // insere no início da lista
                        lista.insereInicio(palavra);

                    }
                    
                    // atualizar a nova posição de leitura na linha
                    PosicaoLinha = lmPalavra.end();
                        
                    
                    lbExistePalavra = lmPalavra.find();

                }
                
                // se não for palavra, escreve direto no arquivo
                if (PosicaoLinha < linha.length()) {
                    naoPalavra = linha.substring(PosicaoLinha, linha.length());
                    writer.write(naoPalavra);
                }
                
                // insere a quebra de linha no arquivo
                writer.write("\n");
                // lê a próxima linha
                linha = reader.readLine();
                PosicaoLinha = 0;
            }

            // encerra comunicação entre arquivo lógico e físico, não encerra arquivo físico
            reader.close();
            writer.flush();
            writer.close();

            System.out.println(lista);

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public static void descompactarArquivo(String arquivo) throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(arquivo));
        BufferedWriter writer = new BufferedWriter(new FileWriter("descompactado.txt"));

        String linha;
        String linhaEntrada[];
        ListaEncadeada lista = new ListaEncadeada();
        
        try {
            
            if (reader != null && writer != null) {
                linha = reader.readLine();
                
                while (linha != null && !linha.equals("0")) {
                    
                    // quebra a linha e salva todas as palavras, espaços e pontuação
                    linhaEntrada = linha.split("\\b");

                    // para cada palavra na linha, conferir se está na lista e copiar a palavra ou a posição na lista para o arquivo
                    for (int i = 0; i < linhaEntrada.length; i++) {
                        
                        // se for letra (e não número)
                        if(linhaEntrada[i].matches("[A-Za-z]+")) {
                            No temp = lista.buscaLinear(linhaEntrada[i]);

                            // escreve no arquivo compactado 
                            writer.write(linhaEntrada[i]);
                            
                            // se palavra não estiver na lista
                            if (temp == null) {
                                // insere no início da lista
                                lista.insereInicio(linhaEntrada[i]);
                                
                            // se palavra estiver na lista
                            } else {
                                lista.removePalavra(linhaEntrada[i]);
                                lista.insereInicio(linhaEntrada[i]);
                            }
                        
                        // se a palavra for um número, já existe na lista
                        } else if(linhaEntrada[i].matches("[0-9]+")){
                            // pega a posição na lista e escreve no arquivo 
                            int posicao = Integer.parseInt(linhaEntrada[i]);
                            // busca a palavra pela posição na lista
                            No temp = lista.buscaPalavra(posicao);
                            
                            // escreve a palavra referente à posição no arquivo
                            writer.write(temp.getElemento());

                            // remove palavra da lista
                            lista.removePalavra(temp.getElemento());
                            
                            // insere no início da lista
                            lista.insereInicio(temp.getElemento());

                        // se for pontuação ou espaço
                        } else {
                            // escreve no arquivo
                            writer.write(linhaEntrada[i]);
                        }
                    }
                    
                    // insere a quebra de linha no arquivo
                    writer.write("\n");
                    // lê a próxima linha
                    linha = reader.readLine();
                }

            }
            // encerra comunicação entre arquivo lógico e físico, não encerra arquivo físico
            reader.close();
            writer.flush();
            writer.close();

            System.out.println(lista);

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
