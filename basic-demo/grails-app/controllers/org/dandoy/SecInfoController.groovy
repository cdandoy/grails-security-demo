package org.dandoy

class SecInfoController {
    def springSecurityService

    def index() {
        def currentUser = springSecurityService.currentUser
        def principal = springSecurityService.principal
        def authentication = springSecurityService.authentication
        def authorities = authentication.authorities
        boolean authenticated = authentication.authenticated
        return [
                currentUser   : currentUser,
                principal     : principal,
                authentication: authentication,
                authorities   : authorities,
                authenticated : authenticated,
        ]
    }
}
