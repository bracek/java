package com.ixonos.skillnet.web.skills.edit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zkplus.databind.AnnotateDataBinderInit;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.ListModelList;

import com.ixonos.skillnet.logic.bean.Skill;
import com.ixonos.skillnet.logic.service.SkillService;
import com.ixonos.skillnet.web.controllers.SkillsComparator;

public class ImportSkillsInitiator extends AnnotateDataBinderInit {

	@Resource
	protected SkillService skillService = (SkillService) SpringUtil
			.getApplicationContext().getBean("skillService");

	@Override
	public void doAfterCompose(final Page page,
 final Component[] comps)
			throws Exception {
		page.setVariable("skillList", new ListModelList(
				getOrderedListOfSkills()));
		page.setVariable("mergedMap", new HashMap<String, Skill>());
		page.setVariable("lmlModel", new ListModelList());
		page.setVariable("lmrModel", new ListModelList());
		page.setVariable("lolModel", new ListModelList());
		page.setVariable("lorModel", new ListModelList());

		super.doAfterCompose(page, comps);
	}

	@SuppressWarnings("unchecked")
	private List<Skill> getOrderedListOfSkills() {
		final List<Skill> skills = skillService.readAll();
		final Skill[] skillsArray = skills.toArray(new Skill[skills.size()]);
		Arrays.sort(skillsArray, new SkillsComparator(true, "Name"));
		return new ArrayList<Skill>(Arrays.asList(skillsArray));
	}

}
