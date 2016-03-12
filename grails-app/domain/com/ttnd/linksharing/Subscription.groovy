package com.ttnd.linksharing

class Subscription {
    Topic topic
    User user
    Seriousness seriousness=Seriousness.SERIOUS
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
}
