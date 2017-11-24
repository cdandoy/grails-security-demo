package org.dandoy

import grails.gorm.transactions.Transactional
import grails.plugin.springsecurity.userdetails.GormUserDetailsService
import grails.plugin.springsecurity.userdetails.NoStackUsernameNotFoundException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException

class MyGormUserDetailsService extends GormUserDetailsService {
    @Override
    UserDetails loadUserByUsername(String username, boolean loadRoles) throws UsernameNotFoundException {
        throw new IllegalStateException("loadUserByUsername() is not supported by MyGormUserDetailsService")
    }

    @Transactional(readOnly = true, noRollbackFor = [IllegalArgumentException, UsernameNotFoundException])
    UserDetails loadUserByUsernameAndHost(String host, String username) throws UsernameNotFoundException {

        User user = User.findByHostAndUsername(host, username)

        if (!user) {
            log.warn 'User not found: {}', username
            throw new NoStackUsernameNotFoundException()
        }

        Collection<GrantedAuthority> authorities = loadAuthorities(user, username, true)
        createUserDetails(host, user, authorities)
    }

    private static UserDetails createUserDetails(String host, User user, Collection<GrantedAuthority> authorities) {
        new MyGrailsUser(host, user.username, user.password, user.enabled, !user.accountExpired, !user.passwordExpired, !user.accountLocked, authorities, user.id)
    }
}
