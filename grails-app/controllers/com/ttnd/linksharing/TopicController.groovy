package com.ttnd.linksharing

import com.ttnd.linksharing.CO.ResourceSearchCO
import grails.converters.JSON

class TopicController {

    def index() {}

    def show(ResourceSearchCO co) {
        Topic topic = Topic.read(co.topicId)
        List<User> users = topic.getSubscribedUsers()

        if (!topic) {
            flash.error = "Topic doesn't exists"
            redirect(controller: 'login', action: 'index')
        } else if (topic.visibility == Visibility.PUBLIC)
        //render("public topic Success")
            render view: "show", model: [topic: topic, users: users]
        else {
            User user = session.user
            if (!Subscription.findByUserAndTopic(user, topic)) {
                flash.error = "Private Topic,Not Subscribed"
                render(flash.error)
            } else
                render view: "show", model: [topic: topic, users: users]
        }
    }

    def save(String topicName, String visibility) {
        Map jsonObj=[:]
        User user = session.user
        Topic topic = Topic.findOrCreateByCreatedByAndName(user, topicName)
        topic.visibility = Visibility.convert(visibility)
        if (topic.validate()) {
            topic.save(flush: true)
//            Topic.list().add(topic)
            jsonObj.message = "topic is saved"
        } else {
            jsonObj.error = "Topic can't be saved"
            flash.error="${topic.errors.allErrors}"
        }
//        response.setContentType("application/json")
        render jsonObj as JSON
    }
}
