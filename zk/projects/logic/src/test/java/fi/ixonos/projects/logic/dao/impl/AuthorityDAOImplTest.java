package fi.ixonos.projects.logic.dao.impl;

import fi.ixonos.projects.logic.bean.Authority;
import fi.ixonos.projects.logic.bean.CodeTable;
import fi.ixonos.projects.logic.bean.Users;
import fi.ixonos.projects.logic.dao.AuthorityDAO;
import fi.ixonos.projects.logic.dao.CodeTableDAO;
import fi.ixonos.projects.logic.dao.UsersDAO;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import fi.ixonos.projects.logic.AbstractWebTest;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author katrami
 */
public class AuthorityDAOImplTest extends AbstractWebTest implements TestInterface {

    @Resource
    private AuthorityDAO authorityDAO;
    @Resource
    private CodeTableDAO codeTableDAO;
    @Resource
    private UsersDAO usersDAO;

    @Test
    @Override
    public void testInsert() {
        CodeTable code = new CodeTable();
        code.setGroupCode("AUTHORITIES");
        code.setCode("ROLE_TESTER");
        code.setIndex(3);
        code.setDescription("");
        codeTableDAO.create(code);
        Authority authority = new Authority();
        authority.setAuthority(code);
        Users user = usersDAO.read(2);
        authority.setUser(user);
        authorityDAO.create(authority);
        flush();
        IdHolder.authorityId = authority.getAuthorityId();
        IdHolder.userId = user.getUserId();
    }

    @Test
    @Override
    public void testLoad() {
        Authority authority = authorityDAO.read(IdHolder.authorityId);
        assertEquals("ROLE_TESTER", authority.getAuthority().getCode());
        assertEquals(IdHolder.userId, authority.getUser().getUserId());
    }

    @Test
    @Override
    public void testUpdate() {
        CodeTable code = new CodeTable();
        code.setGroupCode("AUTHORITIES");
        code.setCode("UPDATED_ROLE_TESTER");
        code.setIndex(4);
        code.setDescription("");
        codeTableDAO.create(code);
        Authority authority = authorityDAO.read(IdHolder.authorityId);
        authority.setAuthority(code);
        Users user = usersDAO.read(4);
        authority.setUser(user);
        IdHolder.userId = user.getUserId();
        authorityDAO.update(authority);
        flush();
        authority = authorityDAO.read(IdHolder.authorityId);
        assertEquals("UPDATED_ROLE_TESTER", authority.getAuthority().getCode());
        assertEquals(IdHolder.userId, authority.getUser().getUserId());
    }

    @Test
    @Override
    public void testDelete() {
        Authority authority = authorityDAO.read(IdHolder.authorityId);
        authorityDAO.delete(authority);
        flush();
        authority = authorityDAO.read(IdHolder.authorityId);
        boolean isDeleted = false;
        try {
            authority.getAuthorityId();
        } catch (ObjectNotFoundException e) {
            isDeleted = true;
        }
        assertTrue("Authority was not successfully deleted", isDeleted);
        // clear remained data from database
        clearDB();
    }

    private void clearDB() {
        // clear authority codes
        DetachedCriteria criteria = DetachedCriteria.forClass(CodeTable.class);
        criteria.add(Restrictions.in("code", new String[]{"ROLE_TESTER", "UPDATED_ROLE_TESTER"}));
        List<CodeTable> codes = codeTableDAO.readByCriteria(criteria);
        if (codes.size() == 2) {
            for (int i = 0; i < codes.size(); i++) {
                codeTableDAO.delete(codes.get(i));
            }
        }
        flush();
    }
}
