package util;

import java.io.IOException;
import java.net.*;

public class ClientTransport {

    private DatagramSocket socket;

    public ClientTransport() {
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    private InetAddress getLocalhost() {
        try {
            return InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    private DatagramPacket createPacket(Answer answer) {
        byte[] byteArray = answer.toByteArray();
        return new DatagramPacket(byteArray, byteArray.length, getLocalhost(), 9713);
    }

    public void send(Answer answer) {
        try {
            socket.send(createPacket(answer));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Answer receive() throws IOException {
        try {
            byte[] buf = new byte[9196];
            socket.setSoTimeout(3000);
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            return Answer.fromByteArray(buf);
        } catch (SocketTimeoutException e) {
            return new Answer("Сервер временно недоступен");
        }
    }
}
