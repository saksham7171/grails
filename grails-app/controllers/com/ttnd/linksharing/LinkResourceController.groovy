package com.ttnd.linksharing

class LinkResourceController extends ResourceController {


    def save(LinkResource linkResource) {
        linkResource.createdBy = session.user
        if (linkResource.validate()) {
            linkResource.save(flush: true)
            addToReadingItems(linkResource)
            flash.message = "Resource saved Successfully"
        } else {
            flash.error = "Resource Can't be Saved!! Please enter a valid Url"
        }
        redirect(controller: 'user', action: 'index')
    }


}
