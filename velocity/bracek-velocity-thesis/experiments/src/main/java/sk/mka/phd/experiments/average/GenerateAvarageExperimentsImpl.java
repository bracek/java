package sk.mka.phd.experiments.average;

import java.util.ArrayList;
import sk.mka.phd.matlab.cons.Const.BBColums;

/**
 *
 * @author katrami
 * Class need as input folder defined by pom.xml, which contains subfolders, where each subofolders constains file result.txt.
 * Class generate average statistics for all experiment, which are defiened in input folder. Experiemnts show success dependence
 * on population class B. 
 */
public final class GenerateAvarageExperimentsImpl extends AbstractGenerateExperimentsAverage implements IGenerateExperimentsAverage {

    private static final String TEMPLATE_AVERAGE_LIST_VOICED_EXPERIMENTS = "input/experiments/table_list_experiments.vm";
    private static int index = 1;

    /**
     * 
     * @param directorySuffix
     * @param desiredColumn if results.txt contains also AB,BA results, than desiredColumn is 5 else 3 
     */
    public GenerateAvarageExperimentsImpl(final String directorySuffix, final BBColums desiredColumn) {
        super(desiredColumn);
        this.setTemplateVelocityFileForAverageListExperiments(TEMPLATE_AVERAGE_LIST_VOICED_EXPERIMENTS);
        setUpFolderPath(directorySuffix);
        
    }

    /**
     *
     * @param folderName
     * @param expArrayList
     */
    @Override
    public void extracPopClassBFromFolderName(final String folderName,final  ArrayList expArrayList) {
        expArrayList.add(index);
        index++;
    }

    @Override
    public void setUpFolderPath(final String lastFolder) {
        setExperimentStartFolder(lastFolder);
    }
}
