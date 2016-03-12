package com.ttnd.linksharing

class CustomTagLib {
    static defaultEncodeAs = [taglib: 'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
    static namespace = "tl"

    def showAdmin = { attrs, body ->
        Boolean isAdmin = Boolean.valueOf(attrs.isAdmin)
        if (isAdmin) {
            out << body()
        }
    }

    def showUserList={attrs->

        out<<render(template: '/unit/userList',model: [user:attrs.userList])
    }
}
