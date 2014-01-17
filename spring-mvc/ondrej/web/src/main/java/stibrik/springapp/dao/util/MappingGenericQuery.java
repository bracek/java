package stibrik.springapp.dao.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

/**
 * The Helper class MappingGenericQuery.
 * 
 * @author Ondrej Stibrik
 */
@SuppressWarnings("unchecked")
public abstract class MappingGenericQuery<T> extends MappingSqlQuery {

	// -------------------------------- ATTRS ----------------------------------

	// ----------------------------- CONSTRUCTORS ------------------------------
	/**
	 * Instantiates a new mapping generic query.
	 * 
	 * @param ds
	 *            the ds
	 * @param query
	 *            the query
	 */
	public MappingGenericQuery(final DataSource ds, final String query) {
		super(ds, query);
		compile();
	}

	/**
	 * Instantiates a new mapping generic query.
	 * 
	 * @param ds
	 *            the ds
	 * @param query
	 *            the query
	 * @param paramsWithTypes
	 *            the params with types
	 */
	public MappingGenericQuery(final DataSource ds, final String query,
			final Map<String, Integer> paramsWithTypes) {
		super(ds, query);

		if (paramsWithTypes != null) {
			for (final Entry<String, Integer> paramWithType : paramsWithTypes
					.entrySet()) {
				declareParameter(new SqlParameter(paramWithType.getKey(),
						paramWithType.getValue()));
			}
		}

		compile();
	}

	/**
	 * Instantiates a new mapping generic query.
	 * 
	 * @param ds
	 *            the ds
	 * @param query
	 *            the query
	 * @param paramsTypes
	 *            the params types
	 */
	public MappingGenericQuery(final DataSource ds, final String query,
			final Integer[] paramsTypes) {
		super(ds, query);

		if (paramsTypes != null) {
			for (final Integer paramType : paramsTypes) {
				declareParameter(new SqlParameter(paramType));
			}
		}

		compile();
	}

	// -------------------------------- METHODS --------------------------------
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.jdbc.object.MappingSqlQuery#mapRow(java.sql.ResultSet
	 * , int)
	 */
	@Override
	protected abstract T mapRow(final ResultSet arg0, final int arg1)
			throws SQLException;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.object.SqlQuery#execute()
	 */
	@Override
	public List<T> execute() throws DataAccessException {
		return super.execute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.object.SqlQuery#execute(int, int,
	 * java.util.Map)
	 */
	@Override
	public List<T> execute(final int p1, final int p2, final Map context)
			throws DataAccessException {
		return super.execute(p1, p2, context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.object.SqlQuery#execute(int, int)
	 */
	@Override
	public List<T> execute(final int p1, final int p2)
			throws DataAccessException {
		return super.execute(p1, p2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.object.SqlQuery#execute(int, java.util.Map)
	 */
	@Override
	public List<T> execute(final int p1, final Map context)
			throws DataAccessException {
		return super.execute(p1, context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.object.SqlQuery#execute(int)
	 */
	@Override
	public List<T> execute(final int p1) throws DataAccessException {
		return super.execute(p1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.object.SqlQuery#execute(long,
	 * java.util.Map)
	 */
	@Override
	public List<T> execute(final long p1, final Map context)
			throws DataAccessException {
		return super.execute(p1, context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.object.SqlQuery#execute(long)
	 */
	@Override
	public List<T> execute(final long p1) throws DataAccessException {
		return super.execute(p1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.object.SqlQuery#execute(java.util.Map)
	 */
	@Override
	public List<T> execute(final Map context) throws DataAccessException {
		return super.execute(context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.object.SqlQuery#execute(java.lang.Object[],
	 * java.util.Map)
	 */
	@Override
	public List<T> execute(final Object[] params, final Map context)
			throws DataAccessException {
		return super.execute(params, context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.object.SqlQuery#execute(java.lang.Object[])
	 */
	@Override
	public List<T> execute(final Object[] params) throws DataAccessException {
		return super.execute(params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.object.SqlQuery#execute(java.lang.String,
	 * java.util.Map)
	 */
	@Override
	public List<T> execute(final String p1, final Map context)
			throws DataAccessException {
		return super.execute(p1, context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.object.SqlQuery#execute(java.lang.String)
	 */
	@Override
	public List<T> execute(final String p1) throws DataAccessException {
		return super.execute(p1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.jdbc.object.SqlQuery#executeByNamedParam(java.util
	 * .Map, java.util.Map)
	 */
	@Override
	public List<T> executeByNamedParam(final Map paramMap, final Map context)
			throws DataAccessException {
		return super.executeByNamedParam(paramMap, context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.jdbc.object.SqlQuery#executeByNamedParam(java.util
	 * .Map)
	 */
	@Override
	public List<T> executeByNamedParam(final Map paramMap)
			throws DataAccessException {
		return super.executeByNamedParam(paramMap);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.object.SqlQuery#findObject(int, int,
	 * java.util.Map)
	 */
	@Override
	public T findObject(final int p1, final int p2, final Map context)
			throws DataAccessException {
		return (T) super.findObject(p1, p2, context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.object.SqlQuery#findObject(int, int)
	 */
	@Override
	public T findObject(final int p1, final int p2) throws DataAccessException {
		return (T) super.findObject(p1, p2);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.object.SqlQuery#findObject(int,
	 * java.util.Map)
	 */
	@Override
	public T findObject(final int p1, final Map context)
			throws DataAccessException {
		return (T) super.findObject(p1, context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.object.SqlQuery#findObject(int)
	 */
	@Override
	public T findObject(final int p1) throws DataAccessException {
		return (T) super.findObject(p1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.object.SqlQuery#findObject(long,
	 * java.util.Map)
	 */
	@Override
	public T findObject(final long p1, final Map context)
			throws DataAccessException {
		return (T) super.findObject(p1, context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.jdbc.object.SqlQuery#findObject(long)
	 */
	@Override
	public T findObject(final long p1) throws DataAccessException {
		return (T) super.findObject(p1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.jdbc.object.SqlQuery#findObject(java.lang.Object[],
	 * java.util.Map)
	 */
	@Override
	public T findObject(final Object[] params, final Map context)
			throws DataAccessException {
		return (T) super.findObject(params, context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.jdbc.object.SqlQuery#findObject(java.lang.Object[])
	 */
	@Override
	public T findObject(final Object[] params) throws DataAccessException {
		return (T) super.findObject(params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.jdbc.object.SqlQuery#findObject(java.lang.String,
	 * java.util.Map)
	 */
	@Override
	public T findObject(final String p1, final Map context)
			throws DataAccessException {
		return (T) super.findObject(p1, context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.jdbc.object.SqlQuery#findObject(java.lang.String)
	 */
	@Override
	public T findObject(final String p1) throws DataAccessException {
		return (T) super.findObject(p1);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.jdbc.object.SqlQuery#findObjectByNamedParam(java.
	 * util.Map, java.util.Map)
	 */
	@Override
	public T findObjectByNamedParam(final Map paramMap, final Map context)
			throws DataAccessException {
		return (T) super.findObjectByNamedParam(paramMap, context);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.jdbc.object.SqlQuery#findObjectByNamedParam(java.
	 * util.Map)
	 */
	@Override
	public T findObjectByNamedParam(final Map paramMap)
			throws DataAccessException {
		return (T) super.findObjectByNamedParam(paramMap);
	}
}
