/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.ixonos.projects.web.allocation;

import fi.ixonos.projects.logic.bean.Projects;
import fi.ixonos.projects.logic.bean.Users;
import fi.ixonos.projects.logic.context.ProjectsApplicationContext;
import fi.ixonos.projects.logic.service.ProjectsService;
import fi.ixonos.projects.logic.service.UsersService;
import fi.ixonos.projects.web.projects.AddProjectWindow;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.userdetails.User;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.OpenEvent;
import org.zkoss.zkmax.zul.Portalchildren;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Menu;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Menuseparator;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

/**
 *
 * @author polakja
 */
public class EditAllocationWindow extends Window {

    private ProjectsService projectService = (final ProjectsService) ProjectsApplicationContext.getApplicationContext().getBean("projectsService");
    private UsersService usersService = (final UsersService) ProjectsApplicationContext.getApplicationContext().getBean("usersService");
    private Map<Panel, Projects> projectMap = new HashMap<Panel, Projects>();
    private Map<Panel, Combobox> availableMap = new HashMap<Panel, Combobox>();
    private Map<Panel, Listbox> allocatedMap = new HashMap<Panel, Listbox>();
    private Panel renaming = null;

    public void init() throws Exception {
        Listbox projectsListbox = (Listbox) getFellow("projectsListbox");
        projectsListbox.setModel(new ListModelList(getAvailableProjects()));
        projectsListbox.setItemRenderer(new ListitemRenderer() {

            @Override
            public void render(final Listitem item,final  Object data) throws Exception {
                item.setLabel(((Projects) data).getName());
                item.setValue(data);
            }
        });
        final String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Collection<Projects> openedProjects = usersService.getOpenedProjects(username);
        for (Projects project : openedProjects) {
            findPortalColumn().appendChild(createPanel(project));
        }

    }

    public List<Projects> getAvailableProjects() throws Exception {
        List<Projects> projects = projectService.readAll();
        final String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        projects.removeAll(usersService.getOpenedProjects(username));
        return projects;
    }

    public void onProjectAdded(final Event e) throws InterruptedException, Exception {
        AddProjectWindow win = (AddProjectWindow) Executions.createComponents("/WEB-INF/jsp/tiles/projects/addProject.zul", null, null);
        win.doModal();
        Projects project = win.getProject();
        if (project.getProjectsId() != null) {
            Portalchildren pc = findPortalColumn();
            final String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
            usersService.openProject(username, project);
            pc.appendChild(createPanel(project));
            Listbox projectsListbox = (Listbox) getFellow("projectsListbox");
            ListModelList comboModel = (ListModelList) projectsListbox.getModel();
            comboModel.clear();
            comboModel.addAll(getAvailableProjects());
        }
    }

    public void onProjectSelected(final Event e) throws InterruptedException, Exception {
        Listbox projectsListbox = (Listbox) e.getTarget();
        Listitem item = projectsListbox.getSelectedItem();
        if (item != null) {
            Portalchildren pc = findPortalColumn();
            final String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
            Projects project = (Projects) item.getValue();
            usersService.openProject(username, project);
            pc.appendChild(createPanel(project));
            ListModelList listModel = (ListModelList) projectsListbox.getModel();
            listModel.clear();
            listModel.addAll(getAvailableProjects());
            projectsListbox.setSelectedIndex(-1);
        }
    }

    public void onProjectRenaming(final Event e) {
        OpenEvent oe = (OpenEvent) e;
        if (oe.isOpen()) {
            ((Textbox) getFellow("renameTextbox")).setValue("");
        }
        renaming = (Panel) oe.getReference();
        if (renaming != null) {
            Textbox textbox = (Textbox) getFellow("renameTextbox");
            textbox.setValue(projectMap.get(renaming).getName());
            textbox.select();
            textbox.focus();
        }
    }

    public void onProjectRenamed(final Event e) throws Exception {
        try {
            if (renaming != null) {
                renameProject(renaming, ((Textbox) getFellow("renameTextbox")).getValue());
            }
        } finally {
            ((Popup) getFellow("renamePopup")).close();
        }
    }

    public void onSaveAllClicked(final Event e) throws Exception {
        for(Map.Entry<Panel, Projects> entry : projectMap.entrySet()) {
            Projects project = entry.getValue();
            if(project.isDirty()) {
                if(checkValues(project))
                    return;
            }
        }

        for(Map.Entry<Panel, Projects> entry : projectMap.entrySet()) {
            Projects project = entry.getValue();
            if(project.isDirty()) {
                projectService.update(project);
                project = projectService.getProject(project.getProjectsId());
                projectMap.put(entry.getKey(), project);
                updatePanelData(entry.getKey());
            }
        }
    }

    private Portalchildren findPortalColumn() {
        List children = getFellow("portalLayout").getChildren();
        Portalchildren column = null;
        int count = Integer.MAX_VALUE;
        for (Object object : children) {
            Portalchildren pc = (Portalchildren) object;
            if (pc.getChildren().size() < count) {
                count = pc.getChildren().size();
                column = pc;
            }
        }
        return column;
    }

    @Transactional
    private Panel createPanel(final Projects project) {
        final Panel p = initPanel(new Panel());// Get a panel template
        projectMap.put(p, project);
        p.setTitle(project.getName());
        Panelchildren pc = p.getPanelchildren();
        // description textbox
        final Textbox description = new Textbox(project.getDescription());
        description.setWidth("100%");
        description.setStyle("margin-bottom: 4px;");
        description.addEventListener(Events.ON_CHANGING, new EventListener() {

            @Override
            public void onEvent(final Event event) throws Exception {
                Projects project = projectMap.get(p);
                project.setDescription(description.getValue());
                project.setDirty(true);
                updatePanelData(p);
            }
        });
        pc.appendChild(description);
        // from date hbox
        Component[] dateBox = new Component[3];
        final Datebox dateboxFrom = new Datebox(project.getDateFrom());
        dateboxFrom.addEventListener(Events.ON_CHANGE, new EventListener() {

            @Override
            public void onEvent(final Event event) throws Exception {
                Projects project = projectMap.get(p);
                project.setDateFrom(dateboxFrom.getValue());
                project.setDirty(true);
                updatePanelData(p);
            }
        });
        final Datebox dateboxTo = new Datebox(project.getDateTo());
        dateboxTo.addEventListener(Events.ON_CHANGE, new EventListener() {

            @Override
            public void onEvent(final Event event) throws Exception {
                Projects project = projectMap.get(p);
                project.setDateTo(dateboxTo.getValue());
                project.setDirty(true);
                updatePanelData(p);
            }
        });
        dateBox[0] = dateboxFrom;
        dateBox[1] = new Label("  -  ");
        dateBox[2] = dateboxTo;
        Hbox dateHbox = new Hbox(dateBox);
        dateHbox.setWidth("100%");
        dateHbox.setStyle("margin-bottom: 8px;");
        pc.appendChild(dateHbox);
        pc.appendChild(new Label(Labels.getLabel("allocation.allocatedUsers")));
        // users list
        Listbox usersListbox = new Listbox();
        allocatedMap.put(p, usersListbox);
        usersListbox.setWidth("100%");
        pc.appendChild(usersListbox);
        Listhead listhead = new Listhead();
        Listheader imageListHeader = new Listheader("");
        imageListHeader.setWidth("20px");
        listhead.appendChild(imageListHeader);
        Listheader usernameHeader = new Listheader(Labels.getLabel("allocation.nameSurname"));
        usernameHeader.setSortAscending(new UsersComparator(true, UsersComparator.SURNAME_COLUMN));
        usernameHeader.setSortDescending(new UsersComparator(false, UsersComparator.SURNAME_COLUMN));
        usernameHeader.setSortDirection("ascending");
        listhead.appendChild(usernameHeader);
        usersListbox.appendChild(listhead);
        usersListbox.setModel(new ListModelList());
        usersListbox.setStyle("margin-bottom: 4px;");
        pc.appendChild(new Label(Labels.getLabel("allocation.availableUsers")));
        Combobox usersCombobox = new Combobox();
        availableMap.put(p, usersCombobox);
        usersCombobox.setWidth("92%");
        usersCombobox.setAutodrop(true);
        usersCombobox.setModel(new ListModelList());
        pc.appendChild(usersCombobox);
        usersListbox.setItemRenderer(new ListitemRenderer() {

            @Override
            public void render(final Listitem item,final  Object data) throws Exception {
                item.setDraggable("true");
                Listcell imageCell = new Listcell();
                Button removeButton = new Button("", "/img/minus-8.png");
                removeButton.addEventListener(Events.ON_CLICK, new EventListener() {

                    @Override
                    public void onEvent(final Event event) throws Exception {
                        try {
                            Projects project = projectMap.get(p);
                            project.getUsersCollection().remove((Users) item.getValue());
                            project.setDirty(true);
                            updatePanelData(p);
                        } catch (Exception e) {
                            Messagebox.show(e.getMessage());
                        }
                    }
                });
                imageCell.appendChild(removeButton);
                item.appendChild(imageCell);
                Listcell nameCell = new Listcell();
                Users user = (Users) data;
                nameCell.appendChild(new Label(user.getName() + " " + user.getSurname()));
                item.appendChild(nameCell);
                item.setValue(data);
            }
        });
        usersCombobox.setItemRenderer(new ComboitemRenderer() {

            @Override
            public void render(final Comboitem item,final  Object data) throws Exception {
                Users u = (Users) data;
                item.setLabel(u.getUsername() + " - [ " + u.getName() + " " + u.getSurname() + " ]");
                item.setValue(data);
            }
        });
        usersCombobox.addEventListener(Events.ON_CHANGE, new EventListener() {

            @Override
            public void onEvent(final Event event) throws Exception {
                try {
                    Comboitem ci = ((Combobox) event.getTarget()).getSelectedItem();
                    if (ci != null) {
                        Projects project = projectMap.get(p);
                        if(project.getUsersCollection() == null)
                            project.setUsersCollection(new HashSet<Users>());
                        project.getUsersCollection().add((Users) ci.getValue());
                        project.setDirty(true);
                        updatePanelData(p);
                    }
                } catch (Exception e) {
                    Messagebox.show(e.getMessage());
                    throw e;
                }
            }
        });
        Menubar menubar = new Menubar();
        menubar.setWidth("100%");
        Menuitem miSave = new Menuitem(Labels.getLabel("common.button.save"), "/img/save-icon.png");
        miSave.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(final Event event) throws Exception {
                Projects project = projectMap.get(p);
                if (checkValues(project)) {
                    return;
                } else {
                    // update data in database
                    projectService.update(project);
                    // reload data from database
                    project = projectService.getProject(project.getProjectsId());
                    projectMap.put(p, project);
                    updatePanelData(p);
                }
            }
        });
        menubar.appendChild(miSave);
        Menuitem miRevert = new Menuitem(Labels.getLabel("common.button.revert"), "/img/revert-icon.png");
        miRevert.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(final Event event) throws Exception {
                Projects project = projectMap.get(p);
                // reload data from database
                project = projectService.getProject(project.getProjectsId());
                projectMap.put(p, project);
                updatePanelData(p);
            }
        });
        menubar.appendChild(miRevert);
        Menu menuMore = new Menu(Labels.getLabel("common.button.more") + "  ", "/img/more-icon.png");
        Menupopup menuPopup = new Menupopup();
        Menuitem miRename = new Menuitem(Labels.getLabel("common.button.rename"), "/img/rename-icon.png");
        miRename.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(final Event event) throws Exception {
                ((Popup) getFellow("renamePopup")).open(p, "overlap");
            }
        });
        menuPopup.appendChild(miRename);
        menuPopup.appendChild(new Menuseparator());
        Menuitem miDelete = new Menuitem(Labels.getLabel("common.button.delete"), "/img/delete-icon.png");
        miDelete.addEventListener(Events.ON_CLICK, new EventListener() {

            @Override
            public void onEvent(final Event event) throws Exception {
                Projects project = projectMap.get(p);
                int answer = Messagebox.show(Labels.getLabel("project.question.delete") + " '" + project.getName() + "' ?", Labels.getLabel("common.dialog.confirmation"), Messagebox.YES | Messagebox.NO, Messagebox.QUESTION);
                if (answer == Messagebox.YES) {
                    final String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
                    usersService.closeProject(username, project);
                    projectService.delete(project);
                    projectMap.remove(p);
                    availableMap.remove(p);
                    allocatedMap.remove(p);
                    p.detach();
                }
            }
        });
        menuPopup.appendChild(miDelete);
        menuMore.appendChild(menuPopup);
        menubar.appendChild(menuMore);
        pc.appendChild(menubar);
        p.addEventListener(Events.ON_CLOSE, new EventListener() {

            @Override
            public void onEvent(final Event event) throws Exception {
                Projects project = projectMap.get(p);
                if (project != null) {
                    final String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
                    boolean canClose = true;
                    if (project.isDirty()) {
                        canClose = false;
                        if(!checkValues(project)) {
                            int result = Messagebox.show(Labels.getLabel("allocation.saveChanges"), Labels.getLabel("common.dialog.confirmation"), Messagebox.YES | Messagebox.NO | Messagebox.CANCEL, Messagebox.QUESTION);
                            if (result != Messagebox.CANCEL) {
                                if (result == Messagebox.YES) {
                                    projectService.update(project);
                                }
                                canClose = true;
                            }
                        }
                    }
                    if(canClose) {
                        usersService.closeProject(username, project);
                        projectMap.remove(p);
                        availableMap.remove(p);
                        allocatedMap.remove(p);
                        Listbox projectsListbox = (Listbox) getFellow("projectsListbox");
                        ListModelList comboModel = (ListModelList) projectsListbox.getModel();
                        comboModel.clear();
                        comboModel.addAll(getAvailableProjects());
                        projectsListbox.setSelectedIndex(-1);
                        updateMainPanel();
                    }
                    else{
                      event.stopPropagation();
                    }
                }
            }
        });
        p.addEventListener(Events.ON_DROP, new EventListener() {

            @Override
            public void onEvent(final Event event) throws Exception {
                Component dragged = ((DropEvent) event).getDragged();
                Component target = ((DropEvent) event).getTarget();
                Projects project = null;
                if ("usersSourceListbox".equals(dragged.getParent().getId()) && target instanceof Panel) {
                    project = projectMap.get((Panel) target);
                }
                if (dragged instanceof Listitem && project != null) {
                    Listitem li = (Listitem) dragged;
                    if (li.getValue() instanceof Users) {
                        if(project.getUsersCollection() == null)
                            project.setUsersCollection(new HashSet<Users>());
                        if(!project.getUsersCollection().contains((Users) li.getValue())) {
                            project.getUsersCollection().add((Users) li.getValue());
                            project.setDirty(true);
                            updatePanelData(p);
                        }
                    }
                }
            }
        });
        updatePanelData(p);
        return p;
    }

    private Panel initPanel(final Panel panel) {
        panel.setBorder("normal");
        panel.setCollapsible(true);
        panel.setClosable(true);
        panel.setMaximizable(false);
        panel.appendChild(new Panelchildren());
        panel.setDroppable("true");
        return panel;
    }

    private List<Users> getAvailableUsers(final Projects project) {
        List<Users> users = usersService.readAll();
        users.removeAll(new ArrayList<Users>(project.getUsersCollection()));
        return users;
    }

    public void onDropUserOnList(final Event event) throws Exception {
        Component dragged = ((DropEvent) event).getDragged();
        if (!"usersSourceListbox".equals(dragged.getParent().getId()) && dragged instanceof Listitem) {
            Listitem li = (Listitem) dragged;
            if (li.getValue() instanceof Users) {
                Panel p = retrieveProjectPanel(li.getParent());
                if (p == null) {
                    return;
                }
                Projects project = projectMap.get(p);
                Collection<Users> collection = project.getUsersCollection();
                if (collection.contains((Users) li.getValue())) {
                    collection.remove((Users) li.getValue());
                    project.setDirty(true);
                    updatePanelData(p);
                }
            }
        }
    }

    private Panel retrieveProjectPanel(final Component comp) {
        if (comp instanceof Panel) {
            return (Panel) comp;
        } else {
            return retrieveProjectPanel(comp.getParent());
        }
    }

    private void renameProject(Panel p, String newProjectName) throws Exception {
        Projects project = projectMap.get(p);
        if (project != null) {
            //update project name
            String oldName = project.getName();
            project.setName(newProjectName);
            if(checkValues(project)) {
                project.setName(oldName);
                return;
            }
            else {
                projectService.update(project);
                // remove existing project panel
                Portalchildren pc = (Portalchildren) p.getParent();
                Component ref = p.getNextSibling();
                p.detach();
                projectMap.remove(p);
                availableMap.remove(p);
                allocatedMap.remove(p);
                // reload data from database
                project = projectService.getProject(project.getProjectsId());
                // recreate project panel
                pc.insertBefore(createPanel(project), ref);
            }
        }
    }

    private boolean checkValues(final Projects project) throws Exception {
        StringBuilder error = new StringBuilder();
        if (project.getName() == null || "".equals(project.getName())) {
            error.append(Labels.getLabel("project.error.insertName"));
        }
        if (project.getDateFrom() == null) {
            error.append("\n").append(Labels.getLabel("project.error.insertStartDate"));
        }
        if (project.getDateTo() == null) {
            error.append("\n").append(Labels.getLabel("project.error.insertStartDate"));

        } else if (project.getDateTo() != null && project.getDateTo().before(project.getDateFrom())) {
            error.append("\n").append(Labels.getLabel("project.error.badDateOrder"));
        }

        if (error.length() > 0) {
            Messagebox.show(error.toString(), Labels.getLabel("common.dialog.error"), Messagebox.OK, Messagebox.ERROR);
            return true;
        } else {
            return false;
        }
    }

    private void updatePanelData(final Panel p) {
        Projects project = projectMap.get(p);
        Listbox usersListbox = allocatedMap.get(p);
        Combobox usersCombobox = availableMap.get(p);

        ListModelList listModel = (ListModelList) usersListbox.getModel();
        listModel.clear();
        if(project.getUsersCollection() == null)
            project.setUsersCollection(new HashSet<Users>());
        Users[] array = project.getUsersCollection().toArray(new Users[0]);
        Listhead listhead = (Listhead) usersListbox.getHeads().toArray()[0];
        Listheader usernameHeader = (Listheader) listhead.getChildren().get(1);// username
        boolean isAscending = "ascending".equals(usernameHeader.getSortDirection());
        Arrays.sort(array, new UsersComparator(isAscending, UsersComparator.SURNAME_COLUMN));
        listModel.addAll(Arrays.asList(array));
        ListModelList comboModel = (ListModelList) usersCombobox.getModel();
        comboModel.clear();
        comboModel.addAll(getAvailableUsers(project));
        usersCombobox.setSelectedIndex(-1);

        if (project.isDirty()) {
            p.setStyle("padding: 2px; background-color:#FF3333; margin-bottom: 12px;");
        } else {
            p.setStyle("padding: 2px; background-color:#33AAFF; margin-bottom: 12px;");
        }

        updateMainPanel();
    }

    private void updateMainPanel() {
        boolean dirty = false;
        for (Projects project : projectMap.values()) {
            if (project.isDirty()) {
                dirty = true;
                break;
            }
        }
        getFellow("changed").setVisible(dirty);
    }
}
