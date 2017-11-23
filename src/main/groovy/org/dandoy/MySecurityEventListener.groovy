package org.dandoy

import grails.plugin.springsecurity.web.SecurityRequestHolder

/**
 * Adds Tomcat's guessed user name to the session when the user logs in.<br/>
 * See also application.groovy:
 * <pre>
 * grails.plugin.springsecurity.onAuthenticationSuccessEvent = { e, appCtx ->
 *   org.dandoy.MySecurityEventListener.onAuthenticationSuccessEvent(e)
 * }
 * </pre>
 */
class MySecurityEventListener {
    static def onAuthenticationSuccessEvent(event) {
        def username = event.source.principal.username
        SecurityRequestHolder.request.getSession(false)?.setAttribute('userName', username)
    }
}
