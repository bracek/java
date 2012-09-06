package com.ixonos.skillnet.web.skills.edit;

import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import com.ixonos.skillnet.logic.bean.Skill;
import com.ixonos.skillnet.web.skills.edit.ImportSkillsWindow.ImportSkillsListItem;

public class SkillListitemRenderer implements ListitemRenderer {

	public void render(Listitem item, Object data) throws Exception {
		Skill skill = (Skill) data;
		ImportSkillsListItem impItem = (ImportSkillsListItem) ((Listitem) getParentComponent(
				item, Listitem.class)).getValue();

		Skill actSkill = impItem.getActSkill();
		if(actSkill != null)
			item.setSelected(skill.getSkillId().equals(actSkill.getSkillId()));
		item.setValue(data);
		item.setLabel(skill.getName());
	}

	private Component getParentComponent(Component component, Class<?> clazz)
			throws Exception {
		int index = 0;
		while ((component != null)
				&& !clazz.isInstance((component = component.getParent()))) {
			index++;
			if (index == 50) {
				throw new Exception("No parent " + clazz + " found!");
			}
		}
		if (component == null) {
			throw new Exception("No parent " + clazz + " found!");
		}
		return component;
	}
}
