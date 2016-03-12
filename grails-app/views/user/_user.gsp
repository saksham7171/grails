<div class="well">
    <div class="row">
        <div class="col-xs-4">
            %{--<div class="glyphicon glyphicon-user" style="font-size:70px"></div>--}%
            <ls:userImage userId="${user.id}"/>
        </div>

        <div class="col-xs-8">
            <div class="h4">${user.username}</div>

            <p class="text-muted ">@${user.username} 5min</p>

            <p class="text-muted inline">Subscriptions</p>

            <p class="text-muted inline" style="float:right">Topics</p>
            <br>

            <p class="text-info inline"><ls:subscriptionCount userId="${user.id}"></ls:subscriptionCount> </p>

            <p class="text-info inline" style="float:right"><ls:topicCount userId="${user.id}"></ls:topicCount></p>

        </div>
    </div>

</div>