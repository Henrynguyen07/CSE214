/**
 * The SongLinkedList class implements a Doubly-Linked list. This will be the functionality of
 * the "playlist" that the user will create. Class will contain parameters; head, tail, cursor, size, c
 *
 * @Author: Henry Nguyen
 * SBU ID: 111484010
 * Recitation 08
 */

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.lang.Math;
import java.io.IOException;

public class SongLinkedList {
    /**
     * @param size
     *      Contains the total number of songs in the playlist
     * @param c
     *      Contains the played song
     * @param checkPlayer
     *      Will be used to check if a song is currently playing
     */
    private SongNode head;
    private SongNode tail;
    private SongNode cursor;
    private int size;
    private Clip c;
    boolean checkPlayer = false;

    /**
     * Will initialize SongLinkedList and setting the parameters
     * head,tail, and cursor to null.
     */
    public SongLinkedList() {
        head = null;
        tail = null;
        cursor = null;
    }

    // GETTERS AND SETTERS

    /**
     * Gets the node reference "head" of the linkedlist
     *
     * @return The node head
     */
    public SongNode getHead() {
        return head;
    }

    /**
     * Gets the node reference "tail" of the linkedList
     *
     * @return The node tail
     */
    public SongNode getTail() {
        return tail;
    }

    /**
     * Gets the position of cursor which is pointing at a node
     *
     * @return Cursor which has a reference of node with data
     */
    public SongNode getCursor() {
        return cursor;
    }

    /**
     * Sets head to a new value
     *
     * @param newHead
     */
    public void setHead(SongNode newHead) {
        head = newHead;
    }

    /**
     * Sets tail to a new value
     *
     * @param newTail
     */
    public void setTail(SongNode newTail) {
        tail = newTail;
    }

    /**
     * Moves the position of cursor to new destination
     *
     * @param newCursor
     */
    public void setCursor(SongNode newCursor) {
        cursor = newCursor;
    }

    /**
     * Sets the size of the Linkedlist of filled Songs object
     *
     * @param newSize
     */
    public void setSize(int newSize) {
        size = newSize;
    }

    /**
     * Plays the song requested by user input. If name doesn't exist, an error is thrown
     * First, checks if a song is currently playing, if a song is playing, it will stop and play the song
     * NOTE: Song folder has to be named "songs" and within the project folder.
     * @param name
     * @throws IllegalArgumentException
     *      Indicates that the provided name does not correspond to a song
     *      in the playlist, or that the wav file could not be found
     */
    public void play(String name) throws IllegalArgumentException, IOException, UnsupportedAudioFileException, LineUnavailableException {
        try {
            if (ifExist(name))
                if (checkPlayer == true) {
                    if (c.isRunning()) {
                        c.stop();
                    }
                }
            AudioInputStream AIS = AudioSystem.getAudioInputStream(new File(name + ".wav"));
            c = AudioSystem.getClip();
            c.open(AIS);
            c.start();
            checkPlayer = true;
        } catch (Exception ex) {
            System.out.println("IllegalArgumentException: Indicates that the provided name does not correspond to a song in the playlist, or that the wav file could not be found.");
        }
    }

    /**
     * Moves the cursor forward to the next node. If the cursor is at the tail,
     * cursor doesnt move
     */
    public void cursorForwards() {
        try {
            if (cursor == null || cursor.getNext() == null) {
                throw new NullPointerException();
            } else if (cursor != getTail()) {
                this.setCursor(cursor.getNext());
                System.out.println("Cursor has moved to next song\n");
            }
        } catch (NullPointerException e) {
            System.out.println("Error: Cursor is already at the end of the playlist\n");
        }
    }

    /**
     * Moves the cursor backwards to the previous node. If the cursor is at the head,
     * cursor doesn't move.
     */
    public void cursorBackwards() {
        try {
            if (cursor == null || cursor.getPrev() == null) {
                throw new NullPointerException();
            } else if (cursor != getHead()) {
                this.setCursor(cursor.getPrev());
                System.out.println("Cursor has moved to previous song\n");
            }
        } catch (NullPointerException e) {
            System.out.println("Error: Cursor is already at the beginning of the playlist\n");
        }
    }

    /**
     *
     * @param newSong
     *      Object song to add to the linkedList (playlist).
     * @throws IllegalArgumentException
     */
    public void insertAfterCursor(Song newSong) throws IllegalArgumentException {
        SongNode newNode = new SongNode(newSong);
        newNode.setData(newSong);
        if (cursor == null) {
            setHead(newNode);
            setTail(newNode);
            setCursor(newNode);
        } else {
            newNode.setNext(cursor.getNext());
            cursor.setNext(newNode);
            newNode.setPrev(cursor);
            if (newNode.getNext() != null) {
                newNode.getNext().setPrev(newNode);
            } else {
                this.setTail(newNode);
            }
            cursor = newNode;
        }
        size++;
        System.out.println(newSong.getName() + " by " + newSong.getArtist() + " is added to your playlist.\n");
    }

    /**
     * Removed the object that is currently at the cursor. THen is returned to be printed to the console
     * @return
     *      Returns the object at the cursor
     * @throws IllegalArgumentException
     *      If cursor is null
     *
     */
    public Song removeCursor() throws IllegalArgumentException {
        Song removedData = null;
        if (cursor == null) {
            throw new IllegalArgumentException();
        } else {
            if (cursor == head) {
                removedData = head.getData();
                head = cursor.getNext();
                cursor = head;

            } else if (cursor.getNext() == null) {
                removedData = cursor.getData();
                cursor.getPrev().setNext(null);
                cursor = cursor.getPrev();
            } else {
                removedData = cursor.getData();
                cursor.getPrev().setNext(cursor.getNext());
                cursor.getNext().setPrev(cursor.getPrev());
                cursor = cursor.getNext();
            }
        }
        System.out.println(removedData.toString() + " was removed from the playlist\n");
        return removedData;
    }

    /**
     * The amount of Songs in the playlist
     * @return
     *      Amount of songs
     */
    public int getSize() {
        return size;
    }

    /**
     * This method will randomize a song to choose and play it
     * @return
     *      The randomized song selected
     */
    public Song random() {
        SongNode temp = head;
        int rand = randomNumber();
        if (rand > 0) {
            for (int i = 0; i < rand; i++) {
                temp = temp.getNext();
            }
        }
        return temp.getData();
    }

    /**
     * This will shuffle the playlist within the playlist. Using the random method, it will
     * choose numbers between 0 and size. Creating a temporary new linkedList. Songs will be added
     * to the temporary linkedlist while deleting the same song in the old linkedlist.
     * Then, the original linkedlist will be cloned to the temporary linked list
     */
    public void shuffle() {
        SongLinkedList tempList = new SongLinkedList();
        try {
            if (head == null || head == tail) {
                throw new NullPointerException();
            } else {
                SongNode tempCursor = this.getCursor();
                while (this.getSize() > 0) {
                    Song rand = this.random();
                    tempList.insertAfterCursor(rand);
                    cursor = head;
                    while (cursor.getData() != rand) {
                        cursor = cursor.getNext();
                    }
                    removeNotCursor();
                    size--;
                }
                // PUT THE NEW SHUFFLED LINKEDLIST TO OLD LINKEDLIST
                cursor = head;
                tempList.cursor = tempList.head;
                for (int i = 0; i < tempList.size; i++) {
                    insertAfterCursor(tempList.cursor.getData());
                    tempList.cursor = tempList.cursor.getNext();
                }
                // SET CURSOR TO OLD CURSOR BEFORE SHUFFLE
                cursor = head;
                while (cursor.getData() != tempCursor.getData()) {
                    cursor = cursor.getNext();
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Error: Playlist doesn't exist or size is only 1");
        }
    }

    /**
     * Prints the playlist in a neatly formatted table
     */
    public void printPlaylist() {
        System.out.println("Playlist:");
        System.out.println(String.format("%-40s%-25s%-25s%-25s", "Song", "|Artist", "|Album", "|Length (s)"));
        System.out.println("-----------------------------------------------------------------------------------------------------");
        SongNode tempCursor = this.getCursor();
        cursor = head;
        while (cursor != null) {
            System.out.print(toString());
            if (tempCursor == cursor) {
                System.out.print(" <----\n");
            } else {
                System.out.println();
            }
            cursor = cursor.getNext();
        }
        cursor = tempCursor;
        System.out.println();
    }

    /**
     * This method will clear the whole playlist. If method is called and size == 0,
     * an error is thrown.
     * @Exception NullPointerException
     */
    public void deleteAll() {
        try {
            if (size == 0) {
                throw new NullPointerException();
            } else {
                head = null;
                tail = null;
                cursor = null;
                size = 0;
                System.out.println();

            }
        } catch (NullPointerException e) {
            System.out.println("Error: Playlist is empty. Nothing to clear\n");
        }

    }

    /**
     * Method used to return a string of the Song object
     * @return
     *      Parameters of Song object
     */
    public String toString() {
        return String.format("%-40s%-26s%-26s%-3d", cursor.getData().getName(), cursor.getData().getArtist(), cursor.getData().getAlbum(), cursor.getData().getLength());
    }

    /**
     * Generate a random number
     * @return
     *      A random number between 0 and size
     */
    public int randomNumber() {
        int max = this.getSize();
        // Generating a numbers within 0 to size EXCLUSIVE
        return (int) (Math.random() * max);
    }

    /**
     * This method is used to shuffle. This is similar to the "removeCursor" method however doesn't
     * change the cursor
     * @return
     *      Returns the Song that was removed
     * @throws IllegalArgumentException
     */
    public Song removeNotCursor() throws IllegalArgumentException {
        Song removedData = null;
        try {
            if (cursor == null) {
                throw new IllegalArgumentException("NOPE");
            } else {
                if (cursor == head) {
                    removedData = head.getData();
                    head = cursor.getNext();
                } else if (cursor.getNext() == null) {
                    removedData = cursor.getData();
                    cursor.getPrev().setNext(null);
                } else {
                    removedData = cursor.getData();
                    cursor.getPrev().setNext(cursor.getNext());
                    cursor.getNext().setPrev(cursor.getPrev());
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }
        return removedData;
    }

    /**
     * This will check if the song exists in the current playlist
     * @param name
     * @return
     *      True: Song exists
     *      False: Song doesn't exist
     */
    public boolean ifExist(String name) {
        if (head == null || tail == null) {
            throw new IllegalArgumentException();
        } else {
            SongNode tempCursor = head;
            while (tempCursor != null) {
                if (tempCursor.getData().getName().equalsIgnoreCase(name)) {
                    return true;
                } else {
                    tempCursor = tempCursor.getNext();
                }
            }
        }
        return false;
    }
}




