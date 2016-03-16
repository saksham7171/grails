package com.ttnd.linksharing

import com.ttnd.linksharing.CO.ResourceSearchCO
import com.ttnd.linksharing.CO.SearchCO
import com.ttnd.linksharing.CO.TopicSearchCO

class LoginController {

    def index() {
        if (session.user) {
            redirect(controller: 'user', action: 'index')
        } else {
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
            } else {
                flash.error = "User is not active"
            }
        } else {
            flash.error = "User is not found"
            redirect action: 'index'
        }
    }

    def validateEmail() {
        render !User.countByEmail(params.email) as Boolean
    }

    def validateUserName() {
        render !User.countByUsername(params.username) as Boolean
    }

    def existUserName() {
        render User.countByUsername(params.username) as Boolean
    }

    def globalSearch(ResourceSearchCO co) {
        ResourceSearchCO resourceSearchCO = new ResourceSearchCO(q: co.q)
        co.max = co.max ?: 5
        co.offset = co.offset ?: 0
        List<Resource> resources = []
        if (!session.user) {
            resourceSearchCO.visibility = Visibility.PUBLIC
        }
        resources = Resource.globalSearch(resourceSearchCO).list([max:co.max,offset:co.offset])
        Integer total=Resource.globalSearch(resourceSearchCO).count()
        render view: "/global/search", model: [resources: resources,total:total,co:co]
    }
}