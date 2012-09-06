package com.ixonos.skillnet.web.skills.edit;

import java.io.StringReader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.springframework.transaction.annotation.Transactional;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zkplus.spring.SpringUtil;
import org.zkoss.zul.Bandbox;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Fileupload;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.ixonos.skillnet.logic.bean.Node;
import com.ixonos.skillnet.logic.bean.Skill;
import com.ixonos.skillnet.logic.service.GroupsService;
import com.ixonos.skillnet.logic.service.NodeService;
import com.ixonos.skillnet.logic.service.SkillService;
import com.ixonos.skillnet.logic.service.UsersService;
import com.ixonos.skillnet.web.servlet.SkillnetInitServlet;

public class ImportSkillsWindow extends Window {

	/** serialVersionUID */
	private static final long serialVersionUID = -1258322343811030667L;

	private static final XMLInputFactory xmlif = XMLInputFactory.newInstance();

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

	private final GroupsService groupsService = (GroupsService) SpringUtil
			.getApplicationContext().getBean("groupsService");
	private final UsersService usersService = (UsersService) SpringUtil
			.getApplicationContext().getBean("usersService");
	private final SkillService skillService = (SkillService) SpringUtil
			.getApplicationContext().getBean("skillService");
	private final NodeService nodeService = (NodeService) SpringUtil
			.getApplicationContext().getBean("nodeService");

	private final List<Skill> mergedList = new ArrayList<Skill>();
	private final List<Skill> otherList = new ArrayList<Skill>();
	private final List<Node> nodesList = new ArrayList<Node>();
	private final Map<String, Skill> mergedMap = new HashMap<String, Skill>();

	private Skill lmlSkill;
	private Skill lmrSkill;
	private Skill lolSkill;
	private Skill lorSkill;

	private boolean changed = false;

	public void setLmlSkill(Skill lmlSkill) {
		this.lmlSkill = lmlSkill;
	}

	public Skill getLmlSkill() {
		return lmlSkill;
	}

	public void setLmrSkill(Skill lmrSkill) {
		this.lmrSkill = lmrSkill;
	}

	public Skill getLmrSkill() {
		return lmrSkill;
	}

	public void setLolSkill(Skill lolSkill) {
		this.lolSkill = lolSkill;
	}

	public Skill getLolSkill() {
		return lolSkill;
	}

	public void setLorSkill(Skill lorSkill) {
		this.lorSkill = lorSkill;
	}

	public Skill getLorSkill() {
		return lorSkill;
	}

	public void onUpload(Event event) throws Exception {
		try {
			try {
				parseImportFile();
			} catch (XMLStreamException xse) {
				Messagebox.show(xse.getLocalizedMessage(), "Error",
						Messagebox.OK, Messagebox.ERROR);
				xse.printStackTrace();
			}
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}

	private void parseImportFile() throws Exception {
		Media media = Fileupload.get();
		if (media != null) {

			XMLEventReader reader = null;
			synchronized (xmlif) {
				StringReader r = new StringReader(media.getStringData());
				reader = xmlif.createXMLEventReader(r);
			}

			mergedList.clear();
			otherList.clear();
			mergedMap.clear();
			nodesList.clear();

			while (reader.hasNext()) {
				XMLEvent nextEvent = reader.nextEvent();
				int type = nextEvent.getEventType();
				if (type == XMLEvent.START_ELEMENT) {
					StartElement elm = nextEvent.asStartElement();
					if (elm.getName().equals(qn_trees))
						parseTrees(reader);
					if (elm.getName().equals(qn_skills))
						parseSkills(reader);
				}
			}

			ListModelList lml = (ListModelList) getPage().getVariable(
					"lmlModel");
			lml.clear();
			for (int i = 0; i < (int) (mergedList.size() / 2); i++) {
				Skill impSkill = mergedList.get(i);
				Skill actSkill = mergedMap.get(impSkill.getName());
				lml.add(new ImportSkillsListItem(impSkill, actSkill));
			}
			((Listbox) this.getFellow("listMergedLeft")).setModel(lml);

			ListModelList lmr = (ListModelList) getPage().getVariable(
					"lmrModel");
			lmr.clear();
			for (int i = (int) (mergedList.size() / 2); i < mergedList.size(); i++) {
				Skill impSkill = mergedList.get(i);
				Skill actSkill = mergedMap.get(impSkill.getName());
				lmr.add(new ImportSkillsListItem(impSkill, actSkill));
			}
			((Listbox) this.getFellow("listMergedRight")).setModel(lmr);

			ListModelList lol = (ListModelList) getPage().getVariable(
					"lolModel");
			lol.clear();
			for (int i = 0; i < (int) (otherList.size() / 2); i++) {
				Skill impSkill = otherList.get(i);
				lol.add(new ImportSkillsListItem(impSkill, null));
			}
			((Listbox) this.getFellow("listOtherLeft")).setModel(lol);

			ListModelList lor = (ListModelList) getPage().getVariable(
					"lorModel");
			lor.clear();
			for (int i = (int) (otherList.size() / 2); i < otherList.size(); i++) {
				Skill impSkill = otherList.get(i);
				lor.add(new ImportSkillsListItem(impSkill, null));
			}
			((Listbox) this.getFellow("listOtherRight")).setModel(lor);

			changed = !mergedList.isEmpty() || !otherList.isEmpty();
		}
	}

	private void parseTrees(XMLEventReader reader) throws Exception {
		while (reader.hasNext()) {
			XMLEvent nextEvent = reader.nextEvent();
			int type = nextEvent.getEventType();
			if (type == XMLEvent.START_ELEMENT) {
				StartElement elm = nextEvent.asStartElement();
				if (elm.getName().equals(qn_node)) {
					parseNode(reader, nodeService.getRoot(groupsService
							.getTreeRootName(getAttribute(
									"SPRING_SECURITY_LAST_USERNAME",
									SESSION_SCOPE).toString(),
									SkillnetInitServlet.props)));
				}
			} else if (type == XMLEvent.END_ELEMENT) {
				if (nextEvent.asEndElement().getName().equals(qn_trees))
					return;
			}
		}
	}

	private void parseNode(XMLEventReader reader, Node parent)
			throws XMLStreamException {
		Node node = new Node();
		node.setParentNode(parent);

		boolean childrenDone = false;
		while (reader.hasNext()) {
			XMLEvent nextEvent = reader.nextEvent();
			int type = nextEvent.getEventType();
			if (type == XMLEvent.START_ELEMENT) {
				StartElement elm = nextEvent.asStartElement();
				if (elm.getName().equals(qn_skill)) {
					node.setSkill(parseSkill(elm));
					nodesList.add(node);
				} else if (elm.getName().equals(qn_node)) {
					parseNode(reader, node);
				}
			} else if (type == XMLEvent.END_ELEMENT) {
				if (nextEvent.asEndElement().getName().equals(qn_children))
					childrenDone = true;
				else if (nextEvent.asEndElement().getName().equals(qn_node))
					if (childrenDone)
						return;
			}
		}
	}

	private void parseSkills(XMLEventReader reader) throws XMLStreamException {
		while (reader.hasNext()) {
			XMLEvent nextEvent = reader.nextEvent();
			int type = nextEvent.getEventType();
			if (type == XMLEvent.START_ELEMENT) {
				StartElement elm = nextEvent.asStartElement();
				if (elm.getName().equals(qn_skill)) {
					parseSkill(elm);
				}
			} else if (type == XMLEvent.END_ELEMENT) {
				if (nextEvent.asEndElement().getName().equals(qn_skills))
					return;
			}
		}
	}

	private Skill parseSkill(StartElement elm) {
		String name = elm.getAttributeByName(qn_name).getValue();
		String eval = elm.getAttributeByName(qn_evaluable).getValue();
		Skill skill = new Skill(null, name, new Date(), Boolean.valueOf(eval));
		skill.setDescription(elm.getAttributeByName(qn_description).getValue());

		List<Skill> skills = skillService.findAlike(name);

		if (skills.size() > 0) {
			mergedList.add(skill);
			mergedMap.put(skill.getName(), skills.get(0));
		} else {
			otherList.add(skill);
		}

		return skill;
	}

	@Transactional
	public void onImport(Event event) throws Exception {
		if (nodesList.isEmpty())
			importSkills();
		else
			importTrees();

		int imported = mergedList.size() + otherList.size();
		int merged = mergedMap.size();
		int created = imported - merged;
		String msg = Labels.getLabel("skillsImport.information");
		Messagebox.show(MessageFormat.format(msg, imported, merged, created),
				Labels.getLabel("skillsImport.info"), Messagebox.OK,
				Messagebox.INFORMATION);

		changed = false;
		this.detach();
	}

	private void importSkills() throws Exception {
		for (Object object : ((ListModelList) getPage().getVariable("lmlModel")))
			createSkill((ImportSkillsListItem) object);
		for (Object object : ((ListModelList) getPage().getVariable("lmrModel")))
			createSkill((ImportSkillsListItem) object);
		for (Object object : ((ListModelList) getPage().getVariable("lolModel")))
			createSkill((ImportSkillsListItem) object);
		for (Object object : ((ListModelList) getPage().getVariable("lorModel")))
			createSkill((ImportSkillsListItem) object);
	}

	private Skill createSkill(ImportSkillsListItem impItem) throws Exception {
		Skill skill = mergedMap.get(impItem.getImpSkill().getName());
		if (skill == null) {
			Skill impSkill = impItem.getImpSkill();
			impSkill.setSkillId(null);
			impSkill.setCreated(new Date());
			String login = getAttribute("SPRING_SECURITY_LAST_USERNAME",
					SESSION_SCOPE).toString();
			impSkill.setCreatedBy(usersService.getUser(login));
			skillService.create(impSkill);
			return impSkill;
		}
		return skill;
	}

	private void importTrees() throws Exception {
		for (Node node : nodesList) {
			Skill impSkill = node.getSkill();
			Skill actSkill = mergedMap.get(impSkill.getName());
			node.setSkill(createSkill(new ImportSkillsListItem(impSkill,
					actSkill)));
			nodeService.create(node);
		}
	}

	public void onCancel(Event event) {
		try {
			if (changed) {
				int response = Messagebox.show(Labels
						.getLabel("skillsImport.confirmation"), Labels
						.getLabel("skillsImport.confirm"), Messagebox.YES
						| Messagebox.NO, Messagebox.QUESTION);
				if (response == Messagebox.NO)
					return;
			}

			this.detach();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}

	public void onCheck(Event event) throws Exception {
		Checkbox cb = (Checkbox) event.getTarget();
		Bandbox bb = (Bandbox) cb.getParent().getChildren().get(1);
		Listitem li = (Listitem) getParentComponent(cb, Listitem.class);

		ImportSkillsListItem item = (ImportSkillsListItem) li.getValue();

		if (!cb.isChecked())
			mergedMap.remove(item.getImpSkill().getName());
		else
			mergedMap.put(item.getImpSkill().getName(), item.getActSkill());

		bb.setDisabled(!cb.isChecked());
	}

	@SuppressWarnings("unchecked")
	public void onSearch(Event event) {
		Textbox tb = (Textbox) event.getTarget();
		Listbox lb = (Listbox) tb.getParent().getParent().getChildren().get(1);
		if (tb.getValue() != null && !"".equals(tb.getValue()))
			lb
					.setModel(new ListModelList(skillService.findAlike(tb
							.getValue())));
		else
			lb.setModel(new ListModelList((List<Skill>) getPage().getVariable(
					"skillList")));
	}

	public void onSkillSelect(Event event) throws Exception {
		Listbox lb = (Listbox) event.getTarget();
		Bandbox bb = (Bandbox) getParentComponent(lb, Bandbox.class);
		Listitem li = (Listitem) getParentComponent(lb, Listitem.class);

		ImportSkillsListItem item = (ImportSkillsListItem) li.getValue();
		Skill skill = (Skill) lb.getSelectedItem().getValue();

		item.setActSkill(skill);
		mergedMap.put(item.getImpSkill().getName(), item.getActSkill());

		bb.setValue(skill.getName());
		bb.close();
	}

	private Component getParentComponent(Component component, Class<?> clazz)
			throws Exception {
		int index = 0;
		while ((component != null)
				&& !clazz.isInstance((component = component.getParent()))) {
			index++;
			if (index == 50) {
				throw new Exception("No parent " + clazz + " found!");
			}
		}
		if (component == null) {
			throw new Exception("No parent " + clazz + " found!");
		}
		return component;
	}

	public class ImportSkillsListItem {

		private Skill impSkill;
		private Skill actSkill;

		public ImportSkillsListItem(Skill impSkill, Skill actSkill) {
			this.impSkill = impSkill;
			this.actSkill = actSkill;
		}

		public Skill getImpSkill() {
			return impSkill;
		}

		public Skill getActSkill() {
			return actSkill;
		}

		public void setActSkill(Skill actSkill) {
			this.actSkill = actSkill;
		}

		public void setImpSkill(Skill impSkill) {
			this.impSkill = impSkill;
		}

	}

}
