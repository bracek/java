package sk.mka.app.finalizer.addfinal.notbyline;

import sk.mka.app.finalizer.AbstractAction;
import sk.mka.app.finalizer.IAction;
import sk.mka.app.finalizer.Utils;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: katrami
 * Date: 1/22/14
 * Time: 9:21 AM
 */
public class AddMissingFinalNew extends AbstractAction implements IAction {

    @Override
    public void parseFile(final File file) {

        String everything;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file.getAbsolutePath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        try {
            StringBuilder sb = new StringBuilder();
            if (br != null) {
                String line = br.readLine();

                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }

                everything = sb.toString();
                processFile(file, everything);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
        }
    }

    public void processFile(final File file, final String everything) {

        int finalKeywordAddedForFile = 0;
        final StringBuffer stringBuffer = new StringBuffer();
        final String[] fileContentByLine = everything.split(Utils.NEWLINE);

        for (int i = 0; i < fileContentByLine.length; i++) {

            final String currentLine = fileContentByLine[i];
            boolean doModification = false;

            if (currentLine.contains(Utils.PUBLIC) || currentLine.contains(Utils.PRIVATE) || currentLine.contains(Utils.PROTECTED) && currentLine.contains(Utils.OPEN_PARENTHESIS_OPENING))
                doModification = true;
            if (currentLine.indexOf(Utils.INSERT) > 0)
                doModification = false;
            if (currentLine.indexOf(Utils.UPDATE) > 0)
                doModification = false;
            if (currentLine.startsWith(Utils.COMMENT))
                doModification = false;
            if (currentLine.contains(Utils.GET_CLASS))
                doModification = false;
            if (currentLine.contains(Utils.DOT_CLASS))
                doModification = false;
            if (currentLine.contains(Utils.AT_TABLE))
                doModification = false;
            if (currentLine.contains(Utils.GET_INSTANCE))
                doModification = false;

            if (doModification) {

                final int openingsPostBracket = currentLine.indexOf(Utils.OPEN_PARENTHESIS_OPENING);
                final int closeBracketPos = currentLine.lastIndexOf(Utils.OPEN_PARENTHESIS_CLOSING);

                if (openingsPostBracket > 0) {
                    final String beginning = currentLine.substring(0, openingsPostBracket);
                    final String end = currentLine.substring(closeBracketPos + 1, currentLine.length());

                    final StringBuffer tempStringBuffer = new StringBuffer();
                    tempStringBuffer.append(beginning);
                    tempStringBuffer.append(Utils.OPEN_PARENTHESIS_OPENING);


                    if (closeBracketPos > 0) {
                        final String methodsArguments = currentLine.substring(openingsPostBracket + 1, closeBracketPos);

                        //check if methods has arguments
                        if (methodsArguments.trim().length() > 0) {
                            if (methodsArguments.contains(Utils.DOT)) {
                                doModification = false;
                            } else if (methodsArguments.contains(Utils.THIS)) {
                                doModification = false;
                            } else {
                                doModification = true;
                                final String fixedArguments = appendFinalToArguments(methodsArguments);

                                // check different = not replacing file with the same content - there is any change for file

                                if (!fixedArguments.equals(methodsArguments))
                                    finalKeywordAddedForFile++;

                                tempStringBuffer.append(fixedArguments);
                            }
                        } else
                            doModification = false;
                    } else
                        doModification = false;


                    if (doModification) {
                        tempStringBuffer.append(Utils.OPEN_PARENTHESIS_CLOSING);
                        tempStringBuffer.append(end);
                        tempStringBuffer.append(Utils.NEWLINE);
                        stringBuffer.append(tempStringBuffer);
                    }
                } else
                    doModification = false;
            }

            if (!doModification) {
                stringBuffer.append(currentLine);
                stringBuffer.append(Utils.NEWLINE);
            }
        }//end of for


//        System.out.println("out: " + stringBuffer);
        if (stringBuffer.length() > 0 && finalKeywordAddedForFile > 0) {
            String absolutePath = file.getAbsolutePath();
            final int lastIndexOfDot = absolutePath.lastIndexOf(".");
            absolutePath = absolutePath.substring(0, lastIndexOfDot);
            absolutePath += Utils.DOT_JAVA;
            writeToFile(absolutePath, stringBuffer.toString());
        }
    }


    private void writeToFile(final String filename, final String output) {
        try {
            final BufferedWriter out = new BufferedWriter(new FileWriter(
                    filename));
            out.write(output);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String appendFinalToArguments(final String methodsArguments) {

        final StringBuffer stringBuffer = new StringBuffer();
        final String[] split = methodsArguments.split(Utils.COMMA);
        for (int i = 0; i < split.length; i++) {

            if (!split[i].contains(Utils.FINAL)) {
                if (!split[i].contains(Utils.REQUIRED))
                    if (!split[i].equals(Utils.OPEN_PARENTHESIS_CLOSING))
                        if (!split[i].contains(">")) {
                            stringBuffer.append(Utils.FINAL + Utils.SPACE);
                            numberOfAddedFinalKeyword++;
                        }
            }
            stringBuffer.append(split[i]);

            if (i < split.length - 1) {
                stringBuffer.append(Utils.COMMA);
            }
        }

        return stringBuffer.toString();
    }
}
