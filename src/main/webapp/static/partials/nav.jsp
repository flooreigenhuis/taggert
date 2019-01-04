<!--
Copyright 2019 Incentro

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
-->

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
                <li><a href="${loginUrl}"><i class="material-icons left">account_circle</i>Log in</a></li>
            </c:if>
            <c:if test="${logoutUrl ne null}">
                <li><a href="${logoutUrl}"><i class="material-icons left">account_circle</i>Log out</a></li>
            </c:if>
        </ul>

    </div>
</nav>

<ul class="sidenav" id="slide-menu">
    <c:if test="${loginUrl ne null}">
        <li><a href="${loginUrl}"><i class="material-icons left">account_circle</i>Log in</a></li>
    </c:if>
    <c:if test="${logoutUrl ne null}">
        <li><a href="${logoutUrl}"><i class="material-icons left">account_circle</i>Log out</a></li>
    </c:if>
</ul>
