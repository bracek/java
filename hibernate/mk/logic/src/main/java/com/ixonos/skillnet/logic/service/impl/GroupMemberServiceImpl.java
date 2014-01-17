package com.ixonos.skillnet.logic.service.impl;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.ixonos.skillnet.logic.bean.GroupMember;
import com.ixonos.skillnet.logic.dao.GenericDAO;
import com.ixonos.skillnet.logic.dao.GroupMemberDAO;
import com.ixonos.skillnet.logic.service.AbstractGenericService;
import com.ixonos.skillnet.logic.service.GroupMemberService;

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
    public void setServiceDAO(final @Qualifier("groupMemberDAO") GenericDAO genericDAO) {
        super.setServiceDAO(genericDAO);
    }
}
