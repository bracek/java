package sk.mka.phd.experiments.average;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
import sk.mka.phd.matlab.cons.Const;
import sk.mka.phd.matlab.cons.Const.BBColums;
import sk.mka.phd.matlab.cons.SeparatorConst;
import sk.mka.phd.matlab.file.FileReaderMka;
import sk.mka.phd.matlab.file.FileMkaImpl;
import sk.mka.phd.matlab.file.MkaResultsFilesInFolderFileReaderImpl;
import sk.mka.phd.velocity.data.impl.LoadInputDataImpl;
import sk.mka.phd.tools.math.DecimalFormatMka;
import sk.mka.phd.velocity.data.file.IFileLoadData;
import sk.mka.phd.data.ILoadData;
import sk.mka.phd.files.reader.load.arbitrary.ArbitraryLoadSingleLineImpl;

/**
 *
 * @author bracek
 * @date Nov 17, 2009
 */
public abstract class AbstractGenerateExperimentsAverage implements IGenerateExperimentsAverage {

    public static final String INPUT_PARAMS_TXT = "input_params.txt";
    public static final String TOPOLOGY_TXT = "topology.txt";
    private int rows = 0;
    private int cols = 0;
    protected String experimentStartFolder = null;
    protected String templateVelocityFileForAverageListExperiments = null;
    protected int whichColumnContainsBBclassification; // if result file doesn't contain AB, BA result than this value has to be 3 else 5
    public ArrayList experimentsList = new ArrayList();
    public Logger log = Logger.getLogger(GenerateAvarageVoicedExperimentsImpl.class);

    public AbstractGenerateExperimentsAverage(final BBColums desiredColumn) {
        this.whichColumnContainsBBclassification = desiredColumn.value();
    }

    @Override
    public void init() throws IOException {
        File startingDirectory = new File(experimentStartFolder);
        final FileReaderMka getResultsFile = new MkaResultsFilesInFolderFileReaderImpl();
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
            reader = new BufferedReader(new FileReader(new File(this.templateVelocityFileForAverageListExperiments)));
            final FileWriter fileWriter = new FileWriter(new File(Const.OUTPUT.TXT.OUTPUT_TABLE_TEX));
            // Output
            final Writer writer = new BufferedWriter(fileWriter);
            final Map<String, Object> model = new HashMap<String, Object>();
            model.put("info", info);
            // Velocity context
            final VelocityContext context = new VelocityContext(model);
            // Generating file according template
            velocityEngine.evaluate(context, writer, "", reader);
            writer.close();
        } catch (final IOException e) {
            log.debug(e.getMessage());
            throw new RuntimeException("Cannot generate output for template " + e.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }

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
                } else if (j == whichColumnContainsBBclassification) {
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
    public ArrayList readFile(final File directory) {
        ArrayList expArrayList = null;
        try {
            String folderName = null;
            if (directory.isDirectory()) {
                folderName = directory.getName();
            }

            String finalPath = directory.getPath() + "/" + RESULT_TXT;
            final ILoadData loadInputData = new LoadInputDataImpl();

            loadInputData.setSeparator(SeparatorConst.Separator.TABULATOR_SEPARATOR.getSeparatorValue());
            final Object[][] info = ((IFileLoadData) loadInputData).loadDataAsObjects(finalPath);
            expArrayList = countAvarageForFile(folderName, info);

            //read mc
            String finalPathInputParams = directory.getPath() + "/" + INPUT_PARAMS_TXT;
            ArbitraryLoadSingleLineImpl inputParams = new ArbitraryLoadSingleLineImpl(finalPathInputParams, "\t");
            ArrayList<Object> lines = inputParams.getLines();

            String[] separatedElements = lines.toString().split(",");
            if (separatedElements.length < 5) {
//                try {
//                    throw new Exception("! Trouble with loading inputFile" + finalPathInputParams);
//                } catch (Exception ex) {
//                    log.error(ex);
//                    System.exit(-1);
//                }
            }

            for (int i = 0; i < separatedElements.length; i++) {
                //momentum mc
                if (i == 0) {
                    String mc = separatedElements[i].toString();
                    mc = mc.replace("[", "");
                    mc = mc.replace("]", "");
                    mc = mc.replace(" ", "");
//                    System.out.println("mc = " + mc);
                    expArrayList.add(mc);
                }

                //learning rate lr
                if (i == 2) {
                    String lr = separatedElements[i].toString();
                    lr = lr.replace("[", "");
                    lr = lr.replace("]", "");
                    lr = lr.replace(" ", "");
//                    System.out.println("mc = " + mc);
                    expArrayList.add(lr);
                }
            }

            String finalTopology = directory.getPath() + "/" + TOPOLOGY_TXT;
            ArbitraryLoadSingleLineImpl inputParamsTopoloy = new ArbitraryLoadSingleLineImpl(finalTopology, "\t");
            ArrayList<Object> linRes = inputParamsTopoloy.getLines();

            String[] separateLinesTopology = linRes.toString().split(",");
            if (separateLinesTopology.length < 4) {
                try {
                    throw new Exception("! Trouble with loading file" + finalPathInputParams);
                } catch (Exception ex1) {
                    log.error(ex1);
                }
            }

            StringBuilder buffer = new StringBuilder();
            for (int i = 0; i < separateLinesTopology.length; i++) {
                //momentum mc
                String neronsValue = separateLinesTopology[i].toString();
                neronsValue = neronsValue.replace("[", "");
                neronsValue = neronsValue.replace("]", "");
                neronsValue = neronsValue.replace(" ", "");
                buffer.append(neronsValue);
                if (i != separateLinesTopology.length - 1) {
                    buffer.append("-");
                }
//                    System.out.println("mc = " + mc);
            }
            expArrayList.add(buffer);



            if (this.cols == 0) {
                this.cols = expArrayList.size();
                log.debug("setting cols for array: " + cols);
            }
            System.out.println(directory);

        } catch (IOException ex) {
            log.fatal(ex);
        }

        return expArrayList;
    }

    private void writeInfoToOuputFile(final Object[][] info) throws IOException {
        final Writer output = new FileMkaImpl().openFileForWriting(OUTPUT_AVERAGE_EXPERIMENTS);


        for (int i = 0; i
                < info.length; i++) {
            Object[] objects = info[i];


            for (int j = 0; j
                    < objects.length; j++) {
                Object object = objects[j];

                try {
                    output.write(object.toString());
                    output.write(SeparatorConst.Separator.TABULATOR_SEPARATOR.getSeparatorValue());

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

    public int getWhichColumnContainsBBclassification() {
        return whichColumnContainsBBclassification;






    }

    public void setWhichColumnContainsBBclassification(final int whichColumnContainsBBclassification) {
        this.whichColumnContainsBBclassification = whichColumnContainsBBclassification;



    }
}
