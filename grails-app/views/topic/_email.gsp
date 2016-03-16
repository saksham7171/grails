<div class="modal fade" id="invitation" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Send invitation</h4>
            </div>
            <div class="modal-body">
                <g:form class="form-horizontal" controller="topic" action="invite">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-10">
                            <g:field type="email" name="email" class="form-control" id="email" placeholder="Email"/>
                        </div>
                        <label class="col-sm-2 control-label">Topic</label>
                        <div class="col-sm-10">
                                <ls:showTopicList/>
                        </div>
                    </div>
                    <div class="modal-footer">
                    <g:submitButton name="emailBtn" type="submit" class="btn btn-primary btn-block" value="Invite"/>
                    </div>
                </g:form>
            </div>

        </div>
    </div>
</div>