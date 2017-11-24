<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="app"/>
</head>

<body>
<h4>Orders</h4>
<table class="table table-hover">
    <thead>
    <tr>
        <th>Order</th>
        <th>Customer</th>
        <th>Ordered</th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${orders}" var="order">
        <tr>
            <td><g:link action="detail" id="${order.id}">#${order.id}</g:link></td>
            <td>${order.customer}</td>
            <td><g:formatDate date="${order.dateCreated}" type="date"/></td>
        </tr>
    </g:each>
    </tbody>
</table>
</body>