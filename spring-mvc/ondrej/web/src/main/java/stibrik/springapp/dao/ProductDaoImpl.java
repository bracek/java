package stibrik.springapp.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.jdbc.object.SqlUpdate;
import org.springframework.stereotype.Repository;
import stibrik.springapp.bean.Product;
import stibrik.springapp.dao.util.MappingGenericQuery;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

private static final String INSERT = "INSERT INTO product (final id,final  price,final  description) values (nextVal('product_seq'), :price, :desc)";
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
public ProductDaoImpl(
final @Qualifier("dataSourceTarget") DataSource datasource) {
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

Map<String, Integer> fbiParams = new HashMap<String, Integer>();
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

/*
 * (non-Javadoc)
 * 
 * @see stibrik.springapp.dao.ProductDao#getProductList()
 */
public List<Product> getProductList() {
return baseQuery.execute();
}

/*
 * (non-Javadoc)
 * 
 * @see
 * stibrik.springapp.dao.ProductDao#updateProduct(stibrik.springapp.bean
 * .Product)
 */
public void updateProduct(final Product prod) {
Map<String, Object> paramMap = new HashMap<String, Object>();
paramMap.put("id", prod.getId());
paramMap.put("price", prod.getPrice());
paramMap.put("desc", prod.getDescription());

int rowNum = update.updateByNamedParam(paramMap);

logger.debug("updateProduct(): Product updated, " + rowNum
+ " affected.");
}

/*
 * (non-Javadoc)
 * 
 * @see
 * stibrik.springapp.dao.ProductDao#addProduct(stibrik.springapp.bean.Product
 * )
 */
public void addProduct(final Product prod) {
Map<String, Object> paramMap = new HashMap<String, Object>();
paramMap.put("price", prod.getPrice());
paramMap.put("desc", prod.getDescription());

int rowNum = insert.updateByNamedParam(paramMap);

logger.debug("addProduct(): Product inserted, " + rowNum + " affected.");
}

/*
 * (non-Javadoc)
 * 
 * @see stibrik.springapp.dao.ProductDao#findById(java.lang.Integer)
 */
public Product findById(final Integer id) {
Map<String, Object> paramMap = new HashMap<String, Object>();
paramMap.put("id", id);

List<Product> result = findByIdQuery.executeByNamedParam(paramMap);
if (result.size() > 0) {
return result.get(0);
} else {
return null;
}

}

/*
 * (non-Javadoc)
 * 
 * @see stibrik.springapp.dao.ProductDao#deleteProduct(java.lang.Integer)
 */
public void deleteProduct(final Integer id) {
Map<String, Object> paramMap = new HashMap<String, Object>();
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

/*
 * (non-Javadoc)
 * 
 * @see stibrik.springapp.dao.util.MappingGenericQuery#mapRow(ResultSet,
 * int)
 */
@Override
protected Product mapRow(final ResultSet rs, final int rowNum)
throws SQLException {
Product product = new Product();
product.setId(rs.getInt("id"));
product.setPrice(rs.getDouble("price"));
product.setDescription(rs.getString("description"));
return product;
}
}
}
