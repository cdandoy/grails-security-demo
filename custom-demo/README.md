# custom-demo
This application identifies users not only by username but also by the host used to login.
It shows how to implement a custom 
[AbstractAuthenticationProcessingFilter](https://docs.spring.io/spring-security/site/docs/current/apidocs/org/springframework/security/web/authentication/AbstractAuthenticationProcessingFilter.html),
[AuthenticationProvider](https://docs.spring.io/spring-security/site/docs/current/apidocs/org/springframework/security/authentication/AuthenticationProvider.html) and a
[UserDetailsService](https://docs.spring.io/spring-security/site/docs/current/apidocs/org/springframework/security/core/userdetails/UserDetailsService.html)
in Grails

A `host` field has been added to the [`User`](https://github.com/cdandoy/grails-security-demo/blob/master/custom-demo/grails-app/domain/org/dandoy/User.groovy)
class and the `username` is unique by host.

[`Bootstrap.groovy`](https://github.com/cdandoy/grails-security-demo/blob/master/custom-demo/grails-app/init/grails/security/demo/BootStrap.groovy)
 creates two users with the same name (me) but registered with different hosts (localhost and 127.0.0.1) and with different passwords (password and secret).
 
You can login at [http://localhost:8080/](http://localhost:8080/) using `me/password` and at  [http://127.0.0.1:8080/](http://127.0.0.1:8080/) using `me/secret`.
