package com.ixonos.skillnet.web.groupmanagment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Textbox;

import com.ixonos.skillnet.logic.bean.CodeTable;
import com.ixonos.skillnet.logic.bean.GroupAuthority;
import com.ixonos.skillnet.logic.bean.Groups;
import com.ixonos.skillnet.logic.context.SkillnetApplicationContext;
import com.ixonos.skillnet.logic.service.CodeTableService;
import com.ixonos.skillnet.logic.service.GroupAuthorityService;
import com.ixonos.skillnet.logic.service.GroupsService;
import com.ixonos.skillnet.web.codes.ApplicationCodes;

/**
 * 
 * @author katrami
 */
@Service("groupController")
@Scope("prototype")
public class GroupsController extends GenericForwardComposer implements
		ListitemRenderer {

	private static final Logger logger = Logger
			.getLogger(GroupsController.class);

	@Resource
	protected GroupAuthorityService groupAuthorityService = (final GroupAuthorityService) SkillnetApplicationContext
			.getApplicationContext().getBean("groupAuthorityService");
	@Resource
	protected GroupsService groupsService;
	@Resource
	protected CodeTableService codeTableService;
	protected Groups selectedGroup = null;
	protected Checkbox Checkbox;
	protected Datebox date;
	protected Listbox LISTBOX_OF_GROUP;
	protected Listbox LISTBOX_AUTHORITIES;
	protected ListModelList listModelList;
	protected ListModelList listModelListAuthorities;
	protected CodeTable selectedCodeTable = null;
	protected Textbox AuthGroupCode;
	protected Textbox Id;
	protected Textbox Name;
	private final List listCheckboxEnabled = new ArrayList();
	private HashMap<Integer, Boolean> hashMapEnabled;

	@Override
	public void doAfterCompose(final Component comp) throws Exception {
		super.doAfterCompose(comp);
		listModelList = new ListModelList();
		final List<Groups> groups = groupsService.readAll();
		listModelList.addAll(groups);
		LISTBOX_OF_GROUP.setModel(listModelList);
		LISTBOX_OF_GROUP.setItemRenderer(this);

		// list of authorities
		listModelListAuthorities = new ListModelList();
		final List<CodeTable> listOfAuthorities = listOfAuthoritiesFromCodeTable();
		listModelListAuthorities.addAll(listOfAuthorities);
		LISTBOX_AUTHORITIES.setModel(listModelListAuthorities);
		LISTBOX_AUTHORITIES.setItemRenderer(this);
		LISTBOX_OF_GROUP.addEventListener("onSelect", new EventListener() {

			// TODO prepare cheeckbox which will be enabled for specific groups
			@Override
			public void onEvent(final Event e) throws Exception {

				hashMapEnabled = new HashMap<Integer, Boolean>();
				cleanHashMap(hashMapEnabled);
				final int index = LISTBOX_OF_GROUP.getSelectedIndex();
				selectedGroup = (Groups) listModelList.get(index);
				final List<GroupAuthority> authorityListFromDatabase = getAuthoritiesForSelectedGroups(selectedGroup);
				final List<CodeTable> listOfAllAuthorities = listOfAuthoritiesFromCodeTable();

				listCheckboxEnabled.clear();
				findMatchInListForCodeTableAndGroupAuthorityTable(
						authorityListFromDatabase, listOfAllAuthorities);

				listModelListAuthorities.clear();
				listModelListAuthorities.addAll(listOfAllAuthorities);
				Name.setValue(selectedGroup.getGroupName());
				LISTBOX_AUTHORITIES.setModel(listModelListAuthorities);
				return;

			}

			private void cleanHashMap(					final HashMap<Integer,
 Boolean> hashMapEnabled) {
				for (int i = 0; i < listOfAuthoritiesFromCodeTable().size(); i++) {
					hashMapEnabled.put(i, Boolean.FALSE);
				}
			}

			private void findMatchInListForCodeTableAndGroupAuthorityTable(					final List<GroupAuthority> authorityListFromDatabase,
					final List<CodeTable> listOfAllAuhtoritiesFromCodeTable) {
				Integer codeTableId;
				Integer groupAuthorityCodeTableId;

				listCheckboxEnabled.clear();
				for (int idx = 0; idx < listOfAllAuhtoritiesFromCodeTable
						.size(); idx++) {
					final CodeTable currentCodeTable = listOfAllAuhtoritiesFromCodeTable
							.get(idx);

					boolean alreadyFilled = false;

					// finding match between list
					codeTableId = currentCodeTable.getCodeTableId();

					for (int m = 0; m < authorityListFromDatabase.size(); m++) {
						final GroupAuthority currentGroupAuthority = authorityListFromDatabase
								.get(m);
						groupAuthorityCodeTableId = currentGroupAuthority
								.getAuthority().getCodeTableId();

						// will be enabled flag for true, if is match betweew
						// autorities for selected group and all authorities
						if (codeTableId.equals(groupAuthorityCodeTableId)) {
							hashMapEnabled.put(idx, new Boolean(true));
							alreadyFilled = true;
						} else {
							if (!alreadyFilled) {
								hashMapEnabled.put(idx, new Boolean(false));
							}
						}
					}
				}
			}

			private List<GroupAuthority> getAuthoritiesForSelectedGroups(					final Groups selectedGroup) {
				final DetachedCriteria dCriteria2 = DetachedCriteria
						.forClass(GroupAuthority.class);
				dCriteria2.add(Restrictions.eq(
						ApplicationCodes.GROUP_AUTHORITY.GROUP, selectedGroup));
				final List<GroupAuthority> authorityListFromDatabase = groupAuthorityService
						.readByCriteria(dCriteria2);
				return authorityListFromDatabase;
			}
		});
	}

	@Override
	public void render(final Listitem listItem,
 final Object data)
			throws Exception {
		if (data instanceof Groups) {
			listItem.setValue(data);
			final Groups group = (Groups) data;
			new Listcell(group.getGroupName()).setParent(listItem);
		}
		Boolean valueCheckbox;

		if (data != null) {
			if (data instanceof CodeTable) {
				listItem.setValue(data);
				final CodeTable codeTable = (CodeTable) data;
				new Listcell(codeTable.getCode()).setParent(listItem);
				if (hashMapEnabled != null) {
					final int index = listItem.getIndex();
					valueCheckbox = hashMapEnabled.get(index);
					if (valueCheckbox.equals(Boolean.TRUE)) {
						listItem.setSelected(Boolean.TRUE);
					}
				}
			}
		}

	}

	@Transactional
	public void onClick$add(final Event e) {
		final String nameValue = Name.getValue();
		if (nameValue != null) {
			final Groups group = new Groups();
			group.setGroupName(nameValue);
			groupsService.create(group);
			final List<Groups> groups = groupsService.readAll();
			listModelList.clear();
			listModelList.addAll(groups);
			selectedGroup = group;
		}

	}

	@Transactional
	public void onClick$delete(final Event e) {
		if (null != selectedGroup) {
			int index = listModelList.indexOf(selectedGroup);
			try {
				groupsService.delete(selectedGroup);
			} catch (final EntityNotFoundException e1) {
				logger.error("This is harmless as someone else has already deleted this item.");
				e1.printStackTrace();
			}

			listModelList.remove(selectedGroup);
			if (index >= listModelList.size()) {
				index = listModelList.size() - 1;
			}

			if (listModelList.size() > 0) {
				selectedGroup = (Groups) listModelList.get(index);
				LISTBOX_OF_GROUP.setSelectedIndex(index);
				Name.setValue(selectedGroup.getGroupName());
			} else {
				selectedGroup = null;
			}

		}
	}

	@Transactional
	public void onClick$update(final Event e) {
		if (selectedGroup != null) {
			selectedGroup.setGroupName(Name.getValue());
			try {
				groupsService.update(selectedGroup);
			} catch (final EntityNotFoundException e1) {
				e1.printStackTrace();
				final int index = LISTBOX_OF_GROUP.getSelectedIndex();
				listModelList.remove(index);
				alert("'" + selectedGroup.getGroupName() + "' "
						+ Labels.getLabel("group.alert.groupDeleted"));
				if (listModelList.size() > 0) {
					selectedGroup = (Groups) listModelList.get(0);
					LISTBOX_OF_GROUP.setSelectedIndex(0);
					Name.setValue(selectedGroup.getGroupName());
				} else {
					selectedGroup = null;
				}

			}
			final List<Groups> skills = groupsService.readAll();
			listModelList.clear();
			listModelList.addAll(skills);

		}
	}

	private List<CodeTable> listOfAuthoritiesFromCodeTable() {
		final DetachedCriteria dCriteria2 = DetachedCriteria
				.forClass(CodeTable.class);
		dCriteria2.add(Restrictions.eq("groupCode",
				ApplicationCodes.ATUHORITIES.AUTHORITIES_NAME));
		dCriteria2.addOrder(Order.asc("index"));
		return codeTableService.readByCriteria(dCriteria2);
	}
}
