package com.ttnd.linksharing

import com.ttnd.linksharing.constants.Constants

class LoginController {

    def index() {
        if (session.user)
            forward(controller: 'User', action: 'index')
        else
          return Resource.getTopPost()
//        def resources=Resource.getTopPost()
//        render "failure"
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

    def register() {
        User user = new User(email: "dummmy@ttnd.com", username: "dummy", password: Constants.password,
                firstName: "dummyfirst", lastName: "dummylast", admin: false, active: true, confirmPassword: Constants.password)
        if(!user.save()){
            flash.error="User can't be stored"
            render(user.errors.allErrors.collect{message(error: it)}.join(","))
        }
        else
            render "Success"
    }
}
