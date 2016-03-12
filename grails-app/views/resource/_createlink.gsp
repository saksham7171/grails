<div class="modal fade" id="link" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Share Link</h4>
            </div>
            <div class="modal-body">
                <g:form class="form-horizontal" controller="resource" action="saveLinkResource">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Link</label>
                        <div class="col-sm-10">
                            <g:field type="url" name="url" class="form-control" id="url" placeholder="URL"/>
                        </div>
                        <label class="col-sm-2 control-label">Description</label>
                        <div class="col-sm-10">
                            <g:textArea name="description" class="form-control" rows="5" id="comment"/>
                        </div>
                        <label class="col-sm-2 control-label">Topic</label>
                        <div class="col-sm-10">
                            <div class="dropdown">
                                <g:select name="topic" from="${topiclist}" optionKey=""/>
                            </div>
                        </div>
                    </div>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <g:submitButton name="save" type="submit" formaction="/LinkResource/save" value="Save" class="btn btn-primary"/>
                </g:form>
            </div>
            <div class="modal-footer">

            </div>
        </div>
    </div>
</div>