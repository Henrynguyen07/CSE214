/**
 * This class will be controlling the playlist. With different methods, the user
 * can manipulate the playlist
 *
 * @Author: Henry Nguyen
 * SBU ID: 111484010
 * Recitation 08
 */

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Scanner;

public class Player {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        SongLinkedList play = new SongLinkedList();
        Scanner input = new Scanner(System.in);
        String choice;
        boolean menuFlag = true;
        while (menuFlag == true) {
            System.out.println("(A)   Add Song to Playlist");
            System.out.println("(F)   Go to Next Song");
            System.out.println("(B)   Go to Previous Song");
            System.out.println("(R)   Remove Song from Playlist");
            System.out.println("(L)   Play a Song");
            System.out.println("(C)   Clear the Playlist");
            System.out.println("(S)   Shuffle Playlist");
            System.out.println("(Z)   Random Song");
            System.out.println("(P)   Print Playlist");
            System.out.println("(T)   Get the total amount of songs in the playlist");
            System.out.println("(Q)   Exit the Playlist");
            System.out.println();
            System.out.print("Enter an Option: ");
            choice = input.nextLine();
            switch (choice.toUpperCase()) {
                case "A":
                    try {
                        Song song = new Song();
                        System.out.print("Enter song title: ");
                        song.setName(input.nextLine());
                        if (song.getName().isEmpty())
                            throw new IllegalArgumentException("Indicates that input is invalid");
                        System.out.print("Enter artist(s) of the song: ");
                        song.setArtist(input.nextLine());
                        if (song.getArtist().isEmpty())
                            throw new IllegalArgumentException("Indicates that input is invalid");
                        System.out.print("Enter album: ");
                        song.setAlbum(input.nextLine());
                        if (song.getArtist().isEmpty())
                            throw new IllegalArgumentException("Indicates that input is invalid");
                        System.out.print("Enter Length (in seconds): ");
                        String temp = input.nextLine();
                        if (temp.isEmpty()) {
                            temp = "0";
                        }
                        int integer = Integer.parseInt(temp);
                        if (integer > 0) {
                            song.setLength(integer);
                            play.insertAfterCursor(song);
                        } else {
                            throw new NumberFormatException("Length has to be greater than 0");
                        }
                    } catch (Exception e) {
                        System.out.println(e+"\n");
                    }
                    break;
                case "F":
                    play.cursorForwards();
                    break;
                case "B":
                    play.cursorBackwards();
                    break;
                case "R":
                    if (play.getCursor() != null) {
                        play.removeCursor();
                        play.setSize(play.getSize() - 1);
                    } else {
                        System.out.println("Error: Cursor is null\n");
                    }
                    break;
                case "L":
                    System.out.print("Enter name of song to play: ");
                    play.play(input.nextLine());
                    System.out.println();
                    break;
                case "C":
                    play.deleteAll();
                    break;
                case "S":
                    play.shuffle();
                    System.out.println();
                    break;
                case "P":
                    play.printPlaylist();
                    break;
                case "T":
                    System.out.println(play.getSize());
                    break;
                case "Z":
                    System.out.println("Playing a random song...");
                    Song songToPlay = play.random();
                    System.out.println(songToPlay.getName() + " by " + songToPlay.getArtist() + " is now playing\n");
                    play.play(songToPlay.getName());
                    break;
                case "Q":
                    System.out.println("Program Terminated");
                    menuFlag = false;
                    break;
            }
        }
    }
}
