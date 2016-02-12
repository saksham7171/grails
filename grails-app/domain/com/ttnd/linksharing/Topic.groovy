package com.ttnd.linksharing


class Topic {
    String name
    User createdBy
    Date dateCreated
    Date lastUpdated
    Visibility visibility
    static hasMany = [resources: Resource, subscriptions: Subscription]

    static constraints = {
        name(unique: "createdBy", blank: false)
        visibility(blank: false)
        createdBy(blank: false)
    }
}
