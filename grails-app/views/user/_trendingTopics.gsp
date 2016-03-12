<div class="row">
    <div class="col-xs-4">
        <div class="panel panel-default">
            <div class="panel-heading">
                Trending Topics <a href="#" class="inline" style="float:right">view all</a>
            </div>
            <g:each in="${trendingTopics}" var="trendingTopic">
            <div class="panel-body">
                <div class="row">
                    <div class="col-xs-2">
                        <div class="glyphicon glyphicon-user" style="font-size:50px"></div>
                    </div>

                    <div class="col-xs-9 col-xs-offset-1">
                        <a href="#">${trendingTopic.name}</a>
                        <br>

                        <p class="text-muted newinline">@${trendingTopic.createdBy}</p>

                        <p class="text-muted newinline">Subscriptions</p>

                        <p class="text-muted newinline" style="float:right">Posts</p>
                        <br>
                        <a href="#" class="newinline">subscribe</a>

                        <p class="text-info newinline">50</p>

                        <p class="text-info newinline" style="float:right">${trendingTopic.count}</p>
                    </div>
                    <a href="#"><div class="glyphicon glyphicon-trash inline" style="float:right;margin-top:12px"></div>
                    </a>
                    <a href="#"><div class="glyphicon glyphicon-pencil inline"
                                     style="float:right;margin-top:12px"></div></a>
                    <a href="#"><div class="glyphicon glyphicon-envelope inline"
                                     style="float:right;margin-top:12px"></div></a>

                    <div class="dropdown inline" style="float:right;margin-top:5px">
                        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1"
                                data-toggle="dropdown" aria- haspopup="true" aria-expanded="true">
                            Private
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            <li><a href="#">Private</a></li>
                            <li><a href="#">Public</a></li>

                        </ul>
                    </div>

                    <div class="dropdown inline" style="float:right;margin-top:5px">
                        <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1"
                                data-toggle="dropdown" aria- haspopup="true" aria-expanded="true">
                            Serious
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            <li><a href="#">Serious</a></li>
                            <li><a href="#">Serious1</a></li>

                        </ul>
                    </div>
                </div>
                <hr>
            </g:each>
            </div>
        </div>
    </div>
</div>