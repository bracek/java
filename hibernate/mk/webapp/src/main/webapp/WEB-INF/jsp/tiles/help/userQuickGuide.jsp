<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<security:authorize
    ifAnyGranted="ROLE_USER,ROLE_GROUP_MANAGER,ROLE_ADMIN">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/main.css"/>" media="screen"/>
        <link rel="stylesheet" type="text/css" href="<c:url value="/css/menu.css"/>" media="screen"/>
    </head>
    <body>
        <h1>SkillNET – user quick guide</h1>
        <table border="0" width="98%" align="center">
            <thead>
                <tr>
                    <th></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td class="userguide">
                        SkillNET is a tool for the maintanance of skills for every employee.
                        <br>
                        <br>
                        <u>Users are authorized to: </u>
                        <li>         fill in their experiences  in the time period and appropriate levels (Skills → Practicum) </li>
                        <li>      edit personal details (User management → Edit user’s detail) </li>
                        <li>     create own report (Reporting → My report) </li>


                        <br>
                        <p class="title">
                            1.     Skills → Practicum
                        </p>
                        <br>

                        <p>
                            <img src="<c:url value="/img/help/userGuide/image001.jpg"/>" class="arrow"/>
                        </p>

                        <br>
                        <u> The practicum screen is divided into three parts:</u>

                        <li >Tree –structured tree of skills </li>

                        <li>Practicum – list of experiences related with selected skill in tree</li>

                        <li>      Skills – list of skills structured in table with full text search possibility</li>

                        <br>
                        <u> The user can choose the skill in two ways:</u>

                        <li>1.      by expanding nodes in the <b>Tree</b> panel and selecting the concrete skill (leaf of tree)</li>

                        <li>2.      by full text searching in the <b>Skills</b> panel and selecting skill in table.</li>

                        <br>
                        If the user chooses the second option then the skill will be automatically pre-selected in the <b>Tree</b> panel.
                        <br>
                        <br>
                        After the skill is selected, the user can fill information in the <b>Practicum</b> panel. The practicum panel is used for filling in time periods and levels in the context of the selected skill. For each skill user can define more practices which can be updated or deleted.
                        <br>
                        <br>
                        Each skill in the tree is flagged with an icon which provides quick information about user’s experiences with the related skill.
                        <br>
                        <br>
                        <p class="title">
                            2.     User management → Edit user’s detail
                        </p>
                        <br>
                        <p>
                            <img src="<c:url value="/img/help/userGuide/image002.jpg"/>" class="arrow"/>
                        </p>
                        In this part users can change their personal details and upload or update their CVs in the Ixonos format.


                        <br>
                        <br>
                        <p class="title">
                            3.     Reporting → My report
                        </p>
                        <br>

                        In this section user can create report in different formats (PDF, XML, HTML, RTF, Excel…). Report contains all user’s experiences with date and level information.
                        <img src="<c:url value="/img/help/userGuide/image002.jpg"/>" class="arrow"/>


                        <p>
                            <img src="<c:url value="/img/help/userGuide/image004.jpg"/>" class="arrow"/>
                        </p>
                        In the upper right corner the name of logged in user is visible with the possibility to change his password and logout from SkillNET.


                    </td>
                </tr>
            </tbody>
        </table>
    </body>
</html>
</security:authorize>
