package thread;

import util.Answer;
import util.ServerTransport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ReadingThread implements Runnable {


    private ServerTransport transport;
    private Queue<Answer> answerQueue;

    public ReadingThread(ServerTransport transport, Queue<Answer> answerQueue) {
        this.answerQueue = answerQueue;
        this.transport = transport;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public void run()  {
        answerQueue.offer(transport.receive());
    }
}