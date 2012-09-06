package sk.mka.phd.velocity.data.phoneme;

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
import sk.mka.phd.matlab.cons.SeparatorConst;
import sk.mka.phd.matlab.file.FileMkaImpl;
import sk.mka.phd.tools.math.DecimalFormatMka;
import sk.mka.phd.velocity.data.phoneme.AbstractPhonemeLoadData;

/**
 *
 * @author katrami
 */
public class PhonemeLoadDataImpl extends AbstractPhonemeLoadData implements PhonemeLoadData {

    private static Logger log = Logger.getLogger(PhonemeLoadDataImpl.class);
    private double generalAvarage = 0;
    private double avarageClassAA = 0;
    private double avarageClassAB = 0;
    private double avarageClassBA = 0;
    private double avarageClassBB = 0;
    private final List<Integer> nnId = new ArrayList<Integer>();
    private List<Double> resultAvarage = new ArrayList<Double>();

    /**
     * @{@inheritDoc}
     */
    @Override
    public List rateAvarage() {

        double result = 0;
        if (this.generalAvarage == 0 || this.avarageClassAA == 0 || this.avarageClassBB == 0 || this.getRows() == 0) {
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
            log.debug("avarageClassA:" + this.avarageClassAA);
            result = (this.avarageClassAA / getRows());
            log.debug("result:" + result);
            result = DecimalFormatMka.doFormat(DecimalFormatMka.TWO_PATTERN_ACCURACY, result);
            resultAvarage.add(result);

            //avarage class AB
            log.debug("avarageClassAB:" + this.avarageClassAB);
            result = (this.avarageClassAB / getRows());
            log.debug("result:" + result);
            result = DecimalFormatMka.doFormat(DecimalFormatMka.TWO_PATTERN_ACCURACY, result);
            resultAvarage.add(result);

            //avarage class BA
            log.debug("avarageClassBA:" + this.avarageClassBA);
            result = (this.avarageClassBA / getRows());
            log.debug("result:" + result);
            result = DecimalFormatMka.doFormat(DecimalFormatMka.TWO_PATTERN_ACCURACY, result);
            resultAvarage.add(result);

            //avarage class B
            log.debug("avarageClassB:" + this.avarageClassBB);
            result = (this.avarageClassBB / getRows());
            result = DecimalFormatMka.doFormat(DecimalFormatMka.TWO_PATTERN_ACCURACY, result);
            log.debug("result:" + result);
            resultAvarage.add(result);

        }
        return resultAvarage;
    }

    @Override
    public Object[][] modifyList(List<String> phone, int cols) {

        int numberOfLines = phone.size();  //get number of phonema
        int nRows = numberOfLines / cols;
        int expandedRows = 0;

        if ((nRows % cols) != 0) {
            expandedRows = numberOfLines + cols;
        } else {
            expandedRows = numberOfLines;
        }


        Object[][] newInfo = new Object[expandedRows / cols][2 * cols]; //because is neccessary to add id for each line

        int index = 0;
        int currentCol = 0;
        for (int i = 0; i <= numberOfLines / cols; i++) { //one iteration should generate one line
            currentCol = 0;
            for (int j = 0; j < cols; j++) {
                index = (i * cols) + j;
                if (index < numberOfLines) {
                    newInfo[i][currentCol] = index;
                    currentCol++;
                    newInfo[i][currentCol] = phone.get(index);
                    System.out.println("phone.get(" + index + ")= " + phone.get(index));
                    currentCol++;
                } else {
                    newInfo[i][currentCol] = "";
                    currentCol++;
                    newInfo[i][currentCol] = "";
                    currentCol++;
                }
            }
        }

        return newInfo;
    }

    @Override
    public String[][] loadData(String filename) throws IOException {
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
                    subStr = strLine.split(SeparatorConst.Separator.TABULATOR_SEPARATOR.getSeparatorValue());
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
        final Writer writerA = new FileMkaImpl().openFileForWriting(Const.GENERAL_OUTPUT.OUTPUT_SUCCESS_CLASS_AA);
        final Writer writerAB = new FileMkaImpl().openFileForWriting(Const.GENERAL_OUTPUT.OUTPUT_SUCCESS_CLASS_AB);
        final Writer writerB = new FileMkaImpl().openFileForWriting(Const.GENERAL_OUTPUT.OUTPUT_SUCCESS_CLASS_BB);
        final Writer writerBA = new FileMkaImpl().openFileForWriting(Const.GENERAL_OUTPUT.OUTPUT_SUCCESS_CLASS_BA);
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
            String usedSeparator = SeparatorConst.Separator.TABULATOR_SEPARATOR.getSeparatorValue();
            final Double avaragaValue = Double.valueOf(x.toString());
            if (col == 0) {   //save nn id
                successWriter.write(x.toString());
                writerA.write(x.toString());
                writerAB.write(x.toString());
                writerBA.write(x.toString());
                writerB.write(x.toString());
            } else if (col == 1) { //save general success
                successWriter.write(usedSeparator + x + "\n");
                generalAvarage += avaragaValue;
            } else if (col == 2) { //aa
                avarageClassAA += avaragaValue;
                writerA.write(usedSeparator + x + "\n");
            } else if (col == 3) { //ab
                avarageClassAB += avaragaValue;
                writerAB.write(usedSeparator + x + "\n");
            } else if (col == 4) { //ba
                avarageClassBA += avaragaValue;
                writerBA.write(usedSeparator + x + "\n");
            } else if (col == 5) {
                avarageClassBB += avaragaValue;
                writerB.write(usedSeparator + x + "\n");
            }
            col++;
        }
        successWriter.close();
        writerA.close();
        writerAB.close();
        writerBA.close();
        writerB.close();

        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getCols(); j++) {
                log.debug("numbers[" + i + "][" + j + "]:=" + numbers[i][j]);
            }
        }
        return numbers;
    }

    @Override
    public List<Integer> getListOfPhonemeId() {
        return nnId;
    }

    @Override
    public void setSeparator(String separator) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
