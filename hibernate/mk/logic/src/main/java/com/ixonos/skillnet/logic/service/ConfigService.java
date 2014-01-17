package com.ixonos.skillnet.logic.service;

import java.util.Map;

import com.ixonos.skillnet.logic.bean.Config;

/**
 * @author stibron
 *
 */
public interface ConfigService {

	 void saveProperty(final String property,final  Object value,final  String description);
	
	 Map<String, Config> getProperties();
	
	 String getStringProperty(final String property);
	
	 String getStringProperty(final String property,final  String defaultValue);
	
	 Integer getIntProperty(final String property);
	
	 Integer getIntProperty(final String property,final  Integer defaultValue);
}
