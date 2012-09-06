package com.ixonos.skillnet.web.reporting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import com.ixonos.skillnet.logic.bean.PracticumView;

/**
 * Condition tree node.
 * 
 * @author molnala
 *
 */
public class CriteriaTreeNode extends AbstractCriteriaTreeItem {   
        
    /**
     * List of children.
     */
    private List<CriteriaTreeItem> children = new ArrayList<CriteriaTreeItem>();

    /**
     * Constructor.
     * 
     * @param operator operator
     */
    public CriteriaTreeNode(int operator) {
        super();
        this.operator = operator;
    }

    /**
     * Setter for operator.
     * 
     * @param operator
     * @throws IllegalArgumentException in case of illegal operator argument
     */
    public void setOperator(int operator) throws IllegalArgumentException {
        if (OP_AND!=operator && OP_OR!=operator) {
            throw new IllegalArgumentException("Only AND and OR operators are supported in tree nodes!");
        }        
        this.operator = operator;
    }

    /**
     * Returns the list of children items.
     */    
    public List<CriteriaTreeItem> getChildren() {
        return children;
    }
    
    /**
     * Returns the children item according to the given index in the children list.
     * 
     * @param index child index
     * @return child item at the given index
     */
    public CriteriaTreeItem getChild(int index) {
        try {
            return children.get(index);
        }
        catch (Exception e) {
            // e.g. wrong index
            return null;
        }
    }
    
    /**
     * Returns the index of the child item with the same id, or -1 in case of not found child with the same id.
     */
    public int getChildIndex(CriteriaTreeItem child) {
        for (CriteriaTreeItem item : children) {
            if (item.getId()==child.getId()) {
                return children.indexOf(item);
            }
        }        
        return -1;
    }

    /**
     * Add a new child to the list of children.
     */
    public void addChild(CriteriaTreeItem child) {
        children.add(child);
    }
    
    /**
     * Removes the child from the list of children.
     */
    public void removeChild(CriteriaTreeItem child) {
        children.remove(child);
    }    
    
    /**
     * Sets the list of children.
     */
    public void setChildren(List<CriteriaTreeItem> children) {
        this.children = children;
    }
    
    /**
     * Sets the list of children - will contain only the <code>child</code> item.
     * 
     * @param child
     */
    public void setChild(CriteriaTreeItem child) {
        if (children==null) {
            children = new ArrayList<CriteriaTreeItem>();
        }
        else {
            children.clear();
        }
        children.add(child);
    }    
    
    /**
     * Returns the count of children.
     * 
     */
    public int getChildCount() {
        return children.size();
    }
    
    /**
     * Returns the path to the given item.
     * 
     * @param child
     * @return null if the given item was not found in the given subtree, otherwise the path to the child item. 
     */
    public List<Integer> getChildPath(CriteriaTreeItem child) {
        int childIndex = getChildIndex(child);
        if (childIndex!=-1) {
            List<Integer> ret = new ArrayList<Integer>();
            ret.add(childIndex);
            return ret;
        }
        else {
            for (CriteriaTreeItem ch : children) {
                if (ch instanceof CriteriaTreeNode) {
                    List<Integer> ret = ((CriteriaTreeNode)ch).getChildPath(child);
                    if (ret!=null) {
                        ret.add(getChildIndex(ch));
                        return ret;
                    }
                }                
            }
        }        
        return null;
    }

    @Override
    public Criterion toCriterion() {
        boolean hasAnyCriterion = false;
                
        switch (operator) {        
            case OP_OR:
                Junction crOr = Restrictions.disjunction();                
                for (CriteriaTreeItem child: getChildren()) {
                    Criterion childCr = child.toCriterion();
                    if (childCr!=null) {
                        crOr.add(childCr);
                        hasAnyCriterion = true;
                    }
                }                
                return hasAnyCriterion ? crOr : null;
            case OP_AND:                
                Junction crAnd = Restrictions.conjunction();
                Collection<Criterion> subCriterionList = new HashSet<Criterion>();
                for (CriteriaTreeItem child: getChildren()) {
                    Criterion childCr = child.toCriterion();
                    if (childCr!=null && child instanceof CriteriaTreeLeaf && "skill".equals(((CriteriaTreeLeaf)child).getVariable())) {
                        crAnd.add(Subqueries.propertyIn("username", DetachedCriteria.forClass(
                                PracticumView.class).add(childCr).setProjection(Projections.property("username"))));
                        subCriterionList.add(childCr);
                        hasAnyCriterion = true;
                    }
                    else if (childCr!=null) {
                        crAnd.add(childCr);
                    }
                }
                if (hasAnyCriterion) {
                    Junction crOrPart = Restrictions.disjunction();
                    for (Criterion cr : subCriterionList) {
                        crOrPart.add(cr);
                    }
                    crAnd.add(crOrPart);
                }                
                return crAnd;
            default: return null;
        }
    }
    
    @Override
    public String toString() {                
        return new StringBuilder().
            append(getId()).append(": ").
            append(getOperatorAsString()).
            append(" (").append(getChildCount()).append(")").
            toString();
    }
    
    @Override
    public String toStringIncludingSubCritera() {
        StringBuilder strBuilder = new StringBuilder();
        if (operator!=-1) {
            strBuilder.append("(");
        }
        for (CriteriaTreeItem sub : children) {
            if (strBuilder.length()>1) {
                strBuilder.append(" ").append(getOperatorAsString()).append(" ");
            }
            strBuilder.append(sub.toStringIncludingSubCritera());
        }
        if (operator!=-1) {
            strBuilder.append(")");
        }
        return strBuilder.toString();
    }
    
}
