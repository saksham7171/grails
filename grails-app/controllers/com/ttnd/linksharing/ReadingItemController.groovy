package com.ttnd.linksharing

import grails.converters.JSON

class ReadingItemController {


    def changeIsRead(Long resourceId) {
        User user = session.user
        Map jsonObj = [:]
        ReadingItem readingItem = ReadingItem.findByUserAndResource(user, Resource.get(resourceId))
        jsonObj.status = readingItem.isRead

        if (ReadingItem.executeUpdate("update ReadingItem set isRead=:isRead where id=:id",
                [isRead: !readingItem.isRead, id: readingItem.id])) {
            jsonObj.status = !jsonObj.status
            jsonObj.message = "Success"
        } else {
            jsonObj.error = "failure"
        }
        JSON jsonResponse = jsonObj as JSON
        render jsonResponse
    }
}
