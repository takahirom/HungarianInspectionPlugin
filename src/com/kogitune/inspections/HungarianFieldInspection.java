package com.kogitune.inspections;

import com.intellij.codeInsight.daemon.impl.quickfix.RenameElementFix;
import com.intellij.codeInspection.BaseJavaLocalInspectionTool;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.JavaElementVisitor;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiModifierList;
import com.kogitune.inspections.toolbar.ToggleHungarianEnable;
import org.jetbrains.annotations.NotNull;

public class HungarianFieldInspection extends BaseJavaLocalInspectionTool {
    public HungarianFieldInspection() {
        super();
    }

    @NotNull
    @Override
    public PsiElementVisitor buildVisitor(@NotNull final ProblemsHolder holder, boolean isOnTheFly) {
        return new JavaElementVisitor() {
            @Override
            public void visitField(PsiField field) {
                super.visitField(field);
                if (ToggleHungarianEnable.isHangurianEnabled(holder.getProject())) {
                    alertIfNotHungarian(holder, field);
                } else {
                    alertIfHungarian(holder, field);
                }

            }
        };
    }

    private void alertIfHungarian(ProblemsHolder holder, PsiField field) {
        final String fieldName = field.getName();
        if (fieldName.matches("[ms][A-Z].*")) {
            String newFiledName = fieldName.substring(1, 2).toLowerCase() + fieldName.substring(2, fieldName.length());
            holder.registerProblem(field, "Hungary field name", new RenameElementFix(field, newFiledName));
        }
    }

    private void alertIfNotHungarian(ProblemsHolder holder, PsiField field) {
        final String fieldName = field.getName();
        if (fieldName.matches("[a-z][a-z].*")) {
            String prefix = "m";
            final PsiModifierList modifierList = field.getModifierList();
            if (modifierList != null && modifierList.hasModifierProperty("static")) {
                prefix = "s";
            }
            String newFiledName = prefix + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
            holder.registerProblem(field, "Not Hungary field name", new RenameElementFix(field, newFiledName));
        }
    }
}
