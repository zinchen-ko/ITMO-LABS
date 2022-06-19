package instruments;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CommandsExecuteScript {
    private String filleName;
    File file;
    CommandManager commandManager;
    FileWorker fileWorker;
    private ArrayList<String> commands = new ArrayList<String>();
    Scanner scanner;
    String[] comannd;

    /**
     * Конструктор, который задание название файла с командами, обьект для обработки команд и обьект для работы с файлами
     */
    public CommandsExecuteScript(String filleName,CommandManager commandManager, FileWorker fileWorker){
        this.filleName=filleName;
        this.commandManager=commandManager;
        this.fileWorker=fileWorker;
    }

    /**
     * Начало обработки команд с скрипта
     * @exception FileNotFoundException
     */
    public void startRead() throws IOException {
        file=new File(filleName);
        System.out.println(filleName);
        if(FileWorker.fileCheckAccessRead1(file)){

            scanner=new Scanner(file);
            while (scanner.hasNextLine()){
                commands.add(scanner.nextLine());
            }
            startTreatment();
        }

    }

    public void startTreatment() throws IOException {
        while (commands.size()>0){

            comannd=commands.get(0).toLowerCase().trim().split(" ");

            switch (comannd[0]){
                case "help":
                    commandManager.commandsHelp();
                    break;
                case "info":
                    commandManager.commandsInfo();
                    break;
                case "show":
                    commandManager.commandsShow();
                    break;
                case "insert":
                    try {
                        System.out.println(comannd[1]);
                        int key = Integer.parseInt(comannd[1]);
                        commandManager.commandInsertExecute(key, commands);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    break;
                case "update":
                    try {
                        Integer.parseInt(comannd[1]);
                        int key = Integer.parseInt(comannd[1]);
                        commandManager.newcommandsUpdateExecute(key,commands);
                    } catch(NumberFormatException e){
                        System.out.println("ключ может принимать только целое числовое значение ");
                    }
                    break;
                case "remove_key":
                    if(checkCommndLine(1,comannd)){
                        break;
                    }
                    try {
                        Integer.parseInt(comannd[1]);
                        int key =Integer.parseInt(comannd[1]);
                        commandManager.commandsRemoveKey(key);
                    } catch(NumberFormatException e){
                        System.out.println("ключ может принимать только целое числовое значение ");
                    }
                    break;
                case "clear":
                    commandManager.commandsClear();
                    break;
                case "save":
                    commandManager.commandsSave();
                    break;
                case "exit":
                    System.exit(0);
                    break;
                case "history":
                    commandManager.commandsHistory();
                    break;
                case "replace_if_lowe":
                    try {
                        int key =Integer.parseInt(comannd[1]);
                        commandManager.commandsReplaceIfLowe(key);
                    } catch(NumberFormatException e){
                        System.out.println("ключ может принимать только целое числовое значение ");
                    }
                    break;
                case "remove_lower_key":
                    try {
                        int key =Integer.parseInt(comannd[1]);
                        commandManager.commandsRemoveLowerKey(key);
                    } catch(NumberFormatException e){
                        System.out.println("ключ может принимать только целое числовое значение ");
                    }
                    break;
                case "remove_any_by_transferred_students":
                    try {
                        int transferred =Integer.parseInt(comannd[1]);
                        commandManager.commandsRemoveAnyByTransferredStudents(transferred);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    break;
                case "count_by_average_mark":
                    try {
                        long mark =Long.parseLong(comannd[1]);
                        commandManager.commandsCountByAverageMark(mark);
                    } catch(NumberFormatException e){
                        System.out.println("ключ может принимать только целое числовое значение ");
                    }
                    commandManager.historyRecord(comannd[0]);
                    break;
                case "print_descending":
                    commandManager.commandsPrintDescending();
                    break;
                default:
                    System.out.println("Такой команды не существует. Воскользуйтесь help для получения всех возможных команд");
                    break;
                case "execute_script":
                    if(checkCommndLine(1,comannd)){
                        break;
                    }
                    if (filleName.equals(comannd[1])){

                    }else {
                        commandManager.commandsExecuteScript(commandManager, comannd[1]);
                    }
            }
            commands.remove(0);
        }
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
//public class ExecuteScript {
//    private CommandManager commandManager;
//    private String filleName;
//    private boolean flag = true;
//
//    /**
//     * Reads commands from a file and sends them for execution
//     */
//
//

//    public void executeDocument(String filleName, CommandManager commandManager) throws IOException {
//        this.filleName = filleName;
//        this.commandManager = commandManager;
//        try {
//            File file = new File(filleName);
//            Scanner scanner = new Scanner(file);
//            while (scanner.hasNextLine() && (flag)) {
//                String[] comannd = scanner.nextLine().toLowerCase().trim().split(" ");
//
//                switch (comannd[0]) {
//                    case "help":
//                        commandManager.commandsHelp();
//                        break;
//                    case "info":
//                        commandManager.commandsInfo();
//                        break;
//                    case "show":
//                        commandManager.commandsShow();
//                        break;
//                    case "insert":
//                        try {
//                            Integer.parseInt(comannd[1]);
//                            int key = Integer.parseInt(comannd[1]);
//                            commandManager.commandsInsert(key);
//                        } catch(NumberFormatException e){
//                            System.out.println("ключ может принимать только целое числовое значение ");
//                        }
//                        break;
//                    case "update":
//                        try {
//                            Integer.parseInt(comannd[1]);
//                            int key = Integer.parseInt(comannd[1]);
//                            commandManager.commandsUpdate(key);
//                        } catch(NumberFormatException e){
//                            System.out.println("ключ может принимать только целое числовое значение ");
//                        }
//                        break;
//                    case "remove_key":
//                        if(checkCommndLine(1,comannd)){
//                            break;
//                        }
//                        try {
//                            Integer.parseInt(comannd[1]);
//                            int key =Integer.parseInt(comannd[1]);
//                            commandManager.commandsRemoveKey(key);
//                        } catch(NumberFormatException e){
//                            System.out.println("ключ может принимать только целое числовое значение ");
//                        }
//                        break;
//                    case "clear":
//                        commandManager.commandsClear();
//                        break;
//                    case "save":
//                        commandManager.commandsSave();
//                        break;
//                    case "exit":
//                        flag = false;
//                        break;
//                    case "history":
//                        commandManager.commandsHistory();
//                        break;
//                    case "replace_if_lowe":
//                        try {
//                            int key =Integer.parseInt(comannd[1]);
//                            commandManager.commandsReplaceIfLowe(key);
//                        } catch(NumberFormatException e){
//                            System.out.println("ключ может принимать только целое числовое значение ");
//                        }
//                        break;
//                    case "remove_lower_key":
//                        try {
//                            int key =Integer.parseInt(comannd[1]);
//                            commandManager.commandsRemoveLowerKey(key);
//                        } catch(NumberFormatException e){
//                            System.out.println("ключ может принимать только целое числовое значение ");
//                        }
//                        break;
//                    case "remove_any_by_transferred_students":
//                        try {
//                            int transferred =Integer.parseInt(comannd[1]);
//                            commandManager.commandsRemoveAnyByTransferredStudents(transferred);
//                        } catch (NumberFormatException e) {
//                            e.printStackTrace();
//                        }
//                        break;
//                    case "count_by_average_mark":
//                        try {
//                            long mark =Long.parseLong(comannd[1]);
//                            commandManager.commandsCountByAverageMark(mark);
//                        } catch(NumberFormatException e){
//                            System.out.println("ключ может принимать только целое числовое значение ");
//                        }
//                        commandManager.historyRecord(comannd[0]);
//                        break;
//                    case "print_descending":
//                        commandManager.commandsPrintDescending();
//                        break;
//                    default:
//                        System.out.println("Такой команды не существует. Воскользуйтесь help для получения всех возможных команд");
//                        break;
//                }
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//    }
//    private boolean checkCommndLine(int arguments,String[] comanndLine){
//        if(arguments!=comanndLine.length-1){
//            System.out.println("данная команда принемает "+ arguments +" аргументов");
//            return true;
//        }else {
//            return false;
//        }
//
//    }
}


