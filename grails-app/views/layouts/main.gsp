<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="Grails"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <asset:stylesheet src="bootstrap.css"/>
    <asset:stylesheet src="font-awesome.min.css"/>
  %{--  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
 --}%
    <asset:javascript src="jquery-2.2.1.min.js"/>
    <asset:javascript src="bootstrap.min.js"/>
    <asset:javascript src="application.js"/>
    <asset:javascript src="jquery.validate.min.js"/>
    <asset:javascript src="additional-methods.min.js"/>
    <g:layoutHead/>
</head>

<body>

<div class="wrapper">
    <div class="container">
        <nav class="navbar navbar-default">
            <div class="navbar-header">
                <a class="navbar-brand navbar-left" href="/">
                    Link Sharing
                </a>
            </div>

            <form class="navbar-form pull-right">
                <g:if test="${session.user}">
                <a href="#"><span class="glyphicon glyphicon-search"></span></a>
                <input type="text" class="form-control" placeholder="Search">
                <a href="#topic" data-toggle="modal"><div class="glyphicon glyphicon-comment inline"></div></a>
                <a href="#invitation" data-toggle="modal"><div class="glyphicon glyphicon-envelope inline"></div></a>
                <a href="#link" data-toggle="modal"><div class="fa fa-chain inline"></div></a>
                <a href="#document" data-toggle="modal"><div class="fa fa-file inline"></div></a>

                <div class="dropdown inline">
                    <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1"
                            data-toggle="dropdown" aria- haspopup="true" aria-expanded="true">
                        <a href="#"><div class="glyphicon glyphicon-user"></div></a>
                        ${session.user} <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                        <li><a href="#">Profile</a></li>
                        <li><a href="#">Users</a></li>
                        <li><a href="/login/logout">Logout</a></li>
                    </ul>
                </div>
                </g:if>
            </form>
        </nav>
        <g:if test="${session.user}">
            <g:render template="/topic/create"/>
            <g:render template="/resource/createdoc"/>
            <g:render template="/resource/createlink"/>
            <g:render template="/topic/email"/>
        </g:if>


        <g:if test="${flash.message}">
            <div class="alert alert-success alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                ${flash.message}
            </div>

        </g:if>



        <g:if test="${flash.error}">
            <div class="alert alert-danger alert-dismissible" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                ${flash.error}
            </div>

        </g:if>
            <div class="json" role="alert" style="display: none">
            </div>
        <g:layoutBody/>
    </div>
</div>

</body>
</html>
