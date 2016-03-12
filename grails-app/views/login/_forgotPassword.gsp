<div id="pwdModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>

                <h1 class="text-center">What's My Password?</h1>
            </div>

            <div class="modal-body">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div class="text-center">

                                <p>If you have forgotten your password you can reset it here.</p>

                                <div class="panel-body">
                                <fieldset>
                                    <g:form>
                                        <div class="form-group">
                                            <input class="form-control input-lg" id="email" placeholder="E-mail Address"
                                                   name="email" type="email">
                                        </div>
                                        <g:submitButton type="submit" name="submit" value="Send My Password"
                                                        formaction="/user/forgotPassword"
                                                        class="btn btn-lg btn-primary btn-block"/>
                                        </fieldset>
                                    </g:form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="modal-footer">
                <div class="col-md-12">
                    <button class="btn" data-dismiss="modal" aria-hidden="true">Cancel</button>
                </div>
            </div>
        </div>
    </div>
</div>