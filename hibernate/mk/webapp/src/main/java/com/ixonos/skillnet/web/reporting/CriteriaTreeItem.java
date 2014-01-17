package com.ixonos.skillnet.web.reporting;

import org.hibernate.criterion.Criterion;

/**
 * Criteria tree item.
 *  
 * @author molnala
 *
 */
public interface CriteriaTreeItem {
    
    /**
     * Returns the Criterion representation of the object.
     * 
     */
     Criterion toCriterion();
    
    /**
     * Returns the String representation of the operator.
     * 
     */
     String getOperatorAsString();
    
    /**
     * Returns the unique id.
     */
     int getId();
    
    /**
     * Returns String representation including the sub items(criteria).
     */
     String toStringIncludingSubCritera();
}
