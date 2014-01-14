package sk.mka.app.finalizer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Recursive file listing under a specified directory.
 * 
 * @author javapractices.com
 * @author Miroslav Katrak
 * @author anonymous user
 */
public final class RemoveUnusedModifierFromInterface {

	/**
	 * Demonstrate use.
	 * 
	 * @param aArgs
	 *            - <tt>aArgs[0]</tt> is the full name of an existing directory
	 *            that can be read.
	 */
	public static void main(String... aArgs) throws FileNotFoundException {
		final File startingDirectory = new File(aArgs[0]);
		// File startingDirectory = new File(".java");
		final List<File> files = RemoveUnusedModifierFromInterface
				.getFileListing(startingDirectory);

		// print out all file names, in the the order of File.compareTo()
		for (File file : files) {
			if (file.isFile()) {
				doFileModification(file);
			}
		}
	}

	private static void doFileModification(final File file) {
		BufferedReader reader = null;
		final StringBuffer stringBuffer = new StringBuffer();
		final StringBuffer paramsTemporaryBuffer = new StringBuffer();
		boolean doModification = false;

		try {

			String extension = null;
			String fileName = file.getName();
			int i = fileName.lastIndexOf('.');
			if (i > 0) {
				extension = fileName.substring(i + 1);
			}
			if (extension != null && extension.equals(Utils.JAVA)) {
				System.out.println(file);
				doModification = true;

				boolean isInterface = false;

				Scanner scanner = null;
				try {
					scanner = new Scanner(file);
					while (scanner.hasNextLine()) {
						String line = scanner.nextLine();
						if (!line.contains(Utils.COMMENT)
								|| !line.startsWith("/*")) {
							if (line.contains("interface")
									|| line.contains("public interface")) {
								if (line.contains("{")) {
									isInterface = true;
									break;
								}
							}
						}
					}
				} finally {
					if (scanner != null)
						scanner.close();
				}

				if (!isInterface)
					return;
				else {
					reader = new BufferedReader(new FileReader(file));
					String line = null;

					// repeat until all lines is read
					while ((line = reader.readLine()) != null) {
						if (line.contains(Utils.COMMENT)) { // skip commented
															// code

							int indexOfSlash = line.indexOf(Utils.COMMENT);

							if (line.contains(Utils.LEFT_BRACKET)) {
								int indexOfBeginingBracket = line
										.indexOf(Utils.LEFT_BRACKET);
								if (indexOfSlash < indexOfBeginingBracket) {
									appendLine(stringBuffer, line);
								} else {
									modify(stringBuffer, paramsTemporaryBuffer,
											line);
								}
							} else
								appendLine(stringBuffer, line);
						} else
							modify(stringBuffer, paramsTemporaryBuffer, line);
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (doModification) {
			String absolutePath = file.getAbsolutePath();
			final int lastIndexOfDot = absolutePath.lastIndexOf(".");
			absolutePath = absolutePath.substring(0, lastIndexOfDot);
			absolutePath += ".java";
			writeToFile(absolutePath, stringBuffer.toString());
		}
	}

	private static void modify(StringBuffer stringBuffer,
			StringBuffer paramsTemporaryBuffer, String line) {

		if (line.contains("interface")) {
			paramsTemporaryBuffer.append(line);
			stringBuffer.append(line);
			stringBuffer.append(Utils.NEWLINE);
		} else {

			StringBuffer tempBuffer = new StringBuffer();
			String removedModfierLine = line.replace("public", "");
			tempBuffer.append(removedModfierLine);
			tempBuffer.append(Utils.NEWLINE);
			stringBuffer.append(tempBuffer);
		}

	}

	private static void appendLine(StringBuffer stringBuffer, String line) {
		stringBuffer.append(line);
		stringBuffer.append(Utils.NEWLINE);
	}

	private static void writeToFile(final String filename, final String output) {
		try {
			final BufferedWriter out = new BufferedWriter(new FileWriter(
					filename));
			final String outText = output.toString();
			out.write(outText);
			out.close();
		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Recursively walk a directory tree and return a List of all Files found;
	 * the List is sorted using File.compareTo().
	 * 
	 * @param aStartingDir
	 *            is a valid directory, which can be read.
	 */
	static public List<File> getFileListing(File aStartingDir)
			throws FileNotFoundException {
		validateDirectory(aStartingDir);
		List<File> result = getFileListingNoSort(aStartingDir);
		Collections.sort(result);
		return result;
	}

	// PRIVATE //
	static private List<File> getFileListingNoSort(File aStartingDir)
			throws FileNotFoundException {
		List<File> result = new ArrayList<File>();
		File[] filesAndDirs = aStartingDir.listFiles();
		List<File> filesDirs = Arrays.asList(filesAndDirs);
		for (File file : filesDirs) {
			result.add(file); // always add, even if directory
			if (!file.isFile()) {
				// must be a directory
				// recursive call!
				List<File> deeperList = getFileListingNoSort(file);
				result.addAll(deeperList);
			}
		}
		return result;
	}

	/**
	 * Directory is valid if it exists, does not represent a file, and can be
	 * read.
	 */
	static private void validateDirectory(File aDirectory)
			throws FileNotFoundException {
		if (aDirectory == null) {
			throw new IllegalArgumentException("Directory should not be null.");
		}
		if (!aDirectory.exists()) {
			throw new FileNotFoundException("Directory does not exist: "
					+ aDirectory);
		}
		if (!aDirectory.isDirectory()) {
			throw new IllegalArgumentException("Is not a directory: "
					+ aDirectory);
		}
		if (!aDirectory.canRead()) {
			throw new IllegalArgumentException("Directory cannot be read: "
					+ aDirectory);
		}
	}
}
