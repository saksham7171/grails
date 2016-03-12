package com.ttnd.linksharing.CO

import com.ttnd.linksharing.User
import com.ttnd.linksharing.Visibility

class TopicSearchCO extends SearchCO {
    Long userId
    Visibility visibility

    User getUser() {
        return User.get(userId)
    }
}
