package com.ttnd.linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Subscription)
class SubscriptionSpec extends Specification {

    def "validating Subscription"() {
        given:
        Subscription sub = new Subscription(topic: topic, user: user, seriousness: seriousness)

        when:
        Boolean valid = sub.validate()

        then:
        valid == result

        where:
        sno | topic       | user       | seriousness        | result
        1   | new Topic() | new User() | Seriousness.CASUAL | true
        2   | null        | new User() | Seriousness.CASUAL | false
        3   | new Topic() | null       | Seriousness.CASUAL | false
        4   | new Topic() | new User() | null               | false
    }

    def "validating duplicate subscription"() {
        given:
        User user = new User()
        Topic topic = new Topic()
        Subscription sub = new Subscription(topic: topic, user: user, seriousness: Seriousness.VERY_SERIOUS)
        Subscription sub1 = new Subscription(topic: topic, user: user, seriousness: Seriousness.VERY_SERIOUS)

        when:
        sub.save(flush: true)
        sub1.save(flush: true)

        then:
        !sub.errors.allErrors.size()
        sub1.errors.allErrors.size()
        sub1.errors.getFieldError('user')
    }
}
