package com.ixonos.skillnet.logic.service.impl;

import com.ixonos.skillnet.logic.bean.CodeTable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.ixonos.skillnet.logic.bean.GroupAuthority;
import com.ixonos.skillnet.logic.bean.Groups;
import com.ixonos.skillnet.logic.dao.GenericDAO;
import com.ixonos.skillnet.logic.dao.GroupAuthorityDAO;
import com.ixonos.skillnet.logic.service.AbstractGenericService;
import com.ixonos.skillnet.logic.service.AuthorityService;
import com.ixonos.skillnet.logic.service.CodeTableService;
import com.ixonos.skillnet.logic.service.GroupAuthorityService;
import com.ixonos.skillnet.logic.service.GroupsService;
import java.util.ArrayList;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.annotation.Secured;
import static com.ixonos.skillnet.logic.enumeration.SkillnetRole.ROLE_ADMIN;

/**
 *
 * @author magurja
 */
@Service("groupAuthorityService")
public final class GroupAuthorityServiceImpl extends AbstractGenericService<GroupAuthority> implements GroupAuthorityService {

    public static final String AUTHORITIES = "AUTHORITIES";

    @Resource
    protected GroupAuthorityDAO groupAuthorityDAO;

    @Resource
    protected AuthorityService authorityService;

    @Resource
    protected GroupsService groupsService;

    @Resource
    protected CodeTableService codeTableService;

    @Secured(ROLE_ADMIN)
    @Override
    @Transactional
    public boolean changeGroupAuthorities(final String groupName,
final  List<String> incomingAuthorities) throws Exception {
        Groups group = null;
        try {
            group = groupsService.getGroup(groupName);
        } catch (Exception ex) {
            Logger.getLogger(GroupAuthorityServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<GroupAuthority> authorityCollection = group.getGroupAuthorityCollection();
        List<GroupAuthority> authForDele = new ArrayList<GroupAuthority>();
        List<GroupAuthority> authoritiesForAdd = new ArrayList<GroupAuthority>();
        List<String> actualAuthorities = new ArrayList<String>();

        //set all previous authorities to actualAuthorities
        for (GroupAuthority groupAuthority : authorityCollection) {
            actualAuthorities.add(groupAuthority.getAuthority().getCode());
            if (!incomingAuthorities.contains(groupAuthority.getAuthority().getCode())) {
                authForDele.add(groupAuthority); // authorities which will be removed because not in incoming authorities
            }
        }

        for (String auth : incomingAuthorities) {
            if (!actualAuthorities.contains(auth)) {
                authoritiesForAdd.add(getGroupAuthority(auth));
            }
        }

        // delete
        for (GroupAuthority memberForDelete : authForDele) {
            authorityCollection.remove(memberForDelete);
            delete(memberForDelete);
        }
        // insert
        for (GroupAuthority memberForAdd : authoritiesForAdd) {
            memberForAdd.setGroup(group);
            authorityCollection.add(memberForAdd);
        }

        groupsService.update(group);
        return true;
    }

    private GroupAuthority getGroupAuthority(final String authority) throws Exception {
        GroupAuthority groupAuthority = new GroupAuthority();
        final CodeTable code = codeTableService.getCode(AUTHORITIES, authority);
        groupAuthority.setAuthority(code);
        return groupAuthority;
    }

    @Autowired
    @Override
    public void setServiceDAO(final @Qualifier("groupAuthorityDAO") GenericDAO genericDAO) {
        super.setServiceDAO(genericDAO);
    }
}
