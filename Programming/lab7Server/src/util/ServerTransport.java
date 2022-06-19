package util;

import java.io.IOException;
import java.net.*;

public class ServerTransport {

    private DatagramSocket socket;
    private InetAddress address;
    private InetAddress clientInetAddress;
    private int clientPort;

    public ServerTransport() {
        try {
            socket = new DatagramSocket(9713);
            address = InetAddress.getByName("localhost");
        } catch (SocketException | UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public void send(Answer answer) {
        try {
            byte[] byteArray = answer.toByteArray();
            DatagramPacket packet = new DatagramPacket(byteArray, byteArray.length, clientInetAddress, clientPort);
            socket.send(packet);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Answer receive() {
        try {
            byte[] buf = new byte[9196];
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);
            clientInetAddress = packet.getAddress();
            clientPort = packet.getPort();
            Answer answer = Answer.fromByteArray(buf);
            answer.setPort(packet.getPort());
            return answer;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Answer receiveSpec() {
        try {
            byte[] buf1 = new byte[9196];
            socket.setSoTimeout(1);
            DatagramPacket packet = new DatagramPacket(buf1, buf1.length);
            socket.receive(packet);
            clientInetAddress = packet.getAddress();
            clientPort = packet.getPort();
            return Answer.fromByteArray(buf1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void send(Answer answer, InetAddress clientInetAddress, int clientPort){
        try {
            byte[] byteArray = answer.toByteArray();
            DatagramPacket packet = new DatagramPacket(byteArray, byteArray.length, clientInetAddress, clientPort);
            socket.send(packet);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
