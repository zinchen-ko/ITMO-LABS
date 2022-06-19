package util;

import collection.*;
import dateBase.BaseManager;

import java.sql.SQLException;
import java.util.*;

public class CommandManager {

    private FileWorker fileWorker;
    private LinkedHashMap<Integer, String> history = new LinkedHashMap<Integer, String>();
    private int numberCommands = 0;
    private Map<Integer, StudyGroup> collection;
    private ServerTransport transport;
    private BaseManager baseManager;

    public CommandManager(Map<Integer, StudyGroup> collection, FileWorker fileWorker, ServerTransport transport,BaseManager baseManager) {
        this.collection = collection;
        this.fileWorker = fileWorker;
        this.transport=transport;
        this.baseManager=baseManager;

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

    public String commandHelp() {
        String commandHelp = "";
        for (String comand : commandList) {
            if (commandHelp.equals("")) {
                commandHelp = comand;
            } else {
                commandHelp = commandHelp + "\n" + comand;
            }
        }
        return commandHelp;
//        transport.send(new Answer(commandHelp));
    }

    public String commandInfo() {
        String send = "";
        send = "Колличество элементов в коллекции : " + collection.size() + "\n";
        send = send + "Дата загрузки коллекции из файла : " + fileWorker.dateDownloads + "\n";
        if (fileWorker.datePreservation == null) {
            send = send + "Сохранения коллекции в файл в текущей сессии не происходило" + "\n";
        } else {
            send = send + "Дата последнего сохранения коллекции в файл : " + fileWorker.datePreservation + "\n";
        }
        send = send + "Всего было введено валидных команд:" + numberCommands + "шт" + "\n";
        return send;
    }

    public String commandShow() {
        StringBuilder stringBuilder = new StringBuilder(" ");
        collection.entrySet().stream().map(Map.Entry::getValue).forEach(x -> stringBuilder.append("\n").append(x).toString());
//        transport.send(new Answer(stringBuilder.toString()));
        return stringBuilder.toString();
    }

    public String commandClear(Answer answer) throws SQLException {
        ArrayList<Integer> listForRemove = new ArrayList<Integer>();
        for (Map.Entry<Integer, StudyGroup> element : collection.entrySet()) {
            if (baseManager.isStudyOwnedBy(element.getKey(),answer.getLogin())) {
                baseManager.removeStudy(element.getKey());
                listForRemove.add(element.getKey());
            }
        }
        for (int k : listForRemove) {
            collection.remove(k);
        }
//        transport.send(new Answer("Коллекция была полностью очищена"));
        return "Ваши элементы были очищены";
    }

//    public void commandSave() throws IOException {
//        fileWorker.filleWrite();
//        transport.send(new Answer("Коллекция была полностью записана в файл"));
//    }

    public String commandHistory() {
        String send = "";
        for (int i = numberCommands; i >= numberCommands - 15; i--) {
            if (history.get(i) != null) {
                send = send + history.get(i).toString()  + "\n";
            }
        }
//        transport.send(new Answer(send));
        return send;
    }

    public String commandPrintDescending() {
        StringBuilder stringBuilder = new StringBuilder(" ");
        collection.entrySet().stream().sorted(Map.Entry.comparingByValue()).map(Map.Entry::getValue).forEach(x -> stringBuilder.append("\n").append(x).toString());
//        transport.send(new Answer(stringBuilder.toString()));
        return stringBuilder.toString();
    }

    public String commandRemoveLowerKey(Answer answer) throws SQLException {
        int key = Integer.parseInt(answer.getArgument());
        ArrayList<Integer> listForRemove = new ArrayList<Integer>();
        for (Map.Entry<Integer, StudyGroup> element : collection.entrySet()) {
            if (element.getKey() < key) {
                if (baseManager.isStudyOwnedBy(element.getKey(),answer.getLogin())) {
                    baseManager.removeStudy(element.getKey());
                    listForRemove.add(element.getKey());
                }
            }
        }
        for (int k : listForRemove) {
            collection.remove(k);
        }
//        transport.send(new Answer("Все элементы коллекции, которые меньше " + key + " успешно удалены"));
        return "Все элементы коллекции, которые меньше " + key + " успешно удалены";
    }

    public String commandRemoveAnyByTransferredStudents(Answer answer) throws SQLException {
        int transferred = Integer.parseInt(answer.getArgument());
        ArrayList<Integer> listForRemoveTransferred = new ArrayList<Integer>();
        for (Map.Entry<Integer, StudyGroup> element : collection.entrySet()) {
            if (element.getValue().getTransferredStudents() == transferred) {
                if (baseManager.isStudyOwnedBy(element.getKey(),answer.getLogin())) {
                    baseManager.removeStudy(element.getKey());
                    listForRemoveTransferred.add(element.getKey());
                }
            }
        }
        for (int k: listForRemoveTransferred) {
            collection.remove(listForRemoveTransferred.get(0));
        }
//        transport.send(new Answer("Элемент со значением " + transferred + " успешно удалена"));
        return "Элемент со значением " + transferred + " успешно удалена";
    }

    public String commandCountByAverageMark(Answer answer) {
        long mark = Long.parseLong(answer.getArgument());
        int countOfMark = 0;
        for (Map.Entry<Integer, StudyGroup> element : collection.entrySet()) {
            if (element.getValue().getAverageMark() == mark) {
                countOfMark = countOfMark + 1;
            }
        }
        if (countOfMark == 0) {
//            transport.send(new Answer("Таких элементов не существует"));
            return "Таких элементов не существует";
        } else {
//            transport.send(new Answer("Количество элементов: " + countOfMark));
            return "Количество элементов: " + countOfMark;
        }
    }

    public String commandRemoveKey(Answer answer) throws SQLException {
        int argument = Integer.parseInt(answer.getArgument());
        if (baseManager.isStudyOwnedBy(argument,answer.getLogin())) {
            int key = Integer.parseInt(answer.getArgument());
            if (collection.containsKey(key)) {
                collection.remove(key);
//                transport.send(new Answer("Элемент под номером " + key + " умпешно удален"));
                baseManager.removeStudy(key);
                return "Элемент под номером " + key + " умпешно удален";
            } else {
//                transport.send(new Answer("Ошибка"));
//                transport.send(new Answer("Элемента с таким номером нет в коллекции"));
                return "Элемента с таким номером нет в коллекции";
            }
        } else {
//            transport.send(new Answer("Результат"));
//            transport.send(new Answer("У вас нет доступа к этому элементу коллекции"));
            return "У вас нет доступа к этому элементу коллекции";
        }
    }

    public String commandInsert(Answer answer) throws SQLException {
        baseManager.addElement(answer.getStudyGroup(),answer.getLogin());
        collection=baseManager.getCollection();
//        transport.send(new Answer("Успешно добавлено"));
        return "Успешно добавлено";
//        int key = studyGroup.getId();
//        if (collection.containsKey(key)) {
//            transport.send(new Answer("Элемент с таким ключом уще существует"));
//        } else {
//            collection.put(key,studyGroup);
//            transport.send(new Answer("Успешно заменено"));
//            baseManager.addElement(studyGroup,login);
//        }
    }

    public String commandUpdate(Answer answer) throws SQLException {
        int argument = Integer.parseInt(answer.getArgument());
        if (baseManager.isStudyOwnedBy(argument,answer.getLogin())) {
            int key = Integer.parseInt(answer.getArgument());
            int checkpoint = 0;
            for (Map.Entry<Integer, StudyGroup> element : collection.entrySet()) {
                if (element.getKey() == key) {
                    checkpoint = 1;
                }
            }
            if (checkpoint == 1) {
                StudyGroup studyGroup = collection.get(key);
                CollectionConstruct construct = new CollectionConstruct(collection, studyGroup,transport,baseManager);
                switch (answer.getArgument2()) {
                    case "1":
                        Long arg1 = Long.parseLong(answer.getArgument3());
                        while (!construct.checkStudentsCount(key,arg1)) {}
                        while (!construct.makeStudyGroup()) ;
                        break;
                    case "3":
                        Long arg2 = Long.parseLong(answer.getArgument3());
                        while (!construct.checkAverageMark(key,arg2)) {}
                        while (!construct.makeStudyGroup()) ;
                        break;
                    case "2":
                        int arg3 = Integer.parseInt(answer.getArgument3());
                        while (!construct.checkTransferredStudents(key,arg3)) {}
                        while (!construct.makeStudyGroup()) ;
                        break;
                    default:
//                        transport.send(new Answer("Ошибка"));
//                        transport.send(new Answer("Вы ввели не то число"));
                        return "Вы ввели не то число";
                }
            } else {
//                transport.send(new Answer("Ошибка"));
//                transport.send(new Answer("Элемента с таким id не существует, введите команду заново, и будьте внимательней"));
                return "Элемента с таким id не существует, введите команду заново, и будьте внимательней";
            }
        } else {
//            transport.send(new Answer("Результат"));
//            transport.send(new Answer("У вас нет доступа к этому элементу коллекции"));
            return "У вас нет доступа к этому элементу коллекции";
        }
        return "Успешно заменено";
    }

    public String commandReplaceIfLowe(Answer answer) throws SQLException {
        int argument = Integer.parseInt(answer.getArgument());
        if (baseManager.isStudyOwnedBy(argument, answer.getLogin())) {
            int key = Integer.parseInt(answer.getArgument());
            int checkpoint = 0;
            long averageMark = 0;
            long countOfStudents = 0;
            int transferredStudents = 0;
            for (Map.Entry<Integer, StudyGroup> element : collection.entrySet()) {
                if (element.getKey() == key) {
                    checkpoint = 1;
                    averageMark = element.getValue().getAverageMark();
                    countOfStudents = element.getValue().getStudentsCount();
                    transferredStudents = element.getValue().getTransferredStudents();

                }
            }
            if (checkpoint == 1) {
                StudyGroup studyGroup = collection.get(key);
                CollectionConstruct construct = new CollectionConstruct(collection, studyGroup, transport, baseManager);
                switch (answer.getArgument2()) {
                    case "1":
                        long students = Long.parseLong(answer.getArgument3());
                        if (students < countOfStudents) {
                            while (!construct.checkStudentsCount1(students,answer.getArgument())) {
                            }
                            while (!construct.makeStudyGroup()) ;
                            return "Успешно заменено";
                        } else {
//                            transport.send(new Answer("Ошибка"));
//                            transport.send(new Answer("Новое значение больше старого, замены не произошло"));
                            return "Новое значение больше старого, замены не произошло";
                        }
                    case "2":
                        int transferred = Integer.parseInt(answer.getArgument3());
                        if (transferred < transferredStudents) {
                            while (!construct.checkTransferredStudents1(transferred,answer.getArgument())) {
                            }
                            while (!construct.makeStudyGroup()) ;
                            return "Успешно заменено";
                        } else {
//                            transport.send(new Answer("Ошибка"));
//                            transport.send(new Answer("Новое значение больше старого, замены не произошло"));
                            return "Новое значение больше старого, замены не произошло";
                        }
                    case "3":
                        long average = Long.parseLong(answer.getArgument3());
                        if (average < averageMark) {
                            while (!construct.checkAverageMark1(average,answer.getArgument())) {
                            }
                            while (!construct.makeStudyGroup()) ;
                            return "Успешно заменено";
                        } else {
//                            transport.send(new Answer("Результат"));
//                            transport.send(new Answer("Новое значение больше старого, замены не произошло"));
                            return "Новое значение больше старого, замены не произошло";
                        }
                    default:
//                        transport.send(new Answer("Ошибка"));
//                        transport.send(new Answer("Вы ввели неверные данные, попробуйте снова, и будьте внимательней"));
                        return "Вы ввели неверные данные, попробуйте снова, и будьте внимательней";
                }
            } else {
//                transport.send(new Answer("Ошибка"));
//                transport.send(new Answer("Элемента с таким id не существует"));
                return "Элемента с таким id не существует";
            }
        } else {
//            transport.send(new Answer("Ошибка"));
//            transport.send(new Answer("У вас нет доступа к этому элементу коллекции"));
            return "У вас нет доступа к этому элементу коллекции";
        }
    }

}