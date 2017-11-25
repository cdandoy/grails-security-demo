package org.dandoy

import org.grails.datastore.mapping.multitenancy.web.SessionTenantResolver
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import static grails.gorm.multitenancy.Tenants.withId

class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        withId(1L) {
            Account.withNewSession {
                def requestURL = request.getRequestURL().toString()
                String host = new URI(requestURL).getHost()
                def account = Account.findByHost(host)
                request.session.setAttribute(SessionTenantResolver.ATTRIBUTE, account.id)
            }
        }
        return super.attemptAuthentication(request, response)
    }
}
