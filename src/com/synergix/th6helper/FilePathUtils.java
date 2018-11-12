package com.synergix.th6helper;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class FilePathUtils {
	public static final String SCHEDULED_JOB_FOLDER = "TH6/src/main/java/synergix/th6/business/action/scheduledjob";
	public static final String ACCESS_TH5_SERVICE = "TH6/src/main/java/synergix/th6/business/action/service/ac/AccessTH5Service.java";
	public static final String TH6_POM_DOT_XML = "TH6/pom.xml";
	public static final String EM_LOCATOR = "TH6/src/main/java/synergix/th6/business/util/persistence/EmLocator.java";

	static Path getEmLocatorPath(String projectPath) {
		return Paths.get(projectPath + "/" + EM_LOCATOR);
	}

	static List<Path> getFilesToBeDisabled(String projectPath) {
		List<Path> fileList = new ArrayList<>();
		String scheduledJobFolderAbsolutePath = projectPath + "/" + SCHEDULED_JOB_FOLDER;

		Path scheduledJobFolderPath = Paths.get(scheduledJobFolderAbsolutePath);
		try {
			Files.walkFileTree(scheduledJobFolderPath, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) {
					if (Files.isRegularFile(file)) {
						fileList.add(file);
					}
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			System.out.println("Not a valid TH6 project");
		}
		return fileList;
	}
}
