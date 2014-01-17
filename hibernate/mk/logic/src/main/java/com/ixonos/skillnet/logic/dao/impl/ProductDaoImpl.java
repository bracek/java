package com.ixonos.skillnet.logic.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.stereotype.Repository;

import sk.mka.util.MappingGenericQuery;

import com.ixonos.skillnet.logic.bean.Product;
import com.ixonos.skillnet.logic.dao.ProductDao;

/**
 * The Class ProductDaoImpl.
 */
@Repository("productDao")
public class ProductDaoImpl extends SimpleJdbcDaoSupport implements ProductDao {

	// -------------------------------- ATTRS ----------------------------------
	/** The logger. */
	private static final Log logger = LogFactory.getLog(ProductDaoImpl.class);
	// --- QUERIES ----
	private static final String BASE_QUERY = "SELECT id, price, description FROM product ORDER BY id asc";
	private static final String BY_ID_QUERY = "SELECT id, price, description FROM product WHERE id = :id";
	private static final String INSERT = "INSERT INTO product (final id,final  price,final  description) values (nextVal('product_id'), :price, :desc)";
	private static final String UPDATE = "UPDATE product set (final price,final  description) = (:price, :desc) WHERE id = :id";
	private static final String DELETE = "DELETE FROM product WHERE id = :id";
	private GeneralQuery baseQuery, findByIdQuery;
	private SqlUpdate update, insert, delete;

	// ----------------------------- CONSTRUCTORS ------------------------------
	/**
	 * Instantiates a new product manager service impl.
	 * 
	 * @param datasource
	 *            the datasource
	 */
	@Autowired
	public ProductDaoImpl(final @Qualifier("dataSource") DataSource datasource) {
		super.setDataSource(datasource);
	}

	// -------------------------------- METHODS --------------------------------
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.dao.support.DaoSupport#initDao()
	 */
	@Override
	protected void initDao() throws Exception {
		baseQuery = new GeneralQuery(BASE_QUERY);

		final Map<String, Integer> fbiParams = new HashMap<String, Integer>();
		fbiParams.put("id", Types.INTEGER);
		findByIdQuery = new GeneralQuery(BY_ID_QUERY, fbiParams);

		insert = new SqlUpdate(getDataSource(), INSERT);
		insert.declareParameter(new SqlParameter("price", Types.DOUBLE));
		insert.declareParameter(new SqlParameter("desc", Types.VARCHAR));

		update = new SqlUpdate(getDataSource(), UPDATE);
		update.declareParameter(new SqlParameter("id", Types.INTEGER));
		update.declareParameter(new SqlParameter("price", Types.DOUBLE));
		update.declareParameter(new SqlParameter("desc", Types.VARCHAR));

		delete = new SqlUpdate(getDataSource(), DELETE);
		delete.declareParameter(new SqlParameter("id", Types.INTEGER));
	}

	@Override
	public List<Product> getProductList() {
		return baseQuery.execute();
	}

	@Override
	public void updateProduct(final Product prod) {
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", prod.getId());
		paramMap.put("price", prod.getPrice());
		paramMap.put("desc", prod.getDescription());

		final int rowNum = update.updateByNamedParam(paramMap);

		logger.debug("updateProduct(): Product updated, " + rowNum
				+ " affected.");
	}

	@Override
	public void addProduct(final Product prod) {
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("price", prod.getPrice());
		paramMap.put("desc", prod.getDescription());

		final int rowNum = insert.updateByNamedParam(paramMap);

		logger.debug("addProduct(): Product inserted, " + rowNum + " affected.");
	}

	@Override
	public Product findById(final Integer id) {
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);

		final List<Product> result = findByIdQuery
				.executeByNamedParam(paramMap);
		if (result.size() > 0) {
			return result.get(0);
		} else {
			return null;
		}

	}

	@Override
	public void deleteProduct(final Integer id) {
		final Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);

		delete.updateByNamedParam(paramMap);
	}

	// ----------------------------- INNER CLASSES -----------------------------
	/**
	 * The Class GeneralQuery.
	 */
	private class GeneralQuery extends MappingGenericQuery<Product> {

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
				final Map<String, Integer> paramsWithTypes) {
			super(getDataSource(), query, paramsWithTypes);
		}

		@Override
		protected Product mapRow(final ResultSet rs, final int rowNum)
				throws SQLException {
			final Product product = new Product();
			product.setId(rs.getInt("id"));
			product.setPrice(rs.getDouble("price"));
			product.setDescription(rs.getString("description"));
			return product;
		}
	}
}
