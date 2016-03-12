package com.ttnd.linksharing

import com.ttnd.linksharing.CO.TopicSearchCO
import grails.transaction.Transactional

@Transactional
class SubscriptionService {

    def search(TopicSearchCO co) {
        if (co.userId) {
            User user = co.getUser()
            return user.getSubscribedTopic()
        }
    }
}
