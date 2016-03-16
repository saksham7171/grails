<div class="topicBox">
    <div class="row">
        <div class="col-md-3">
            <ls:userImage userId="${topic.createdBy.id}"/>
        </div>

        <div class="col-md-9">
            <div class="row">
                <div class="col-md-6 col-xs-6">
                    <span class="text-primary">
                        <a href="${createLink(controller: "user", action: "profile", params: [userId: topic.createdBy.id])}">${topic.createdBy.getName()}</a>
                    </span>
                </div>

                <div style="display: none" class="edit">

                    <g:textField name="name" value="${topic.name}" class="editTextBox"/>
                    <g:hiddenField name="topic.id" value="${topic.id}" class="editTopicId"/>
                    <g:submitButton value="submit" type="submit" name="save${topic.id}" id="saveSub"
                                    class="btn btn-sm btn-primary editTitleSave">Save</g:submitButton>
                    <button  value="cancel" type="submit"
                             class="btn btn-sm btn-primary editTitleCancel">Cancel</button>

                </div>

                <div class="col-md-4 col-md-offset-2 col-xs-6 " id="orig${topic.id}">
                    <span class="text-primary tc" >
                        <ins><g:link name="topicClickLnk" controller="topic" action="show"
                                     params='[topicId: topic.id]'>${topic.name}</g:link></ins></span>
                </div>
            </div>

            <div class="row">
                <div class="col-md-4">
                    <span class="text-muted">@${topic.createdBy}</span><br/>
                    <g:unless test="${session.user.id==topic.createdBy.id}">
                        <ls:showSubscribe userId="${session.user?.id}" topicId="${topic.id}"/>
                    </g:unless>
                </div>

                <div class="col-md-4 col-xs-6">
                    <span class="text-muted">Subscriptions</span><br/>
                    <span class="text-primary"><ls:subscriptionCount topicId="${topic.id}"/></span>
                </div>

                <div class="col-md-4 col-xs-6">
                    <span class="text-muted">Posts</span><br/>
                    <span class="text-primary"><ls:resourcecCount topicId="${topic.id}"/></span>
                </div>
            </div>
        </div>
    </div>
    <g:if test="${session.user}">
        <div class="footer">
            <div class="row">

                <ls:canUpdateTopic userId="${session.user.id}" topicId="${topic.id}">
                    <div class="col-md-4">
                        <div class="dropdown">
                            <ls:showSeriousness topicId="${topic.id}"/>
                        </div>
                    </div>

                    <div class="col-md-4">
                        <div class="dropdown">
                            <ls:showVisibility topicId="${topic.id}"/>
                        </div>
                    </div>

                    <div class="col-md-4">
                        <a href="#"><span class="glyphicon glyphicon-envelope"
                                          style="font-size:20px"></span></a>
                        <span class="fa fa-pencil-square-o editButton" style="font-size: 18px;color:blue"
                              id="subEdit"></span>
                        <a href="${createLink(controller: 'topic', action: 'delete', params: [id: topic.id])}"><span
                                class="fa fa-trash" style="font-size:20px"></span></a>
                    </div>
                </ls:canUpdateTopic>
            </div>
        </div>

    </g:if>
</div>
<hr>

