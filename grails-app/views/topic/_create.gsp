<%@ page import="com.ttnd.linksharing.Visibility" %>
<div class="modal fade" id="topic" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">Create Topic</h4>
            </div>
            <div class="modal-body">
                <g:form class="form-horizontal" controller="topic" action="save">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Name</label>
                        <div class="col-sm-10">
                            <g:textField name="name" class="form-control" id="name" placeholder="name" />
                        </div>
                        <label class="col-sm-2 control-label">Visibility</label>
                        <div class="col-sm-10">
                            <div class="dropdown">
                                <g:select class="dropdown-toggle btn" name="visibility" from="${com.ttnd.linksharing.Visibility.values()}"/>
                            </div>
                        </div>
                    </div>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <g:submitButton formaction="/topic/save" type="submit" name="submit" value="save" class="btn btn-primary"/>

                </g:form>
            </div>
            <div class="modal-footer">

            </div>
        </div>
    </div>
</div>