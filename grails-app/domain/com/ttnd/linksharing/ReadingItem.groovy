package com.ttnd.linksharing

class ReadingItem {
    Resource resource
    User user
    Boolean isRead
    static belongsTo = [user: User, resource: Resource]

    static constraints = {
        resource(unique: "user")
    }
}
