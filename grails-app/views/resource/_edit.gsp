<div class="modal fade" id="editResource" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Share Link</h4>
            </div>

            <div class="modal-body">
                <g:form class="form-horizontal" controller="resource" action="saveLinkResource">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Description</label>

                        <div class="col-sm-10">
                            <g:textArea name="description" value="${resource?.description}" class="form-control"
                                        rows="5" id="comment"/>
                            <g:hiddenField name="id" value="${resource?.id}"></g:hiddenField>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <g:submitButton name="save" type="submit" formaction="/Resource/update" value="Update"
                                        class="btn btn-primary btn-block"/>
                    </div>
                </g:form>
            </div>
        </div>
    </div>
</div>