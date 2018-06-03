package org.itis.androidgenerate.androidgeneration.testcases.Buttons;

import org.itis.androidgenerate.androidgeneration.library.Model;
import org.itis.androidgenerate.androidgeneration.library.ReaderWriter;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class ButtonCheck {

    public ReaderWriter readerWriter;
    public Model model;

    public ButtonCheck(ReaderWriter readerWriter, Model model) {

        this.readerWriter = readerWriter;
        this.model = model;

    }

    public void buttonFind() throws FileNotFoundException {
        Scanner scanner = readerWriter.inBegin();
        String ss;
        while (scanner.hasNext()) {
            ss = scanner.nextLine();
            if(ss.indexOf("(Button)") != -1) { buttonCase(ss);}
        }

    }


    public void buttonCase(String s) {
        int b = s.indexOf("R");
        int c = s.indexOf(")", b);
        readerWriter.methodName(model);
        model.setEnd(model.getEnd() + " throws Exception {\n\t\t" + model.getActivityName() + " activity = Robolectric.buildActivity("+ model.getActivityName() +".class).create().get();" +
                "\n\t\tButton view = (Button) activity.findViewById(" + s.substring(b,c) + ");\n\t\tassertNotNull(view);\n\t\t" +
                "view.performClick();\n\t\tassertThat(ShadowToast.getTextOfLatestToast(), equalTo('Yes'));\n\t}") ;

    }

}

