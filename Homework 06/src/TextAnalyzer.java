/**
 * TextAnalyzer is the driver for this class. It will be responsible of reading all text files and parsing each individual text file.
 * Prgoram will first look for the stopwords Text file and then create new Passages with a reference of StopWords
 *
 *
 * @Author: Henry Nguyen
 *      SBU ID: 111484010
 *      Recitation 08
 */

import java.util.*;
import java.io.*;

public class TextAnalyzer {
    public static void main(String[] args) throws IOException {
        ArrayList<Passage> passageArrayList = new ArrayList<>();
        Passage stopWords = null;
        String directoryPath = "";

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the directory: ");
//        directoryPath = "C:\\Users\\Henry\\OneDrive - Stony Brook University\\Fall 2019\\CSE 214\\Homework 06\\passageFolder";
        directoryPath = sc.nextLine();

        File[] directoryOfFiles = new File(directoryPath).listFiles();

        System.out.format("%-30s%20s", "Text (Title)", "| Similarities (%) \n");
        System.out.println("------------------------------------------------------------------------------------------------------------------------" +
                "------------------------");

        for (File i : directoryOfFiles) {
            if (i.getName().equalsIgnoreCase("StopWords.txt"))
                stopWords = new Passage(i.getName(), i);
        }
        for (File i : directoryOfFiles) {
            try {
                if (!i.getName().equalsIgnoreCase("StopWords.txt")) {
                    if (i.length() > 0 || i == null)
                        passageArrayList.add(new Passage(i.getName(), i, stopWords.getStopWords()));
                    else {
                        throw new IllegalArgumentException();
                    }
                }
            } catch (Exception e) {
                System.out.println("Error: " + i.getName() + " is empty or Null");
            }
        }
        for (Passage i : passageArrayList) {
            Passage.similarTitles = new Hashtable<>();
            for (Passage j : passageArrayList) {
                if (i != j) {
                    Passage.cosineSimilarity(i, j);
                }
            }
            System.out.format("%-30s|%50s", i.getTitle().substring(0, i.getTitle().indexOf(".txt")), " " + i.toString());
            System.out.println();
        }
        System.out.println("\nSuspected Texts With Same Authors");
        System.out.println("------------------------------------------------------------------------------------------------------------------------" +
                "------------------------");
        System.out.println(Passage.getSuspectedAuthor());
        System.out.println("Program Terminating....");

    }
}


