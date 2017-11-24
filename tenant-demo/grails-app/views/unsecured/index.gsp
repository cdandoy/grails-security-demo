<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="app"/>
</head>

<body>
<h1>@Secured('permitAll')</h1>
<g:include controller="secInfo"/>
</body>
</html>