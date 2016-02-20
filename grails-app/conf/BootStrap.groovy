import com.ttnd.linksharing.DocumentResource
import com.ttnd.linksharing.LinkResource
import com.ttnd.linksharing.ReadingItem
import com.ttnd.linksharing.Resource
import com.ttnd.linksharing.ResourceRating
import com.ttnd.linksharing.Seriousness
import com.ttnd.linksharing.Subscription
import com.ttnd.linksharing.Topic
import com.ttnd.linksharing.User
import com.ttnd.linksharing.Visibility
import com.ttnd.linksharing.constants.Constants
import spock.util.mop.Use

import javax.xml.bind.ValidationException

class BootStrap {

    def init = { servletContext ->
        List<User> users = createUsers()
        List<Topic> topics = createTopics()
        List<Resource> resources = createResources(topics)
        List<Subscription> subscriptions = subscribeTopics(topics)
        List<ReadingItem> readingItems = createReadingItem(topics, resources)
        List<ResourceRating> resourceRatings = createResourceRating(readingItems)
    }

    List<User> createUsers() {

        User user = new User(email: "sakham.sharma@ttnd.com", username: "saksham", password: Constants.password,
                firstName: "saksham", lastName: "sharma", admin: true, active: true)
        User user1 = new User(email: "brij.kishor@ttnd.com", username: "brij", password: Constants.password,
                firstName: "brij", lastName: "kishor", admin: false, active: true)
        List<User> users = []

        if (!User.count()) {
            try {
                user.save(flush: true, failOnError: true)
                users.add(user)
                log.info "${user} is created successfully"
            } catch (ValidationException e) {
                log.error "Error : ${user.errors.allErrors}"
            }
            try {
                user1.save(flush: true, failOnError: true)
                users.add(user)
                log.info "${user1} is created successfully"
            } catch (ValidationException e) {
                log.error "Error : ${user1.errors.allErrors}"
            }
        }
        users
    }

    List<Topic> createTopics() {

        List<Topic> topics = []
        List<User> users = User.list()
        users.each { user ->
            if (!user.topics?.size()) {
                (1..5).each {
                    Topic topic = new Topic(name: "topic" + it, visibility: Visibility.PUBLIC, createdBy: user)
                    if (topic.save()) {
                        user.addToTopics(topic)
                        topics.add(topic)
                        log.info("$topic is created successfully")
                    } else log.info "$topic cant be created"
                }
            }
        }
        topics
    }

    List<Resource> createResources(List<Topic> topics) {
        List<Resource> resources = []


        if (!Resource.list()) {
            topics.each { topic ->
                2.times {
                    Resource linkresource = new LinkResource(description: "link resource for ${topic.name}",
                            createdBy: topic.createdBy, topic: topic, url: "https://www.google.com/")
                    Resource documentresource = new DocumentResource(description: "document resource for ${topic.name}",
                            createdBy: topic.createdBy, topic: topic, filePath: "/home/saksham")
                    if (linkresource.save()) {
                        resources.add(linkresource)
                        topic.addToResources(linkresource)
                        topic.createdBy.addToResources(linkresource)
                        log.info "${linkresource} saved successfullly by ${topic.createdBy} for $topic"
                    } else
                        log.error "${linkresource} can't be saved ${linkresource.errors.allErrors}"
                    if (documentresource.save()) {
                        resources.add(documentresource)
                        topic.addToResources(documentresource)
                        topic.createdBy.addToResources(documentresource)
                        log.info "${documentresource} saved successfully by ${topic.createdBy} for $topic"
                    } else
                        log.error "${documentresource} can't be saved ${documentresource.errors.allErrors}"
                }
            }
        }
        resources
    }

    List<Subscription> subscribeTopics(List<Topic> topics) {
        List<User> users = User.list()
        List<Subscription> subscriptions = []

        users.each { user ->
            topics.each { topic ->
                if (!Subscription.findByUserAndTopic(user, topic)) {
                    Subscription subscription = new Subscription(user: user, topic: topic, seriousness: Seriousness.SERIOUS)
                    if (subscription.save()) {
                        subscriptions.add(subscription)
                        user.addToSubscriptions(subscription)
                        topic.addToSubscriptions(subscription)
                        log.info "$user subscribed $subscription for Topic :$topic"
                    } else
                        log.info "Topic cant be subscribed"
                } else log.info "$topic already subscribed by $user"
            }

        }
        subscriptions
    }

    List<ReadingItem> createReadingItem(List<Topic> topics, List<Resource> resources) {
        List<ReadingItem> readingItems = []
        List<User> users = User.list()

        users.each { user ->
            topics.each { topic ->
                if (Subscription.findByUserAndTopic(user, topic)) {
                    topic.resources.each { resource ->
                        if (resource.createdBy != user && !user.readingItems?.contains(resource)) {
                            ReadingItem readingItem = new ReadingItem(user: user, resource: resource, isRead: false)
                            if (readingItem.save()) {
                                readingItems.add(readingItem)
                                resource.addToReadingItems(readingItem)
                                user.addToReadingItems(readingItem)
                                log.info "$readingItem saved Succesfully for $user"
                            } else {
                                log.info "$readingItem cant be saved"
                            }
                        }
                    }
                }
            }
        }
        readingItems
    }

    List<ResourceRating> createResourceRating(List<ReadingItem> readingItems) {
        List<ResourceRating> resourceRatings = []
        readingItems.each { readingItem ->
            if (!readingItem.isRead) {
                ResourceRating resourceRating = new ResourceRating(user: readingItem.user, score: 3, resource: readingItem.resource)
                if (resourceRating.save()) {
                    resourceRatings.add(resourceRating)
                    readingItem.user.addToResourceRatings(resourceRating)
                    readingItem.resource.addToResourceRatings(resourceRating)
                    log.info "$resourceRating given by ${readingItem.user} for ${readingItem.resource}"
                } else {
                    log.info "$resourceRating cant be given for ${readingItem.resource}"
                }
            }

        }
        resourceRatings
    }

    def destroy = {

    }
}

