package com.ttnd.linksharing

import com.ttnd.linksharing.DTO.EmailDTO
import com.ttnd.linksharing.util.PasswordGenerator
import org.springframework.web.context.request.RequestContextHolder
import grails.transaction.Transactional

@Transactional
class EmailService {
    def mailService
    def userService
    def groovyPageRenderer
    def grailsApplication

    def sendMail(EmailDTO emailDTO) {
        mailService.sendMail {
            to emailDTO.to.toArray()
            subject emailDTO.subject
            if (emailDTO.content) {
                html emailDTO.content
            } else {
                body(view: emailDTO.view, model: emailDTO.model)
            }
        }
    }

    def forgotPassword(String email) {
        User user = User.findByEmail(email)
        if (user && userService.isActive(user.id)) {
            String newPassword = PasswordGenerator.getRandomPassword()
            if (newPassword) {
                EmailDTO emailDTO = new EmailDTO()
                emailDTO.to = []
                emailDTO.to.add(email)
                emailDTO.subject = "Forgot Password Mail"
                emailDTO.content = groovyPageRenderer.render(template: '/login/password', model: [userName: user.username, newPassword: newPassword])
                if (sendMail(emailDTO)) {
                    user.password = newPassword
                    if (user.save(flush: true, validate: false)) {
                        return true
                    }
                }
            }
        }
        return false
    }

    def invite(Long id, String email) {
        Topic topic = Topic.get(id)
        User user = User.findByEmail(email)
        if (topic && user) {
            def session = RequestContextHolder.currentRequestAttributes().getSession()
            EmailDTO emailDTO = new EmailDTO()
            emailDTO.to = []
            emailDTO.to.add(email)
            emailDTO.subject = "Topic Invitation"
            emailDTO.content = groovyPageRenderer.render(template: '/topic/invite', model: [userName: session.user
                    .firstName, topicName                                                           : topic.name, email: email, topicId: topic.id, base: grailsApplication.config
                    .grails.serverBaseURL])
            if (sendMail(emailDTO)) {
                return true
            }
        } else {
            return false
        }
    }

    def sendUnreadResources(User user,List<Resource> unreadReadingItems){
        EmailDTO emailDTO=new EmailDTO()
        emailDTO.to=[user.email]
        emailDTO.subject="Unread Items"
        emailDTO.content=groovyPageRenderer.render(template: '/resource/unreadResources',model:[user:user,unreadResources:unreadReadingItems])
        sendMail(emailDTO)
    }


}
