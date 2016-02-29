package com.ttnd.linksharing

import com.ttnd.linksharing.CO.ResourceSearchCO

class TopicController {

    def index() {}

    def show(ResourceSearchCO co) {
        Topic topic = Topic.read(co.topicId)

        if (!topic) {
            flash.error = "Topic doesn't exists"
            redirect(controller: 'login', action: 'index')
        } else if (topic.visibility ==Visibility.PUBLIC)
            render("public topic Success")
        else {
            User user = session.user
            if (!Subscription.findByUserAndTopic(user, topic)) {
                flash.error = "Private Topic,Not Subscribed"
                render (flash.error)
            } else
                render("private topic Success")
        }
    }

    def save(String name,String visibility){
        User user=session.user

        Topic topic=new Topic(createdBy:user,name:name,visibility: Visibility.convert(visibility))

        if(topic.validate()){
            topic.save(flush: true)
            user.addToTopics(topic)
            Topic.list().add(topic)
            flash.message="topic is saved"
        }
        else{
            flash.error="Topic can't be saved"
            log.error "${topic.errors.allErrors}"
        }
        redirect(uri:'/')
    }
}
