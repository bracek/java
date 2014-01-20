package com.ixonos.skillnet.logic.service.impl;

import static com.ixonos.skillnet.logic.enumeration.SkillnetRole.ROLE_ADMIN;
import static com.ixonos.skillnet.logic.enumeration.SkillnetRole.ROLE_GM;
import static com.ixonos.skillnet.logic.enumeration.SkillnetRole.ROLE_USER;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ixonos.skillnet.logic.bean.Config;
import com.ixonos.skillnet.logic.dao.GenericDAO;
import com.ixonos.skillnet.logic.service.AbstractGenericService;
import com.ixonos.skillnet.logic.service.ConfigService;

/**
 * @author stibron
 *
 */
@Service("configService")
public final class ConfigServiceImpl extends AbstractGenericService<Config> implements ConfigService {

	/**
	 * Log4j logger for this class
	 */
	private static Logger logger = Logger.getLogger(ConfigServiceImpl.class);
	
	/* (non-Javadoc)
	 * @see com.ixonos.skillnet.logic.service.AbstractGenericService#setServiceDAO(com.ixonos.skillnet.logic.dao.GenericDAO)
	 */
	@Autowired
    @Override
    public void setServiceDAO(final @Qualifier("configDAO") GenericDAO<Config> genericDAO) {
        super.setServiceDAO(genericDAO);
    }
	
	/* (non-Javadoc)
	 * @see com.ixonos.skillnet.logic.service.ConfigService#getIntProperty(java.lang.String)
	 */
	@Secured({ROLE_ADMIN, ROLE_USER, ROLE_GM})
	@Transactional(readOnly = true)
	@Override
	public Integer getIntProperty(final String property) {
		return getIntProperty(property, null);
	}

	/* (non-Javadoc)
	 * @see com.ixonos.skillnet.logic.service.ConfigService#getIntProperty(java.lang.String, java.lang.Integer)
	 */
	@Secured({ROLE_ADMIN, ROLE_USER, ROLE_GM})
	@Transactional(readOnly = true)
	@Override
	public Integer getIntProperty(final String property,
final  Integer defaultValue) {
		try {
			Config c = read(property);
			return Integer.parseInt(c.getValue());
			
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/* (non-Javadoc)
	 * @see com.ixonos.skillnet.logic.service.ConfigService#getProperties()
	 */
	@Secured({ROLE_ADMIN, ROLE_USER, ROLE_GM})
	@Transactional(readOnly = true)
	@Override
	public Map<String, Config> getProperties() {
		List<Config> props = readAll();
		Map<String, Config> result = new HashMap<String, Config>();
		for (Config c : props) {
			result.put(c.getProperty(), c);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.ixonos.skillnet.logic.service.ConfigService#getStringProperty(java.lang.String)
	 */
	@Secured({ROLE_ADMIN, ROLE_USER, ROLE_GM})
	@Transactional(readOnly = true)
	@Override
	public String getStringProperty(final String property) {
		return getStringProperty(property, null);
	}

	/* (non-Javadoc)
	 * @see com.ixonos.skillnet.logic.service.ConfigService#getStringProperty(java.lang.String, java.lang.String)
	 */
	@Secured({ROLE_ADMIN, ROLE_USER, ROLE_GM})
	@Transactional(readOnly = true)
	@Override
	public String getStringProperty(final String property,
final  String defaultValue) {
		try {
			String result = read(property).getValue();
			if (result != null && !result.isEmpty()) {
				return result;
			} else {
				return defaultValue;
			}
			
		} catch (Exception e) {
			return defaultValue;
		}
	}

	/* (non-Javadoc)
	 * @see com.ixonos.skillnet.logic.service.ConfigService#saveProperty(java.lang.String, java.lang.Object, java.lang.String)
	 */
    @Secured(ROLE_ADMIN)
	@Transactional
	@Override
	public void saveProperty(final String property,
final  Object value,
final  String description) {
		Config c = null;
		try {
			c = find(property);
		} catch (Exception e) {
			logger.debug("#saveProperty: Property '" + property + "' not found in DB.");
		}
		
		if (c == null) {
			create(new Config(property, value == null ? null : value.toString(), description));
			
		} else {
			c.setValue(value == null ? null : value.toString());
			c.setDescription(description);
			update(c);
		}
	}

}
