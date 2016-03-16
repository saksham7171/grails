<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title></title>
</head>

<body>
<div class="row">
    <div class="col-xs-5">
        <div class="panel panel-default ">
            <div class="panel-heading">
                Topic : ${topic.name}
            </div>

            <div class="panel-body">
                <g:render template="/user/trendingTopics" model="[topic: topic]"/>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading">
                Users : ${topic.name}
            </div>

            <div class="panel-body" style="overflow-y:auto;height: 400px">
                <g:each in="${users}" var="user">
                    <g:render template="/user/user" model="[user: user]"/>
                </g:each>
            </div>
        </div>
    </div>

    <div class="col-xs-7 col">
        <div class="panel panel-default">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-md-1">
                        Posts
                    </div>

                    <div class="col-md-offset-4 col-md-6" style="text-align:left">
                        <g:form action="show" controller="topic" class="search-form">
                            <div class="form-inline">
                                <g:textField name="q" class="form-control"
                                             placeholder="Description String"/>
                                <g:hiddenField name="topicId" value="${topic.id}"/>
                                <g:submitButton name="submit" value="find" formaction="/topic/show"
                                                type="submit" class="btn btn-default">Find</g:submitButton>
                            </div>

                        </g:form>
                    </div>
                </div>
            </div>

            <div class="panel-body" style="overflow-y: auto;height: 620px">
                <g:render template="topicPosts" model="[resources: resources]"/>
            </div>

            <div class="pagination">
                <g:paginate total="${total}" controller="topic" action="show" prev="prev" next="next" max="${co.max}"
                            offset="${co.offset}" params="[topicId:topic.id]"/>
            </div>
        </div>

    </div>
</div>

</body>
</html>