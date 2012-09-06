package sk.mka.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

@SuppressWarnings("unchecked")
public abstract class MappingGenericQuery<T> extends MappingSqlQuery {

	// -------------------------------- ATTRS ----------------------------------

	// ----------------------------- CONSTRUCTORS ------------------------------
	/**
	 * Instantiates a new mapping generic query.
	 * 
	 * @param ds the ds
	 * @param query the query
	 */
	public MappingGenericQuery(DataSource ds, String query) {
        super(ds, query);
        compile();
    }
	
	/**
	 * Instantiates a new mapping generic query.
	 * 
	 * @param ds the ds
	 * @param query the query
	 * @param paramsWithTypes the params with types
	 */
	public MappingGenericQuery(DataSource ds, String query, Map<String, Integer> paramsWithTypes) {
        super(ds, query);
        
        if (paramsWithTypes != null) {
        	for (Entry<String, Integer> paramWithType : paramsWithTypes.entrySet()) {
        		declareParameter(new SqlParameter(paramWithType.getKey(), paramWithType.getValue()));
        	}
        }
        
        compile();
    }
	
	/**
	 * Instantiates a new mapping generic query.
	 * 
	 * @param ds the ds
	 * @param query the query
	 * @param paramsTypes the params types
	 */
	public MappingGenericQuery(DataSource ds, String query, Integer[] paramsTypes) {
        super(ds, query);
        
        if (paramsTypes != null) {
        	for (Integer paramType : paramsTypes) {
        		declareParameter(new SqlParameter(paramType));
        	}
        }
        
        compile();
    }
	
	// -------------------------------- METHODS --------------------------------
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet, int)
	 */
    protected abstract T mapRow(ResultSet arg0, int arg1) throws SQLException;
    

    /* (non-Javadoc)
     * @see org.springframework.jdbc.object.SqlQuery#execute()
     */
	@Override
    public List<T> execute() throws DataAccessException {
        return super.execute();
    }
	
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.object.SqlQuery#execute(int, int, java.util.Map)
	 */
	@Override
    public List<T> execute(int p1, int p2, Map context) throws DataAccessException {
        return super.execute(p1, p2, context);
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.object.SqlQuery#execute(int, int)
     */
    @Override
    public List<T> execute(int p1, int p2) throws DataAccessException {
        return super.execute(p1, p2);
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.object.SqlQuery#execute(int, java.util.Map)
     */
    @Override
    public List<T> execute(int p1, Map context) throws DataAccessException {
        return super.execute(p1, context);
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.object.SqlQuery#execute(int)
     */
    @Override
    public List<T> execute(int p1) throws DataAccessException {
        return super.execute(p1);
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.object.SqlQuery#execute(long, java.util.Map)
     */
    @Override
    public List<T> execute(long p1, Map context) throws DataAccessException {
        return super.execute(p1, context);
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.object.SqlQuery#execute(long)
     */
    @Override
    public List<T> execute(long p1) throws DataAccessException {
        return super.execute(p1);
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.object.SqlQuery#execute(java.util.Map)
     */
    @Override
    public List<T> execute(Map context) throws DataAccessException {
        return super.execute(context);
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.object.SqlQuery#execute(java.lang.Object[], java.util.Map)
     */
    @Override
    public List<T> execute(Object[] params, Map context)
            throws DataAccessException {
        return super.execute(params, context);
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.object.SqlQuery#execute(java.lang.Object[])
     */
    @Override
    public List<T> execute(Object[] params) throws DataAccessException {
        return super.execute(params);
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.object.SqlQuery#execute(java.lang.String, java.util.Map)
     */
    @Override
    public List<T> execute(String p1, Map context) throws DataAccessException {
        return super.execute(p1, context);
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.object.SqlQuery#execute(java.lang.String)
     */
    @Override
    public List<T> execute(String p1) throws DataAccessException {
        return super.execute(p1);
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.object.SqlQuery#executeByNamedParam(java.util.Map, java.util.Map)
     */
    @Override
    public List<T> executeByNamedParam(Map paramMap, Map context)
            throws DataAccessException {
        return super.executeByNamedParam(paramMap, context);
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.object.SqlQuery#executeByNamedParam(java.util.Map)
     */
    @Override
    public List<T> executeByNamedParam(Map paramMap) throws DataAccessException {
        return super.executeByNamedParam(paramMap);
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.object.SqlQuery#findObject(int, int, java.util.Map)
     */
    @Override
    public T findObject(int p1, int p2, Map context)
            throws DataAccessException {
        return (T) super.findObject(p1, p2, context);
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.object.SqlQuery#findObject(int, int)
     */
    @Override
    public T findObject(int p1, int p2) throws DataAccessException {
        return (T) super.findObject(p1, p2);
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.object.SqlQuery#findObject(int, java.util.Map)
     */
    @Override
    public T findObject(int p1, Map context) throws DataAccessException {
        return (T) super.findObject(p1, context);
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.object.SqlQuery#findObject(int)
     */
    @Override
    public T findObject(int p1) throws DataAccessException {
        return (T) super.findObject(p1);
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.object.SqlQuery#findObject(long, java.util.Map)
     */
    @Override
    public T findObject(long p1, Map context) throws DataAccessException {
        return (T) super.findObject(p1, context);
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.object.SqlQuery#findObject(long)
     */
    @Override
    public T findObject(long p1) throws DataAccessException {
        return (T) super.findObject(p1);
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.object.SqlQuery#findObject(java.lang.Object[], java.util.Map)
     */
    @Override
    public T findObject(Object[] params, Map context)
            throws DataAccessException {
        return (T) super.findObject(params, context);
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.object.SqlQuery#findObject(java.lang.Object[])
     */
    @Override
    public T findObject(Object[] params) throws DataAccessException {
        return (T) super.findObject(params);
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.object.SqlQuery#findObject(java.lang.String, java.util.Map)
     */
    @Override
    public T findObject(String p1, Map context) throws DataAccessException {
        return (T) super.findObject(p1, context);
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.object.SqlQuery#findObject(java.lang.String)
     */
    @Override
    public T findObject(String p1) throws DataAccessException {
        return (T) super.findObject(p1);
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.object.SqlQuery#findObjectByNamedParam(java.util.Map, java.util.Map)
     */
    @Override
    public T findObjectByNamedParam(Map paramMap, Map context)
            throws DataAccessException {
        return (T) super.findObjectByNamedParam(paramMap, context);
    }

    /* (non-Javadoc)
     * @see org.springframework.jdbc.object.SqlQuery#findObjectByNamedParam(java.util.Map)
     */
    @Override
    public T findObjectByNamedParam(Map paramMap)
            throws DataAccessException {
        return (T) super.findObjectByNamedParam(paramMap);
    }
}
