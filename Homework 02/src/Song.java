/**
 * This class will contain data for a Song. This class will then be used in the Song Node
 * class. Class will contain the parameters; name, artist, album, length
 *
 * @Author: Henry Nguyen
 *      SBU ID: 111484010
 *      Recitation 08
 */

public class Song {
    // Variables
    /**
     * @param name
     *      Name of Song
     * @param Artist
     *      Artist of Song
     * @param Album
     *      Album of Song
     * @param Length
     *      Length of the song
     */
    private String name;
    private String artist;
    private String album;
    private int length;

    public Song() {

    }

    // METHODS

    /**
     * Gets name of Song
     * @return
     *      Name of Song
     */
    public String getName() {
        return name;
    }

    /**
     * Gets Artist of Song
     * @return
     *      Name of Artist
     */
    public String getArtist() {
        return artist;
    }

    /**
     * Gets Album of Song
     * @return
     *      Name of Album
     */
    public String getAlbum() {
        return album;
    }

    /**
     * Gets length of Song
     * @return
     *      Length of Song in Seconds
     */
    public int getLength() {
        return length;
    }

    /**
     * Sets the name of the song
     * @param newName
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * Sets the Artist of the Song
     * @param newArtist
     */
    public void setArtist(String newArtist) {
        artist = newArtist;
    }

    /**
     * Sets the name of the album of the song
     * @param newAlbum
     */
    public void setAlbum(String newAlbum) {
        album = newAlbum;
    }

    /**
     * Sets the length
     * @param newLength
     */
    public void setLength(int newLength) {
        length = newLength;
    }

    /**
     * String of the Data
     * @return
     *      Name and Artist
     */
    public String toString() {
        return (name + " by " + artist);
    }
}
