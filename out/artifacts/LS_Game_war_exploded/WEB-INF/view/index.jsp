<%--
  Created by IntelliJ IDEA.
  User: LS
  Date: 15.04.2016
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<!-- обратите внимание на spring тэги -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Index Page</title>
</head>
<body>
<spring:form method="post"  modelAttribute="userJSP" action="check-user">
    <%--
    (path="" - указывает путь, используемый в modelAttribute=''. в нашем случае User.name)
    --%>
    Name: <spring:input path="name"/> <br/>
    Password: <spring:input path="password"/>   <br/>
    <spring:button>Next Page</spring:button>

</spring:form>

</body>
</html>
