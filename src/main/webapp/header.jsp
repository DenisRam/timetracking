<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="pagecontent" var="content"/>


<html>
<head>
    <title><fmt:message key="title" bundle="${content}"/> </title>
    <style>
        <%@include file='css/header.css' %>
    </style>

</head>
<body>
    <div class="header">
        <p> <fmt:message key="head.logo.message" bundle="${content}"/></p>
        <form name = "changeLocale" method="post" action="controller">
            <input type="hidden" name="command" value="changeLocale">
            <c:set var = "currentPage" value="${currentPage}" scope="request"/>
            <input type="hidden" name="currentPage" value="${currentPage}">
            <button class="change_locale" type="submit" name="locale" value="0">English</button>
            <button class="change_locale" type="submit" name="locale" value="1">Руский</button>

        </form>
        <c:if test="${currentPage} != path.page.index">
        <form name="logout" method="get" action="logout">
            <input type="hidden" name="command" value="logout">
            <button class="change_locale" type="submit">
                <fmt:message key="button.logout" bundle="${content}"/>
            </button>
        </form>
        </c:if>


    </div>

</body>
</html>
