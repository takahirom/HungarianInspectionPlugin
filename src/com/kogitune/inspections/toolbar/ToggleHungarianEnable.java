package com.kogitune.inspections.toolbar;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;

public class ToggleHungarianEnable extends AnAction {
    public static final String IS_HUNGARIAN_ENABLED = "IS_HUNGARIAN_ENABLED";

    @Override
    public void update(AnActionEvent e) {
        super.update(e);
        new ToggleIconService().toggleIcon(e.getPresentation(), isHangurianEnabled(e.getProject()));
    }

    public void actionPerformed(AnActionEvent e) {
        final Project project = e.getProject();
        PropertiesComponent properties = PropertiesComponent.getInstance(project);
        final boolean hangurianEnabled = isHangurianEnabled(project);
        properties.setValue(IS_HUNGARIAN_ENABLED, String.valueOf(!hangurianEnabled));

        new ToggleIconService().toggleIcon(e.getPresentation(), !hangurianEnabled);
    }

    public static boolean isHangurianEnabled(Project project) {
        PropertiesComponent properties = PropertiesComponent.getInstance(project);
        return properties.getBoolean(IS_HUNGARIAN_ENABLED, true);
    }


}
