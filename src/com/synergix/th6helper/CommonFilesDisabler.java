package com.synergix.th6helper;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CommonFilesDisabler extends AnAction {

	@Override
	public void actionPerformed(AnActionEvent actionEvent) {
		String projectBasePath = actionEvent.getProject().getBasePath();
		this.disabledScheduledJob(projectBasePath);
		this.disableEmLocatorInitOnStartup(projectBasePath);
	}




	private void disableEmLocatorInitOnStartup(String projectBasePath) {
		Path absolutePathToEmLocator = FilePathUtils.getEmLocatorPath(projectBasePath);
		this.commentLineMatchingPatternInFile(absolutePathToEmLocator, "EmLocator.initOnStartup");
	}

	private void disabledScheduledJob(String projectPath) {
		for (Path absolutePath : FilePathUtils.getFilesToBeDisabled(projectPath)) {
			this.commentLineMatchingPatternInFile(absolutePath, "@Scheduled");
		}
	}

	private void commentLineMatchingPatternInFile(Path absoluteFilePath, String pattern) {
		List<String> lines = new ArrayList<>();
		String line = null;
		try {
			BufferedReader bufferedReader = Files.newBufferedReader(absoluteFilePath);
			boolean toBeIgnored = false;
			while ((line = bufferedReader.readLine()) != null) {
				if (line.trim().startsWith(pattern)) {
					line = "//" + line;
					toBeIgnored = true;
				}
				lines.add(line);
			}
			bufferedReader.close();

			BufferedWriter bufferedWriter = Files.newBufferedWriter(absoluteFilePath); //new BufferedWriter(fileWriter);
			for (String s : lines) {
				bufferedWriter.write(s );
				bufferedWriter.write(System.getProperty("line.separator"));
				bufferedWriter.flush();
			}
			bufferedWriter.close();
			if (toBeIgnored) {
				CommandLineUtils.ignoreFile(absoluteFilePath.toAbsolutePath().toString());
			}

		} catch (IOException e) {
			System.out.println("Not a valid TH6 project");
		}
	}
}
