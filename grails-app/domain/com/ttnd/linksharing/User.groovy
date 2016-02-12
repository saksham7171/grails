package com.ttnd.linksharing

import javax.validation.constraints.Min

class User {
    String email
    String username
    String password
    String firstName
    String lastName
    Boolean admin
    Boolean active
    Byte[] photo
    Date dateCreated
    Date lastUpdated

    static hasMany = [subscriptions  : Subscription, topics: Topic, readingItems: ReadingItem,
                      resourceRatings: ResourceRating, resources: Resource]

    static mapping = {
        photo(sqlType: "longblob")
    }

    static transients = ['name']

    String getName() {
        [this.firstName,this.lastName].findAll{it}.join(' ')
    }


    static constraints = {
        email(unique: true, email: true, blank: false)
        password(blank: false, minSize: 5)
        firstName(blank: false)
        lastName(blank: false)
        active(nullable: true)
        admin(nullable: true)
        photo(nullable: true)
    }


}
