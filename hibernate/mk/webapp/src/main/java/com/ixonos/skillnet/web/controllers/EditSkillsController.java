package com.ixonos.skillnet.web.controllers;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.util.media.ContentTypes;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.DropEvent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Image;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.SimpleListModel;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;
import org.zkoss.zul.Window;
import org.zkoss.zul.api.Checkbox;
import org.zkoss.zul.api.Treechildren;

import com.ixonos.skillnet.logic.bean.Node;
import com.ixonos.skillnet.logic.bean.Skill;
import com.ixonos.skillnet.logic.bean.Users;
import com.ixonos.skillnet.logic.security.SkillnetUser;
import com.ixonos.skillnet.logic.service.GroupsService;
import com.ixonos.skillnet.logic.service.NodeService;
import com.ixonos.skillnet.logic.service.SkillService;
import com.ixonos.skillnet.logic.service.UsersService;
import com.ixonos.skillnet.web.servlet.SkillnetInitServlet;

@org.springframework.stereotype.Component("EditSkillsController")
@Scope("prototype")
public class EditSkillsController extends GenericForwardComposer implements
		TreeitemRenderer, ListitemRenderer {

	private static final Logger logger = Logger
			.getLogger(EditSkillsController.class);
	private static final XMLOutputFactory xmlof = XMLOutputFactory
			.newInstance();
	private static final XMLEventFactory xmlef = XMLEventFactory.newInstance();
	private static final String SKILLNET_URI = "http://www.ixonos.com/skillnet";
	private final QName qn_skills = new QName(SKILLNET_URI, "skills", "isn");
	private final QName qn_skill = new QName(SKILLNET_URI, "skill", "isn");
	private final QName qn_trees = new QName(SKILLNET_URI, "trees", "isn");
	private final QName qn_node = new QName(SKILLNET_URI, "node", "isn");
	private final QName qn_children = new QName(SKILLNET_URI, "children", "isn");
	private final QName qn_name = new QName(SKILLNET_URI, "name", "isn");
	private final QName qn_evaluable = new QName(SKILLNET_URI, "evaluable",
			"isn");
	private final QName qn_description = new QName(SKILLNET_URI, "description",
			"isn");
	final String username = ((SkillnetUser) SecurityContextHolder.getContext()
			.getAuthentication().getPrincipal()).getUsername();
	@Autowired
	protected SkillnetTreeModel skillnetTreeModel;
	@Resource
	protected UsersService userService;
	@Resource
	protected SkillService skillService;
	@Resource
	protected NodeService nodeService;
	@Resource
	protected GroupsService groupsService;
	protected Skill selectedSkill = null;
	protected Textbox name;
	protected Textbox description;
	protected Textbox mapping;
	protected Textbox filter;
	protected Checkbox evaluable;
	protected Datebox date;
	protected Listbox list;
	protected Listbox mappingList;
	protected Tree tree;
	private ListModelList listModelList;
	protected Map<Object, Boolean> openedItems = new HashMap<Object, Boolean>();
	private Thread taskThread;

	@Override
	public void render(final Treeitem item,
 final Object data) throws Exception {
		final Node node = (Node) data;
		final Treecell tcSkillName = new Treecell(node.getSkill().getName());
		Treerow tr = null;
		/*
		 * Since only one treerow is allowed, if treerow is not null, append
		 * treecells to it. If treerow is null, construct a new treerow and
		 * attach it to item.
		 */
		if (item.getTreerow() == null) {
			tr = new Treerow();
			tr.setDraggable("true");
			tr.setDroppable("true");
			tr.addEventListener("onDrop", new EventListener() {

				@Transactional
				@Override
				public void onEvent(final Event e) throws Exception {
					final Component dragged = ((DropEvent) e).getDragged();
					if (dragged instanceof Listitem) {
						final Listitem li = (Listitem) dragged;
						final Integer skillID = (Integer) li.getValue();
						final Treeitem ti = (Treeitem) ((Treerow) e.getTarget())
								.getParent();
						final Integer nodeID = (Integer) ti.getValue();
						try {
							nodeService.insertNode(nodeID, skillID);
							// remove from model children map
							final Node node = new Node();
							node.setNodeId(nodeID);
							skillnetTreeModel.removeChildrenInfoFromMap(node);
							// set not-cached flag
							ti.unload();
							ti.setOpen(true);
							tree.renderItem(ti);
						} catch (final ConstraintViolationException ex) {
							Messagebox.show(
									Labels.getLabel("skillsEdit.warning.skillExists"),
									Labels.getLabel("skillsEdit.warning"), 1,
									"OK");
						}
					} else if (dragged instanceof Treerow) {
						final Treeitem tisrc = (Treeitem) ((Treerow) dragged)
								.getParent();
						final Treeitem tidest = (Treeitem) ((Treerow) e
								.getTarget()).getParent();
						final Integer nodeIDsrc = (Integer) tisrc.getValue();
						final Integer nodeIDdest = (Integer) tidest.getValue();

						try {
							if (nodeService.moveNode(nodeIDsrc, nodeIDdest) != null) {
								skillnetTreeModel.setRootName(groupsService
										.getTreeRootName(
												((SkillnetUser) SecurityContextHolder
														.getContext()
														.getAuthentication()
														.getPrincipal())
														.getUsername(),
												SkillnetInitServlet.props));
								tree.setModel(skillnetTreeModel);
							} else {
								Messagebox.show(
										Labels.getLabel("skillsEdit.warning.notPossible"),
										Labels.getLabel("skillsEdit.warning"),
										1, "OK");
							}
						} catch (final DataIntegrityViolationException ex) {
							Messagebox.show(
									Labels.getLabel("skillsEdit.warning.skillExists"),
									Labels.getLabel("skillsEdit.warning"), 1,
									"OK");
							;
						}
					}
				}
			});
			item.addEventListener("onOpen", new EventListener() {

				@Override
				public void onEvent(final Event event) throws Exception {
					if (openedItems.containsKey(item.getValue())) {
						openedItems.remove(item.getValue());
					} else {
						openedItems.put(item.getValue(), Boolean.TRUE);
					}
				}
			});

			tr.setParent(item);
			item.setValue(node.getNodeId());
		} else {
			tr = item.getTreerow();
			tr.getChildren().clear();
		}
		// Attach treecells to treerow
		tcSkillName.setParent(tr);
		if (openedItems.containsKey(node.getNodeId())) {
			item.setOpen(true);
		} else {
			item.setOpen(false);
		}
		item.setContext("expandPopup");
	}

	public void onClick$expandMenuItem(final Event e) {
		final Treeitem selectedItem = tree.getSelectedItem();
		if (selectedItem != null) {
			expandTreeItemsRecursively(selectedItem);
		}
	}

	private void expandTreeItemsRecursively(final Treeitem treeItem) {
		if (!treeItem.isOpen()) {
			treeItem.setOpen(true);
		}
		if (treeItem.getTreechildren() != null) {
			final Object[] items = treeItem.getTreechildren().getItems()
					.toArray();
			for (int i = 0; i < items.length; i++) {
				final Object item = items[i];
				if (item instanceof Treeitem) {
					expandTreeItemsRecursively((Treeitem) item);
				}
			}
		}
	}

	@Override
	public void render(final Listitem listItem,
 final Object data)
			throws Exception {
		final Skill skill = (Skill) data;
		new Listcell(skill.getName()).setParent(listItem);
		new Listcell(skill.getDescription()).setParent(listItem);
		Image image = null;
		if (skill.getValuable().toString().equals("true")) {
			image = new Image("/img/icons/true.png");
		} else if (skill.getValuable().toString().equals("false")) {
			image = new Image("/img/icons/false.png");
		}
		final Listcell lcIcon = new Listcell();
		image.setParent(lcIcon);
		lcIcon.setParent(listItem);

		listItem.setDraggable("true");
		listItem.setDroppable("true");
		listItem.addEventListener("onDrop", new EventListener() {

			@Transactional
			@Override
			public void onEvent(final Event e) throws Exception {
				final Component dragged = ((DropEvent) e).getDragged();
				if (dragged instanceof Treerow) {
					final Treerow tr = (Treerow) ((DropEvent) e).getDragged();
					final Treeitem ti = (Treeitem) tr.getParent();
					final Integer nodeID = (Integer) ti.getValue();
					final Component parent = ti.getParent();
					nodeService.delete(nodeService.read(nodeID));
					if (parent instanceof Treechildren) {
						final Component oparent = parent.getParent();
						if (oparent instanceof Tree) {
							((Treechildren) parent).removeChild(ti);
							tree.invalidate();
						} else if (oparent instanceof Treeitem) {
							final Treeitem item = (Treeitem) oparent;
							final Integer nodeIdForRemove = (Integer) item
									.getValue();
							// remove from model children map
							final Node node = new Node();
							node.setNodeId(nodeIdForRemove);
							skillnetTreeModel.removeChildrenInfoFromMap(node);
							// set not-cached flag
							item.unload();
							item.setOpen(true);
							tree.renderItem(item);
						}
					}
				}
			}
		});
		listItem.setValue(skill.getSkillId());
	}

	@Override
	public void doAfterCompose(final Component comp) throws Exception {
		super.doAfterCompose(comp);

		final String userName = ((SkillnetUser) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal()).getUsername();

		tree.setTreeitemRenderer(this);
		tree.addEventListener("onLoad", new EventListener() {

			@Override
			@Transactional(readOnly = true)
			public void onEvent(final Event e) throws Exception {
				skillnetTreeModel.setRootName(groupsService.getTreeRootName(
						userName, SkillnetInitServlet.props));
				skillnetTreeModel.cleanChildrenMap();
				tree.setModel(skillnetTreeModel);
			}
		});

		list.setItemRenderer(this);
		list.addEventListener("onLoad", new EventListener() {

			@Override
			public void onEvent(final Event e) throws Exception {
				list.setModel(listModelList);
			}
		});
		list.addEventListener("onSelect", new EventListener() {

			@Override
			public void onEvent(final Event e) throws Exception {
				final int index = list.getSelectedIndex();
				selectedSkill = (Skill) ((ListModelList) list.getModel())
						.get(index);
				name.setValue(selectedSkill.getName());
				description.setValue(selectedSkill.getDescription());
				evaluable.setChecked(selectedSkill.getValuable());

				final String rootName = ((SkillnetTreeModel) tree.getModel())
						.getRootName();
				final List<String> paths = skillService.getNodePaths(
						selectedSkill.getSkillId(), rootName);

				if (paths != null) {
					mappingList.setModel(new SimpleListModel(paths));
				} else {
					mappingList.setModel(new SimpleListModel(
							new ArrayList<String>()));
				}

				return;
			}
		});

		mappingList.addEventListener("onSelect", new EventListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void onEvent(final Event event) throws Exception {
				final String path = mappingList.getSelectedItem().getLabel();
				selectSkillInTree(tree.getItems(), path.split("/"), 0);
			}
		});

		Events.postEvent(new Event("onLoad", tree));

		desktop.enableServerPush(true);

		taskThread = new Thread(new ListDataLoadRunnable());
		taskThread.start();

	}

	@SuppressWarnings("unchecked")
	private void selectSkillInTree(final Collection<Treeitem> items,
			final String[] pathArray,
 final int i) {

		for (final Treeitem treeItem : items) {
			if (treeItem.getLabel().equals(pathArray[i])) {
				if (i == pathArray.length - 1) {
					treeItem.setSelected(true);
				} else {
					treeItem.setOpen(true);
					selectSkillInTree(treeItem.getTreechildren().getItems(),
							pathArray, i + 1);
				}
				return;
			}
		}
	}

	@Transactional
	public void onClick$add(final Event e) {
		final String nameValue = name.getValue();
		final String descriptionValue = description.getValue();
		final Boolean valuableValue = evaluable.isChecked();
		if (nameValue != null) {
			final Skill skill = new Skill();
			skill.setName(nameValue);
			skill.setDescription(descriptionValue);
			skill.setValuable(valuableValue);
			skill.setCreated(new Date());
			final String userName = ((SkillnetUser) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal())
					.getUsername();
			final Users userCrit = new Users();
			userCrit.setUsername(userName);
			final List<Users> user = userService.readByCriteria(userCrit);
			skill.setCreatedBy(user.get(0));
			skillService.create(skill);
			final List<Skill> skills = skillService.readAll();
			listModelList.clear();
			listModelList.addAll(skills);
			selectedSkill = skill;
			filterListModel(filter.getValue());
			final int index = ((ListModelList) list.getModel())
					.indexOf(selectedSkill);
			list.setSelectedIndex(index);
		}
	}

	@Transactional
	public void onClick$update(final Event e) throws InterruptedException {
		if (selectedSkill != null) {
			boolean update = true;
			if (selectedSkill.getValuable() == true
					&& evaluable.isChecked() == false) {
				final int answer = Messagebox
						.show(Labels.getLabel("skillsEdit.question.evaluable1")
								+ " '"
								+ selectedSkill.getName()
								+ "' "
								+ Labels.getLabel("skillsEdit.question.evaluable2"),
								Labels.getLabel("skillsEdit.question.confirmation"),
								Messagebox.YES | Messagebox.NO,
								Messagebox.QUESTION);
				if (answer == Messagebox.NO) {
					update = false;
				}
			}
			if (update) {
				selectedSkill.setName(name.getValue());
				selectedSkill.setDescription(description.getValue());
				selectedSkill.setValuable(evaluable.isChecked());
				selectedSkill.setModified(new Date());

				final Users userCrit = new Users();
				userCrit.setUsername(username);
				final List<Users> user = userService.readByCriteria(userCrit);
				selectedSkill.setModifiedBy(user.get(0));
				try {
					skillService.update(selectedSkill);
				} catch (final EntityNotFoundException exception) {
					final int index = listModelList.indexOf(selectedSkill);
					listModelList.remove(index);
					alert("'" + selectedSkill.getName() + "'"
							+ Labels.getLabel("skillsEdit.alert.skillDeleted"));
					if (((ListModelList) list.getModel()).size() > 0) {
						selectedSkill = (Skill) ((ListModelList) list
								.getModel()).get(0);
						list.setSelectedIndex(0);

						name.setValue(selectedSkill.getName());
						description.setValue(selectedSkill.getDescription());
						evaluable.setChecked(selectedSkill.getValuable());
					} else {
						selectedSkill = null;
					}
				}
				final List<Skill> skills = skillService.readAll();
				listModelList.clear();
				listModelList.addAll(skills);
				filterListModel(filter.getValue());
				final int index = ((ListModelList) list.getModel())
						.indexOf(selectedSkill);
				list.setSelectedIndex(index);
			}
		}
	}

	public void onClick$import(final Event e) throws InterruptedException {
		((Window) Executions.createComponents(
				"/WEB-INF/jsp/tiles/skills/importSkills.zul", null, null))
				.doModal();
	}

	@Transactional
	public void onClick$exportSkills(final Event evt)
			throws InterruptedException {
		try {
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();

			XMLEventWriter writer = null;
			synchronized (xmlof) {
				writer = xmlof.createXMLEventWriter(baos);
			}
			writer.add(xmlef.createStartDocument());
			writer.add(xmlef.createIgnorableSpace("\n"));
			writer.add(xmlef.createStartElement(qn_skills, null, null));
			writer.add(xmlef.createNamespace("isn", SKILLNET_URI));

			for (final Object o : listModelList.toArray()) {
				if (o instanceof Skill) {
					exportSkill(writer, (Skill) o, 1);
				}
			}

			writer.add(xmlef.createIgnorableSpace("\n"));
			writer.add(xmlef.createEndElement(qn_skills, null));
			writer.add(xmlef.createEndDocument());

			Filedownload.save(baos.toByteArray(),
					ContentTypes.getContentType("xml"), "skill.xml");

		} catch (final XMLStreamException xse) {
			Messagebox.show(xse.getLocalizedMessage(), "Error", 1, "OK");
			xse.printStackTrace();
		}
	}

	private void exportSkill(final XMLEventWriter writer,
 final Skill s,
			final int lvl) throws XMLStreamException {
		writer.add(xmlef.createIgnorableSpace("\n"));
		for (int i = 0; i < lvl; i++) {
			writer.add(xmlef.createIgnorableSpace("\t"));
		}

		writer.add(xmlef.createStartElement(qn_skill, null, null));

		final String name = s.getName() != null ? s.getName() : "";
		writer.add(xmlef.createAttribute(qn_name, name));

		final String eval = s.getValuable() != null ? s.getValuable()
				.toString() : "";
		writer.add(xmlef.createAttribute(qn_evaluable, eval));

		final String desc = s.getDescription() != null ? s.getDescription()
				: "";
		writer.add(xmlef.createAttribute(qn_description, desc));

		writer.add(xmlef.createIgnorableSpace("\n"));
		for (int i = 0; i < lvl; i++) {
			writer.add(xmlef.createIgnorableSpace("\t"));
		}

		writer.add(xmlef.createEndElement(qn_skill, null));
	}

	@Transactional
	public void onClick$exportTrees(final Event e) throws InterruptedException {
		try {
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();

			XMLEventWriter writer = null;
			synchronized (xmlof) {
				writer = xmlof.createXMLEventWriter(baos);
			}
			writer.add(xmlef.createStartDocument());
			writer.add(xmlef.createIgnorableSpace("\n"));
			writer.add(xmlef.createStartElement(qn_trees, null, null));
			writer.add(xmlef.createNamespace("isn", SKILLNET_URI));

			final Node root = (Node) skillnetTreeModel.getRoot();
			for (int i = 0, c = skillnetTreeModel.getChildCount(root); i < c; i++) {
				final Node child = (Node) skillnetTreeModel.getChild(root, i);
				exportChild(writer, child, 1);
			}

			writer.add(xmlef.createIgnorableSpace("\n"));
			writer.add(xmlef.createEndElement(qn_trees, null));
			writer.add(xmlef.createEndDocument());

			Filedownload.save(baos.toByteArray(),
					ContentTypes.getContentType("xml"), "tree.xml");

		} catch (final XMLStreamException xse) {
			Messagebox.show(xse.getLocalizedMessage(), "Error", 1, "OK");
			xse.printStackTrace();
		}
	}

	private void exportChild(final XMLEventWriter writer,
 final Node node,
			final int lvl) throws XMLStreamException {

		writer.add(xmlef.createIgnorableSpace("\n"));
		for (int i = 0; i < lvl; i++) {
			writer.add(xmlef.createIgnorableSpace("\t"));
		}
		writer.add(xmlef.createStartElement(qn_node, null, null));

		exportSkill(writer, node.getSkill(), lvl + 1);

		writer.add(xmlef.createIgnorableSpace("\n"));
		for (int i = 0; i < lvl + 1; i++) {
			writer.add(xmlef.createIgnorableSpace("\t"));
		}
		writer.add(xmlef.createStartElement(qn_children, null, null));

		for (int i = 0, c = skillnetTreeModel.getChildCount(node); i < c; i++) {
			final Node child = (Node) skillnetTreeModel.getChild(node, i);
			exportChild(writer, child, lvl + 2);
		}

		writer.add(xmlef.createIgnorableSpace("\n"));
		for (int i = 0; i < lvl + 1; i++) {
			writer.add(xmlef.createIgnorableSpace("\t"));
		}
		writer.add(xmlef.createEndElement(qn_children, null));

		writer.add(xmlef.createIgnorableSpace("\n"));
		for (int i = 0; i < lvl; i++) {
			writer.add(xmlef.createIgnorableSpace("\t"));
		}
		writer.add(xmlef.createEndElement(qn_node, null));
	}

	@Transactional
	public void onClick$delete(final Event e) throws InterruptedException {
		if (null != selectedSkill) {
			final int answer = Messagebox.show(
					Labels.getLabel("skillsEdit.question.delete") + " '"
							+ selectedSkill.getName() + "' ?",
					Labels.getLabel("skillsEdit.question.confirmation"),
					Messagebox.YES | Messagebox.NO, Messagebox.QUESTION);
			if (answer == Messagebox.YES) {
				int index = list.getSelectedIndex();
				try {
					skillService.delete(selectedSkill);
				} catch (final EntityNotFoundException exception) {
					logger.error("This is harmless as someone else has already deleted this item.");
				}
				listModelList.remove(selectedSkill);
				filterListModel(filter.getValue());
				if (index >= ((ListModelList) list.getModel()).size()) {
					index = ((ListModelList) list.getModel()).size() - 1;
				}
				if (((ListModelList) list.getModel()).size() > 0) {
					selectedSkill = (Skill) ((ListModelList) list.getModel())
							.get(index);
					list.setSelectedIndex(index);
					name.setValue(selectedSkill.getName());
					description.setValue(selectedSkill.getDescription());
					evaluable.setChecked(selectedSkill.getValuable());
				} else {
					selectedSkill = null;
				}
			}
		}
	}

	@Transactional
	public void onDrop$treepanel(final DropEvent e) throws Exception {
		final String rootName = groupsService.getTreeRootName(
				((SkillnetUser) SecurityContextHolder.getContext()
						.getAuthentication().getPrincipal()).getUsername(),
				SkillnetInitServlet.props);
		if (e.getDragged() instanceof Listitem) {
			final Listitem li = (Listitem) e.getDragged();
			final Integer skillID = (Integer) li.getValue();
			nodeService.insertNode(nodeService.getRoot(rootName).getNodeId(),
					skillID);
		}
		if (e.getDragged() instanceof Treerow) {
			final Treeitem tisrc = (Treeitem) ((Treerow) e.getDragged())
					.getParent();
			final Integer nodeIDsrc = (Integer) tisrc.getValue();
			nodeService.moveNode(nodeIDsrc, nodeService.getRoot(rootName)
					.getNodeId());
		}
		Events.postEvent(new Event("onLoad", tree));
	}

	@SuppressWarnings("unchecked")
	private void updateSorting() {
		Boolean sorted = false;
		final Iterator<Listheader> iterator = list.getListhead().getChildren()
				.iterator();
		while (iterator.hasNext()) {
			final Listheader column = iterator.next();
			final String sortDirection = column.getSortDirection();
			if ("ascending".equals(sortDirection)) {
				((ListModelList) list.getModel()).sort(new SkillsComparator(
						true, column.getLabel()), true);
				sorted = true;
				break;
			} else if ("descending".equals(sortDirection)) {
				((ListModelList) list.getModel()).sort(new SkillsComparator(
						false, column.getLabel()), false);
				sorted = true;
				break;
			}
		}
		if (sorted == false) {
			((ListModelList) list.getModel()).sort(new SkillsComparator(true,
					"Name"), true);
		}
	}

	public void onChanging$filter(final InputEvent e) {
		filterListModel(e.getValue());
		selectedSkill = null;
		name.setValue(null);
		evaluable.setChecked(false);
		description.setValue(null);
		mappingList.setModel(new SimpleListModel(new ArrayList<String>()));
		list.invalidate();
	}

	public void onClick$filterReset(final Event e) {
		if (!filter.getValue().equals("")) {
			filter.setValue("");
			Events.postEvent(new InputEvent("onChanging", filter, ""));
		}
	}

	@SuppressWarnings("unchecked")
	private void filterListModel( String filter) {
		filter = filter.toLowerCase();
		if (filter.equals("") || filter.equals("*")) {
			list.setModel(listModelList);
		} else {
			final List<Skill> filteredSkills = new ArrayList<Skill>();
			final List<Skill> skills = listModelList.getInnerList();
			if (filter.endsWith("*")) {
				filter = filter.substring(0, filter.length() - 1);
			}
			if (filter.startsWith("*")) {
				filter = filter.substring(1);
				for (int i = 0; i < skills.size(); i++) {
					final Skill skill = skills.get(i);
					if (skill.getName().toLowerCase().contains(filter)) {
						filteredSkills.add(skill);
					}
				}
			} else {
				for (int i = 0; i < skills.size(); i++) {
					final Skill skill = skills.get(i);
					if (skill.getName().toLowerCase().startsWith(filter)) {
						filteredSkills.add(skill);
					}
				}
			}
			final ListModelList filteredModel = new ListModelList(
					filteredSkills);
			list.setModel(filteredModel);
		}
		updateSorting();
	}

	protected SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");

	private class ListDataLoadRunnable implements Runnable {

		@Override
		@Transactional
		public void run() {
			try {
				if (desktop.isServerPushEnabled()) {

					listModelList = new ListModelList();
					listModelList.addAll(skillService.readAll());
					listModelList
							.sort(new SkillsComparator(true, "Name"), true);

					Executions.activate(desktop);
					Events.postEvent(new Event("onLoad", list));
					Executions.deactivate(desktop);
				}
			} catch (final InterruptedException consumed) {
				// do nothing - interruption is consumed
			} catch (final Throwable e) {
				e.printStackTrace();
			} finally {
				desktop.enableServerPush(false);
			}
		}
	}
}
