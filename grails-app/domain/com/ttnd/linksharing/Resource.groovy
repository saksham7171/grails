package com.ttnd.linksharing

import com.ttnd.linksharing.CO.ResourceSearchCO
import com.ttnd.linksharing.VO.RatingInfoVO
import com.ttnd.linksharing.VO.TopicVO

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
                'topic'{
                    eq('visibility', co.visibility)
                }
            }
        }
    }

    RatingInfoVO getRatingInfo(){
        List result=ResourceRating.createCriteria().get {
            projections {
                count('score')
                avg('score')
                sum('score')
            }
            eq('resource',this)
        }
        new RatingInfoVO(totalVotes: result[0],averageScore: result[1],totalScore: result[2])
    }

   static List<Resource> getTopPost(){
        List<Resource> resources=[]
        def result=ResourceRating.createCriteria().list(){
            projections {
                property('resource.id')
            }
            groupProperty('resource.id')
            count('id','totalVotes')
            order('totalVotes','desc')
            maxResults 5
        }
        List list=result.collect{it[0]}
        resources=Resource.getAll(list)
    }

}
