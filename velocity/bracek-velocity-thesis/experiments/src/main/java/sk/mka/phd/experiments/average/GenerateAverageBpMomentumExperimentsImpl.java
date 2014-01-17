package sk.mka.phd.experiments.average;

import java.util.ArrayList;
import sk.mka.phd.matlab.cons.Const.BBColums;
import sk.mka.phd.tools.filesystem.FileSystem;

/**
 *
 * @author bracek
 * @date Dec 10, 2009
 */
public final class GenerateAverageBpMomentumExperimentsImpl extends AbstractGenerateExperimentsAverage implements IGenerateExperimentsAverage {

    private static final String TEMPLATE_AVERAGE_LIST_VOICED_EXPERIMENTS = "input/experiments/voiced/table_list_experiments.vm";

    public GenerateAverageBpMomentumExperimentsImpl(final String directorySuffix, final BBColums desiredColumn) {
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
        String excludeBadCharacter = folderName.replace("_", "");
        String result = excludeBadCharacter.replace(".", "");
        expArrayList.add(result);
    }

    @Override
    public void setUpFolderPath(final String lastFolder) {
        final String user = FileSystem.getLoggedUser();
        final String path = HOME + user + STATISTICS_BPMOMENTUM_FONIK + lastFolder + "/";
        this.experimentStartFolder = path;
        log.debug("experimentStartFolder: " + experimentStartFolder);

    }
}
