package com.ixonos.skillnet.web.controllers;

import org.zkoss.zul.TreeModel;

public interface SkillnetTreeModel extends TreeModel {
	public void setRootName(final String rootName);
	public String getRootName();
	public void cleanChildrenMap();
	public void removeChildrenInfoFromMap(final Object parent);
}
