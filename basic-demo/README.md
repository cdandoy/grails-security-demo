#basic-demo
A basic example of a [Grails](https://grails.org/) application and the [Spring Security plugin](http://grails-plugins.github.io/grails-spring-security-core/)

It consists of a default Grails application and a basic Grails Spring Security setup.

The only addition is that it implements 'Tomcat's Guessed Username' by using a 
`onAuthenticationSuccessEvent` listener which sets a session attribute.  
See [`application.groovy:25`](https://github.com/cdandoy/grails-security-demo/blob/master/basic-demo/grails-app/conf/application.groovy#L25)
