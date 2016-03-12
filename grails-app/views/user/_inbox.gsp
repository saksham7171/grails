<div class="row">
    <div class="col-xs-2">
        <ls:userImage userId="${item.resource.createdBy.id}"/>
    </div>

    <div class="col-xs-10">
        ${item.resource.createdBy}<p class="text-muted inline">@${item.resource.createdBy} 5min</p>
        <g:link class="pull-right" name="topicClickLnk" controller="topic" action="show"
                params='[topicId: item.resource.topic.id]'>${item.resource.topic.name}</g:link>
        <p>${item.resource.description}</p>
        <a href="#"><div class="fa fa-facebook-official"></div></a>
        <a href="#"><div class="fa fa-twitter inline"></div></a>
        <a href="#"><div class="fa fa-google-plus inline"></div></a>
        <a href="/resource/show/${item.resource.id}" class="inline" style="float:right"><u>View Post</u></a>
        <ls:isRead id="${item.resource.id}"><u>&nbspMark as read</u></ls:isRead>
        <ls:resourceType id="${item.resource.id}" url="${item.resource}" />
    </div>
</div>
<hr>