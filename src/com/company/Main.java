package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        final String mainPath = "F://TEST";             //Рабочий путь к файлам
        StringBuilder report = new StringBuilder();
        report.append("");                              //Заголовок файла лога
        String[][][] dir = new String[][][]{
                {
                        {"Games", "src", "main", "Main.java"},
                        {"Games", "src", "main", "Utils.java"},
                        {"Games", "src", "test"},
                        {"Games", "res", "drawables"},
                        {"Games", "res", "vectors"},
                        {"Games", "res", "icons"},
                        {"Games", "savegames"},
                        {"Games", "temp", "temp.txt"},
                }
        };

        Set<String> set = new LinkedHashSet<>();
        String path = "";
        for (int i = 0; i < dir.length; i++) {
            for (int j = 0; j < dir[i].length; j++) {
                for (String d : dir[i][j]) {
                    path = path + "/" + d;
                    set.add(path);
                }
                path = "";
            }
        }
        for (String cPath : set) {
            if (cPath.contains(".")) {
                File newFile = new File(mainPath + cPath);
                try {
                    boolean created = newFile.createNewFile();
                    if (created)
                        report.append("File " + cPath + " has been created\n");
                } catch (IOException ex) {
                    report.append(ex.getMessage() + "\n");
                }
                if (cPath.contains("temp.txt")) {
                    try (FileWriter writer = new FileWriter(newFile, false)) {
                        writer.write(String.valueOf(report));
                        writer.flush();
                    } catch (IOException ex) {
                        report.append(ex.getMessage() + "\n");
                    }
                }
            } else {
                File newDir = new File(mainPath + cPath);
                if (!newDir.isDirectory()) {
                    boolean created = newDir.mkdir();
                    if (created) report.append("Folder " + cPath + " has been created\n");
                } else {
                    report.append("Folder " + cPath + " already exist\n");
                }
            }
        }
    }
}
