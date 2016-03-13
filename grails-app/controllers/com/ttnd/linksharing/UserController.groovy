package com.ttnd.linksharing

import com.ttnd.linksharing.CO.ResourceSearchCO
import com.ttnd.linksharing.CO.TopicSearchCO
import com.ttnd.linksharing.CO.UpdatePasswordCO
import com.ttnd.linksharing.CO.UserCO
import com.ttnd.linksharing.CO.UserSearchCO
import com.ttnd.linksharing.VO.TopicVO
import com.ttnd.linksharing.VO.UserVO

class UserController {
    def assetResourceLocator
    def subscriptionService
    def topicService
    def emailService

    def index() {
        User user = session.user
        List<ReadingItem> readingItems = ReadingItem.findAllByUser(user)
        render view: 'index', model: [subList: user.getSubscribedTopic(), topics: Topic.getTrendingTopics(),
                                      items  : readingItems, user: user, topicList: user.getSubscribedTopic()]
        //render("user dashboard ${session.user}")

    }

    def image(Long id) {
        User user = User.get(id)
        byte[] photo

        if (user?.photo) {
            photo = user.photo
        } else {
            photo = assetResourceLocator.findAssetForURI('default.png').byteArray
        }

        OutputStream out = response.getOutputStream()
        out.write(photo)
        out.flush()
        out.close()
    }

    def register(UserCO co) {

        def photo = request.getFile('photo')
        co.photo = photo.bytes
        User user = new User()
        user.properties = co.properties

        if (user.save(flush: true)) {
            flash.message = "User ${user.username} is saved successfully"
        } else {
            flash.error = "User cant be saved"
        }
        redirect controller: 'login', action: 'index'
    }

    def profile(ResourceSearchCO resourceSearchCO) {
        User user = User.get(resourceSearchCO.userId)
        if (session.user) {
            if (!(session.user.admin || (session.user.equals(User.load(resourceSearchCO.userId))))) {
                resourceSearchCO.visibility = Visibility.PUBLIC
            }

        } else {
            resourceSearchCO.visibility = Visibility.PUBLIC
        }

        List<Resource> resources = Resource.search(resourceSearchCO).list()
        render view: 'profile', model: [user: user, resources: resources]
    }

    def topics(Long id) {
        List<TopicVO> createdTopics = []
        TopicSearchCO topicSearchCO = new TopicSearchCO(userId: id)
        if (session.user) {
            if (!(session.user.admin || session.user.equals(User.load(id)))) {
                topicSearchCO.visibility = Visibility.PUBLIC
            }

        } else {
            topicSearchCO.visibility = Visibility.PUBLIC
        }
        createdTopics = topicService.search(topicSearchCO)
        render template: "/topic/list", model: [topicList: createdTopics]
    }

    def subscriptions(Long id) {
        TopicSearchCO topicSearchCO = new TopicSearchCO(userId: id)
        if (session.user) {
            if (!(session.user.admin || (session.user.equals(User.load(id))))) {
                topicSearchCO.visibility = Visibility.PUBLIC
            }

        } else {
            topicSearchCO.visibility = Visibility.PUBLIC
        }

        List<TopicVO> subscribedTopics = subscriptionService.search(topicSearchCO)
        render template: "/topic/list", model: [topicList: subscribedTopics]
    }

    def list(UserSearchCO co) {
        if (session.user.admin) {
            List<UserVO> normalUsers = []
            List<User> users = User.search(co).list([sort: co.sort, order: co.order])
            users.each {
                normalUsers.add(new UserVO(id: it.id, name: it.username, firstName: it.firstName, lastName: it.lastName, email: it.email, isActive: it.active))
            }
            render view: "list", model: [users: normalUsers]
        } else {
            flash.error = "permission denied"
            redirect controller: "user", action: "index"
        }
    }

    def toggleActive(Long id) {
        User user = User.get(id)
        if (user && !user.admin) {
            user.active = !user.active
            user.save(validate: false, flush: true)
            flash.message = "successfully changed"
        } else {
            flash.error = "Permission Denied"
        }
        redirect(controller: "user", action: "list")

    }

    def forgotPassword(String email) {
        if (email) {
            if (emailService.forgotPassword(email)) {
                flash.message = "Email Has Been Sent"
                render(view: '/login/index', model: [topPosts: Resource.getTopPost(), recentShares: Resource.getRecentShare()])
            } else {
                flash.error = "Email can't be Sent"
                render(view: '/login/index', model: [topPosts: Resource.getTopPost(), recentShares: Resource.getRecentShare()])
            }
        } else {
            flash.error = "Please Enter an Email"
            redirect(controller: 'login', action: 'index')
        }
    }

    def edit() {
        render view: 'edit', model: [user: session.user]
    }

    def save(UserCO co) {
        User user = User.findByEmail(co.email)
        if (user) {
            user.firstName = co.firstName
            user.lastName = co.lastName
            co.photo = request.getFile('photo').bytes
            if (co.photo) {
                user.photo = co.photo
            }
            if (user.save(flush: true, validate: false)) {
                flash.message = "Profile updated successfully"
                session.user = user
            } else {
                flash.error = "Error updating profile"
            }
        } else {
            flash.error = "User Not Found"
        }
        redirect(controller: "user", action: "edit")
    }

    def updatePassword(UpdatePasswordCO co) {
        User user = co.getUser()
        if (user.password == co.oldPassword) {
            println "old password is correct"
            co.validate()
            if (co.hasErrors()) {
                flash.error = "Errors in form"
            } else {
                user.password = co.password
                if (user.save(flush: true,validate: false)) {
                    flash.message = "Password changed successfully"
                    session.user = user
                } else {
                    flash.error = "Error in updating password"
                }
            }
        } else {
            flash.error = "Old Password do not match!"
        }
        redirect(controller: "user", action: "edit")
    }


}
