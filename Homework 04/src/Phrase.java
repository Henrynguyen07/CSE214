import java.util.*;
/**
 * Phrase will extend queue interface class with java API ArrayLists
 * Methods include
 *
 * @Author: Henry Nguyen
 *      SBU ID: 111484010
 *      Recitation 08
 */

public class Phrase extends ArrayList<Bigram> implements Queue {
    /**
     * Adds Bigram to end of the queue
     * @param b
     */
    public void enqueue(Bigram b) {
        super.add(b);

    }

    /**
     * Removes front of queue
     * @return
     */
    public Bigram dequeue() {
        return super.remove(0);
    }

    /**
     * Returns fronts of queue
     * @return
     */
    public Bigram peek() {
        return super.get(0);
    }

    /**
     * Converts a string to a queue of Bigrams
     * @param s
     * @return
     *      A queue of Bigram
     */
    public static Phrase buildPhraseFromStringforEnc(String s) {
        Phrase phrase = new Phrase();
        s = s.toUpperCase();
        s = s.replaceAll("[^A-Z]", "");
        s += ' ';
        s.replaceAll("J", "I");
        for (int i = 0; i < s.length() - 1; i++) {
            Bigram newBigram = new Bigram();
            if (s.charAt(i) == s.charAt(i + 1) || s.charAt(i) != ' ' && s.charAt(i + 1) == ' ') {
                newBigram.setFirst(s.charAt(i));
                newBigram.setSecond('X');
                phrase.enqueue(newBigram);
            } else {
                newBigram.setFirst(s.charAt(i));
                newBigram.setSecond(s.charAt(i + 1));
                phrase.enqueue(newBigram);
                i++;
            }
        }
        return phrase;
    }

    /**
     * Encrypts this phrase, storing the encrypted bigrams in a new queue and returning
     * the new queue
     * @param key
     * @return
     *      Encrypted Bigram Queue
     * </dt>Precondition:
     *      Key is not null
     * @throws IllegalArgumentException
     *      Key is null
     */

    public Phrase encrypt(KeyTable key) throws IllegalArgumentException {
        Phrase encryptedQueue = new Phrase();
        Phrase originalQueue;
        try {
            if (key != null) {
                Scanner input = new Scanner(System.in);
                System.out.print("Please enter a phrase to encrypt: ");
                String inputKey = input.nextLine();
                originalQueue = buildPhraseFromStringforEnc(inputKey);

                while (!originalQueue.isEmpty()) {
                    Bigram temp = originalQueue.dequeue();
                    Bigram encrypted = new Bigram();
                    int tempFirstRow = 0;
                    int tempFirstColumn = 0;
                    int tempSecondRow = 0;
                    int tempSecondColumn = 0;

                    if (key.findRow(temp.getFirst()) == key.findRow(temp.getSecond())) {
                        tempFirstRow = key.findRow(temp.getFirst());
                        tempSecondRow = key.findRow(temp.getSecond());
                        tempFirstColumn = key.findCol(temp.getFirst()) + 1;
                        tempSecondColumn = key.findCol(temp.getSecond()) + 1;


                        if (tempFirstColumn == 5)
                            tempFirstColumn = 0;
                        if (tempSecondColumn == 5)
                            tempSecondColumn = 0;


                        encrypted.setFirst(key.getKeyTable()[tempFirstRow][tempFirstColumn]);
                        encrypted.setSecond(key.getKeyTable()[tempSecondRow][tempSecondColumn]);
                        encryptedQueue.add(encrypted);
                    } else if (key.findCol(temp.getFirst()) == key.findCol(temp.getSecond())) {
                        tempFirstRow = key.findRow(temp.getFirst()) + 1;
                        tempSecondRow = key.findRow(temp.getSecond()) + 1;
                        tempFirstColumn = key.findCol(temp.getFirst());
                        tempSecondColumn = key.findCol(temp.getSecond());
                        if (tempFirstRow == 5)
                            tempFirstRow = 0;
                        if (tempSecondRow == 5)
                            tempSecondRow = 0;

                        encrypted.setFirst(key.getKeyTable()[tempFirstRow][tempFirstColumn]);
                        encrypted.setSecond(key.getKeyTable()[tempSecondRow][tempSecondColumn]);
                        encryptedQueue.add(encrypted);
                    } else {
                        tempFirstRow = key.findRow(temp.getFirst());
                        tempSecondRow = key.findRow(temp.getSecond());
                        tempFirstColumn = key.findCol(temp.getFirst());
                        tempSecondColumn = key.findCol(temp.getSecond());
                        encrypted.setFirst(key.getKeyTable()[tempFirstRow][tempSecondColumn]);
                        encrypted.setSecond(key.getKeyTable()[tempSecondRow][tempFirstColumn]);
                        encryptedQueue.add(encrypted);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Key is Null");

        }
        return encryptedQueue;

    }

    /**
     * Decrypts this phrase, storing the encrypted bigrams in a new queue and returning
     * the new queue
     * @param key
     * @return
     *      Dncrypted Bigram Queue
     * </dt>Precondition:
     *      Key is not null
     * @throws IllegalArgumentException
     *      Key is null
     */
    public Phrase decrypt(KeyTable key) throws IllegalArgumentException {
        Phrase decryptedQueue = new Phrase();
        Phrase originalQueue;
        try {
            if (key != null) {
                Scanner input = new Scanner(System.in);
                System.out.print("Please enter a phrase to encrypt: ");
                String inputKey = input.nextLine();
                originalQueue = buildPhraseFromStringforEnc(inputKey);


                while (!originalQueue.isEmpty()) {
                    Bigram temp = originalQueue.dequeue();
                    Bigram decrypted = new Bigram();
                    int tempFirstRow = 0;
                    int tempFirstColumn = 0;
                    int tempSecondRow = 0;
                    int tempSecondColumn = 0;

                    if (key.findRow(temp.getFirst()) == key.findRow(temp.getSecond())) {
                        tempFirstRow = key.findRow(temp.getFirst());
                        tempSecondRow = key.findRow(temp.getSecond());
                        tempFirstColumn = key.findCol(temp.getFirst()) - 1;
                        tempSecondColumn = key.findCol(temp.getSecond()) - 1;

                        if (tempFirstColumn == -1) {
                            tempFirstColumn = 4;
                        }
                        if (tempSecondColumn == -1) {
                            tempSecondColumn = 4;
                        }

                        decrypted.setFirst(key.getKeyTable()[tempFirstRow][tempFirstColumn]);
                        decrypted.setSecond(key.getKeyTable()[tempSecondRow][tempSecondColumn]);
                        decryptedQueue.add(decrypted);
                    } else if (key.findCol(temp.getFirst()) == key.findCol(temp.getSecond())) {
                        tempFirstRow = key.findRow(temp.getFirst()) - 1;
                        tempSecondRow = key.findRow(temp.getSecond()) - 1;
                        tempFirstColumn = key.findCol(temp.getFirst());
                        tempSecondColumn = key.findCol(temp.getSecond());

                        if (tempFirstRow == -1) {
                            tempFirstRow = 4;
                        }
                        if (tempSecondRow == -1) {
                            tempSecondRow = 4;
                        }


                        decrypted.setFirst(key.getKeyTable()[tempFirstRow][tempFirstColumn]);
                        decrypted.setSecond(key.getKeyTable()[tempSecondRow][tempSecondColumn]);
                        decryptedQueue.add(decrypted);
                    } else {
                        tempFirstRow = key.findRow(temp.getFirst());
                        tempSecondRow = key.findRow(temp.getSecond());
                        tempFirstColumn = key.findCol(temp.getFirst());
                        tempSecondColumn = key.findCol(temp.getSecond());
                        decrypted.setSecond(key.getKeyTable()[tempSecondRow][tempFirstColumn]);
                        decrypted.setFirst(key.getKeyTable()[tempFirstRow][tempSecondColumn]);
                        decryptedQueue.add(decrypted);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: Key is Null");
        }
        return decryptedQueue;
    }
}

