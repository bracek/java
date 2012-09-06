package com.ixonos.skillnet.logic.service;

import java.util.Map;

import com.ixonos.skillnet.logic.bean.Config;

/**
 * @author stibron
 *
 */
public interface ConfigService {

	public void saveProperty(String property, Object value, String description);
	
	public Map<String, Config> getProperties();
	
	public String getStringProperty(String property);
	
	public String getStringProperty(String property, String defaultValue);
	
	public Integer getIntProperty(String property);
	
	public Integer getIntProperty(String property, Integer defaultValue);
}
