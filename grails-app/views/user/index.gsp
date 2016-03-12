
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <title></title>
</head>
<body>
<div class="row">
    <div class="col-xs-4" >
        <div class="well">
            <div class="row">
                <div class="col-xs-4">
                    <div class="glyphicon glyphicon-user" style="font-size:70px"></div>
                </div>
                <div class="col-xs-8">
                    <div class="h4">${session.user}</div>
                    <p class="text-muted "> @${session.user} 5min</p>
                    <p class="text-muted inline">Subscriptions</p>
                    <p class="text-muted inline" style="float:right">Topics</p>
                    <br>
                    <p class="text-info inline">50</p>
                    <p class="text-info inline" style="float:right">30</p>

                </div>
            </div>

        </div>
    </div>

    <div class="col-xs-7 col-xs-offset-1 col">
        <div class="panel panel-default">
            <div class="panel-heading">
                Inbox
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-xs-2">
                        <div class="glyphicon glyphicon-user" style="font-size:70px"></div>
                    </div>
                    <div class="col-xs-10">
                        uday Pratap Singh<p class="text-muted inline"> @uday 5min</p>
                        <a href="#" class="inline" style="float:right">Grails</a>
                        <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta 				    		 felis
                        </p>
                        <a href="#"><div class="fa fa-facebook-official"></div></a>
                        <a href="#"><div class="fa fa-twitter inline"></div></a>
                        <a href="#"><div class="fa fa-google-plus inline"></div></a>
                        <a href="#" class="inline" style="float:right"><u>View Post</u></a>
                        <a href="#" class="inline" style="float:right"><u>Mark as read</u></a>
                        <a href="#" class="inline" style="float:right"><u>view full site</u></a>
                        <a href="#" class="inline" style="float:right"><u>Download</u></a>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-xs-2">
                        <div class="glyphicon glyphicon-user" style="font-size:70px"></div>
                    </div>
                    <div class="col-xs-10">
                        uday Pratap Singh<p class="text-muted inline"> @uday 5min</p>
                        <a href="#" class="inline" style="float:right">Grails</a>
                        <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta 				    		 felis
                        </p>
                        <a href="#"><div class="fa fa-facebook-official"></div></a>
                        <a href="#"><div class="fa fa-twitter inline"></div></a>
                        <a href="#"><div class="fa fa-google-plus inline"></div></a>
                        <a href="#" class="inline" style="float:right"><u>View Post</u></a>
                        <a href="#" class="inline" style="float:right"><u>Mark as read</u></a>
                        <a href="#" class="inline" style="float:right"><u>view full site</u></a>
                        <a href="#" class="inline" style="float:right"><u>Download</u></a>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<div class="row">
    <div class="col-xs-4">
        <div class="panel panel-default">
            <div class="panel-heading">
                Subscriptions <a href="#" class="inline" style="float:right">view all</a>
            </div>
            <div class="panel-body">
                <div class="row">
                    <div class="col-xs-2">
                        <div class="glyphicon glyphicon-user" style="font-size:50px"></div>
                    </div>
                    <div class="col-xs-9 col-xs-offset-1">
                        <a href="#">Grails</a>
                        <br>
                        <p class="text-muted newinline">@Uday</p>
                        <p class="text-muted newinline">Subscriptions</p>
                        <p class="text-muted newinline" style="float:right">Topics</p>
                        <br>
                        <a href="#" class="newinline">Unsubscribe</a>
                        <p class="text-info newinline">50</p>
                        <p class="text-info newinline" style="float:right">30</p>
                    </div>
                    <a href="#"><div class="glyphicon glyphicon-trash inline" style="float:right;margin-top:12px"></div></a>
                    <a href="#"><div class="glyphicon glyphicon-pencil inline" style="float:right;margin-top:12px"></div></a>
                    <a href="#"><div class="glyphicon glyphicon-envelope inline" style="float:right;margin-top:12px"></div></a>
                    <div class="dropdown inline" style="float:right;margin-top:5px">
                        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-  					haspopup="true" aria-expanded="true">
                            Private
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            <li><a href="#">Private</a></li>
                            <li><a href="#">Public</a></li>

                        </ul>
                    </div>
                    <div class="dropdown inline" style="float:right;margin-top:5px">
                        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-  					haspopup="true" aria-expanded="true">
                            Serious
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            <li><a href="#">Serious</a></li>
                            <li><a href="#">Serious1</a></li>

                        </ul>
                    </div>
                </div>
                <hr>
                <div class="row">
                    <div class="col-xs-2">
                        <div class="glyphicon glyphicon-user" style="font-size:50px"></div>
                    </div>
                    <div class="col-xs-9 col-xs-offset-1">
                        <a href="#">Grails</a>
                        <br>
                        <p class="text-muted newinline">@Uday</p>
                        <p class="text-muted newinline">Subscriptions</p>
                        <p class="text-muted newinline" style="float:right">Topics</p>
                        <br>
                        <a href="#" class="newinline">Unsubscribe</a>
                        <p class="text-info newinline">50</p>
                        <p class="text-info newinline" style="float:right">30</p>
                    </div>

                    <a href="#"><div class="glyphicon glyphicon-envelope inline" style="float:right;margin-top:12px"></div></a>

                    <div class="dropdown inline" style="float:right;margin-top:5px">
                        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-  					haspopup="true" aria-expanded="true">
                            Serious
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            <li><a href="#">Serious</a></li>
                            <li><a href="#">Serious1</a></li>

                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<g:render template="trendingTopics"/>
</body>
</html>