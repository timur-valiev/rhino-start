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
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span6 offset3">
                <div class="page-header">
                    <h2>Rhino script processor</h2>
                </div>
                <br>
                Result: ${result}
                <br>
                <form:form modelAttribute="rhinoform" action="/rhino/process" method="post" class="well">
                    <form:label path="script">Enter script</form:label>
                    <form:textarea path="script" class="input-xlarge" style="height:400px;width:300px;"/>
                    <form:errors path="script" cssClass="error" />
                    <br>
                    <input type="submit" value="Submit" class="btn btn-primary"/>
                </form:form>
            </div>
        </div>
    </div>
</body>
</html>
