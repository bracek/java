package com.ixonos.skillnet.web.skills.edit;

import com.ixonos.skillnet.logic.bean.Skill;
import com.ixonos.skillnet.web.skills.edit.ImportSkillsWindow.ImportSkillsListItem;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

public class SkillListitemRenderer implements ListitemRenderer {

    @Override
    public void render(final Listitem item,
                       final Object data) throws Exception {
        final Skill skill = (Skill) data;
        final ImportSkillsListItem impItem = (ImportSkillsListItem) ((Listitem) getParentComponent(
                item, Listitem.class)).getValue();

        final Skill actSkill = impItem.getActSkill();
        if (actSkill != null)
            item.setSelected(skill.getSkillId().equals(actSkill.getSkillId()));
        item.setValue(data);
        item.setLabel(skill.getName());
    }

    private Component getParentComponent(Component component,
                                         final Class<?> clazz) throws Exception {
        int index = 0;
        while (component != null
                && !clazz.isInstance(component = component.getParent())) {
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
