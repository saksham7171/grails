package com.ttnd.linksharing

import com.ttnd.linksharing.CO.TopicSearchCO

class Subscription {
    Topic topic
    User user
    Seriousness seriousness = Seriousness.SERIOUS
    Date dateCreated
    Date lastUpdated
    static belongsTo = [user: User, topic: Topic]

    static constraints = {
        user(unique: "topic")
    }
    static mapping = {
        topic lazy: false
        user lazy: false
    }
    static namedQueries = { //todo include visibility search criteria also
        search { TopicSearchCO co ->
            if (co.userId) {
                eq('user.id', co.userId)
            }
            if (co.q) {
                'topic' {
                    ilike('name', "%${co.q}%")
                }
            }
        }
    }

}
