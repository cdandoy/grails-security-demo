package grails.security.demo

import org.dandoy.Role
import org.dandoy.User
import org.dandoy.UserRole

class BootStrap {

    def init = { servletContext ->
        def adminRole = new Role(authority: 'ROLE_ADMIN').save()

        def localhostUser = new User(host: 'localhost', username: 'me', password: 'password').save()
        UserRole.create(localhostUser, adminRole)

        def homeUser = new User(host: '127.0.0.1', username: 'me', password: 'secret').save()
        UserRole.create(homeUser, adminRole)

        UserRole.withSession {
            it.flush()
            it.clear()
        }
    }

    def destroy = {
    }
}
