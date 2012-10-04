import java.util.*;
import java.io.*;

/**
 * Recursive file listing under a specified directory.
 * 
 * @author javapractices.com
 * @author Miroslav Katrak
 * @author anonymous user
 */
public final class FileListing {

	private static final String COMMENT = "//";
	private static final String LEFT_BRACKET = "(";

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
		final List<File> files = FileListing.getFileListing(startingDirectory);

		// print out all file names, in the the order of File.compareTo()
		for (File file : files) {
			if (file.isFile()) {
				System.out.println(file);
				appendFinalToMethod(file);
			}
		}
	}

	private static void appendFinalToMethod(final File file) {
		BufferedReader reader = null;
		final StringBuffer stringBuffer = new StringBuffer();
		final StringBuffer paramsTemporaryBuffer = new StringBuffer();

		try {
			reader = new BufferedReader(new FileReader(file));
			String line = null;

			// repeat until all lines is read

			while ((line = reader.readLine()) != null) {

				if (line.contains(COMMENT)) { // skip commented code

					int indexOfSlash = line.indexOf(COMMENT);

					if (line.contains(LEFT_BRACKET)) {
						int indexOfBeginingBracket = line.indexOf(LEFT_BRACKET);
						if (indexOfSlash < indexOfBeginingBracket) {
							appendLine(stringBuffer, line);
						} else {
							modify(stringBuffer, paramsTemporaryBuffer, line);
						}
					} else
						appendLine(stringBuffer, line);
				} else
					modify(stringBuffer, paramsTemporaryBuffer, line);

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

		String absolutePath = file.getAbsolutePath();
		final int lastIndexOfDot = absolutePath.lastIndexOf(".");
		absolutePath = absolutePath.substring(0, lastIndexOfDot);
		absolutePath += ".java";
		writeToFile(absolutePath, stringBuffer.toString());
		// writeToFile(absolutePath, stringBuffer.toString());
	}

	private static void modify(StringBuffer stringBuffer,
			StringBuffer paramsTemporaryBuffer, String line) {
		if (line.contains("private") || line.contains("public")
				|| line.contains("protected")) {
			if (!line.contains("new")) {
				// first check if has no parameter

				if (line.contains("(")) {

					// System.out.println("single_line:" + line);

					int indexOfStart = line.indexOf("(");
					final String beg = line.substring(0, indexOfStart + 1);

					if (line.contains(")")) {
						// start and end on the same line
						// System.out.println("line:" + line);

						int endOfStart = line.indexOf(")");
						final String middle = line.substring(indexOfStart + 1,
								endOfStart);

						// System.out.println("beg: " + beg);
						final String end = line.substring(endOfStart);
						// System.out.println("end: " + end);

						// System.out.println("\tmiddle: " +
						// middle);
						boolean isWhitespace = middle.matches("^\\s*$");
						if (!isWhitespace) {
							appendFinalToParams(stringBuffer, beg, middle, end);
						} else {
							appendLine(stringBuffer, line);
						}
					} else { // no contains ")"
						// appendLine(stringBuffer, line);
						// System.out.println("\tparamsTemporaryBuffer: "
						// + paramsTemporaryBuffer);
						paramsTemporaryBuffer.append(line);
					}
				} else {
					appendLine(stringBuffer, line);
				}
			} else
				appendLine(stringBuffer, line);
		} else {
			if (paramsTemporaryBuffer.length() > 0) {

				if (line.contains(")")) {
					paramsTemporaryBuffer.append(line);
					final int indexOfStartBracket = paramsTemporaryBuffer
							.indexOf("(");
					final int indexOfEndOfStart = paramsTemporaryBuffer
							.indexOf(")");

					final String beggining = paramsTemporaryBuffer.substring(0,
							indexOfStartBracket + 1);
					final String middle = paramsTemporaryBuffer.substring(
							indexOfStartBracket + 1, indexOfEndOfStart);
					final String end = paramsTemporaryBuffer
							.substring(indexOfEndOfStart);

					final StringBuffer tempStringBuffer = new StringBuffer();
					appendFinalToParams(tempStringBuffer, beggining, middle,
							end);
					stringBuffer.append(tempStringBuffer.toString());
					paramsTemporaryBuffer.delete(0,
							paramsTemporaryBuffer.length());

				} else
					paramsTemporaryBuffer.append(line);

			} else
				appendLine(stringBuffer, line);
		}
	}

	private static void appendFinalToParams(StringBuffer stringBuffer,
			final String beg, final String middle, final String end) {
		final String[] split = middle.split(",");
		for (int i = 0; i < split.length; i++) {
			if (i == 0) {
				stringBuffer.append(beg);
			}

			if (!split[i].contains("final"))
				stringBuffer.append("final ");

			stringBuffer.append(split[i]);
			if (i < split.length - 1)
				stringBuffer.append(",");

			if (i == split.length - 1) {
				stringBuffer.append(end);
			}

			stringBuffer.append("\n");
			// System.out.println("stringbuffer: " + stringBuffer.toString());
		}
	}

	private static void appendLine(StringBuffer stringBuffer, String line) {
		stringBuffer.append(line);
		stringBuffer.append("\n");
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
