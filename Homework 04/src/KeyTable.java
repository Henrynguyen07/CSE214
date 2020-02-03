import java.util.*;
/**
 * KeyTable will be responsible for the plaintext key matrix. Methods include: buildFromString, getKeyTable, findRow, findCol.
 * Parameters include a 5x5 matrix called "key".
 *
 * @Author: Henry Nguyen
 *      SBU ID: 111484010
 *      Recitation 08
 */
public class KeyTable {
    /**
     * @param key
     *      [row][column] matrix
     */
    private static char[][] key;

    public KeyTable() {
        key = new char[5][5];
    }

    /**
     *  Builds a new KeyTable object from a string and returns it
     * @param keyphrase
     *      User input
     * @return
     *      keyTable
     * </dt>Precondition
     *      Keyphrase is not null
     * @throws IllegalArgumentException
     *      keyphrase is null
     */
    public static KeyTable buildFromString(String keyphrase) throws IllegalArgumentException {
        KeyTable keyTable = new KeyTable();
        try {
            int character = 65;
            for (int k = 0; k < 26; k++) {
                if (character != 74) {
                    keyphrase += (char) character;
                }
                character++;
            }
            keyphrase = keyphrase.replaceAll("[^A-Z]", "");
            keyphrase = removeDuplicate(keyphrase);
            try {
                int k = 0;
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (k < keyphrase.length())
                            keyTable.key[i][j] = keyphrase.charAt(k);
                        k++;
                    }
                }
            } catch (Exception e) {
                System.out.println("Error: Keyphrase cant be empty!");
            }
        } catch (Exception e) {
            System.out.println("Error: Keyphrase is null");
        }
        return keyTable;
    }

    /**
     *
     * @return
     *      Key matrix
     */
    public char[][] getKeyTable() {
        return key;
    }

    /**
     * Returns the row in which c occurs
     * @param c
     * @return
     *      Index of character row
     * <dt>Precondition: c is a valid character
     * @throws IllegalArgumentException
     *      c is not a valid letter in the key matrix
     */
    public int findRow(char c) throws IllegalArgumentException {
        int row = 0;
        try {
            if (Character.isLetter(c)) {
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (key[i][j] == c) {
                            row = i;
                            break;
                        }
                    }
                }
            }
            return row;
        } catch (Exception e) {
            System.out.println("Character is not a valid letter in the key Matrix");
            return 0;
        }
    }

    /**
     * Returns the row in which c occurs
     * @param c
     * @return
     *      Index of character row
     * <dt>Precondition: c is a valid character
     * @throws IllegalArgumentException
     *      c is not a valid letter in the key matrix
     */
    public int findCol(char c) throws IllegalArgumentException {
        int column = 0;
        try {
            if (Character.isLetter(c)) {

                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        if (key[i][j] == c) {
                            column = j;
                            break;
                        }
                    }
                }
            }
            return column;
        } catch (Exception e) {
            System.out.println("Character is not a valid letter in the key Matrix");
            return 0;
        }
    }

    /**
     * @return
     *      Prints out matrix
     */
    public String toString()
    {
        String str = "";

        for (int i = 0 ; i < 5 ; i ++ ){
            for (int j = 0 ; j < 5 ; j++){
                str += key[i][j]+"\t";
            }
            str += "\n";
        }
        return str;
    }

    /**
     *
     * @param string
     * @return
     *      String with all duplicates removed
     */
    public static String removeDuplicate(String string) {
        String str = new String();
        for (int i = 0; i < string.length(); i++)
        {
            char c = string.charAt(i);
            if (str.indexOf(c) < 0)
            {
                str += c;
            }
        }
        return str;
    }
}
