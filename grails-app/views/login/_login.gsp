<div class="panel panel-default">
    <div class="panel-heading">
        Login
    </div>

    <div class="panel-body">
        <g:form class="form-horizontal">
            <div class="form-group">
                <label class="col-xs-2 control-label">UserName</label>

                <div class="col-xs-10">
                    <g:field name="username" type="text" class="form-control" id="name" placeholder="Username"/>
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-2 control-label">Password</label>

                <div class="col-xs-10">
                    <g:passwordField name="password" class="form-control" id="Password" placeholder="Password"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-offset-2 col-xs-10">
                    <div class="checkbox">
                        <label>
                            <input type="checkbox"> Remember me
                        </label>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-offset-2 col-xs-10">
                    <g:submitButton name="submit" formaction="/login/loginHandler" type="submit" value="Sign in" class="btn btn-primary"/>
                </div>
            </div>
        </g:form>
        <a href="#"><p class="text-right">Forgot Password?</p></a>
    </div>
</div>