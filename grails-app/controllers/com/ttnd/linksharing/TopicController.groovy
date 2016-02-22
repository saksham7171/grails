package com.ttnd.linksharing

class TopicController {

    def index() {}

    def show(Long id) {
        Topic topic = Topic.get(id)

        if (!topic) {
            flash.error = "Topic doesn't exists"
            redirect(controller: 'login', action: 'index')
        } else if (topic.visibility == Visibility.PUBLIC)
            render("Success")
        else {
            User user = session.user
            if (!Subscription.findByUserAndTopic(user, topic)) {
                flash.error = "Private Topic,Not Subscribed"
            } else
                render("Success")
        }
    }
}
