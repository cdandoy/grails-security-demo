<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
</head>

<body>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <sec:ifLoggedIn>
                    <li class="navbar-brand">
                        <sec:loggedInUserInfo field='username'/>
                        @
                        <sec:loggedInUserInfo field='host'/>
                    </li>
                </sec:ifLoggedIn>
                <li class="${params.controller == 'unsecured' ? 'active' : ''}">
                    <g:link controller="unsecured">
                        Unsecured
                    </g:link>
                </li>
                <li class="${params.controller == 'secured' ? 'active' : ''}">
                    <g:link controller="secured">
                        Secured
                    </g:link>
                </li>
            </ul>

            <sec:ifLoggedIn>
                <g:form controller="logout" class="nav navbar-nav navbar-right">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <button type="submit" id="button-logout" class="btn btn-primary">Logout</button>
                        </li>
                    </ul>
                </g:form>
            </sec:ifLoggedIn>
        </div><!--/.nav-collapse -->
    </div>
</nav>

<div class="container">
    <g:layoutBody/>
</div>
<asset:javascript src="application.js"/>

</body>
</html>
