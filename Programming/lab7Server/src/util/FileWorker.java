package util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import collection.*;
import com.google.gson.Gson;

public class FileWorker {

    private String filleName;
    private final Gson json = new Gson();
    private Map<Integer, StudyGroup> collection;
    Date datePreservation;
    Date dateDownloads;

    public FileWorker(String filleName, Map<Integer, StudyGroup> collection) {
        this.filleName = filleName;
        this.collection = collection;
    }

    public FileWorker(){}

    /**
     * A class containing methods for reading and writing
     */



    public boolean fileRead() throws IOException {

        File file = new File("LAB");
        if (fileCheckAccessRead1(file)) {
            InputStreamReader inputStreamReader;
            BufferedReader bufferedReader = null;
            inputStreamReader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            bufferedReader = new BufferedReader(inputStreamReader);
            try {
                while (bufferedReader.ready()) {
                    String s = bufferedReader.readLine();
                    StudyGroup studyGroup = json.fromJson(s, StudyGroup.class);
                    int id = studyGroup.getId();
                    collection.put(id, studyGroup);
                }
                bufferedReader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            fixNameFileRead();
            fileRead();
        }
        dateDownloads=new Date();
        return true;
    }

    /**
     * Checks the file for readability
     * @param file file for reading
     * @return Returns true if the file can be read
     */
    public boolean fileCheckAccessRead(File file) {
        if (file.length()!=0) {
            if (file.exists()) {
                if (file.canRead()) {
                    return true;
                } else {
                    System.out.println("К сожалению у вас нет прав на чтение этого файла  ");
                    return false;
                }
            } else {
                System.out.println("Файла с указанным именем не существует");
                return false;
            }
        }else{
            System.out.println("Вы не ввели название файла. Пожалкйста введите его ");
            return false;
        }
    }
    public static boolean fileCheckAccessRead1(File file) {
        if (file.length()!=0) {
            if (file.exists()) {
                if (file.canRead()) {
                    return true;
                } else {
                    System.out.println("К сожалению у вас нет прав на чтение этого файла  ");
                    return false;
                }
            } else {
                System.out.println("Файла с указанным именем не существует");
                return false;
            }
        }else{
            System.out.println("Вы не ввели название файла. Пожалкйста введите его ");
            return false;
        }
    }


    public void filleWrite() {
        File check = new File(filleName);
        if (fileCheckAccessWrite(check)) {
            try {
                FileWriter writer = new FileWriter(check);
                for (Map.Entry<Integer, StudyGroup> element : collection.entrySet()) {
                    StudyGroup studyGroup1 = collection.get(element.getKey());
                    String colectJson = json.toJson(studyGroup1) + "\n";
                    writer.write(colectJson);
                }
                writer.flush();
                writer.close();
            } catch (FileNotFoundException e) {
                System.err.println("К сожалению, файла по указанному адресу не существует.");
            } catch (IOException e) {
                System.out.println("Невозможно записать данные в файл");
            }
            System.out.println("Коллекция успешно сохранена");
            datePreservation = new Date();
        }else {
            fixNameFileWrite();
            filleWrite();
        }
    }

    /**
     * Checks the file for write capability
     * @param file file for writing
     * @return Returns true if the file can be write
     */
    private boolean fileCheckAccessWrite(File file) {
        if (file.exists()) {
            if (file.canWrite()) {
                return true;
            } else {
                System.out.println("К сожалению, у вас нет прав на запись в этот файл");
                return false;
            }
        } else {
            System.out.println("К сожалению файла с указанныи именем не существет ");
            return false;
        }
    }

    private void  fixNameFileRead(){
        System.out.println("Пожалуйста, введите другое название файла.Для выхода из программы напишите exit");
        Scanner scanner=new Scanner(System.in);
        filleName = scanner.nextLine();
    }

    private void fixNameFileWrite(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Если хотите изменить файл для записи введите его название, если хотите закрыть режим сохранения введите exit");
        filleName = scanner.nextLine();
    }
}