package instruments;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CommandReader {

    private String[] comannd;
    private CommandManager commandManager;
    private CommandsExecuteScript commandsExecuteScript;
    private FileWorker fileWorker;
    private boolean flag = true;

    public CommandReader(CommandManager commandManager, FileWorker fileWorker) {
        this.commandManager = commandManager;
    }

    /**
     * Reads the commands entered by the user and sends them for execution
     */

    public void reader() throws IOException {
        System.out.println("Введите команду. Для получения списка всех возможных команды введите help");
        while (flag) {
            Scanner scanner = new Scanner(System.in);
            comannd = scanner.nextLine().toLowerCase().trim().split(" ");

            switch (comannd[0]) {
                case "help":
                    if(checkCommndLine(0,comannd)){
                        break;
                    }
                    commandManager.commandsHelp();
                    break;
                case "info":
                    if(checkCommndLine(0,comannd)){
                        break;
                    }
                    commandManager.commandsInfo();
                    commandManager.historyRecord(comannd[0]);
                    break;
                case "show":
                    if(checkCommndLine(0,comannd)){
                        break;
                    }
                    commandManager.commandsShow();
                    commandManager.historyRecord(comannd[0]);
                    break;
                case "insert":
                    if(checkCommndLine(1,comannd)){
                        break;
                    }
                    try {
                        Integer.parseInt(comannd[1]);
                        int key = Integer.parseInt(comannd[1]);
                        commandManager.commandsInsert(key);
                    } catch(NumberFormatException e){
                        System.out.println("ключ может принимать только целое числовое значение ");
                    }
                    commandManager.historyRecord(comannd[0]);
                    break;
                case "update":
                    if(checkCommndLine(1,comannd)){
                        break;
                    }
                    try {
                        Integer.parseInt(comannd[1]);
                        int key = Integer.parseInt(comannd[1]);
                        commandManager.commandsUpdate(key);
                    } catch(NumberFormatException e){
                        System.out.println("ключ может принимать только целое числовое значение ");
                    }
                    commandManager.historyRecord(comannd[0]);
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
                    commandManager.historyRecord(comannd[0]);
                    break;
                case "clear":
                    if(checkCommndLine(0,comannd)){
                        break;
                    }
                    commandManager.commandsClear();
                    commandManager.historyRecord(comannd[0]);
                    break;
                case "save":
                    if(checkCommndLine(0,comannd)){
                        break;
                    }
                    commandManager.commandsSave();
                    commandManager.historyRecord(comannd[0]);
                    break;
                case "execute_script":
                    if(checkCommndLine(1,comannd)){
                        break;
                    }
                    CommandsExecuteScript commandsExecuteScript = new CommandsExecuteScript(comannd[1],commandManager,fileWorker);
                    commandsExecuteScript.startRead();
                    commandManager.historyRecord(comannd[0]);
                    break;
                case "exit":
                    System.exit(0);
                    break;
                case "history":
                    if(checkCommndLine(0,comannd)){
                        break;
                    }
                    commandManager.commandsHistory();
                    commandManager.historyRecord(comannd[0]);
                    break;
                case "replace_if_lowe":
                    if(checkCommndLine(1,comannd)){
                        break;
                    }
                    try {
                        int key =Integer.parseInt(comannd[1]);
                        commandManager.commandsReplaceIfLowe(key);
                    } catch(NumberFormatException e){
                        System.out.println("ключ может принимать только целое числовое значение ");
                    }
                    commandManager.historyRecord(comannd[0]);
                    break;
                case "remove_lower_key":
                    if(checkCommndLine(1,comannd)){
                        break;
                    }
                    try {
                        int key =Integer.parseInt(comannd[1]);
                        commandManager.commandsRemoveLowerKey(key);
                    } catch(NumberFormatException e){
                        System.out.println("ключ может принимать только целое числовое значение ");
                    }
                    commandManager.historyRecord(comannd[0]);
                    break;
                case "remove_any_by_transferred_students":
                    if(checkCommndLine(1,comannd)){
                        break;
                    }
                    try {
                        int transferred =Integer.parseInt(comannd[1]);
                        commandManager.commandsRemoveAnyByTransferredStudents(transferred);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    commandManager.historyRecord(comannd[0]);
                    break;
                case "count_by_average_mark":
                    if(checkCommndLine(1,comannd)){
                        break;
                    }
                    try {
                    long mark =Long.parseLong(comannd[1]);
                    commandManager.commandsCountByAverageMark(mark);
                } catch(NumberFormatException e){
                    System.out.println("ключ может принимать только целое числовое значение ");
                }
                    commandManager.historyRecord(comannd[0]);
                    break;
                case "print_descending":
                    if(checkCommndLine(0,comannd)){
                        break;
                    }
                    commandManager.commandsPrintDescending();
                    commandManager.historyRecord(comannd[0]);
                    break;
                default:
                    System.out.println("Такой команды не существует. Воскользуйтесь help для получения всех возможных команд");
                    break;
            }
        }
    }
    /**
     * Checks the number of argements entered by the user
     * @param arguments count of arguments
     * @param comanndLine the command entered by the user broken down by spaces
     * @return returns true if this command does not have the entered number of arguments
     */
    private boolean checkCommndLine(int arguments,String[] comanndLine){
        if(arguments!=comanndLine.length-1){
            System.out.println("данная команда принемает "+ arguments +" аргументов");
            return true;
        }else {
            return false;
        }

    }
}
