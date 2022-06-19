package run;

import collection.StudyGroup;
import com.google.gson.Gson;
//import dataBase.DataBaseManager;
import dateBase.BaseManager;
import util.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;

public class Main {
//    private static Map<Integer, StudyGroup> collection = new HashMap<Integer, StudyGroup>();
//    private static FileWorker fileWorker = new FileWorker("C://Python/LAB.txt", collection);
//    private static ServerTransport transport = new ServerTransport();
//    private static Gson gson = new Gson();
//    private static CommandManager manager = new CommandManager(collection, fileWorker, transport);
//    private static CommandReader reader = new CommandReader(transport, manager, collection);

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        Map<Integer, StudyGroup> collection = new HashMap<Integer, StudyGroup>();
        FileWorker fileWorker = new FileWorker("LAB", collection);
        ServerTransport transport = new ServerTransport();
        BaseManager baseManager = new BaseManager(3115,transport,collection);
        collection=baseManager.getCollection();
        CommandManager manager = new CommandManager(collection, fileWorker, transport,baseManager);
        CommandReader reader = new CommandReader(transport, manager, collection,baseManager);
        System.out.println("Сервер запущен");
        System.out.println("Ожидание команды от клиента");

        ServerConsole console = new ServerConsole(transport,baseManager,reader,manager);
        console.run();
    }
}
