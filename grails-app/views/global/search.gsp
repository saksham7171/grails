<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title></title>
</head>

<body>
<div class="row">
    <div class="col-xs-5">
        <div class="panel panel-default">
            <div class="panel-heading">
                Trending Topics <a href="#" class="inline" style="float:right">view all</a>
            </div>

            <div class="panel-body" style="overflow-y:auto;height: 350px">
                <g:each in="${com.ttnd.linksharing.Topic.getTrendingTopics()}" var="topic">
                    <g:render template="/user/trendingTopics" model="[topic: topic]"/>
                </g:each>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading">
                Top Posts <a href="#" class="inline" style="float:right">view all</a>
            </div>

            <div class="panel-body" style="overflow-y:auto;height: 350px">
                <g:each in="${com.ttnd.linksharing.Resource.getTopPost()}" var="topic">
                    <g:render template="/shared/posts" model="[post: topic]"/>
                </g:each>
            </div>
        </div>
    </div>

    <div class="col-xs-7 col">
        <div class="panel panel-default">
            <div class="panel-heading">
                Posts
            </div>

            <div class="panel-body" style="height: 700px">
                <g:render template="/topic/topicPosts" model="[resources: resources]"/>
                <div class="pagination">
                    <g:paginate next="Next" prev="Prev" total="${total}" maxsteps="${resources.size()}"
                                controller="login" action="globalSearch"
                                max="${co.max}" offset="${co.offset}"/>
                </div>
            </div>
        </div>

    </div>
</div>
</body>
</html>