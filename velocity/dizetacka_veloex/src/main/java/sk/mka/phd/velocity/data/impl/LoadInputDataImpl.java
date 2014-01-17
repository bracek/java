package sk.mka.phd.velocity.data.impl;

import sk.mka.phd.velocity.data.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import sk.mka.phd.matlab.cons.Const;
import sk.mka.phd.matlab.file.FileMkaImpl;
import sk.mka.phd.tools.math.DecimalFormatMka;

/**
 *
 * @author bracek
 */
public class LoadInputDataImpl extends AbstractLoadData implements LoadData, PhonemeLoadData {

    private double generalAvarage = 0;
    private double avarageClassA = 0;
    private double avarageClassB = 0;
    private final List<String> phonemaArrayList = new ArrayList<String>();
    private final List<Integer> nnId = new ArrayList<Integer>();
    private List<String> generalSuccess = new ArrayList<String>();
    private List<Double> resultAvarage = new ArrayList<Double>();
    private static Logger log = Logger.getLogger(LoadInputDataImpl.class);

    @Override
    public String[][] loadData(final String filename) throws IOException {
        String[] subStr;
        try {
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream(filename);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            rows = 0;
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                log.info(strLine);
                if (!strLine.equals("")) {
                    subStr = strLine.split("\t");
                    cols = subStr.length;
                    for (int i = 0; i < subStr.length; i++) {
                        numbersList.add(subStr[i]);
                        if (i == 0) { //getting neuron id
                            int index = Integer.valueOf(subStr[i]);
                            nnId.add(index);
                        }
                    }
                }
                if (!strLine.equals("")) {
                    rows++;
                }
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {
            //Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        log.info("numbersList = " + numbersList);
        String[][] numbers = new String[getRows()][getCols()];
        int row = 0;
        int col = 0;

        final Writer successWriter = new FileMkaImpl().openFileForWriting(Const.GENERAL_OUTPUT.OUTPUT_GENERAL_SUCCESS);
        final Writer writerA = new FileMkaImpl().openFileForWriting(Const.GENERAL_OUTPUT.OUTPUT_SUCCESS_CLASS_A);
        final Writer writerB = new FileMkaImpl().openFileForWriting(Const.GENERAL_OUTPUT.OUTPUT_SUCCESS_CLASS_B);
        Object x;
        for (int i = 0; i < numbersList.size(); i++) {
            x = numbersList.get(i);
            if (i != 0) {
                if ((i % getCols()) == 0) {
                    row++;
                    col = 0;
                }
            }
            numbers[row][col] = (String) x;
            final Double avaragaValue = Double.valueOf(x.toString());
            if (col == 0) {   //save nn id
                successWriter.write(x.toString());
                writerA.write(x.toString());
                writerB.write(x.toString());
            } else if (col == 1) { //save general success
                successWriter.write("\t" + x + "\n");
                generalAvarage += avaragaValue;
            } else if (col == 2) {
                avarageClassA += avaragaValue;
                writerA.write("\t" + x + "\n");
            } else if (col == 3) {
                avarageClassB += avaragaValue;
                writerB.write("\t" + x + "\n");
            }
            col++;
        }
        successWriter.close();
        writerA.close();
        writerB.close();

        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                log.debug("numbers[" + i + "][" + j + "]:=" + numbers[i][j]);
            }
        }
        return numbers;
    }

    @Override
    public List loadPhonemaList(final String filename) {
        String[] subStr;
        try {
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream(filename);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            rows = 0;
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                log.debug("phonema: " + strLine);
                if (!strLine.equals("")) {
                    subStr = strLine.split("\t");
                    cols = subStr.length;
                    for (int i = 0; i < subStr.length; i++) {
                        phonemaArrayList.add(subStr[i]);
                    }
                }
            }
            //Close the input stream
            in.close();
        } catch (Exception e) {
            log.fatal("FATAL" + e.getMessage());
        }
        log.info("numbersList = " + numbersList);
        return phonemaArrayList;
    }

    /**
     * @{@inheritDoc}
     */
    @Override
    public List rateAvarage() {

        double result = 0;
        if (this.generalAvarage == 0 || this.avarageClassA == 0 || this.avarageClassB == 0 || this.getRows() == 0) {
            try {
                throw new Exception("! Error, function: rateAvarage() is calling too early");
            } catch (Exception ex) {
                log.debug(ex);
            }
        } else {

            //general avarage (class A + class B/ 2)
            log.debug("rows:" + getRows());
            log.debug("generateAvarage:" + this.generalAvarage);
            result = (this.generalAvarage / getRows());
            result = DecimalFormatMka.doFormat(DecimalFormatMka.TWO_PATTERN_ACCURACY, result);
            log.debug("result:" + result);

            resultAvarage.add(result);
            //avarage class A
            log.debug("avarageClassA:" + this.avarageClassA);
            result = (this.avarageClassA / getRows());
            log.debug("result:" + result);
            result = DecimalFormatMka.doFormat(DecimalFormatMka.TWO_PATTERN_ACCURACY, result);
            resultAvarage.add(result);
            //avarage class B
            log.debug("avarageClassB:" + this.avarageClassB);
            result = (this.avarageClassB / getRows());
            result = DecimalFormatMka.doFormat(DecimalFormatMka.TWO_PATTERN_ACCURACY, result);
            log.debug("result:" + result);
            resultAvarage.add(result);

        }
        return resultAvarage;
    }

    @Override
    public List<Integer> getNnId() {
        return nnId;
    }

    @Override
    public List<String> getGeneralSuccess() {
        return generalSuccess;
    }
}
