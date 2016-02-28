package com.ttnd.linksharing

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(ResourceController)
@Mock([LinkResource, Resource, User, Topic])
class ResourceControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    def "check deletion of resource success"() {
        given:
        Resource linkresource = new LinkResource(description: "link resource",
                createdBy: new User(), topic: new Topic(), url: "https://www.google.com/")
        linkresource.save(flush: true)

        when:
        controller.delete(1)

        then:
        response.contentAsString == "Resource deleted with id : 1"

    }

    def "check deletion of resource failure"() {

        when:
        controller.delete(1)

        then:
        response.contentAsString == "Resource can't be deleted"

    }
}
