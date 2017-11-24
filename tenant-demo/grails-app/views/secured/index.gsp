<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="app"/>
</head>

<body>
<h1>@Secured('ROLE_ADMIN')</h1>
<g:include controller="secInfo"/>
<h4>Orders</h4>
<table class="table table-hover">
    <thead>
    <tr>
        <th>#</th>
        <th>Customer</th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${orders}" var="order">
        <tr>
            <td>${order.id}</td>
            <td>${order.customer}</td>
        </tr>
    </g:each>
    </tbody>
</table>
</body>
</html>