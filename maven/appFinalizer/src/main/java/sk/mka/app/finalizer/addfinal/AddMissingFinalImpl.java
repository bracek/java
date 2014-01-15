package sk.mka.app.finalizer.addfinal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import sk.mka.app.finalizer.AbstractAction;
import sk.mka.app.finalizer.IAction;
import sk.mka.app.finalizer.Utils;

public final class AddMissingFinalImpl extends AbstractAction implements
		IAction {

	public void parseFile(final File file) {
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

				reader = new BufferedReader(new FileReader(file));
				String line = null;

				while ((line = reader.readLine()) != null) {

					if (line.contains(Utils.COMMENT)) { // skip commented code

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

	// @Override
	// protected void modify(StringBuffer stringBuffer,
	// StringBuffer paramsTemporaryBuffer, String line) {
	//
	// }

	@Override
	protected void modify(StringBuffer stringBuffer,
			StringBuffer paramsTemporaryBuffer, String line) {
		if (line.contains(Utils.PRIVATE) || line.contains(Utils.PUBLIC)
				|| line.contains("static") || line.contains(Utils.PROTECTED)) {
			if (!line.contains(Utils.NEW)) {

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

}
