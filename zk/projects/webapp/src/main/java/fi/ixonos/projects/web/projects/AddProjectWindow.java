package fi.ixonos.projects.web.projects;

import fi.ixonos.projects.logic.bean.Projects;
import fi.ixonos.projects.logic.bean.Users;
import fi.ixonos.projects.logic.context.ProjectsApplicationContext;
import fi.ixonos.projects.logic.service.ProjectsService;
import java.util.ArrayList;
import java.util.List;
import org.zkoss.util.resource.Labels;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Window;

/**
 *
 * @author katrami
 */
public final class AddProjectWindow extends Window {

    private Projects project;
    private ProjectsService projectsService = (final ProjectsService) ProjectsApplicationContext.getApplicationContext().getBean("projectsService");

    public AddProjectWindow() {
        this.project = new Projects();
    }

    public void addProject(final Event event) throws Exception {
        if (checkValues(project)) {
            return;
        }
        try {

            projectsService.create(project);
        } catch (Exception e) {
            Messagebox.show(e.getMessage(), Labels.getLabel("projects.error"), Messagebox.OK, Messagebox.ERROR);
            return;
        }
        this.detach();
    }

    public void onCancel(final Event event) {
        this.detach();
    }

    public boolean checkValues(final Projects project) throws Exception {

        StringBuilder error = new StringBuilder();
        if (project.getName() == null || "".equals(project.getName())) {
            error.append(Labels.getLabel("projects.error.insertName"));
        }
        if (project.getDateFrom() == null) {
            error.append("\n").append(Labels.getLabel("projects.error.insertStartDate"));
        }
        if (project.getDateTo() == null) {
            error.append("\n").append(Labels.getLabel("projects.error.insertStartDate"));

        } else if (project.getDateTo() != null && project.getDateTo().before(project.getDateFrom())) {
            error.append("\n").append(Labels.getLabel("projects.error.badDateOrder"));
        }

        final List<Projects> listOfExistingProjects = projectsService.readAll();
        boolean alreadyExistProjectName = false;
        for (Projects projects : listOfExistingProjects) {
            if (projects.getName().equals(this.project.getName())) {
                alreadyExistProjectName = true;
                break;
            }
        }

        if (alreadyExistProjectName) {
            error.append("\n").append(Labels.getLabel("projects.error.already.exist"));
        }

        if (error.length() > 0) {
            Messagebox.show(error.toString(), "Error", Messagebox.OK, Messagebox.ERROR);
            return true;
        } else {
            return false;
        }
    }

    public Projects getProject() {
        return project;
    }

    public void setProject(final Projects project) {
        this.project = project;
    }
}
