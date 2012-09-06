//package com.ixonos.skillnet.web.controllers;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Set;
//
//import javax.annotation.Resource;
//
//import org.hibernate.Criteria;
//import org.hibernate.criterion.Conjunction;
//import org.hibernate.criterion.DetachedCriteria;
//import org.hibernate.criterion.Disjunction;
//import org.hibernate.criterion.Order;
//import org.hibernate.criterion.Projections;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.context.annotation.Scope;
//import org.springframework.context.support.ResourceBundleMessageSource;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.zkoss.util.Locales;
//import org.zkoss.util.resource.Labels;
//import org.zkoss.zk.ui.Component;
//import org.zkoss.zk.ui.Executions;
//import org.zkoss.zk.ui.event.Event;
//import org.zkoss.zk.ui.event.EventListener;
//import org.zkoss.zk.ui.util.GenericForwardComposer;
//import org.zkoss.zul.Button;
//import org.zkoss.zul.Checkbox;
//import org.zkoss.zul.Combobox;
//import org.zkoss.zul.Comboitem;
//import org.zkoss.zul.ComboitemRenderer;
//import org.zkoss.zul.Intbox;
//import org.zkoss.zul.ListModelList;
//import org.zkoss.zul.Listbox;
//import org.zkoss.zul.Listcell;
//import org.zkoss.zul.Listheader;
//import org.zkoss.zul.Listitem;
//import org.zkoss.zul.ListitemRenderer;
//import org.zkoss.zul.SimpleListModel;
//import org.zkoss.zul.Window;
//
//import com.ixonos.skillnet.logic.bean.CodeTable;
//import com.ixonos.skillnet.logic.bean.Practicum;
//import com.ixonos.skillnet.logic.bean.Skill;
//import com.ixonos.skillnet.logic.bean.Users;
//import com.ixonos.skillnet.logic.service.CodeTableService;
//import com.ixonos.skillnet.logic.service.PracticumService;
//import com.ixonos.skillnet.logic.service.SkillService;
//import com.ixonos.skillnet.logic.service.UsersService;
//import com.ixonos.skillnet.web.reporting.SingleReportController;
//import com.ixonos.skillnet.web.usermanagement.find.QueryModel;
//
//@Service("QueryController")
//@Scope("prototype")
//public class QueryController extends GenericForwardComposer implements ListitemRenderer {
//
//	public QueryController() {
//	}
//
//	@Resource
//	protected UsersService userService;
//	@Resource
//	protected SkillService skillService;
//	@Resource
//	protected PracticumService practicumService;
//	@Resource
//	protected CodeTableService codeTableService;
//	@Resource
//	protected ResourceBundleMessageSource messageSource;
//
//
//	private Listbox list;
//	private ListModelList listModelList;
//    private List<Skill> skillList;
//	private List<Users> userList;
//	private List<CodeTable> levels;
//	private Listbox userListbox;
//	private Listbox skillAndLevelGrid;
//	private Checkbox exactCheck;
//
//	private String filterText;
//	// Export button
//	private Button export;
//
//	@Override
//	public void doAfterCompose(Component comp) throws Exception {
//		super.doAfterCompose(comp);
//
//		DetachedCriteria managerCriteria = DetachedCriteria.forClass(Users.class);
//		managerCriteria.createAlias("manager", "m");
//		managerCriteria.add(Restrictions.isNotNull("manager"));
//		managerCriteria.setProjection(Projections.property("manager"));
//		managerCriteria.addOrder(Order.asc("m.username"));
//		managerCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//		userList = userService.readByCriteria(managerCriteria);
//		SimpleListModel userListModel = new SimpleListModel(userList);
//        userListbox.setModel(userListModel);
//        userListbox.setItemRenderer(new ListitemRenderer(){
//        	public void render(Listitem item, Object data) throws Exception {
//                item.setLabel(((Users)data).getHumanReadableUsername());
//                item.setValue(data);
//            }
//        });
//
//		DetachedCriteria levelCriteria = DetachedCriteria.forClass(CodeTable.class, "c");
//        levelCriteria.add(Restrictions.eq("c.groupCode", "LEVELS"));
//        levelCriteria.addOrder(Order.asc("c.index"));
//        levels = codeTableService.readByCriteria(levelCriteria);
//
//		DetachedCriteria skillCriteria = DetachedCriteria.forClass(Skill.class);
//		skillCriteria.add(Restrictions.eq("valuable", true));
//		skillCriteria.addOrder(Order.asc("name"));
//		skillList = skillService.readByCriteria(skillCriteria);
//
//		List<QueryModel> model = new ArrayList<QueryModel>();
//        for (Skill skill : skillList) {
//        	QueryModel qModel = new QueryModel();
//        	qModel.setSkill(skill);
//        	qModel.setLevel(levels.get(0));
//        	model.add(qModel);
//        }
//
//        skillAndLevelGrid.setModel(new SimpleListModel(model));
//        skillAndLevelGrid.setItemRenderer(new ListitemRenderer(){
//        	public void render(Listitem item, Object data) throws Exception {
//                item.setValue(data);
//                final QueryModel qModel = (QueryModel)data;
//
//                // first cell
//                new Listcell(qModel.getSkill().getName()).setParent(item);
//
//                // second cell
//                Listcell lc = new Listcell();
//                lc.setParent(item);
//                final Combobox levelCombo = new Combobox();
//                //SimpleListModel levelComboModel = new SimpleListModel(levels);
//                ListModelList levelComboModel = new ListModelList();
//                levelComboModel.addAll(levels);
//                levelCombo.setModel(levelComboModel);
//                levelCombo.setItemRenderer(new ComboitemRenderer() {
//                    public void render(Comboitem comboitem, Object data) throws Exception {
//                    	levelCombo.setReadonly(true);
//                    	levelCombo.setSelectedIndex(0);
//                        comboitem.setLabel(((CodeTable)data).getCode());
//                        comboitem.setValue(data);
//                    }
//                });
//                levelCombo.addEventListener("onSelect", new EventListener(){
//                    @Override
//                    public void onEvent(Event event) throws Exception {
//                    	qModel.setLevel((CodeTable)levelCombo.getSelectedItem().getValue());
//                    }
//                });
//                levelCombo.setParent(lc);
//
//        		// third cell
//        		Listcell lc3 = new Listcell();
//                lc3.setParent(item);
//                final Intbox lengthBox = new Intbox();
//                lengthBox.addEventListener("onChange", new EventListener(){
//                    @Override
//                    public void onEvent(Event event) throws Exception {
//                    	qModel.setLength(lengthBox.getValue());
//                    }
//                });
//                lengthBox.setParent(lc3);
//            }
//        });
//
//		// result table
//		listModelList = new ListModelList();
//		list.setModel(listModelList);
//		list.setItemRenderer(this);
//	}
//
//	public void onDoubleClick(Event e) throws Exception {
//		Listbox lb = (Listbox)e.getTarget().getFellow("list");
//		Listitem li = lb.getSelectedItem();
//		if (li != null && li.getValue() != null) {
//			String userName = li.getValue().toString();
//			SingleReportController.initReportForUser(lb.getDesktop().getSession(), userName);
//			Window win = (Window) Executions.createComponents("/WEB-INF/jsp/tiles/reporting/single_report.zul", null, null);
//			win.setClosable(true);
//            win.doModal();
//		}
//	}
//
//	@Transactional
//	public void onClick$find(Event e) {
//	    StringBuilder filterStrBuilder = new StringBuilder();
//		Set<?> selManagers = userListbox.getSelectedItems();
//		Disjunction disjunction = Restrictions.disjunction();  // OR
//		// handle skill and other criteria
//		Set<?> selItems = skillAndLevelGrid.getSelectedItems();
//		List<Integer> skillIds = new ArrayList<Integer>();
//		DetachedCriteria dCriteria = DetachedCriteria.forClass(Practicum.class);
//		dCriteria.createAlias("userId", "u");
//		StringBuilder skillFilterStrBuilder = new StringBuilder();
//		String joinStr = exactCheck.isChecked() ? " " + Labels.getLabel("userFind.and") + " " : " " + Labels.getLabel("userFind.or") + " ";
//		for (Object selItem : selItems) {
//		    if (skillFilterStrBuilder.length()>0) {
//                skillFilterStrBuilder.append(joinStr);
//            }
//			Conjunction conjunction = Restrictions.conjunction();  // AND
//			QueryModel query = (QueryModel) ((Listitem)selItem).getValue();
//			skillIds.add(query.getSkill().getSkillId());
//			conjunction.add(Restrictions.eq("skillId", query.getSkill()));
//			conjunction.add(Restrictions.ge("level", query.getLevel()));
//			skillFilterStrBuilder.append(query.getSkill().getName()).append(" - ").append(messageSource.getMessage(query.getLevel().getCode(), null, query.getLevel().getCode(), Locales.getCurrent()));
//	        if(query.getLength() != null){
//	        	int skillLength = query.getLength();
//				if(skillLength <= 0){
//					alert(Labels.getLabel("userFind.zeroWarning"));
//				} else {
//					int days = skillLength * 31;
//					conjunction.add(Restrictions.or(Restrictions.sqlRestriction("{alias}.date_to is null AND (current_date - {alias}.date_from)>=" + days),
//								                    Restrictions.sqlRestriction("{alias}.date_to is not null AND ({alias}.date_to - {alias}.date_from)>=" + days)));
//					skillFilterStrBuilder.append(" (").append(query.getLength()).append("m)");
//				}
//	        }
//            disjunction.add(conjunction);
//	    }
//		if (selItems!=null && !selItems.isEmpty()) {
//		    filterStrBuilder.append(Labels.getLabel("userFind.skillsColon") + " ").append(skillFilterStrBuilder).append("\n");
//		}
//
//		// handle manager criteria
//		if(selManagers != null && selManagers.size() > 0){
//		    StringBuilder managerFilterStrBuilder = new StringBuilder();
//			Object[] managerIds = new Object[selManagers.size()];
//			Iterator<?> it = selManagers.iterator();
//			for (int i=0; i < selManagers.size(); i++) {
//				Users user = (Users) ((Listitem)it.next()).getValue();
//				managerIds[i] = user.getUserId();
//				if (managerFilterStrBuilder.length()>0) {
//	                managerFilterStrBuilder.append(", ");
//	            }
//	            managerFilterStrBuilder.append(user.getHumanReadableUsername());
//			}
//			dCriteria.add(Restrictions.in("u.manager.userId", managerIds));
//			filterStrBuilder.append(Labels.getLabel("userFind.groupManagerColon") + " ").append(managerFilterStrBuilder).append("\n");
//		}
//		dCriteria.add(disjunction);
//		filterText = filterStrBuilder.toString();
//		// get practicum from DB
//		List<Practicum> practList = practicumService.readByCriteria(dCriteria);
//		// filter practicum
//		List<Practicum> result = new ArrayList<Practicum>();
//		if(exactCheck.isChecked() && selItems.size() > 0){
//			result = filterPracticum(practList, skillIds);
//		} else {
//			result = practList;
//		}
//		// set data to model
//		this.listModelList.clear();
//		this.listModelList.addAll(result);
//		// update sorting
//		updateSorting();
//		// disable the Export button if the result table is empty
//		export.setDisabled(listModelList.isEmpty());
//	}
//
//	@Transactional
//	public void onClick$clear(Event e){
//		userListbox.clearSelection();
//		List<QueryModel> model = new ArrayList<QueryModel>();
//        for (Skill skill : skillList) {
//        	QueryModel qModel = new QueryModel();
//        	qModel.setSkill(skill);
//        	qModel.setLevel(levels.get(0));
//        	model.add(qModel);
//        }
//        skillAndLevelGrid.setModel(new SimpleListModel(model));
//        skillAndLevelGrid.clearSelection();
//		listModelList.clear();
//		export.setDisabled(true);
//	}
//
//	@Transactional
//    public void onClick$export(Event e) throws Exception {
//	    Collection<Practicum> practicumCollection = listModelList.getInnerList();
//	    SingleReportController.initReportForPracticums(list.getDesktop().getSession(), practicumCollection, filterText);
//        Window win = (Window) Executions.createComponents("/WEB-INF/jsp/tiles/reporting/single_report.zul", null, null);
//        win.setClosable(true);
//        win.doModal();
//	}
//
//	/**
//	 * This method filter result received from database to match "AND criteria" for skills
//	 * @param practList
//	 * @param skillIds
//	 * @return List<Practicum>
//	 */
//	@SuppressWarnings("unchecked")
//	private List<Practicum> filterPracticum(List<Practicum> practList, List<Integer> skillIds){
//		List<Integer> ids = new ArrayList<Integer>();
//		// get users and skills IDs
//		for(int i=0 ; i < practList.size(); i++){
//			Practicum pract = practList.get(i);
//			Integer userId = pract.getUserId().getUserId();
//			if(!ids.contains(userId)){
//				ids.add(userId);
//			}
//		}
//		// list of Practicum's for each user
//		ArrayList<Practicum>[] userPractList = new ArrayList[ids.size()];
//		for(int i=0 ; i < ids.size() ; i++){
//			userPractList[i] = new ArrayList<Practicum>();  //initialize array
//		}
//
//		for(int i=0 ; i < ids.size(); i++){
//			for(int j=0 ; j < practList.size(); j++){
//				Practicum pract = practList.get(j);
//				if(pract.getUserId().getUserId().equals(ids.get(i))){
//					userPractList[i].add(pract);
//				}
//			}
//		}
//		// filter result (practList) to get only Practicum's with all selected skills (skill="something" AND skill="other_skill" AND ...)
//		List<Practicum> newPractList = new ArrayList<Practicum>();
//		for(int i=0 ; i < userPractList.length; i++){
//			List<Integer> canBeAdded = new ArrayList<Integer>();
//			for(int j=0 ; j < userPractList[i].size(); j++){
//				if(skillIds.contains(userPractList[i].get(j).getSkillId().getSkillId())){
//					canBeAdded.add(userPractList[i].get(j).getSkillId().getSkillId());
//				}
//			}
//			if(canBeAdded.containsAll(skillIds)){
//				newPractList.addAll(userPractList[i]);
//			}
//		}
//		return newPractList;
//	}
//
//	@SuppressWarnings("unchecked")
//	private void updateSorting() {
//		Boolean sorted = false;
//		Iterator<Listheader> iterator = list.getListhead().getChildren().iterator();
//		HashMap<Integer, String> map = new HashMap<Integer, String>();
//		int i = 0;
//		map.put(0, "User");
//		map.put(1, "Skill");
//		map.put(2, "Level");
//		map.put(3, "Date From");
//		map.put(4, "Date To");
//		map.put(5, "Manager");
//		while (iterator.hasNext()) {
//			Listheader column = iterator.next();
//			String sortDirection = column.getSortDirection();
//			if ("ascending".equals(sortDirection)) {
//				listModelList.sort(new PracticumRowLabelComparator(true, map.get(i)), true);
//				sorted = true;
//				break;
//			} else if ("descending".equals(sortDirection)) {
//				listModelList.sort(new PracticumRowLabelComparator(false,  map.get(i)), false);
//				sorted = true;
//				break;
//			}
//			i++;
//		}
//		if (sorted == false)
//			listModelList.sort(new PracticumRowLabelComparator(true, "User"), true);
//	}
//
//	public void render(Listitem listItem, Object data) throws Exception {
//		Practicum pract = (Practicum)data;
//		new Listcell(pract.getUserId().getHumanReadableUsername()).setParent(listItem);
//		new Listcell(pract.getSkillId().getName()).setParent(listItem);
//		new Listcell(pract.getLevel().getCode()).setParent(listItem);
//		new Listcell(pract.getDateFrom().toString()).setParent(listItem);
//		if(pract.getDateTo() != null){
//			new Listcell(pract.getDateTo().toString()).setParent(listItem);
//		} else {
//			new Listcell("").setParent(listItem);
//		}
//		if(pract.getUserId().getManager() != null){
//			new Listcell(pract.getUserId().getManager().getHumanReadableUsername()).setParent(listItem);
//		} else {
//			new Listcell("").setParent(listItem);
//		}
//		listItem.setValue(pract.getUserId().getUsername());
//	}
//}
