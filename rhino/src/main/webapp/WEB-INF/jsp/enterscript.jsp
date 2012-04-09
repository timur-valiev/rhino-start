<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
    <link href="/static/bootstrap/css/bootstrap.min.css"/ rel="stylesheet" type="text/css" >
    <link href="/static/css/fieldError.css"/ rel="stylesheet" type="text/css" >
    <script type="text/javascript" src="/static/js/jquery.js"></script>
    <script type="text/javascript" src="/static/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
    <%@ include file="header.jsp" %>
    <div class="container" align="center">
                <div class="page-header">
                    <h2>${lang} script processor</h2>
                </div>

                <br>
                <form:form modelAttribute="scriptform" action="/${lang}/process" method="post" class="well">
                    <table>
                        <tr>
                            <td>
                                Result: ${result}
                            </td>

                            <td>
                                <form:label path="alias">Enter objects alias</form:label>
                                <form:input path="alias" class="input"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <form:label path="script">Enter script</form:label>
                                <form:textarea path="script" class="input-xlarge" style="height:300px;width:300px;"/>
                                <form:errors path="script" cssClass="error" />
                            </td>
                            <td>
                                <form:label path="object">Enter JSON</form:label>
                                <form:textarea path="object" class="input-xlarge" style="height:300px;width:300px;"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="submit" value="Submit" class="btn btn-primary"/>
                            </td>
                        </tr>
                    </table>
                </form:form>

    </div>
</body>
</html>
