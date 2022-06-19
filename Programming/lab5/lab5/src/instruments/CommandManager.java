package instruments;

import collection.*;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class CommandManager {
    private CommandsExecuteScript commandsExecuteScript;
    private FileWorker fileWorker;
    private LinkedHashMap<Integer, String> history = new LinkedHashMap<Integer, String>();
    private int numberCommands = 0;
    private final Gson json = new Gson();
    private Map<Integer, StudyGroup> collection;
    private boolean flag1 = true;

    public CommandManager(HashMap<Integer, StudyGroup> collection, FileWorker fileWorker) {
        this.collection = collection;
        this.fileWorker = fileWorker;
    }


    private ArrayList<String> commandList = new ArrayList<String>();

    {
        commandList.add("help                                : вывести справку по доступным командам");
        commandList.add("info                                : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        commandList.add("show                                : вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        commandList.add("insert                              : добавить новый элемент с заданным ключом");
        commandList.add("update                              : обновить значение элемента коллекции, id которого равен заданному");
        commandList.add("remove_key                          : удалить элемент из коллекции по его ключу");
        commandList.add("clear                               : очистить коллекцию");
        commandList.add("save                                : сохранить коллекцию в файл");
        commandList.add("execute_script                      : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        commandList.add("exit                                : завершить программу (без сохранения в файл)");
        commandList.add("history                             : вывести последние 13 команд (без их аргументов)");
        commandList.add("replace_if_lowe                     : заменить значение по ключу, если новое значение меньше старого");
        commandList.add("remove_lower_key                    : удалить из коллекции все элементы, ключ которых меньше, чем заданный");
        commandList.add("remove_any_by_transferred_students  : удалить из коллекции один элемент, значение поля transferredStudents которого эквивалентно заданному");
        commandList.add("count_by_average_mark               : вывести количество элементов, значение поля averageMark которых равно заданному");
        commandList.add("print_descending                    : вывести элементы коллекции в порядке убывания");

    }

    Scanner scanner = new Scanner(System.in);

    /**
     * All methods of this class process the data entered by the user.
     */

    public void historyRecord(String command) {
        numberCommands = numberCommands + 1;
        history.put(numberCommands, command);
        if (numberCommands > 13) {
            history.remove(numberCommands - 13);
        }
    }

    public void commandsHelp() {
        for (String comand : commandList) {
            System.out.println(comand);
        }
    }

    public void commandsInfo() {
        System.out.println("Колличество элементов в коллекции           : " + collection.size());
        System.out.println("Дата загрузки коллекции из файла            : " + fileWorker.dateDownloads);
        if (fileWorker.datePreservation == null) {
            System.out.println("Сохранения коллекции в файл в текущей сессии не происходило");
        } else {
            System.out.println("Дата последнего сохранения коллекции в файл : " + fileWorker.datePreservation);
        }
        System.out.println("Всего было введено валидных команд:" + numberCommands + "шт");
    }

    public void commandsShow() {
        for (Map.Entry<Integer, StudyGroup> element : collection.values()) {
            System.out.println(element);
        }
    }

    public void commandsInsert(int key) {
        FileConstruct fileConstruct = new FileConstruct(collection);
        while (!fileConstruct.checkId(key)) {
        }
        while (!fileConstruct.checkName()) ;
        while (!fileConstruct.creatCoordinates()) ;
        while (!fileConstruct.checkStudentsCount()) ;
        while (!fileConstruct.checkAverageMark()) ;
        while (!fileConstruct.checkTransferredStudents()) ;
        while (!fileConstruct.checkPersonName()) ;
        while (!fileConstruct.cheakPersonInfo()) ;
        while (!fileConstruct.makeStudyGroup()) ;
        System.out.println("Элемент успешно добавлен в колекцию");
    }

    public void commandsUpdate(int key) {
        int checkpoint = 0;
        for (Map.Entry<Integer, StudyGroup> element : collection.entrySet()) {
            if (element.getKey() == key) {
                checkpoint = 1;
            }
        }
        if (checkpoint == 1) {
            StudyGroup studyGroup = collection.get(key);
            FileConstruct fileConstruct = new FileConstruct(collection, studyGroup);
            System.out.println("Введите номер поля, которое вы хотите изменить");
            System.out.println("Название группы        - 1");
            System.out.println("Месторасположение      - 2");
            System.out.println("Количество учеников    - 3");
            System.out.println("Средний балл           - 4");
            System.out.println("Количество иностранцев - 5");
            System.out.println("Год обучения           - 6");
            System.out.println("Админа группы          - 7");
            switch (scanner.nextLine().trim()) {
                case "1":
                    while (!fileConstruct.checkName()) {
                    }
                    while (!fileConstruct.makeStudyGroup()) ;
                    break;
                case "2":
                    while (!fileConstruct.creatCoordinates())
                        while (!fileConstruct.makeStudyGroup()) ;
                    break;
                case "3":
                    while (!fileConstruct.checkStudentsCount())
                        while (!fileConstruct.makeStudyGroup()) ;
                    break;
                case "4":
                    while (!fileConstruct.checkAverageMark())
                        while (!fileConstruct.makeStudyGroup()) ;
                    break;
                case "5":
                    while (!fileConstruct.checkTransferredStudents())
                        while (!fileConstruct.makeStudyGroup()) ;
                    break;
                case "6":
                    while (!fileConstruct.setSemester())
                        while (!fileConstruct.makeStudyGroup()) ;
                    break;
                case "7":
                    while (!(fileConstruct.checkPersonName()) && (fileConstruct.cheakPersonInfo()))
                        while (!fileConstruct.makeStudyGroup()) ;
                    break;
                default:
                    System.out.println("Вы ввели неверное значение");
            }
        } else {
            System.out.println("Элемента с таким id не существует, введите команду заново, и будьте внимательней.");
        }
    }

    public void commandsRemoveKey(int key) {
        if (collection.containsKey(key)) {
            collection.remove(key);
            System.out.println("Элемент под номером " + key + " умпешно удален");
        } else {
            System.out.println("Элемента с таким номером нет в коллекции");
        }
    }

    public void commandsClear() {
        collection.clear();
        System.out.println("Коллекция полностью очищена");
    }

    public void commandsSave() throws IOException {
        fileWorker.filleWrite();
    }

    public void commandsHistory() {
        for (int i = numberCommands; i >= numberCommands - 15; i--) {
            if (history.get(i) != null) {
                System.out.println(history.get(i));
            }
        }
    }

    public void commandsReplaceIfLowe(int key) {
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
            FileConstruct fileConstruct = new FileConstruct(collection, studyGroup);
            System.out.println("Введите номер поля, которое вы хотите изменить");
            System.out.println("Количество учеников    - 1");
            System.out.println("Количество иностранцев - 2");
            System.out.println("Средний балл           - 3");
            switch (scanner.nextLine().trim()) {
                case "1":
                    System.out.println("Введите новое значение количества студентов");
                    long students = Long.parseLong(scanner.nextLine());
                    if (students < countOfStudents) {
                        while (!fileConstruct.checkStudentsCount())
                            while (!fileConstruct.makeStudyGroup()) ;
                        System.out.println("Успешно заменено");
                    } else {
                        System.out.println("Новое значение больше старого, замены не произошло");
                    }
                    break;
                case "2":
                    System.out.println("Введите новое значение количества иностранцев");
                    int transferred = Integer.parseInt(scanner.nextLine());
                    if (transferred < transferredStudents) {
                        while (!fileConstruct.checkTransferredStudents())
                            while (!fileConstruct.makeStudyGroup()) ;
                        System.out.println("Успешно заменено");
                    } else {
                        System.out.println("Новое значение больше старого, замены не произошло");
                    }
                    break;
                case "3":
                    System.out.println("Введите новое значение среднего балла");
                    long average = Long.parseLong(scanner.nextLine());
                    if (average < averageMark) {
                        while (!fileConstruct.checkAverageMark())
                            while (!fileConstruct.makeStudyGroup()) ;
                        System.out.println("Успешно заменено");
                    } else {
                        System.out.println("Новое значение больше старого, замены не произошло");
                    }
                    break;
                default:
                    System.out.println("Вы ввели неверные данные, попробуйте снова, и будьте внимательней");
            }
        } else {
            System.out.println("Элемента с таким id не существует");
        }
    }

    public void commandsPrintDescending() {
        TreeSet < StudyGroup> collections = new TreeSet<StudyGroup>();
        for (Map.Entry<Integer, StudyGroup> element : collection.entrySet()) {
            collections.add(element.getValue());
        }
        for (StudyGroup barsuk : collections) {
            System.out.println(barsuk);
        }
    }

    public void commandsRemoveLowerKey(int key) {
        ArrayList<Integer> listForRemove = new ArrayList<Integer>();
        for (Map.Entry<Integer, StudyGroup> element : collection.entrySet()) {
            if (element.getKey() < key) {
                listForRemove.add(element.getKey());
            }
        }
        for (int k : listForRemove) {
            collection.remove(k);
        }
        System.out.println("Все элементы коллекции, которые меньше " + key + " успешно удалены");
    }

    public void commandsRemoveAnyByTransferredStudents(int transferred) {
        ArrayList<Integer> listForRemoveTransferred = new ArrayList<Integer>();
        for (Map.Entry<Integer, StudyGroup> element : collection.entrySet()) {
            if (element.getValue().getTransferredStudents() == transferred) {
                    listForRemoveTransferred.add(element.getKey());
            }
        }
        for (int k: listForRemoveTransferred) {
            collection.remove(listForRemoveTransferred.get(0));
        }
        System.out.println("Рандоманый чувак с таким параметром был удален");
    }

    public void commandsCountByAverageMark(long mark) {
        int countOfMark = 0;
        for (Map.Entry<Integer, StudyGroup> element : collection.entrySet()) {
            if (element.getValue().getAverageMark() == mark) {
                countOfMark = countOfMark + 1;
            }
        }
        if (countOfMark == 0) {
            System.out.println("Таких элементов не существует");
        } else {
            System.out.println("Количество элементов: " + countOfMark);
        }
    }
    public void commandInsertExecute(int key, ArrayList commands) {

        FileConstruct fileConstruct = new FileConstruct(collection);
        if (fileConstruct.checkId(key)) {
            if (fileConstruct.checkName((String) commands.get(1))) {
                if (fileConstruct.creatCoordinates((String) commands.get(2), (String) commands.get(3))) {
                    if (fileConstruct.checkStudentsCount1((String) commands.get(4))) {
                        if (fileConstruct.checkAverageMark1((String) commands.get(5))) {
                            if (fileConstruct.checkTransferredStudents1((String) commands.get(6))) {
                                if (fileConstruct.checkPersonName((String) commands.get(7))) {
                                    if (fileConstruct.cheakPersonInfo1((String) commands.get(8),(String) commands.get(9),(String) commands.get(10),(String) commands.get(11))) {
                                        if (fileConstruct.makeStudyGroup()) {
                                           System.out.println("Элемент успешно добавлен в коллекцию");
                                            removeComand(11,commands);
                                        }
                                    } else {
                                        removeComand(11,commands);
                                    }

                                } else {
                                    removeComand(8,commands);
                                }

                            } else {
                                removeComand(7, commands);
                            }

                        } else {
                            removeComand(6, commands);
                        }

                    } else {
                        removeComand(5, commands);
                    }

                } else {
                    removeComand(4, commands);
                }
            } else {
                removeComand(3, commands);
            }
        } else {
            removeComand(1, commands);
        }
    }

    public void newcommandsUpdateExecute(int key, ArrayList commands) {
        int checkpoint = 0;
        for (Map.Entry<Integer, StudyGroup> element : collection.entrySet()) {
            if (element.getKey() == key) {
                checkpoint = 1;
            }
        }
        if (checkpoint == 1) {
            StudyGroup studyGroup = collection.get(key);
            FileConstruct fileConstruct = new FileConstruct(collection, studyGroup);

            switch ((String) commands.get(1)) {
                case "1":
                    if (fileConstruct.checkName((String) commands.get(2))) {
                        fileConstruct.makeStudyGroup();
                    } else {
                        System.out.println("Значение строки не подходит");
                    }
                    removeComand(2, commands);

                    break;
                case "2":

                    if (fileConstruct.creatCoordinates((String) commands.get(2), (String) commands.get(3))) {
                        fileConstruct.makeStudyGroup();

                    }
                    removeComand(3, commands);

                    break;
                case "3":
                    if (fileConstruct.checkStudentsCount1((String) commands.get(2))) {
                        fileConstruct.makeStudyGroup();
                    }
                    removeComand(2, commands);
                    break;
                case "4":
                    if (fileConstruct.checkAverageMark1((String) commands.get(2))) {
                        fileConstruct.makeStudyGroup();
                    }
                    removeComand(2, commands);
                    break;
                case "5":
                    if (fileConstruct.checkTransferredStudents1((String) commands.get(2))) {
                        fileConstruct.makeStudyGroup();
                    }
                    removeComand(2, commands);

                    break;
                case "6":
                    if (fileConstruct.checkPersonName((String) commands.get(2))) {
                        fileConstruct.makeStudyGroup();
                    }
                    removeComand(2, commands);
                    break;
                case "7":
                    if (fileConstruct.cheakPersonInfo1((String) commands.get(2),(String) commands.get(3),(String) commands.get(4),(String) commands.get(5))) {
                        fileConstruct.makeStudyGroup();
                    }
                    removeComand(5, commands);
                    break;
                default:
                    System.out.println("Вы ввели невалидное значение. Для повтора выполните запрос update (id)");
            }
        } else {
            System.out.println("Элемента с таким id не существует, процесс выполнения данной команды был прерван");
        }
        System.out.println("Элемент успешно отредактирован");
    }


    private void removeComand(int x, ArrayList com) {
        int k = x;
        while (k > 0) {
            com.remove(1);
            k = k - 1;
        }


    }

    public void commandsExecuteScript(CommandManager commandManager, String filleName) {
        CommandsExecuteScript commandsExecuteScript = new CommandsExecuteScript(filleName, commandManager, fileWorker);
        try {
            commandsExecuteScript.startRead();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        commandsExecuteScript = null;
        System.gc();

    }


}