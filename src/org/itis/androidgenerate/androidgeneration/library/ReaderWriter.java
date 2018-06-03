package org.itis.androidgenerate.androidgeneration.library;

import java.io.*;
import java.util.Scanner;

public class ReaderWriter {


    public FileReader reader;
    public Scanner sc;
    public FileWriter writer;
    public static int q;
    public static int w = 1;
    public String ab = "";
    public Model model;

    public void filereader_writer(String name) throws FileNotFoundException {
        try {
            reader = new FileReader(name);
            File file = new File(model.getProjectPath() + "\\newTest\\" + className() + ".java");
            file.getParentFile().mkdirs();
            writer = new FileWriter(model.getProjectPath() + "\\newTest\\" + className() + ".java");


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sc = inBegin();

    }

    public void isBegin(String ab) throws FileNotFoundException {
        this.ab = ab;
        filereader_writer(ab);
    }

    public void begin(Model model) {
        this.q = model.getActivityNumber();
        this.model = model;

        model.setEnd("@RunWith(RobolectricTestRunner.class)\npublic class " + className() + " {\n");

    }

    public void end(Model model) {

        model.setEnd(model.getEnd() + "\n}");
        try {
            writer.write(model.getEnd());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Scanner inBegin() throws FileNotFoundException {
        FileReader ss = new FileReader(ab);
        Scanner sc = new Scanner(ss);
        return sc;
    }

    public String className() {

        return "ActivityTest" + q;

    }

    public void methodName(Model model) {
        model.setEnd(model.getEnd() + "\n\t@Test\n\tpublic void test" + (w++) + "()");
    }

}
