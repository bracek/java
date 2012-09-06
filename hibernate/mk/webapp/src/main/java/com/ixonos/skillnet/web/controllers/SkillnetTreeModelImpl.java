package com.ixonos.skillnet.web.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.zul.event.TreeDataListener;

import com.ixonos.skillnet.logic.bean.Node;
import com.ixonos.skillnet.logic.service.NodeService;
import com.ixonos.skillnet.logic.util.NodesComparator;

@Component("skillnetTreeModel")
public class SkillnetTreeModelImpl implements SkillnetTreeModel {
	private String rootName;
	private Map<Integer, List<Node>> childrenMap = new HashMap<Integer, List<Node>>();
	private Integer ROOT_KEY = Integer.valueOf(0);

	@Resource
	protected NodeService nodeService;

	@Override
	public void addTreeDataListener(TreeDataListener arg0) {
	}

	@Override
	@Transactional
	public Object getChild(Object parent, int index) {
		prepareChildrenInMap((Node)parent);
		Integer parentId = ((Node)parent).getNodeId();
		return childrenMap.get(parentId).get(index);
	}

	@Override
	@Transactional
	public int getChildCount(Object parent) {
		prepareChildrenInMap((Node)parent);
		Integer parentId = ((Node)parent).getNodeId();
		return childrenMap.get(parentId).size();
	}

	@Override
	public int[] getPath(Object arg0, Object arg1) {
		return null;
	}

	@Override
	@Transactional
	public Object getRoot() {
		if (!childrenMap.containsKey(ROOT_KEY)) {
			Node root = nodeService.getRoot(rootName);
			List<Node> nodeList = new ArrayList<Node>();
			nodeList.add(root);
			childrenMap.put(ROOT_KEY, nodeList);
		}
		return childrenMap.get(ROOT_KEY).get(0);
	}

	@Override
	@Transactional
	public boolean isLeaf(Object node) {
		return getChildCount(node) == 0;
	}

	@Override
	public void removeTreeDataListener(TreeDataListener arg0) {
	}

	@Override
	public void setRootName(String rootName) {
		this.rootName = rootName;
	}

	@Override
	public String getRootName() {
		return rootName;
	}
	
	private void prepareChildrenInMap(Node parent) {
		Integer parentId = (parent).getNodeId();
		if (!childrenMap.containsKey(parentId)) {
			List<Node> children = nodeService.getChildren(parent);	
			Node[] nodesArray = children.toArray(new Node[children.size()]);
			Arrays.sort(nodesArray,new NodesComparator(true));
			children = Arrays.asList(nodesArray); 
			childrenMap.put(parentId, children);
		}
	}

	@Override
	public void cleanChildrenMap() {
		childrenMap = new HashMap<Integer, List<Node>>();		
	}

	@Override
	public void removeChildrenInfoFromMap(Object parent) {
		Integer parentId = ((Node)parent).getNodeId();
		childrenMap.remove(parentId);		
	}
}
