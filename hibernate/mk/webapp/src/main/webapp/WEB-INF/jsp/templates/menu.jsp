<%@ page import="sk.mka.web.spring.SkillnetRole" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div id="side-bar">

    <security:authorize ifAnyGranted="ROLE_USER, ROLE_GROUP_MANAGER">
        <span class="title"><img src="<c:url value="/img/menu_title_arrow.gif"/>" class="arrow"/><fmt:message key="menu.skills" /></span>
        <div class="submenu">
            <security:authorize ifAnyGranted="ROLE_GROUP_MANAGER">
                <a href='<c:url value="/skills/skillsEdit.do"/>'><fmt:message key="menu.skills.edit" /></a>
            </security:authorize>

            <security:authorize ifAnyGranted="ROLE_USER">
                <a href='<c:url value="/skills/skillsPracticum.do"/>'><fmt:message key="menu.skills.practicum" /></a>
            </security:authorize>

            <security:authorize ifAnyGranted="ROLE_GROUP_MANAGER">
                <a href='<c:url value="/usermanagement/usermanagementFind.do"/>'><fmt:message key="menu.usermanagement.findUser" /></a>
            </security:authorize>

        </div>
    </security:authorize>

    <!-- usermanagement -->
    <security:authorize ifAnyGranted="ROLE_GROUP_MANAGER,ROLE_ADMIN, ROLE_USER">
        <span class="title"><img src="<c:url value="/img/menu_title_arrow.gif"/>" class="arrow"/><fmt:message key="menu.usermanagement" /></span>
        <div class="submenu">

            <security:authorize ifAnyGranted="ROLE_USER">
                <a href='<c:url value="/usermanagement/usermanagementEditDetail.do"/>'><fmt:message key="menu.usermanagement.edit.detail" /></a>
            </security:authorize>

            <security:authorize ifAnyGranted="ROLE_ADMIN">
                <a href='<c:url value="/usermanagement/usermanagementEdit.do"/>'><fmt:message key="menu.usermanagement.edit" /></a>
            </security:authorize>

        </div>
    </security:authorize>

    <!-- groupmanagement -->
    <security:authorize ifAnyGranted="ROLE_ADMIN">
        <span class="title"><img src="<c:url value="/img/menu_title_arrow.gif"/>" class="arrow"/><fmt:message key="menu.groupmanagement" /></span>
        <div class="submenu">
            <a href='<c:url value="/groupmanagement/groupmanagementEdit.do"/>'><fmt:message key="menu.groupmanagement.listGroup" /></a>
        </div>
    </security:authorize>

    <!-- reporting -->
    <security:authorize ifAnyGranted="ROLE_USER,ROLE_GROUP_MANAGER">
        <span class="title"><img src="<c:url value="/img/menu_title_arrow.gif"/>" class="arrow"/><fmt:message key="menu.reporting" /></span>
        <div class="submenu">

            <security:authorize ifAnyGranted="ROLE_USER">
                <a href='<c:url value="/reporting/reportingMyReport.do"/>'><fmt:message key="menu.reporting.myreport" /></a>
            </security:authorize>

            <security:authorize ifAnyGranted="ROLE_GROUP_MANAGER">
                <a href='<c:url value="/reporting/reportingCustomReport.do"/>'><fmt:message key="menu.reporting.reports" /></a>
            </security:authorize>
        </div>
    </security:authorize>

    <!-- company tree -->
    <security:authorize ifAnyGranted="ROLE_GROUP_MANAGER, ROLE_ADMIN, ROLE_USER">
        <span class="title"><img src="<c:url value="/img/menu_title_arrow.gif"/>" class="arrow"/><fmt:message key="menu.companytree" /></span>
        <div class="submenu">
            <security:authorize ifAnyGranted="ROLE_GROUP_MANAGER, ROLE_ADMIN, ROLE_USER">
                <a href='<c:url value="/companytree/companytreeList.do"/>'><fmt:message key="menu.companytree.companytree" /></a>
            </security:authorize>
        </div>
    </security:authorize>

    <!-- admin security -->
    <security:authorize ifAnyGranted="ROLE_ADMIN">
        <span class="title"><img src="<c:url value="/img/menu_title_arrow.gif"/>"  class="arrow"/><fmt:message key="menu.admin" /></span>
        <div class="submenu">
            <a href='<c:url value="/admin/adminConfigParamsEdit.do"/>'><fmt:message key="menu.admin.configParams" /></a>
            <a href='<c:url value="/admin/adminImportUsers.do"/>'><fmt:message key="menu.admin.importUsers" /></a>
        </div>
    </security:authorize>

    <!-- help -->
    <security:authorize ifAnyGranted="ROLE_GROUP_MANAGER, ROLE_ADMIN, ROLE_USER">
        <span class="title"><img src="<c:url value="/img/menu_title_arrow.gif"/>" class="arrow"/><fmt:message key="menu.help" /></span>
        <div class="submenu">
            <security:authorize ifAnyGranted="ROLE_GROUP_MANAGER, ROLE_ADMIN, ROLE_USER">
                <a href='<c:url value="/help/userGuide.do"/>'><fmt:message key="menu.help.userGuide" /></a>
            </security:authorize>
        </div>
    </security:authorize>


    <!-- ROLE_USER -->
    <security:authorize ifAnyGranted="ROLE_USER">
        <p>Menu</p>
        <p><a href="<c:url value="/person.do"/>"><fmt:message key="button.create"/></a></p>
        <p><a href="<c:url value="/person/search.do"/>"><fmt:message key="button.search"/></a></p>

        <!--
        <a href="<c:url value="/"/>">Login</a>
        <p><a href="<c:url value="/faces/user/registration.faces"/>"><fmt:message key="menu.registration"/></a></p>
        -->
        <p><a href="<c:url value="/anonymous/about.do"/>"><fmt:message key="menu.about"/></a></p>
        <p><a href="<c:url value="/anonymous/curriculum.do"/>"><fmt:message key="menu.curriculum"/></a></p>
        <p><a href="<c:url value="/menu/message/viewMessages.do"/>"><fmt:message key="menu.insertMessage"/></a></p>
    </security:authorize>


    <!-- ROLE_GROUP_MANAGER -->
    <secure:authorize ifAnyGranted="ROLE_GROUP_MANAGER">
        <security:authorize ifAnyGranted="ROLE_GROUP_MANAGER">
            <p>Submenu</p>
        </security:authorize>

        <!-- TODO rewrite register to adding product -->
        <security:authorize ifAnyGranted="ROLE_GROUP_MANAGER">
            <p><a href="<c:url value="/register.do"/>"><fmt:message key="menu.product"/></a></p>
        </security:authorize>

        <security:authorize ifAnyGranted="ROLE_GROUP_MANAGER">
            <p><a href="<c:url value="/book/viewBooks.do"/>"><fmt:message key="submenu.insertBook"/></a></p>
        </security:authorize>
    </secure:authorize>

    <!-- ROLE_ADMIN -->
    <secure:authorize ifAnyGranted="ROLE_ADMIN">
        <p>Admin</p>
        <!--
        <p><a href="<c:url value="/users/list.do"/>"><fmt:message key="button.listUsers"/></a></p>
        -->
    </secure:authorize>

</div>
