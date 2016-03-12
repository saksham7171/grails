package com.ttnd.linksharing

class ReadingItemController {

    def index() {}

    def changeIsRead(Long resourceId) {
        User user = session.user
        ReadingItem readingItem = ReadingItem.findByUserAndResource(user, Resource.get(resourceId))
        if (ReadingItem.executeUpdate("update ReadingItem set isRead=:isRead where id=:id",
                [isRead: !readingItem.isRead, id: readingItem.id])) {
        }
        redirect(uri: '/')
    }
}
