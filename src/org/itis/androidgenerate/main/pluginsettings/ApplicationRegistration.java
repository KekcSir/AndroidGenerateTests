package org.itis.androidgenerate.main.pluginsettings;

import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.components.ApplicationComponent;
import org.jetbrains.annotations.NotNull;

public class ApplicationRegistration implements ApplicationComponent {

    @Override
    public void initComponent() {
        ActionManager am = ActionManager.getInstance();
        GeneratedAction ga = new GeneratedAction();

        DefaultActionGroup pvM = (DefaultActionGroup) am.getAction("ProjectViewPopupMenu");
        pvM.addSeparator();
        pvM.add(ga);

        DefaultActionGroup epM = (DefaultActionGroup) am.getAction("EditorPopupMenu");
        epM.addSeparator();
        epM.add(ga);

    }

    @NotNull
    public String getComponentName() {
        return "Run generate tests";
    }

}
