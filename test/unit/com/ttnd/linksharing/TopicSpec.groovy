package com.ttnd.linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Topic)
class TopicSpec extends Specification {

    def "validating topic"() {
        given:
        Topic topic = new Topic(createdBy: creator, name: name, visibility: visibility)

        when:
        Boolean valid = topic.validate()

        then:
        valid == result

        where:
        sno | creator    | name   | visibility         | result
        1   | new User() | "name" | Visibility.PRIVATE | true
        2   | null       | "name" | Visibility.PRIVATE | false
        3   | new User() | ""     | Visibility.PRIVATE | false
        4   | new User() | null   | Visibility.PRIVATE | false
        5   | new User() | "name" | null               | false
    }

    def "validating Duplicate topic"() {
        given:
        Topic topic = new Topic(name: "grails", createdBy: new User(), visibility: Visibility.PRIVATE)
        Topic topic1 = new Topic(name: "grails", createdBy: new User(), visibility: Visibility.PRIVATE)

        when:
        topic.save(flush: true)
        topic1.save(flush: true)

        then:
        !topic.errors.allErrors.size()
        topic1.errors.allErrors.size()
        topic1.errors.getFieldError('name')

    }

    def "toString validation"() {

        given:
        Topic topic = new Topic(name: "grails", createdBy: new User(firstName: "saksham ", lastName: "sharma"), visibility: Visibility.PRIVATE)

        expect:
        topic.toString() == "grails Createdby:saksham sharma"
    }

}
