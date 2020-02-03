import java.util.*;
/**
 * Main method that will drive methods in the class KeyTable, Phrase, and Bigram
 *
 * @Author: Henry Nguyen
 *      SBU ID: 111484010
 *      Recitation 08
 */

public class PlayfairEncryptionEngine {
    public static void main (String[] args) {
        /**
         * @param keyTable
         *      Holds instnace of Class KeyTable
         * @param phrase
         *      Holds instance of Phrase class
         * @param selection
         *      Holds user input that drives menu
         * @param inputKey
         *      Holds encryption/decryption text
         */

        KeyTable keyTable = new KeyTable();
        Phrase phrase = new Phrase();
        String selection;
        String inputKey;
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Key Phrase: ");
        inputKey = input.nextLine();
        inputKey = inputKey.toUpperCase();
        keyTable = keyTable.buildFromString(inputKey);
        System.out.println("Generation Success!\n");
        System.out.println("Menu: ");
        System.out.println("(CK) - Change Key");
        System.out.println("(PK) - Print Key");
        System.out.println("(EN) - Encrypt");
        System.out.println("(DE) - Decrypt");
        System.out.println("(Q) - Quit Program\n");

        while (true) {
            System.out.print("Please select an option: ");
            selection = input.nextLine();
            selection = selection.toUpperCase();
            switch (selection) {
                case "CK":
                    System.out.print("Please enter a phrase to encrypt: ");
                    inputKey = input.nextLine();
                    inputKey = inputKey.toUpperCase();
                    System.out.println("Generation Success!\n");
                    keyTable = keyTable.buildFromString(inputKey);
                    break;
                case "PK":
                    System.out.println(keyTable.toString());
                    break;
                case "EN":
                    phrase = phrase.encrypt(keyTable);
                    String encryptedText = "";
                    while(!phrase.isEmpty())
                        encryptedText += phrase.dequeue().toString();
                    System.out.println("Encrypted text is: " + encryptedText + "\n");
                    break;
                case "DE":
                    phrase = phrase.decrypt(keyTable);
                    String decryptedText = "";
                    while(!phrase.isEmpty())
                        decryptedText += phrase.dequeue().toString();
                    System.out.println("Decrypted text is: " + decryptedText + "\n");
                    break;
                case "Q":
                    System.out.println("Program Terminating.....");
                    System.exit(0);
                default:
                    System.out.println("Error: Not a Option\n");
            }
        }
    }
}
