package fi.ixonos.projects.web;

/**
 *
 * @author katrami
 * @date Oct 15, 2010
 */
import fi.ixonos.projects.logic.bean.Projects;
import java.util.Comparator;

@SuppressWarnings("unchecked")
public class ProjectsComparator implements Comparator {

    private boolean _asc;
    private String _column;

    public ProjectsComparator(boolean asc, String column) {
        _asc = asc;
        _column = column;
    }

    @Override
    public int compare(Object o1, Object o2) {
        Projects project1 = (Projects) o1;
        Projects project2 = (Projects) o2;
        int v = 0;
        if ("nameListbox".equals(_column)) {
            v = project1.getName().compareToIgnoreCase(project2.getName());
        } else if ("startDateListbox".equals(_column)) {
            if (project1.getDateFrom() == null) {
                v = -1;
            } else if (project2.getDateFrom() == null) {
                v = 1;
            } else {
                v = -project1.getDateFrom().compareTo(project2.getDateFrom());
            }
        } else if ("endDateListbox".equals(_column)) {
            if (project1.getDateTo() == null) {
                v = -1;
            } else if (project2.getDateTo() == null) {
                v = 1;
            } else {
                v = -project1.getDateTo().compareTo(project2.getDateTo());
            }
        }
//        } else if ("stateListbox".equals(_column)) {
//            v = project1.getState().getIndex().compareTo(project2.getState().getIndex());
//        }
        return _asc ? v : -v;
    }
}
