/**
 * The class will represent a node in the linked list. The class will contain
 * parameters; prev, next, data as reference to the previous node and the next node.
 * Within the linked list, it will contain a Song variable that represents data being
 * stored in each node.
 *
 * @Author: Henry Nguyen
 *      SBU ID: 111484010
 *      Recitation 08
 */

public class SongNode {
    // VARIABLES
    /**
     * @param prev
     *      Previous Node
     * @param next
     *      Next node
     * @param data
     *      Parameters of Song class
     */
    private SongNode prev;
    private SongNode next;
    private Song data;

    // CONSTRUCTOR
    public SongNode() {
    }

    /**
     * @param songNode
     *      Constructing a SongNode with Data
     */
    public SongNode(Song songNode) {
        data = songNode;
    }

    /**
     * @return
     *      Previous node from cursor
     */
    public SongNode getPrev() {
        return prev;
    }

    /**
     * @return
     *      Next node from cursor
     */
    public SongNode getNext() {
        return next;
    }

    /**
     * @return
     *      Data from cursor
     */
    public Song getData() {
        return data;
    }

    /**
     * @param newPrev
     *      Set the new reference for previous node
     */
    public void setPrev(SongNode newPrev) {
        prev = newPrev;
    }

    /**
     * @param newNext
     *      Set the new reference for next node
     */
    public void setNext(SongNode newNext) {
        next = newNext;
    }

    /**
     * @param newData
     *      Set the new Data for the current node
     */
    public void setData(Song newData) {
        data = newData;
    }
}
