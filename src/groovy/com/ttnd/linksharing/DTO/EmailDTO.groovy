package com.ttnd.linksharing.DTO

import grails.validation.Validateable

@Validateable
class EmailDTO {
    List<String> to
    String subject
    String view
    String content
    Map model
}