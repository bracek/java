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
	private final Integer ROOT_KEY = Integer.valueOf(0);

	@Resource
	protected NodeService nodeService;

	@Override
	public void addTreeDataListener(final TreeDataListener arg0) {
	}

	@Override
	@Transactional
	public Object getChild(final Object parent,
 final int index) {
		prepareChildrenInMap((Node) parent);
		final Integer parentId = ((Node) parent).getNodeId();
		return childrenMap.get(parentId).get(index);
	}

	@Override
	@Transactional
	public int getChildCount(final Object parent) {
		prepareChildrenInMap((Node) parent);
		final Integer parentId = ((Node) parent).getNodeId();
		return childrenMap.get(parentId).size();
	}

	@Override
	public int[] getPath(final Object arg0,
 final Object arg1) {
		return null;
	}

	@Override
	@Transactional
	public Object getRoot() {
		if (!childrenMap.containsKey(ROOT_KEY)) {
			final Node root = nodeService.getRoot(rootName);
			final List<Node> nodeList = new ArrayList<Node>();
			nodeList.add(root);
			childrenMap.put(ROOT_KEY, nodeList);
		}
		return childrenMap.get(ROOT_KEY).get(0);
	}

	@Override
	@Transactional
	public boolean isLeaf(final Object node) {
		return getChildCount(node) == 0;
	}

	@Override
	public void removeTreeDataListener(final TreeDataListener arg0) {
	}

	@Override
	public void setRootName(final String rootName) {
		this.rootName = rootName;
	}

	@Override
	public String getRootName() {
		return rootName;
	}

	private void prepareChildrenInMap(final Node parent) {
		final Integer parentId = parent.getNodeId();
		if (!childrenMap.containsKey(parentId)) {
			List<Node> children = nodeService.getChildren(parent);
			final Node[] nodesArray = children
					.toArray(new Node[children.size()]);
			Arrays.sort(nodesArray, new NodesComparator(true));
			children = Arrays.asList(nodesArray);
			childrenMap.put(parentId, children);
		}
	}

	@Override
	public void cleanChildrenMap() {
		childrenMap = new HashMap<Integer, List<Node>>();
	}

	@Override
	public void removeChildrenInfoFromMap(final Object parent) {
		final Integer parentId = ((Node) parent).getNodeId();
		childrenMap.remove(parentId);
	}
}
