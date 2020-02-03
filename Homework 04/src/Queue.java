/**
 * Queue interface that implement methods enqueue, peek, dequeue
 *
 * @Author: Henry Nguyen
 *      SBU ID: 111484010
 *      Recitation 08
 */

public interface Queue {
    public void enqueue(Bigram b);
    public Bigram peek();
    public Bigram dequeue();
}
