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

    def afterInsert = {
        Topic.withNewSession {
            Subscription subscription = new Subscription(user: this.createdBy, topic: this, seriousness: Seriousness.VERY_SERIOUS)
            if (subscription.save())
                log.info "$subscription saved Successfully"
            else
                log.info "Error while saving subscriptions"
        }
    }

    String toString(){
        return "$name Createdby:$createdBy"
    }

}
