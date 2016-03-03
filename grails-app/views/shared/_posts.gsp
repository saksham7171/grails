<div class="row">
    <div class="col-xs-2">
        <div class="glyphicon glyphicon-user" style="font-size:70px"></div>
    </div>

    <div class="col-xs-10">
        ${post.createdBy.username}<p class="text-muted inline">@${post.createdBy.username} 5min</p>
        <a href="" class="inline" style="float:right">Grails</a>
        <g:link controller="topic" action="show" params="[topicId:post.topicId,visibility:post.topic.visibility.toString()]">${post.topic}</g:link>

        <p>${post.description}</p>
        <a href="#"><div class="fa fa-facebook-official"></div></a>
        <a href="#"><div class="fa fa-twitter inline"></div></a>
        <a href="#"><div class="fa fa-google-plus inline"></div></a>
        <a href="#" class="inline" style="float:right"><u>View Post</u></a>
        <a href="#" class="inline" style="float:right"><u>Mark as read</u></a>
        <a href="#" class="inline" style="float:right"><u>view full site</u></a>
        <a href="#" class="inline" style="float:right"><u>Download</u></a>
    </div>
</div>