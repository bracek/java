package com.ixonos.skillnet.logic.service.impl;

import static com.ixonos.skillnet.logic.enumeration.SkillnetConfigParams.MINIMUM_SKILLS;
import static com.ixonos.skillnet.logic.enumeration.SkillnetConfigParams.MINIMUM_SKILLS_DEFAULT;
import static com.ixonos.skillnet.logic.enumeration.SkillnetRole.ROLE_ADMIN;
import static com.ixonos.skillnet.logic.enumeration.SkillnetRole.ROLE_GM;
import static com.ixonos.skillnet.logic.enumeration.SkillnetRole.ROLE_USER;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ixonos.skillnet.logic.bean.Node;
import com.ixonos.skillnet.logic.bean.Skill;
import com.ixonos.skillnet.logic.dao.GenericDAO;
import com.ixonos.skillnet.logic.dao.SkillDAO;
import com.ixonos.skillnet.logic.jdbc.JdbcGenericDAO;
import com.ixonos.skillnet.logic.jdbc.JdbcSkillDAO;
import com.ixonos.skillnet.logic.service.AbstractGenericService;
import com.ixonos.skillnet.logic.service.ConfigService;
import com.ixonos.skillnet.logic.service.SkillService;

/**
 * 
 * @author magurja
 */
@Service("skillService")
public final class SkillServiceImpl extends AbstractGenericService<Skill>
		implements SkillService {

	@Resource
	protected SkillDAO skillDAO;

	@Resource
	protected JdbcSkillDAO jdbcSkillDAO;

	/** The configuration service. */
	@Resource
	protected ConfigService configService;

	@Autowired
	@Override
	public void setServiceDAO(final @Qualifier("skillDAO") GenericDAO genericDAO) {
		super.setServiceDAO(genericDAO);
	}

	@Autowired
	@Override
	public void setJdbcGenericDAO(
			final @Qualifier("jdbcSkillDAO") JdbcGenericDAO jdbcGenericDAO) {
		super.setJdbcGenericDAO(jdbcGenericDAO);
	}

	@Secured({ ROLE_ADMIN, ROLE_USER, ROLE_GM })
	@Transactional(readOnly = true)
	@Override
	public List<Skill> findAlike(final String name) {
		if (jdbcSkillDAO == null) {
			throw new IllegalArgumentException(
					"Given argument (jdbcNodeDAO) is null.");
		}
		return jdbcSkillDAO.findAlike(name);
	}

	@Secured({ ROLE_ADMIN, ROLE_USER, ROLE_GM })
	@Transactional(readOnly = true)
	@Override
	public List<String> getNodePaths(final int skillId, final String rootName) {
		final List<Node> nodes = read(skillId).getNodeCollection();
		final List<String> paths = new ArrayList<String>();

		if (nodes.size() == 0)
			return null;

		for (final Node node : nodes) {
			final String path = getNodePath(node, rootName, "");
			if (path.length() != 0)
				paths.add(path.substring(0, path.length() - 1));
		}
		return paths;
	}

	private String getNodePath(final Node node, final String rootName,
			String path) {
		if (node.getSkill().getName().equals(rootName))
			return path;

		path = node.getSkill().getName() + "/" + path;
		if (node.getParentNode() != null) {
			return getNodePath(node.getParentNode(), rootName, path);
		}
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ixonos.skillnet.logic.service.SkillService#getMinimalCountOfSkills()
	 */
	@Transactional(readOnly = true)
	@Override
	public Integer getMinimalCountOfSkills() {
		final Integer minSkillsParam = configService.getIntProperty(
				MINIMUM_SKILLS, MINIMUM_SKILLS_DEFAULT);
		final Integer skillsCount = skillDAO.getValuableSkillsCount();

		return new BigDecimal(skillsCount)
				.multiply(new BigDecimal(minSkillsParam))
				.divide(new BigDecimal(100), 0, BigDecimal.ROUND_DOWN)
				.intValue();
	}
}
