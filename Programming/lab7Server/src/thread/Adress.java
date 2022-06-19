package thread;

import util.ServerTransport;

import java.net.InetAddress;
import java.util.concurrent.Future;

public class Adress {

    private InetAddress inetAddress;
    private int port;
    private Future<String> futureMessage;
    private ServerTransport transport;


    public Adress(InetAddress inetAddress, int port, Future<String> futureMessage, ServerTransport transport) {
        this.inetAddress = inetAddress;
        this.port = port;
        this.futureMessage = futureMessage;
        this.transport = transport;
    }

    public InetAddress getInetAddress() {
        return inetAddress;
    }

    public void setInetAddress(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Future<String> getMessage() {
        return futureMessage;
    }


    public void setMessage(Future<String> message) {
        this.futureMessage = message;
    }

    public ServerTransport getTransfer() {
        return transport;
    }

    public void setTransfer(ServerTransport transfer) {
        this.transport = transport;
    }
}
