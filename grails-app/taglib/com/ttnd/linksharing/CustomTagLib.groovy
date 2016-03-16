package com.ttnd.linksharing

class CustomTagLib {
    // static defaultEncodeAs = [taglib: 'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    static namespace = "ls"

    def isRead = { attrs, body ->
        User user = session.user
        Resource resource = Resource.get(attrs.id)
        ReadingItem readingItem = ReadingItem.findByUserAndResource(user, resource)
        Boolean read = readingItem?.isRead
        if (user && readingItem) {
            if (read) {
                body = "<u>&nbspMark as Unread</u>"
            } else {
                body = "<u>&nbspMark as Read</u>"
            }
            out << g.link(body, name: "readingItem", class: "readingItem", style: "float:right", resourceId: resource.id)
        }
    }

    def resourceType = { attrs, body ->
        def resourceType = Resource.checkResourceType(attrs.id)
        def link = attrs.url
        if (resourceType == "LinkResource") {
            out << "<a href='${link}' target='_blank' class='pull-right'><u>View Full Site</u></a>"
        } else if (resourceType == "DocumentResource") {
            out << "<a href='${createLink(controller: 'documentResource', action: 'download', params: [id: attrs.id])}' class='pull-right'><u>Download</u></a>"
        }
    }

    def canDeleteResource = { attrs, body ->
        User user = attrs.user
        Long resourceId = attrs.id
        if (user) {
            if (User.canDeleteResource(user, resourceId)) {
                out << "<a href='${createLink(controller: 'resource', action: 'delete', params: [id: resourceId])}' class='pull-right'><u>Delete</u></a>"
            }
        }

    }

    def subscriptionCount = { attrs, body ->
        Long userId = attrs.userId
        Long topicId = attrs.topicId
        if (userId) {
            out << Subscription.countByUser(User.load(userId))
        } else if (topicId) {
            out << Subscription.countByTopic(Topic.load(topicId))
        }
    }

    def resourcecCount = { attrs, body ->
        Long topicId = attrs.topicId
        if (topicId) {
            out << Resource.countByTopic(Topic.load(topicId))
        }
    }

    def topicCount = { attrs, body ->
        Long userId = attrs.userId
        if (userId) {
            out << Topic.countByCreatedBy(User.load(userId))
        }
    }

    def showSubscribe = { attrs, body ->
        User user = User.get(attrs.userId)
        Long topicId = attrs.topicId

        if (user && topicId) {
            if (user.isSubscribed(topicId)) {
                Subscription subscription = Subscription.findByUserAndTopic(user, Topic.get(topicId))
                out << "<a href='${createLink(controller: 'subscription', action: 'delete', params: [id: subscription.id])}'><u>Unsubscribe</u></a>"
            } else {
                out << "<a href='${createLink(controller: 'subscription', action: 'save', params: [id: topicId])}'><u>Subscribe</u></a>"
            }
        }
    }

    def userImage = { attrs, body ->
        out << "<img src=\"/user/image/${attrs.userId}\" width=\"100\" height=\"100\"/>"
    }

    def canUpdateTopic = { attrs, body ->
        User user = User.get(attrs.userId)
        Topic topic = Topic.get(attrs.topicId)

        if (user?.admin || user?.equals(topic.createdBy)) {
            out << body()
        }
    }

    def showSeriousness = { attrs, body ->
        Long topicId = attrs.topicId
        User user = session.user
        Subscription sub = user?.getSubscription(attrs.topicId)

        if (sub) {
            out << g.select(name: "seriousness", id: "seriousness", from: Seriousness.values(), value: sub.seriousness, optionKey: "key", class: "btn btn-sm btn-default dropdown-toggle seriousness", topicId: topicId)
        }
    }

    def showVisibility = { attrs, body ->
        Long topicId = attrs.topicId
        Topic topic = Topic.get(topicId)
        out << g.select(name: "visibility", id: "visibility", from: Visibility.values(), value: topic.visibility, optionKey: "key", class: "btn btn-sm btn-default dropdown-toggle visibility", topicName: topic.name)
    }

    def showTopicList = { attrs, body ->
        User user = session.user
        out << g.select(name: "topic", from: user.getSubscribedTopic(), optionKey: "id", class: "btn btn-default")
    }
}


