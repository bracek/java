package fi.ixonos.projects.logic.service.impl;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import fi.ixonos.projects.logic.bean.GroupMember;
import fi.ixonos.projects.logic.dao.GenericDAO;
import fi.ixonos.projects.logic.dao.GroupMemberDAO;
import fi.ixonos.projects.logic.service.AbstractGenericService;
import fi.ixonos.projects.logic.service.GroupMemberService;

/**
 *
 * @author magurja
 */
@Service("groupMemberService")
public final class GroupMemberServiceImpl extends AbstractGenericService<GroupMember> implements GroupMemberService {	
	
	@Resource
	protected GroupMemberDAO groupMemberDAO;

    @Autowired
    @Override
    public void setServiceDAO(@Qualifier("groupMemberDAO") GenericDAO genericDAO) {
        super.setServiceDAO(genericDAO);
    }
}