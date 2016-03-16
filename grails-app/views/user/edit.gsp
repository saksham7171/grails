
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title></title>
</head>

<body>
<div class="col-xs-5">
    <g:render template="user" model="[user:session.user]"></g:render>
    <g:hiddenField name="id" id="id" value="${user.id}"/>
    <div class="panel panel-default">
        <div class="panel-heading">
            Topics <a href="#" class="inline" style="float:right"></a>
        </div>

        <div class="panel-body" style="overflow-y:auto;height: 500px">
            <div id="createdTopics">
            </div>
        </div>
    </div>

</div>

<div class="col-xs-7 col">
    <div class="panel panel-default">
        <div class="panel-heading">
                Profile
        </div>

        <div class="panel-body" style="height: 300px">
            <g:uploadForm class="form-horizontal" controller="user" action="save">
                <div class="form-group">
                    <label class="col-xs-3 control-label">First Name</label>

                    <div class="col-xs-9">
                        <g:field type="text" class="form-control" id="firstName" name="firstName" value="${user?.firstName}"
                                 placeholder="First Name"/>
                        <div class="alert-danger">
                            <g:fieldError field="firstName" bean="${user}"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 control-label">Last Name</label>

                    <div class="col-xs-9">
                        <g:field type="text" class="form-control" id="lastName" value="${user?.lastName}" name="lastName"
                                 placeholder="Last Name"/>
                        <div class="alert-danger">
                            <g:fieldError field="lastName" bean="${user}"/>
                        </div>
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-xs-3 control-label">UserName</label>

                    <div class="col-xs-9">
                        <g:field type="text" class="form-control" id="username" name="username" value="${user?.username}"
                                 placeholder="UserName" disabled="true" />
                        <div class="alert-danger">
                            <g:fieldError field="username" bean="${user}"/>
                        </div>
                        <g:hiddenField name="email" value="${user?.email}"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 control-label">Photo</label>
                    <input type="file" class="btn btn-primary" name="photo" id="photo">
                <div class="form-group">
                <hr>
                    <div class="col-xs-offset-9 col-xs-10">
                <g:submitButton name="register" type="submit" class="btn btn-primary" value="Update"/>
                </div>
            </div>
            </g:uploadForm>

        </div>
    </div>


</div>

    <div class="panel panel-default">
        <div class="panel-heading">
            Change Password
        </div>

        <div class="panel-body" style="height: 300px">
            <g:form class="form-horizontal" name="login">
                <div class="form-group">
                    <label class="col-xs-2 control-label">Old Password</label>

                    <div class="col-xs-10">
                        <g:passwordField name="oldPassword" class="form-control" id="oldPassword" placeholder="old Password"/>
                    </div>
                    <g:hiddenField name="id" value="${user.id}"></g:hiddenField>
                </div>

                <div class="form-group">
                    <label class="col-xs-2 control-label">New Password</label>

                    <div class="col-xs-10">
                        <g:passwordField name="password" class="form-control" id="password" placeholder="new Password"/>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-2 control-label">Confirm Password</label>

                    <div class="col-xs-10">
                        <g:passwordField name="confirmPassword" class="form-control" id="confirmPassword" placeholder="confirm Password"/>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-xs-offset-9 col-xs-10">
                        <g:submitButton name="submit" formaction="/user/updatePassword" type="submit" value="Update" class="btn btn-primary"/>
                    </div>
                </div>
            </g:form>
        </div>
</div>

</div>
<g:javascript>
    $(document).ready(function () {
        $.ajax({
            url:"/user/topics",
            data:{id:$("#id").val()},
            success:function(result){
                $('#createdTopics').html(result)
            }
        });
    });
</g:javascript>
</body>
</html>