package com.ttnd.linksharing

import com.ttnd.linksharing.CO.UserCO


class LoginController {

    def index() {
        if (session.user)
            forward(controller: 'User', action: 'index')
        else {
            //return Resource.getTopPost()
            def topPosts = Resource.getTopPost()
            def recentShares = Resource.getRecentShare()
            render view: 'index', model: [topPosts: topPosts, recentShares: recentShares]
        }
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
