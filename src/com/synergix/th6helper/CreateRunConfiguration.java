package com.synergix.th6helper;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

public class CreateRunConfiguration extends AnAction {

	@Override
	public void actionPerformed(AnActionEvent e) {
		System.out.println("Created all run configuration");
	}
}
