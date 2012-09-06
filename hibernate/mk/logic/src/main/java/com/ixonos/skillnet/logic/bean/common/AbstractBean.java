package com.ixonos.skillnet.logic.bean.common;

import org.apache.log4j.Logger;

import java.lang.reflect.Field;

/**
 * @author stibron
 */
public class AbstractBean {

    /**
     * logger
     */
    private static final Logger logger = Logger.getLogger(AbstractBean.class);

    @Override
    public String toString() {
        
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");

        result.append(this.getClass().getName());
        Field[] fields = this.getClass().getDeclaredFields();

        for(Field field : fields) {
            try {
                result.append(field.getName());
                result.append("=");
                result.append(field.get(this));
            }
            catch (IllegalAccessException ex) {
                logger.error(ex);
            }
            result.append("," + newLine);
        }

        return result.toString();

    }

}
