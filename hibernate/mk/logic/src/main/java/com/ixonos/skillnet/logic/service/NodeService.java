package com.ixonos.skillnet.logic.service;

import java.util.List;

import com.ixonos.skillnet.logic.bean.Node;

/**
 *
 * @author magurja
 */
public interface NodeService extends HibernateGenericService<Node> {

	Node getChild(Node node, int index);

	List<Node> getChildren(Node childNode);

	Node getRoot(String rootName);	
	
	Node insertNode(Integer parentID, Integer skillID);

	int getChildCount(Node parent);

	Node moveNode(Integer nodeID, Integer newParentID);

}
