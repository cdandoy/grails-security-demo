// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'org.dandoy.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'org.dandoy.UserRole'
grails.plugin.springsecurity.authority.className = 'org.dandoy.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
        [pattern: '/', access: ['permitAll']],
        [pattern: '/error', access: ['permitAll']],
        [pattern: '/shutdown', access: ['permitAll']],
        [pattern: '/assets/**', access: ['permitAll']],
        [pattern: '/**/js/**', access: ['permitAll']],
        [pattern: '/**/css/**', access: ['permitAll']],
        [pattern: '/**/images/**', access: ['permitAll']],
        [pattern: '/**/favicon.ico', access: ['permitAll']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
        [pattern: '/assets/**', filters: 'none'],
        [pattern: '/**/js/**', filters: 'none'],
        [pattern: '/**/css/**', filters: 'none'],
        [pattern: '/**/images/**', filters: 'none'],
        [pattern: '/**/favicon.ico', filters: 'none'],
        [pattern: '/**', filters: 'JOINED_FILTERS']
]

// Adds Tomcat's guessed username
grails.plugin.springsecurity.useSecurityEventListener = true
grails.plugin.springsecurity.onAuthenticationSuccessEvent = { e, appCtx ->
    grails.plugin.springsecurity.web.SecurityRequestHolder.request.getSession(false)?.setAttribute('userName', e.source.principal.username)
}

grails.plugin.springsecurity.providerNames = [
        'myDaoAuthenticationProvider',
        'anonymousAuthenticationProvider',
        'rememberMeAuthenticationProvider',
]
