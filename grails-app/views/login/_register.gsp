<div class="panel panel-default">
    <div class="panel-heading">
        Register
    </div>

    <div class="panel-body">
<g:uploadForm class="form-horizontal" controller="user" action="register">
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
        <label class="col-xs-3 control-label">Email</label>

        <div class="col-xs-9">
            <g:field type="email" class="form-control" id="email" name="email" value="${user?.email}"
                     placeholder="Email"/>
            <div class="alert-danger">
                <g:fieldError field="email" bean="${user}"/>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-xs-3 control-label">UserName</label>

        <div class="col-xs-9">
            <g:field type="text" class="form-control" id="username" name="username" value="${user?.username}"
                     placeholder="UserName"/>
            <div class="alert-danger">
                <g:fieldError field="username" bean="${user}"/>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-xs-3 control-label">Password</label>

        <div class="col-xs-9">
            <g:field type="password" class="form-control" id="password" name="password" placeholder="password"/>
            <div class="alert-danger">
                <g:fieldError field="password" bean="${user}"/>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-xs-3 control-label">Confirm Password</label>

        <div class="col-xs-9">
            <g:field type="password" class="form-control" id="confirmPassword" name="confirmPassword"
                     placeholder="Confirm Password"/>
            <div class="alert-danger">
                <g:fieldError field="confirmPassword" bean="${user}"/>
            </div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-xs-3 control-label">Photo</label>
        <input type="file" name="photo" id="photo">
    <div class="form-group">
        <div class="col-xs-offset-3 col-xs-10">
%{--<g:actionSubmit  formaction="/user/register" type="submit" class="btn btn-primary" value="Register"/>--}%
    <g:submitButton name="register" type="submit" class="btn btn-primary" value="Register"/>
    </div>
</div>
</g:uploadForm>

</div>
</div>