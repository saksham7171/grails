<div class="modal fade" id="document" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Share Document</h4>
            </div>

            <div class="modal-body">
                <g:uploadForm class="form-horizontal" controller="resource" action="saveDocumentResource">
                    <div class="form-group">
                        <div class="row">
                            <label class="col-sm-2 control-label">Document</label>

                            <div class="col-sm-10">
                                <input type="file" class="btn btn-primary" id="doc" name="doc"/>
                            </div>
                        </div>
<br>
                        <div class="row">
                            <label class="col-sm-2 control-label">Description</label>

                            <div class="col-sm-9">
                                <g:textArea name="description" class="form-control" rows="5" id="comment"/>
                            </div>
                            <br>

                            <div class="row">
                                <label class="col-sm-2 control-label">Topic</label>

                                <div class="col-sm-9">

                                    <ls:showTopicList/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">


                    <g:submitButton name="save" formaction="/DocumentResource/save" value="Share" type="submit"
                                    class="btn btn-primary btn-block"/>
                    </div>
                </g:uploadForm>
            </div>


        </div>
    </div>
</div>