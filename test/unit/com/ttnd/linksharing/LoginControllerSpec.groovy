package com.ttnd.linksharing

import com.ttnd.linksharing.constants.Constants
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(LoginController)
@Mock([User])
class LoginControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    def "check if user session exists"() {
        given: "a session user exists"
        session["user"] = User.load(1)

        when:
        controller.index()

        then:
        response.forwardedUrl == '/user/index'
    }

    def "check if user doesn't exist"() {
        when:
        controller.index()

        then:
        response.contentAsString == "failure"
    }

    def "if user is logged out"() {
        when:
        controller.logout()
        then:
        !session.user
        response.forwardedUrl == '/login/index'
    }

    def "check if user doesn't exists"() {
        given:
        String username = "saksham"
        String password = "default"

        when:
        controller.loginHandler(username, password)

        then:
        response.contentAsString == "User is not found"
    }

    def "check if user exists and not active"() {
        given:
        User user = new User(email: "sakham.sharma@ttnd.com", username: "saksham", password: Constants.password,
                firstName: "saksham", lastName: "sharma", admin: true, active: false, confirmPassword: Constants.password)
        user.save(flush: true)

        when:
        controller.loginHandler(username, password)

        then:
        response.contentAsString == "User is not active"

        where:
        username  | password
        "saksham" | Constants.password
    }

    def "check if user exists and active"() {
        given:
        User user = new User(email: "sakham.sharma@ttnd.com", username: "saksham", password: Constants.password,
                firstName: "saksham", lastName: "sharma", admin: true, active: true, confirmPassword: Constants.password)
        user.save(flush: true)

        when:
        controller.loginHandler(username, password)

        then:
        session.user
        response.redirectedUrl == '/user/index'

        where:
        username  | password
        "saksham" | Constants.password
    }
}
