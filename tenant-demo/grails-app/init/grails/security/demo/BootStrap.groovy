package grails.security.demo

import org.dandoy.*

import static grails.gorm.multitenancy.Tenants.withId

class BootStrap {
    static final orders = [
            [
                    customer    : 'Customer1',
                    orderDetails: [
                            [
                                    product : 'Product 1',
                                    quantity: 1
                            ],
                            [
                                    product : 'Product 2',
                                    quantity: 2
                            ],
                    ]
            ],
            [
                    customer    : 'Customer2',
                    orderDetails: [
                            [
                                    product : 'Product 3',
                                    quantity: 1
                            ],
                            [
                                    product : 'Product 4',
                                    quantity: 2
                            ],
                    ]
            ],
    ]

    def init = { servletContext ->
        withId(null) {
            def adminRole = new Role(authority: 'ROLE_ADMIN').save()
            createAccount('Account 1', 'localhost', adminRole, 'me', 'password', orders)
            createAccount('Account 2', '127.0.0.1', adminRole, 'me', 'secret', orders)
        }
/*
        UserRole.withSession {
            it.flush()
            it.clear()
        }
*/

/*
        createOrders(account1, [
                [
                        customer    : 'Customer1',
                        orderDetails: [
                                [
                                        product : 'Product 1',
                                        quantity: 1
                                ],
                                [
                                        product : 'Product 2',
                                        quantity: 2
                                ],
                        ]
                ],
                [
                        customer    : 'Customer2',
                        orderDetails: [
                                [
                                        product : 'Product 3',
                                        quantity: 1
                                ],
                                [
                                        product : 'Product 4',
                                        quantity: 2
                                ],
                        ]
                ],
        ])
*/
    }

    def destroy = {
    }

    private static void createAccount(String name, String host, Role role, String username, String password, orderInfos) {
        def account = withId(null) {
            def account = new Account(name: name, host: host).save(flush: true)
            def user = new User(host: host, account: account, username: username, password: password).save()
            UserRole.create(user, role)
            return account
        }
        withId(account.id) {
            Account.withSession { session ->
                orderInfos.each { orderInfo ->
                    def order = new Order(account: account, customer: orderInfo.customer)
                    orderInfo.orderDetails.each {
                        order.addToOrderDetails(new OrderDetail(it))
                    }
                    order.save(failOnError: true)
                }
                session.flush()
                session.clear()
            }
        }
    }
}
