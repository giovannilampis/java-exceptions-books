package org.library.exceptions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	private static final String FILE_PATH = "/Users/giovannilampis/Desktop/JAVA EXPERIS/REPOSITORIES/java-exceptions-books/libri.txt";


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Numero di libri: ");
        int numBooks = Integer.parseInt(sc.nextLine());

        Libro[] catalogo = new Libro[numBooks];

        for (int i = 0; i < numBooks; i++) {
            try {
                System.out.print("Titolo: ");
                String titolo = sc.nextLine();

                System.out.print("Numero di pagine: ");
                int numPagine = Integer.parseInt(sc.nextLine());

                System.out.print("Autore: ");
                String autore = sc.nextLine();
                
                System.out.print("Editore: ");
                String editore = sc.nextLine();

                Libro libro = new Libro(titolo, numPagine, autore, editore);
                catalogo[i] = libro;
            } catch (Exception e) {
                System.err.println("Dati non validi: " + e.getMessage());
                i--;
            }
        }
        sc.close();

        System.out.println("\n------------------------------\n");

        FileWriter writer = null;
        try {
            writer = new FileWriter(FILE_PATH);
            for (Libro libro : catalogo) {
                writer.write(libro.getTitolo() + "\n");
            }
            System.out.println("Titoli dei libri scritti nel file " + FILE_PATH);
        } catch (IOException e) {
            System.err.println("Errore durante la scrittura del file: " + e.getMessage());
        } finally {
        	
        	  try {
                  if (writer != null) {
                      writer.close();
                  }
              } catch (IOException e) {
                  e.printStackTrace();
              }
          }

          // Leggi i titoli dei libri dal file
          File file = new File(FILE_PATH);
          Scanner reader = null;
          try {
              reader = new Scanner(file);
              System.out.println("\n------------------------------\n");
              System.out.println("Titoli dei libri letti dal file:");
              while (reader.hasNextLine()) {
                  String line = reader.nextLine();
                  System.out.println(line);
              }
          } catch (IOException e) {
              System.err.println("Errore durante la lettura del file: " + e.getMessage());
          } finally {
              if (reader != null) {
                  reader.close();
              }
          }
      }
}

