
/**
 * FruquencyList will will hold all the frequencies of each passage
 *
 *
 * @Author: Henry Nguyen
 *      SBU ID: 111484010
 *      Recitation 08
 */
import java.util.*;

public class FrequencyList {
    /**
     * @param word
     *      Holds reference of word
     * @param frequencies
     *      Holds reference of frequencies of word in each passage
     * @param passageIndicies
     *      Holds refernece of passage indexes
     */
    private String word;
    private ArrayList<Double> frequencies;
    private Hashtable<String, Integer> passageIndices = new Hashtable<String, Integer>();

    public FrequencyList(String word, ArrayList<Passage> passages) {
        this.word = word;
        for (int i = 0; i < passages.size(); i++) {
            frequencies.add(passages.get(i).getWordFrequency(word));
            passageIndices.put(passages.get(i).getTitle(), i);
        }

    }

    public void addPassage(Passage p) {
        frequencies.add(p.getWordFrequency(word));
        passageIndices.put(p.getTitle(),passageIndices.size() + 1);
    }
    public String getWord() {
        return word;
    }
}
