package com.synergix.th6helper;

import java.io.IOException;

public class CommandLineUtils {

	public static void executeCommand(String command) {
		ProcessBuilder processBuilder = new ProcessBuilder(command);
		try {
			System.out.println(command);
			Process process = Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String buildAddToIgnoreOnCommitCommand(String absoluteFilePath) {
		return "svn changelist ignore-on-commit " + "\"" + absoluteFilePath + "\"";
	}

	public static void ignoreFile(String filePath) {
		executeCommand(buildAddToIgnoreOnCommitCommand(filePath));
	}
}
