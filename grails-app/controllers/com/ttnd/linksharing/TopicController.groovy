package com.ttnd.linksharing

import com.ttnd.linksharing.CO.ResourceSearchCO
import grails.converters.JSON

class TopicController {

    def emailService


    def show(ResourceSearchCO co) {
        Topic topic = Topic.read(co.topicId)
        List<User> users = topic.getSubscribedUsers()
        co.max = co.max ?: 2
        co.offset = co.offset ?: 0
        Integer total

        if (!topic) {
            flash.error = "Topic doesn't exists"
            redirect(controller: 'login', action: 'index')
        } else if (topic.visibility == Visibility.PUBLIC) {
            co.visibility = Visibility.PUBLIC
            List<Resource> resources = Resource.search(co).list([max: co.max, offset: co.offset])
            total = resources.totalCount
            render view: "show", model: [topic: topic, users: users, resources: resources, total: total, co: co]
        } else {
            User user = session.user
            if (!Subscription.findByUserAndTopic(user, topic)) {
                flash.error = "Private Topic,Not Subscribed"
                redirect controller: 'user',action: 'index'
            } else {
                List<Resource> resources = Resource.search(co).list([max: co.max, offset: co.offset])
                total = resources.totalCount
                render view: "show", model: [topic: topic, users: users, resources: resources, total: total, co: co]
            }
        }
    }

    def save(String topicName, String visibility) {
        Map jsonObj = [:]
        User user = session.user
        Topic topic = Topic.findOrCreateByCreatedByAndName(user, topicName)
        topic.visibility = Visibility.convert(visibility)
        if (topic.validate()) {
            topic.save(flush: true)
            jsonObj.message = "topic is saved Successfully"
            flash.message = "Topic is saved Successfully"
        } else {
            jsonObj.error = "Topic can't be saved"
            flash.error = "Topic can't be saved"
        }
        render jsonObj as JSON
    }

    def invite(Long topic, String email) {
        if (topic && email) {
            if (emailService.invite(topic, email)) {
                flash.message = "Invitation Sent"
            } else {
                flash.error = "Invitation not Sent"
            }
        }
        redirect(controller: 'login', action: 'index')
    }

    def join(Long id) {
        Topic topic = Topic.get(id)
        if (topic && session.user) {
            Subscription subscription = new Subscription(user: session.user, topic: topic)

            if (subscription.validate()) {
                subscription.save(flush: true)
                flash.message = "You have subscribed to this topic successfully."
            } else
                flash.error = "Failure. Could not subscribe to the topic."
        }
        redirect(controller: "login", action: "index")
    }

    def delete(Long id) {

        Topic topic = Topic.get(id)
        User user = session.user

        if (topic) {
            if (user.admin || (topic.createdBy.id == user.id)) {
                topic.delete(flush: true)
                flash.message = "Successfully deleted"
            } else
                flash.error = "Can't be deleted"
        } else
            flash.error = "Topic not found"

        redirect(controller: 'user', action: 'index')
    }

    def titleUpdate(Long id, String topicName) {
        Topic topic = Topic.get(id)
        Map json = [:]
        if (topicName) {
            topic.name = topicName
        }
        if (topic.save(flush: true)) {
            json.message = "Topic Name Updated"
        } else {
            json.error = "Can't update !! Topic Name Already exists"
        }
        render json as JSON
    }
}
