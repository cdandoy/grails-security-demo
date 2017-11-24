package org.dandoy

class OrderDetail {
    String product
    int quantity

    static belongsTo = [order:Order]
    static constraints = {
    }
    static mapping = {
        table 'order_details'
    }
}
