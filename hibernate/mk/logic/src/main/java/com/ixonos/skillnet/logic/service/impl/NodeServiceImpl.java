package com.ixonos.skillnet.logic.service.impl;

import static com.ixonos.skillnet.logic.enumeration.SkillnetRole.ROLE_ADMIN;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ixonos.skillnet.logic.bean.Node;
import com.ixonos.skillnet.logic.bean.Skill;
import com.ixonos.skillnet.logic.dao.GenericDAO;
import com.ixonos.skillnet.logic.dao.NodeDAO;
import com.ixonos.skillnet.logic.jdbc.JdbcGenericDAO;
import com.ixonos.skillnet.logic.jdbc.JdbcNodeDAO;
import com.ixonos.skillnet.logic.service.AbstractGenericService;
import com.ixonos.skillnet.logic.service.NodeService;
import com.ixonos.skillnet.logic.service.SkillService;
import com.ixonos.skillnet.logic.util.NodesComparator;
import org.springframework.security.annotation.Secured;
import static com.ixonos.skillnet.logic.enumeration.SkillnetRole.ROLE_GM;;

/**
 *
 * @author magurja
 */
@Service("nodeService")
public final class NodeServiceImpl extends AbstractGenericService<Node> implements NodeService {	
	
	@Resource
	protected NodeDAO nodeDAO;
	@Resource
	protected JdbcNodeDAO jdbcNodeDAO;
	@Resource
	protected SkillService skillService;

    @Autowired
    @Override
    public void setServiceDAO(final @Qualifier("nodeDAO") GenericDAO genericDAO) {
        super.setServiceDAO(genericDAO);
    }
    @Autowired
    @Override
    public void setJdbcGenericDAO(final @Qualifier("jdbcNodeDAO") JdbcGenericDAO jdbcGenericDAO) {
        super.setJdbcGenericDAO(jdbcGenericDAO);
    }

    @Override
	public Node getChild(final Node node,
final  int index) {
		Integer nodeId = node.getNodeId();
		List<Node> nodes = read(nodeId).getChildren(); 
		Node[] nodesArray = nodes.toArray(new Node[nodes.size()]);
		Arrays.sort(nodesArray,new NodesComparator(true));
		nodes = Arrays.asList(nodesArray); 
		return nodes.get(index);
	}

	@Override
	public List<Node> getChildren(final Node node) {
		Integer nodeId = node.getNodeId();
		return read(nodeId).getChildren();
	}

	@Override
	public Node getRoot(final String rootName) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Node.class);
		if (rootName.equals("ROOT")) {			
			criteria.add(Restrictions.isNull("parentNode"));			
		} else {
			criteria.createAlias("skill", "s");
			criteria.add(Restrictions.eq("s.name", rootName));
		}
		return readByCriteria(criteria).get(0);
	}

	@Secured(ROLE_GM)
	@Override
	public Node insertNode(final Integer parentID,
final  Integer skillID) {
		Node parent = nodeDAO.read(parentID);
		Node node = new Node();
		node.setParentNode(parent);
		Skill skill = skillService.read(skillID);
		node.setSkill(skill);
		create(node);
        return node;
	}

	@Override
	public int getChildCount(final Node parent) {
        if (jdbcNodeDAO == null) {
            throw new IllegalArgumentException("Given argument (jdbcNodeDAO) is null.");
        }
		return jdbcNodeDAO.getChildCount(parent);
	}
	
	@Secured(ROLE_GM)
	@Override
	@Transactional
	public Node moveNode(final Integer nodeID,final  Integer newParentID) {
		Boolean canMove = true;
		Node node = nodeDAO.read(nodeID);
		Node newParent = nodeDAO.read(newParentID);
		Node parent = newParent;
		if(parent.getParentNode() != null){
			// go from "newParent" node back to ROOT
			while(parent.getParentNode() != null){
				parent = parent.getParentNode();
				// if "newParent" node is in a subtree with "node" as its parent => move cannot be performed
				if(parent.getNodeId().equals(nodeID)){
					canMove = false;
				}
			}
		}
		if(canMove){
			node.setParentNode(newParent);
			update(node);
			return node;
		}
		return null;
	}

}
