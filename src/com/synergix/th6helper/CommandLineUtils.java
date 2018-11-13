package com.synergix.th6helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandLineUtils {

	public static void executeCommand(String command) {
		String s = null;
		try {
			System.out.println("\n\n" + command);
			Process p = Runtime.getRuntime().exec(command);
			BufferedReader stdInput = new BufferedReader(new
					InputStreamReader(p.getInputStream()));

			BufferedReader stdError = new BufferedReader(new
					InputStreamReader(p.getErrorStream()));

			// read the output from the command
			while ((s = stdInput.readLine()) != null) {
				System.out.println("Output: " + s);
			}

			// read any errors from the attempted command
			while ((s = stdError.readLine()) != null) {
				System.out.println("Error: " + s);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String buildAddToIgnoreOnCommitCommand(String absoluteFilePath) {
		return "svn changelist ignore-on-commit "  + "\"" + absoluteFilePath + "\"";
	}

	public static void ignoreFile(String filePath) {
		executeCommand(buildAddToIgnoreOnCommitCommand(filePath));
	}
}
