<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title></title>
</head>

<body>
    <div class="col-xs-6">
        <div class="panel panel-default">
            <div class="panel-heading">
                Recent Shares
            </div>
            <div class="panel-body" style="overflow-y:auto;height: 320px">
            <g:each in="${recentShares}" var="recentShare">
                <g:render template="/shared/posts" model="[post:recentShare]"/>
                <hr>
            </g:each>
            </div>
        </div>

            <div class="panel panel-default">
                <div class="panel-heading">
                    Top Posts
                </div>
                <div class="panel-body" style="overflow-y:auto;height: 320px">
                    <g:each in="${topPosts}" var="topPost">
                        <g:render template="/shared/posts" model="[post:topPost]"/>
                        <hr>
                    </g:each>
                </div>
            </div>
        </div>


    <div class="col-xs-5 col-xs-offset-1">
        <g:render template="/login/login"/>
        <g:render template="/login/register"/>
        %{--<g:renderErrors bean="${user}"/>--}%
    </div>






</body>
</html>