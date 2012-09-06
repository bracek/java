package sk.mka.phd.matlab.generator;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import sk.mka.phd.matlab.cons.Const;

import sk.mka.phd.matlab.file.FileMkaImpl;
import sk.mka.phd.matlab.file.PhonemeFileReader;

/**
 *
 * @author katrami
 */
public abstract class AbstractGenerator implements Generator {

    private int start = 0;
    private static String[] divisionArray;
    private static Logger log = Logger.getLogger(AbstractGenerator.class);
    static final String CONFIG_FILENAME = "target/classes/config-logic.properties";
    protected String[] excludePhonemeList;
    /**
     * set 1 if you want to load phoneme which are voiced otherwise 0 (unvoiced) will be loadad from voicedVoicelessList of
     * phoneme
     */
    private int wantVoiced;

    public ArrayList getListOfExpectedIds(final File aFile) {
        ArrayList arrayList = new ArrayList();
        try {
            //use buffering, reading one line at a time
            //FileReader always assumes default encoding is OK!
            BufferedReader input = new BufferedReader(new FileReader(aFile));
            try {
                String line = null;

                while ((line = input.readLine()) != null) {
                    String[] res = line.split("\t");
                    if (res[2].equals(String.valueOf(getWantVoiced()))) {
                        arrayList.add(res[0]);
                    }
                }
            } finally {
                input.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return arrayList;
    }

    @Override
    public void init() {
        Writer output = null;
        try {
            output = openFileForWriting();
            int j = 1;
            File aFile = new File(Const.VOICED_UNVOICED_LIST);
            ArrayList unvoicedListOfIds = getListOfExpectedIds(aFile);
            String voicedId = null;
            for (int idx = start; idx < Const.NUMBER_OF_PHONEME; idx++) {
                for (int k = 0; k < unvoicedListOfIds.size(); k++) {
                    voicedId = (String) unvoicedListOfIds.get(k);
                    if (Integer.valueOf(voicedId) == idx && !isExcluded(idx)) {
                        final String x = "nnIdsForFutherTraining(" + j + ")=" + idx + ";";
                        output.write(x + "\n");
                        System.out.println(x);
                        j++;
                        continue;
                    }
                }
            }
            System.out.println("Your file: " + Const.PHONEME_FILENAME + " has been written successfuly");
            output.close();
        } catch (IOException ex) {
            log.debug(ex);
        } finally {
            try {
                output.close();
            } catch (IOException ex) {
                log.debug(ex);
            }
        }
    }

    protected Writer openFileForWriting() {
       int i = 0;
        Writer output;
        try {
            FileInputStream fstream = new FileInputStream(CONFIG_FILENAME);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            while ((strLine = br.readLine()) != null) {
                System.out.println(strLine);
                divisionArray = strLine.split("=");
                String key = divisionArray[0];
                if (key.trim().equals("start.id")) {
                    log.debug(key);
                    String value = divisionArray[1];
                    String cleanValue = value.trim();
                    this.start = (int) Integer.valueOf(cleanValue);
                    log.debug(this.start);
                }
                i++;
            }
            in.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        log.debug("i:" + i);
        //            final FileWriterMka fileWriter = new FileWriterMkaImpl(Const.PHONEME_FILENAME);
        output = new FileMkaImpl().openFileForWriting(Const.PHONEME_FILENAME);
        return output;
    }

    /**
     *
     * @param id
     * @return
     */
    private boolean isExcluded(final int id) {
        boolean isExcluded = false;
        for (int l = 0; l < excludePhonemeList.length; l++) {
            String exlucedId = excludePhonemeList[l];
            if (id == Integer.valueOf(exlucedId)) {
                isExcluded = true;
                continue;
            }
        }
        return isExcluded;
    }

    /**
     * 
     * @param excludedIdListFilename
     */
    protected void loadSetExcludedIdList(final String excludedIdListFilename) {
        File excludeFile = new File(excludedIdListFilename);
        PhonemeFileReader phonemeFileReader = new PhonemeFileReader();
        String excludeList = phonemeFileReader.getFileContent(excludeFile);
        excludePhonemeList = excludeList.split("\n");
    }

    /**
     * @return the wantVoiced
     */
    public int getWantVoiced() {
        return wantVoiced;
    }

    /**
     * @param wantVoiced the wantVoiced to set
     */
    public void setWantVoiced(int wantVoiced) {
        this.wantVoiced = wantVoiced;
    }
}
