<g:each in="${resources}" var="resource">
<div class="row">
    <div class="col-xs-2">
        <ls:userImage userId="${resource.createdBy.id}"/>
    </div>

    <div class="col-xs-10">

        <p>${resource.description}</p>
        <a href="#"><div class="fa fa-facebook-official"></div></a>
        <a href="#"><div class="fa fa-twitter inline"></div></a>
        <a href="#"><div class="fa fa-google-plus inline"></div></a>
        <a href="/resource/show/${resource.id}" class="inline" style="float:right"><u>View Post</u></a>
        <ls:isRead id="${resource.id}"><u>&nbspMark as read</u></ls:isRead>
        <ls:resourceType id="${resource.id}" url="${resource}" />
    </div>
</div>
<hr>
</g:each>