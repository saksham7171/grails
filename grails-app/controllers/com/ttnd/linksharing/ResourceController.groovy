package com.ttnd.linksharing

class ResourceController {

    def index() {
        render "Resource"
    }

    def delete(Long id){
        Resource resource=Resource.load(id)
        try {

            if (!resource.delete()) {
                render("Resource deleted with id : $id")
            }
        } catch (Exception e) {
            log.error "Error : ${e.message}"
            render "User can't be deleted"
        }
    }

}
