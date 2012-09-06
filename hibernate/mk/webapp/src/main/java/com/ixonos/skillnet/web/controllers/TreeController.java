package com.ixonos.skillnet.web.controllers;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.SimpleTreeNode;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;
import org.zkoss.zul.api.Checkbox;

import com.ixonos.skillnet.logic.bean.Node;
import com.ixonos.skillnet.logic.security.SkillnetUser;
import com.ixonos.skillnet.logic.service.GroupsService;
import com.ixonos.skillnet.web.servlet.SkillnetInitServlet;

@Service("TreeController")
@Scope("prototype")
public class TreeController extends GenericForwardComposer implements
		TreeitemRenderer {

	public TreeController() {
	}

	@Autowired
	protected SkillnetTreeModel skillnetTreeModel;

	@Resource
	protected GroupsService groupsService;

	protected Textbox Name;
	protected Textbox Description;
	protected Checkbox Valuable;
	protected Tree tree;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		skillnetTreeModel.setRootName(groupsService.getTreeRootName(
				((SkillnetUser) SecurityContextHolder.getContext()
						.getAuthentication().getPrincipal()).getUsername(),
				SkillnetInitServlet.props));
		tree.setModel(skillnetTreeModel);
		tree.setTreeitemRenderer(this);
	}

	@Override
	public void render(Treeitem item, Object data) throws Exception {
		SimpleTreeNode t = (SimpleTreeNode) data;
		Node node = (Node) t.getData();
		Treecell tcSkillName = new Treecell(node.getSkill().getName());
		Treerow tr = null;
		/*
		 * Since only one treerow is allowed, if treerow is not null, append
		 * treecells to it. If treerow is null, construct a new treerow and
		 * attach it to item.
		 */
		if (item.getTreerow() == null) {
			tr = new Treerow();
			tr.setParent(item);
		} else {
			tr = item.getTreerow();
			tr.getChildren().clear();
		}
		// Attach treecells to treerow
		tcSkillName.setParent(tr);
		item.setOpen(false);

	}
}
