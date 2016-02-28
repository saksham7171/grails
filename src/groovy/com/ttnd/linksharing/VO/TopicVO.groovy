package com.ttnd.linksharing.VO

import com.ttnd.linksharing.User
import com.ttnd.linksharing.Visibility

class TopicVO {
    Integer id
    String name
    Visibility visibility
    Integer count
    User createdBy

    String toString(){
        return "$id,$name,$visibility,$count,$createdBy ------"
    }
}
