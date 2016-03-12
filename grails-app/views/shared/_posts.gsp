<div class="row">
    <div class="col-xs-2">
        <ls:userImage userId="${post.createdBy.id}"/>
    </div>

    <div class="col-xs-10">
        <a href="${createLink(controller: "user",action: "profile", params: [userId: post.topic.createdBy.id])}">${post.topic.createdBy.getName()}</a>
        <p class="text-muted inline">@${post.createdBy.username} 5min</p>

        <g:link controller="topic" action="show" style="float:right" params="[topicId:post.topicId,visibility:post.topic.visibility.toString()]">${post.topic}</g:link>

        <p>${post.description}</p>
        <a href="#"><div class="fa fa-facebook-official"></div></a>
        <a href="#"><div class="fa fa-twitter inline"></div></a>
        <a href="#"><div class="fa fa-google-plus inline"></div></a>
        <a href="/resource/show/${post.id}" class="inline" style="float:right"><u>View Post</u></a>
        <ls:resourceType id="${post.id}" url="${post}" />
    </div>
</div>