<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: OS
  Date: 6/12/2021
  Time: 10:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="menu.jsp"/>

<h3>Upload Multiple File:</h3>

<!-- MyUploadForm -->

<%--@elvariable id="myUploadForm" type=""--%>
<form:form modelAttribute="myUploadForm" method="POST" action="" enctype="multipart/form-data">

    Description:

    <br>

    <form:input path="description" style="width:300px;"/>

    <br/><br/>

    File to upload (1): <form:input path="fileDatas" type="file"/><br />

    File to upload (2): <form:input path="fileDatas" type="file"/><br />

    File to upload (3): <form:input path="fileDatas" type="file"/><br />

    File to upload (4): <form:input path="fileDatas" type="file"/><br />

    File to upload (5): <form:input path="fileDatas" type="file"/><br />

    <input type="submit" value="Upload">

</form:form>
</body>
</html>
