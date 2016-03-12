package com.ttnd.linksharing

import grails.test.spock.IntegrationSpec

class ReadingItemControllerIntegrationSpec extends IntegrationSpec {

    def setup() {
    }

    def cleanup() {
    }

    def "check chnage is read"() {
        setup:
        ReadingItemController readingItemController = new ReadingItemController()

        when:
        readingItemController.changeIsRead(id, isRead)
        ReadingItem readingItem = ReadingItem.get(id)

        then:
        readingItem.isRead == isRead

        where:
        id | isRead
        1  | false
        2  | true
    }
}