package com.ttnd.linksharing

class DocumentResource extends Resource {
    String filePath
    static constraints = {
        filePath(filePath: true, blank: false)
    }
    String toString(){
        return "$filePath"
    }
}
