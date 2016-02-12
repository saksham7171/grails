package com.ttnd.linksharing

class ResourceRating {
    Resource resource
    User user
    Integer score
    static belongsTo = [user: User, resource: Resource]

    static constraints = {
        score(range: 1..5)
        resource(unique: "user")
    }
}
