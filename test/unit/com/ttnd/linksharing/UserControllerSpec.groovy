package com.ttnd.linksharing

import com.ttnd.linksharing.constants.Constants
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(UserController)
@Mock([User])
class UserControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    def "check user dashboard"() {
        when: "user exists"
        User user = new User(email: "sakham.sharma@ttnd.com", username: "saksham", password: Constants.password,
                firstName: "saksham", lastName: "sharma", admin: true, active: true, confirmPassword: Constants.password)
        user.save(flush: true)

        session["user"]=user
        controller.index()

        then: "dashboard view should be rendered"
        response.contentAsString == "user dashboard ${session.user}"
    }
}
