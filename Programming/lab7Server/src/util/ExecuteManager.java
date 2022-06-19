package util;

import collection.StudyGroup;
import dateBase.BaseManager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ExecuteManager {

    private ArrayList<String> commands = new ArrayList<String>();
    private Map<Integer, StudyGroup> collection;
    private ServerTransport transport;
    private FileWorker fileWorker;
    private int numberCommands = 0;
    private LinkedHashMap<Integer, String> history = new LinkedHashMap<Integer, String>();
    private CollectionConstruct construct;

    public ExecuteManager(Map<Integer, StudyGroup> collection, ServerTransport transport, FileWorker fileWorker, CollectionConstruct construct) {
        this.collection = collection;
        this.transport=transport;
        this.fileWorker=fileWorker;
        this.construct=construct;
    }

    public ExecuteManager(Map<Integer, StudyGroup> collection, ServerTransport transport, CollectionConstruct construct) {
        this.collection = collection;
        this.transport=transport;
        this.construct=construct;
    }

    private ArrayList<String> commandList = new ArrayList<String>();

    {
        commandList.add("help : вывести справку по доступным командам");
        commandList.add("info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        commandList.add("show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        commandList.add("insert : добавить новый элемент с заданным ключом");
        commandList.add("update : обновить значение элемента коллекции, id которого равен заданному");
        commandList.add("remove_key : удалить элемент из коллекции по его ключу");
        commandList.add("clear : очистить коллекцию");
        commandList.add("save : сохранить коллекцию в файл");
        commandList.add("execute_script : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        commandList.add("exit : завершить программу (без сохранения в файл)");
        commandList.add("history : вывести последние 13 команд (без их аргументов)");
        commandList.add("replace_if_lowe : заменить значение по ключу, если новое значение меньше старого");
        commandList.add("remove_lower_key : удалить из коллекции все элементы, ключ которых меньше, чем заданный");
        commandList.add("remove_any_by_transferred_students : удалить из коллекции один элемент, значение поля transferredStudents которого эквивалентно заданному");
        commandList.add("count_by_average_mark : вывести количество элементов, значение поля averageMark которых равно заданному");
        commandList.add("print_descending : вывести элементы коллекции в порядке убывания");

    }

    public void historyRecord(String command) {
        numberCommands = numberCommands + 1;
        history.put(numberCommands, command);
        if (numberCommands > 13) {
            history.remove(numberCommands - 13);
        }
    }

    public void commandHelp() {
        String commandHelp = "";
        for (String comand : commandList) {
            if (commandHelp.equals("")) {
                commandHelp = comand;
            } else {
                commandHelp = commandHelp + "\n" + comand;
            }
        }
        historyRecord("help");
        transport.send(new Answer(commandHelp));
    }

    public void commandInfo() {
        FileWorker fileWorker = new FileWorker();
        String send = "";
        send = "Колличество элементов в коллекции : " + collection.size() + "\n";
        send = send + "Дата загрузки коллекции из файла : " + fileWorker.dateDownloads + "\n";
        if (fileWorker.datePreservation == null) {
            send = send + "Сохранения коллекции в файл в текущей сессии не происходило" + "\n";
        } else {
            send = send + "Дата последнего сохранения коллекции в файл : " + fileWorker.datePreservation + "\n";
        }
        send = send + "Всего было введено валидных команд:" + numberCommands + "шт" + "\n";
        historyRecord("info");
        transport.send(new Answer(send));
    }

    public void commandShow() {
        StringBuilder stringBuilder = new StringBuilder(" ");
        collection.entrySet().stream().map(Map.Entry::getValue).forEach(x -> stringBuilder.append("\n").append(x).toString());
        transport.send(new Answer(stringBuilder.toString()));
        historyRecord("show");
    }

    public void commandClear() {
        collection.clear();
        historyRecord("clear");
        transport.send(new Answer("Коллекция была полностью очищена"));
    }

    public void commandHistory() {
        String send = "";
        for (int i = numberCommands; i >= numberCommands - 15; i--) {
            if (history.get(i) != null) {
                send = send + history.get(i).toString()  + "\n";
            }
        }
        historyRecord("history");
        transport.send(new Answer(send));
    }

    public void commandSave()  {
        fileWorker.filleWrite();
        historyRecord("save");
        transport.send(new Answer("Коллекция была полностью записана в файл"));
    }

    public void commandsPrintDescending() {
        StringBuilder stringBuilder = new StringBuilder(" ");
        collection.entrySet().stream().sorted(Map.Entry.comparingByValue()).map(Map.Entry::getValue).forEach(x -> stringBuilder.append("\n").append(x).toString());
        historyRecord("print_descending");
        transport.send(new Answer(stringBuilder.toString()));
    }

    public void commandCountByAverageMark(long mark) {
        int countOfMark = 0;
        for (Map.Entry<Integer, StudyGroup> element : collection.entrySet()) {
            if (element.getValue().getAverageMark() == mark) {
                countOfMark = countOfMark + 1;
            }
        }
        if (countOfMark == 0) {
            transport.send(new Answer("Таких элементов не существует"));
        } else {
            transport.send(new Answer("Количество элементов: " + countOfMark));
        }
    }

    public void commandRemoveAnyByTransferredStudents(int transferred) {
        ArrayList<Integer> listForRemoveTransferred = new ArrayList<Integer>();
        for (Map.Entry<Integer, StudyGroup> element : collection.entrySet()) {
            if (element.getValue().getTransferredStudents() == transferred) {
                listForRemoveTransferred.add(element.getKey());
            }
        }
        for (int k: listForRemoveTransferred) {
            collection.remove(listForRemoveTransferred.get(0));
        }
        transport.send(new Answer("Группа со значением " + transferred + " успешно удалена"));
    }

    public void commandRemoveLowerKey(int key) {
        ArrayList<Integer> listForRemove = new ArrayList<Integer>();
        for (Map.Entry<Integer, StudyGroup> element : collection.entrySet()) {
            if (element.getKey() < key) {
                listForRemove.add(element.getKey());
            }
        }
        for (int k : listForRemove) {
            collection.remove(k);
        }
        transport.send(new Answer("Все элементы коллекции, которые меньше " + key + " успешно удалены"));
    }

    public void commandRemoveKey(String argument) {
        int key = Integer.parseInt(argument);
        if (collection.containsKey(key)) {
            collection.remove(key);
            transport.send(new Answer("Элемент под номером " + key + " умпешно удален"));
        } else {
            transport.send(new Answer("Элемента с таким номером нет в коллекции"));
        }
    }

    public void commandReplaceIfLowe(int x, int y, int z) {
        int checkpoint = 0;
        long averageMark = 0;
        long countOfStudents = 0;
        int transferredStudents = 0;
        for (Map.Entry<Integer, StudyGroup> element : collection.entrySet()) {
            if (element.getKey() == x) {
                checkpoint = 1;
                averageMark = element.getValue().getAverageMark();
                countOfStudents = element.getValue().getStudentsCount();
                transferredStudents = element.getValue().getTransferredStudents();
            }
        }
        if (checkpoint == 1) {
            if (y == 1) {
                long z1 = Long.parseLong(String.valueOf(z));
                if (z1 < countOfStudents) {
                    while (!construct.checkStudentsCount1(z1,String.valueOf(x))) {}
                    while (!construct.makeStudyGroup()) ;
                    transport.send(new Answer("Успешно заменено"));
                } else {
                    transport.send(new Answer("Новое значение больше старого, замены не произошло"));
                }
            } else if(y==2) {
                if (z < transferredStudents) {
                    while (!construct.checkTransferredStudents1(z,String.valueOf(x)))
                        while (!construct.makeStudyGroup()) ;
                    transport.send(new Answer("Успешно заменено"));
                } else {
                    transport.send(new Answer("Новое значение больше старого, замены не произошло"));
                }
            } else {
                long z3 = Long.parseLong(String.valueOf(z));
                if (z3 < countOfStudents) {
                    while (!construct.checkStudentsCount1(z3,String.valueOf(x))) {}
                    while (!construct.makeStudyGroup()) ;
                    transport.send(new Answer("Успешно заменено"));
                } else {
                    transport.send(new Answer("Новое значение больше старого, замены не произошло"));
                }
            }
        } else {
            transport.send(new Answer("Такого элемента не существует"));
        }
    }

    public void commandUpdateExecute(int key, int change, String argument) {
        int checkpoint = 0;
        for (Map.Entry<Integer, StudyGroup> element : collection.entrySet()) {
            if (element.getKey() == key) {
                checkpoint = 1;
            }
        }
        if (checkpoint == 1) {
            StudyGroup studyGroup = collection.get(key);
            CollectionConstruct construct1 = new CollectionConstruct(collection,studyGroup,transport);
            if (change == 1) {
                while (!construct1.checkName(argument)) { }
                while (!construct1.makeStudyGroup()) ;
            } else if (change == 2) {
                long arg = Long.parseLong(argument);
                while (!construct1.checkStudentsCount1(arg,String.valueOf(key))) {}
                while (!construct1.makeStudyGroup()) ;
            } else if(change == 3) {
                int arg1 = Integer.parseInt(argument);
                while (!construct1.checkTransferredStudents1(arg1,String.valueOf(key)))
                    while (!construct1.makeStudyGroup()) ;
            } else if(change == 4) {
                long arg2 = Long.parseLong(argument);
                while (!construct1.checkStudentsCount1(arg2,String.valueOf(key))) {}
                while (!construct1.makeStudyGroup()) ;
            }
        } else {
            transport.send(new Answer("Не правильно ввели число"));
        }
    }

    public void commandInsertExecute(int key,String name, int x, int y, long students, long mark, int transferred, String adminName, String birthday, float height, int weight, String passportID) {
        CollectionConstruct construct2 = new CollectionConstruct(collection);
        if (construct2.checkId(key)) {
            construct2.checkName(name);
            construct2.creatCoordinates(x,y);
            construct2.checkStudentsCount1(students,String.valueOf(key));
            construct2.checkAverageMark1(mark,String.valueOf(key));
            construct2.checkTransferredStudents1(transferred,String.valueOf(key));
            construct2.cheakPersonInfo1(adminName,birthday,height,weight,passportID);
            construct2.makeStudyGroup1();
        }
    }
}
