package org.dandoy

import grails.gorm.MultiTenant

class Order implements MultiTenant<Order> {
    static belongsTo = [account: Account]
    Long tenantId
    String customer
    Date dateCreated
    Date lastUpdated

    static hasMany = [
            orderDetails: OrderDetail
    ]

    void setAccount(Account account) {
        this.account = account
        this.tenantId = account.id
    }

    static constraints = {
    }

    static mapping = {
        table 'orders'
    }
}
