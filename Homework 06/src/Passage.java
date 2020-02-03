/**
 * Passage will contain all the behaviors of each passage
 *
 *
 * @Author: Henry Nguyen
 *      SBU ID: 111484010
 *      Recitation 08
 */

import java.io.*;
import java.util.*;

public class Passage extends Hashtable<String, Integer> {
    /**
     * @param suspectedAuthor
     *      String of suspected Authors
     * @param title
     *      String of title
     * @param wordCount
     *      Counter of total words in textfile
     * @param wordList
     *      Set of unique words
     * @param stopWords
     *      Set of stop words
     * @param similarTitles
     *      holds the similarity of word
     */
    private static String suspectedAuthor = "";
    private String title;
    private int wordCount;
    private Set<String> wordList;
    private Set<String> stopWords;
    static Hashtable<String, Double> similarTitles;

    /**
     * Constructor for text files that is not the stop word file. parseStopWord method is called which reads all stop words in file;
     * @param title
     *      Holds reference of title file name
     * @param file
     *      Holds reference of text file
     * @param stopWords
     *      Holds a set of unique stop words.
     * @throws IOException
     */
    public Passage(String title, File file, Set<String> stopWords) throws IOException {
        this.title = title;
        this.stopWords = stopWords;
        wordList = new HashSet<String>();
        parseFile(file);
    }

    /**
     * Constructor for stopWords text file. method parseFile is called which will
     * @param title
     *      Holds reference of title file name
     * @param file
     *      Holds reference of text file
     * @throws IOException
     */
    public Passage(String title, File file) throws IOException {
        this.title = title;
        this.wordList = new HashSet<String>();
        parseStopWords(file);
    }

    /**
     * Will Reads line by line of the stop words text file
     * @param file
     * @throws IOException
     */
    public void parseStopWords(File file) throws IOException {
        String[] wordArray = null;
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String temp;

        while ((temp = br.readLine()) != null) {
            wordArray = temp.replaceAll("[^a-zA-Z ]", "").split(" ");
            for (String c : wordArray) {
                wordList.add(c);
            }
        }
        stopWords = wordList;
        fr.close();
    }

    /**
     * Will read line by line of the text file and store it into the hashtable
     * @param file
     * @throws IOException
     */
    public void parseFile(File file) throws IOException {
        String[] wordArray = null;
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String temp;
        while ((temp = br.readLine()) != null) {
            wordArray = temp.replaceAll("[^a-zA-Z ]", "").toLowerCase().split(" ");
            for (String c : wordArray) {
                if (c != null && !c.equalsIgnoreCase("") && !stopWords.contains(c)) {
                    wordCount++;
                    Integer j = this.get(c);
                    if (j == null) {
                        this.put(c, 1);
                    } else {
                        this.put(c, j + 1);
                    }
                }
            }
        }
        fr.close();
    }

    /**
     * Will calculate the simiularity of each word frequency. and store each frequency in hashtable in similarTitles.
     * The formula is provided in the homework manual. If a similarity is greater > 0, it will be printed
     * @param passage1
     *      Holds reference of passage 1
     * @param passage2
     *      Holds reference of passage 2 which will be the comparator
     * @return result
     *      similarity %
     */
    public static double cosineSimilarity(Passage passage1, Passage passage2) {
        ArrayList<String> words1 = new ArrayList<>(passage1.getWords());
        ArrayList<String> words2 = new ArrayList<>(passage2.getWords());
        double top = 0;
        double bottom1 = 0;
        double bottom2 = 0;
        for (int i = 0; i < words1.size(); i++) {
            if (passage1.get(words1.get(i)) != null) {
                if (passage2.get(words1.get(i)) != null) {
                    top += passage1.getWordFrequency(words1.get(i)) * passage2.getWordFrequency(words1.get(i));
                }
            }
        }
        for (int i = 0; i < words1.size(); i++)
            bottom1 += Math.pow(passage1.getWordFrequency(words1.get(i)), 2);
        for (int j = 0; j < words2.size(); j++)
            bottom2 += Math.pow(passage2.getWordFrequency(words2.get(j)), 2);
        bottom1 = Math.sqrt(bottom1);
        bottom2 = Math.sqrt(bottom2);
        double result = Math.round((top / (bottom1 * bottom2)) * 100);
        similarTitles.put(passage2.getTitle().substring(0, passage2.getTitle().indexOf(".txt")), result);
        if (result >= 60) {
            suspectedAuthorString(passage1.getTitle(), passage2.getTitle(), result);
        }
        return result;
    }
/**
 * Returns word Frequency
 */
    public double getWordFrequency(String word) {
        return (double) this.get(word) / this.wordCount;
    }

    /**
     * Unique set of words
     * @return
     */
    public Set<String> getWords() {
        return this.keySet();
    }

    /**
     * Returns title
     * @return
     */
    public String getTitle() {
        return title;
    }


    public Set<String> getStopWords() {
        return stopWords;
    }

    public Hashtable<String, Double> getSimilarTitles() {
        return similarTitles;
    }

    public void setWordCount(int newWordCount) {
        this.wordCount = newWordCount;
    }

    public void setSimilarTitles(Hashtable newSimilarTitles) {
        this.similarTitles = (Hashtable<String, Double>) newSimilarTitles;
    }

    public void setStopWords(Set<String> newStopWords) {
        stopWords = newStopWords;
    }

    /**
     * To String method that would print out the similarities Hashtable
     * @return
     */
    public String toString() {
        String result = "";
        for (String i : similarTitles.keySet()) {
            result += i + " (" + similarTitles.get(i) + "%), ";
        }
        return result;
    }

    /**
     * if a percentage is higher than 60%, a string will be printed which will represent them to be suspected author
     * @param title1
     * @param title2
     * @param similiarity
     * @return
     */
    public static String suspectedAuthorString(String title1, String title2, Double similiarity) {
        suspectedAuthor += title1 + " and" + title2 + " may have the same author (" + similiarity + "% similar)\n";
        return suspectedAuthor;
    }

    public static String getSuspectedAuthor() {
        return suspectedAuthor;
    }


}
