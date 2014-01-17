package com.ixonos.skillnet.web.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.api.Checkbox;

import com.ixonos.skillnet.logic.bean.Skill;
import com.ixonos.skillnet.logic.bean.Users;
import com.ixonos.skillnet.logic.service.SkillService;
import com.ixonos.skillnet.logic.service.UsersService;

@Service("SkillsController")
@Scope("prototype")
public class SkillsController extends GenericForwardComposer implements
		ListitemRenderer {

	public SkillsController() {
	}

	private static final Logger logger = Logger
			.getLogger(SkillsController.class);

	@Resource
	protected UsersService userService;
	@Resource
	protected SkillService skillService;

	protected Skill selectedSkill = null;
	protected Textbox Name;
	protected Textbox Description;
	protected Checkbox Valuable;
	protected Datebox date;
	protected Listbox list;
	protected ListModelList listModelList;

	@Override
	public void doAfterCompose(final Component comp) throws Exception {
		super.doAfterCompose(comp);
		listModelList = new ListModelList();
		List<Skill> skills = skillService.readAll();
		listModelList.addAll(skills);
		list.setModel(listModelList);
		list.setItemRenderer(this);
		list.addEventListener("onSelect", new EventListener() {
			public void onEvent(final Event e) throws Exception {
				int index = list.getSelectedIndex();
				selectedSkill = (Skill) listModelList.get(index);
				Name.setValue(selectedSkill.getName());
				Description.setValue(selectedSkill.getDescription());
				Valuable.setChecked(selectedSkill.getValuable());
				return;
			}
		});
	}

	@Transactional
	public void onClick$add(final Event e) {
		String nameValue = Name.getValue();
		String descriptionValue = Description.getValue();
		Boolean valuableValue = Valuable.isChecked();
		if (nameValue != null) {
			Skill skill = new Skill();
			skill.setName(nameValue);
			skill.setDescription(descriptionValue);
			skill.setValuable(valuableValue);
			skill.setCreated(new Date());
			DetachedCriteria criteria = DetachedCriteria.forClass(Users.class);
			criteria.add(Restrictions.eq("userId", 5));
			List<Users> user = userService.readByCriteria(criteria);
			skill.setCreatedBy(user.get(0));
			this.skillService.create(skill);
			List<Skill> skills = this.skillService.readAll();
			this.listModelList.clear();
			this.listModelList.addAll(skills);
			this.selectedSkill = skill;
		}
	}

	@Transactional
	public void onClick$update(final Event e) {
		if (selectedSkill != null) {
			selectedSkill.setName(Name.getValue());
			selectedSkill.setDescription(Description.getValue());
			selectedSkill.setValuable(Valuable.isChecked());
			try {
				this.skillService.update(selectedSkill);
			} catch (EntityNotFoundException exception) {
				int index = list.getSelectedIndex();
				listModelList.remove(index);

				alert("'" + selectedSkill.getName() + "' "
						+ Labels.getLabel("skillsEdit.alert.skillDeleted"));
				if (listModelList.size() > 0) {
					selectedSkill = (Skill) listModelList.get(0);
					list.setSelectedIndex(0);
					Name.setValue(selectedSkill.getName());
					Description.setValue(selectedSkill.getDescription());
					Valuable.setChecked(selectedSkill.getValuable());
				} else {
					selectedSkill = null;
				}
			}
			List<Skill> skills = skillService.readAll();
			listModelList.clear();
			listModelList.addAll(skills);

		}
	}

	@Transactional
	public void onClick$delete(final Event e) {
		if (null != selectedSkill) {
			int index = listModelList.indexOf(selectedSkill);
			try {
				this.skillService.delete(selectedSkill);
			} catch (EntityNotFoundException exception) {
				logger
						.error("This is harmless as someone else has already deleted this item.");
			}
			listModelList.remove(selectedSkill);
			if (index >= listModelList.size()) {
				index = listModelList.size() - 1;
			}
			if (listModelList.size() > 0) {
				selectedSkill = (Skill) listModelList.get(index);
				list.setSelectedIndex(index);
				Name.setValue(selectedSkill.getName());
				Description.setValue(selectedSkill.getDescription());
				Valuable.setChecked(selectedSkill.getValuable());
			} else {
				selectedSkill = null;
			}
		}
	}

	protected SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");

	public void render(final Listitem listItem,final  Object data) throws Exception {
		Skill skill = (Skill) data;
		new Listcell(skill.getName()).setParent(listItem);
		new Listcell(skill.getDescription()).setParent(listItem);
		new Listcell(skill.getValuable().toString()).setParent(listItem);
	}
}
