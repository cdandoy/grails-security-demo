package org.dandoy

class Account {
    String name
    String host
    static hasMany = [orders: Order, users: User]

    static constraints = {
    }
    static mapping = {
        table 'accounts'
    }
}
