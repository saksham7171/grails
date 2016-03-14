package com.ttnd.linksharing

import com.ttnd.linksharing.CO.ResourceSearchCO
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

    def globalSearch(String q) {
        ResourceSearchCO resourceSearchCO = new ResourceSearchCO(q: q)
        TopicSearchCO topicSearchCO = new TopicSearchCO(q: q)
        List<Topic> topics = []
        List<Resource> resources = []
        if (!session.user) {
            resourceSearchCO.visibility = Visibility.PUBLIC
            topicSearchCO.visibility = Visibility.PUBLIC
        } else {
            topicSearchCO.userId = session.user.id
        }

        Subscription.search(topicSearchCO).list().each {
            topics.add(it.topic)
        }
        resources = Resource.search(resourceSearchCO).list()
        render view: "/global/search", model: [resources: resources, topics: topics]
    }
}