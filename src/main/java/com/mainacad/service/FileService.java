package com.mainacad.service;

import com.mainacad.model.ConnectionInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    public static final String MAIN_DIR = System.getProperty("user.dir");
    public static final String SEP = System.getProperty("file.separator");
    public static final String FILES_DIR = MAIN_DIR + SEP + "files";

    public static synchronized void writeTextToFile(String text, String fileName, boolean append) {
        checkTargetDir();
        try (FileWriter fileWriter = new FileWriter(FILES_DIR + SEP + fileName, append)) {
            fileWriter.write(text + "\n");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkTargetDir() {
        File file = new File(FILES_DIR);
        if(!file.exists()) {
            file.mkdir();
        }
    }

    public  static String readTextFromFile(String fileName) {
        String out = "";

        try (BufferedReader bufferedReader =
                     new BufferedReader(new FileReader(FILES_DIR + SEP + fileName))) {
            String line;
            while ( ( line = bufferedReader.readLine()) != null ) {
                out += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }

    public  static List<ConnectionInfo> readConnectionsFromFile(String fileName) {
        List<ConnectionInfo> connectionInfoList = new ArrayList<>();

        try (FileReader fileReader = new FileReader(FILES_DIR + SEP + fileName);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ( ( line = bufferedReader.readLine()) != null ) {
                ConnectionInfo connectionInfo = new ConnectionInfo();

                String[] words = line.split(" ");
                connectionInfo.setSessionId(Integer.valueOf(words[0]));
                connectionInfo.setConnectionTime(Long.valueOf(words[1]));
                connectionInfo.setIp(words[2]);

                connectionInfoList.add(connectionInfo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return connectionInfoList;
    }
}

