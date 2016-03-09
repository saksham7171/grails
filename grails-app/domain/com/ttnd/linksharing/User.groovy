package com.ttnd.linksharing

import javax.validation.constraints.Min

class User {
    String email
    String username
    String password
    String firstName
    String lastName
    String confirmPassword
    Boolean admin = false
    Boolean active = true
    Byte[] photo
    Date dateCreated
    Date lastUpdated

    static hasMany = [subscriptions  : Subscription, topics: Topic, readingItems: ReadingItem,
                      resourceRatings: ResourceRating, resources: Resource]

    static mapping = {
        sort id: "desc"
        photo(sqlType: "longblob")

    }

    static transients = ['name', 'confirmPassword', 'subscribedTopics']

    String getName() {
        return "$firstName $lastName"
    }

    String toString() {
        return "$username"
    }

    static constraints = {

        email(unique: true, email: true, blank: false)
        password(blank: false, minSize: 5)
        firstName(blank: false)
        lastName(blank: false)
        active(nullable: true)
        admin(nullable: true)
        photo(nullable: true)
        confirmPassword(bindable: true, nullable: true, blank: true, validator: { val, obj ->
            if (obj.password != val)
                return 'com.ttnd.linksharing.User.confirmPassword.validator'
        })
    }

    List<Topic> getSubscribedTopic() {
        List<Topic> topicList = Subscription.createCriteria().list() {
            projections {
                property('topic')
            }
            eq('user', this)

        }
        topicList
    }

    static Boolean canDeleteResource(User user, Long ResourceId) {
        Resource resource = Resource.read(ResourceId)
        if (user.admin || resource.createdBy.id == user.id)
            return true
        else
            return false
    }

    Integer getScore(Resource resource) {
        ResourceRating resourceRating = ResourceRating.findByUserAndResource(this, resource)
        return resourceRating.score
    }

    Boolean isSubscribed(Long topicId) {
        if (Subscription.findByUserAndTopic(this, Topic.get(topicId)))
            return true
        else
            return false
    }
}
