package sk.mka.app.finalizer.addfinal;

import sk.mka.app.finalizer.AbstractAction;
import sk.mka.app.finalizer.IAction;
import sk.mka.app.finalizer.Utils;

import java.io.*;

public final class AddMissingFinalImpl extends AbstractAction implements
        IAction {

    @Override
    public void parseFile(final File file) {
        BufferedReader reader = null;
        final StringBuffer stringBuffer = new StringBuffer();
        final StringBuffer paramsTemporaryBuffer = new StringBuffer();
        boolean doModification = false;

        try {

            String extension = null;
            String fileName = file.getName();
            int i = fileName.lastIndexOf(Utils.DOT);
            if (i > 0) {
                extension = fileName.substring(i + 1);
            }
            if (extension != null && extension.equals(Utils.JAVA)) {
                System.out.println(file);
                doModification = true;

                reader = new BufferedReader(new FileReader(file));
                String line;

                while ((line = reader.readLine()) != null) {

                    if (line.contains(Utils.COMMENT)) { // skip commented code

                        int indexOfSlash = line.indexOf(Utils.COMMENT);

                        if (line.contains(Utils.OPEN_PARENTHESIS_OPENING)) {
                            int indexOfBeginingBracket = line
                                    .indexOf(Utils.OPEN_PARENTHESIS_OPENING);
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

    @Override
    protected void modify(final StringBuffer stringBuffer, final StringBuffer paramsTemporaryBuffer, final String line) {
        if (line.contains(Utils.PRIVATE) || line.contains(Utils.PUBLIC) || line.contains(Utils.STATIC) || line.contains(Utils.PROTECTED)) {
            if (!line.contains(Utils.NEW) && !line.contains(".class") && !line.contains("getClass()")) {

                if (line.contains(Utils.OPEN_PARENTHESIS_OPENING)) {

                    int indexOfStart = line
                            .indexOf(Utils.OPEN_PARENTHESIS_OPENING);
                    final String beg = line.substring(0, indexOfStart + 1);

                    if (line.contains(Utils.OPEN_PARENTHESIS_CLOSING)) {
                        int endOfStart = line
                                .lastIndexOf(Utils.OPEN_PARENTHESIS_CLOSING) + 1;
                        final String middle = line.substring(indexOfStart + 1,
                                endOfStart);
                        final String end = line.substring(endOfStart);
                        boolean isWhitespace = middle.matches("^\\s*$");
                        if (!isWhitespace) {
                            appendFinalToParams(stringBuffer, beg, middle, end);
                        } else {
                            appendLine(stringBuffer, line);
                        }
                    } else {
                        stringBuffer.append(line);
                        stringBuffer.append(Utils.NEWLINE);
                    }
                } else {
                    appendLine(stringBuffer, line);
                }
            } else
                appendLine(stringBuffer, line);
        } else {
            if (paramsTemporaryBuffer.length() > 0) {

                if (line.contains(Utils.OPEN_PARENTHESIS_CLOSING)) {
                    paramsTemporaryBuffer.append(line);
                    final int indexOfStartBracket = paramsTemporaryBuffer
                            .indexOf(Utils.OPEN_PARENTHESIS_OPENING);
                    final int indexOfEndOfStart = paramsTemporaryBuffer
                            .lastIndexOf(Utils.OPEN_PARENTHESIS_CLOSING) + 1;

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

    private void appendFinalToParams(final StringBuffer stringBuffer,
                                     final String beg,
                                     final String middle,
                                     final String end) {

        final String[] split = middle.split(Utils.COMMA);

        boolean doModification = false;

        if (beg.contains(Utils.PUBLIC) || beg.contains(Utils.PRIVATE) || beg.contains(Utils.PROTECTED) && beg.contains(Utils.OPEN_PARENTHESIS_OPENING))
            doModification = true;
        if (beg.indexOf("INSERT") > 0)
            doModification = false;
        if (beg.indexOf("UPDATE") > 0)
            doModification = false;


        if (doModification) {
            for (int i = 0; i < split.length; i++) {
                if (i == 0) {
                    stringBuffer.append(beg);
                }

                if (!split[i].contains(Utils.FINAL)) {
                    if (!split[i].contains(Utils.REQUIRED))
                        if (!split[i].equals(Utils.OPEN_PARENTHESIS_CLOSING))
                            if (!split[i].contains(">"))

                                stringBuffer.append(Utils.FINAL + Utils.SPACE);
                }
                stringBuffer.append(split[i]);

                if (i < split.length - 1) {
                    stringBuffer.append(Utils.COMMA);
                }

                if (i == split.length - 1) {
                    stringBuffer.append(end);
                    stringBuffer.append(Utils.NEWLINE);
                }
            }
        } else {
            stringBuffer.append(beg);
            stringBuffer.append(middle);
            stringBuffer.append(end);
            stringBuffer.append(Utils.NEWLINE);

        }
    }


    private void appendLine(final StringBuffer stringBuffer, final String line) {
        stringBuffer.append(line);
        stringBuffer.append(Utils.NEWLINE);
    }

    private void writeToFile(final String filename,
                             final String output) {
        try {
            final BufferedWriter out = new BufferedWriter(new FileWriter(
                    filename));
            out.write(output);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
