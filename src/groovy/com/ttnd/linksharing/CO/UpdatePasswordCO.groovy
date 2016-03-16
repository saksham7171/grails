package com.ttnd.linksharing.CO

import com.ttnd.linksharing.User
import grails.validation.Validateable

@Validateable
class UpdatePasswordCO {
    String oldPassword
    String password
    String confirmPassword
    Integer id

    User getUser() {
        User.get(id)
    }

    static constraints = {
        importFrom User
    }

    String toString() {
        "${oldPassword}:${password}:${id}"
    }
}