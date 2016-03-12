package com.ttnd.linksharing

import com.ttnd.linksharing.VO.TopicVO


class Topic {
    String name
    User createdBy
    Date dateCreated
    Date lastUpdated
    Visibility visibility
    static hasMany = [resources: Resource, subscriptions: Subscription]

    static mapping = {
        sort name: "asc"

    }

    static constraints = {
        name(unique: "createdBy", blank: false)
        visibility(blank: false)
        createdBy(blank: false)
    }

    def afterInsert = {
        Topic.withNewSession {
            Subscription subscription = new Subscription(user: this.createdBy, topic: this, seriousness: Seriousness.VERY_SERIOUS)
            if (subscription.save())
                log.info "$subscription saved Successfully"
            else
                log.info "Error while saving subscriptions"
        }
    }

    String toString() {
        return "$name Createdby:$createdBy"
    }

    static List<TopicVO> getTrendingTopics() {
        List<TopicVO> topicVOList=[]

        List result = Resource.createCriteria().list {
            projections {
                createAlias('topic', 't')
                groupProperty('t.id')
                property('t.name')
                property('t.visibility')
                count('id', 'rcount')
                property('t.createdBy')
            }
            maxResults 5
            eq('t.visibility',Visibility.PUBLIC)
            order('t.name', 'desc')
            order('rcount', 'desc')
        }

        result.each{list->
          topicVOList.add(new TopicVO(id:list[0],name: list[1],visibility: list[2],count:list[3],createdBy: list[4]))
        }
        topicVOList
    }
}

