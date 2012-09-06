package com.ixonos.skillnet.web.reporting;


/**
 * Abstract condition tree item.
 * 
 * @author molnala
 *
 */
public abstract class AbstractCriteriaTreeItem implements CriteriaTreeItem {

    // possible operators
    public static final int OP_EQ = 0;
    public static final int OP_GE = 1;
    public static final int OP_LE = 2;
    public static final int OP_GT = 3;
    public static final int OP_LT = 4;
    public static final int OP_IN = 5;
    public static final int OP_LIKE = 6;
    // TODO NULL, NOT NULL, etc.
    public static final int OP_AND = 10;
    public static final int OP_OR = 11;
    
    /**
     * Last assigned index - ensures the unique indexes.
     */
    private static int lastIndex = 1;
    
    /**
     * Unique identificator
     */
    protected int id;
    
    /**
     * Criteria operator. 
     */
    protected int operator;
    
    /**
     * Sets the unique id.
     */
    public AbstractCriteriaTreeItem() {
        id = lastIndex++;
    }

    @Override
    public int getId() {
        return id;
    }
    
    /**
     * Returns the operator
     */
    public int getOperator() {
        return operator;
    }
    
    @Override
    public String getOperatorAsString() {
        return getOperatorAsString(operator);
    }
    
    /**
     * Returns String representation of the given operator.
     * 
     * @param operator
     * @return
     */
    public static String getOperatorAsString(int operator) {
        switch (operator) {
        case AbstractCriteriaTreeItem.OP_EQ:
            return "=";
        case AbstractCriteriaTreeItem.OP_GE:
            return ">=";
        case AbstractCriteriaTreeItem.OP_LE:
            return "<=";
        case AbstractCriteriaTreeItem.OP_GT:
            return ">";
        case AbstractCriteriaTreeItem.OP_LT:
            return "<";
        case AbstractCriteriaTreeItem.OP_IN:
            return "IN";
        case AbstractCriteriaTreeItem.OP_LIKE:
            return "LIKE";
        case AbstractCriteriaTreeItem.OP_AND:
            return "AND";
        case AbstractCriteriaTreeItem.OP_OR:
            return "OR";
        default: 
            return "<unknown operator>";
    }
    }

}
