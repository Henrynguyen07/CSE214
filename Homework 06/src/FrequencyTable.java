/**
 * FrequencyTable will hold all the words and theyre respective frequency in each passage
 *
 * @Author: Henry Nguyen
 * SBU ID: 111484010
 * Recitation 08
 */

import java.util.*;

public class FrequencyTable {
    /**
     * @param list
     *      Instance of frequencyList
     * @param frequencyTable
     *      instance of FrequencyTable
     */
    private static ArrayList<FrequencyList> list;
    private static FrequencyTable frequencyTable;

    /**
     * Constructor that will create a Table that would hold the frequency of each passage of x word
     * @param freqlist
     */
    public FrequencyTable(ArrayList<FrequencyList> freqlist) {
        this.list =  freqlist;
    }

    /**
     * creates a table that will add create a list that correlates to each frequencies of each word.
     * @param passages
     * @return
     */
    public static FrequencyTable buildTable(ArrayList<Passage> passages) {
        for (Passage i : passages) {
            for (String j : i.getWords()) {
                list.add(new FrequencyList(j,passages));
            }
        }
        frequencyTable = new FrequencyTable(list);
        return frequencyTable;
    }

    /**
     * adds new passage frequencies to word
     * @param p
     * @throws IllegalArgumentException
     *      Thrown when passage is null or empty
     */
    public void addPassage(Passage p) throws IllegalArgumentException {
        try {
            if (!p.isEmpty() || p == null) {
                for (String i : p.getWords()) {
                    for (FrequencyList j : list) {
                        if (j.getWord().equalsIgnoreCase(i)) {
                            j.addPassage(p);
                        }
                    }
                }
            } else {
                throw new IllegalArgumentException("Error: " + p.getTitle() + " is either null or empty");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public double getFrequencyWord(String word, Passage p) {
        return p.getWordFrequency(word);
    }

}
