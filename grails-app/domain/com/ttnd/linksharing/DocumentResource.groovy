package com.ttnd.linksharing

import com.ttnd.linksharing.constants.Constants

class DocumentResource extends Resource {
    String filePath
    String contentType
    String fileName

    static transients = ['contentType', 'fileName']

    static constraints = {
        filePath(filePath: true, blank: false)
        contentType(matches: Constants.DOCUMENT_CONTENT_TYPE)
    }

    String toString() {
        return "$filePath"
    }

    String getFileName() {
        fileName = filePath.substring(filePath.lastIndexOf('/'))
        return fileName
    }

    def deleteResource() {
        String filePath = this.filePath
        boolean fileDeleted = new File(filePath).delete()
        if (fileDeleted) {
            this.delete(flush: true)
            return true
        } else {
            return false
        }
    }
}
