package fi.ixonos.projects.web.groupmanagment;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

import fi.ixonos.projects.logic.bean.CodeTable;
import fi.ixonos.projects.logic.bean.Groups;
import fi.ixonos.projects.logic.service.GroupAuthorityService;

/**
 *
 * @author katrami
 */
public class GroupsUpdateAuthoritiesWindow extends Window {

    final GroupAuthorityService groupAuthorityService = (GroupAuthorityService) SpringUtil.getApplicationContext().getBean("groupAuthorityService");

    public void onUpdateAuthority(final Event event) throws Exception {

        List<String> listOfAuthoritiesForCurrentGroup = new ArrayList<String>();
        List listItems = ((Listbox) this.getFellow("LISTBOX_AUTHORITIES")).getItems();

        for (Object listItem : listItems) {
            Listitem li = (Listitem) listItem;
            if (li.isSelected()) {
                listOfAuthoritiesForCurrentGroup.add(((CodeTable) li.getValue()).getCode());
            }
        }

        final Listbox listbox = ((Listbox) this.getFellow("LISTBOX_OF_GROUP"));
        final Listitem selectedItem = listbox.getSelectedItem();
        if (selectedItem == null || selectedItem.getValue() == null) {
            return;
        }
        final Groups group = (Groups) selectedItem.getValue();
        final String groupName = group.getGroupName();

        groupAuthorityService.changeGroupAuthorities(groupName, listOfAuthoritiesForCurrentGroup);
    }
}
