package fi.ixonos.projects.web.timeline;

import fi.ixonos.projects.logic.bean.Projects;
import fi.ixonos.projects.logic.bean.Users;
import fi.ixonos.projects.logic.service.ProjectsService;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.zkforge.timeline.Bandinfo;
import org.zkforge.timeline.data.OccurEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

import fi.ixonos.projects.logic.service.UsersService;
import fi.ixonos.projects.web.servlet.ProjectsInitServlet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;
import org.zkoss.util.resource.Labels;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Panel;

public class TimeLineWindow extends Window {

    private static final long serialVersionUID = 1L;
    private static Logger logger = Logger.getLogger(TimeLineWindow.class);
    private static ArrayList<String> colors;
    private List<String> assignedColors = new ArrayList<String>();
    private Random random = new Random();

    @SuppressWarnings("unchecked")
    public TimeLineWindow() {
        colors = new ArrayList<String>();
        Properties properties = ProjectsInitServlet.props;
        for (Entry entry : properties.entrySet()) {
            String key = entry.getKey().toString();
            if (key.equals("timeline.projecstate.colors") && (entry.getValue() != null)) {
                String[] value = entry.getValue().toString().split(",");
                colors.addAll(Arrays.asList(value));
            }
        }
    }

    public void init() throws Exception {
        ((Combobox) getFellow("timelineCombobox")).setSelectedIndex(0);
        setProjectsTimelineModel((Listbox) getFellow("select"));
    }

    public void onTimelineListboxSelected(Event event) throws InterruptedException, Exception {
        Combobox timeline = (Combobox) getFellow("timelineCombobox");
        Listbox listbox = (Listbox) getFellow("select");
        for (Object object : listbox.getItems()) {
            Listitem li = (Listitem) object;
            Listcell lc = (Listcell) li.getFirstChild();
            Panel cp = (Panel)lc.getFirstChild();
            if(!li.isSelected() && lc.getValue() != null) {
                releaseColor((String) lc.getValue());
                cp.setStyle("background-color:transparent");
                lc.setValue(null);
            } else if(li.isSelected() && lc.getValue() == null) {
                String color = acquireColor();
                cp.setStyle("background-color:" + color);
                lc.setValue(color);
            }
        }
        List list = new ArrayList(listbox.getSelectedItems());
        if (Labels.getLabel("timeline.combobox.projects").equals(timeline.getValue())) {
            setTimeline(getProjectsTimeLineModel(list));
        } else if (Labels.getLabel("timeline.combobox.users").equals(timeline.getValue())) {
            setTimeline(getUsersTimeLineModel(list));
        }
    }

    public void onTimelineComboboxSelected(Event event) {
        Combobox timeline = (Combobox) getFellow("timelineCombobox");
        Listbox listbox = (Listbox) getFellow("select");
        assignedColors.clear(); // release allocated color resources
        if (Labels.getLabel("timeline.combobox.projects").equals(timeline.getValue())) {
            setProjectsTimelineModel(listbox);
        } else if (Labels.getLabel("timeline.combobox.users").equals(timeline.getValue())) {
            setUsersTimelineModel(listbox);
        }
    }

    private void setProjectsTimelineModel(Listbox listbox) {
        final ProjectsService projectService = (ProjectsService) SpringUtil.getApplicationContext().getBean("projectsService");
        List<Projects> projectList = projectService.readAll();
        listbox.getChildren().clear();
        Listhead listhead = new Listhead();
        Listheader imageListHeader = new Listheader("clr");
        imageListHeader.setWidth("20px");
        listhead.appendChild(imageListHeader);
        listhead.appendChild(new Listheader("project name"));
        listbox.appendChild(listhead);
        listbox.setModel(new ListModelList(projectList));
        listbox.setItemRenderer(new ListitemRenderer() {
            @Override
            public void render(Listitem item, Object data) throws Exception {
                Listcell colorCell = new Listcell();
                Panel panel = new Panel();
                panel.setWidth("20px");
                panel.setHeight("20px");
                colorCell.appendChild(panel);
                item.appendChild(colorCell);
                Listcell nameCell = new Listcell();
                Projects project = (Projects) data;
                nameCell.appendChild(new Label(project.getName()));
                item.appendChild(nameCell);
                item.setValue(data);
            }
        });
        //listbox.setSelectedItems(null);
    }

    private void setUsersTimelineModel(Listbox listbox) {
        final UsersService userService = (UsersService) SpringUtil.getApplicationContext().getBean("usersService");
        List<Users> userList = userService.getSortedUsers("surname");
        listbox.getChildren().clear();
        Listhead listhead = new Listhead();
        Listheader imageListHeader = new Listheader("clr");
        imageListHeader.setWidth("20px");
        listhead.appendChild(imageListHeader);
        listhead.appendChild(new Listheader("user name"));
        listbox.appendChild(listhead);
        listbox.setModel(new ListModelList(userList));
        listbox.setItemRenderer(new ListitemRenderer() {
            @Override
            public void render(Listitem item, Object data) throws Exception {
                Listcell colorCell = new Listcell();
                Panel panel = new Panel();
                panel.setWidth("20px");
                panel.setHeight("20px");
                if(!item.isSelected()) {
                    panel.setStyle("background-color:transparent");
                    colorCell.setValue(null);
                }
                colorCell.appendChild(panel);
                item.appendChild(colorCell);
                Listcell nameCell = new Listcell();
                Users user = (Users) data;
                nameCell.appendChild(new Label(user.getSurname() + " " + user.getName()));
                item.appendChild(nameCell);
                item.setValue(data);
            }
        });
        //listbox.setSelectedItems(null);
    }

    /***
     * 
     * @return get timeline for all users
     */
    public static ListModelList getUsersTimeLineModel(List<Listitem> itemsList) throws Exception {
        final UsersService usersService = (UsersService) SpringUtil.getApplicationContext().getBean("usersService");

        final ListModelList model = new ListModelList();
        for(Listitem listitem : itemsList) {
            Users user = (Users) listitem.getValue();
            String color = (String) ((Listcell)listitem.getFirstChild()).getValue();
            Collection<Projects> projectsCollection = usersService.getProjectsCollectionForUser(user.getUsername());
            //return equals color for project
            for (Projects projects : projectsCollection) {
                OccurEvent event = new OccurEvent();
                event.setText(projects.getName());
                event.setDuration(true);
                event.setColor(color);
                event.setDescription("<html><head/><body>"
                        + "Description: " + projects.getDescription()
                        + "<br/>"
                        + "</body></html>");
                event.setLinkUrl("http://www.ixonos.com");
                event.setStart(projects.getDateFrom());
                if (projects.getDateTo() == null) {
                    if (new Date().before(projects.getDateFrom())) {
                        event.setEnd(event.getStart());
                    } else {
                        event.setEnd(new Date());
                    }
                } else {
                    event.setEnd(projects.getDateTo());
                }
                if (event.getEnd().before(event.getStart())) {
                    logger.error("Projects (id = " + projects.getProjectsId() + ") date order in timeline is not correct!");
                } else {
                    model.add(0, event);
                }
            }
        }
        return model;

    }

    /**
     *
     * @return timeline for all available projects
     */
    public ListModelList getProjectsTimeLineModel(List<Listitem> itemsList) {
        ListModelList model = new ListModelList();

        for (Listitem listitem : itemsList) {
            Projects project = (Projects) listitem.getValue();
            String color = (String) ((Listcell)listitem.getFirstChild()).getValue();
            OccurEvent event = new OccurEvent();
            event.setDuration(true);
            event.setText(project.getName());
            String description = project.getDescription() == null ? "" : project.getDescription();
            event.setDescription("<html><head/><body>"
                    + "Description: " + description
                    + "<br/>"
                    + "</body></html>");
            event.setLinkUrl("http://www.ixonos.com");
            event.setColor(color);

            event.setStart(project.getDateFrom());
            if (project.getDateTo() == null) {
                if (new Date().before(project.getDateFrom())) {
                    event.setEnd(event.getStart());
                } else {
                    event.setEnd(new Date());
                }
            } else {
                event.setEnd(project.getDateTo());
            }

            if (event.getEnd().before(event.getStart())) {
                logger.error("Projects (id = " + project.getProjectsId() + ") date order in timeline is not correct!");
            } else {
                model.add(event);
            }
        }
        return model;

    }

    private void setTimeline(ListModelList model) {
        Bandinfo bandinfo = (Bandinfo) this.getFellow("bandTimeline");
        bandinfo.setModel(new BindingListModelList(model, true));
        Iterator<OccurEvent> iterator = model.iterator();
        while (iterator.hasNext()) {
            bandinfo.addOccurEvent(iterator.next());

        }
    }

    private String acquireColor() {
        int i=0;
        String color = null;
        if(colors.isEmpty())
            throw new IllegalStateException("Failed to load colors from properties");
        if(assignedColors.size() < colors.size())
            do {
                color = colors.get(i++ % colors.size());
            }while(assignedColors.contains(color));
        else {
            color = generateColor(256);
            colors.add(color);
        }
        assignedColors.add(color);
        return color;
    }

    private void releaseColor(String color) {
        assignedColors.remove(color);
    }

    private String generateColor(int threshold) {
        StringBuilder sb = new StringBuilder("#");
        sb.append(Integer.toHexString(random.nextInt(threshold))); // red
        sb.append(Integer.toHexString(random.nextInt(threshold))); // green
        sb.append(Integer.toHexString(random.nextInt(threshold))); // blue
        return sb.toString();
    }

}
