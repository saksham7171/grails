import com.ttnd.linksharing.DocumentResource
import com.ttnd.linksharing.LinkResource
import com.ttnd.linksharing.Resource
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
        List<Resource> resources = createResources()
//        subscribeTopics(users, topics)
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
        List<User> users = User.list()
        List<Topic> topics = []

        users.each { user ->
            if (!user.topics?.size()) {
                (1..5).each {
                    Topic topic = new Topic(name: "topic" + it, visibility: Visibility.PUBLIC, createdBy: user)
                    if(topic.save()) {
                        user.addToTopics(topic)
                        topics.add(topic)
                        log.info("$topic is created successfully")
                    }
                    else log.info "$topic cant be created"
                }
            }
        }
        topics
    }

    List<Resource> createResources() {
        List<Resource> resources = []
        List<Topic> topics=Topic.list()

        if (!Resource.list()) {
            topics.each {topic->
                2.times {
                    Resource linkresource=new LinkResource(description:"link resource for ${topic.name}",
                            createdBy:topic.createdBy,topic:topic ,url:"https://www.google.com/")
                    Resource documentresource=new DocumentResource(description:"document resource for ${topic.name}",
                            createdBy:topic.createdBy,topic:topic ,filePath:"/home/saksham" )
                    if(linkresource.save())
                    {
                        resources.add(linkresource)
                        topic.addToResources(linkresource)
                        topic.createdBy.addToResources(linkresource)
                        log.info "${linkresource} saved successfullly by ${topic.createdBy} for $topic"
                    }
                    else
                        log.error "${linkresource} can't be saved ${linkresource.errors.allErrors}"
                    if(documentresource.save()){
                        resources.add(documentresource)
                        topic.addToResources(documentresource)
                        topic.createdBy.addToResources(documentresource)
                        log.info "${documentresource} saved successfully by ${topic.createdBy} for $topic"
                    }
                    else
                        log.error "${documentresource} can't be saved ${documentresource.errors.allErrors}"
                }
            }
        }
        resources
    }

//    void subscribeTopics(List<User> users, List<Topic> topics) {
//
//        if (!Subscription.count()) {
//            users.each { user ->
//                List<User> otherUsers = User.findAllByIdNotEqual(user,[max:user.id])
//                otherUsers.each {otherUser->
//                    otherUser.addToSubscriptions()
//                }
//            }
//        }
//
    def destroy = {

    }
}

