package org.itis.androidgenerate.main.separation;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ClassSeparation {

    private ArrayList<String> paths;
    private ArrayList<String> ideaClass;
    private ArrayList<String> androidClass;

    private String testPath = "";

    public ArrayList<String> getIdeaClass() {
        return ideaClass;
    }

    public ArrayList<String> getAndroidClass() {
        return androidClass;
    }


    public ClassSeparation() {
        paths = new ArrayList<String>();
        androidClass = new ArrayList<>();
        ideaClass = new ArrayList<>();
    }

    public ArrayList<String> getPaths() {
        return paths;
    }

    public boolean fileContainsWord(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName))).contains("AppCompatActivity");
    }

    public String getTestPath() {

        return testPath;
    }

    public void files(File folder) throws IOException {

        File[] folderEntries = folder.listFiles();
        for (File entry : folderEntries) {
            if (entry.isDirectory()) {
                if (entry.getPath().endsWith("test\\java")) {
                    testPath = entry.getPath();
                }
                files(entry);
                continue;
            } else {
                if (entry.getAbsolutePath().substring(entry.getAbsolutePath().length() - 5, entry.getAbsolutePath().length()).equals(".java")) {
                    paths.add(entry.getAbsolutePath());
                }

            }
        }
    }

    public void razdelenie(File folder) throws IOException {
        files(folder);
        for (String path : paths) {
            if (fileContainsWord(path)) {
                System.out.println(path);
                androidClass.add(path);
            } else {
                ideaClass.add(path);
            }
        }
    }
}
