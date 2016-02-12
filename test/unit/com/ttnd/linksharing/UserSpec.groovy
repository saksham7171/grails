package com.ttnd.linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(User)
class UserSpec extends Specification {

    def setup() {

    }

    def cleanup() {
    }

    void "Testing User"() {
        given:

        User user = new User(firstName: fname,lastName: lname)

        expect:
        user.getName() == fullName

        where:
        fname   | lname    | fullName
        "ram"   | "kumar"  | "ram kumar"
        "shyam" | "sharma" | "shyam sharma"
//        ""|""

    }
}
