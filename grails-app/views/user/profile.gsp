
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title></title>
</head>

<body>
<div class="row">
    <div class="col-xs-5">
        <g:render template="user" model="[user: user]"></g:render>
        <g:hiddenField name="id" id="id" value="${user.id}"/>

        <div class="panel panel-default">
            <div class="panel-heading">
                Topics <a href="#" class="inline" style="float:right">view all</a>
            </div>

            <div class="panel-body"  style="overflow-y:auto;height: 250px">
            <div id="createdTopics">

            </div>
            </div>
        </div>

        <div class="panel panel-default ">
            <div class="panel-heading">
                Subscriptions
            </div>

            <div class="panel-body" style="overflow-y:auto;height: 250px">
                <div id="subscribedTopics">
                </div>
            </div>
        </div>
    </div>

    <div class="col-xs-7 col">
        <div class="panel panel-default">
            <div class="panel-heading">
                Posts
            </div>

            <div class="panel-body" style="overflow-y: auto;height: 740px">
                    <g:render template="/topic/topicPosts" model="[resources: resources]"/>
            </div>
        </div>

    </div>
</div>
<g:javascript>
$(document).ready(function () {
    $.ajax({
        url:"/user/topics",
        data:{id:$("#id").val()},
        success:function(result){
            $('#createdTopics').html(result)
        }
    });
    $.ajax({
        url:"/user/subscriptions",
        data:{id:$("#id").val()},
        success:function(result){
            $('#subscribedTopics').html(result)
        }
    });
});
</g:javascript>
</body>
</html>