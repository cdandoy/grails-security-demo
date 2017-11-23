package org.dandoy

import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.util.Assert

class MyDaoAuthenticationProvider extends DaoAuthenticationProvider {

    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper()
    private String userNotFoundEncodedPassword

    void setAuthoritiesMapper(GrantedAuthoritiesMapper authoritiesMapper) {
        this.authoritiesMapper = authoritiesMapper
    }

    @Override
    Authentication authenticate(Authentication authenticationX) throws AuthenticationException {
        Assert.isInstanceOf(
                MyUsernamePasswordHostAuthenticationToken.class, authenticationX,
                messages.getMessage("AbstractUserDetailsAuthenticationProvider.onlySupports", "Only MyUsernamePasswordHostAuthenticationToken is supported")
        )
        MyUsernamePasswordHostAuthenticationToken authentication = (MyUsernamePasswordHostAuthenticationToken) authenticationX

        String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName()

        boolean cacheWasUsed = true
        UserDetails user = userCache.getUserFromCache(username)

        if (user == null) {
            cacheWasUsed = false

            try {
                user = retrieveUserByHost(username, authentication)
            } catch (UsernameNotFoundException notFound) {
                logger.debug("User '" + username + "' not found")

                if (hideUserNotFoundExceptions) {
                    throw new BadCredentialsException(messages.getMessage(
                            "AbstractUserDetailsAuthenticationProvider.badCredentials",
                            "Bad credentials"))
                } else {
                    throw notFound
                }
            }

            Assert.notNull(user, "retrieveUser returned null - a violation of the interface contract")
        }

        try {
            preAuthenticationChecks.check(user)
            additionalAuthenticationChecks(user, authentication)
        } catch (AuthenticationException exception) {
            if (cacheWasUsed) {
                // There was a problem, so try again after checking
                // we're using latest data (i.e. not from the cache)
                cacheWasUsed = false
                user = retrieveUserByHost(username, authentication)
                preAuthenticationChecks.check(user)
                additionalAuthenticationChecks(user, authentication)
            } else {
                throw exception
            }
        }

        postAuthenticationChecks.check(user)

        if (!cacheWasUsed) {
            this.userCache.putUserInCache(user)
        }

        Object principalToReturn = user

        if (forcePrincipalAsString) {
            principalToReturn = user.getUsername()
        }

        return createSuccessAuthentication(principalToReturn, authentication, user)
    }

    protected Authentication createSuccessAuthentication(Object principal, Authentication authentication, UserDetails user) {
        MyUsernamePasswordHostAuthenticationToken myUsernamePasswordHostAuthenticationToken = (MyUsernamePasswordHostAuthenticationToken) authentication
        MyUsernamePasswordHostAuthenticationToken result = new MyUsernamePasswordHostAuthenticationToken(
                principal,
                authentication.getCredentials(),
                myUsernamePasswordHostAuthenticationToken.getHost(),
                authoritiesMapper.mapAuthorities(user.getAuthorities())
        )
        result.setDetails(authentication.getDetails())

        return result
    }

    private final UserDetails retrieveUserByHost(String username, MyUsernamePasswordHostAuthenticationToken authentication) throws AuthenticationException {
        UserDetails loadedUser

        try {
            MyGormUserDetailsService userDetailsService = (MyGormUserDetailsService) getUserDetailsService()
            loadedUser = userDetailsService.loadUserByUsernameAndHost(authentication.host, username)
        } catch (UsernameNotFoundException notFound) {
            if (authentication.getCredentials() != null) {
                String presentedPassword = authentication.getCredentials().toString()
                passwordEncoder.isPasswordValid(userNotFoundEncodedPassword, presentedPassword, null)
            }
            throw notFound
        } catch (Exception repositoryProblem) {
            throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem)
        }

        if (loadedUser == null) {
            throw new InternalAuthenticationServiceException("UserDetailsService returned null, which is an interface contract violation")
        }
        return loadedUser
    }
}
