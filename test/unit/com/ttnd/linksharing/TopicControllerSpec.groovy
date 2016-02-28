package com.ttnd.linksharing

import com.ttnd.linksharing.CO.ResourceSearchCO
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(TopicController)
@Mock([Topic, User, Subscription])
class TopicControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    def "check if topic doesn't exists"() {
        given:
        ResourceSearchCO co = new ResourceSearchCO(topicId: 1)

        when:
        controller.show(co)

        then:
        flash.error == "Topic doesn't exists"
        response.redirectedUrl == '/login/index'

    }

    def "check if topic exists and visibility is private and not subscribed"() {
        given:
        Topic topic = new Topic(name: "topic1", visibility: Visibility.PRIVATE, createdBy: new User())
        topic.save(flush: true)
        ResourceSearchCO co = new ResourceSearchCO(topicId: 1)

        when:
        controller.show(co)

        then:
        flash.error == "Private Topic,Not Subscribed"
    }

    def "check if topic exists and visibility is private and subscribed"() {
        given:
        User user = new User()
        session["user"] = user
        Topic topic = new Topic(name: "topic1", visibility: Visibility.PRIVATE, createdBy: user)
        topic.save(flush: true)
        ResourceSearchCO co = new ResourceSearchCO(topicId: 1)

        when:
        controller.show(co)

        then:
        response.contentAsString == "private topic Success"
    }
}
