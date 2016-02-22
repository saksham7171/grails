package com.ttnd.linksharing

class LoginController {

    def index() {
        if (session.user)
            forward(controller: 'User', action: 'index')
        else
            render("failure")
    }

    def logout() {
        session.invalidate()
        forward(action: 'index')

    }

    def loginHandler(String username, String password) {
        User user = User.findByUsernameAndPassword(username, password)

        if (user) {
            if (user.active) {
                session["user"] = user
                redirect(controller: 'User', action: 'index')
            } else
                flash.error = "User is not active"
        } else
            flash.error = "User is not found"
            render(flash.error)
    }
}
