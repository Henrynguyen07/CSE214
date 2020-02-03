/**
 * Bigram class will be hold the 2 character sequence.
 *
 * @Author: Henry Nguyen
 *      SBU ID: 111484010
 *      Recitation 08
 */

public class Bigram  {
    /**
     * @param first
     *      First character
     * @param second
     *      Second character
     */
    private char first;
    private char second;
    public Bigram() {
    }

    /**
     * Takes a new character and sets the first character as that new character
     * @param newFirst
     *      Holds new first character
     */
    public void setFirst(char newFirst) {
        first = newFirst;
    }

    /**
     * Takes a new character and sets the second character as that new character
     * @param newSecond
     *      Holds new second character
     */
    public void setSecond(char newSecond) {
        second = newSecond;
    }

    /**
     *
     * @return
     *      First charactert
     */
    public char getFirst() {
        return first;
    }

    /**
     *
     * @return
     *      Second character
     */
    public char getSecond() {
        return second;
    }

    /**
     *
     * @return
     *      To String "First + Second"
     */
    public String toString() {
        return Character.toString(first) + Character.toString(second);
    }
}
