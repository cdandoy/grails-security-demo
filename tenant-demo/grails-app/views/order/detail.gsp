<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="app"/>
</head>

<body>
<ol class="breadcrumb">
    <li><g:link action="index">Orders</g:link> </li>
    <li class="active">Order #${order.id}</li>
</ol>

<h4>Order #${order.id} <small>- ${order.customer}</small></h4>
<table class="table table-hover">
    <thead>
    <tr>
        <th>Product</th>
        <th>Quantity</th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${order.orderDetails.sort{it.id}}" var="orderDetail">
        <tr>
            <td>${orderDetail.product}</td>
            <td>${orderDetail.quantity}</td>
        </tr>
    </g:each>
    </tbody>
</table>
</body>