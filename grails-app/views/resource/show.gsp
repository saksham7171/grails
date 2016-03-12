<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
  <title></title>
</head>
<body>
<div class="row">
    <div class="col-md-7">
        <div class="panel panel-default">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-md-3">
                        <ls:userImage userId="${resource.createdBy.id}"/>
                    </div>

<div class="col-md-9">
    <div class="row">
        <div class="col-md-6">
            <span class="text-primary">${resource.createdBy.getName()}</span>
        </div>

<div class="col-md-4 col-md-offset-1">
    <g:link controller="topic" action="show" style="float:right" params="[topicId:resource.topicId,visibility:resource.topic.visibility.toString()]">${resource.topic}</g:link>
</div>
</div>

<div class="row">
    <div class="col-md-6">
        <span class="text-muted">@${resource.createdBy.username}</span>
    </div>
    <div class="col-md-6 ">
        <span class="text-muted">${resource.dateCreated}</span>
    </div>
</div>
<g:if test="${session.user}">
    <div class="row">
        <div class="col-md-10 col-md-offset-7">
            <g:form controller="resourceRating" action="save" params="[id:resource.id]">
                <g:select name="score" from="${1..5}" optionKey="${it}"
                         value="${session.user.getScore(resource)}"/>
                <g:submitButton name="saveResourceScoreBtn" class="btn btn-default btn-primary"
                                value="Save" type="submit"/>
            </g:form>
        </div>
    </div>
</g:if>
</div>
</div>
</div>

<div class="panel-body">
    <span class="text-justify">${resource.description}</span>
</div>

<div class="panel-footer">
    <div class="row">
        <div class="col-md-3 col-xs-12">
            <div class="row">
                <div class="col-md-4 col-xs-4">
                    <a href="#"><span class="fa fa-facebook-official"
                                      style="font-size: large"></span></a>
                </div>

                <div class="col-md-4 col-xs-4">
                    <a href="#"><span class="fa fa-twitter-square" style="font-size: large"></span></a>
                </div>

                <div class="col-md-4 col-xs-4">
                    <a href="#"><span class="fa fa-google-plus" style="font-size: large"></span></a>
                </div>
            </div>
        </div>



        <div class="col-md-1 ">
            <a href="#">
                <ins>Edit</ins>
            </a>
        </div>

        <div class="col-md-2 ">
            <ins><ls:resourceType id="${resource.id}" url="${resource}" /></ins>
        </div>
        <div class="col-md-2 ">
            <ins><ls:canDeleteResource user="${session.user}" id="${resource.id}"></ls:canDeleteResource></ins>
        </div>
    </div>
</div>
</div>
</div>

<div class="col-md-5">
    <div class="panel panel-default ">
        <div class="panel-heading">
            Trending Topics
        </div>

        <div class="panel-body" style="overflow-y: auto;height: 500px">
            <g:each in="${topics}" var="topic">
                <g:render template="/user/trendingTopics" model="[topic: topic]"/>
            </g:each>
        </div>
    </div>
</div>
</body>

</body>
</html>