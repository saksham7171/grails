package com.ttnd.linksharing

class UserController {

    def index() {
        User user = session.user
        /*render view: 'index',model: [topiclist:user.getSubscribedTopic(),trendingTopics:Topic.getTrendingTopics()]*/
        render("user dashboard ${session.user}")
    }
}
