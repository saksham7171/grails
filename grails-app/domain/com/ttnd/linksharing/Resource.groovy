package com.ttnd.linksharing

abstract class Resource {
    String description
    User createdBy
    Topic topic
    Date dateCreated
    Date lastUpdated
    static belongsTo = [topic: Topic]
    static hasMany = [readingItems: ReadingItem, resourceRatings: ResourceRating]

    static constraints = {
        createdBy(blank: false)
        description(blank: false)
        topic(blank: false)

    }

    static mapping = {
        description(type: "text")
    }
    
}
