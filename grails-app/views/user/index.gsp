<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title></title>
</head>

<body>
<div>


</div>
<div class="row">
    <div class="col-xs-5">
        <g:render template="user" model="[user:user]"></g:render>

        <div class="panel panel-default">
            <div class="panel-heading">
                Subscriptions <a href="#" class="inline" style="float:right">view all</a>
            </div>

            <div class="panel-body" style="overflow-y:auto;height: 250px">
                <g:each in="${subList}" var="topic">
                    <g:render template="show" model="[topic: topic]"/>
                </g:each>
            </div>
        </div>

        <div class="panel panel-default ">
            <div class="panel-heading">
                Trending Topics
            </div>

            <div class="panel-body" style="overflow-y:auto;height: 250px">
                <g:each in="${topics}" var="topic">
                    <g:render template="trendingTopics" model="[topic: topic]"/>
                </g:each>
            </div>
        </div>
    </div>

    <div class="col-xs-7 col">
        <div class="panel panel-default">
            <div class="panel-heading">
                Inbox
            </div>

            <div class="panel-body" style="overflow-y: auto;height: 740px">
                <g:each in="${items}" var="item">
                    <g:render template="inbox" model="[item: item]"/>
                </g:each>
            </div>
        </div>

    </div>
</div>
</body>
</html>