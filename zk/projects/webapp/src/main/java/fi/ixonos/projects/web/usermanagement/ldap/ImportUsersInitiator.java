package fi.ixonos.projects.web.usermanagement.ldap;

import java.util.ArrayList;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zkplus.databind.AnnotateDataBinderInit;
import org.zkoss.zkplus.databind.BindingListModel;
import org.zkoss.zkplus.databind.BindingListModelList;

/**
 * @author polakja
 *
 */
public class ImportUsersInitiator extends AnnotateDataBinderInit {

	@Override
    public void doAfterCompose(final Page page,
final  Component[] comps) throws Exception {
		
		BindingListModel emptyList  = new BindingListModelList(new ArrayList<String>(), true);
		page.setVariable("ldapUsers", emptyList);
		
		super.doAfterCompose(page, comps);
	}
}
