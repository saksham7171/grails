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
                Topics <a href="#" class="inline" style="float:right">view all</a>
            </div>

            <div class="panel-body" style="overflow-y:auto;height: 750px">
                <g:each in="${topics}" var="topic">
                    <g:render template="/user/show" model="[topic: topic]"/>
                </g:each>
            </div>
        </div>
    </div>

    <div class="col-xs-7 col">
        <div class="panel panel-default">
            <div class="panel-heading">
                Posts
            </div>

            <div class="panel-body" style="overflow-y: auto;height: 740px">
                    <g:render template="/topic/topicPosts" model="[resources:resources]"/>
            </div>
        </div>

    </div>
</div>
</body>
</html>