package com.ttnd.linksharing

import grails.test.spock.IntegrationSpec

class LoginControllerIntegrationSpec extends IntegrationSpec {

    def setup() {
    }

    def cleanup() {
    }

    def "check top posts"() {
        setup:
        LoginController loginController = new LoginController()

        when:
        List list = loginController.index()

        then:
        !list.isEmpty()

    }
}
