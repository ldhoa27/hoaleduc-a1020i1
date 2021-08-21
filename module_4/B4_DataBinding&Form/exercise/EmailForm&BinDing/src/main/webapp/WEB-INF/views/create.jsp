<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: OS
  Date: 6/12/2021
  Time: 9:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form modelAttribute="email" action="/create" method="post">
    <table>
        <tr>
            <td><form:label path="language">Language</form:label></td>
            <td><form:select path="language" items="${languages}"/></td>
        </tr>
        <tr>
            <td><form:label path="pageSize">Page Size</form:label></td>
            <td><form:select path="pageSize" items="${pageSizes}"/> </td>
        </tr>
        <tr>
            <td><form:label path="spamFillter">Spams Fillter</form:label></td>
            <td><form:checkbox path="spamFillter" value="true"/> Enable Spam Fillter </td>
        </tr>

        <tr>
            <td><form:label path="signature">Signature</form:label></td>
            <td><form:textarea path="signature"/> </td>
        </tr>

        <tr>
            <td><input type="submit" value="Submit"/></td>
        </tr>
    </table>

</form:form>
</body>
</html>
