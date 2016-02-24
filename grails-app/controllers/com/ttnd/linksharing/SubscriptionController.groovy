package com.ttnd.linksharing


class SubscriptionController {

    def index() {
        render "Subscription index"
    }

    def save(Long id) {
        Topic topic = Topic.get(id)
        topic ?: render("Topic not found")
        Subscription subscription = new Subscription(topic: topic, user: session.user)
        if (subscription.validate()) {
            subscription.save(flush: true)
            render("subscription saved successfully")
        } else {
            render("subscription cant be saved")
            render(subscription.errors.allErrors)
        }
    }

    def delete(Long id) {
        Subscription subscription = Subscription.load(id)
        try {
            subscription.delete()
            render("Susbcription deleted successfully")
        } catch (Exception e) {
            log.error "Error : ${e.message}"
            render("Subscription not found")
        }
    }

    def update(Long id, String seriousness) {
        Subscription subscription = Subscription.get(id)
        if (subscription ?: render("Subscription not found")) {
            subscription.seriousness = Seriousness.convert(seriousness)
            if (subscription.save(flush: true)) {
                render("Successfully updated $subscription $subscription.seriousness")
            } else {
                render("Cant be updated")
            }
        } else {
            render("Subscription not found")
        }
    }
}
