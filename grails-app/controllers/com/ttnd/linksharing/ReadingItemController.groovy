package com.ttnd.linksharing

import grails.converters.JSON

class ReadingItemController {

    def index() {}

    def changeIsRead(Long resourceId) {
        User user = session.user
        Map jsonObj = [:]
        ReadingItem readingItem = ReadingItem.findByUserAndResource(user, Resource.get(resourceId))
        if (ReadingItem.executeUpdate("update ReadingItem set isRead=:isRead where id=:id",
                [isRead: !readingItem.isRead, id: readingItem.id])) {
            jsonObj.success = "Success"
        } else {
            jsonObj.error = "failure"
        }
        JSON jsonResponse = jsonObj as JSON

        //redirect(uri: '/')
     render jsonResponse
    }
}
