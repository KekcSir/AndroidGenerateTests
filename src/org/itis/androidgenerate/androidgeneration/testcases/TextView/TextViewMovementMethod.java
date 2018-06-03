package org.itis.androidgenerate.androidgeneration.testcases.TextView;

import org.itis.androidgenerate.androidgeneration.library.Model;
import org.itis.androidgenerate.androidgeneration.library.ReaderWriter;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextViewMovementMethod {

    public ReaderWriter readerWriter;
    public Model model;

    public TextViewMovementMethod() {
        this.readerWriter = readerWriter;
        this.model = model;
    }

    public void textViewFind() throws FileNotFoundException {
        Scanner scanner = readerWriter.inBegin();
        String ss;
        while (scanner.hasNext()) {
            ss = scanner.nextLine();
            if (ss.indexOf("(TextView)") != -1) {
                textViewNotNullCase(ss);
            }
        }

    }

    public void textViewNotNullCase(String s) {
        int b = s.indexOf("R");
        int c = s.indexOf(")", b);
        readerWriter.methodName(model);
        model.setEnd(model.getEnd() + " throws Exception {\n\t\t" + model.getActivityName() + " activity = Robolectric.buildActivity(" + model.getActivityName() + ".class).create().get();" +
                "    TextView textView = (TextView) activity.findViewById(" + s.substring(b, c) + ");\n" +
                "    MovementMethod movement = new ArrowKeyMovementMethod();\n" +
                "    assertNull(textView.getMovementMethod());\n" +
                "    textView.setMovementMethod(movement);\n" +
                "    assertThat(textView.getMovementMethod()).isSameAs(movement);");

    }
}
