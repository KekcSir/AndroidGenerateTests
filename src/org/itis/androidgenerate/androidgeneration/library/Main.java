package org.itis.androidgenerate.androidgeneration.library;

import org.itis.androidgenerate.androidgeneration.testcases.Buttons.ButtonCheck;
import org.itis.androidgenerate.androidgeneration.testcases.Buttons.LoginAndPasswordCheck;

import java.io.FileNotFoundException;

public class Main {

    Model model = new Model();
    ReaderWriter readerWriter = new ReaderWriter();
    ButtonCheck buttonCheck = new ButtonCheck(readerWriter, model);
    LoginAndPasswordCheck loginAndPasswordCheck = new LoginAndPasswordCheck(readerWriter, model);

    public Main() {

    }

    public void mainMethod(String nameActivity, String path, int q) throws FileNotFoundException {
        model.setActivityNumber(q);
        model.setProjectPath(path);
        model.setActivityName(nameActivity.substring(nameActivity.lastIndexOf("\\") + 1, nameActivity.length() - 5));
        readerWriter.begin(model);
        readerWriter.isBegin(nameActivity);
        loginAndPasswordCheck.loginAndPasswordFind();
        buttonCheck.buttonFind();
        readerWriter.end(model);

    }

}
