package com.ixonos.skillnet.logic.jdbc.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.stereotype.Repository;

import com.ixonos.skillnet.logic.bean.Skill;
import com.ixonos.skillnet.logic.jdbc.JdbcSkillDAO;
import com.ixonos.skillnet.logic.jdbc.util.MappingGenericQuery;

@Repository("jdbcSkillDAO")
public class JdbcSkillDAOImpl extends SimpleJdbcDaoSupport implements
		JdbcSkillDAO {

	private static final String LIST_OF_IDS_QUERY = "SELECT skill_id FROM skill";
	private static final String LIKE_NAME_QUERY = "SELECT * FROM skill WHERE name ~* :name";
	private static final String DELETE = "DELETE FROM skill WHERE skill_id = :id";

	private static Pattern escaper = Pattern.compile("([^a-zA-z0-9])");

	private GeneralQuery findByIdQuery;
	private GeneralQuery likeNameQuery;
	private SqlUpdate delete;

	// ----------------------------- CONSTRUCTORS ------------------------------
	/**
	 * Instantiates a new product manager service impl.
	 * 
	 * @param datasource
	 *            the datasource
	 */
	@Autowired
	public JdbcSkillDAOImpl(@Qualifier("dataSourceJdbc") DataSource datasource) {
		super.setDataSource(datasource);
	}

	@Override
	protected void initDao() throws Exception {
		delete = new SqlUpdate(getDataSource(), DELETE);
		delete.declareParameter(new SqlParameter("id", Types.INTEGER));

		Map<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("name", Types.VARCHAR);
		likeNameQuery = new GeneralQuery(LIKE_NAME_QUERY, paramMap);

		findByIdQuery = new GeneralQuery(LIST_OF_IDS_QUERY);

	}

	@Override
	public List<Skill> findAlike(String name) {
		Map<String, Object> argMap = new HashMap<String, Object>();
		argMap.put("name", ".*" + escapeRegex(name) + ".*");
		return likeNameQuery.executeByNamedParam(argMap);
	}

	private String escapeRegex(String str) {
		return escaper.matcher(str).replaceAll("\\\\$1");
	}

	@Override
	public void jdbcDelete(Integer id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		delete.updateByNamedParam(paramMap);
	}

	@Override
	public List<Skill> jdbcGetListOfIds() {
		return findByIdQuery.execute();
	}

	// ----------------------------- INNER CLASSES -----------------------------
	/**
	 * The Class GeneralQuery.
	 */
	private class GeneralQuery extends MappingGenericQuery<Skill> {

		/**
		 * Instantiates a new general query.
		 * 
		 * @param query
		 *            the query
		 */
		public GeneralQuery(String query) {
			super(getDataSource(), query);
		}

		/**
		 * Instantiates a new general query.
		 * 
		 * @param query
		 *            the query
		 * @param paramsWithTypes
		 *            the params with types
		 */
		public GeneralQuery(String query, Map<String, Integer> paramsWithTypes) {
			super(getDataSource(), query, paramsWithTypes);
			System.out.println(getParsedSql().toString());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see stibrik.springapp.dao.util.MappingGenericQuery#mapRow(ResultSet,
		 * int)
		 */
		protected Skill mapRow(ResultSet rs, int rowNum) throws SQLException {
			Skill skill = new Skill();
			skill.setSkillId(new Integer(rs.getInt("skill_id")));
			skill.setName(rs.getString("name"));
			skill.setValuable(rs.getBoolean("valuable"));
			return skill;
		}
	}

}
