<g:each in="${topicList}" var="topic">
    <g:render template="/user/trendingTopics" model="[topic: topic]"/>
</g:each>