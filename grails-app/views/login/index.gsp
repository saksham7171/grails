<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title></title>
</head>

<body>
<div class="row">
    <div class="col-xs-6">
        <div class="panel panel-default">
            <div class="panel-heading">
                Recent Shares
            </div>

            <div class="panel-body">
                <div class="row">
                    <div class="col-xs-2">
                        <div class="glyphicon glyphicon-user" style="font-size:70px"></div>
                    </div>

                    <div class="col-xs-10">
                        uday Pratap Singh<p class="text-muted inline">@uday 5min</p>
                        <a href="#" class="inline" style="float:right">Grails</a>

                        <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta                             felis
                        </p>
                        <a href="#"><div class="fa fa-facebook-official"></div></a>
                        <a href="#"><div class="fa fa-twitter inline"></div></a>
                        <a href="#"><div class="fa fa-google-plus inline"></div></a>
                        <a href="#" class="inline" style="float:right"><u>View Post</u></a>
                    </div>
                </div>
                <br>

                <div class="row">
                    <div class="col-xs-2">
                        <div class="glyphicon glyphicon-user" style="font-size:70px"></div>
                    </div>

                    <div class="col-xs-10">
                        uday Pratap Singh<p class="text-muted inline">@uday 5min</p>
                        <a href="#" class="inline" style="float:right">Grails</a>

                        <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta                             felis
                        </p>
                        <a href="#"><div class="fa fa-facebook-official"></div></a>
                        <a href="#"><div class="fa fa-twitter inline"></div></a>
                        <a href="#"><div class="fa fa-google-plus inline"></div></a>
                        <a href="#" class="inline" style="float:right"><u>View Post</u></a>
                    </div>
                </div>
            </div>

        </div>
    </div>

    <div class="col-xs-5 col-xs-offset-1">
        <g:render template="login"/>
    </div>

    <div class="row">
        <div class="col-xs-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Top Posts
                    <div class="dropdown" style="float:right">
                        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1"
                                data-toggle="dropdown" aria- haspopup="true" aria-expanded="true">
                            Today
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            <li><a href="#">Today</a></li>
                            <li><a href="#">1 Week</a></li>
                            <li><a href="#">1 month</a></li>
                            <li><a href="#">1 year</a></li>
                        </ul>
                    </div>
                </div>

                <div class="panel-body">
                    <div class="row">
                        <div class="col-xs-2">
                            <div class="glyphicon glyphicon-user" style="font-size:70px"></div>
                        </div>

                        <div class="col-xs-10">
                            uday Pratap Singh<p class="text-muted inline">@uday 5min</p>
                            <a href="#" class="inline" style="float:right">Grails</a>

                            <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta                             felis
                            </p>
                            <a href="#"><div class="fa fa-facebook-official"></div></a>
                            <a href="#"><div class="fa fa-twitter inline"></div></a>
                            <a href="#"><div class="fa fa-google-plus inline"></div></a>
                            <a href="#" class="inline" style="float:right"><u>View Post</u></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-xs-5 col-xs-offset-1">
        <g:render template="register" />
        </div>

    </div>

</div>
</body>
</html>