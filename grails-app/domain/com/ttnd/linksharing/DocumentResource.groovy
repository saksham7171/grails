package com.ttnd.linksharing

import com.ttnd.linksharing.constants.Constants

class DocumentResource extends Resource {
    String filePath
    String contentType
    String fileName

    static transients = ['contentType','fileName']

    static constraints = {
        filePath(filePath: true, blank: false)
        contentType(matches: Constants.DOCUMENT_CONTENT_TYPE)
    }

    String toString() {
        return "$filePath"
    }

    String getFileName(){
        filePath.substring(filePath.lastIndexOf('/'))
    }
}
