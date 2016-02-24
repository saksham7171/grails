package com.ttnd.linksharing

import javax.validation.constraints.Min

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

    static transients = ['name', 'confirmPassword']

    String getName() {
        return "$firstName $lastName"
    }

    String toString() {
        return "$firstName $lastName"
    }

    static constraints = {

        email(unique: true, email: true, blank: false)
        password(blank: false, minSize: 5)
        firstName(blank: false)
        lastName(blank: false)
        active(nullable: true)
        admin(nullable: true)
        photo(nullable: true)
        confirmPassword(bindable:true,nullable: true, blank: true, validator: { val, obj ->
            if (obj.password != val)
                return 'wrong.password'
        })


    }


}
