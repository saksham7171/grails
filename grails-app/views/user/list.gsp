<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title>Users</title>
</head>

<body>
<div class="panel panel-default">
    <div class="panel-heading">
        <div class="row">
            <div class="col-md-2">
                Users
            </div>

            <div class="col-md-8">
                <g:form name="adminUsersSearchForm" class="form-inline" controller="user" action="list">
                    <div class="form-group col-md-3">
                        <div class="input-group">
                            <select name="active" id="active" class="btn btn-primary">
                                <option value="${null}">All users</option>
                                <option value="${true}">Active users</option>
                                <option value="${false}">Inactive users</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-6">
                            <g:textField name="q" type="text" class="form-control" id="q" placeholder="Search"/>
                                    <button type="submit">
                                        <span class="glyphicon glyphicon-search" style="font-size: small"></span>
                                    </button>

                    </div>
                </g:form>
            </div>
        </div>
    </div>

    <div class="panel-body">
        <div class="table-responsive">
            <table class="table table-condensed table-hover">
                <thead>
                <tr>
                    <g:sortableColumn property="id" title="Id"/>
                    <g:sortableColumn property="username" title="Username"/>
                    <g:sortableColumn property="email" title="Email"/>
                    <g:sortableColumn property="firstName" title="FirstName"/>
                    <g:sortableColumn property="lastName" title="LastName"/>
                    <g:sortableColumn property="active" title="Active"/>
                    <th>Manage</th>
                </tr>
                </thead>
                <g:each in="${users}" var="user">
                    <g:if test="${user.isActive}">
                        <g:set var="alertClass" value="alert alert-success"/>
                    </g:if>
                    <g:else>
                        <g:set var="alertClass" value="alert alert-danger"/>
                    </g:else>
                    <tr class="${alertClass}">
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.email}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.isActive}</td>
                        <td>
                            <g:if test="${user.isActive}">
                                <g:link controller="user" action="toggleActive"
                                        params="[id: user.id]">De-activate</g:link>
                            </g:if>
                            <g:else>
                                <g:link controller="user" action="toggleActive" params="[id: user.id]">Activate</g:link>
                            </g:else>
                        </td>
                    </tr>
                </g:each>
            </table>
        </div>
    </div>
</div>
</body>
</html>