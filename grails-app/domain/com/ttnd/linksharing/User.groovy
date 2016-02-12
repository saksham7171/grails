package com.ttnd.linksharing

import javax.validation.constraints.Min

class User {
    String email
    String username
    String password
    String firstName
    String lastName
    boolean admin
    boolean active
    byte[] photo
    Date dateCreated
    Date lastUpdated

    static hasMany = [subscriptions  : Subscription, topics: Topic, readingItems: ReadingItem,
                      resourceRatings: ResourceRating, resources: Resource]

    static mapping = {
        photo(sqlType: "blob")
    }

    static transients = ['name']

    String getName() {
        return "${firstName} ${lastName}"
    }


    static constraints = {
        email(unique: true, email: true, blank: false)
        password(blank: false, minSize: 5)
        firstName(blank: false)
        lastName(blank: false)
        active(nullable: true)
        admin(nullable: true)
    }


}
