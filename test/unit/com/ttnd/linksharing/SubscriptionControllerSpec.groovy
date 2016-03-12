package com.ttnd.linksharing

import com.ttnd.linksharing.constants.Constants
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(SubscriptionController)
@Mock([Topic, Subscription, User])
class SubscriptionControllerSpec extends Specification {


    def "check save subscription if topic doesn't exist"() {
        when:
        controller.save(1)

        then:
        response.contentAsString == "Topic not found"
    }

    def "check save subscription if topic exists"() {
        given:
        User user = new User()
        user.save(flush: true)
        session["user"] = user
        Topic topic = new Topic(name: "topic", visibility: Visibility.PUBLIC, createdBy: user)
        topic.save(flush: true)

        when:
        controller.save(1)

        then:
        response.contentAsString == "subscription saved successfully"
    }

    def "check save subscription if topic exists but not user"() {
        given:
        Topic topic = new Topic(name: "topic", visibility: Visibility.PUBLIC, createdBy: new User())
        topic.save(flush: true)

        when:
        controller.save(1)

        then:
        response.contentAsString == "subscription cant be saved"
    }

    def "check delete subscription if subscription exists"() {
        given:
        User user = new User(email: "sakham.sharma@ttnd.com", username: "saksham", password: Constants.password,
                firstName: "saksham", lastName: "sharma", admin: true, active: true, confirmPassword: Constants.password)
        user.save(flush: true)

        and:
        Topic topic = new Topic(name: "topic1", visibility: Visibility.PRIVATE, createdBy: user)
        topic.save(flush: true)

        and:
        Subscription subscription = new Subscription(user: user, topic: topic)
        subscription.save(flush: true)

        when:
        controller.delete(1)

        then:
        response.contentAsString == "Susbcription deleted successfully"
    }

    def "check delete subscriiption if subsription doesn't exist"() {
        when:
        controller.delete(1)

        then:
        response.contentAsString == "Subscription not found"
    }

    def "check update subscription if subscription doesn't exist"() {
        when:
        controller.update(1, "serious")

        then:
        response.contentAsString == "Subscription not found"
    }

    def "check update subscription if subscription exist success"() {
        given:
        User user = new User(email: "sakham.sharma@ttnd.com", username: "saksham", password: Constants.password,
                firstName: "saksham", lastName: "sharma", admin: true, active: true, confirmPassword: Constants.password)
        user.save(flush: true)

        and:
        Topic topic = new Topic(name: "topic1", visibility: Visibility.PRIVATE, createdBy: user)
        topic.save(flush: true)

        and:
        Subscription subscription = new Subscription(user: user, topic: topic, seriousness: Seriousness.SERIOUS)
        subscription.save(flush: true)

        when:
        controller.update(1, "casual")

        then:
        response.contentAsString == "Successfully updated " + subscription + " " + subscription.seriousness
    }

}
