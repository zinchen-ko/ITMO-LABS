package thread;

import dateBase.BaseManager;
import util.Answer;
import util.CommandManager;
import util.CommandReader;

import java.util.concurrent.Callable;

public class ProcessThread implements Callable<String> {
    private CommandManager manager;
    private BaseManager baseManager;
    private Answer answer;
    private CommandReader reader;

    public ProcessThread(Answer answer,CommandManager manager, BaseManager baseManager,CommandReader reader) {
        this.answer=answer;
        this.manager=manager;
        this.baseManager=baseManager;
        this.reader=reader;
    }

    @Override
    public String call() throws Exception {
        return reader.reader(answer);
    }

}
