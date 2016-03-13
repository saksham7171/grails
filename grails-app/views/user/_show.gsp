<div class="row">
    <div class="col-md-3">
        <ls:userImage userId="${topic.createdBy.id}"/>
    </div>

    <div class="col-md-9">
        <div class="row">
            <div class="col-md-6 col-xs-6">
                <span class="text-primary">
                    <a href="${createLink(controller: "user",action: "profile", params: [userId: topic.createdBy.id])}">${topic.createdBy.getName()}</a>
                </span>
            </div>

            <div class="col-md-4 col-md-offset-2 col-xs-6">
                <span class="text-primary">
                    <ins><g:link name="topicClickLnk" controller="topic" action="show"
                                 params='[topicId: topic.id]'>${topic.name}</g:link></ins></span>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4">
                <span class="text-muted">@${topic.createdBy}</span><br/>
                <ls:showSubscribe userId="${session.user?.id}" topicId="${topic.id}"/>
            </div>

            <div class="col-md-4 col-xs-6">
                <span class="text-muted">Subscriptions</span><br/>
                <span class="text-primary"><ls:subscriptionCount topicId="${topic.id}"></ls:subscriptionCount> </span>
            </div>

            <div class="col-md-4 col-xs-6">
                <span class="text-muted">Posts</span><br/>
                <span class="text-primary"><ls:resourcecCount topicId="${topic.id}"></ls:resourcecCount> </span>
            </div>
        </div>
    </div>
</div>
<div class="footer">
    <div class="row">
        <div class="col-md-4">
            <div class="dropdown">
                <ls:showSeriousness name="seriousness"  topicId="${topic.id}"/>
            </div>
        </div>
        <ls:canUpdateTopic userId="${session.user.id}" topicId="${topic.id}">
        <div class="col-md-4">
            <div class="dropdown">
                <ls:showVisibility topicId="${topic.id}"/>
            </div>
        </div>

        <div class="col-md-4">
            <a href="#"><span class="glyphicon glyphicon-envelope"
                              style="font-size:20px"></span></a>
            <a href="#"><span class="fa fa-file-o" style="font-size:20px"></span></a>
            <a href="${createLink(controller: 'topic',action: 'delete',params: [id:topic.id])}"><span class="fa fa-trash" style="font-size:20px"></span></a>
        </div>
        </ls:canUpdateTopic>
    </div>
</div>
<hr>

