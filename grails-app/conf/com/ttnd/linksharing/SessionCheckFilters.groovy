package com.ttnd.linksharing

class SessionCheckFilters {

    def filters = {
//        all(controller: 'login|console|topic', uriExclude: '/user/image/', invert: true) {
//            before = {
//                if (!session.user)
//                    redirect(controller: 'Login', action: 'index')
//            }
//            after = { Map model ->
//
//            }
//            afterView = { Exception e ->
//
//            }
//        }
        loginCheck(controller: '*', action: 'save|delete|update|changeIsRead|join') {
            before = {
                if (!session.user)
                    redirect(controller: "login", action: "index")
            }

        }

        userIndexcheck(controller: 'user', action: 'index|toggleActive|edit|updatePassword') {
            before = {

                if (!session.user)
                    redirect(controller: "login", action: "index")
            }
        }


        consoleCheck(controller: "console", action: "*") {
            before = {

                if (!(session.user?.admin))
                    redirect(controller: "login", action: "index")
            }
        }


    }

}
