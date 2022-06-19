package util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Authorization {

    Scanner scanner;
    public static String login;
    public static String password;
    ClientTransport transport;
    private boolean isOffline = true;
    private boolean onClient = true;
    private boolean logOrReg;


    public Authorization(Scanner scanner, ClientTransport transport) {
        this.scanner=scanner;
        this.transport = transport;
    }

    public void user() throws IOException {

        System.out.println("Введите login для авторизации, или registration для регистрации");
        while (isOffline) {
            String send = scanner.nextLine();
            if (send.equals("login")){
                logOrReg = true;
                isOffline=false;
            } else if (send.equals("registration")) {
                logOrReg = false;
                isOffline = false;
            } else {
                System.out.println("Такой функции нет, введите еще раз");
            }
        }
        while(onClient) {
            if (logOrReg) {
                System.out.println("Введите логин и пароль поочередно");
                login = scanner.nextLine();
                password = scanner.nextLine();
//                transport.send(new Answer("login",login,password,false, InetAddress.getByName("localhost")));
                transport.send(new Answer("login",null,null,null,null,login,password,InetAddress.getByName("localhost"),false));
                if(transport.receive().getAnswer().equals("Успех")){
                    onClient=false;
                } else  {
                    System.out.println("Не правильно введён логин или пароль");
                }
            } else {
                System.out.println("Введите новый логин и пароль поочередно");
                login = scanner.nextLine();
                password = scanner.nextLine();
//                transport.send(new Answer("registration",login,password,true,InetAddress.getByName("localhost")));
                transport.send(new Answer("registration",null,null,null,null,login,password,InetAddress.getByName("localhost"),true));
                String al = transport.receive().getAnswer();
                if(transport.receive().getAnswer().equals("Успех")){
                    onClient=false;
                } else  {
                    System.out.println("Не правильно введён логин или пароль");
                }
            }
        }
    }
}
