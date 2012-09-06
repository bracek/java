package com.ixonos.skillnet.web.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.InputEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Column;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.GroupsModel;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Row;
import org.zkoss.zul.RowRenderer;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treecell;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.TreeitemRenderer;
import org.zkoss.zul.Treerow;
import org.zkoss.zul.api.Checkbox;

import com.ixonos.skillnet.logic.bean.CodeTable;
import com.ixonos.skillnet.logic.bean.Node;
import com.ixonos.skillnet.logic.bean.Practicum;
import com.ixonos.skillnet.logic.bean.Skill;
import com.ixonos.skillnet.logic.bean.Users;
import com.ixonos.skillnet.logic.security.SkillnetUser;
import com.ixonos.skillnet.logic.service.CodeTableService;
import com.ixonos.skillnet.logic.service.GroupsService;
import com.ixonos.skillnet.logic.service.NodeService;
import com.ixonos.skillnet.logic.service.PracticumService;
import com.ixonos.skillnet.logic.service.SkillService;
import com.ixonos.skillnet.logic.service.UsersService;
import com.ixonos.skillnet.web.servlet.SkillnetInitServlet;

@Service("PracticumTreeController")
@Scope("prototype")
public class PracticumTreeController extends GenericForwardComposer implements
		TreeitemRenderer, RowRenderer, ComboitemRenderer, ListitemRenderer {

	public PracticumTreeController() {
	}

	@Autowired
	protected SkillnetTreeModel skillnetTreeModel;
	@Resource
	protected CodeTableService codeTableService;
	@Resource
	protected PracticumService practicumService;
	@Resource
	protected UsersService usersService;
	@Resource
	protected GroupsService groupsService;
	@Resource
	protected SkillService skillService;
	@Resource
	protected NodeService nodeService;

	protected Textbox Name;
	protected Textbox Description;
	protected Checkbox Valuable;
	protected Tree tree;
	protected Listbox listSkill;
	protected Grid practicumgrid;
	protected GroupsModel groupsModel;
	protected ListModelList listModelList;
	protected ListModelList listModelCombobox;
	protected ListModelList listModelPracticumGrid;
	protected Label skillabel;
	protected Panel treepanel;
	protected List<CodeTable> codes;
	protected Map<Object, Boolean> openedItems = new HashMap<Object, Boolean>();

	private Skill selectedSkill;
	private Treeitem parentOfSelectedTreeItem;
	private Users user;
	private Map<String, Image> icons = new HashMap<String, Image>();
	private Map<Integer, ArrayList<Treeitem>> treeItems = new HashMap<Integer, ArrayList<Treeitem>>();
	private Thread taskThread;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		prepareIcons();
		skillnetTreeModel.setRootName(groupsService.getTreeRootName(
				((SkillnetUser) SecurityContextHolder.getContext()
						.getAuthentication().getPrincipal()).getUsername(),
				SkillnetInitServlet.props));
		skillnetTreeModel.cleanChildrenMap();
		tree.setModel(skillnetTreeModel);
		tree.setTreeitemRenderer(this);
		listModelPracticumGrid = new ListModelList();
		practicumgrid.setModel(listModelPracticumGrid);
		practicumgrid.setRowRenderer(this);
		codes = codeTableService.getCodes("LEVELS");
		setUser();
		listModelCombobox = new ListModelList();
		listModelCombobox.addAll(codes);
		tree.addEventListener("onSelect", new EventListener() {
			public void onEvent(Event e) throws Exception {
				onTreeItemSelect();
				listSkill.setSelectedItem(null);
			}
		});

		listSkill.setItemRenderer(this);
		listSkill.addEventListener("onLoad", new EventListener() {
			@Override
			public void onEvent(Event e) throws Exception {
				listSkill.setModel(listModelList);
			}
		});
		listSkill.addEventListener("onSelect", new EventListener() {
			@SuppressWarnings("unchecked")
			public void onEvent(Event e) throws Exception {
				int index = listSkill.getSelectedIndex();
				selectedSkill =  (Skill) ((ListModelList) listSkill.getModel()).get(index);
				//if panel is opened
				if(treepanel.isOpen()) {
					ArrayList<Node> parentNodeList = new ArrayList<Node>();
					DetachedCriteria criteria = DetachedCriteria
							.forClass(Node.class);
					criteria.add(Restrictions.eq("skill.id", selectedSkill
							.getSkillId()));
					// get selected skill from listbox from db
					List<Node> nodeList = nodeService.readByCriteria(criteria);
					// get parents of selected node
					String rootName = skillnetTreeModel.getRootName();
					for (int i = 0; i < nodeList.size(); i++) {
						Node node = nodeList.get(i);
						// search until root is found
						while (node.getParentNode() != null
								&& !node.getParentNode().getSkill().getName()
										.equals(rootName)) {
							node = node.getParentNode();
							parentNodeList.add(node);
							// search in bad tree
							if (node.getParentNode() == null) {
								parentNodeList.clear();
								break;
							}
						}
						// good tree found
						if (node.getParentNode() != null)
							break;
					}
					if (parentNodeList.size() > 0) {
						// return if first parent is not yet rendered
						if (!treeItems.containsKey(parentNodeList.get(
								parentNodeList.size() - 1).getSkill().getSkillId())) {
							tree.setSelectedItem(null);
							skillabel.setValue(null);
							listModelPracticumGrid.clear();
							return;
						}
						// set parents node to open
						for (int i = parentNodeList.size() - 1; i > -1; i--)
							treeItems.get(
									parentNodeList.get(i).getSkill().getSkillId())
									.get(0).setOpen(true);
						// select same node in tree as is selected in listbox
						Iterator<Treeitem> nodeListIterator = treeItems.get(
								parentNodeList.get(0).getSkill().getSkillId()).get(
								0).getTreechildren().getChildren().iterator();
						while (nodeListIterator.hasNext()) {
							Treeitem item = nodeListIterator.next();
							if (((Skill) item.getValue()).getSkillId().equals(
									selectedSkill.getSkillId())) {
								tree.setSelectedItem(item);
								onTreeItemSelect();
								break;
							}
						}
					} else {
						// return if selected skill is not yet rendered
						if (!treeItems.containsKey(selectedSkill.getSkillId())) {
							tree.setSelectedItem(null);
							skillabel.setValue(null);
							listModelPracticumGrid.clear();
							return;
						}
						// select one of top nodes
						tree.setSelectedItem(treeItems.get(
								selectedSkill.getSkillId()).get(0));
						onTreeItemSelect();
					}
				}
				//if panel is collapsed
				else {
					setPracticumTable(selectedSkill);
				}
			}
		});
		desktop.enableServerPush(true);
		taskThread = new Thread(new ListDataLoadRunnable());
		taskThread.start();
	}

	private void onTreeItemSelect() {
		Treeitem selectedItem = tree.getSelectedItem();
		parentOfSelectedTreeItem = selectedItem.getParentItem();
		selectedSkill = (Skill) selectedItem.getValue();
		setPracticumTable(selectedSkill);
	}
	
	private void setPracticumTable(Skill skill) {
		skillabel.setValue(skill.getName());
		listModelPracticumGrid.clear();
		listModelPracticumGrid.addAll(practicumService.getPracticum(user,
				skill));
		listModelPracticumGrid.sort(new PracticumRowLabelComparator(true,
				"Date To", false), true);
		if (skill.getValuable())
			listModelPracticumGrid.add(new Practicum());
	}

	private void setUser() {
		if (this.user != null)
			return;

		String userName = ((SkillnetUser) SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal()).getUsername();
		this.user = new Users();
		this.user.setUsername(userName);
		List<Users> users = usersService.readByCriteria(this.user);
		this.user = users.get(0);
	}

	private void prepareIcons() {
		Image image = new Image("/img/icons/empty.png");
		image.setTooltiptext("No practicum");
		icons.put("empty", image);
		image = new Image("/img/icons/beginner.png");
		image.setTooltiptext("Beginner");
		icons.put("beginner", image);
		image = new Image("/img/icons/elementary.png");
		image.setTooltiptext("Elementary");
		icons.put("elementary", image);
		image = new Image("/img/icons/lowerintermeidate.png");
		image.setTooltiptext("Lower Intermediate");
		icons.put("lowerintermediate", image);
		image = new Image("/img/icons/intermediate.png");
		image.setTooltiptext("Intermediate");
		icons.put("intermediate", image);
		image = new Image("/img/icons/upperintermediate.png");
		image.setTooltiptext("Upper Intermediate");
		icons.put("upperintermediate", image);
		image = new Image("/img/icons/advanced.png");
		image.setTooltiptext("Advanced");
		icons.put("advanced", image);
	}

	private Image getImage(Integer level) {
		if (0 == level)
			return (Image) icons.get("beginner").clone();
		else if (1 == level)
			return (Image) icons.get("elementary").clone();
		else if (2 == level)
			return (Image) icons.get("lowerintermediate").clone();
		else if (3 == level)
			return (Image) icons.get("intermediate").clone();
		else if (4 == level)
			return (Image) icons.get("upperintermediate").clone();
		else if (5 == level)
			return (Image) icons.get("advanced").clone();
		else
			return (Image) icons.get("empty").clone();
	}

	private Practicum getYoungestPracticum(Users user, Skill skill) {
		List<Practicum> practicumList = practicumService.getPracticum(user,
				skill);
		if (practicumList.size() != 0) {
			Iterator<Practicum> iterator = practicumList.iterator();
			Practicum yougestPracticum = practicumList.get(0);
			while (iterator.hasNext()) {
				Practicum practicum = iterator.next();
				if (practicum.getDateTo() == null) {
					yougestPracticum = practicum;
					break;
				}
				if (yougestPracticum.getDateTo().compareTo(
						practicum.getDateTo()) < 0)
					yougestPracticum = practicum;
			}
			return yougestPracticum;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private void refreshTree() {
		//if not yet rendered
		if ((ArrayList<Treeitem>) treeItems.get(
				selectedSkill.getSkillId())==null)
			return;
		ArrayList<Treeitem> ti = (ArrayList<Treeitem>) treeItems.get(
				selectedSkill.getSkillId()).clone();
		treeItems.get(selectedSkill.getSkillId()).clear();
		for (Treeitem item : ti) {
			try {
				Node node = new Node();
				node.setSkill((Skill) item.getValue());
				tree.getTreeitemRenderer().render(item, node);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("unchecked")
	private void updateSorting() {
		Iterator<Column> iterator = practicumgrid.getColumns().getChildren()
				.iterator();
		while (iterator.hasNext()) {
			Column column = iterator.next();
			String sortDirection = column.getSortDirection();
			if ("ascending".equals(sortDirection)) {
				listModelPracticumGrid.sort(new PracticumRowLabelComparator(
						true, column.getLabel(), false), true);
				break;
			} else if ("descending".equals(sortDirection)) {
				listModelPracticumGrid.sort(new PracticumRowLabelComparator(
						false, column.getLabel(), false), false);
				break;
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void onChanging$filter(InputEvent e) {		
		System.out.println("onChanging$filter -> START");
		String filter = e.getValue();
		filter = filter.toLowerCase();
		if (filter.equals("") || filter.equals("*")) {
			listSkill.setModel(listModelList);
		} else {
			List<Skill> filteredSkills = new ArrayList<Skill>();
			List<Skill> skills = listModelList.getInnerList();
			if (filter.endsWith("*"))
				filter = filter.substring(0, filter.length() - 1);
			if (filter.startsWith("*")) {
				filter = filter.substring(1);
				for (int i = 0; i < skills.size(); i++) {
					Skill skill = skills.get(i);
					if (skill.getName().toLowerCase().contains(filter))
						filteredSkills.add(skill);
				}
			} else {
				for (int i = 0; i < skills.size(); i++) {
					Skill skill = skills.get(i);
					if (skill.getName().toLowerCase().startsWith(filter))
						filteredSkills.add(skill);
				}
			}
			ListModelList filteredModel = new ListModelList(filteredSkills);
			listSkill.setModel(filteredModel);
		}		
		try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("onChanging$filter -> END");
	}

	private void updateTreeItemMap(Treeitem item, Integer skillId) {
		ArrayList<Treeitem> ti = treeItems.get(skillId);
		if (ti == null)
			ti = new ArrayList<Treeitem>();
		if (!ti.contains(item))
			ti.add(item);
		treeItems.put(skillId, ti);
	}

	private void showErrorMessage(String errorMessage) {
		try {
			Messagebox.show(errorMessage, "Error", Messagebox.OK,
					Messagebox.ERROR);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void render(final Treeitem item, Object data) throws Exception {
		Node node = (Node) data;
		Treecell tcSkillName = new Treecell(node.getSkill().getName());
		Treecell tcIcon = new Treecell();
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
		item.addEventListener("onOpen", new EventListener() {
			@Override
			public void onEvent(Event event) throws Exception {
				if (openedItems.containsKey(item.getValue()))
					openedItems.remove(item.getValue());
				else
					openedItems.put(item.getValue(), Boolean.TRUE);
			}
		});
		if (node.getSkill().getValuable()) {
			Image image;
			setUser();
			Practicum youngestPracticum = getYoungestPracticum(user, node
					.getSkill());
			if (youngestPracticum == null)
				image = getImage(6);
			else
				image = getImage(youngestPracticum.getLevel().getIndex());
			image.setParent(tcIcon);
		}
		// Attach treecells to treerow
		tcSkillName.setParent(tr);
		tcIcon.setParent(tr);
		if (selectedSkill != null
				&& selectedSkill.getSkillId().equals(
						node.getSkill().getSkillId())
				&& (parentOfSelectedTreeItem != null
						&& parentOfSelectedTreeItem
								.equals(item.getParentItem()) || parentOfSelectedTreeItem == null)) {
			tree.setSelectedItem(item);
		}
		if (openedItems.containsKey(node.getSkill()))
			item.setOpen(true);
		else
			item.setOpen(false);

		updateTreeItemMap(item, node.getSkill().getSkillId());

		item.setValue(node.getSkill());
		item.setContext("expandPopup");
	}

	public void onClick$expandMenuItem(Event e) {
		Treeitem selectedItem = tree.getSelectedItem();
		if (selectedItem != null)
			expandTreeItemsRecursively(selectedItem);
	}

	private void expandTreeItemsRecursively(Treeitem treeItem) {
		if (!treeItem.isOpen()) {
			treeItem.setOpen(true);
		}
		if (treeItem.getTreechildren() != null) {
			Object[] items = treeItem.getTreechildren().getItems().toArray();
			for (int i = 0; i < items.length; i++) {
				Object item = items[i];
				if (item instanceof Treeitem) {
					expandTreeItemsRecursively((Treeitem) item);
				}
			}
		}
	}

	@Override
	public void render(Row row, Object data) throws Exception {
		Practicum practicum = (Practicum) data;
		boolean practicumIsNull = false;
		if (practicum.getSkillId() == null)
			practicumIsNull = true;
		row.setValue(practicum);
		final Datebox dateFrom = new Datebox(practicum.getDateFrom());
		dateFrom.addEventListener("onChange", new EventListener() {
			public void onEvent(Event e) {
				Row row = (Row) dateFrom.getParent();
				Practicum practicum = (Practicum) row.getValue();
				if (practicum.getSkillId() != null) {
					if (dateFrom.getValue() == null) {
						showErrorMessage(Labels.getLabel("skillsPracticum.error.insertDateFrom"));
						dateFrom.setValue(practicum.getDateFrom());
					} else if (practicum.getDateTo()!=null && dateFrom.getValue().after(practicum.getDateTo())) {
						showErrorMessage(Labels.getLabel("skillsPracticum.error.badDateOrder"));
						dateFrom.setValue(practicum.getDateFrom());
					} 
					//update only if date was changed
					else if (!dateFrom.getValue().equals(practicum.getDateFrom())) {
						practicum.setDateFrom(dateFrom.getValue());
						practicumService.update(practicum);
						//sorting removed because of problems with datebox popup
						//updateSorting();
					}
				} else {
					practicum.setDateFrom(dateFrom.getValue());
				}
			}
		});
		final Datebox dateTo = new Datebox(practicum.getDateTo());
		dateTo.addEventListener("onChange", new EventListener() {
			public void onEvent(Event e) {
				Row row = (Row) dateTo.getParent();
				Practicum practicum = (Practicum) row.getValue();
				if (practicum.getSkillId() != null) {
					if (practicum.getDateFrom()!=null && dateTo.getValue()!=null && practicum.getDateFrom().after(dateTo.getValue())) {
						showErrorMessage(Labels.getLabel("skillsPracticum.error.badDateOrder"));
						dateTo.setValue(practicum.getDateTo());
					} 
					//update only if date was changed 
					else if ((dateTo.getValue()!=null && !dateTo.getValue().equals(practicum.getDateTo())) ||
							(dateTo.getValue()==null && practicum.getDateTo()!=null)) {
						practicum.setDateTo(dateTo.getValue());
						practicumService.update(practicum);
						//sorting removed because of problems with datebox popup
						//updateSorting();
						refreshTree();
					}
				} else {
					practicum.setDateTo(dateTo.getValue());
				}
			}
		});
		dateFrom.setParent(row);
		dateTo.setParent(row);
		dateFrom.setFormat("MM/yyyy");
		dateTo.setFormat("MM/yyyy");
		dateFrom.setLenient(false);
		dateTo.setLenient(false);
		dateFrom.setTooltiptext(Labels.getLabel("skillsPracticum.dateFrom.tooltip"));
		dateTo.setTooltiptext(Labels.getLabel("skillsPracticum.dateTo.tooltip"));
		final Combobox codeCombobox = new Combobox();
		codeCombobox.setModel(listModelCombobox);
		codeCombobox.setItemRenderer(this);
		codeCombobox.setWidth("90%");
		codeCombobox.setParent(row);
		
		codeCombobox.setReadonly(true);
		codeCombobox.addEventListener("onChange", new EventListener() {
			public void onEvent(Event e) {
				Row row = (Row) codeCombobox.getParent();
				Practicum practicum = (Practicum) row.getValue();
				practicum.setLevel((CodeTable) codeCombobox.getSelectedItem()
						.getValue());
				codeCombobox.setTooltiptext(codeCombobox.getValue());
				if (practicum.getSkillId() != null) {
					practicumService.update(practicum);
					//updateSorting();
					refreshTree();
				}
			}
		});
		if (!practicumIsNull) {
			codeCombobox.setValue(((Practicum) row.getValue()).getLevel()
					.getCode());
			codeCombobox.setTooltiptext(((Practicum) row.getValue()).getLevel()
					.getCode());
		}

		final Textbox descriptionTextbox = new Textbox();
		descriptionTextbox.setWidth("97%");
		descriptionTextbox.setParent(row);
		descriptionTextbox.addEventListener("onChange", new EventListener() {
			public void onEvent(Event e) {
				Row row = (Row) descriptionTextbox.getParent();
				Practicum practicum = (Practicum) row.getValue();
				practicum.setDescription(descriptionTextbox.getText());
				descriptionTextbox.setTooltiptext(descriptionTextbox.getText());
				if (practicum.getSkillId() != null) {
					practicumService.update(practicum);
					refreshTree();
				}
			}
		});
		if (!practicumIsNull) {
			descriptionTextbox.setValue(((Practicum) row.getValue()).getDescription());
			descriptionTextbox.setTooltiptext(((Practicum) row.getValue()).getDescription());
		}
		
		final Button addDeleteButton = new Button(practicumIsNull ? Labels.getLabel("skillsPracticum.add")
				: Labels.getLabel("skillsPracticum.delete"));
		if (!practicumIsNull) {
			addDeleteButton.addEventListener("onClick", new EventListener() {
				public void onEvent(Event e) {
					Row row = (Row) addDeleteButton.getParent();
					Practicum practicum = (Practicum) row.getValue();
					practicumService.delete(practicum);
					listModelPracticumGrid.remove(practicum);
					refreshTree();
				}
			});
		} else {
			addDeleteButton.addEventListener("onClick", new EventListener() {
				public void onEvent(Event e) {
					Row row = (Row) addDeleteButton.getParent();
					Practicum practicum = (Practicum) row.getValue();
					StringBuffer errorMessage = new StringBuffer();
					if (practicum.getDateFrom() == null) {
						errorMessage.append(Labels.getLabel("skillsPracticum.error.insertDateFrom"));
					}
					if (practicum.getLevel() == null) {
						if (errorMessage.length()!=0)
							errorMessage.append("\n");
						errorMessage.append(Labels.getLabel("skillsPracticum.error.insertlevel"));
					}
					if (practicum.getDateFrom()!=null && practicum.getDateTo()!=null && practicum.getDateFrom().after(practicum.getDateTo())) {
						if (errorMessage.length()!=0)
							errorMessage.append("\n");
						errorMessage.append(Labels.getLabel("skillsPracticum.error.badDateOrder"));
					}
					if (errorMessage.length() != 0) {
						showErrorMessage(errorMessage.toString());
					} else {
						practicum.setUserId(user);
						practicum.setSkillId(selectedSkill);
						listModelPracticumGrid.remove((Practicum) row
								.getValue());
						listModelPracticumGrid.add(new Practicum());
						practicumService.create(practicum);
						listModelPracticumGrid.add(practicum);
						updateSorting();
						refreshTree();
					}
				}
			});
		}
		addDeleteButton.setParent(row);
	}

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
					Events.postEvent(new Event("onLoad", listSkill));
					Executions.deactivate(desktop);
				}
			} catch (InterruptedException consumed) {
				// do nothing - interruption is consumed
			} catch (Throwable e) {
				e.printStackTrace();
			} finally {
				desktop.enableServerPush(false);
			}
		}
	}

	@Override
	public void render(Comboitem comboItem, Object data) throws Exception {
		CodeTable item = (CodeTable) data;
		comboItem.setLabel(item.getCode());
		comboItem.setValue(item);
	}

	@Override
	public void render(Listitem listItem, Object data) throws Exception {
		Skill skill = (Skill) data;
		new Listcell(skill.getName()).setParent(listItem);
		new Listcell(skill.getDescription()).setParent(listItem);
	}
}
