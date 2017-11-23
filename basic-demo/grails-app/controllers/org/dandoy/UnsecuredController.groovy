package org.dandoy

import grails.plugin.springsecurity.annotation.Secured

@Secured('permitAll')
class UnsecuredController {
    def index() { }
}
