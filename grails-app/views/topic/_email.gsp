<div class="modal fade" id="invitation" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Send invitation</h4>
            </div>
            <div class="modal-body">
                <g:form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Email</label>
                        <div class="col-sm-10">
                            <g:field type="email" name="email" class="form-control" id="email" placeholder="Email"/>
                        </div>
                        <label class="col-sm-2 control-label">Topic</label>
                        <div class="col-sm-10">
                            <div class="dropdown">
                                <g:select name="topic" from="${topicList}" optionKey="id" />
                            </div>
                        </div>
                    </div>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary">Invite</button>
                </g:form>
            </div>
            <div class="modal-footer">
            </div>
        </div>
    </div>
</div>