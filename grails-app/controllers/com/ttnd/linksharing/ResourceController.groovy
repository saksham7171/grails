package com.ttnd.linksharing

import com.ttnd.linksharing.CO.ResourceSearchCO
import com.ttnd.linksharing.VO.RatingInfoVO
import com.ttnd.linksharing.VO.TopicVO

class ResourceController {

    def index() {
        render "Resource"
    }

    def delete(Long id) {
        Resource resource = Resource.load(id)
        try {

            if (!resource.delete()) {
                render("Resource deleted with id : $id")
            }
        } catch (Exception e) {
            log.error "Error : ${e.message}"
            render "Resource can't be deleted"
        }
    }

    def search(ResourceSearchCO co) {
        if (co.q) {
            co.visibility = Visibility.PUBLIC
            render(Resource.search(co).list())
        }
    }

    def show(Long id) {
        render("show rating")
        Resource resource=Resource.get(id)
        RatingInfoVO vo = resource.ratingInfo
        render(vo.totalVotes + " " + vo.averageScore + " " + vo.totalScore)
    }

    def showTrendingTopics(){
        List<TopicVO> list=Topic.getTrendingTopics()
        render list
    }

    def saveLinkResource(LinkResource linkResource){
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

    def saveDocumentResource(String description,String topicName,String filePath){
        User user=session.user
        Topic topic=Topic.findByNameAndCreatedBy(topicName,user)
        Resource documentResource=new DocumentResource(description: description,filePath: filePath,createdBy: user,topic: topic)
        if(documentResource.validate()){
            documentResource.save(flush:true)
            flash.message="Resource saved"
            render(flash.message)
        }
        else{
            flash.error="Resource cant be saved"
            redirect(controller: 'user',action: 'index')
        }
    }

}
