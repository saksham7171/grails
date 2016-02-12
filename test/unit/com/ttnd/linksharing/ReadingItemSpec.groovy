package com.ttnd.linksharing

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(ReadingItem)
class ReadingItemSpec extends Specification {


    void "validating unique reading item"() {
        given:
        LinkResource resource = new LinkResource()
        User user = new User()
        ReadingItem readingItem = new ReadingItem(resource: resource, user: user, isRead: true)
        ReadingItem readingItem1 = new ReadingItem(resource: resource, user: user, isRead: false)

        when:
        readingItem.save(flush: true)
        readingItem1.save(flush: true)

        then:
        !readingItem.errors.allErrors.size()
        readingItem1.errors.allErrors.size()
        readingItem1.errors.getFieldError('resource')

    }
}
