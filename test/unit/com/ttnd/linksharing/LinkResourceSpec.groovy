package com.ttnd.linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(LinkResource)
class LinkResourceSpec extends Specification {

    def "validating linkresource"() {
        given:
        LinkResource linkResource = new LinkResource(description: description, createdBy: createdby, topic: topic, url: url)

        when:
        Boolean valid = linkResource.validate()

        then:
        result == valid

        where:
        sno | description   | createdby  | topic       | url                        | result
        1   | "description" | new User() | new Topic() | "http://www.tothenew.com/" | true
        2   | ""            | new User() | new Topic() | "http://www.tothenew.com/" | false
        3   | null          | new User() | new Topic() | "http://www.tothenew.com/" | false
        4   | "description" | null       | new Topic() | "http://www.tothenew.com/" | false
        5   | "description" | new User() | null        | "http://www.tothenew.com/" | false
        6   | "description" | new User() | new Topic() | "1354asdasfsa"             | false
        7   | "description" | new User() | new Topic() | ""                         | false
        8   | "description" | new User() | new Topic() | null                       | false


    }
}
