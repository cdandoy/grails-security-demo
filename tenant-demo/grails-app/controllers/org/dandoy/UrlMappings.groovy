package org.dandoy

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(controller: 'unsecured')
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
