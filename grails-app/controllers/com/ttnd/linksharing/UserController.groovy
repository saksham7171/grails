package com.ttnd.linksharing

class UserController {

    def index() {
        render("user dashboard "+session.user)
    }
}
