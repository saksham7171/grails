package com.ttnd.linksharing

class TopicController {

    def index() {}

    def show(Long id) {
        Topic topic = Topic.read(id)

        if (!topic) {
            flash.error = "Topic doesn't exists"
            redirect(controller: 'login', action: 'index')
        } else if (topic.visibility == Enum.Visibility.PUBLIC)
            render("public topic Success")
        else {
            User user = session.user
            if (!Subscription.findByUserAndTopic(user, topic)) {
                flash.error = "Private Topic,Not Subscribed"
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
            render "Topic saved Successfully"
        }
        else{
            flash.error="Topic can't be saved"
            log.error "${topic.errors.allErrors}"
        }
    }
}
