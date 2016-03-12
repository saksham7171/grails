<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title></title>
</head>

<body>
<div class="row">
    <div class="col-xs-5">
        <div class="panel panel-default panel-primary ">
            <div class="panel-heading">
                Topic : ${topic.name}
            </div>

            <div class="panel-body" ">
                    <g:render template="/user/trendingTopics" model="[topic:topic]"/>
            </div>
        </div>

        <div class="panel panel-default panel-primary">
            <div class="panel-heading">
                Users : ${topic.name}
            </div>

            <div class="panel-body" style="overflow-y:auto;height: 400px">
                <g:each in="${users}" var="user">
                    <g:render template="/user/user" model="[user:user]"/>
                </g:each>
            </div>
        </div>
    </div>

    <div class="col-xs-7 col">
        <div class="panel panel-default panel-primary">
            <div class="panel-heading">
                Posts : ${topic.name}
            </div>

            <div class="panel-body" style="overflow-y: auto;height: 620px">
                <g:each in="${topic.resources}" var="resource">
                    <g:render template="topicPosts" model="[resource: resource]"/>
                </g:each>
            </div>
        </div>

    </div>
</div>

</body>
</html>