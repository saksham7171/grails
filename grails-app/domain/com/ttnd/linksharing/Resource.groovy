package com.ttnd.linksharing

import com.ttnd.linksharing.CO.ResourceSearchCO
import com.ttnd.linksharing.VO.RatingInfoVO

abstract class Resource {
    String description
    User createdBy
    Date dateCreated
    Date lastUpdated

    static transients = ['ratingInfo']
    static belongsTo = [topic: Topic]
    static hasMany = [readingItems: ReadingItem, resourceRatings: ResourceRating]

    static constraints = {
        createdBy(blank: false)
        description(blank: false)
        topic(blank: false)

    }
    static namedQueries = {
        search { ResourceSearchCO co ->
            if (co.topicId) {
                eq('topic', Topic.get(co.topicId))
            }
            if (co.visibility) {
                'topic' {
                    eq('visibility', co.visibility)
                }
            }
            if (co.userId) {
                eq('createdBy.id', co.userId)
            }
            if (co.q) {
                ilike('description', "%${co.q}%")
            }
        }
    }

    RatingInfoVO getRatingInfo() {
        List result = ResourceRating.createCriteria().get {
            projections {
                count('score')
                avg('score')
                sum('score')
            }
            eq('resource', this)
        }
        new RatingInfoVO(totalVotes: result[0], averageScore: result[1], totalScore: result[2])
    }

    static List<Resource> getTopPost() {
        List<Resource> resources = []
        def result = ResourceRating.createCriteria().list {
            projections {
                property('resource.id')
            }
            createAlias('resource', 'r')
            createAlias('r.topic', 'rt')
            groupProperty('r.id')
            eq('rt.visibility', Visibility.PUBLIC)
            avg('score', 'score')
            order('score', 'desc')
            maxResults 5
        }
        List list = result.collect { it[0] }
        resources = Resource.getAll(list)

    }

    static List<Resource> getRecentShare() {
        def result = Resource.createCriteria().list {
            createAlias('topic', 't')
            eq('t.visibility', Visibility.PUBLIC)
            order('lastUpdated', 'desc')
            maxResults 5
        }
        result
    }

    static def checkResourceType(Long id) {
        Resource resource = Resource.read(id)
        if (resource.class == (LinkResource))
            return "LinkResource"
        else if (resource.class == (DocumentResource))
            return "DocumentResource"
    }

    Boolean canViewBy(User user) {
        if (this.topic.canViewedBy(user))
            return true
        else {
            false
        }
    }

    def deleteResource() {
        log.info "Resource deleted"
    }
}
