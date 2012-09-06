package com.ixonos.skillnet.web.reporting;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 * Criteria tree leaf.
 * 
 * @author molnala
 *
 */
public class CriteriaTreeLeaf extends AbstractCriteriaTreeItem {
    
    /**
     * Criterion variable
     */
    private String variable;
        
    /**
     * Criterion value.
     */
    private Object[] value;
    
    /**
     * Constructor.
     * 
     * @param variable criteria variable
     * @param operator criteria operator
     * @param value criteria value
     */
    public CriteriaTreeLeaf(String variable, int operator, Object[] value) {
        super();
        setVariable(variable);
        setOperator(operator);
        setValue(value);
    }
    
    /**
     * Constructor.
     * 
     * @param variable criteria variable
     * @param operator criteria operator
     * @param value criteria value
     */
    public CriteriaTreeLeaf(String variable, int operator, Object value) {
        this(variable, operator, new Object[]{value});
    }
    
    /**
     * variable getter
     */
    public String getVariable() {
        return variable;
    }

    /**
     * variable setter
     */
    public void setVariable(String variable) {
        this.variable = variable;
    }


    /**
     * operator setter
     */
    public void setOperator(int operator) {
        if (OP_AND==operator || OP_OR==operator) {
            throw new IllegalArgumentException("AND and OR operators are not supported in tree leaves!");
        }        
        this.operator = operator;
    }

    /**
     * value getter
     */
    public Object[] getValues() {
        return value;
    }
    
    /**
     * value getter
     */
    public Object getValue() {
        if (value!=null && value.length>0) {
            return value[0];
        }        
        return null;
    }

    /**
     * value setter
     */
    public void setValue(Object[] value) {
        this.value = value;
    }
    
    /**
     * value setter
     */
    public void setValue(Object value) {
        this.value = new Object[] {value};
    }
    
    /**
     * 
     * @return
     */
    public String getValueAsString() {
        StringBuilder strBuilder = new StringBuilder();
        if (value==null || value.length==0 || value[0]==null) {
            strBuilder.append("null");
        }
        else if (value.length==1) {
            strBuilder.append("'").append(value[0].toString()).append("'");
        }
        else {            
            strBuilder.append("(");
            for (Object val : value) {
                if (strBuilder.length()>1) {
                    strBuilder.append(", ");
                }
                strBuilder.append("'").append(val.toString()).append("'");
            }
            strBuilder.append(")");            
        }
        return strBuilder.toString();
    }
    
    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(id).append(": ");
        strBuilder.append(variable).append(" ");
        strBuilder.append(getOperatorAsString()).append(" ");
        strBuilder.append(getValueAsString());        
        return strBuilder.toString();
    }
    
    @Override
    public Criterion toCriterion() {
        switch (operator) {
            case OP_EQ:
                return Restrictions.eq(variable, getValue());
            case OP_GE:
                return Restrictions.ge(variable, getValue());
            case OP_LE:
                return Restrictions.le(variable, getValue());
            case OP_GT:
                return Restrictions.gt(variable, getValue());
            case OP_LT:
                return Restrictions.lt(variable, getValue());
            case OP_IN:
                return Restrictions.in(variable, getValues());
            case OP_LIKE:
                return Restrictions.like(variable, "%" + getValue() + "%");
            default: return null;
        }        
    }
    
    @Override
    public String toStringIncludingSubCritera() {
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(variable).append(" ");
        strBuilder.append(getOperatorAsString()).append(" ");
        strBuilder.append(getValueAsString());           
        return strBuilder.toString();
    }

}
