package fi.ixonos.projects.web.allocation;

import fi.ixonos.projects.logic.bean.Users;
import java.util.Comparator;

@SuppressWarnings("unchecked")
public class UsersComparator implements Comparator {

    public static final String NAME_COLUMN = "name_column";
    public static final String SURNAME_COLUMN = "surname_column";

    private boolean _asc;
    private String _column;

    public UsersComparator(boolean asc, String column) {
        _asc = asc;
        _column = column;
    }

    @Override
    public int compare(Object o1, Object o2) {
        Users user1 = (Users) o1;
        Users user2 = (Users) o2;
        int v = 0;
        if (NAME_COLUMN.equals(_column)) {
            v = user1.getName().compareToIgnoreCase(user2.getName());
        } else if (SURNAME_COLUMN.equals(_column)) {
            v = user1.getSurname().compareToIgnoreCase(user2.getSurname());
        }
        return _asc ? v : -v;
    }
}
