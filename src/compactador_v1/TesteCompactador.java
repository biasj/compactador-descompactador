/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compactador_v1;

import java.io.IOException;

/**
 *
 * @author beatrizsato
 */
public class TesteCompactador {
    public static void main(String[] args) throws IOException {
        System.out.println("Compactador \n");
        Compactador.leituraArquivo("entrada.txt");
    }
}
