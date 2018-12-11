<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@elvariable id="loginUrl" type="java.lang.String"--%>
<%--@elvariable id="logoutUrl" type="java.lang.String"--%>
<%--@elvariable id="currentUser" type="com.gstar.data.common.appengine.UserFactory.AppUser"--%>
<%--@elvariable id="authManager" type="com.gstar.data.common.appengine.AuthManager"--%>

<nav>
    <div class="nav-wrapper">
        <a href="#" data-target="slide-menu" class="sidenav-trigger show-on-large"><i class="material-icons">menu</i></a>

        <a href="#" class="brand-logo">Taggert</a>

        <ul class="right hide-on-med-and-down">
            <li>${currentUser.userId}</li>
            <c:if test="${loginUrl ne null}">
                <a href="${loginUrl}">Log in</a>
            </c:if>
            <c:if test="${logoutUrl != null}">
                <li><a href="${logoutUrl}"><i class="material-icons left">account_circle</i>Log out</a></li>
            </c:if>
        </ul>

    </div>
</nav>

<ul class="sidenav" id="slide-menu">
    <li><a href="${logoutUrl}"><i class="material-icons left">account_circle</i>Log out</a></li>
</ul>
