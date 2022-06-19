package dateBase;

import util.Answer;
import util.MD5Hasher;
import util.ServerTransport;

import java.sql.SQLException;

public class Authorization {

    private String login;
    private String password;
    private ServerTransport transport;
    private boolean isUser = true;
    private BaseManager baseManager;

    public Authorization(ServerTransport transport, BaseManager baseManager) throws SQLException, ClassNotFoundException {
        this.transport=transport;
        this.baseManager=baseManager;
    }

    public String user(Answer answer) throws SQLException {
        while (isUser) {
            if (!answer.getRegistration()) {
                login = answer.getLogin();
                answer.setPassword(MD5Hasher.hash(answer.getPassword() + "salt" + answer.getLogin()));
                if (baseManager.isUserExist(login, answer.getPassword())) {
                    return "Успех";
//                    transport.send(new Answer("Успех"));
                } else {
                    return "Ошибка";
//                    transport.send(new Answer("Ошибка"));
                }
            } else {
                login = answer.getLogin();
                answer.setPassword(MD5Hasher.hash(answer.getPassword() + "salt" + answer.getLogin()));
                baseManager.addUser(login, answer.getPassword());
                return "Успех";
            }
        }
        return null;
    }
}
