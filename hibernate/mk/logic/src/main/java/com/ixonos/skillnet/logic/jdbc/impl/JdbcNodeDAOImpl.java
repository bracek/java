package com.ixonos.skillnet.logic.jdbc.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.stereotype.Repository;

import com.ixonos.skillnet.logic.bean.Node;
import com.ixonos.skillnet.logic.jdbc.JdbcNodeDAO;
import com.ixonos.skillnet.logic.jdbc.util.MappingGenericQuery;

@Repository("jdbcNodeDAO")
public class JdbcNodeDAOImpl extends SimpleJdbcDaoSupport implements
		JdbcNodeDAO {

	private static final String BY_ID_QUERY = "SELECT node_id FROM node";
	private static final String CHILD_COUNT_QUERY = "SELECT count(final node_id) FROM node WHERE parent_node_id= :parent";
	private static final String DELETE = "DELETE FROM node_id WHERE id = :id";

	private GeneralQuery findByIdQuery;
	private SqlUpdate delete;

	// ----------------------------- CONSTRUCTORS ------------------------------
	/**
	 * Instantiates a new product manager service impl.
	 * 
	 * @param datasource
	 *            the datasource
	 */
	@Autowired
	public JdbcNodeDAOImpl(			final @Qualifier("dataSourceJdbc") DataSource datasource) {
		super.setDataSource(datasource);
	}

	@Override
	protected void initDao() throws Exception {
		delete = new SqlUpdate(getDataSource(), DELETE);
		delete.declareParameter(new SqlParameter("id", Types.INTEGER));
	}

	@Override
	public void jdbcDelete(final Integer id) {
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		delete.updateByNamedParam(paramMap);
	}

	@Override
	public List<Node> jdbcGetListOfIds() {
		findByIdQuery = new GeneralQuery(BY_ID_QUERY);
		return findByIdQuery.execute();
	}

	@Override
	public int getChildCount(final Node parent) {
		final SimpleJdbcTemplate template = new SimpleJdbcTemplate(
				getDataSource());
		final Map<String, Integer> fbiParams = new HashMap<String, Integer>();
		fbiParams.put("parent", parent.getNodeId());
		return template.queryForInt(CHILD_COUNT_QUERY, fbiParams);
	}

	// ----------------------------- INNER CLASSES -----------------------------
	/**
	 * The Class GeneralQuery.
	 */
	private class GeneralQuery extends MappingGenericQuery<Node> {

		/**
		 * Instantiates a new general query.
		 * 
		 * @param query
		 *            the query
		 */
		public GeneralQuery(final String query) {
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
		public GeneralQuery(final String query,
				final Map<String,
 Integer> paramsWithTypes) {
			super(getDataSource(), query, paramsWithTypes);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see stibrik.springapp.dao.util.MappingGenericQuery#mapRow(ResultSet,
		 * int)
		 */
		@Override
		protected Node mapRow(final ResultSet rs,
 final int rowNum)
				throws SQLException {
			final Node node = new Node();
			node.setNodeId(new Integer(rs.getInt("node_id")));
			return node;
		}
	}

}
