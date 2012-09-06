package sk.mka.phd.timit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

import sk.mka.phd.files.reader.IFileListing;
import sk.mka.phd.timit.maven.TimitParams;
import sk.mka.phd.timit.reduction.PhonemaGroup;
import sk.mka.phd.timit.reduction.PhonemeReductionGroups;

/**
 *
 * @author bracek
 */
public class Simplify implements ISimplify {

    private static Logger log = Logger.getLogger(Simplify.class);
    private final String inputDirectory;
    private IFileListing fileListing;
    private final PhonemeReductionGroups phonemeReductionGroups;
    private boolean printDataFlag;

    Simplify(IFileListing fileListing, final String inputDirectory) {
        this.fileListing = fileListing;
        this.inputDirectory = inputDirectory;
        final String finalPath = TimitParams.getInputDirectory(TimitParams.TimitValues.phonemeReduction);
        phonemeReductionGroups = new PhonemeReductionGroups(finalPath, " ");
    }

    private void loadData(final String filename, final String separator) throws IOException {
        String outputActivated;
        PhonemaGroup activatedPhonemaGroup = null;
        StringBuffer stringBuffer = new StringBuffer();

        try {
            // Open the file that is the first
            // command line parameter
            final FileInputStream fstream = new FileInputStream(filename);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line


            while ((strLine = br.readLine()) != null) {
                log.debug(strLine);
                if (!strLine.equals("")) {
                    int index = 0;

                    String[] singleLineArray = loadLine(strLine, separator);

                    for (int i = 0; i < singleLineArray.length; i++) {
                        outputActivated = singleLineArray[i];

                        //create new group which represents new more easily distribution
                        PhonemaGroup phonemaGroup = phonemeReductionGroups.getGroupByPhonema(i);

                        if (phonemaGroup != null) {
                            //update existing group
                            if (phonemaGroup.getIndex() == -1) { //group has index adjusted
                                phonemaGroup.setIndex(index);
                                index++;
                            }
                            if (Integer.valueOf(outputActivated).equals(1)) {
                                phonemaGroup.setActivatedDesiredOutput(true); //activate output
                                activatedPhonemaGroup = phonemaGroup;
                            }

                        } else { //new group
                            ArrayList<Object> col = new ArrayList<Object>();
                            col.add(i);
                            phonemaGroup = new PhonemaGroup(col);
                            phonemaGroup.setIndex(index);
                            if (Integer.valueOf(outputActivated).equals(1)) {
                                phonemaGroup.setActivatedDesiredOutput(true);
                                activatedPhonemaGroup = phonemaGroup;
                            }
                            phonemeReductionGroups.add(phonemaGroup);
                            index++;
                        }
                    }

                    if (!printDataFlag) {
                        phonemeReductionGroups.printData();
                    }
                    printDataFlag = true;

                    final ArrayList<PhonemaGroup> groups = phonemeReductionGroups.getPhonemesGroups();
                    for (int i = 0; i < groups.size(); i++) {
                        if (i == activatedPhonemaGroup.getIndex()) {
                            stringBuffer.append(1);
                        } else {
                            stringBuffer.append(0);
                        }
                        if (i < groups.size() - 1) {
                            stringBuffer.append(",");
                        }
                    }

                    stringBuffer.append("\n");
                    activatedPhonemaGroup.setActivatedDesiredOutput(false); //reset phonema activated flag to zero (each line can have only one winner)
                }
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {
            //Catch exception if any
            log.fatal(e.getMessage());

        }
        //open the same file
        BufferedWriter out = null;
        try {
            // Create file
            final FileWriter ofstream = new FileWriter(filename);
            out = new BufferedWriter(ofstream);
            out.write(stringBuffer.toString());
            out.close();
        } catch (Exception e) {//Catch exception if any
            log.fatal(e.getMessage());
        }
    }

    @Override
    public void parseFiles() {
        List<File> filesIn = fileListing.getFiles(inputDirectory);
        for (File file : filesIn) {
            parseFile(file);
        }
    }

    private void parseFile(final File file) {
        try {
            loadData(file.getAbsolutePath(), ",");
        } catch (IOException ex) {
            log.debug(ex);
        }
    }

    /**
     * Return line as substring array
     * @param strLine
     * @param separator
     * @return
     */
    private String[] loadLine(final String strLine, final String separator) {
        return strLine.split(separator);


    }
}
