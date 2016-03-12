package com.ttnd.linksharing

import com.ttnd.linksharing.VO.RatingInfoVO
import grails.test.spock.IntegrationSpec

class ResourceControllerIntegrationSpec extends IntegrationSpec {


    def "check trending topic"() {
        setup:
        ResourceController resourceController = new ResourceController()

        when:
        List list = resourceController.showTrendingTopics()

        then:
        !list.isEmpty()
    }

    def "check rating-info"() {
        setup:
        ResourceController resourceController = new ResourceController()

        when:
        RatingInfoVO vo = resourceController.show(1)

        then:
        vo != null
    }
}
