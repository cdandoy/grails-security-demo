package org.dandoy

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority

class MyUsernamePasswordHostAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private final String host

    MyUsernamePasswordHostAuthenticationToken(Object principal, Object credentials, String host) {
        super(principal, credentials)
        this.host = host
    }

    MyUsernamePasswordHostAuthenticationToken(Object principal, Object credentials, String host, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities)
        this.host = host
    }

    String getHost() {
        return this.host
    }
}
