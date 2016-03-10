package com.ttnd.linksharing

import com.ttnd.linksharing.CO.ResourceSearchCO
import com.ttnd.linksharing.VO.RatingInfoVO
import com.ttnd.linksharing.VO.TopicVO

class ResourceController {

    def index() {
        render "Resource"
    }

    def delete(Long id) {
        if (User.canDeleteResource(session.user, id)) {
            Resource resource = Resource.load(id)
            try {
                if (resource.deleteResource()) {
                    flash.message = "Resource deleted Successfully"
                } else
                    flash.error = "Resource not deleted"

            } catch (Exception e) {
                log.error "Error : ${e.message}"
                render "Resource can't be deleted"
            }
        } else flash.error = "Resource deletion not allowed"
        redirect(uri: '/')
    }

    def search(ResourceSearchCO co) {
        if (co.q) {
            co.visibility = Visibility.PUBLIC
            render(Resource.search(co).list())
        }
        flash.error = "No criteria provided"
    }

    def show(Long id) {
        List<Topic> topics = Topic.getTrendingTopics()
        Resource resource = Resource.get(id)
        RatingInfoVO vo = resource.ratingInfo
        vo
        if (resource.canViewBy(session.user)) {
            render view: "show", model: [resource: resource, topics: topics]
        }
//        render(vo.totalVotes + " " + vo.averageScore + " " + vo.totalScore)
    }

    def showTrendingTopics() {
        List<TopicVO> list = Topic.getTrendingTopics()
        list
    }

    private def addToReadingItems(Resource resource) {

        ReadingItem readingItem
        Topic topic = resource.topic
        List<User> subscribedUsers = topic.getSubscribedUsers()
        subscribedUsers.each { user ->
            if (user.id == resource.createdBy.id) {
                readingItem = new ReadingItem(user: user, resource: resource, isRead: true)
            } else {
                readingItem = new ReadingItem(user: user, resource: resource, isRead: false)
            }
            if (readingItem.save(flush: true)) {
                println "added to reading items"
            }

        }


    }
}
