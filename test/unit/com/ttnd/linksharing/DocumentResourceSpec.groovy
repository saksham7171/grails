package com.ttnd.linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(DocumentResource)
class DocumentResourceSpec extends Specification {

    def "validating document resource"() {
        given:
        DocumentResource documentResource = new DocumentResource(description: description, createdBy: createdby,
                topic: topic, filePath: path)
        when:
        Boolean valid = documentResource.validate()

        then:
        valid == result

        where:
        sno | description   | createdby  | topic       | path            | result
        1   | "description" | new User() | new Topic() | "/home/saksham" | true
        2   | ""            | new User() | new Topic() | "/home/saksham" | false
        3   | null          | new User() | new Topic() | "/home/saksham" | false
        4   | "description" | null       | new Topic() | "/home/saksham" | false
        5   | "description" | new User() | null        | "/home/saksham" | false
        6   | "description" | new User() | new Topic() | ""              | false
        7   | "description" | new User() | new Topic() | null            | false

    }

    def "toString validation"() {
        given:
        DocumentResource documentResource = new DocumentResource(description: "description", createdBy: new User(),
                topic: new Topic(), filePath: "/home/saksham")

        expect:
        documentResource.toString() == "/home/saksham"

    }
}
