package com.ttnd.linksharing

class LinkResourceController extends ResourceController {

    def index() {}
    def save(LinkResource linkResource){
        linkResource.createdBy=session.user
        if(linkResource.validate()){
            linkResource.save(flush:true)
            flash.message="Resource saved"
        }
        else{
            flash.error=linkResource.errors.allErrors.collect{message(error: it)}
        }
        redirect(controller: 'user',action: 'index')
    }
}
