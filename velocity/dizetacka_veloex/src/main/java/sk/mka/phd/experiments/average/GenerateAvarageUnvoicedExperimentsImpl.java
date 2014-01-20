package sk.mka.phd.experiments.average;

import java.util.ArrayList;
import sk.mka.phd.matlab.cons.Const;
import sk.mka.phd.tools.filesystem.FileSystem;

/**
 *
 * @author katrami
 * Class need as input folder defined by pom.xml, which contains subfolders, where each subofolders constains file result.txt.
 * Class generate average statistics for all experiment, which are defiened in input folder. Experiemnts show success dependence
 * on population class B. 
 */
public class GenerateAvarageUnvoicedExperimentsImpl extends AbstractGenerateExperimentsAverage implements GenerateExperimentsAverageInterface {

    public GenerateAvarageUnvoicedExperimentsImpl(final String directorySuffix) {
        this.setTemplateVelocityFileForAverageListExperiments(Const.INPUT.VM.TEMPLATE_AVERAGE_LIST_VOICED_EXPERIMENTS);
        setUpFolderPath(directorySuffix);
    }

    /**
     *
     * @param folderName
     * @param expArrayList
     */
    @Override
    public void extracPopClassBFromFolderName(final String folderName,
final             ArrayList expArrayList) {
        String excludeBadCharacter = folderName.replace(CHARACTER_, "\\_");
        int beggin = excludeBadCharacter.indexOf('u');
        int end = excludeBadCharacter.indexOf('p');
        String popClassB = excludeBadCharacter.substring(beggin + 1, end);
        expArrayList.add(popClassB);
    }

    @Override
    public void setUpFolderPath(final String lastFolder) {
        final String user = FileSystem.getLoggedUser();
        final String path = "/home/" + user + EXPERIMENTS_PATH_RELATIVE + "unvoiced/" + lastFolder + "/";
        this.experimentStartFolder = path;
        log.debug("experimentStartFolder: " + experimentStartFolder);
    }
}

