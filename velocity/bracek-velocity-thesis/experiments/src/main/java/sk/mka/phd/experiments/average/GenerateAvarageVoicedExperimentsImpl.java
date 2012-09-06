package sk.mka.phd.experiments.average;

import java.util.ArrayList;
import sk.mka.phd.matlab.cons.Const.BBColums;
import sk.mka.phd.tools.filesystem.FileSystem;

/**
 *
 * @author katrami
 * Class need as input folder defined by pom.xml, which contains subfolders, where each subofolders constains file result.txt.
 * Class generate average statistics for all experiment, which are defiened in input folder. Experiemnts show success dependence
 * on population class B. 
 */
public final class GenerateAvarageVoicedExperimentsImpl extends AbstractGenerateExperimentsAverage implements IGenerateExperimentsAverage {

    private static final String TEMPLATE_AVERAGE_LIST_VOICED_EXPERIMENTS = "input/experiments/voiced/table_list_experiments.vm";

    /**
     * 
     * @param directorySuffix
     * @param desiredColumn if results.txt contains also AB,BA results, than desiredColumn is 5 else 3 
     */
    public GenerateAvarageVoicedExperimentsImpl(String directorySuffix, final BBColums desiredColumn) {
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
    public void extracPopClassBFromFolderName(final String folderName, ArrayList expArrayList) {
        String excludeBadCharacter = folderName.replace(CHARACTER_, "\\_");
        int end = excludeBadCharacter.indexOf('p');
        if (end > 0) {
            String popClassB = excludeBadCharacter.substring(0, end);
            expArrayList.add(popClassB);
        }
//        else {
//            expArrayList.add(index);
//            index++;
//        }

    }

    @Override
    public void setUpFolderPath(String lastFolder) {
        final String user = FileSystem.getLoggedUser();
        final String path = HOME + user + EXPERIMENTS_PATH_RELATIVE + "voiced/" + lastFolder + "/";
        this.experimentStartFolder = path;
        log.debug("experimentStartFolder: " + experimentStartFolder);
    }
}
