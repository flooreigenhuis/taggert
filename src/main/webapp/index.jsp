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

<%--@elvariable id="loginUrl" type="java.lang.String"--%>
<%--@elvariable id="logoutUrl" type="java.lang.String"--%>
<%--@elvariable id="name" type="type="java.lang.String"--%>
<%--@elvariable id="user" type="com.google.appengine.api.users.User"--%>
<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">

<jsp:include page="/static/partials/head.jsp"/>

<body>

<jsp:include page="/static/partials/nav.jsp"/>

<div class="container main">


  <div class="row">
    <div class="col s12 center-align">
      <img class="target"
           alt="random"
           src="https://vignette.wikia.nocookie.net/dragonballfanon/images/7/70/Random.png/revision/latest?cb=20161221030547">
    </div>
  </div>

  <div class="row">
    <div class="col s6 center-align">
      <button class="btn btn-large cat-opt">Cat</button>
    </div>
    <div class="col s6 center-align">
      <button class="btn btn-large cat-opt">Dog</button>
    </div>
    <div class="col s6 center-align">
      <button class="btn btn-large cat-opt">Ferret</button>
    </div>
    <div class="col s6 center-align">
      <button class="btn btn-large cat-opt">Unicorn</button>
    </div>
  </div>

</div>

<jsp:include page="/static/partials/bottom.jsp"/>

</body>
</html>
