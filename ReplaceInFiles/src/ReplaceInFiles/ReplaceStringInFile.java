package ReplaceInFiles;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceStringInFile {

	public static void main(String[] args) throws IOException {

		String delimiter = args[0];
		String fileToReadPath = args[1];
		String fileToWritePath = args[2];

		File fileToRead = new File(fileToReadPath);
		Scanner scannerToRead = new Scanner(fileToRead);

		FileWriter fileToWrite = new FileWriter(fileToWritePath);

		Pattern pattern = Pattern.compile(delimiter);

		loopLines(scannerToRead, fileToWrite, pattern);

		File fileToCountLines = new File(fileToReadPath);
		Scanner scannerToCountLines = new Scanner(fileToCountLines);

		countLines(scannerToCountLines, fileToWrite);

		fileToWrite.close();
		scannerToRead.close();
		scannerToCountLines.close();
	}

	private static void loopLines(Scanner scannerToRead, FileWriter fileToWrite, Pattern pattern) throws IOException {

		int countMatches = 0;
		boolean splitString = true;

		while (scannerToRead.hasNext()) {
			
			String currentSection = scannerToRead.nextLine();

			if (splitString) {
				Matcher matcher = pattern.matcher(currentSection);
				boolean matchFound = matcher.find();
				
				fileToWrite.append(currentSection);
				System.out.println(currentSection);

				if (matchFound) {
					splitString = false;
				}

			} else {
				fileToWrite.append(currentSection);
				fileToWrite.append("\n");
			}

			countMatches++;
		}

		fileToWrite.append("\n\n>>>>>>>>>>>>>>><<<<<<<<<<<<<<<");
		fileToWrite.append("\n\nTotal Matches: " + String.valueOf(countMatches));
	}

	private static void countLines(Scanner scannerToCountLines, FileWriter fileToWrite) throws IOException {

		int countLines = 0;

		while (scannerToCountLines.hasNextLine()) {

			scannerToCountLines.nextLine();

			countLines++;
		}

		fileToWrite.append("\n\nTotal Lines: " + String.valueOf(countLines));
		fileToWrite.append("\n\n>>>>>>>>>>>>>>><<<<<<<<<<<<<<<");
	}

}
