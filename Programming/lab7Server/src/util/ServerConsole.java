package util;

import dateBase.BaseManager;
import thread.Adress;
import thread.ProcessThread;
import thread.ReadingThread;
import thread.SendingThread;

import java.util.concurrent.*;

public class ServerConsole {

    private ExecutorService receivePoll;
    private ExecutorService processingPool;
    private ExecutorService sendingPool;
    private BlockingQueue<Answer> answerQueue;
    private ServerTransport transport;
    private BlockingQueue<Adress> resultQueue;
    private BaseManager baseManager;
    private CommandManager manager;
    private CommandReader reader;

    public ServerConsole(ServerTransport transport, BaseManager baseManager, CommandReader reader, CommandManager manager) {
        this.baseManager=baseManager;
        this.manager=manager;
        this.reader=reader;
        this.transport=transport;
        receivePoll = new ThreadPoolExecutor(1, 4, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
//        receivePoll = ForkJoinPool.commonPool();
        //receivePoll = Executors.newFixedThreadPool(4);
//        processingPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        processingPool = ForkJoinPool.commonPool();
        sendingPool = ForkJoinPool.commonPool();
        resultQueue = new LinkedBlockingQueue<>();
        answerQueue = new LinkedBlockingQueue<>();
    }

    public void run () {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        executorService.submit(this::receiveAnswer);
        executorService.submit(this::processRequest);
        executorService.submit(this::sendResponse);
    }

    private void receiveAnswer() {
        while (true) {
            sleep(10);
            receivePoll.submit(new ReadingThread(transport, answerQueue));
        }
    }

    private void processRequest() {
        while (true) {
            sleep(10);
            if (answerQueue.isEmpty()) continue;
            Answer answer = answerQueue.poll();
            Future<String> future = processingPool.submit(new ProcessThread(answer, manager, baseManager,reader));
            resultQueue.offer(new Adress(answer.getClientInetAddress(), answer.getPort(), future, transport));
        }
    }

    private void sendResponse() {
        while (true) {
            sleep(10);
            if (resultQueue.isEmpty()) continue;
            sendingPool.submit(new SendingThread(resultQueue.poll()));
        }
    }
    private void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
