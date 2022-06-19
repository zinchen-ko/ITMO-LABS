package util;
/**
 * Класс выполнения команды Execute Script
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CommandExecuteScript {

    private String filleName;
    File file;
    ExecuteManager manager;
    FileWorker fileWorker;
    private ArrayList<String> commands = new ArrayList<String>();
    Scanner scanner;
    String[] comannd;
    public static boolean executeFlag;
    private ServerTransport transport;

    /**
     * Конструктор, который задание название файла с командами, обьект для обработки команд и обьект для работы с файлами
     */
    public CommandExecuteScript(String filleName,ExecuteManager manager, FileWorker fileWorker){
        this.filleName=filleName;
        this.manager=manager;
        this.fileWorker=fileWorker;
    }
    public CommandExecuteScript(ArrayList<String> arrayList,ExecuteManager manager){
        commands=arrayList;
        this.manager=manager;
    }
    public CommandExecuteScript(ExecuteManager manager,String filleName, ServerTransport transport){
        this.manager=manager;
        this.filleName=filleName;
        this.transport = transport;
    }

    /**
     * Начало обработки команд с скрипта
     * @exception FileNotFoundException
     */
    public void startRead() throws IOException {
        file=new File(filleName);
        scanner=new Scanner(file);
        while (scanner.hasNextLine()){
            commands.add(scanner.nextLine());
        }
        startTreatment();
    }

    public void startTreatment() throws IOException {
        executeFlag = true;
        while (commands.size()>0){

            comannd=commands.get(0).toLowerCase().trim().split(" ");

            switch (comannd[0]) {
                case "help":
                    if(checkCommndLine(0,comannd)){ break; }
                    manager.commandHelp();
                    break;
                case "show":
                    if(checkCommndLine(0,comannd)){ break; }
                    manager.commandShow();
                    break;
                case "remove_key":
                    if(checkCommndLine(1,comannd)){ break; }
                    manager.commandRemoveKey(comannd[1]);
                    break;
                case "clear":
                    if(checkCommndLine(0,comannd)){ break; }
                    manager.commandClear();
                    break;
                case "history":
                    if(checkCommndLine(0,comannd)){ break; }
                    manager.commandHistory();
                    break;
                case "remove_any_by_transferred_students":
                    if(checkCommndLine(0,comannd)){ break; }
                    int x = Integer.parseInt(comannd[1]);
                    manager.commandRemoveAnyByTransferredStudents(x);
                    break;
                case "count_by_average_mark":
                    if(checkCommndLine(1,comannd)){ break; }
                    long y = Long.parseLong(comannd[1]);
                    manager.commandCountByAverageMark(y);
                    break;
                case "save":
                    if(checkCommndLine(0,comannd)){
                        break;
                    }
                    manager.commandSave();
                    break;
                case "remove_lower_key":
                    if(checkCommndLine(1,comannd)){ break; }
                    try {
                        int key =Integer.parseInt(comannd[1]);
                        manager.commandRemoveLowerKey(key);
                    } catch(NumberFormatException e){
                        System.out.println("ключ может принимать только целое числовое значение ");
                    }
                    break;
                case "print_descending":
                    if(checkCommndLine(0,comannd)){
                        break;
                    }
                    manager.commandsPrintDescending();
                    break;
                case "replace_if_lowe":
                    if(checkCommndLine(1,comannd)){ break; }
                    try {
                        int key =Integer.parseInt(comannd[1]);
                        int argument = Integer.parseInt(commands.get(1));
                        int arg = Integer.parseInt(commands.get(2));
                        manager.commandReplaceIfLowe(key,argument,arg);
                        commands.remove(1);
                        commands.remove(2);
                    } catch(NumberFormatException e){
                        System.out.println("ключ может принимать только целое числовое значение ");
                    }
                    break;
                case "info":
                    if(checkCommndLine(0,comannd)){
                        break;
                    }
                    manager.commandInfo();
                    break;
                case "insert":
                    if(checkCommndLine(1,comannd)){ break; }
                    try {
                        int key = Integer.parseInt(comannd[1]);
                        int x1 = Integer.parseInt(commands.get(2));
                        int y1 = Integer.parseInt(commands.get(3));
                        long student = Integer.parseInt(commands.get(4));
                        long mark = Integer.parseInt(commands.get(5));
                        int transferred = Integer.parseInt(commands.get(6));
                        float height = Float.parseFloat(commands.get(9));
                        int weight = Integer.parseInt(commands.get(10));
                        manager.commandInsertExecute(key,commands.get(1),x1,y1,student,mark,transferred,commands.get(7),commands.get(8),height,weight,commands.get(11));
                        commands.remove(1);
                        commands.remove(2);
                        commands.remove(3);
                        commands.remove(4);
                        commands.remove(5);
                        commands.remove(6);
                        commands.remove(7);
                        commands.remove(8);
                        commands.remove(9);
                        commands.remove(10);
                        commands.remove(11);
                    } catch(NumberFormatException e){
                        System.out.println("ключ может принимать только целое числовое значение, строго больше нуля  ");
                    }
                    break;
                case "update":
                    if(checkCommndLine(1,comannd)){ break; }
                    try {
                        int key =Integer.parseInt(comannd[1]);
                        int change = Integer.parseInt(commands.get(1));
                        manager.commandUpdateExecute(key,change,commands.get(2));
                        commands.remove(1);
                        commands.remove(2);
                    } catch(NumberFormatException e){
                        System.out.println("ключ может принимать только целое числовое значение, строго больше нуля  ");
                    }
                    break;
                default:
                    System.out.println("команды <"+comannd[0]+"> не существует ");
                    break;
            }
            commands.remove(0);
        }
        executeFlag = false;
        transport.send(new Answer("Результат"));
    }
    /**
     * проверка на соответствие количества аргументов
     * @param arguments количество аргументов которое принимает команда
     * @param comanndLine масиив команд
     */
    private boolean checkCommndLine(int arguments,String[] comanndLine){
        if(arguments!=comanndLine.length-1){
            System.out.println("Комманда"+ comanndLine[0] +" принемает "+ arguments +" аргумента");
            return true;
        }else {
            return false;
        }
    }
}






