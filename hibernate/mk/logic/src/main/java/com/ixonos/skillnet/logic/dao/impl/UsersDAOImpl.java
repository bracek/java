package com.ixonos.skillnet.logic.dao.impl;

import static com.ixonos.skillnet.logic.enumeration.SkillnetRole.ROLE_USER;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.ixonos.skillnet.logic.bean.Users;
import com.ixonos.skillnet.logic.dao.UsersDAO;

/**
*
* @author magurja
*/
@Repository("usersDAO")
public class UsersDAOImpl extends GenericDAOImpl<Users> implements UsersDAO {

   @Autowired
   public UsersDAOImpl(final @Qualifier("sessionFactory") SessionFactory sessionFactory) {
       setSessionFactory(sessionFactory);
   }
   
   /* (non-Javadoc)
    * @see com.ixonos.skillnet.logic.dao.UsersDAO#getUsersWithFewSkills(java.lang.Integer)
    */
   @SuppressWarnings("unchecked")
   @Override
   public List<Users> getUsersWithFewSkills(final Integer minimumSkills) {
	   
	   StringBuilder sql = new StringBuilder();
	   sql.append("select distinct u.* ");
	   sql.append("  from users u");
	   sql.append("      ,authority a");
	   sql.append("      ,code_table ct");
	   sql.append("      ,group_member gm");
	   sql.append("      ,group_authority ga");
	   sql.append(" where not exists");
	   sql.append("      (select x.user_id");
	   sql.append("         from (select distinct p.skill_id, p.user_id");
	   sql.append("                 from practicum p ");
	   sql.append("              ) x ");
	   sql.append("         where x.user_id = u.user_id    ");
	   sql.append("         group by user_id");
	   sql.append("        having count(*) >= :minimumCount");
	   sql.append("      )");
	   sql.append("   and u.enabled = true");
	   sql.append("   and ((u.user_id = a.user_id ");
	   sql.append("         and a.authority = ct.code_table_id");
	   sql.append("         and ct.group_code = :groupCode");
	   sql.append("         and ct.code = :code");
	   sql.append("        ) or (");
	   sql.append("         u.user_id = gm.user_id");
	   sql.append("         and gm.group_id = ga.group_id");
	   sql.append("         and ga.authority = ct.code_table_id");
	   sql.append("         and ct.group_code = :groupCode");
	   sql.append("         and ct.code = :code");
	   sql.append("       ))");
	   
	   return (List<Users>) getSession().createSQLQuery(sql.toString())
	   		.addEntity(Users.class)
	   		.setInteger("minimumCount", minimumSkills)
	   		.setString("groupCode", "AUTHORITIES")
	   		.setString("code", ROLE_USER)
	   		.list();
   }
}
