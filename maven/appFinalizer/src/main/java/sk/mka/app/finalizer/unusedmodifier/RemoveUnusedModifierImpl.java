package sk.mka.app.finalizer.unusedmodifier;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import sk.mka.app.finalizer.AbstractAction;
import sk.mka.app.finalizer.IAction;
import sk.mka.app.finalizer.Utils;

public class RemoveUnusedModifierImpl extends AbstractAction implements IAction {

	public void parseFile(final File file) {
		BufferedReader reader = null;
		final StringBuffer stringBuffer = new StringBuffer();
		final StringBuffer paramsTemporaryBuffer = new StringBuffer();
		boolean isInterface = false;

		try {

			String extension = null;
			String fileName = file.getName();
			int i = fileName.lastIndexOf('.');
			if (i > 0) {
				extension = fileName.substring(i + 1);
			}
			if (extension != null && extension.equals(Utils.JAVA)) {
				System.out.println(file);

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
								}
							}
						}

						if (isInterface) {
							if (line.contains(Utils.CLASS))
								isInterface = false;
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

		if (isInterface) {
			String absolutePath = file.getAbsolutePath();
			final int lastIndexOfDot = absolutePath.lastIndexOf(Utils.DOT);
			absolutePath = absolutePath.substring(0, lastIndexOfDot);
			absolutePath += ".java";
			writeToFile(absolutePath, stringBuffer.toString());
		}
	}
	
	protected void modify(StringBuffer stringBuffer, StringBuffer paramsTemporaryBuffer, String line) {
		
		if (line.contains("public interface")) {
			paramsTemporaryBuffer.append(line);
			stringBuffer.append(line);
			stringBuffer.append(Utils.NEWLINE);
		} else {
	
			String removedModfierLine = "";
			StringBuffer tempBuffer = new StringBuffer();
			if (line.contains(Utils.PUBLIC)) {
				removedModfierLine = line.replace(Utils.PUBLIC,
						Utils.EMPTY_STRING);
			} else if (line.contains(Utils.ABSTRACT)) {
				if (!line.contains(Utils.PACKAGE)) {
					removedModfierLine = line.replace(Utils.ABSTRACT,
							Utils.EMPTY_STRING);
				} else
					tempBuffer.append(line);
			} else {
				tempBuffer.append(line);
			}
	
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
}
