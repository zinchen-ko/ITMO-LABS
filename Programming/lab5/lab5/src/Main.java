import collection.StudyGroup;
import com.google.gson.Gson;
import instruments.*;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

/**
 * {@author Zinchenko Konstantin P3115
 */

public class Main {

    private static final Gson Gson =new Gson();
    private static String homedir = System.getenv("LAB");

    /**
     * {@param args Accepts a file
     */
    public static void main(String[] args) throws IOException {
        HashMap<Integer, StudyGroup> collection= new HashMap<>();
        FileWorker fileWorker = new FileWorker(homedir,collection);
        CommandManager commandManager =new CommandManager(collection, fileWorker);
        CommandReader commandRead=new CommandReader(commandManager,fileWorker);
        fileWorker.fileRead();
        commandRead.reader();
    }
}