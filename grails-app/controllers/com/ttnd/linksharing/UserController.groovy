package com.ttnd.linksharing

import com.ttnd.linksharing.CO.UserCO

class UserController {
    def assetResourceLocator

    def index() {
        User user = session.user
        List<ReadingItem> readingItems = ReadingItem.findAllByUser(user)
        render view: 'index', model: [subList: user.getSubscribedTopic(), topics: Topic.getTrendingTopics(),
                                      items  : readingItems, user: user,topiclist:user.getSubscribedTopic()]
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
        def topPosts = Resource.getTopPost()
        def recentShares = Resource.getRecentShare()
        def photo = request.getFile('photo')
        co.photo = photo.bytes
        User user = new User()
        user.properties = co.properties

        if (!user.save(flush: true)) {
            flash.error = "User cant be saved"
        } else {
            flash.message = "User ${user.username} is saved successfully"
            session["user"] = user
        }
        render view: '/login/index', model: [topPosts: topPosts, recentShares: recentShares, user: user]
    }

}
