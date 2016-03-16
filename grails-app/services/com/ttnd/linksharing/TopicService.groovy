package com.ttnd.linksharing

import com.ttnd.linksharing.CO.TopicSearchCO
import com.ttnd.linksharing.VO.TopicVO
import grails.transaction.Transactional

@Transactional
class TopicService {

    def search(TopicSearchCO co) {
        List<TopicVO> createdTopics = []

        if (co.userId) {
            List<Topic> topicList = Topic.createCriteria().list(max: co.max) {
                eq('createdBy.id', co.userId)

                if (co.visibility) {
                    eq('visibility', co.visibility)
                }
            }

            topicList.each {
                topic -> createdTopics.add(new TopicVO(id: topic.id, name: topic.name, visibility: topic.visibility, createdBy: topic.createdBy))
            }

        }
        return createdTopics
    }
}
