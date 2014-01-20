<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<table border="0" class="header" width="100%">
	<tbody>
		<tr>
			<td align="right">
				<%-- principal equals com.ixonos.skillnet.logic.security.SkillnetUser
            for example: pricipal.fullName == skillnetUser.getFullName() --%>
				<fmt:message key="header.loggeduser" />:&nbsp;

			</td>
		</tr>
		<tr>
			<td align="right">
				<%-- not needed when LDAP is used
                <jsp:include page="/WEB-INF/jsp/tiles/usermanagement/changePasswordLabel.zul"/>
                --%>
			</td>
		</tr>
	</tbody>
</table>