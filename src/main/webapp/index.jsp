<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<c:if test="${locale == 0}"><fmt:setLocale value="en_US" scope="session"/></c:if>
<c:if test="${locale == 1}"><fmt:setLocale value="ru_RU" scope="session"/></c:if>

<fmt:setBundle basename="pagecontent" var="pc"/>
<fmt:setBundle basename="message" var="message"/>
<html>
  <head>
      <c:set var="currentPage" value="path.page.index" scope="request"/>
      <title><fmt:message key="title" bundle="${pc}"/></title>

      <%--<style>--%>
        <%--<%@include file='css/login.css' %>--%>
      <%--</style>--%>


  </head>

  <body class="login_body">
      <header class="header">
        <c:import url="header.jsp"/>
      </header>


        <form name="login_form" method="post" action="createUser">
          <div class="login_form_element">
            <p class="input_element_text">
                <fmt:message key="login.login" bundle="${pc}"/>
            </p>
            <input class="loginField" type="text" name="login" placeholder="login">
          </div>

          <div class="login_form_element">
            <p class="inputElementText">
            <fmt:message key="login.password" bundle="${pc}"/>
            </p>
            <input class="passwordField" type="text" name="password" placeholder="password">
          </div>

          <div class="logInFormElement">
            <button class="loginButton" type="submit" name="command" value="singIn">
              <fmt:message key="login.button.singIn" bundle="${pc}"/>
            </button>
          </div>




        </form>




      asdkfjsakl;jdsadfsadfsadfsadfsadfasd
</body>
</html>
