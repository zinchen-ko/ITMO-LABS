package util;

import collection.StudyGroup;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static util.Authorization.login;
import static util.Authorization.password;

public class Console {

    private Map<Integer, StudyGroup> collection=new HashMap<Integer, StudyGroup>();
    private boolean isRunnable;
    private boolean isRun = true;
    private boolean isExecute = true;
    private String[] command;
    private ClientTransport transport = new ClientTransport();
    Scanner scanner = new Scanner(System.in);


    public Console() {}

    public void consoleRun() throws IOException {

        Authorization authorization = new Authorization(scanner,transport);
        authorization.user();
        isRunnable = true;
        System.out.println("Введите команду. Для получения списка всех возможных команды введите help");

        while (isRunnable) {

            command = scanner.nextLine().toLowerCase().trim().split(" ");

            switch (command[0]) {
                case "help": //YES
                case "print_descending": //YES
                case "history": //YES
                case "info": //YES
                case "show": //YES
                case "clear": //YES
                    if(checkCommandLine(0,command)){break;}
                    System.out.println(commandNonArg(command[0]));
                    break;
                case "insert": //YES
                    if(checkCommandLine(0,command)){break;}
                    System.out.println(commandInsert(command[0]));
                    break;
                case "update": //YES
                case "replace_if_lowe": //YES
                    if(checkCommandLine(1,command)){break;}
                    System.out.println(commandArgSpecial(command[0],command[1]));
                    break;
                case "remove_key": //YES
                case "count_by_average_mark": //YES
                case "remove_any_by_transferred_students": //YES
                case "remove_lower_key": //YES
                    if(checkCommandLine(1,command)){break;}
                    System.out.println(commandArg(command[0],command[1]));
                    break;
                case "execute_script":
                    if(checkCommandLine(1,command)){break;}
                    System.out.println(commandExecuteScript(command[0],command[1]));
                    break;
                case "exit": //YES
                    if(checkCommandLine(0,command)){break;}
                    isRunnable = false;
                    System.exit(0);
                    break;
                default:
                    System.out.println("Такой команды не сущетсвует");
                    break;
            }
        }
    }
    private boolean checkCommandLine(int arguments,String[] comanndLine){
        if(arguments!=comanndLine.length-1){
            System.out.println("Данная команда принимает "+ arguments +" аргументов");
            return true;
        }else {
            return false;
        }
    }

    private String commandNonArg(String commandName) throws IOException {
//        transport.send(new Answer(commandName,"",login,password, InetAddress.getByName("localhost")));
        transport.send(new Answer(commandName,null,null,null,null,login,password,InetAddress.getByName("localhost"),true));
        return transport.receive().getAnswer();
    }

    private String commandArg(String commandName, String commandArgument) throws IOException {
//        transport.send(new Answer(commandName,commandArgument,login,password,InetAddress.getByName("localhost")));
        transport.send(new Answer(commandName,commandArgument,null,null,null,login,password,InetAddress.getByName("localhost"),false));
        return transport.receive().getAnswer();
    }

    private String commandArgSpecial(String commandName,String commandArgument) throws IOException {
        System.out.println("Введите номер поля, которое вы хотите изменить" + "\n" + "Количество учеников - 1" + "\n" + "Средний балл - 2" +"\n" + "Количество иностранцев - 3");
        String argument2 = scanner.nextLine();
        System.out.println("Введитеновое значение");
        String argument3 = scanner.nextLine();
        transport.send(new Answer(commandName,commandArgument,argument2,argument3,null,login,password,InetAddress.getByName("localhost"),false));
        return transport.receive().getAnswer();
    }

    private String commandInsert(String commandName) throws IOException {
        CollectionConstruct construct = new CollectionConstruct(collection,login);
        int key = Integer.parseInt("1");
        while (!construct.checkId(key)){}
        while (!construct.checkName()) ;
        while (!construct.creatCoordinates()) ;
        while (!construct.checkStudentsCount()) ;
        while (!construct.checkAverageMark()) ;
        while (!construct.checkTransferredStudents()) ;
        while (!construct.cheakPerson()) ;
        construct.setOwner();
        StudyGroup studyGroup=construct.makeStudyGroup() ;
        transport.send(new Answer(commandName,null,null,null,studyGroup,login,password,InetAddress.getByName("localhost"),false));
        return transport.receive().getAnswer();
    }

    private String commandExecuteScript(String commandName,String commandArgument) throws IOException {
//        File file = new File(commandArgument);
//        Scanner in = new Scanner(file);
//        String executeArgument = "";
//        while (in.hasNextLine()) {
//            executeArgument = executeArgument + in.nextLine() + "\n";
//        }
//        transport.send(new Answer(executeArgument));
        transport.send(new Answer(commandName,commandArgument,InetAddress.getByName("localhost")));
        while (isExecute) {
            String send = transport.receive().getAnswer();
            if (send.equals("Результат")) {
//                in.close();
                return "Окончание выполнения команд из файла";
            } else if (send.equals("Ошибка")){
//                in.close();
                return "Ошибка в командах, проверьте каждую строку";
            } else {
                System.out.println(send);
            }
        }
        return "Окончание выполнения команд из файла";
    }
}
