package com.ttnd.linksharing

import com.ttnd.linksharing.CO.SearchCO
import com.ttnd.linksharing.CO.UserSearchCO
import com.ttnd.linksharing.VO.UserVO


class User {
    String email
    String username
    String password
    String firstName
    String lastName
    String confirmPassword
    Boolean admin
    Boolean active
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
        username(unique: true)
        confirmPassword(bindable: true, nullable: true, blank: true, validator: { val, obj ->
            if (obj.password != val) {
                return 'com.ttnd.linksharing.User.confirmPassword.validator'
            }
        })
    }

    static namedQueries = {
        search { UserSearchCO co ->
            if (co.active != null) {
                eq('active', co.active)
            }
            if (co.q) {
                or {
                    ilike("firstName", "%${co.q}%")
                    ilike("lastName", "%${co.q}%")
                    ilike("email", "%${co.q}%")
                    ilike("username", "%${co.q}%")
                }
            }
            eq('admin', false)
        }
    }

    List<Topic> getSubscribedTopic() {
        List<Topic> topicList = Subscription.createCriteria().list {
            projections {
                property('topic')
            }
            eq('user', this)

        }
        topicList
    }

    static Boolean canDeleteResource(User user, Long ResourceId) {
        Resource resource = Resource.read(ResourceId)
        return (user.admin || resource.createdBy.id == user.id) ? true : false
    }

    Integer getScore(Resource resource) {
        ResourceRating resourceRating = ResourceRating.findByUserAndResource(this, resource)
        return resourceRating?.score
    }

    Boolean isSubscribed(Long topicId) {
        return Subscription.findByUserAndTopic(this, Topic.get(topicId)) ? true : false
    }

    Subscription getSubscription(Long topicId) {
        Subscription subscription = Subscription.findByUserAndTopic(this, Topic.get(topicId))
        return subscription
    }

    Boolean equals(Topic topic) {
        return (this == topic.createdBy) ? true : false
    }

    static List<UserVO> getNormalUser(SearchCO co) {
        List<UserVO> normalUsers = []
        User.findAllByAdminNotEqual(true, [sort: co.sort, order: co.order]).each {
            normalUsers.add(new UserVO(id: it.id, name: it.username, firstName: it.firstName, lastName: it.lastName, email: it.email, isActive: it.active))
        }
        return normalUsers
    }
}
