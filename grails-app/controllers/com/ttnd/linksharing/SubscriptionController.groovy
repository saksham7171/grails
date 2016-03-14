package com.ttnd.linksharing

import grails.converters.JSON


class SubscriptionController {

    def index() {
        render "Subscription index"
    }

    def save(Long id) {
        User user = session.user
        Topic topic = Topic.get(id)
        if (topic ?: render("Topic not found")) {
            Subscription subscription = new Subscription(topic: topic, user: user)
            if (subscription.validate()) {
                subscription.save(flush: true)
                flash.message = "subscription saved successfully"
            } else {
                flash.error = "Can't Subscribe"
            }
        }
        redirect(uri: '/')
    }

    def delete(Long id) {

        Subscription subscription = Subscription.load(id)
        if (subscription.topic.createdBy.id != session.user.id) {
            try {
                subscription.delete(flush: true)
                flash.message = "Susbcription deleted successfully"
            } catch (Exception e) {
                log.error "Error : ${e.message}"
                flash.error = "Subscription not found"
            }
        } else {
            flash.error = "Can't Unsubscribe Created Topic"
        }
        redirect(uri: '/')
    }

    def update(Long id, String seriousness) {
        Map jsonObj = [:]
        Subscription subscription = Subscription.findByUserAndTopic(session.user, Topic.load(id))
        if (subscription ?: render("Subscription not found")) {                 //todo correct if statement
            subscription.seriousness = Seriousness.convert(seriousness)
            if (subscription.save(flush: true)) {
                jsonObj.message = "Successfully updated"
            } else {
                jsonObj.error = "Cant be updated"
            }
        }
        render jsonObj as JSON
    }
}
