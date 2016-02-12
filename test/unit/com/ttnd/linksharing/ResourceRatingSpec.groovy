package com.ttnd.linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ResourceRating)
class ResourceRatingSpec extends Specification {

    def "validating resource rating"() {
        given:
        ResourceRating resourceRating = new ResourceRating(resource: resource, user: user, score: score)

        when:
        Boolean valid = resourceRating.validate()

        then:
        valid == result

        where:
        sno | resource           | user       | score | result
        1   | new LinkResource() | new User() | 3     | true
        2   | null               | new User() | 3     | false
        3   | new LinkResource() | null       | 3     | false
        4   | new LinkResource() | new User() | 10    | false
        5   | new LinkResource() | new User() | 0     | false

    }

    def "validate unique resource rating"() {
        given:
        LinkResource resource = new LinkResource()
        User user = new User()
        ResourceRating resourceRating = new ResourceRating(resource: resource, user: user, score: 3)
        ResourceRating resourceRating1 = new ResourceRating(resource: resource, user: user, score: 4)

        when:
        resourceRating.save(flush: true)
        resourceRating1.save(flush: true)

        then:
        !resourceRating.errors.allErrors.size()
        resourceRating1.errors.allErrors.size()
        resourceRating1.errors.getFieldError('resource')


    }
}
