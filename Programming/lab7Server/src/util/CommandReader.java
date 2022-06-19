package util;

import collection.StudyGroup;
import dateBase.Authorization;
import dateBase.BaseManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public class CommandReader {

    private CommandManager manager;
    private ServerTransport transport;
    private Map<Integer, StudyGroup> collection;
    private boolean isRun = true;
    private String[] command;
    private BaseManager baseManager;

    public CommandReader(ServerTransport transport, CommandManager manager, Map<Integer, StudyGroup> collection, BaseManager baseManager) {
        this.transport = transport;
        this.manager = manager;
        this.collection = collection;
        this.baseManager = baseManager;
    }

    public String reader(Answer answer) throws IOException, SQLException, ClassNotFoundException {

        System.out.println("Сервер начал работу и ждёт новых запросов...");
        collection = baseManager.getCollection();

            switch (answer.getCommand()) {
                case "help":
                    System.out.println("Исполнение команды " + answer.getCommand() + "...");
                    manager.historyRecord(answer.getCommand());
                    System.out.println("Результат выполнения команды был отправлен клиенту");
                    return manager.commandHelp();
                case "info":
                    System.out.println("Исполнение команды " + answer.getCommand() + "...");
                    manager.historyRecord(answer.getCommand());
                    System.out.println("Результат выполнения команды был отправлен клиенту");
                    return manager.commandInfo();
                case "show":
                    System.out.println("Исполнение команды " + answer.getCommand() + "...");
                    manager.historyRecord(answer.getCommand());
                    System.out.println("Результат выполнения команды был отправлен клиенту");
                    return manager.commandShow();
                case "insert":
                    System.out.println("Исполнение команды " + answer.getCommand() + "...");
                    manager.historyRecord(answer.getCommand());
                    System.out.println("Результат выполнения команды был отправлен клиенту");
                    return manager.commandInsert(answer);
                case "update":
                    System.out.println("Исполнение команды " + answer.getCommand() + "...");
                    manager.historyRecord(answer.getCommand());
                    System.out.println("Результат выполнения команды был отправлен клиенту");
                    return manager.commandUpdate(answer);
                case "remove_key":
                    System.out.println("Исполнение команды " + answer.getCommand() + "...");
                    manager.historyRecord(answer.getCommand());
                    System.out.println("Результат выполнения команды был отправлен клиенту");
                    return manager.commandRemoveKey(answer);
                case "clear":
                    System.out.println("Исполнение команды " + answer.getCommand() + "...");
                    manager.historyRecord(answer.getCommand());
                    System.out.println("Результат выполнения команды был отправлен клиенту");
                    return manager.commandClear(answer);
//                case "save":
//                    System.out.println("Исполнение команды " + answer.getAnswer() + "...");
//                    manager.commandSave();
//                    manager.historyRecord(answer.getAnswer());
//                    System.out.println("Результат выполнения команды был отправлен клиенту");
//                    break;
                case "execute_script":
                    System.out.println("Исполнение команды " + answer.getCommand() + "...");
                    CollectionConstruct construct = new CollectionConstruct(collection);
                    ExecuteManager executeManager = new ExecuteManager(collection, transport, construct);
                    CommandExecuteScript executeScript = new CommandExecuteScript(executeManager, answer.getArgument(), transport);
                    executeScript.startRead();
                    manager.historyRecord(answer.getCommand());
                    System.out.println("Результат выполнения команды был отправлен клиенту");
                    break;
                case "history":
                    System.out.println("Исполнение команды " + answer.getCommand() + "...");
                    System.out.println("Результат выполнения команды был отправлен клиенту");
                    return manager.commandHistory();
                case "replace_if_lowe":
                    System.out.println("Исполнение команды " + answer.getCommand() + "...");
                    manager.historyRecord(answer.getCommand());
                    System.out.println("Результат выполнения команды был отправлен клиенту");
                    return manager.commandReplaceIfLowe(answer);
                case "remove_lower_key":
                    System.out.println("Исполнение команды " + answer.getCommand() + "...");
                    manager.historyRecord(answer.getCommand());
                    System.out.println("Результат выполнения команды был отправлен клиенту");
                    return manager.commandRemoveLowerKey(answer);
                case "remove_any_by_transferred_students":
                    System.out.println("Исполнение команды " + answer.getCommand() + "...");
                    manager.historyRecord(answer.getCommand());
                    System.out.println("Результат выполнения команды был отправлен клиенту");
                    return manager.commandRemoveAnyByTransferredStudents(answer);
                case "count_by_average_mark":
                    System.out.println("Исполнение команды " + answer.getCommand() + "...");
                    manager.historyRecord(answer.getCommand());
                    System.out.println("Результат выполнения команды был отправлен клиенту");
                    return manager.commandCountByAverageMark(answer);
                case "print_descending":
                    System.out.println("Исполнение команды " + answer.getCommand() + "...");
                    manager.historyRecord(answer.getCommand());
                    System.out.println("Результат выполнения команды был отправлен клиенту");
                    return manager.commandPrintDescending();
                case "login":
                case "registration":
                    Authorization authorization = new Authorization(transport, baseManager);
                    return authorization.user(answer);
                default:
                    break;

            }
        return null;
    }
}