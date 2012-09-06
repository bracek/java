//package com.ixonos.skillnet.web.reporting;
//
//import java.lang.reflect.Field;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.Comparator;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import javax.annotation.Resource;
//
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//
//import org.hibernate.Criteria;
//import org.hibernate.criterion.DetachedCriteria;
//import org.hibernate.criterion.Junction;
//import org.hibernate.criterion.Projections;
//import org.hibernate.criterion.Restrictions;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Service;
//import org.zkoss.util.resource.Labels;
//import org.zkoss.zk.ui.Component;
//import org.zkoss.zk.ui.event.DropEvent;
//import org.zkoss.zk.ui.event.Event;
//import org.zkoss.zk.ui.event.EventListener;
//import org.zkoss.zk.ui.event.Events;
//import org.zkoss.zk.ui.util.GenericForwardComposer;
//import org.zkoss.zkex.zul.Jasperreport;
//import org.zkoss.zul.Button;
//import org.zkoss.zul.Combobox;
//import org.zkoss.zul.Comboitem;
//import org.zkoss.zul.ComboitemRenderer;
//import org.zkoss.zul.ListModelList;
//import org.zkoss.zul.Listbox;
//import org.zkoss.zul.Listcell;
//import org.zkoss.zul.Listitem;
//import org.zkoss.zul.ListitemRenderer;
//import org.zkoss.zul.Menu;
//import org.zkoss.zul.Menuitem;
//import org.zkoss.zul.Messagebox;
//import org.zkoss.zul.SimpleListModel;
//import org.zkoss.zul.Tree;
//import org.zkoss.zul.Treecell;
//import org.zkoss.zul.Treeitem;
//import org.zkoss.zul.TreeitemRenderer;
//import org.zkoss.zul.Treerow;
//import org.zkoss.zul.Textbox;
//import org.apache.log4j.Logger;
//
//import com.ixonos.skillnet.logic.bean.CodeTable;
//import com.ixonos.skillnet.logic.bean.Practicum;
//import com.ixonos.skillnet.logic.bean.PracticumView;
//import com.ixonos.skillnet.logic.bean.Skill;
//import com.ixonos.skillnet.logic.bean.Users;
//import com.ixonos.skillnet.logic.service.CodeTableService;
//import com.ixonos.skillnet.logic.service.PracticumService;
//import com.ixonos.skillnet.logic.service.PracticumViewService;
//import com.ixonos.skillnet.logic.service.SkillService;
//import com.ixonos.skillnet.logic.service.UsersService;
//
///**
// *
// * Controller class for report.zul
// *
// * @author molnala
// *
// */
//@Service("ReportController")
//@Scope("prototype")
//public class ReportController extends GenericForwardComposer {
//
//    private static final Logger logger = Logger.getLogger(ReportController.class);
//
//    public static final String USER_REPORT_TEMPLATE = "user_report.jasper";
//    public static final String PRACTICUM_REPORT_TEMPLATE = "practicum_report.jasper";
//
//    public static final String DATE_FORMAT = "dd.MM.yyyy";
//
//    /**
//     * Maps type to allowed operators.
//     */
//    private static final Map<Class<?>, Integer[]> typeOperatorMap = new HashMap<Class<?>, Integer[]>();
//    static {
//        typeOperatorMap.put(String.class, new Integer[]{CriteriaTreeLeaf.OP_EQ, CriteriaTreeLeaf.OP_LIKE, CriteriaTreeLeaf.OP_IN});
//        typeOperatorMap.put(Integer.class, new Integer[]{CriteriaTreeLeaf.OP_EQ, CriteriaTreeLeaf.OP_GE, CriteriaTreeLeaf.OP_GT,
//            CriteriaTreeLeaf.OP_LE, CriteriaTreeLeaf.OP_LT, CriteriaTreeLeaf.OP_IN});
//    }
//
//    @Resource
//    private UsersService usersService;
//
//    @Resource
//    private SkillService skillService;
//
//    @Resource
//    private PracticumService practicumService;
//
//    @Resource
//    private CodeTableService codeTableService;
//
//    @Resource
//    private PracticumViewService practicumViewService;
//
//    private List<Users> gmList;
//    private List<Users> userList;
//    private List<Skill> skillList;
//    private List<CodeTable> levels;
//
//    private Listbox format_listbox;
//    private Listbox gmListbox;
//    private Listbox userListbox;
//    private Listbox skill_and_level_listbox;
//
//    private Jasperreport pracReport;
//
//    private Menuitem tc_changeToAnd;
//    private Menuitem tc_changeToOr;
//    private Menuitem tc_remove;
//    private Menu tc_createRoot;
//    private Menu tc_addNode;
//    private Menu tc_wrapIn;
//
//    private Button saveBtn;
//    private Button addBtn;
//
//    // advanced filtering
//    private Tree criteriaTree;
//
//    private Combobox criteriaVariableCombobox;
//    private Combobox criteriaOperatorCombobox;
//    private Listbox criteriaValueListbox;
//    private Textbox criteriaValueTextbox;
//
//    @Override
//    public void doAfterCompose(Component comp) throws Exception {
//        super.doAfterCompose(comp);
//
//        if (usersService==null) {
//            logger.error("usersService not found!");
//        }
//        if (skillService==null) {
//            logger.error("skillService not found!");
//        }
//
//        comp.addEventListener("onShowUsersReport", new EventListener(){
//            @Override
//            public void onEvent(Event event) throws Exception {
//                showUsersReport();
//            }
//        });
//
//        comp.addEventListener("onShowPracticumsReport", new EventListener(){
//            @Override
//            public void onEvent(Event event) throws Exception {
//                showPracticumsReport();
//            }
//        });
//
//        comp.addEventListener("onGMSelect", new EventListener(){
//            @Override
//            public void onEvent(Event event) throws Exception {
//                updateUsersList();
//            }
//        });
//
//        comp.addEventListener("onVariableSelect", new EventListener(){
//            @Override
//            public void onEvent(Event event) throws Exception {
//                refreshOperatorCombobox();
//                updateEditPanelButtons();
//            }
//        });
//
//        comp.addEventListener("onValueChanged", new EventListener(){
//            @Override
//            public void onEvent(Event event) throws Exception {
//                updateEditPanelButtons();
//            }
//        });
//
//        DetachedCriteria gmCriteria = DetachedCriteria.forClass(Users.class, "u");
//        gmCriteria.add(Restrictions.isNotNull("manager"));
//        gmCriteria.setProjection(Projections.property("manager"));
//        gmCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//        gmList = usersService.readByCriteria(gmCriteria);
//        SimpleListModel gmListModel = new SimpleListModel(getSortedList(gmList, USERS_COMPARATOR));
//        gmListbox.setModel(gmListModel);
//        gmListbox.setItemRenderer(new UserListboxRenderer());
//
//        // fill users list box
//        userList = usersService.readAll();
//        SimpleListModel userListModel = new SimpleListModel(userList);
//        userListbox.setModel(userListModel);
//        userListbox.setItemRenderer(new UserListboxRenderer());
//
//        DetachedCriteria levelCriteria = DetachedCriteria.forClass(CodeTable.class, "c");
//        levelCriteria.add(Restrictions.eq("c.groupCode", "LEVELS"));
//        levels = codeTableService.readByCriteria(levelCriteria);
//
//        skillList = getSortedList(skillService.readAll(), SKILL_COMPARATOR);
//        List<Practicum> prList = new ArrayList<Practicum>();
//        for (Skill skill : skillList) {
//            Practicum pr = new Practicum();
//            pr.setSkillId(skill);
//            pr.setLevel(levels.get(0));
//            prList.add(pr);
//        }
//        skill_and_level_listbox.setModel(new SimpleListModel(prList));
//        skill_and_level_listbox.setItemRenderer(new SkillAndLevelRenderer());
//
//        criteriaTree.setModel(new CriteriaTreeModel());
//        criteriaTree.setTreeitemRenderer(new CriteriaTreeItemRenderer());
//
//        Field[] prViewFields = PracticumView.class.getDeclaredFields();
//        SimpleListModel criteriaVariableComboboxModel = new SimpleListModel(prViewFields);
//        criteriaVariableCombobox.setModel(criteriaVariableComboboxModel);
//        criteriaVariableCombobox.setItemRenderer(new ConditionVariableComboboxRenderer());
//
//        criteriaOperatorCombobox.setModel(new ListModelList());
//        criteriaOperatorCombobox.setItemRenderer(new ConditionOperatorComboboxRenderer());
//
//        criteriaValueListbox.setModel(new ListModelList());
//    }
//
//    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    //
//    // Criteria tree context menu related event methods
//    //
//
//    public void onCreateRootOr() {
//        CriteriaTreeNode root = new CriteriaTreeNode(AbstractCriteriaTreeItem.OP_OR);
//        ((CriteriaTreeModel)criteriaTree.getModel()).setRoot(root);
//        criteriaTree.setModel(criteriaTree.getModel());
//        setSelectedItem(root);
//    }
//
//    public void onCreateRootAnd() {
//        CriteriaTreeNode root = new CriteriaTreeNode(AbstractCriteriaTreeItem.OP_AND);
//        ((CriteriaTreeModel)criteriaTree.getModel()).setRoot(root);
//        criteriaTree.setModel(criteriaTree.getModel());
//        setSelectedItem(root);
//    }
//
//    public void onAddOrNode() {
//        addNewCriteriaTreeNode(AbstractCriteriaTreeItem.OP_OR);
//    }
//
//    public void onAddAndNode() {
//        addNewCriteriaTreeNode(AbstractCriteriaTreeItem.OP_AND);
//    }
//
//    public void onChangeToAnd() {
//        changeSelectedNodeOperator(AbstractCriteriaTreeItem.OP_AND);
//    }
//
//    public void onChangeToOr() {
//        changeSelectedNodeOperator(AbstractCriteriaTreeItem.OP_OR);
//    }
//
//    public void onRemoveSelectedNode() {
//        removeSelectedTreeNode();
//    }
//
//    public void onWrapInAnd() {
//        wrapSelectedItem(AbstractCriteriaTreeItem.OP_AND);
//    }
//
//    public void onWrapInOr() {
//        wrapSelectedItem(AbstractCriteriaTreeItem.OP_OR);
//    }
//
//
//    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    //
//    // Criteria editing area button event methods
//    //
//
//    public void onAddBtn() {
//        addNewConditionTreeLeaf();
//    }
//
//    public void onSaveBtn() {
//        updateSelectedTreeLeaf();
//    }
//
//    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    //
//    // Criteria tree event methods
//    //
//
//    public void onContextOpen() {
//        Treeitem selectedItem = criteriaTree.getSelectedItem();
//        boolean emptyModel = ((CriteriaTreeModel)criteriaTree.getModel()).isEmpty();
//
//        tc_createRoot.setVisible(selectedItem==null && emptyModel);
//        tc_remove.setVisible(!(selectedItem==null));
//        tc_wrapIn.setVisible(!(selectedItem==null));
//
//        if (selectedItem==null) {
//            tc_addNode.setVisible(false);
//            tc_changeToOr.setVisible(false);
//            tc_changeToAnd.setVisible(false);
//        }
//        else if (selectedItem.getValue() instanceof CriteriaTreeNode) {     // in case of node
//            tc_addNode.setVisible(true);
//
//            if (((CriteriaTreeNode)selectedItem.getValue()).getOperator()==AbstractCriteriaTreeItem.OP_AND) {
//                tc_changeToOr.setVisible(true);
//                tc_changeToAnd.setVisible(false);
//
//            }
//            else if (((CriteriaTreeNode)selectedItem.getValue()).getOperator()==AbstractCriteriaTreeItem.OP_OR){
//                tc_changeToOr.setVisible(false);
//                tc_changeToAnd.setVisible(true);
//            }
//        }
//        else {  // leaf
//            tc_addNode.setVisible(false);
//            tc_changeToOr.setVisible(false);
//            tc_changeToAnd.setVisible(false);
//        }
//    }
//
//    public void onItemSelect() {
//        // get the selected tree item
//        Treeitem selectedItem = criteriaTree.getSelectedItem();
//        if (selectedItem.getValue() instanceof CriteriaTreeLeaf) {     // in case of leaf
//            // get the condition variable
//            String variable = ((CriteriaTreeLeaf)selectedItem.getValue()).getVariable();
//            // TODO not found variable
//            for (Object item : criteriaVariableCombobox.getItems()) {
//                if (variable.equals(((Field)((Comboitem)item).getValue()).getName())) {
//                    criteriaVariableCombobox.setSelectedItem((Comboitem)item);
//                    // update the operator combobox and values list box
//                    refreshOperatorCombobox((CriteriaTreeLeaf)selectedItem.getValue(), (Field)((Comboitem)item).getValue());
//                    break;
//                }
//            }
//        }
//        else if (selectedItem.getValue() instanceof CriteriaTreeNode) {     // in case of node
//            criteriaVariableCombobox.setValue("");
//            criteriaOperatorCombobox.setModel(new ListModelList());
//            criteriaOperatorCombobox.setSelectedIndex(-1);
//            criteriaValueListbox.setModel(new ListModelList());
//            criteriaValueTextbox.setText(null);
//        }
//
//        updateEditPanelButtons();
//    }
//
//    /**
//     * Returns the selected criteria tree item. If none is selected, then shows an error message and returns null.
//     *
//     * @return the selected criteria tree item
//     */
//    private Treeitem getSelectedItem() {
//        Treeitem selectedTreeItem = criteriaTree.getSelectedItem();
//        if (selectedTreeItem==null) {
//            try {
//                Messagebox.show(Labels.getLabel("customReport.error.selItem"), Labels.getLabel("customReport.error"), Messagebox.OK, Messagebox.EXCLAMATION);
//            }
//            catch(InterruptedException e) {
//            }
//        }
//        return selectedTreeItem;
//    }
//
//    /**
//     * Returns the array of selected criteria values.
//     *
//     * @return array of selected criteria values
//     */
//    private Object[] getSelectedValues() {
//        Object[] selectedValues = null;
//        if (criteriaValueListbox.isVisible()) {
//            Set<?> selectedItems = criteriaValueListbox.getSelectedItems();
//            selectedValues = new Object[selectedItems.size()];
//            int index = 0;
//            for (Object selectedItem : selectedItems) {
//                 selectedValues[index++] = ((Listitem)selectedItem).getValue();
//            }
//        }
//        else if (criteriaValueTextbox.isVisible()) {
//            selectedValues = new Object[]{criteriaValueTextbox.getText()};
//        }
//
//        return selectedValues;
//    }
//
//    /**
//     * Sets to visible the value textbox or listbox.
//     *
//     * @param visibility true - the textbox will be visible, otherwise the list box
//     */
//    private void setValueTextboxVisibility(boolean visibility) {
//        criteriaValueTextbox.setVisible(visibility);
//        criteriaValueListbox.setVisible(!visibility);
//    }
//
//
//    /**
//     * Returns a new CriteriaTreeLeaf object according to the selected criteria variable, operator and values.
//     *
//     * @return
//     */
//    public CriteriaTreeLeaf getNewConditionTreeLeaf() {
//        String selectedVariable = ((Field)criteriaVariableCombobox.getSelectedItem().getValue()).getName();
//        Integer selectedOperator = (Integer)criteriaOperatorCombobox.getSelectedItem().getValue();
//        Object[] selectedValues = getSelectedValues();
//
//        if (selectedVariable==null || selectedOperator==null || selectedValues==null || selectedValues.length==0) {
//            logger.error("Error: selectedVariable=" + selectedVariable + ", selectedOperator=" + selectedOperator + ",selectedValues" + selectedValues);
//            return null;
//        }
//
//        return new CriteriaTreeLeaf(selectedVariable, selectedOperator, selectedValues);
//    }
//
//    /**
//     * Updates the criteria edit panel buttons visibility according to the selected criteria variable, operator and value(s).
//     */
//    public void updateEditPanelButtons() {
//        // get selected criteria variable
//        Comboitem selectedVariableItem = criteriaVariableCombobox.getSelectedItem();
//        String selectedVariable = (selectedVariableItem!=null && selectedVariableItem.getValue() instanceof Field)
//            ? ((Field)criteriaVariableCombobox.getSelectedItem().getValue()).getName() : null;
//        // get selected criteria operator
//        Integer selectedOperator = criteriaOperatorCombobox.getSelectedItem()!=null ?
//                (Integer)criteriaOperatorCombobox.getSelectedItem().getValue() : null;
//        // get selected criteria values
//        Object[] selectedValues = getSelectedValues();
//        // get selected criteria tree item
//        Treeitem selectedTreeItem = criteriaTree.getSelectedItem();
//
//        if (selectedVariable==null || selectedOperator==null || selectedValues==null || selectedValues.length==0) {
//            addBtn.setDisabled(true);
//            saveBtn.setDisabled(true);
//        }
//        else {
//            if (selectedTreeItem==null) {
//                addBtn.setDisabled(false);
//                saveBtn.setDisabled(true);
//            }
//            else if (selectedTreeItem.getValue() instanceof CriteriaTreeLeaf) {
//                CriteriaTreeModel treeModel = (CriteriaTreeModel)criteriaTree.getModel();
//                // enable only in case if the parent node of the selected one is not the root one
//                addBtn.setDisabled(
//                        treeModel.getParent((CriteriaTreeLeaf)selectedTreeItem.getValue()).equals(treeModel.getRoot()));
//                saveBtn.setDisabled(false);
//            }
//            else if (selectedTreeItem.getValue() instanceof CriteriaTreeNode) {
//                addBtn.setDisabled(false);
//                saveBtn.setDisabled(true);
//            }
//        }
//    }
//
//    /**
//     * Updates the selected criteria tree leaf according to the selected criteria variable, operator and value(s).
//     */
//    public void updateSelectedTreeLeaf() {
//        Treeitem selectedTreeItem = getSelectedItem();
//        if (selectedTreeItem==null) {
//            logger.error("Internal error: Selected criteria tree item can't be null!");
//            return;
//        }
//
//        CriteriaTreeLeaf newLeaf = getNewConditionTreeLeaf();
//        if (newLeaf!=null && selectedTreeItem.getValue() instanceof CriteriaTreeLeaf) {
//            newLeaf = ((CriteriaTreeModel)criteriaTree.getModel()).updateChild((CriteriaTreeLeaf)selectedTreeItem.getValue(), newLeaf);
//            criteriaTree.setModel(criteriaTree.getModel());
//            setSelectedItem(newLeaf);
//        }
//    }
//
//    /**
//     * Creates a new CriteriaTreeLeaf object according to the selected criteria variable, operator and value(s)
//     * and adds it to the selected criteria tree node (parent node of the selected criteria tree leaf)
//     */
//    public void addNewConditionTreeLeaf() {
//        CriteriaTreeItem newChild = getNewConditionTreeLeaf();
//        if (newChild==null) {
//            logger.error("Can't create new CriteriaTreeLeaf object!");
//            return;
//        }
//
//        addNewCriteriaTreeItem(newChild);
//    }
//
//    /**
//     * Adds the newItem to the currently selected criteria tree node or in case of leaf to the selected leaf parent node.
//     *
//     * @param newItem the new item to be added to the tree
//     */
//    public void addNewCriteriaTreeItem(CriteriaTreeItem newItem) {
//        if (newItem==null) {
//            logger.error("Can't add null item to the tree!");
//            return;
//        }
//
//        Treeitem selectedTreeItem = criteriaTree.getSelectedItem();
//        // the tree doesn't have any item -> create root item
//        if (selectedTreeItem==null && ((CriteriaTreeModel)criteriaTree.getModel()).isEmpty()) {
//            ((CriteriaTreeModel)criteriaTree.getModel()).setRoot(newItem);
//        }
//        else if (selectedTreeItem!=null) {
//            CriteriaTreeItem selectedItemValue = (CriteriaTreeItem)selectedTreeItem.getValue();
//            if (selectedItemValue instanceof CriteriaTreeNode) {
//                ((CriteriaTreeModel)criteriaTree.getModel()).addChild((CriteriaTreeNode)selectedItemValue, newItem);
//            }
//            else if (selectedItemValue instanceof CriteriaTreeLeaf) {
//                CriteriaTreeNode parentNode = ((CriteriaTreeModel)criteriaTree.getModel()).getParent(selectedItemValue);
//                ((CriteriaTreeModel)criteriaTree.getModel()).addChild(parentNode, newItem);
//            }
//        }
//        else {
//            try {
//                Messagebox.show(Labels.getLabel("customReport.error.selItem"), Labels.getLabel("customReport.error"), Messagebox.OK, Messagebox.EXCLAMATION);
//            }
//            catch(InterruptedException e) {
//            }
//        }
//        criteriaTree.setModel(criteriaTree.getModel());
//        setSelectedItem(newItem);
//    }
//
//    /**
//     * Sets the related criteria tree item according to the given data tree model
//     * and updates the criteria edit panel buttons.
//     *
//     * @param item criteria tree model item
//     */
//    private void setSelectedItem(CriteriaTreeItem item) {
//        Treeitem treeItem = getTreeItem(item);
//        if (treeItem!=null) {
//            criteriaTree.setSelectedItem(treeItem);
//            updateEditPanelButtons();
//        }
//    }
//
//    /**
//     * Finds the Treeitem associated with the given CriteriaTreeItem
//     *
//     * @param modelItem
//     * @return
//     */
//    private Treeitem getTreeItem(CriteriaTreeItem modelItem) {
//        if (modelItem==null) {
//            return null;
//        }
//
//        Treeitem foundItem = null;
//
//        for (Object itemObject : criteriaTree.getItems()) {
//            if (itemObject instanceof Treeitem
//                    && ((Treeitem)itemObject).getValue() instanceof CriteriaTreeItem
//                    && modelItem.equals(((Treeitem)itemObject).getValue())) {
//                foundItem = (Treeitem)itemObject;
//                break;
//            }
//        }
//
//        return foundItem;
//    }
//
//    /**
//     * Creates a new CriteriaTreeNode object according to the operator argument
//     * and adds it to the selected criteria tree node (parent node of the selected criteria tree leaf)
//     */
//    public void addNewCriteriaTreeNode(int operator) {
//        CriteriaTreeItem newChild = new CriteriaTreeNode(operator);
//        addNewCriteriaTreeItem(newChild);
//        setSelectedItem(newChild);
//    }
//
//    /**
//     * Removes the selected tree item (node including the subtree)
//     */
//    public void removeSelectedTreeNode() {
//        Treeitem selectedTreeItem = getSelectedItem();
//        if (selectedTreeItem==null) {
//            return;
//        }
//        CriteriaTreeItem selectedNode = (CriteriaTreeItem)selectedTreeItem.getValue();
//        CriteriaTreeItem parentNode = ((CriteriaTreeModel)criteriaTree.getModel()).removeChild(selectedNode);
//        criteriaTree.setModel(criteriaTree.getModel());
//        setSelectedItem(parentNode);
//    }
//
//    /**
//     * Changes the selected criteria tree node operator to newOperator.
//     *
//     * @param newOperator
//     */
//    public void changeSelectedNodeOperator(int newOperator) {
//        Treeitem selectedTreeItem = getSelectedItem();
//        if (selectedTreeItem!=null && selectedTreeItem.getValue() instanceof CriteriaTreeNode) {
//            CriteriaTreeNode newNode = new CriteriaTreeNode(newOperator);
//            newNode = ((CriteriaTreeModel)criteriaTree.getModel()).updateChild((CriteriaTreeNode)selectedTreeItem.getValue(), newNode);
//            criteriaTree.setModel(criteriaTree.getModel());
//            setSelectedItem(newNode);
//        }
//    }
//
//    /**
//     * Wraps the selected criteria tree item in a new node with the given operator.
//     *
//     * @param operator the new wrapper node's operator
//     */
//    public void wrapSelectedItem(int operator) {
//        Treeitem selectedTreeItem = getSelectedItem();
//        if (selectedTreeItem==null) {
//            return;
//        }
//
//        CriteriaTreeItem treeItem = (CriteriaTreeItem) selectedTreeItem.getValue();
//        treeItem = ((CriteriaTreeModel)criteriaTree.getModel()).wrapItem(treeItem, new CriteriaTreeNode(operator));
//        criteriaTree.setModel(criteriaTree.getModel());
//        setSelectedItem(treeItem);
//    }
//
//    /**
//     * Refreshes the operator combobox and values listbox content according to the variable combobox selected item.
//     *
//     */
//    public void refreshOperatorCombobox() {
//        // get the selected variable
//        Field selectedField = (Field)criteriaVariableCombobox.getSelectedItem().getValue();
//        // get the selected variable's type
//        Class<?> selectedFieldType = selectedField.getType();
//        // get the list of allowed operators for the selected variable type and alter data model
//        ((ListModelList)criteriaOperatorCombobox.getModel()).clear();
//        ((ListModelList)criteriaOperatorCombobox.getModel()).addAll(Arrays.asList(typeOperatorMap.get(selectedFieldType)));
//        criteriaOperatorCombobox.setSelectedIndex(-1); // clear selection
//
//        clearCriteriaValue();
//    }
//
//    /**
//     * Refreshes the operator combobox and values listbox content according to the <code>selectedField</code>
//     * and sets the selected operator and values according to the <code>leaf</code> criteria tree leaf.
//     *
//     * @param leaf
//     * @param selectedField
//     */
//    public void refreshOperatorCombobox(CriteriaTreeLeaf leaf, Field selectedField) {
//        // OPERATOR
//        Class<?> selectedFieldType = selectedField.getType();
//        ListModelList newModel = new ListModelList(Arrays.asList(typeOperatorMap.get(selectedFieldType)));
//        criteriaOperatorCombobox.setModel(newModel);
//        int operator = leaf.getOperator();  // get the condition operator
//        boolean selectedAny = false;
//        for (int i=0; i<newModel.size() ;i++) {
//            Comboitem newItem = new Comboitem(CriteriaTreeLeaf.getOperatorAsString((Integer)newModel.getElementAt(i)));
//            newItem.setValue(newModel.getElementAt(i));
//            criteriaOperatorCombobox.getItems().add(newItem);
//            if (operator==(Integer)newModel.getElementAt(i)) {
//                criteriaOperatorCombobox.setSelectedItem(newItem);
//                selectedAny = true;
//            }
//        }
//        if (!selectedAny) {
//            criteriaOperatorCombobox.setSelectedIndex(-1);
//        }
//        else {
//            onOperatorChanged();    // enable/disable multiselect in values listbox
//        }
//
//        // VALUE
//        Object[] values = leaf.getValues();
//        setValueTextboxVisibility(operator==AbstractCriteriaTreeItem.OP_LIKE);
//        if (operator==AbstractCriteriaTreeItem.OP_LIKE) {
//            criteriaValueTextbox.setText(values.length>0 ? (String)values[0] : null);
//        }
//        else {
//            DetachedCriteria dc = DetachedCriteria.forClass(PracticumView.class);
//            dc.setProjection(Projections.property(selectedField.getName()));
//            dc.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//            List<?> result = practicumViewService.readByCriteria(dc);
//            Collection<?> disctinctValueList = distinctFilter(result);
//            List<?> sortedList = getSortedList(disctinctValueList);
//            ((ListModelList)criteriaValueListbox.getModel()).clear();
//            ((ListModelList)criteriaValueListbox.getModel()).addAll(sortedList);
//            criteriaValueListbox.clearSelection();
//            criteriaValueListbox.renderAll();
//
//            List<Object> valuesList = Arrays.asList(values);
//            criteriaValueListbox.clearSelection();
//            for (Object item : criteriaValueListbox.getItems()) {
//                if (valuesList.contains(((Listitem)item).getValue())) {
//                    criteriaValueListbox.addItemToSelection((Listitem)item);
//                }
//            }
//        }
//    }
//
//    /**
//     * Clears all criteria value related componenets.
//     */
//    private void clearCriteriaValue() {
//        criteriaValueTextbox.setText(null);
//        criteriaValueListbox.setModel(new ListModelList());
//    }
//
//    /**
//     * Enables or disables multi-select in the values list box according to the selected operator.
//     * Only in case of IN operator is enabled multiselect.
//     *
//     */
//    public void onOperatorChanged() {
//        if (criteriaOperatorCombobox.getSelectedItem()!=null && criteriaOperatorCombobox.getSelectedItem().getValue() instanceof Integer) {
//            int selectedOperator = (Integer)criteriaOperatorCombobox.getSelectedItem().getValue();
//            setValueTextboxVisibility(selectedOperator==AbstractCriteriaTreeItem.OP_LIKE);
//            criteriaValueListbox.setMultiple(selectedOperator==AbstractCriteriaTreeItem.OP_IN);
//
//            if (selectedOperator==AbstractCriteriaTreeItem.OP_LIKE) {
//                criteriaValueTextbox.setText(null);
//            }
//            else {
//              // get the list of possible values for the selected variable(column)
//              Field selectedField = (Field)criteriaVariableCombobox.getSelectedItem().getValue();
//              DetachedCriteria dc = DetachedCriteria.forClass(PracticumView.class);
//              dc.setProjection(Projections.property(selectedField.getName()));
//              dc.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
//              List<?> result = practicumViewService.readByCriteria(dc);
//              Collection<?> disctinctValueList = distinctFilter(result);    // filter distinct values
//              List<?> sortedList = getSortedList(disctinctValueList);
//              ((ListModelList)criteriaValueListbox.getModel()).clear();
//              ((ListModelList)criteriaValueListbox.getModel()).addAll(sortedList);
//              criteriaValueListbox.clearSelection();
//              criteriaValueListbox.renderAll();
//            }
//        }
//        updateEditPanelButtons();
//    }
//
//    /**
//     * Returns the list of distinct values from the input list.
//     *
//     * @param <T> list element type
//     * @param listToBeFiltered list containing elements of type T (also duplicates)
//     *
//     * @return list containing the distict valuie from the </code>listToBeFiltered</code>
//     */
//    public static <T> Collection<T> distinctFilter(Collection<T> listToBeFiltered) {
//        Set<T> distinctSet = new HashSet<T>();
//        for (T item : listToBeFiltered) {
//            distinctSet.add(item);
//        }
//        return distinctSet;
//    }
//
//    /**
//     * Converts an unsorted collection into sorted List.
//     *
//     * @param <T> element type
//     * @param unsortedCollection the unsorted collection to be sorted
//     * @return List of sorted elements
//     */
//    public static List<?> getSortedList(Collection<?> unsortedCollection) {
//        // convert the Collection to array
//        Object[] array = unsortedCollection.toArray(new Object[unsortedCollection.size()]);
//        Arrays.sort(array);     // sort the array
//        return Arrays.asList(array);    // convert the sorted array to List
//    }
//
//    /**
//     * Converts an unsorted List into sorted List.
//     *
//     * @param unsortedCollection
//     * @param comparator
//     * @return
//     */
//    public static <T> List<T> getSortedList(List<T> unsortedCollection, Comparator<T> comparator) {
//        Collections.sort(unsortedCollection, comparator);
//        return unsortedCollection;
//    }
//
//    /**
//     * Comparator for <CODE>Skill</CODE> objects.
//     * Used to have the skill list in alphabet order.
//     */
//    static final Comparator<Skill> SKILL_COMPARATOR = new Comparator<Skill>() {
//        @Override
//        public int compare(Skill o1, Skill o2) {
//            String skillname1 = o1.getName();
//            String skillname2 = o2.getName();
//            return skillname1.compareToIgnoreCase(skillname2);
//        }
//    };
//
//    /**
//     * Comparator for <CODE>Users</CODE> objects.
//     * Used to have the users and GM list in alphabet order.
//     */
//    static final Comparator<Users> USERS_COMPARATOR = new Comparator<Users>() {
//        @Override
//        public int compare(Users o1, Users o2) {
//            String username1 = o1.getSurname();
//            String username2 = o2.getSurname();
//            return username1.compareToIgnoreCase(username2);
//        }
//    };
//
//
//    /**
//     * Shows user-based report according to advanced filters.
//     */
//    public void onShowUsersReportForAdvancedFilters() {
//        showReportForAdvancedFilters(USER_REPORT_TEMPLATE);
//    }
//
//    /**
//     * Shows practicum-based report according to advanced filters.
//     */
//    public void onShowPracticumReportForAdvancedFilters() {
//        showReportForAdvancedFilters(PRACTICUM_REPORT_TEMPLATE);
//    }
//
//    /**
//     * Renders criteria tree model into String representation.
//     *
//     * @return String representation of the criteria tree model
//     */
//    private String renderCriteriaTreeIntoString() {
//        StringBuilder strBuilder = new StringBuilder();
//        strBuilder.append(((CriteriaTreeItem)criteriaTree.getModel().getRoot()).toStringIncludingSubCritera());
//        return strBuilder.toString();
//    }
//
//    /**
//     * Shows report according to the given report template and advanced filters.
//     *
//     * @param reportTemplate template to be used for the shown report
//     */
//    public void showReportForAdvancedFilters(String reportTemplate) {
//        if (((CriteriaTreeModel)criteriaTree.getModel()).isEmpty()) {
//            try {
//                Messagebox.show(Labels.getLabel("customReport.warning.buildCriteria"), Labels.getLabel("customReport.warning"), Messagebox.OK, Messagebox.EXCLAMATION);
//            }
//            catch (InterruptedException e) {
//            }
//            return;
//        }
//
//        String filterText = renderCriteriaTreeIntoString();
//        DetachedCriteria dc = ((CriteriaTreeModel)criteriaTree.getModel()).toDetachedCriteria();
//        Collection<PracticumView> pracViewResult = practicumViewService.readByCriteria(dc);
//        Collection<Practicum> practicumCollection = null;
//        if (pracViewResult!=null && !pracViewResult.isEmpty()) {
//            Collection<Integer> pracIdCollection = new HashSet<Integer>();
//            for (PracticumView prView : pracViewResult) {
//                pracIdCollection.add(prView.getId());
//            }
//            practicumCollection = practicumService.readByCriteria(
//                    DetachedCriteria.forClass(Practicum.class).add(Restrictions.in("practicumId", pracIdCollection)));
//        }
//
//        showReport(reportTemplate, practicumCollection, filterText);
//    }
//
//    /**
//     * Show report with the specified template, data source colleaction and filter text.
//     *
//     * @param reportTemplate Jasper report template
//     * @param dataSourceCollection data source collection
//     * @param filterText filter text
//     */
//    private void showReport(String reportTemplate, Collection<?> dataSourceCollection, String filterText) {
//        //Preparing parameters
//        Map<String, String> parameters = new HashMap<String, String>();
//        parameters.put("filterText", filterText);
//        parameters.put("dateFormat", DATE_FORMAT);
//        pracReport.setSrc(reportTemplate);
//        pracReport.setParameters(parameters);
//        JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(dataSourceCollection, false);
//        pracReport.setDatasource(source);
//        pracReport.setType((String) format_listbox.getSelectedItem().getValue());
//    }
//
//    /**
//     * Advanced filtering - Criteria tree item renderer.
//     *
//     * @author molnala
//     *
//     */
//    class CriteriaTreeItemRenderer implements TreeitemRenderer {
//
//        /**
//         * Renders the treeItem to String representation.
//         *
//         * @param treeItem
//         * @return
//         */
//        private String renderTreeItem(CriteriaTreeItem treeItem) {
//            if (treeItem instanceof CriteriaTreeNode) {
//                return renderTreeNode((CriteriaTreeNode)treeItem);
//            }
//            else if (treeItem instanceof CriteriaTreeLeaf) {
//                return renderTreeLeaf((CriteriaTreeLeaf)treeItem);
//            }
//
//            return "";
//        }
//
//        /**
//         * Renders the treeNode to String representation.
//         *
//         * @param treeNode
//         * @return
//         */
//        private String renderTreeNode(CriteriaTreeNode treeNode) {
//            return new StringBuilder().
//                append(treeNode.getOperatorAsString()).
//                append(" (").append(treeNode.getChildCount()).append(")").
//                toString();
//        }
//
//        /**
//         * Renders the treeLeaf to String representation.
//         *
//         * @param treeLeaf
//         * @return
//         */
//        private String renderTreeLeaf(CriteriaTreeLeaf treeLeaf) {
//            StringBuilder strBuilder = new StringBuilder();
//            strBuilder.append(treeLeaf.getVariable()).append(" ");
//            strBuilder.append(treeLeaf.getOperatorAsString()).append(" ");
//            strBuilder.append(treeLeaf.getValueAsString());
//            return strBuilder.toString();
//        }
//
//        @Override
//        public void render(Treeitem item, Object data) throws Exception {
//            item.setOpen(true);
//            if (data instanceof CriteriaTreeItem) {
//                Treerow treerow = item.getTreerow();
//                if (treerow==null) {
//                    treerow = new Treerow();
//                    treerow.setDraggable("true");
//                    if (data instanceof CriteriaTreeNode) {
//                        treerow.setDroppable("true");
//                    }
//                    treerow.addEventListener(Events.ON_DROP, new EventListener() {
//                        @Override
//                        public void onEvent(Event event) throws Exception {
//                            Component dragged = ((DropEvent) event).getDragged();
//                            if (dragged instanceof Treerow) {
//                                Treeitem tisrc = (Treeitem) ((Treerow) dragged).getParent();
//                                Treeitem tidest = (Treeitem) ((Treerow) event.getTarget()).getParent();
//                                CriteriaTreeItem srcItem = (CriteriaTreeItem)tisrc.getValue();
//                                CriteriaTreeItem destItem = (CriteriaTreeItem)tidest.getValue();
//
//                                if (srcItem instanceof CriteriaTreeNode
//                                        && ((CriteriaTreeModel)criteriaTree.getModel()).isInSubTree((CriteriaTreeNode)srcItem, destItem)) {
//                                    Messagebox.show(Labels.getLabel("customReport.warning.moveNotPossible"), Labels.getLabel("customReport.warning"), 1, "OK");
//                                }
//                                else {
//                                    srcItem = ((CriteriaTreeModel)criteriaTree.getModel()).moveItem(srcItem, destItem);
//                                    criteriaTree.setModel(criteriaTree.getModel());
//                                    setSelectedItem(srcItem);
//                                }
//                            }
//                        }
//                    });
//                    treerow.setParent(item);
//                }
//                else {
//                    treerow.getChildren().clear();
//                }
//
//                Treecell cell = new Treecell(renderTreeItem((CriteriaTreeItem)data));
//                cell.setParent(treerow);
//
//                item.setValue(data);
//            }
//        }
//    }
//
//
//    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//    //
//    // Simple filtering related methods.
//    //
//
//    /**
//     * Updates the users list according to the selected group manager.
//     *
//     */
//    public void updateUsersList() {
//        Collection<Users> selectedGMCollection = getSelectedGMList();
//        if (selectedGMCollection!=null && !selectedGMCollection.isEmpty()) {
//            DetachedCriteria gmCriteria = DetachedCriteria.forClass(Users.class, "u");
//            gmCriteria.add(Restrictions.in("manager", selectedGMCollection));
//            userList = usersService.readByCriteria(gmCriteria);
//        }
//        else {
//            userList = usersService.readAll();
//        }
//        SimpleListModel userListModel = new SimpleListModel(getSortedList(userList, USERS_COMPARATOR));
//        userListbox.setModel(userListModel);
//        userListbox.setItemRenderer(new UserListboxRenderer());
//    }
//
//    /**
//     * Returns the collection of levels which are above the specified.
//     *
//     * @param minLevel
//     * @return
//     */
//    private Collection<CodeTable> getHigherLevels(CodeTable minLevel) {
//        Collection<CodeTable> higherLevels = new HashSet<CodeTable>();
//        for (CodeTable level : levels) {
//            if (level.getIndex()>=minLevel.getIndex()) {
//                higherLevels.add(level);
//            }
//        }
//        return higherLevels;
//    }
//
//    /**
//     * Returns collection of selected users.
//     *
//     * @return collection of selected users
//     */
//    private Collection<Users> getSelectedUsersList() {
//        Collection<Users> selectedUsers = new HashSet<Users>();
//        for (Object selectedItem: userListbox.getSelectedItems()) {
//            userListbox.renderItem((Listitem)selectedItem);
//            selectedUsers.add((Users)((Listitem)selectedItem).getValue());
//        }
//        return selectedUsers;
//    }
//
//    /**
//     * Returns list of selected group managers.
//     *
//     * @return collection of selected group managers
//     */
//    private Collection<Users> getSelectedGMList() {
//        Collection<Users> selectedGM = new HashSet<Users>();
//        for (Object selectedItem: gmListbox.getSelectedItems()) {
//            gmListbox.renderItem((Listitem)selectedItem);
//            selectedGM.add((Users)((Listitem)selectedItem).getValue());
//        }
//        return selectedGM;
//    }
//
//    /**
//     * Shows the user-based report.
//     */
//    public void showUsersReport() {
//        showReport(USER_REPORT_TEMPLATE);
//    }
//
//    /**
//     * Shows the practicum-based report.
//     */
//    public void showPracticumsReport() {
//        showReport(PRACTICUM_REPORT_TEMPLATE);
//    }
//
//    /**
//     * Show the report with the given template.
//     *
//     * @param reportTemplate
//     */
//    protected void showReport(String reportTemplate) {
//        StringBuilder filterTextBuilder = new StringBuilder();
//        Set<?> selItems = skill_and_level_listbox.getSelectedItems();
//
//        DetachedCriteria practicumCriteria = DetachedCriteria.forClass(Practicum.class, "p");
//
//        List<Practicum> selectedPracticums = new ArrayList<Practicum>();
//        Collection<Users> selectedUsers = getSelectedUsersList();
//        Collection<Users> selectedGMs = getSelectedGMList();
//        if (!selectedUsers.isEmpty()) {
//            filterTextBuilder.append(Labels.getLabel("customReport.usersColon") + " ");
//            StringBuilder usersBuilder = new StringBuilder();
//            for (Users user : selectedUsers) {
//                if (usersBuilder.length()>0) {
//                    usersBuilder.append(", ");
//                }
//                usersBuilder.append(user.getHumanReadableUsername());
//            }
//            filterTextBuilder.append(usersBuilder);
//            filterTextBuilder.append("\n");
//            practicumCriteria.add(Restrictions.in("userId", selectedUsers));
//        }
//        else if (!selectedGMs.isEmpty()){
//            filterTextBuilder.append(Labels.getLabel("customReport.groupManagerColon") + " ");
//            StringBuilder gmBuilder = new StringBuilder();
//            for (Users gm : selectedGMs) {
//                if (gmBuilder.length()>0) {
//                    gmBuilder.append(", ");
//                }
//                gmBuilder.append(gm.getHumanReadableUsername());
//            }
//            filterTextBuilder.append(gmBuilder);
//            filterTextBuilder.append("\n");
//            practicumCriteria.createAlias("userId", "u");
//            practicumCriteria.add(Restrictions.in("u.manager", selectedGMs));
//        }
//
//        if (!selItems.isEmpty()) {
//            filterTextBuilder.append(Labels.getLabel("customReport.skillsColon") + " ");
//            Junction usersSkillCriterion = Restrictions.disjunction();
//            StringBuilder skillsBuilder = new StringBuilder();
//            for (Object selItem : selItems) {
//                Practicum pr = (Practicum) ((Listitem)selItem).getValue();
//                if (pr!=null) {
//                    usersSkillCriterion.add(Restrictions.and(
//                            Restrictions.eq("skillId", pr.getSkillId()),
//                            Restrictions.in("level", getHigherLevels(pr.getLevel()))));
//                    if (skillsBuilder.length()>0) {
//                        skillsBuilder.append(", ");
//                    }
//                    skillsBuilder.append(pr.getSkillId().getName()).append(" - ").append(pr.getLevel().getCode());
//                    selectedPracticums.add(pr);
//                }
//            }
//            practicumCriteria.add(usersSkillCriterion);
//            filterTextBuilder.append(skillsBuilder);
//            filterTextBuilder.append("\n");
//        }
//
//        List<Practicum> practicumList = null;
//        if (selectedUsers.isEmpty() && selectedPracticums.isEmpty() && selectedGMs.isEmpty()) {
//            practicumList = practicumService.readAll();
//        }
//        else {
//            practicumList = practicumService.readByCriteria(practicumCriteria);
//        }
//        if (!selectedPracticums.isEmpty()) {
//            practicumList = filterPracticumList(practicumList, selectedPracticums);
//        }
//
//        showReport(reportTemplate, practicumList, filterTextBuilder.toString());
//    }
//
//    /**
//     * Filters out practicums belonging to users which doesn't have all the required practicums.
//     *
//     * @param orList list of practicums
//     * @param practicums collection of the required practicums
//     * @return list of matching practicums
//     */
//    private static List<Practicum> filterPracticumList(List<Practicum> orList, Collection<Practicum> practicums) {
//        if (practicums==null || practicums.size()==0 || orList==null || orList.size()==0) {
//            return orList;
//        }
//
//        // the result practicum list
//        List<Practicum> andPracticumList = new ArrayList<Practicum>();
//        // map of (user, list_of_practicums) pairs
//        Map<Users, List<Practicum>> matchingMap = new HashMap<Users, List<Practicum>>();
//        // fill the matchingMap
//        for (Practicum pr : orList) {
//            Users u = pr.getUserId();
//            List<Practicum> prList = matchingMap.get(u);
//            if (prList==null) {
//                prList = new ArrayList<Practicum>();
//            }
//            prList.add(pr);
//            matchingMap.put(u, prList);
//        }
//
//        // check if each user has all the required practicums
//        for (Users u : matchingMap.keySet()) {
//            List<Practicum> prList = matchingMap.get(u);
//            boolean allOk = true;
//            for (Practicum refPr : practicums) {
//                boolean ok = false;
//                for (Practicum pr : prList) {
//                    if (refPr.getSkillId().getSkillId().equals(pr.getSkillId().getSkillId())) {
//                        ok = true;
//                        break;
//                    }
//                }
//                if (!ok) {
//                    allOk = false;
//                    break;
//                }
//            }
//            if (allOk) {
//                andPracticumList.addAll(prList);
//            }
//        }
//
//        return andPracticumList;
//    }
//
//    /**
//     * Simple filtering - Skill and Level list box renderer
//     *
//     * @author molnala
//     *
//     */
//    class SkillAndLevelRenderer implements ListitemRenderer {
//        @Override
//        public void render(Listitem item, Object data) throws Exception {
//            item.setValue(data);
//            final Practicum pr = (Practicum)data;
//            new Listcell(pr.getSkillId().getName()).setParent(item);
//            Listcell lc = new Listcell();
//            lc.setParent(item);
//            final Combobox cb = new Combobox();
//            SimpleListModel cbModel = new SimpleListModel(levels);
//            cb.setModel(cbModel);
//            cbModel.sort(new LevelComparator(), true);
//            cb.setItemRenderer(new ComboitemRenderer() {
//                @Override
//                public void render(Comboitem comboitem, Object data) throws Exception {
//                    cb.setReadonly(true);
//                    cb.setSelectedIndex(0);
//                    comboitem.setLabel(((CodeTable)data).getCode());
//                    comboitem.setValue(data);
//                }
//            });
//            cb.addEventListener(Events.ON_SELECT, new EventListener(){
//                @Override
//                public void onEvent(Event event) throws Exception {
//                    pr.setLevel((CodeTable)cb.getSelectedItem().getValue());
//                }
//            });
//            cb.setParent(lc);
//        }
//    }
//
//    /**
//     * Simple filtering - Users list box renderer
//     *
//     * @author molnala
//     */
//    class UserListboxRenderer implements ListitemRenderer {
//        @Override
//        public void render(Listitem item, Object data) throws Exception {
//            item.setLabel(((Users)data).getHumanReadableUsername());
//            item.setValue(data);
//        }
//    }
//
//    /**
//     * Simple filtering - Level combo box renderer
//     *
//     * @author molnala
//     */
//    class LevelComboboxRenderer implements ListitemRenderer {
//        @Override
//        public void render(Listitem item, Object data) throws Exception {
//            item.setLabel(((CodeTable)data).getCode());
//            item.setValue(data);
//        }
//    }
//
//    /**
//     * Level comparator.
//     *
//     * @author molnala
//     *
//     */
//    class LevelComparator implements Comparator<CodeTable> {
//        @Override
//        public int compare(CodeTable o1, CodeTable o2) {
//            return o1.getIndex().compareTo(o2.getIndex());
//        }
//    }
//
//    /**
//     * Variable combo box renderer.
//     *
//     * @author molnala
//     */
//    class ConditionVariableComboboxRenderer implements ComboitemRenderer {
//        @Override
//        public void render(Comboitem item, Object data) throws Exception {
//            item.setLabel(((Field)data).getName());
//            item.setValue(data);
//        }
//    }
//
//    /**
//     * Operator combo box renderer.
//     *
//     * @author molnala
//     *
//     */
//    class ConditionOperatorComboboxRenderer implements ComboitemRenderer {
//        @Override
//        public void render(Comboitem item, Object data) throws Exception {
//            item.setLabel(CriteriaTreeLeaf.getOperatorAsString((Integer)data));
//            item.setValue(data);
//        }
//    }
//}
