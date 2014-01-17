package com.ixonos.skillnet.logic.service;

import java.util.Map;

import com.ixonos.skillnet.logic.bean.Config;

/**
 * @author stibron
 *
 */
public interface ConfigService {

	public void saveProperty(final String property,final  Object value,final  String description);
	
	public Map<String, Config> getProperties();
	
	public String getStringProperty(final String property);
	
	public String getStringProperty(final String property,final  String defaultValue);
	
	public Integer getIntProperty(final String property);
	
	public Integer getIntProperty(final String property,final  Integer defaultValue);
}
