package org.dandoy

import grails.plugin.springsecurity.userdetails.GrailsUser
import org.springframework.security.core.GrantedAuthority

class MyGrailsUser extends GrailsUser {
    private String host

    MyGrailsUser(String host, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked,
                 Collection<GrantedAuthority> authorities, Object id) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities, id)
        this.host = host
    }

    String getHost() {
        return host
    }
}
