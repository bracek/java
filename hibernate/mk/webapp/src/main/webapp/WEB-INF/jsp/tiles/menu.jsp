<%@ page import="com.ixonos.skillnet.logic.enumeration.SkillnetRole"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<table border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<div class="sdmenu">
				<!-- skills -->
				<security:authorize ifAnyGranted="ROLE_USER, ROLE_GROUP_MANAGER">
					<span class="title"><img
						src="<c:url value="/img/menu_title_arrow.gif"/>" class="arrow" />
						<fmt:message key="menu.skills" /></span>
					<div class="submenu">
						<security:authorize ifAnyGranted="ROLE_GROUP_MANAGER">
							<a href='<c:url value="/skills/skillsEdit.do"/>'><fmt:message
									key="menu.skills.edit" /></a>
						</security:authorize>

						<security:authorize ifAnyGranted="ROLE_USER">
							<a href='<c:url value="/skills/skillsPracticum.do"/>'><fmt:message
									key="menu.skills.practicum" /></a>
						</security:authorize>

						<security:authorize ifAnyGranted="ROLE_GROUP_MANAGER">
							<a href='<c:url value="/usermanagement/usermanagementFind.do"/>'><fmt:message
									key="menu.usermanagement.findUser" /></a>
						</security:authorize>

					</div>
				</security:authorize>

				<!-- usermanagement -->
				<security:authorize
					ifAnyGranted="ROLE_GROUP_MANAGER,ROLE_ADMIN, ROLE_USER">
					<span class="title"><img
						src="<c:url value="/img/menu_title_arrow.gif"/>" class="arrow" />
						<fmt:message key="menu.usermanagement" /></span>
					<div class="submenu">

						<security:authorize ifAnyGranted="ROLE_USER">
							<a
								href='<c:url value="/usermanagement/usermanagementEditDetail.do"/>'><fmt:message
									key="menu.usermanagement.edit.detail" /></a>
						</security:authorize>

						<security:authorize ifAnyGranted="ROLE_ADMIN">
							<a href='<c:url value="/usermanagement/usermanagementEdit.do"/>'><fmt:message
									key="menu.usermanagement.edit" /></a>
						</security:authorize>

					</div>
				</security:authorize>

				<!-- groupmanagement -->
				<security:authorize ifAnyGranted="ROLE_ADMIN">
					<span class="title"><img
						src="<c:url value="/img/menu_title_arrow.gif"/>" class="arrow" />
						<fmt:message key="menu.groupmanagement" /></span>
					<div class="submenu">
						<a href='<c:url value="/groupmanagement/groupmanagementEdit.do"/>'><fmt:message
								key="menu.groupmanagement.listGroup" /></a>
					</div>
				</security:authorize>

				<!-- reporting -->
				<security:authorize ifAnyGranted="ROLE_USER,ROLE_GROUP_MANAGER">
					<span class="title"><img
						src="<c:url value="/img/menu_title_arrow.gif"/>" class="arrow" />
						<fmt:message key="menu.reporting" /></span>
					<div class="submenu">

						<security:authorize ifAnyGranted="ROLE_USER">
							<a href='<c:url value="/reporting/reportingMyReport.do"/>'><fmt:message
									key="menu.reporting.myreport" /></a>
						</security:authorize>

						<security:authorize ifAnyGranted="ROLE_GROUP_MANAGER">
							<a href='<c:url value="/reporting/reportingCustomReport.do"/>'><fmt:message
									key="menu.reporting.reports" /></a>
						</security:authorize>
					</div>
				</security:authorize>

				<!-- company tree -->
				<security:authorize
					ifAnyGranted="ROLE_GROUP_MANAGER, ROLE_ADMIN, ROLE_USER">
					<span class="title"><img
						src="<c:url value="/img/menu_title_arrow.gif"/>" class="arrow" />
						<fmt:message key="menu.companytree" /></span>
					<div class="submenu">
						<security:authorize
							ifAnyGranted="ROLE_GROUP_MANAGER, ROLE_ADMIN, ROLE_USER">
							<a href='<c:url value="/companytree/companytreeList.do"/>'><fmt:message
									key="menu.companytree.companytree" /></a>
						</security:authorize>
					</div>
				</security:authorize>

				<!-- admin security -->
				<security:authorize ifAnyGranted="ROLE_ADMIN">
					<span class="title"><img
						src="<c:url value="/img/menu_title_arrow.gif"/>" class="arrow" />
						<fmt:message key="menu.admin" /></span>
					<div class="submenu">
						<a href='<c:url value="/admin/adminConfigParamsEdit.do"/>'><fmt:message
								key="menu.admin.configParams" /></a> <a
							href='<c:url value="/admin/adminImportUsers.do"/>'><fmt:message
								key="menu.admin.importUsers" /></a>
					</div>
				</security:authorize>

				<!-- help -->
				<security:authorize
					ifAnyGranted="ROLE_GROUP_MANAGER, ROLE_ADMIN, ROLE_USER">
					<span class="title"><img
						src="<c:url value="/img/menu_title_arrow.gif"/>" class="arrow" />
						<fmt:message key="menu.help" /></span>
					<div class="submenu">
						<security:authorize
							ifAnyGranted="ROLE_GROUP_MANAGER, ROLE_ADMIN, ROLE_USER">
							<a href='<c:url value="/help/userGuide.do"/>'><fmt:message
									key="menu.help.userGuide" /></a>
						</security:authorize>
					</div>
				</security:authorize>
			</div>
		</td>
	</tr>
</table>
