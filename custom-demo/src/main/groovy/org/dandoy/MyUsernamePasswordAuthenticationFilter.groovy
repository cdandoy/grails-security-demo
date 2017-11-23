package org.dandoy

import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (request.getMethod() != "POST") {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        String username = obtainUsername(request);
        String password = obtainPassword(request);
        String host = obtainHost(request)

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();

        UsernamePasswordAuthenticationToken authRequest = new MyUsernamePasswordHostAuthenticationToken(username, password, host);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    private static String obtainHost(HttpServletRequest request) {
        def requestURL = request.getRequestURL().toString()
        final String host = new URI(requestURL).getHost()
        return host
    }
}
