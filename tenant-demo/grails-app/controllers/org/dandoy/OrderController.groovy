package org.dandoy

import grails.plugin.springsecurity.annotation.Secured

@Secured('ROLE_ADMIN')
class OrderController {

    def index() {
        [orders: Order.list()]
    }

    def detail(long id) {
        [order: Order.get(id)]
    }
}
