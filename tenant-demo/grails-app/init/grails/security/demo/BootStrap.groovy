package grails.security.demo

import grails.gorm.multitenancy.Tenants
import groovy.json.JsonSlurper
import org.dandoy.*

class BootStrap {
    def init = { servletContext ->
        Tenants.withoutId {
            def adminRole = new Role(authority: 'ROLE_ADMIN').save()
            BootStrap.getResourceAsStream("/dataset.json").withCloseable {
                Map json = (Map) new JsonSlurper().parse(it)
                json.accounts.each { accountData ->
                    def account = new Account(name: accountData.name, host: accountData.host).save(flush: true)
                    Tenants.withId(account.id) {
                        Account.withSession { session ->
                            accountData.users.each { userData ->
                                def user = new User(host: account.host, account: account, username: userData.username, password: userData.password).save()
                                UserRole.create(user, adminRole)
                                account.addToUsers(user)
                            }
                            accountData.orders.each { orderData ->
                                def order = new Order(account: account, customer: orderData.customer)
                                orderData.orderDetails.each { orderDetailData ->
                                    order.addToOrderDetails(new OrderDetail(product: orderDetailData.product, quantity: orderDetailData.quantity))
                                }
                                order.save(failOnError: true)
                                account.addToOrders(order)
                            }
                            session.flush()
                            session.clear()
                        }
                    }

                }
            }
        }
    }
}
