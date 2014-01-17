package com.ixonos.skillnet.web.controllers;

import org.zkoss.zul.TreeModel;

public interface SkillnetTreeModel extends TreeModel {
	 void setRootName(final String rootName);
	 String getRootName();
	 void cleanChildrenMap();
	 void removeChildrenInfoFromMap(final Object parent);
}
