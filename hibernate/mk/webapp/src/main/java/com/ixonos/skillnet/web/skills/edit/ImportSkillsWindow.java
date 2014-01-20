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

	private final GroupsService groupsService = (final GroupsService) SpringUtil
			.getApplicationContext().getBean("groupsService");
	private final UsersService usersService = (final UsersService) SpringUtil
			.getApplicationContext().getBean("usersService");
	private final SkillService skillService = (final SkillService) SpringUtil
			.getApplicationContext().getBean("skillService");
	private final NodeService nodeService = (final NodeService) SpringUtil
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

	public void setLmlSkill(final Skill lmlSkill) {
		this.lmlSkill = lmlSkill;
	}

	public Skill getLmlSkill() {
		return lmlSkill;
	}

	public void setLmrSkill(final Skill lmrSkill) {
		this.lmrSkill = lmrSkill;
	}

	public Skill getLmrSkill() {
		return lmrSkill;
	}

	public void setLolSkill(final Skill lolSkill) {
		this.lolSkill = lolSkill;
	}

	public Skill getLolSkill() {
		return lolSkill;
	}

	public void setLorSkill(final Skill lorSkill) {
		this.lorSkill = lorSkill;
	}

	public Skill getLorSkill() {
		return lorSkill;
	}

	public void onUpload(final Event event) throws Exception {
		try {
			try {
				parseImportFile();
			} catch (final XMLStreamException xse) {
				Messagebox.show(xse.getLocalizedMessage(), "Error",
						Messagebox.OK, Messagebox.ERROR);
				xse.printStackTrace();
			}
		} catch (final InterruptedException ie) {
			ie.printStackTrace();
		}
	}

	private void parseImportFile() throws Exception {
		final Media media = Fileupload.get();
		if (media != null) {

			XMLEventReader reader = null;
			synchronized (xmlif) {
				final StringReader r = new StringReader(media.getStringData());
				reader = xmlif.createXMLEventReader(r);
			}

			mergedList.clear();
			otherList.clear();
			mergedMap.clear();
			nodesList.clear();

			while (reader.hasNext()) {
				final XMLEvent nextEvent = reader.nextEvent();
				final int type = nextEvent.getEventType();
				if (type == XMLEvent.START_ELEMENT) {
					final StartElement elm = nextEvent.asStartElement();
					if (elm.getName().equals(qn_trees))
						parseTrees(reader);
					if (elm.getName().equals(qn_skills))
						parseSkills(reader);
				}
			}

			final ListModelList lml = (ListModelList) getPage().getVariable(
					"lmlModel");
			lml.clear();
			for (int i = 0; i < mergedList.size() / 2; i++) {
				final Skill impSkill = mergedList.get(i);
				final Skill actSkill = mergedMap.get(impSkill.getName());
				lml.add(new ImportSkillsListItem(impSkill, actSkill));
			}
			((Listbox) this.getFellow("listMergedLeft")).setModel(lml);

			final ListModelList lmr = (ListModelList) getPage().getVariable(
					"lmrModel");
			lmr.clear();
			for (int i = mergedList.size() / 2; i < mergedList.size(); i++) {
				final Skill impSkill = mergedList.get(i);
				final Skill actSkill = mergedMap.get(impSkill.getName());
				lmr.add(new ImportSkillsListItem(impSkill, actSkill));
			}
			((Listbox) this.getFellow("listMergedRight")).setModel(lmr);

			final ListModelList lol = (ListModelList) getPage().getVariable(
					"lolModel");
			lol.clear();
			for (int i = 0; i < otherList.size() / 2; i++) {
				final Skill impSkill = otherList.get(i);
				lol.add(new ImportSkillsListItem(impSkill, null));
			}
			((Listbox) this.getFellow("listOtherLeft")).setModel(lol);

			final ListModelList lor = (ListModelList) getPage().getVariable(
					"lorModel");
			lor.clear();
			for (int i = otherList.size() / 2; i < otherList.size(); i++) {
				final Skill impSkill = otherList.get(i);
				lor.add(new ImportSkillsListItem(impSkill, null));
			}
			((Listbox) this.getFellow("listOtherRight")).setModel(lor);

			changed = !mergedList.isEmpty() || !otherList.isEmpty();
		}
	}

	private void parseTrees(final XMLEventReader reader) throws Exception {
		while (reader.hasNext()) {
			final XMLEvent nextEvent = reader.nextEvent();
			final int type = nextEvent.getEventType();
			if (type == XMLEvent.START_ELEMENT) {
				final StartElement elm = nextEvent.asStartElement();
				if (elm.getName().equals(qn_node)) {
					parseNode(reader, nodeService.getRoot(groupsService
							.getTreeRootName(
									getAttribute(
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

	private void parseNode(final XMLEventReader reader,
 final Node parent)
			throws XMLStreamException {
		final Node node = new Node();
		node.setParentNode(parent);

		boolean childrenDone = false;
		while (reader.hasNext()) {
			final XMLEvent nextEvent = reader.nextEvent();
			final int type = nextEvent.getEventType();
			if (type == XMLEvent.START_ELEMENT) {
				final StartElement elm = nextEvent.asStartElement();
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

	private void parseSkills(final XMLEventReader reader)
			throws XMLStreamException {
		while (reader.hasNext()) {
			final XMLEvent nextEvent = reader.nextEvent();
			final int type = nextEvent.getEventType();
			if (type == XMLEvent.START_ELEMENT) {
				final StartElement elm = nextEvent.asStartElement();
				if (elm.getName().equals(qn_skill)) {
					parseSkill(elm);
				}
			} else if (type == XMLEvent.END_ELEMENT) {
				if (nextEvent.asEndElement().getName().equals(qn_skills))
					return;
			}
		}
	}

	private Skill parseSkill(final StartElement elm) {
		final String name = elm.getAttributeByName(qn_name).getValue();
		final String eval = elm.getAttributeByName(qn_evaluable).getValue();
		final Skill skill = new Skill(null, name, new Date(),
				Boolean.valueOf(eval));
		skill.setDescription(elm.getAttributeByName(qn_description).getValue());

		final List<Skill> skills = skillService.findAlike(name);

		if (skills.size() > 0) {
			mergedList.add(skill);
			mergedMap.put(skill.getName(), skills.get(0));
		} else {
			otherList.add(skill);
		}

		return skill;
	}

	@Transactional
	public void onImport(final Event event) throws Exception {
		if (nodesList.isEmpty())
			importSkills();
		else
			importTrees();

		final int imported = mergedList.size() + otherList.size();
		final int merged = mergedMap.size();
		final int created = imported - merged;
		final String msg = Labels.getLabel("skillsImport.information");
		Messagebox.show(MessageFormat.format(msg, imported, merged, created),
				Labels.getLabel("skillsImport.info"), Messagebox.OK,
				Messagebox.INFORMATION);

		changed = false;
		this.detach();
	}

	private void importSkills() throws Exception {
		for (final Object object : (ListModelList) getPage().getVariable(
				"lmlModel"))
			createSkill((ImportSkillsListItem) object);
		for (final Object object : (ListModelList) getPage().getVariable(
				"lmrModel"))
			createSkill((ImportSkillsListItem) object);
		for (final Object object : (ListModelList) getPage().getVariable(
				"lolModel"))
			createSkill((ImportSkillsListItem) object);
		for (final Object object : (ListModelList) getPage().getVariable(
				"lorModel"))
			createSkill((ImportSkillsListItem) object);
	}

	private Skill createSkill(final ImportSkillsListItem impItem)
			throws Exception {
		final Skill skill = mergedMap.get(impItem.getImpSkill().getName());
		if (skill == null) {
			final Skill impSkill = impItem.getImpSkill();
			impSkill.setSkillId(null);
			impSkill.setCreated(new Date());
			final String login = getAttribute("SPRING_SECURITY_LAST_USERNAME",
					SESSION_SCOPE).toString();
			impSkill.setCreatedBy(usersService.getUser(login));
			skillService.create(impSkill);
			return impSkill;
		}
		return skill;
	}

	private void importTrees() throws Exception {
		for (final Node node : nodesList) {
			final Skill impSkill = node.getSkill();
			final Skill actSkill = mergedMap.get(impSkill.getName());
			node.setSkill(createSkill(new ImportSkillsListItem(impSkill,
					actSkill)));
			nodeService.create(node);
		}
	}

	public void onCancel(final Event event) {
		try {
			if (changed) {
				final int response = Messagebox.show(
						Labels.getLabel("skillsImport.confirmation"),
						Labels.getLabel("skillsImport.confirm"), Messagebox.YES
								| Messagebox.NO, Messagebox.QUESTION);
				if (response == Messagebox.NO)
					return;
			}

			this.detach();
		} catch (final InterruptedException ie) {
			ie.printStackTrace();
		}
	}

	public void onCheck(final Event event) throws Exception {
		final Checkbox cb = (Checkbox) event.getTarget();
		final Bandbox bb = (Bandbox) cb.getParent().getChildren().get(1);
		final Listitem li = (Listitem) getParentComponent(cb, Listitem.class);

		final ImportSkillsListItem item = (ImportSkillsListItem) li.getValue();

		if (!cb.isChecked())
			mergedMap.remove(item.getImpSkill().getName());
		else
			mergedMap.put(item.getImpSkill().getName(), item.getActSkill());

		bb.setDisabled(!cb.isChecked());
	}

	@SuppressWarnings("unchecked")
	public void onSearch(final Event event) {
		final Textbox tb = (Textbox) event.getTarget();
		final Listbox lb = (Listbox) tb.getParent().getParent().getChildren()
				.get(1);
		if (tb.getValue() != null && !"".equals(tb.getValue()))
			lb.setModel(new ListModelList(skillService.findAlike(tb.getValue())));
		else
			lb.setModel(new ListModelList((List<Skill>) getPage().getVariable(
					"skillList")));
	}

	public void onSkillSelect(final Event event) throws Exception {
		final Listbox lb = (Listbox) event.getTarget();
		final Bandbox bb = (Bandbox) getParentComponent(lb, Bandbox.class);
		final Listitem li = (Listitem) getParentComponent(lb, Listitem.class);

		final ImportSkillsListItem item = (ImportSkillsListItem) li.getValue();
		final Skill skill = (Skill) lb.getSelectedItem().getValue();

		item.setActSkill(skill);
		mergedMap.put(item.getImpSkill().getName(), item.getActSkill());

		bb.setValue(skill.getName());
		bb.close();
	}

	private Component getParentComponent(final Component component,
			final Class<?> clazz) throws Exception {
		int index = 0;
		while (component != null
				&& !clazz.isInstance(component = component.getParent())) {
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

		public ImportSkillsListItem(final Skill impSkill,
 final Skill actSkill) {
			this.impSkill = impSkill;
			this.actSkill = actSkill;
		}

		public Skill getImpSkill() {
			return impSkill;
		}

		public Skill getActSkill() {
			return actSkill;
		}

		public void setActSkill(final Skill actSkill) {
			this.actSkill = actSkill;
		}

		public void setImpSkill(final Skill impSkill) {
			this.impSkill = impSkill;
		}

	}

}
