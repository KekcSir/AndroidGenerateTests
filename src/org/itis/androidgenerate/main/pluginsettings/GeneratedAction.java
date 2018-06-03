package org.itis.androidgenerate.main.pluginsettings;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import org.itis.androidgenerate.main.separation.TestGeneration;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GeneratedAction extends AnAction {


    public GeneratedAction() {
        super("Run AutoGeneration", "Open GUI dialog to configure and start running EvoSuite to generate JUnit tests automatically",
                loadIcon());
    }

    private static Icon loadIcon() {
        try {
            Image image = ImageIO.read(GeneratedAction.class.getClassLoader().getResourceAsStream("autogen.png"));
            image = image.getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(image);

            return icon;
        } catch (IOException e) {
            e.printStackTrace(); //should not really happen
        }
        return null;
    }

    @Override
    public void actionPerformed(AnActionEvent e) {

        String title = "Auto Generated Plugin";
        TestGeneration testGeneration = new TestGeneration();
        Project project = e.getData(PlatformDataKeys.PROJECT);
        final AsyncGUINotifier notifier = IntelliJNotifier.getNotifier(project);
        Messages.showMessageDialog(project, "Начало работы плагина",
                title, Messages.getInformationIcon());
        String path = new File(project.getBaseDir().getCanonicalPath()).getAbsolutePath();
        try {
            testGeneration.generation(path);
            if (testGeneration.isNotEmpty()) {
                Messages.showMessageDialog(project, "No '.java' file or non-empty source folder was selected in a valid module",
                        title, Messages.getWarningIcon());
                return;
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Messages.showMessageDialog(project, "Тесты успешно созданы",
                title, Messages.getInformationIcon());
    }
}
