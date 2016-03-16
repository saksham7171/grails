package com.ttnd.linksharing.CO

import com.ttnd.linksharing.User
import com.ttnd.linksharing.Visibility

class ResourceSearchCO extends SearchCO {
    Long userId
    Long topicId
    Visibility visibility

    User getUser() {
        return User.get(userId)
    }
}
