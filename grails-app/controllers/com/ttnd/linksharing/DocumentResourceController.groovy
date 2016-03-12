package com.ttnd.linksharing

import grails.transaction.Transactional
import org.springframework.web.multipart.MultipartFile

class DocumentResourceController extends ResourceController {

    def index() {}

    @Transactional
    def save(DocumentResource documentResource) {
        UUID fileName = UUID.randomUUID()
        String path = "${grailsApplication.config.linksharing.documents.folderPath}/${fileName}"
        documentResource.filePath = path
        documentResource.createdBy = session.user



        MultipartFile file = params.doc
        File directoryStructure = new File(grailsApplication.config.linksharing.documents.folderPath)
        if (!directoryStructure.exists()) {
            directoryStructure.mkdirs()
        }


        File savedFile = new File(path)
        documentResource.contentType = file.contentType
        documentResource.validate()
        if (documentResource.errors.getFieldError('contentType')) {
            flash.error = "Please upload pdf file"
        } else {
            file.transferTo(savedFile)

            if (documentResource.save(flush: true)) {
                addToReadingItems(documentResource)
                flash.message = "$documentResource saved in database"
            } else {
                flash.error = "Document cant be saved"
                //render(flash.error)
            }
        }
        redirect(controller: 'user', action: 'index')
    }

    def download(Long id) {
        Resource resource = Resource.get(id)
        if (resource) {
            File file = new File("${resource.filePath}")
            byte[] orderPDF = file.getBytes()
            response.setHeader("Content-disposition", "attachment; filename=" + file.name)
            response.contentLength = orderPDF.length
            response.outputStream << orderPDF
        } else {
            flash.error = "Resource not found"
        }
    }
}
