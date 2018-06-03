package org.itis.androidgenerate.androidgeneration.testcases.Buttons;

import org.itis.androidgenerate.androidgeneration.library.Model;
import org.itis.androidgenerate.androidgeneration.library.ReaderWriter;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class LoginAndPasswordCheck {

    public ReaderWriter readerWriter;
    public Model model;
    public String login = "";
    public String password = "";
    public String button = "";

    public LoginAndPasswordCheck(ReaderWriter readerWriter, Model model) {

        this.readerWriter = readerWriter;
        this.model = model;

    }

    public void loginAndPasswordFind() throws FileNotFoundException {
        Scanner scanner = readerWriter.inBegin();
        String ss;
        while (scanner.hasNext()) {
            ss = scanner.nextLine();
            if(ss.indexOf("EditText") != -1 && ss.indexOf("login") != -1) { login = ss;}
            if(ss.indexOf("EditText") != -1 && ss.indexOf("password") != -1) { password = ss;}
            if(ss.indexOf("Button") != -1 && ss.indexOf("connected") != -1) { button = ss;}
        }
        if(login != "" && password != "" && button != "" ) {
            loginAndPasswordCase();
        }

    }

    public void loginAndPasswordCase() {

        int a = login.indexOf("R");
        int aa = login.indexOf(")",a);
        int b = password.indexOf("R");
        int bb = password.indexOf(")",b);
        int c = button.indexOf("R");
        int cc = button.indexOf(")",c);
        readerWriter.methodName(model);
        model.setEnd(model.getEnd() + " throws Exception {\n\t\t" + model.getActivityName() + " activity = Robolectric.setupActivity(" + model.getActivityName() +".class); \n\t\tEditText login = (EditText) activity.findViewById("+ login.substring(a,aa) +");\n\t\tEditText password = (EditText) activity.findViewById("+ password.substring(b,bb) +");\n\t\tButton button = (Button) activity.findViewById("+ button.substring(c,cc) +");\n\t\tlogin.setText(\"invalid@email\"); \n\t\t password.setText(\"invalidpassword\");\n\t\tbutton.performClick();\n\t\tShadowApplication application = shadowOf(RuntimeEnvironment.application);\n\t\tassertThat(\"Next activity should not started\", application.getNextStartedActivity(), is(nullValue()));\n\t\tassertThat(\"Show error for Email field \", login.getError(), is(CoreMatchers.notNullValue()));\n\t\tassertThat(\"Show error for Password field \", password.getError(), is(CoreMatchers.notNullValue()));\n\t}");

    }
}