package thread;

import util.Answer;
import util.ServerTransport;

import java.net.InetAddress;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class SendingThread implements Runnable {

    private InetAddress inetAddress;
    private int port;
    private Future<String> futureMessage;
    private ServerTransport transport;

    public SendingThread(Adress adress){
        this.transport = adress.getTransfer();
        this.port = adress.getPort();
        this.inetAddress = adress.getInetAddress();
        this.futureMessage = adress.getMessage();
    }

    @Override
    public void run() {
        String message;
        System.out.println("Я получил что-то");
        try{
            message = futureMessage.get();
        } catch(InterruptedException e) {
            e.printStackTrace();
            return;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return;
        }
        transport.send(new Answer(message), inetAddress, port);
    }
}
