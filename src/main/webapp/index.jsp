<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="loginUrl" type="java.lang.String"--%>
<%--@elvariable id="logoutUrl" type="java.lang.String"--%>
<%--@elvariable id="name" type="type="java.lang.String"--%>
<%--@elvariable id="user" type="com.google.appengine.api.users.User"--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>Home</title>
  <link rel="stylesheet" href="<c:url value="/static/css/main.css"/>">
</head>
<body>

<h1>
  <c:choose>
    <c:when test="${name ne null}">
      <span>Welcome home ${name}</span>
    </c:when>
    <c:otherwise>
      <span>Welcome home ${user}</span>
    </c:otherwise>
  </c:choose>
</h1>

<p>
  <c:if test="${loginUrl ne null}">
    <a href="${loginUrl}">Log in</a>
  </c:if>
  <c:if test="${logoutUrl ne null}">
    <a href="${logoutUrl}">Log out</a>
  </c:if>
</p>

</body>
</html>
