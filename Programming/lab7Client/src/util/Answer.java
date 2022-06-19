package util;

import collection.StudyGroup;

import java.io.*;
import java.net.InetAddress;
import java.util.Scanner;

public class Answer implements Serializable {

    private StudyGroup studyGroup;
    private String argument;
    private String answer;
    private boolean registration;
    private String login;
    private String password;
    private InetAddress clientInetAddress;
    private int port;
    private String send;
    private String command;
    private String argument2;
    private String argument3;


    public Answer(String answer, String argument,InetAddress address) {
        this.answer=answer;
        this.argument=argument;
        this.clientInetAddress=address;
    }

    public Answer(String send, String answer, String argument, boolean registration,InetAddress address) {
        this.answer=answer;
        this.argument=argument;
        this.registration=registration;
        this.clientInetAddress=address;
        this.send=send;
    }

    public Answer(String answer, String argument, String login, String password,InetAddress address) {
        this.answer=answer;
        this.argument=argument;
        this.login=login;
        this.password=password;
        this.clientInetAddress=address;
    }

    public Answer(String answer) {
        this.answer=answer;
    }

    public Answer(String command, String argument,String argument2,String argument3, StudyGroup studyGroup,String login, String password,InetAddress address, boolean registration) {
        this.argument=argument;
        this.studyGroup=studyGroup;
        this.password=password;
        this.login=login;
        this.clientInetAddress=address;
        this.command=command;
        this.registration=registration;
        this.argument2=argument2;
        this.argument3=argument3;
    }

    public String getArgument2() {return argument2;}

    public String getArgument3() {return argument3;}

    public String getAnswer() {return answer;}

    public InetAddress getClientInetAddress() {
        return clientInetAddress;
    }

    public String getSend() {return send;}

    public void setClientInetAddress(InetAddress clientInetAddress) {
        this.clientInetAddress = clientInetAddress;
    }
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() { return password;}

    public void setPassword(String password) {this.password=password;}

    public String getLogin(){ return login;}

    public boolean getRegistration() { return registration;}

    public String getCommand() {
        return command;
    }

    public String getArgument() {
        return argument;
    }

    public StudyGroup getStudyGroup() {
        return studyGroup;
    }

    public void setArgument(String argument) {this.argument=argument;}

    public byte[] toByteArray() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream);
            oos.writeObject(this);
            oos.flush();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Answer fromByteArray(byte[] byteArray) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            return (Answer) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }
}
