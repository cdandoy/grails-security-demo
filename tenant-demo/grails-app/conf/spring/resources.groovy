import grails.plugin.springsecurity.SpringSecurityUtils
import org.dandoy.MyDaoAuthenticationProvider
import org.dandoy.MyGormUserDetailsService
import org.dandoy.MyUsernamePasswordAuthenticationFilter
import org.dandoy.UserPasswordEncoderListener

// Place your Spring DSL code here
beans = {
    userPasswordEncoderListener(UserPasswordEncoderListener, ref('hibernateDatastore'))

    def conf = SpringSecurityUtils.securityConfig

    authenticationProcessingFilter(MyUsernamePasswordAuthenticationFilter) {
        authenticationManager = ref('authenticationManager')
        sessionAuthenticationStrategy = ref('sessionAuthenticationStrategy')
        authenticationSuccessHandler = ref('authenticationSuccessHandler')
        authenticationFailureHandler = ref('authenticationFailureHandler')
        rememberMeServices = ref('rememberMeServices')
        authenticationDetailsSource = ref('authenticationDetailsSource')
        requiresAuthenticationRequestMatcher = ref('filterProcessUrlRequestMatcher')
        usernameParameter = conf.apf.usernameParameter // username
        passwordParameter = conf.apf.passwordParameter // password
        continueChainBeforeSuccessfulAuthentication = conf.apf.continueChainBeforeSuccessfulAuthentication // false
        allowSessionCreation = conf.apf.allowSessionCreation // true
        postOnly = conf.apf.postOnly // true
    }

    myDaoAuthenticationProvider(MyDaoAuthenticationProvider) {
        userDetailsService = ref('userDetailsService')
        passwordEncoder = ref('passwordEncoder')
        userCache = ref('userCache')
        saltSource = ref('saltSource')
        preAuthenticationChecks = ref('preAuthenticationChecks')
        postAuthenticationChecks = ref('postAuthenticationChecks')
        authoritiesMapper = ref('authoritiesMapper')
        hideUserNotFoundExceptions = conf.dao.hideUserNotFoundExceptions // true
    }
    userDetailsService(MyGormUserDetailsService) {

    }
}
