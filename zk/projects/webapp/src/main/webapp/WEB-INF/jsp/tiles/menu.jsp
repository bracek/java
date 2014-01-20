<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<table border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<!--            <div class="sdmenu">
                            <div class="submenu">
                                <span class="title"><img alt=""  src="<c:url value="/img/menu_title_arrow.gif"/>" class="arrow"/><fmt:message key="menu.all" /></span>
                                <a href='<c:url value="/usermanagement/usersAdd.do"/>'><fmt:message key="menu.usermanagement.add" /></a>
                            </div>
                        </div>--> <!-- projects --> <security:authorize
				ifAnyGranted="ROLE_GROUP_MANAGER, ROLE_ADMIN">
				<div class="sdmenu">
					<span class="title"><img alt=""
						src="<c:url value="/img/menu_title_arrow.gif"/>" class="arrow" />
						<fmt:message key="menu.projects" /></span>
					<div class="submenu">
						<a href='<c:url value="/projects/editProject.do"/>'><fmt:message
								key="menu.projects.edit" /></a>
					</div>
					<div class="submenu">
						<a href='<c:url value="/projects/timeline.do"/>'><fmt:message
								key="menu.projects.timeline" /></a>
					</div>
					<div class="submenu">
						<a href='<c:url value="/projects/editAllocation.do"/>'><fmt:message
								key="menu.projects.allocation" /></a>
					</div>
				</div>
			</security:authorize> <!-- usermanagement --> <security:authorize
				ifAnyGranted="ROLE_USER, ROLE_GROUP_MANAGER, ROLE_ADMIN">

				<div class="sdmenu">
					<span class="title"><img
						src="<c:url value="/img/menu_title_arrow.gif"/>" class="arrow" />
						<fmt:message key="menu.usermanagement" /></span>

					<div class="submenu">
						<security:authorize
							ifAnyGranted="ROLE_USER, ROLE_GROUP_MANAGER, ROLE_ADMIN">
							<a
								href='<c:url value="/usermanagement/usermanagementEditDetail.do"/>'><fmt:message
									key="menu.usermanagement.edit.detail" /></a>
						</security:authorize>

						<security:authorize ifAnyGranted="ROLE_ADMIN">
							<a href='<c:url value="/usermanagement/usermanagementEdit.do"/>'><fmt:message
									key="menu.usermanagement.edit" /></a>
							<a
								href='<c:url value="/usermanagement/usermanagementImport.do"/>'><fmt:message
									key="menu.usermanagement.import" /></a>
						</security:authorize>
					</div>
				</div>
			</security:authorize> <!-- groupmanagement --> <security:authorize
				ifAnyGranted="ROLE_ADMIN">
				<div class="sdmenu">

					<span class="title"><img
						src="<c:url value="/img/menu_title_arrow.gif"/>" class="arrow" />
						<fmt:message key="menu.groupmanagement" /></span>
					<div class="submenu">
						<a href='<c:url value="/groupmanagement/groupmanagementEdit.do"/>'><fmt:message
								key="menu.groupmanagement.listGroup" /></a>
					</div>
				</div>
			</security:authorize>


		</td>
	</tr>
</table>
