package sk.mka.phd.experiments.average;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import sk.mka.phd.matlab.cons.Const;
import sk.mka.phd.matlab.file.FileMka;
import sk.mka.phd.matlab.file.FileMkaImpl;
import sk.mka.phd.matlab.file.FileMkaResultsFilesInFolder;
import sk.mka.phd.velocity.data.LoadData;
import sk.mka.phd.velocity.data.impl.LoadInputDataImpl;
import sk.mka.phd.tools.math.DecimalFormatMka;

/**
 *
 * @author bracek
 * @date Nov 17, 2009
 */
public abstract class AbstractGenerateExperimentsAverage implements GenerateExperimentsAverageInterface {

    public ArrayList experimentsList = new ArrayList();
    public Logger log = Logger.getLogger(GenerateAvarageVoicedExperimentsImpl.class);
    protected String experimentStartFolder = null;
    private int rows = 0;
    private int cols = 0;
    protected String templateVelocityFileForAverageListExperiments = null;

    @Override
    public void init() throws IOException {
        File startingDirectory = new File(experimentStartFolder);
        final FileMka getResultsFile = new FileMkaResultsFilesInFolder();
        final List<File> files = getResultsFile.getFileListing(startingDirectory);

        for (File file : files) {
            System.out.println("file = " + file);
        }
        for (File file : files) {
            ArrayList expArrayList = readFile(file);
            experimentsList.add(expArrayList);
        }

        final Object[][] unsortedInfo = createFileObject(experimentsList);
        final Object[][] info = new Object[rows][cols];
        final HashMap sortedPopByIndex = putInfoArrayOfArrayToHashMap(unsortedInfo);

        //added sorted functionality to generate better output
        createSortedInfoForVelocity(sortedPopByIndex, info);
//        sortExperimentsByPopulationClassB(sortedPopByIndex);

        writeInfoToOuputFile(info);

        final VelocityEngine velocityEngine = new VelocityEngine();
        BufferedReader reader = null;
        try {
            // Opens template
            if (this.templateVelocityFileForAverageListExperiments == null) {
                try {
                    throw new Exception("you forgot to define template file for experiment");
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(AbstractGenerateExperimentsAverage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            reader = madeVelocityContext(info, velocityEngine, reader, this.templateVelocityFileForAverageListExperiments, Const.GENERAL_OUTPUT.OUTPUT_TABLE_TEX);
        } catch (final IOException e) {
            log.debug(e.getMessage());
            throw new RuntimeException("Cannot generate output for template " + e.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

    protected BufferedReader madeVelocityContext(final Object[][] info, final VelocityEngine velocityEngine,final  BufferedReader reader,final  String fileReaderName,final  String fileWriterName) throws FileNotFoundException, MethodInvocationException, ResourceNotFoundException, ParseErrorException, IOException {
        reader = new BufferedReader(new FileReader(new File(fileReaderName)));
        final FileWriter fileWriter = new FileWriter(new File(fileWriterName));
        // Output
        final Writer writer = new BufferedWriter(fileWriter);
        final Map<String, Object> model = new HashMap<String, Object>();
        model.put("info", info);
        // Velocity context
        final VelocityContext context = new VelocityContext(model);
        // Generating file according template
        velocityEngine.evaluate(context, writer, "", reader);
        writer.close();
        return reader;
    }

    /**
     * Method iw
     * @param info
     */
    @Override
    public ArrayList countAvarageForFile(final String folderName,final  Object[][] info) {
        ArrayList expArrayList = new ArrayList();
        double average = 0;
        double Aaverage = 0;
        double Baverage = 0;
        for (int i = 0; i < info.length; i++) {
            Object[] objects = info[i];
            for (int j = 0; j < objects.length; j++) {
                String value = (String) objects[j];
                if (j == 1) {
                    average += Double.valueOf(value);
                } else if (j == 2) {
                    Aaverage += Double.valueOf(value);
                } else if (j == 3) {
                    Baverage += Double.valueOf(value);
                }
            }
        }
        extracPopClassBFromFolderName(folderName, expArrayList);
        double finalAverageResult = average / info.length;
        finalAverageResult = DecimalFormatMka.doFormat(DecimalFormatMka.TWO_PATTERN_ACCURACY, finalAverageResult);
        expArrayList.add(finalAverageResult);
        double finalAverageResultA = Aaverage / info.length;
        finalAverageResultA = DecimalFormatMka.doFormat(DecimalFormatMka.TWO_PATTERN_ACCURACY, finalAverageResultA);
        expArrayList.add(finalAverageResultA);
        double finalAverageResultB = Baverage / info.length;
        finalAverageResultB = DecimalFormatMka.doFormat(DecimalFormatMka.TWO_PATTERN_ACCURACY, finalAverageResultB);
        expArrayList.add(finalAverageResultB);
        return expArrayList;
    }

    /**
     *
     * @param experimentsList
     * @return
     */
    @Override
    public Object[][] createFileObject(final ArrayList experimentsList) {
        rows = experimentsList.size();
        Object[][] info = new Object[rows][cols];
        int col = 0;
        for (int idx = 0; idx < experimentsList.size(); idx++) {
            ArrayList arrayList = (ArrayList) experimentsList.get(idx);
            col = 0;
            for (Object object : arrayList) {
                info[idx][col] = object;
                col++;
            }
        }
        return info;
    }

    public void createSortedInfoForVelocity(final HashMap sortedPopByIndex,final  Object[][] info) throws NumberFormatException {
        Set keys = sortedPopByIndex.keySet();
        List list = new ArrayList(keys);
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            Object object = list.get(i);
            int key = Integer.valueOf(object.toString());
            Object[] objects = (Object[]) sortedPopByIndex.get(key);
            int currentCol = 0;
            for (Object obj : objects) {
                info[i][currentCol] = obj;
                currentCol++;
            }
        }
    }

    public HashMap putInfoArrayOfArrayToHashMap(final Object[][] unsortedInfo) throws NumberFormatException {
        //necessary to sort
        HashMap sortedPopByIndex = new HashMap();
        for (int i = 0; i < unsortedInfo.length; i++) {
            Object[] objects = unsortedInfo[i];
            for (int j = 0; j < objects.length; j++) {
                if (j == 0) {
                    Object object = objects[j];
                    sortedPopByIndex.put(Integer.valueOf(object.toString()), objects);
                }
            }
        }
        return sortedPopByIndex;
    }

    /**
     * 
     * @param file
     * @return
     */
    @Override
    public ArrayList readFile(final File file) {
        ArrayList expArrayList = null;
        try {
            String folderName = null;
            if (file.isDirectory()) {
                folderName = file.getName();
            }

            String finalPath = file.getPath() + "/" + RESULT_TXT;
            final LoadData loadInputData = new LoadInputDataImpl();
            final Object[][] info = loadInputData.loadDataAsObjects(finalPath);
            expArrayList = countAvarageForFile(folderName, info);

            if (this.cols == 0) {
                this.cols = expArrayList.size();
                log.debug("setting cols for array: " + cols);
            }
            System.out.println(file);

        } catch (IOException ex) {
            log.fatal(ex);
        }
        return expArrayList;
    }

    private void writeInfoToOuputFile(final Object[][] info) throws IOException {
        final Writer output = new FileMkaImpl().openFileForWriting(OUTPUT_AVERAGE_EXPERIMENTS);
        for (int i = 0; i < info.length; i++) {
            Object[] objects = info[i];
            for (int j = 0; j < objects.length; j++) {
                Object object = objects[j];
                try {
                    output.write(object.toString());
                    output.write("\t");

                } catch (IOException ex) {
                    log.fatal(ex);
                }
            }
            output.write("\n");
        }
        System.out.println("Values were written to your file: " + OUTPUT_AVERAGE_EXPERIMENTS + " successfuly");
        output.close();
    }

    @Override
    public String getExperimentStartFolder() {
        return experimentStartFolder;
    }

    @Override
    public void setExperimentStartFolder(final String experimentStartFolder) {
        this.experimentStartFolder = experimentStartFolder;
    }

    public String getTemplateVelocityFileForAverageListExperiments() {
        return templateVelocityFileForAverageListExperiments;
    }

    public void setTemplateVelocityFileForAverageListExperiments(final String templateVelocityFileForAverageListExperiments) {
        this.templateVelocityFileForAverageListExperiments = templateVelocityFileForAverageListExperiments;
    }
}
