package com.ixonos.skillnet.web.reporting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import org.hibernate.criterion.DetachedCriteria;
import org.zkoss.zul.TreeModel;
import org.zkoss.zul.event.TreeDataListener;

import com.ixonos.skillnet.logic.bean.PracticumView;

/**
 * Criteria tree data model.
 * 
 * @author molnala
 *
 */
public class CriteriaTreeModel implements TreeModel {
    private static final Logger logger = Logger.getLogger(CriteriaTreeModel.class);
    
    /**
     * List if tree data listeners.
     */
    private List<TreeDataListener> listenerList = new ArrayList<TreeDataListener>();
    
    /**
     * The root node (is invisible).
     */
    private CriteriaTreeNode root = new CriteriaTreeNode(-1);
    
    public void setRoot(final CriteriaTreeItem item) {
        root.setChild(item);
    }
    
    @Override
    public void addTreeDataListener(final TreeDataListener l) {
        listenerList.add(l);        
    }
    
    @Override
    public void removeTreeDataListener(final TreeDataListener l) {
        listenerList.remove(l);        
    }
    
    @Override
    public Object getChild(final Object parent,final  int index) {
        if (parent instanceof CriteriaTreeNode) {
            return ((CriteriaTreeNode)parent).getChild(index);
        }
            
        return null;
    }
    
    @Override
    public int getChildCount(final Object parent) {
        if (parent instanceof CriteriaTreeNode) {
            return ((CriteriaTreeNode)parent).getChildCount();
        }
        
        return 0;
    }
    
    @Override
    public int[] getPath(final Object parent,final  Object lastNode) {
        if (parent instanceof CriteriaTreeNode && lastNode instanceof CriteriaTreeItem) {
            List<Integer> retList = ((CriteriaTreeNode)parent).getChildPath((CriteriaTreeItem)lastNode);
            if (retList!=null) {
                int ret[] = new int[retList.size()];
                for (int i=0; i<retList.size(); i++) {
                    ret[i] = retList.get(i);
                }
                return revertPath(ret);
            }        
        }        
        
        return null;
    }
    
    /**
     * Helper method, which reverts the path array.
     * 
     * @param path
     * @return
     */
    private static int[] revertPath(final int[] path) {
        if (path==null || path.length==0) {
            return new int[0];
        }
        
        int[] revertedPath = new int[path.length];
        
        for (int i=0; i<path.length; i++) {
            revertedPath[path.length-i-1] = path[i];
        }
        
        return revertedPath;
    }
    
    /**
     * Updates the <code>oldChild</code> with the <code>newChild</code>
     * 
     * @param oldChild
     * @param newChild
     * @return reference to the updated child, or null in case of fail
     */
    public <T extends CriteriaTreeItem> T updateChild(T oldChild, T newChild) {
        if (oldChild instanceof CriteriaTreeLeaf && newChild instanceof CriteriaTreeLeaf) {
            ((CriteriaTreeLeaf)oldChild).setVariable(((CriteriaTreeLeaf)newChild).getVariable());
            ((CriteriaTreeLeaf)oldChild).setOperator(((CriteriaTreeLeaf)newChild).getOperator());
            ((CriteriaTreeLeaf)oldChild).setValue(((CriteriaTreeLeaf)newChild).getValues());
        }
        else if (oldChild instanceof CriteriaTreeNode && newChild instanceof CriteriaTreeNode) {
            ((CriteriaTreeNode)oldChild).setOperator(((CriteriaTreeNode)newChild).getOperator());
        }
        else {
            return null;    // fail
        }
        
        return oldChild;
    }
    
    /**
     * Moves the <code>srcItem</code> to the <code>destItem</code> (in case of node)/parent node of <code>destItem</code>
     * 
     * @param srcItem tree item to be moved
     * @param destItem the destination tree node or the destination tree node's child
     * @return reference to the moved item, or null in case of fail
     */
    public CriteriaTreeItem moveItem(final CriteriaTreeItem srcItem,final  CriteriaTreeItem destItem) {
        CriteriaTreeNode destNode = null;
        if (destItem instanceof CriteriaTreeNode){
            destNode = (CriteriaTreeNode) destItem;
        }
        else {
            destNode = getParent(destItem);
        }
        
        if (destNode==null || srcItem==null) {
            return null;    // fail
        }
        
        CriteriaTreeNode srcParent = getParent(srcItem);
        srcParent.removeChild(srcItem);
        destNode.addChild(srcItem);
        return srcItem;
    }
    
    /**
     * Checks whether the <code>childNode</code> is in the subtree of the <code>parentNode</code>.
     *  
     * @param parentNode
     * @param childNode
     * @return true if <code>childNode</code>'s parent is <code>parentNode</code>
     */
    public boolean isInSubTree(final CriteriaTreeNode parentNode,final  CriteriaTreeItem childNode) {
        CriteriaTreeItem parent = parentNode;
                
        if (((CriteriaTreeNode)parent).getChildIndex(childNode)!=-1) {
            return true;
        }
        else {
            for (CriteriaTreeItem child : ((CriteriaTreeNode)parent).getChildren()) {
                if (child instanceof CriteriaTreeNode &&  isInSubTree((CriteriaTreeNode)child, childNode)) {
                    return true;
                }
            }            
        }       
        
        return false;
    }
    
    /**
     * Adds a new child item to the <code>parentNode</code> node.
     * 
     * @param parentNode
     * @param childItem
     */
    public void addChild(final CriteriaTreeNode parentNode,final  CriteriaTreeItem childItem) {
        parentNode.addChild(childItem);     
    }
    
    /**
     * Removes <code>childItem</code> item from the <code>parentNode</code> node.
     * 
     * @param parentNode
     * @param childItem
     */
    public void removeChild(final CriteriaTreeNode parentNode,final  CriteriaTreeItem childItem) {
        if (parentNode!=null) {
            parentNode.removeChild(childItem);
        }
        else {
            logger.error("Internal error: Parent node can't be null!");
        }
    }
    
    /**
     * Removes the <code>childItem</code> from the data model.
     * 
     * @param childItem
     * @return reference to the deleted node's parent
     */
    public CriteriaTreeItem removeChild(final CriteriaTreeItem childItem) {
        CriteriaTreeNode parentNode = getParent(childItem);
        removeChild(parentNode, childItem);
        return parentNode;
    }
    
    /**
     * Returns the parent item of the given child item, or null if not found.
     * 
     * @param child
     * @return
     */
    public CriteriaTreeNode getParent(final CriteriaTreeItem child) {
        int[] pathToChild = getPath(root, child);        
        int[] pathToParent = Arrays.copyOfRange(pathToChild, 0, pathToChild.length-1);
        CriteriaTreeNode parent = root;
        for (int i : pathToParent) {
            if (parent.getChild(i) instanceof CriteriaTreeNode) {
                parent = (CriteriaTreeNode)parent.getChild(i);
            }
            else {
                logger.error("Parent must be instance of CriteriaTreeNode!");
                return null;
            }
        }
        
        return parent;
    }
    
    /**
     * Inserts the <code>treeItem</code> into <code>wrappingTreeNode</code>, 
     * and adds the <code>wrappingTreeNode</code> to the <code>treeItem</code>'s previous parent. 
     * 
     * @param treeItem the tree item (already existing in this model) to be wrapped (replaced by <code>wrappingTreeNode</code>) 
     * @param wrappingTreeNode the wrapping node
     * @return reference to the wrapped item (<code>treeItem</code>), or null in case of fail
     */
    public CriteriaTreeItem wrapItem(final CriteriaTreeItem treeItem,final  CriteriaTreeNode wrappingTreeNode) {
        CriteriaTreeNode treeItemParent = getParent(treeItem);
        if (treeItemParent==null) {
            logger.error("Can't find parent of " + treeItem + "!");
            return null;
        }
        
        addChild(treeItemParent, wrappingTreeNode);
        moveItem(treeItem, wrappingTreeNode);
        return treeItem;
    }
    
    @Override
    public Object getRoot() {
        return root;
    }
    
    @Override
    public boolean isLeaf(final Object node) {
        return (node instanceof CriteriaTreeLeaf);
    }   

    /**
     * Returns {@link DetachedCriteria} representation of the data model.
     * 
     * @return
     */
    public DetachedCriteria toDetachedCriteria() {
        if (root.getChild(0)!=null) {
            DetachedCriteria dc = DetachedCriteria.forClass(PracticumView.class);     
            dc.add(((CriteriaTreeItem)root.getChild(0)).toCriterion());
            return dc;
        }
        return null;
    }

    /**
     * Checks whether the model contains any item.
     * 
     * @return true if the model is empty.
     */
    public boolean isEmpty() {
        return root.getChildCount()==0;
    }
}
