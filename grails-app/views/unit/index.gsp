

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title></title>
</head>

<body>
<g:each in="${user}" var="u">
    ${u}
    <tl:showAdmin isAdmin="${u.admin}">Admin content</tl:showAdmin>
</g:each>

<tl:showUserList userList="${user}"/>

<g:form action="save">
    <label></label>
    <g:textField name="firstName"></g:textField>
    <g:renderErrors bean="${user}"></g:renderErrors>
    <label>Password</label>
    <g:passwordField name="password"></g:passwordField>
    <label>YES</label><g:checkBox name="cbox"></g:checkBox>
    <label>M</label><g:radio name="r"></g:radio>
    <label>F</label><g:radio name="r"></g:radio>
    <g:submitButton name="submit"></g:submitButton>
</g:form>

</body>
</html>