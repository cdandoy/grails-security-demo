package org.dandoy

import grails.compiler.GrailsCompileStatic
import grails.gorm.MultiTenant
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@GrailsCompileStatic
@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class User implements Serializable, MultiTenant<User> {

    private static final long serialVersionUID = 1
    static belongsTo = [account:Account]
    Long tenantId
    String host
    String username
    String password
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    void setAccount(Account account) {
        this.account = account
        this.tenantId = account.id
    }

    Set<Role> getAuthorities() {
        (UserRole.findAllByUser(this) as List<UserRole>)*.role as Set<Role>
    }

    static constraints = {
        password nullable: false, blank: false, password: true
        username nullable: false, blank: false, unique: ['host']
    }

    static mapping = {
        table "users"
        password column: '`password`'
    }
}
